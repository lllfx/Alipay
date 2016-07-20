package tools;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JFrame;

public class Tools {

	public static boolean strIsOk(String str) {
		if (str == null) {
			return false;
		}
		if (str.trim().equals("")) {
			return false;
		}
		return true;
	}

	public static void setCenterLoaction(JFrame frame) {
		int fw = frame.getWidth();
		int fh = frame.getHeight();
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int) screensize.getWidth();
		int height = (int) screensize.getHeight();
		frame.setLocation((width - fw) / 2, (height - fh) / 2);

	}

	public static Vector<String> getBankList() {
		Vector<String> v = new Vector<String>();

		v.addElement("中国农业银行");
		v.addElement("中国银行");
		v.addElement("中国建设银行");
		v.addElement("中国工商银行");
		v.addElement("中国邮政储蓄");
		v.addElement("浙商银行");
		v.addElement("渤海银行");
		v.addElement("恒丰银行");
		v.addElement("广发银行");
		v.addElement("平安银行");
		v.addElement("民生银行");
		v.addElement("兴业银行");
		v.addElement("浦发银行");
		v.addElement("华夏银行");
		v.addElement("中国光大银行");
		v.addElement("中信银行");
		v.addElement("招商银行");
		v.addElement("交通银行");

		return v;
	}
}
