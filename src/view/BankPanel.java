package view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.Tools;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class BankPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2823300683166260979L;

	private JPanel panel2 = null;
	private JScrollPane scrollPane;
	private JTextField txt_name;
	private JTextField txt_number;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public BankPanel() {
		setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder("添加银行卡"));
		panel.setBounds(10, 10, 660, 120);
		add(panel);
		panel.setLayout(null);

		JButton btnNewButton = new JButton("\u6DFB\u52A0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// add(5);
				// 添加银行卡
				String name = txt_name.getText();
				String number = txt_number.getText();
				if (!Tools.strIsOk(name) || !Tools.strIsOk(number)) {
					JOptionPane.showMessageDialog(BankPanel.this, "姓名或者卡号无效！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (number.length() != 19 && number.length() != 16) {
					JOptionPane.showMessageDialog(BankPanel.this, "卡号为16位或者19位！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String bankName = comboBox.getSelectedItem().toString();
				boolean resultSign = Model.addBankCard(name, number, bankName, BankPanel.this);
				if (resultSign) {
					JOptionPane.showMessageDialog(BankPanel.this, "添加成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					initData();
				}

			}
		});
		btnNewButton.setBounds(423, 83, 100, 30);
		panel.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel.setBounds(39, 33, 60, 20);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u94F6\u884C\uFF1A");
		lblNewLabel_1.setBounds(39, 88, 60, 20);
		panel.add(lblNewLabel_1);

		txt_name = new JTextField();
		txt_name.setBounds(109, 30, 120, 30);
		panel.add(txt_name);
		txt_name.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u5361\u53F7\uFF1A");
		lblNewLabel_2.setBounds(333, 33, 60, 20);
		panel.add(lblNewLabel_2);

		txt_number = new JTextField();
		txt_number.setDocument(new NumberLenghtLimitedDmt(19));
		txt_number.setBounds(403, 29, 150, 30);
		panel.add(txt_number);
		txt_number.setColumns(10);

		comboBox = new JComboBox<String>(Tools.getBankList());
		comboBox.setBounds(109, 83, 120, 30);
		panel.add(comboBox);

		panel2 = new JPanel();
		panel2.setLayout(null);
		panel2.setPreferredSize(new Dimension(595, 395));
		scrollPane = new JScrollPane(panel2);
		scrollPane.setBounds(10, 140, 660, 410);
		add(scrollPane);

	}


	private static int getSY(int index, int height, int padding) {
		int temp1 = (index - 1) / 2;
		return temp1 * (height + padding) + padding;
	}

	private static int getSX(int index, int width, int padding) {
		if (index % 2 == 0) {
			return 2 * padding + width;
		} else {
			return padding;
		}
	}

	/**
	 * 初始化数据
	 */
	public void initData() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				panel2.removeAll();
				panel2.repaint();
				panel2.setLayout(null);
				CardPanel cardPanel = null;
				JSONArray jsonArray = Model.retrievalBankCard(BankPanel.this);
				int size = jsonArray.size();
				for (int i = 0; i < size; i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					String bank_name = jsonObject.getString("bank_name");
					String card_number = jsonObject.getString("card_number");
					cardPanel = new CardPanel();
					cardPanel.setBorder(BorderFactory.createTitledBorder(""));
					cardPanel.setmColor(Color.DARK_GRAY);
					cardPanel.setBankName(bank_name);
					cardPanel.setCardNumber(card_number);
					panel2.add(cardPanel);
					cardPanel.setBounds(getSX((i + 1), CardPanel.width, CardPanel.padding),
							getSY(i, CardPanel.height, CardPanel.padding), CardPanel.width, CardPanel.height);

					// tableVales[i][0] = (i + 1) + "";
					// tableVales[i][1] = jsonObject.getString("in_Name");
					// tableVales[i][2] = jsonObject.getString("out_Name");
					// tableVales[i][3] = jsonObject.getString("amount");
					// tableVales[i][4] = jsonObject.getString("date");
				}
				// tableModel.setDataVector(tableVales, columnNames);
			}

		}).start();

	}
}
