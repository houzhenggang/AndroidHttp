package com.example.simpleandroidhttp.utils.json;

public class JsonUtils {

	private static final String TAG = JsonUtils.class.getSimpleName();

	// public static boolean isJson(String json) {
	// if (TextUtil.isEmpty(json)) {
	// return false;
	// }
	// // try {
	// // JsonParser jsonParser = new JsonParser();
	// // JsonElement jsonElement = jsonParser.parse(json);
	// // return jsonElement.isJsonObject();
	// // } catch (JsonSyntaxException e) {
	// // ZozoLog.e(TAG, json + "is invalid json str :" + e);
	// // }
	// return false;
	// }

	/**
	 * 接收的数据转换成UTF-8
	 * 
	 * @param in
	 * @return
	 */
	public static String JSONTokener(String in) {
		// consume an optional byte order mark (BOM) if it exists
		if (in != null && in.startsWith("\ufeff")) {
			in = in.substring(1);
		}
		return in;
	}
}
