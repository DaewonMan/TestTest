package kr.datasolution.webcrawler.crawleddata;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import kr.datasolution.webcrawler.jsondata.JsonData;

public class WebCrawler {
	public static ArrayList<CrawledData> addCrawledDataToList(JsonData jsonInfo) throws Exception {
		
		ArrayList<CrawledData> datas = new ArrayList<>();
		
		// 해당 사이트의 DOM객체 가져오기
		String url = jsonInfo.getUrl_addr();

		Document doc = Jsoup.connect(url).get();

		// 게시판에 해당하는 DOM객체 찾기
		Elements menus = doc.select(jsonInfo.getNotice_tag());
		Element menu = menus.first();
		String noticeUrl = menu.attr("href");
		int url_indexOfDS = url.indexOf("//");
		int nUrl_indexOfDS = noticeUrl.indexOf("//");
		String temp_url = null;
		
		// //가 없을 시 처리
		if(nUrl_indexOfDS < 0) {
			temp_url = url;
		} else {
			temp_url = url.substring(url_indexOfDS, url_indexOfDS + 3).equals(noticeUrl.substring(nUrl_indexOfDS, nUrl_indexOfDS + 3)) ? url : noticeUrl; // main url과 notice url이 다를 시 notice url을 할당			
		}
		
		// 페이지 이동시 사용할 url
		temp_url = temp_url.substring(0, temp_url.indexOf('/', 9) + 1);
		
		// 게시판 url에 메인 url이 포함되지 않는 경우 예외처리; 예를 들어 /board/~ 이런식을 시작하는 경로에 대한 처리
		if(noticeUrl.indexOf("http") < 0) {
			if(noticeUrl.charAt(0) == '/') { noticeUrl = noticeUrl.substring(1); } // 맨 앞에 /가 있을 시 제거, url에 마지막에 /가 붙기에
			noticeUrl = temp_url + noticeUrl;
		}
		
		// 게시판 페이지의 DOM객체 가져오기
		Document noticeDoc = Jsoup.connect(noticeUrl).get();

		// 게시글에 해당하는 DOM객체 찾기
		Elements notices = noticeDoc.select(jsonInfo.getNotices_tag());

		// 다음 페이지에 해당하는 DOM객체 찾기
		Elements nextPages = null;
		if(!jsonInfo.getNextpages_tag().equals("")) {
			nextPages = noticeDoc.select(jsonInfo.getNextpages_tag());
		}

		String noticeAdd_url = ""; // notice url의 추가 정보
		String nextPageAdd_url = ""; // next page url의 추가 정보
		Element nextPage = null;
		for (int i = 0; i < jsonInfo.getPage_count(); i++) {
			if (i != 0) {
				if (nextPages != null) {
					nextPage = nextPages.get(i - 1);
					nextPageAdd_url = nextPage.attr("href");
					if(nextPage.text().equals("1")) { jsonInfo.setPage_count(jsonInfo.getPage_count() + 1); continue; } // 다음 페이지를 1페이지 링크로 들어 갔을 시 예외처리
					if(nextPageAdd_url.charAt(0) == '/') { nextPageAdd_url = nextPageAdd_url.substring(1); } // 맨 앞에 /가 있을 시 제거, url에 마지막에 /가 붙기에					
				} else {
					nextPageAdd_url = jsonInfo.getNext_url_add_info() + (i + 1);
				}
				
				System.out.println(temp_url + jsonInfo.getNext_url_add_info() + nextPageAdd_url);
				// 다음 페이지의 DOM객체 가져오기
				noticeDoc = Jsoup.connect(temp_url + nextPageAdd_url).get();
				// 게시글에 해당하는 DOM객체 찾기
				notices = noticeDoc.select(jsonInfo.getNotices_tag());
			}
			
			// 해당 페이지의 게시물 읽어들이기
			for (Element notice : notices) {
				if (!notice.attr("href").equals("#")) {
					noticeAdd_url = notice.attr("href");
					if(noticeAdd_url.charAt(0) == '/') { noticeAdd_url = noticeAdd_url.substring(1); } // 맨 앞에 /가 있을 시 제거, url에 마지막에 /가 붙기에
					System.out.println(temp_url + jsonInfo.getUrl_add_info() + noticeAdd_url);
					Document dataDoc = Jsoup.connect(temp_url + jsonInfo.getUrl_add_info() + noticeAdd_url).get();

					String title = dataDoc.select(jsonInfo.getTitle_tag()).text();
					String name = dataDoc.select(jsonInfo.getName_tag()).text();
					
					if(name.equals("")) { name = dataDoc.select(jsonInfo.getName_img_tag()).attr("alt"); } // name에 이름이 없을 시 img테그로 찾는다.
					
					String content = dataDoc.select(jsonInfo.getContent_tag()).text();
					int index_of_year = dataDoc.select(jsonInfo.getDate_tag()).text().indexOf(jsonInfo.getCurrent_year());

					if (index_of_year < 0) { continue; } // out of bound를 막기 위함

					String date = dataDoc.select(jsonInfo.getDate_tag()).text().substring(index_of_year,
							index_of_year + jsonInfo.getDate_length());
					datas.add(new CrawledData(title, content, name, date)); // 데이터 담기

				}

			}
		}

		return datas;
	}
}
