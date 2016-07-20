package tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import net.sf.json.JSONObject;

public class NetworkTools {

	private static final String URL_BASE = "http://localhost:8080/AliPay/base";

	private static final String URL_BANK = "http://localhost:8080/AliPay/bank";

	private static final String URL_ACCOUNT = "http://localhost:8080/AliPay/account";

	private static String getURL(String sign) {
		if ("base".equals(sign)) {
			return URL_BASE;
		}
		if ("bank".equals(sign)) {
			return URL_BANK;
		}
		if ("account".equals(sign)) {
			return URL_ACCOUNT;
		}
		return null;
	}

	public static String postJson(JSONObject json, String sign) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(getURL(sign));
			// �򿪺�URL֮�������
			URLConnection conn = realUrl.openConnection();
			// ����POST�������������������
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// ��ȡURLConnection�����Ӧ�������
			out = new PrintWriter(conn.getOutputStream());
			// �����������
			out.print(json);
			// flush������Ļ���
			out.flush();
			// ����BufferedReader����������ȡURL����Ӧ
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		// ʹ��finally�����ر��������������
		finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

}
