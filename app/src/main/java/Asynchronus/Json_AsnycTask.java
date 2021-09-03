package Asynchronus;

import java.util.ArrayList;

import org.json.JSONObject;

import android.content.Context;
import android.os.AsyncTask;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View.OnClickListener;

import androidx.fragment.app.Fragment;

public class Json_AsnycTask extends AsyncTask<String, Void, JSONObject> {
	Asnychronus_notifier listener;
	Context context;
	Fragment frag;
//	private TransparentProgressDialog pd;
	String method = "";
	ArrayList<String> parameter = new ArrayList<String>();
	ArrayList<String> parameterValue = new ArrayList<String>();

	public void setOnResultsListener(Asnychronus_notifier listener) {
		this.listener = listener;
	}

	public Json_AsnycTask(Context context, String meth,
			ArrayList<String> param, ArrayList<String> paramValue) {
		this.context = context;
	//	pd = new TransparentProgressDialog(context, R.drawable.loading);
		method = meth;
		parameter = param;
		parameterValue = paramValue;

		Log.e("values ",""+param+"--"+paramValue);
	}

	public Json_AsnycTask(Fragment frag, String meth, ArrayList<String> param,ArrayList<String> paramValue)
	{
		this.frag = frag;
		//pd = new TransparentProgressDialog(context, R.drawable.loading);
		method = meth;
		parameter = param;
		parameterValue = paramValue;


	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	//	pd.show();
	}

	@Override
	protected JSONObject doInBackground(String... params) {
		JSONObject jobject = new JSONObject();
		String url = params[0];
		JsonParser jp = new JsonParser();
		jobject = jp.executeHttpPost_server(url, method, parameter,
				parameterValue);
		return jobject;
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	//	pd.hide();
		if (method.equalsIgnoreCase("get")) {
			listener.onResultsSucceeded_Get(result);
		} else if (method.equalsIgnoreCase("album_list")) {
			listener.onResultsSucceeded_Album_List(result);
		} else {
			listener.onResultsSucceeded_Post(result);
		}
	}

	public void setOnResultsListener(OnClickListener onClickListener) {
		// TODO Auto-generated method stub
		this.listener = listener;
	}
}