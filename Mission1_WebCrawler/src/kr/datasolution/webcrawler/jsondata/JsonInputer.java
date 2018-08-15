package kr.datasolution.webcrawler.jsondata;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonInputer {
	public static JsonData inputToBean(String json_ctt) throws Exception {

		// String -> json 파싱
		JSONParser jp = new JSONParser();
		JSONObject jo = (JSONObject) jp.parse(json_ctt);
		JSONArray documents = (JSONArray) jo.get("documents");

		JSONObject info = (JSONObject) documents.get(0);
		
		// 자비빈에 json 데이터 할당
		JsonData j_data = new JsonData();
		j_data.setUrl_addr((String) info.get("url_addr"));
		j_data.setNotice_tag((String) info.get("notice_tag"));
		j_data.setNotices_tag((String) info.get("notices_tag"));
		j_data.setNextpages_tag((String) info.get("nextpages_tag"));
		j_data.setUrl_add_info((String) info.get("url_add_info"));
		j_data.setNext_url_add_info((String) info.get("next_url_add_info"));
		j_data.setTitle_tag((String) info.get("title_tag"));
		j_data.setName_tag((String) info.get("name_tag"));
		j_data.setName_img_tag((String) info.get("name_img_tag"));
		j_data.setContent_tag((String) info.get("content_tag"));
		j_data.setDate_tag((String) info.get("date_tag"));
		j_data.setDate_modify_tag((String) info.get("date_modify_tag"));
		j_data.setDate_length(Integer.parseInt((String) info.get("date_length")));
		j_data.setCurrent_year((String) info.get("current_year"));
		j_data.setPage_count(Integer.parseInt((String) info.get("page_count")));

		return j_data;
	}
}
