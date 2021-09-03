package Asynchronus;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

@SuppressLint("NewApi")
public class JsonParser {
	HttpGet httpget;
	//HttpPost httppost;
	HttpResponse httpResponse;
	String resul;

	@SuppressLint("NewApi")
	public JSONObject executeHttpPost_server(String url, String method,
			ArrayList<String> param, ArrayList<String> value) {
		JSONObject jArray = null;
		try {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);

			HttpClient httpClient = new DefaultHttpClient();

			if (method.equalsIgnoreCase("get")
					|| method.equalsIgnoreCase("album_list")) {
				httpget = new HttpGet(url);
				httpResponse = httpClient.execute(httpget);
				jArray = getFunction();
			} else {
				HttpParams params = new BasicHttpParams();
				params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
						HttpVersion.HTTP_1_1);
				httpClient = new DefaultHttpClient(params);

				HttpPost request = new HttpPost(url);
				MultipartEntity multipart_entity = new MultipartEntity(
						HttpMultipartMode.BROWSER_COMPATIBLE);
				for (int i = 0; i < param.size(); i++) {
					if (param.get(i).toString().equals("photo") == true
							|| param.get(i).toString().equals("cphoto") == true
							|| param.get(i).toString().equals("cimage") == true
							|| param.get(i).toString().equals("image") == true) {
						if (value.get(i).toString().length() > 1) {
							multipart_entity.addPart(param.get(i).toString(),
									new FileBody(new File(value.get(i)
											.toString())));
						} else {
							multipart_entity.addPart(param.get(i).toString(),
									new StringBody(value.get(i).toString()));

						}
					} else if (param.get(i).toString().equals("cvideo") == true) {
						multipart_entity
								.addPart(param.get(i).toString(), new FileBody(
										new File(value.get(i).toString())));
					} else {
						multipart_entity.addPart(param.get(i).toString(),
								new StringBody(value.get(i).toString()));
					}
				}
				request.setEntity(multipart_entity);
				httpClient.execute(request, new PhotoUploadResponseHandler() {
					@Override
					public String handleResponse(HttpResponse response)
							throws ClientProtocolException, IOException {
						HttpEntity r_entity = response.getEntity();
						resul = EntityUtils.toString(r_entity);
						return resul;
					}
				});
				jArray = postFunction();
			}
		} catch (Exception e) {
			Log.e("exception", "exception" + e);
		}
		return jArray;
	}

	private JSONObject getFunction() {
		InputStream isb = null;
		String result = "";
		JSONObject jArray = null;

		HttpEntity resultentity = httpResponse.getEntity();
		try {
			isb = resultentity.getContent();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					isb, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			isb.close();
			result = sb.toString();

			jArray = new JSONObject(result);
		} catch (Exception e) {
		}
		return jArray;
	}

	private JSONObject postFunction() {
		JSONObject result = null;
		try {
			result = new JSONObject(resul);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public interface PhotoUploadResponseHandler extends ResponseHandler<String> {

	}

}