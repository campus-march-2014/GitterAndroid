package org.infobip.campus8;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ChannelListJsonParser {
	
	public List<String> parse(String json) throws JSONException{
		JSONArray jsonArray = new JSONArray(json);
		List<String> channelList = new ArrayList<String>();
		for(int i = 0; i < jsonArray.length(); i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String channelName = jsonObject.getString("name");
			channelList.add(channelName);
		}
		return channelList;
	}
}