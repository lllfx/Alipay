package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class CardPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994291546369895364L;
	public static final int width = 325;
	public static final int height = 200;
	public static final int padding = 5;

	/**
	 * Create the panel.
	 */
	public CardPanel() {

	}

	private String bankName;

	private String cardNumber;

	private Color mColor = null;

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setCardNumber(String cardNumber) {
		
		int len = cardNumber.length();
		if (len <= 4) {
			this.cardNumber = cardNumber;
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < len - 4; i++) {
				sb.append(cardNumber.charAt(i));
			}
			sb.append("****");

			this.cardNumber = sb.toString();
		}


	}

	public void setmColor(Color mColor) {
		this.mColor = mColor;
	}

	@Override
	public void paint(Graphics g) {
		// 1.调用父类函数完成初始化
		// 这句话不能少
		super.paint(g);

		if (cardNumber != null && bankName != null && mColor != null) {
			g.setColor(mColor);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

			// 获得字体宽度方法一
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D rc = fm.getStringBounds(cardNumber, g);
			int w1 = getWidth();
			int h1 = getHeight();
			// System.out.println("w1:"+w1);
			// System.out.println("h1:"+h1);
			int w2 = (int) rc.getWidth();
			int h2 = (int) rc.getHeight();
			// System.out.println("w2:"+w2);
			// System.out.println("h2:"+h2);
			int tempX = (w1 - w2) / 2;
			int tempY = (h1 - h2) / 2 + 20;
			// System.out.println("tempX:"+tempX);
			// System.out.println("tempY:"+tempY);
			g.drawString(cardNumber, tempX, (h1 - h2));

			g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
			fm = g.getFontMetrics();
			rc = fm.getStringBounds(bankName, g);
			w2 = (int) rc.getWidth();
			h2 = (int) rc.getHeight();
			tempX = (w1 - w2) / 2;
			tempY = (h1 - h2) / 2 + 20;
			g.drawString(bankName, tempX, tempY);
		}

	}

}
