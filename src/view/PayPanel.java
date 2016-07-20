package view;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import model.Model;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import tools.Coder;
import tools.Tools;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PayPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6704064618309546409L;
	private JLabel lblcode1;
	private JLabel lblcode2;
	private JTextField txtAmount;
	private JTextField txtAccount;
	private JTable table;
	private DefaultTableModel tableModel; // ���ģ�Ͷ���
	String[] columnNames = { "���", "ת���˺�", "ת���˺�", "���", "ʱ��" }; // ����

	/**
	 * Create the panel.
	 */
	public PayPanel() {
		setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 660, 540);
		add(panel_1);

		panel_1.setBorder(BorderFactory.createTitledBorder("ת��&����"));
		panel_1.setLayout(null);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(475, 120, 174, 181);
		panel_1.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));

		lblcode1 = new JLabel();
		panel_6.add(lblcode1, BorderLayout.CENTER);

		JLabel lblNewLabel_6 = new JLabel("\u6211\u7684\u6536\u6B3E\u7801");
		panel_6.add(lblNewLabel_6, BorderLayout.SOUTH);

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(475, 330, 174, 181);
		panel_1.add(panel_7);
		panel_7.setLayout(new BorderLayout(0, 0));

		lblcode2 = new JLabel();
		panel_7.add(lblcode2, BorderLayout.CENTER);

		JLabel lblNewLabel_7 = new JLabel("\u6211\u7684\u4ED8\u6B3E\u7801");
		panel_7.add(lblNewLabel_7, BorderLayout.SOUTH);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createTitledBorder(""));
		panel.setBounds(22, 24, 628, 64);
		panel_1.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u5BF9\u65B9\u8D26\u53F7\uFF1A");
		lblNewLabel.setBounds(93, 20, 93, 20);
		panel.add(lblNewLabel);

		txtAccount = new JTextField();
		txtAccount.setBounds(173, 16, 120, 30);
		panel.add(txtAccount);
		txtAccount.setColumns(10);

		JButton btnNewButton = new JButton("\u8F6C\u8D26");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String account = txtAccount.getText();
				String amount = txtAmount.getText();
				if (!Tools.strIsOk(account) || !Tools.strIsOk(amount)) {
					JOptionPane.showMessageDialog(PayPanel.this, "�Է��˺Ż��߽����Ч��", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean resultSign = Model.transferAccounts(account, amount, PayPanel.this);
				if (resultSign) {
					JOptionPane.showMessageDialog(PayPanel.this, "ת�˳ɹ���", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
					initData();
				}
			}
		});
		btnNewButton.setBounds(498, 15, 100, 30);
		panel.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("\u91D1\u989D\uFF1A");
		lblNewLabel_1.setBounds(328, 20, 50, 20);
		panel.add(lblNewLabel_1);

		txtAmount = new JTextField();
		txtAmount.setDocument(new NumberLenghtLimitedDmt(10));
		txtAmount.setBounds(388, 16, 60, 30);
		panel.add(txtAmount);
		txtAmount.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(22, 90, 436, 432);
		panel_1.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.setBorder(BorderFactory.createTitledBorder("��¼"));
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // ��ѡ
		tableModel = new DefaultTableModel(null, columnNames);
		table.setModel(tableModel);
		scrollPane.setViewportView(table);
		setCode();
	}

	public void setCode() {
		String path1 = Coder.createQrcode("�ҵ��տ���", "in");
		ImageIcon ic1 = new ImageIcon(path1);
		lblcode1.setIcon(ic1);

		String path2 = Coder.createQrcode("�ҵĸ�����", "out");
		ImageIcon ic2 = new ImageIcon(path2);
		lblcode2.setIcon(ic2);
	}

	public void initData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				JSONArray jsonArray = Model.retrievalTransferRecoder(PayPanel.this);
				if (jsonArray == null) {
					return;
				}
				int size = jsonArray.size();
				String[][] tableVales = new String[size][5];
				for (int i = 0; i < size; i++) {
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					tableVales[i][0] = (i + 1) + "";
					tableVales[i][1] = jsonObject.getString("in_Name");
					tableVales[i][2] = jsonObject.getString("out_Name");
					tableVales[i][3] = jsonObject.getString("amount");
					tableVales[i][4] = jsonObject.getString("date");
				}
				tableModel.setDataVector(tableVales, columnNames);
			}

		}).start();

	}
}
