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

		v.addElement("�й�ũҵ����");
		v.addElement("�й�����");
		v.addElement("�й���������");
		v.addElement("�й���������");
		v.addElement("�й���������");
		v.addElement("��������");
		v.addElement("��������");
		v.addElement("�������");
		v.addElement("�㷢����");
		v.addElement("ƽ������");
		v.addElement("��������");
		v.addElement("��ҵ����");
		v.addElement("�ַ�����");
		v.addElement("��������");
		v.addElement("�й��������");
		v.addElement("��������");
		v.addElement("��������");
		v.addElement("��ͨ����");

		return v;
	}
}
