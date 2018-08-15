package kr.datasolution.webcrawler.main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FileConverter {
	public static String convertToString() throws Exception {
		// 내용 빼낼 준비
		InputStream is = new FileInputStream("webSiteInfo.json");
		InputStreamReader isr = new InputStreamReader(is);
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(isr);

		String line = null;
		StringBuffer sb = new StringBuffer();
		
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}

		return sb.toString();
	}
}
