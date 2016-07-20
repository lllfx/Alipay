package model;

import java.awt.Component;
import javax.swing.JOptionPane;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.MD5Util;
import tools.NetworkTools;
import tools.Tools;

public class Model {

	private static String USERSIGN = null;
	private static String USERNANE = null;
	
	
	private static final String F1 = "asd";

	private static final String F2 = "bcd";

//	public static void main(String[] args) {
//		login("asd2", "asd", null);
//	}

	private static void dispalyError(Component component, String msg) {
		if (component != null) {
			if (component != null) {
				JOptionPane.showMessageDialog(component, msg, "����", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public static boolean login(String userName, String password, Component component) {

		boolean resultSign = false;
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "login");
		sb.append("login");

		json.put("userName", userName);
		sb.append(userName);
		// ��Ҫ
		String temp = MD5Util.MD5(password + F1);
		json.put("password", temp);
		sb.append(temp);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "base");

		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");
			dispalyError(component, "����������Ժ�����");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");
				dispalyError(component, "���ݷ��ش���");
				return resultSign;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�

				String userSign = resultJSon.getString("userSign");
				// ��ֵ
				USERSIGN = userSign;
				USERNANE = userName;
				// System.out.println("��¼�ɹ�");
				resultSign = true;
				break;
			case 1:
				// System.out.println("�����ַ�������");
				dispalyError(component, "�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");
				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("action�ֶβ��Ϸ�");
				dispalyError(component, "action�ֶβ��Ϸ�");
				break;
			case 4:
				// System.out.println("�û�����������Ϊ��");
				dispalyError(component, "�û�����������Ϊ��");
				break;

			case 5:
				// System.out.println("�û��������������");
				dispalyError(component, "�û��������������");
				break;

			case 6:
				// System.out.println("���ݿ����");
				dispalyError(component, "���ݿ����");
				break;

			case 7:
				// System.out.println("δ֪����");
				dispalyError(component, "δ֪����");
				break;
			}
		} catch (Exception e) {
			// System.out.println("���ݷ��ش���");
			dispalyError(component, "���ݷ��ش���");
		}
		return resultSign;
	}

	public static boolean register(String userName, String password, Component component) {
		boolean reusltSign = false;
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "register");
		sb.append("register");
		json.put("userName", userName);
		sb.append(userName);
		// ��Ҫ
		String temp = MD5Util.MD5(password + F1);
		json.put("password", temp);
		sb.append(temp);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "base");

		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");
			dispalyError(component, "����������Ժ�����");
			return reusltSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");
				dispalyError(component, "���ݷ��ش���");
				return reusltSign;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�
				// System.out.println("ע��ɹ�");
				// dispalyError(component, "ע��ɹ�");
				reusltSign = true;
				break;
			case 1:
				// System.out.println("�����ַ�������");
				dispalyError(component, "�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");
				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("action�ֶβ��Ϸ�");
				dispalyError(component, "action�ֶβ��Ϸ�");
				break;
			case 4:
				// System.out.println("�û�����������Ϊ��");

				dispalyError(component, "�û�����������Ϊ��");
				break;

			case 5:
				// System.out.println("�û����Ѵ���");

				dispalyError(component, "�û����Ѵ���");
				break;

			case 6:
				// System.out.println("�������ݿ����");

				dispalyError(component, "�������ݿ����");
				break;

			}

		} catch (Exception e) {
			// System.out.println("���ݷ��ش���");

			dispalyError(component, "���ݷ��ش���");
		}
		return reusltSign;

	}

	public static JSONArray retrievalBankCard(Component component) {
		JSONArray jsonArray = null;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("��¼���ڣ������µ�½��");
			dispalyError(component, "��¼���ڣ������µ�½��");
			return jsonArray;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "retrievalBankCard");
		sb.append("retrievalBankCard");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "bank");
		if (!Tools.strIsOk(result)) {
			// �������
			dispalyError(component, "����������Ժ�����");
			// System.out.println("����������Ժ�����");
			return jsonArray;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");

				dispalyError(component, "���ݷ��ش���");
				return jsonArray;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�
				jsonArray = JSONArray.fromObject(resultJSon.getString("JSONArray"));
				// System.out.println("���п����:");
				// System.out.println(jsonArray);
				// System.out.println("�����ɹ�");

				break;
			case 1:
				// System.out.println("action�����ַ�������");

				dispalyError(component, "action�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");

				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("�ֶβ��Ϸ�");

				dispalyError(component, "�ֶβ��Ϸ�");
				break;
			case 4:
				// System.out.println("�û�������ǩ�����Ϸ�");
				dispalyError(component, "�û�������ǩ�����Ϸ�");
				break;

			case 5:
				// System.out.println("�û�����ǩ����һ��");

				dispalyError(component, "�û�����ǩ����һ��");
				break;

			case 6:
				// System.out.println("��Ҫ��������Ϣ���Ϸ�");

				dispalyError(component, "��Ҫ��������Ϣ���Ϸ�");
				break;

			case 7:
				// System.out.println("û���ҵ����û�");

				dispalyError(component, "û���ҵ����û�");
				break;
			case 8:
				// System.out.println("���ݿ��ѯ����");
				dispalyError(component, "���ݿ��ѯ����");
				break;
			}

		} catch (Exception e) {
			// System.out.println("���ݷ��ش���");

			dispalyError(component, "���ݷ��ش���");
		}
		return jsonArray;
	}

	/**
	 * ������п�
	 * 
	 * @param name
	 * @param number
	 * @param bankName
	 */
	public static boolean addBankCard(String name, String number, String bankName, Component component) {
		boolean resultSign = false;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("��¼���ڣ������µ�½��");
			dispalyError(component, "��¼���ڣ������µ�½��");
			return resultSign;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "addBankCard");
		sb.append("addBankCard");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("name", name);
		sb.append(name);

		json.put("number", number);
		sb.append(number);

		json.put("bankName", bankName);
		sb.append(bankName);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "bank");

		// System.out.println(json);
		// System.out.println(result);
		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");

			dispalyError(component, "����������Ժ����ԣ�");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");
				dispalyError(component, "���ݷ��ش���");
				return resultSign;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�
				resultSign = true;
				// System.out.println("����ɹ�");

				break;
			case 1:
				// System.out.println("action�����ַ�������");

				dispalyError(component, "action�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");

				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:

				dispalyError(component, "�ֶβ��Ϸ�");
				// System.out.println("�ֶβ��Ϸ�");
				break;
			case 4:

				dispalyError(component, "�û�������ǩ�����Ϸ�");
				// System.out.println("�û�������ǩ�����Ϸ�");
				break;

			case 5:

				dispalyError(component, "�û�����ǩ����һ��");
				// System.out.println("�û�����ǩ����һ��");
				break;

			case 6:

				dispalyError(component, "��Ҫ�������Ϣ���Ϸ�");
				// System.out.println("��Ҫ�������Ϣ���Ϸ�");
				break;

			case 7:

				dispalyError(component, "û���ҵ����û�");
				// System.out.println("û���ҵ����û�");
				break;
			case 8:
				dispalyError(component, "���ݿ�������");
				// System.out.println("���ݿ�������");
				break;

			case 9:
				dispalyError(component, "δ֪����");
				// System.out.println("δ֪����");
				break;

			}

		} catch (Exception e) {
			dispalyError(component, "���ݷ��ش���");
			// System.out.println("���ݷ��ش���");
		}
		return resultSign;
	}

	public static boolean transferAccounts(String account, String amount, Component component) {
		boolean resultSign = false;
		if (account.equals(USERNANE)) {
			// System.out.println("�������Լ����Լ�ת��Ŷ��");
			dispalyError(component, "�������Լ����Լ�ת��Ŷ��");
			return resultSign;
		}
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("��¼���ڣ������µ�½��");
			dispalyError(component, "��¼���ڣ������µ�½��");
			return resultSign;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "transferAccount");
		sb.append("transferAccount");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("account", account);
		sb.append(account);

		json.put("amount", amount);
		sb.append(amount);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "account");

		// System.out.println(json);
		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");

			dispalyError(component, "����������Ժ�����");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");

				dispalyError(component, "���ݷ��ش���");
				return resultSign;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�
				// System.out.println("����ɹ�");
				resultSign = true;
				break;
			case 1:
				// System.out.println("action�����ַ�������");

				dispalyError(component, "action�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");

				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("�ֶβ��Ϸ�");

				dispalyError(component, "�ֶβ��Ϸ�");
				break;
			case 4:
				// System.out.println("�û�������ǩ�����Ϸ�");

				dispalyError(component, "�û�������ǩ�����Ϸ�");
				break;

			case 5:
				// System.out.println("�û�����ǩ����һ��");
				dispalyError(component, "�û�����ǩ����һ��");
				break;

			case 6:
				// System.out.println("��Ҫ�������Ϣ���Ϸ�");

				dispalyError(component, "��Ҫ�������Ϣ���Ϸ�");
				break;

			case 7:
				// System.out.println("û���ҵ����û�");
				dispalyError(component, "û���ҵ����û�");
				break;
			case 8:
				System.out.println("�Է��˺Ų�����");

				dispalyError(component, "�Է��˺Ų�����");
				break;

			case 9:

				dispalyError(component, "���ݿ�������");
				// System.out.println("���ݿ�������");
				break;
			case 10:
				dispalyError(component, "δ֪����");
				// System.out.println("δ֪����");
				break;
			}

		} catch (Exception e) {
			dispalyError(component, "���ݷ��ش���");
			// System.out.println("���ݷ��ش���");
		}
		return resultSign;
	}

	public static JSONArray retrievalTransferRecoder(Component component) {
		JSONArray jsonArray = null;
		if (USERSIGN == null || USERNANE == null) {
			if (component != null) {
				JOptionPane.showMessageDialog(component, "��¼���ڣ������µ�½��", "����", JOptionPane.ERROR_MESSAGE);
			}
			return null;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "retrievalTransferRecoder");
		sb.append("retrievalTransferRecoder");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "account");
		if (!Tools.strIsOk(result)) {
			// �������
			if (component != null) {
				JOptionPane.showMessageDialog(component, "����������Ժ�����", "����", JOptionPane.ERROR_MESSAGE);
			}
			return null;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				if (component != null) {
					JOptionPane.showMessageDialog(component, "���ݷ��ش���", "����", JOptionPane.ERROR_MESSAGE);
				}
				return null;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�
				jsonArray = JSONArray.fromObject(resultJSon.getString("JSONArray"));
				// System.out.println(jsonArray);
				break;
			case 1:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "action�����ַ�������", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 2:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "��Ϣ���۸�", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 3:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "�ֶβ��Ϸ�", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 4:
				// System.out.println("�û�������ǩ�����Ϸ�");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "�û�������ǩ�����Ϸ�", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 5:
				// System.out.println("�û�����ǩ����һ��");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "�û�����ǩ����һ��", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 6:
				// System.out.println("��Ҫ��������Ϣ���Ϸ�");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "��Ҫ��������Ϣ���Ϸ�", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 7:
				// System.out.println("û���ҵ����û�");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "û���ҵ����û�", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 8:
				// System.out.println("���ݿ��ѯ����");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "���ݿ��ѯ����", "����", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}

		} catch (Exception e) {
			if (component != null) {
				JOptionPane.showMessageDialog(component, "���ݷ��ش���", "����", JOptionPane.ERROR_MESSAGE);
			}
			// System.out.println("���ݷ��ش���");
		}
		return jsonArray;
	}

	public static int retrievalTransferRecoderNum(Component component) {
		int num = 0;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("��¼���ڣ������µ�½��");

			dispalyError(component, "��¼���ڣ������µ�½��");
			return num;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "retrievalTransferRecoderNum");
		sb.append("retrievalTransferRecoderNum");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "account");
		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");

			dispalyError(component, "����������Ժ�����");
			return num;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");

				dispalyError(component, "���ݷ��ش���");
				return num;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�

				sign = resultJSon.containsKey("num");
				if (!sign) {
					// System.out.println("���ݷ��ش���");
					dispalyError(component, "���ݷ��ش���");
					return num;
				}
				num = resultJSon.getInt("num");
				break;
			case 1:
				// System.out.println("action�����ַ�������");
				dispalyError(component, "action�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");
				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("�ֶβ��Ϸ�");
				dispalyError(component, "�ֶβ��Ϸ�");
				break;
			case 4:
				// System.out.println("�û�������ǩ�����Ϸ�");
				dispalyError(component, "�û�������ǩ�����Ϸ�");
				break;

			case 5:
				// System.out.println("�û�����ǩ����һ��");

				dispalyError(component, "�û�����ǩ����һ��");
				break;

			case 6:
				// System.out.println("��Ҫ��������Ϣ���Ϸ�");

				dispalyError(component, "��Ҫ��������Ϣ���Ϸ�");
				break;

			case 7:
				// System.out.println("û���ҵ����û�");

				dispalyError(component, "û���ҵ����û�");
				break;
			case 8:
				// System.out.println("���ݿ��ѯ����");

				dispalyError(component, "���ݿ��ѯ����");
				break;
			}

		} catch (Exception e) {
			// System.out.println("���ݷ��ش���");
			//
			dispalyError(component, "���ݷ��ش���");
		}
		return num;
	}

	public static int retrievalBankCardNum(Component component) {
		int num = 0;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("��¼���ڣ������µ�½��");

			dispalyError(component, "��¼���ڣ������µ�½��");
			return num;
		}
		StringBuilder sb = new StringBuilder();
		JSONObject json = new JSONObject();
		json.put("action", "retrievalBankCardNum");
		sb.append("retrievalBankCardNum");

		json.put("userName", USERNANE);
		sb.append(USERNANE);

		json.put("userSign", USERSIGN);
		sb.append(USERSIGN);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "bank");
		if (!Tools.strIsOk(result)) {
			// �������
			// System.out.println("����������Ժ�����");

			dispalyError(component, "����������Ժ�����");
			return num;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("���ݷ��ش���");

				dispalyError(component, "���ݷ��ش���");
				return num;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// �ɹ�

				sign = resultJSon.containsKey("num");
				if (!sign) {
					// System.out.println("���ݷ��ش���");

					dispalyError(component, "���ݷ��ش���");
					return num;
				}
				num = resultJSon.getInt("num");

				break;
			case 1:
				// System.out.println("action�����ַ�������");

				dispalyError(component, "action�����ַ�������");
				break;

			case 2:
				// System.out.println("��Ϣ���۸�");

				dispalyError(component, "��Ϣ���۸�");
				break;

			case 3:
				// System.out.println("�ֶβ��Ϸ�");

				dispalyError(component, "�ֶβ��Ϸ�");
				break;
			case 4:

				dispalyError(component, "�û�������ǩ�����Ϸ�");
				// System.out.println("�û�������ǩ�����Ϸ�");
				break;

			case 5:
				dispalyError(component, "�û�����ǩ����һ��");
				// System.out.println("�û�����ǩ����һ��");
				break;

			case 6:
				// System.out.println("��Ҫ��������Ϣ���Ϸ�");

				dispalyError(component, "��Ҫ��������Ϣ���Ϸ�");
				break;

			case 7:
				// System.out.println("û���ҵ����û�");

				dispalyError(component, "û���ҵ����û�");
				break;
			case 8:
				// System.out.println("���ݿ��ѯ����");

				dispalyError(component, "���ݿ��ѯ����");
				break;
			}

		} catch (Exception e) {
			// System.out.println("���ݷ��ش���");

			dispalyError(component, "���ݷ��ش���");
		}
		return num;
	}

	
}
