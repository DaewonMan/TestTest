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
			// json file ��������
			String json_ctt = FileConverter.convertToString();

			// json data�� �ڹٺ� �Ҵ�
			JsonData jsonInfo = JsonInputer.inputToBean(json_ctt);
			
			// �����κ��� �ʿ��� ������ ����
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