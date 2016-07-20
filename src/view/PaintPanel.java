package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class PaintPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6427559347084723386L;

	private String mStr = null;

	private Color mColor = null;

	/**
	 * Create the panel.
	 */
	public PaintPanel() {
	}

	public void setmStr(String mStr) {
		this.mStr = mStr;
	}

	public void setmColor(Color mColor) {
		this.mColor = mColor;
	}

	@Override
	public void paint(Graphics g) {
		// 1.调用父类函数完成初始化
		// 这句话不能少
		super.paint(g);

		if (mStr != null && mColor != null) {
			g.setColor(mColor);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));

			// 获得字体宽度方法一
			FontMetrics fm = g.getFontMetrics();
			Rectangle2D rc = fm.getStringBounds(mStr, g);
			int w1 = getWidth();
			int h1 = getHeight();
			//System.out.println("w1:"+w1);
			//System.out.println("h1:"+h1);
			int w2 = (int) rc.getWidth();
			int h2 = (int) rc.getHeight();
			//System.out.println("w2:"+w2);
			//System.out.println("h2:"+h2);
			int tempX = (w1 - w2) / 2;
			int tempY = (h1 - h2) / 2+20;
			//System.out.println("tempX:"+tempX);
			//System.out.println("tempY:"+tempY);
			g.drawString(mStr, tempX, tempY);
		}

	}

}
