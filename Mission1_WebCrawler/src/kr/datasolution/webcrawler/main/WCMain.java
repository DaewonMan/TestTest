package kr.datasolution.webcrawler.main;

import java.util.ArrayList;

import kr.datasolution.webcrawler.crawleddata.CrawledData;
import kr.datasolution.webcrawler.crawleddata.WebCrawler;
import kr.datasolution.webcrawler.jsondata.JsonData;
import kr.datasolution.webcrawler.jsondata.JsonInputer;

public class WCMain {
	public static void main(String[] args) {
		ArrayList<CrawledData> datas;
		
		try {
			// json file 가져오기
			String json_ctt = FileConverter.convertToString();

			// json data를 자바빈에 할당
			JsonData jsonInfo = JsonInputer.inputToBean(json_ctt);
			
			// 웹으로부터 필요한 데이터 수집
			datas = WebCrawler.addCrawledDataToList(jsonInfo);

			for (CrawledData data : datas) {
				System.out.println(data.getTitle());
				System.out.println(data.getName());
				System.out.println(data.getContent());
				System.out.println(data.getDate());
				System.out.println("---------------------------------------");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
/*
 * Elements newsHeadlines = doc.select("#mp-itn b a"); for (Element headline :
 * newsHeadlines) { System.out.printf("%s\n\t%s\n", headline.attr("title"),
 * headline.absUrl("href")); System.out.println(); }
 */