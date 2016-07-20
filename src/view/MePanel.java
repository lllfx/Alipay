package view;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextField;

import model.Model;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3879927247274300283L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	private PaintPanel ppanle1, ppanle2, ppanle3, ppanle4;

	/**
	 * Create the panel.
	 */
	public MePanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(265, 20, 150, 150);
		add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1, BorderLayout.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon("image//portrait2.jpg"));
		panel.setBorder(BorderFactory.createTitledBorder(""));

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(18, 180, 659, 291);
		add(panel_1);
		panel_1.setBorder(BorderFactory.createTitledBorder("账户概览"));
		panel_1.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createTitledBorder(""));
		panel_3.setBounds(18, 30, 140, 140);
		panel_1.add(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("image//balance.png"));
		panel_3.add(lblNewLabel, BorderLayout.CENTER);

		JPanel panel_4 = new JPanel();
		panel_4.setBorder(BorderFactory.createTitledBorder(""));
		panel_4.setBounds(176, 30, 140, 140);
		panel_1.add(panel_4);
		panel_4.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("image//bank.png"));
		panel_4.add(lblNewLabel_2, BorderLayout.CENTER);

		JPanel panel_5 = new JPanel();
		panel_5.setBorder(BorderFactory.createTitledBorder(""));
		panel_5.setBounds(334, 30, 140, 140);
		panel_1.add(panel_5);
		panel_5.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("image//transfer.png"));
		panel_5.add(lblNewLabel_3, BorderLayout.CENTER);

		JPanel panel_6 = new JPanel();
		panel_6.setBorder(BorderFactory.createTitledBorder(""));
		panel_6.setBounds(492, 30, 140, 140);
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("image//paymin.png"));
		panel_6.add(lblNewLabel_4, BorderLayout.CENTER);

		ppanle1 = new PaintPanel();
		ppanle1.setBounds(18, 200, 140, 60);
		ppanle1.setBorder(BorderFactory.createTitledBorder(""));
		panel_1.add(ppanle1);
		ppanle1.setmColor(Color.BLACK);
		ppanle1.setmStr("99999999999");

		ppanle2 = new PaintPanel();
		ppanle2.setBounds(176, 200, 140, 60);
		ppanle2.setBorder(BorderFactory.createTitledBorder(""));
		panel_1.add(ppanle2);

		ppanle3 = new PaintPanel();
		ppanle3.setBounds(334, 200, 140, 60);
		ppanle3.setBorder(BorderFactory.createTitledBorder(""));
		panel_1.add(ppanle3);

		ppanle4 = new PaintPanel();
		ppanle4.setBounds(492, 200, 140, 60);
		ppanle4.setBorder(BorderFactory.createTitledBorder(""));
		panel_1.add(ppanle4);
		ppanle4.setmColor(Color.BLACK);
		ppanle4.setmStr("0笔");

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 478, 659, 72);
		add(panel_2);
		panel_2.setBorder(BorderFactory.createTitledBorder("账户安全-修改密码"));
		panel_2.setLayout(null);

		JLabel lblNewLabel_5 = new JLabel("\u539F\u5BC6\u7801\uFF1A");
		lblNewLabel_5.setBounds(20, 30, 60, 20);
		panel_2.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setBounds(80, 25, 100, 30);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		lblNewLabel_6.setBounds(190, 30, 53, 20);
		panel_2.add(lblNewLabel_6);

		textField_1 = new JTextField();
		textField_1.setBounds(249, 25, 100, 30);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label.setBounds(359, 30, 66, 20);
		panel_2.add(label);

		textField_2 = new JTextField();
		textField_2.setBounds(419, 26, 100, 30);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JButton btnNewButton = new JButton("\u4FEE\u6539");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		btnNewButton.setBounds(540, 25, 100, 30);
		panel_2.add(btnNewButton);
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				int num = Model.retrievalBankCardNum(MePanel.this);

				ppanle2.setmColor(Color.BLACK);
				ppanle2.setmStr("X" + num);
				ppanle2.repaint();
			}

		}).start();

		new Thread(new Runnable() {

			@Override
			public void run() {
				int num = Model.retrievalTransferRecoderNum(MePanel.this);
				ppanle3.setmColor(Color.BLACK);
				ppanle3.setmStr(num + "笔");
				ppanle3.repaint();
			}

		}).start();
	}
}
