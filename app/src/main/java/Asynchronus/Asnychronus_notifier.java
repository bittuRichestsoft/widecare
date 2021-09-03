package Asynchronus;

import org.json.JSONObject;

public interface Asnychronus_notifier {


	public void onResultsSucceeded_Get(JSONObject result);
	public void onResultsSucceeded_Album_List(JSONObject result);
	public void onResultsSucceeded_Post(JSONObject result);
}
