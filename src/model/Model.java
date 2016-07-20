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
				JOptionPane.showMessageDialog(component, msg, "错误", JOptionPane.ERROR_MESSAGE);
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
		// 重要
		String temp = MD5Util.MD5(password + F1);
		json.put("password", temp);
		sb.append(temp);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "base");

		if (!Tools.strIsOk(result)) {
			// 请求错误
			// System.out.println("网络错误，请稍后再试");
			dispalyError(component, "网络错误，请稍后再试");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");
				dispalyError(component, "数据返回错误");
				return resultSign;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功

				String userSign = resultJSon.getString("userSign");
				// 赋值
				USERSIGN = userSign;
				USERNANE = userName;
				// System.out.println("登录成功");
				resultSign = true;
				break;
			case 1:
				// System.out.println("请求字符串不对");
				dispalyError(component, "请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");
				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("action字段不合法");
				dispalyError(component, "action字段不合法");
				break;
			case 4:
				// System.out.println("用户名或者密码为空");
				dispalyError(component, "用户名或者密码为空");
				break;

			case 5:
				// System.out.println("用户名或者密码错误");
				dispalyError(component, "用户名或者密码错误");
				break;

			case 6:
				// System.out.println("数据库错误");
				dispalyError(component, "数据库错误");
				break;

			case 7:
				// System.out.println("未知错误");
				dispalyError(component, "未知错误");
				break;
			}
		} catch (Exception e) {
			// System.out.println("数据返回错误");
			dispalyError(component, "数据返回错误");
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
		// 重要
		String temp = MD5Util.MD5(password + F1);
		json.put("password", temp);
		sb.append(temp);

		json.put("sign", MD5Util.MD5(sb.toString() + F2));
		String result = NetworkTools.postJson(json, "base");

		if (!Tools.strIsOk(result)) {
			// 请求错误
			// System.out.println("网络错误，请稍后再试");
			dispalyError(component, "网络错误，请稍后再试");
			return reusltSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");
				dispalyError(component, "数据返回错误");
				return reusltSign;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功
				// System.out.println("注册成功");
				// dispalyError(component, "注册成功");
				reusltSign = true;
				break;
			case 1:
				// System.out.println("请求字符串不对");
				dispalyError(component, "请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");
				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("action字段不合法");
				dispalyError(component, "action字段不合法");
				break;
			case 4:
				// System.out.println("用户名或者密码为空");

				dispalyError(component, "用户名或者密码为空");
				break;

			case 5:
				// System.out.println("用户名已存在");

				dispalyError(component, "用户名已存在");
				break;

			case 6:
				// System.out.println("插入数据库错误");

				dispalyError(component, "插入数据库错误");
				break;

			}

		} catch (Exception e) {
			// System.out.println("数据返回错误");

			dispalyError(component, "数据返回错误");
		}
		return reusltSign;

	}

	public static JSONArray retrievalBankCard(Component component) {
		JSONArray jsonArray = null;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("登录过期，请重新登陆！");
			dispalyError(component, "登录过期，请重新登陆！");
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
			// 请求错误
			dispalyError(component, "网络错误，请稍后再试");
			// System.out.println("网络错误，请稍后再试");
			return jsonArray;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");

				dispalyError(component, "数据返回错误");
				return jsonArray;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功
				jsonArray = JSONArray.fromObject(resultJSon.getString("JSONArray"));
				// System.out.println("银行卡结果:");
				// System.out.println(jsonArray);
				// System.out.println("检索成功");

				break;
			case 1:
				// System.out.println("action请求字符串不对");

				dispalyError(component, "action请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");

				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("字段不合法");

				dispalyError(component, "字段不合法");
				break;
			case 4:
				// System.out.println("用户名或者签名不合法");
				dispalyError(component, "用户名或者签名不合法");
				break;

			case 5:
				// System.out.println("用户名和签名不一致");

				dispalyError(component, "用户名和签名不一致");
				break;

			case 6:
				// System.out.println("需要检索的信息不合法");

				dispalyError(component, "需要检索的信息不合法");
				break;

			case 7:
				// System.out.println("没有找到该用户");

				dispalyError(component, "没有找到该用户");
				break;
			case 8:
				// System.out.println("数据库查询错误");
				dispalyError(component, "数据库查询错误");
				break;
			}

		} catch (Exception e) {
			// System.out.println("数据返回错误");

			dispalyError(component, "数据返回错误");
		}
		return jsonArray;
	}

	/**
	 * 添加银行卡
	 * 
	 * @param name
	 * @param number
	 * @param bankName
	 */
	public static boolean addBankCard(String name, String number, String bankName, Component component) {
		boolean resultSign = false;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("登录过期，请重新登陆！");
			dispalyError(component, "登录过期，请重新登陆！");
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
			// 请求错误
			// System.out.println("网络错误，请稍后再试");

			dispalyError(component, "网络错误，请稍后再试！");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");
				dispalyError(component, "数据返回错误");
				return resultSign;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功
				resultSign = true;
				// System.out.println("插入成功");

				break;
			case 1:
				// System.out.println("action请求字符串不对");

				dispalyError(component, "action请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");

				dispalyError(component, "信息被篡改");
				break;

			case 3:

				dispalyError(component, "字段不合法");
				// System.out.println("字段不合法");
				break;
			case 4:

				dispalyError(component, "用户名或者签名不合法");
				// System.out.println("用户名或者签名不合法");
				break;

			case 5:

				dispalyError(component, "用户名和签名不一致");
				// System.out.println("用户名和签名不一致");
				break;

			case 6:

				dispalyError(component, "需要插入的信息不合法");
				// System.out.println("需要插入的信息不合法");
				break;

			case 7:

				dispalyError(component, "没有找到该用户");
				// System.out.println("没有找到该用户");
				break;
			case 8:
				dispalyError(component, "数据库插入错误");
				// System.out.println("数据库插入错误");
				break;

			case 9:
				dispalyError(component, "未知错误");
				// System.out.println("未知错误");
				break;

			}

		} catch (Exception e) {
			dispalyError(component, "数据返回错误");
			// System.out.println("数据返回错误");
		}
		return resultSign;
	}

	public static boolean transferAccounts(String account, String amount, Component component) {
		boolean resultSign = false;
		if (account.equals(USERNANE)) {
			// System.out.println("不可以自己给自己转账哦！");
			dispalyError(component, "不可以自己给自己转账哦！");
			return resultSign;
		}
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("登录过期，请重新登陆！");
			dispalyError(component, "登录过期，请重新登陆！");
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
			// 请求错误
			// System.out.println("网络错误，请稍后再试");

			dispalyError(component, "网络错误，请稍后再试");
			return resultSign;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");

				dispalyError(component, "数据返回错误");
				return resultSign;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功
				// System.out.println("插入成功");
				resultSign = true;
				break;
			case 1:
				// System.out.println("action请求字符串不对");

				dispalyError(component, "action请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");

				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("字段不合法");

				dispalyError(component, "字段不合法");
				break;
			case 4:
				// System.out.println("用户名或者签名不合法");

				dispalyError(component, "用户名或者签名不合法");
				break;

			case 5:
				// System.out.println("用户名和签名不一致");
				dispalyError(component, "用户名和签名不一致");
				break;

			case 6:
				// System.out.println("需要插入的信息不合法");

				dispalyError(component, "需要插入的信息不合法");
				break;

			case 7:
				// System.out.println("没有找到该用户");
				dispalyError(component, "没有找到该用户");
				break;
			case 8:
				System.out.println("对方账号不存在");

				dispalyError(component, "对方账号不存在");
				break;

			case 9:

				dispalyError(component, "数据库插入错误");
				// System.out.println("数据库插入错误");
				break;
			case 10:
				dispalyError(component, "未知错误");
				// System.out.println("未知错误");
				break;
			}

		} catch (Exception e) {
			dispalyError(component, "数据返回错误");
			// System.out.println("数据返回错误");
		}
		return resultSign;
	}

	public static JSONArray retrievalTransferRecoder(Component component) {
		JSONArray jsonArray = null;
		if (USERSIGN == null || USERNANE == null) {
			if (component != null) {
				JOptionPane.showMessageDialog(component, "登录过期，请重新登陆！", "错误", JOptionPane.ERROR_MESSAGE);
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
			// 请求错误
			if (component != null) {
				JOptionPane.showMessageDialog(component, "网络错误，请稍后再试", "错误", JOptionPane.ERROR_MESSAGE);
			}
			return null;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				if (component != null) {
					JOptionPane.showMessageDialog(component, "数据返回错误", "错误", JOptionPane.ERROR_MESSAGE);
				}
				return null;
			}
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功
				jsonArray = JSONArray.fromObject(resultJSon.getString("JSONArray"));
				// System.out.println(jsonArray);
				break;
			case 1:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "action请求字符串不对", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 2:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "信息被篡改", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 3:
				if (component != null) {
					JOptionPane.showMessageDialog(component, "字段不合法", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 4:
				// System.out.println("用户名或者签名不合法");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "用户名或者签名不合法", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 5:
				// System.out.println("用户名和签名不一致");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "用户名和签名不一致", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 6:
				// System.out.println("需要检索的信息不合法");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "需要检索的信息不合法", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;

			case 7:
				// System.out.println("没有找到该用户");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "没有找到该用户", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;
			case 8:
				// System.out.println("数据库查询错误");
				if (component != null) {
					JOptionPane.showMessageDialog(component, "数据库查询错误", "错误", JOptionPane.ERROR_MESSAGE);
				}
				break;
			}

		} catch (Exception e) {
			if (component != null) {
				JOptionPane.showMessageDialog(component, "数据返回错误", "错误", JOptionPane.ERROR_MESSAGE);
			}
			// System.out.println("数据返回错误");
		}
		return jsonArray;
	}

	public static int retrievalTransferRecoderNum(Component component) {
		int num = 0;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("登录过期，请重新登陆！");

			dispalyError(component, "登录过期，请重新登陆！");
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
			// 请求错误
			// System.out.println("网络错误，请稍后再试");

			dispalyError(component, "网络错误，请稍后再试");
			return num;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");

				dispalyError(component, "数据返回错误");
				return num;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功

				sign = resultJSon.containsKey("num");
				if (!sign) {
					// System.out.println("数据返回错误");
					dispalyError(component, "数据返回错误");
					return num;
				}
				num = resultJSon.getInt("num");
				break;
			case 1:
				// System.out.println("action请求字符串不对");
				dispalyError(component, "action请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");
				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("字段不合法");
				dispalyError(component, "字段不合法");
				break;
			case 4:
				// System.out.println("用户名或者签名不合法");
				dispalyError(component, "用户名或者签名不合法");
				break;

			case 5:
				// System.out.println("用户名和签名不一致");

				dispalyError(component, "用户名和签名不一致");
				break;

			case 6:
				// System.out.println("需要检索的信息不合法");

				dispalyError(component, "需要检索的信息不合法");
				break;

			case 7:
				// System.out.println("没有找到该用户");

				dispalyError(component, "没有找到该用户");
				break;
			case 8:
				// System.out.println("数据库查询错误");

				dispalyError(component, "数据库查询错误");
				break;
			}

		} catch (Exception e) {
			// System.out.println("数据返回错误");
			//
			dispalyError(component, "数据返回错误");
		}
		return num;
	}

	public static int retrievalBankCardNum(Component component) {
		int num = 0;
		if (USERSIGN == null || USERNANE == null) {
			// System.out.println("登录过期，请重新登陆！");

			dispalyError(component, "登录过期，请重新登陆！");
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
			// 请求错误
			// System.out.println("网络错误，请稍后再试");

			dispalyError(component, "网络错误，请稍后再试");
			return num;
		}
		try {
			JSONObject resultJSon = JSONObject.fromObject(result);
			boolean sign = resultJSon.containsKey("errorcode");
			if (!sign) {
				// System.out.println("数据返回错误");

				dispalyError(component, "数据返回错误");
				return num;
			}
			// System.out.println(resultJSon);
			int code = resultJSon.getInt("errorcode");
			switch (code) {
			case 0:
				// 成功

				sign = resultJSon.containsKey("num");
				if (!sign) {
					// System.out.println("数据返回错误");

					dispalyError(component, "数据返回错误");
					return num;
				}
				num = resultJSon.getInt("num");

				break;
			case 1:
				// System.out.println("action请求字符串不对");

				dispalyError(component, "action请求字符串不对");
				break;

			case 2:
				// System.out.println("信息被篡改");

				dispalyError(component, "信息被篡改");
				break;

			case 3:
				// System.out.println("字段不合法");

				dispalyError(component, "字段不合法");
				break;
			case 4:

				dispalyError(component, "用户名或者签名不合法");
				// System.out.println("用户名或者签名不合法");
				break;

			case 5:
				dispalyError(component, "用户名和签名不一致");
				// System.out.println("用户名和签名不一致");
				break;

			case 6:
				// System.out.println("需要检索的信息不合法");

				dispalyError(component, "需要检索的信息不合法");
				break;

			case 7:
				// System.out.println("没有找到该用户");

				dispalyError(component, "没有找到该用户");
				break;
			case 8:
				// System.out.println("数据库查询错误");

				dispalyError(component, "数据库查询错误");
				break;
			}

		} catch (Exception e) {
			// System.out.println("数据返回错误");

			dispalyError(component, "数据返回错误");
		}
		return num;
	}

	
}
