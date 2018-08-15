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
		
		// �ش� ����Ʈ�� DOM��ü ��������
		String url = jsonInfo.getUrl_addr();

		Document doc = Jsoup.connect(url).get();

		// �Խ��ǿ� �ش��ϴ� DOM��ü ã��
		Elements menus = doc.select(jsonInfo.getNotice_tag());
		Element menu = menus.first();
		String noticeUrl = menu.attr("href");
		int url_indexOfDS = url.indexOf("//");
		int nUrl_indexOfDS = noticeUrl.indexOf("//");
		String temp_url = null;
		
		// //�� ���� �� ó��
		if(nUrl_indexOfDS < 0) {
			temp_url = url;
		} else {
			temp_url = url.substring(url_indexOfDS, url_indexOfDS + 3).equals(noticeUrl.substring(nUrl_indexOfDS, nUrl_indexOfDS + 3)) ? url : noticeUrl; // main url�� notice url�� �ٸ� �� notice url�� �Ҵ�			
		}
		
		// ������ �̵��� ����� url
		temp_url = temp_url.substring(0, temp_url.indexOf('/', 9) + 1);
		
		// �Խ��� url�� ���� url�� ���Ե��� �ʴ� ��� ����ó��; ���� ��� /board/~ �̷����� �����ϴ� ��ο� ���� ó��
		if(noticeUrl.indexOf("http") < 0) {
			if(noticeUrl.charAt(0) == '/') { noticeUrl = noticeUrl.substring(1); } // �� �տ� /�� ���� �� ����, url�� �������� /�� �ٱ⿡
			noticeUrl = temp_url + noticeUrl;
		}
		
		// �Խ��� �������� DOM��ü ��������
		Document noticeDoc = Jsoup.connect(noticeUrl).get();

		// �Խñۿ� �ش��ϴ� DOM��ü ã��
		Elements notices = noticeDoc.select(jsonInfo.getNotices_tag());

		// ���� �������� �ش��ϴ� DOM��ü ã��
		Elements nextPages = null;
		if(!jsonInfo.getNextpages_tag().equals("")) {
			nextPages = noticeDoc.select(jsonInfo.getNextpages_tag());
		}

		String noticeAdd_url = ""; // notice url�� �߰� ����
		String nextPageAdd_url = ""; // next page url�� �߰� ����
		Element nextPage = null;
		for (int i = 0; i < jsonInfo.getPage_count(); i++) {
			if (i != 0) {
				if (nextPages != null) {
					nextPage = nextPages.get(i - 1);
					nextPageAdd_url = nextPage.attr("href");
					if(nextPage.text().equals("1")) { jsonInfo.setPage_count(jsonInfo.getPage_count() + 1); continue; } // ���� �������� 1������ ��ũ�� ��� ���� �� ����ó��
					if(nextPageAdd_url.charAt(0) == '/') { nextPageAdd_url = nextPageAdd_url.substring(1); } // �� �տ� /�� ���� �� ����, url�� �������� /�� �ٱ⿡					
				} else {
					nextPageAdd_url = jsonInfo.getNext_url_add_info() + (i + 1);
				}
				
				System.out.println(temp_url + jsonInfo.getNext_url_add_info() + nextPageAdd_url);
				// ���� �������� DOM��ü ��������
				noticeDoc = Jsoup.connect(temp_url + nextPageAdd_url).get();
				// �Խñۿ� �ش��ϴ� DOM��ü ã��
				notices = noticeDoc.select(jsonInfo.getNotices_tag());
			}
			
			// �ش� �������� �Խù� �о���̱�
			for (Element notice : notices) {
				if (!notice.attr("href").equals("#")) {
					noticeAdd_url = notice.attr("href");
					if(noticeAdd_url.charAt(0) == '/') { noticeAdd_url = noticeAdd_url.substring(1); } // �� �տ� /�� ���� �� ����, url�� �������� /�� �ٱ⿡
					System.out.println(temp_url + jsonInfo.getUrl_add_info() + noticeAdd_url);
					Document dataDoc = Jsoup.connect(temp_url + jsonInfo.getUrl_add_info() + noticeAdd_url).get();

					String title = dataDoc.select(jsonInfo.getTitle_tag()).text();
					String name = dataDoc.select(jsonInfo.getName_tag()).text();
					
					if(name.equals("")) { name = dataDoc.select(jsonInfo.getName_img_tag()).attr("alt"); } // name�� �̸��� ���� �� img�ױ׷� ã�´�.
					
					String content = dataDoc.select(jsonInfo.getContent_tag()).text();
					int index_of_year = dataDoc.select(jsonInfo.getDate_tag()).text().indexOf(jsonInfo.getCurrent_year());

					if (index_of_year < 0) { continue; } // out of bound�� ���� ����

					String date = dataDoc.select(jsonInfo.getDate_tag()).text().substring(index_of_year,
							index_of_year + jsonInfo.getDate_length());
					datas.add(new CrawledData(title, content, name, date)); // ������ ���

				}

			}
		}

		return datas;
	}
}
