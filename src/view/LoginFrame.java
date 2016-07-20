package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import model.Model;
import tools.Tools;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9068508625158296866L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JButton btnRegister;
	private JPasswordField txtPassword;

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400, 300);
		Tools.setCenterLoaction(this);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("\u652F\u4ED8\u5B9D\u6A21\u62DF\u7CFB\u7EDF");
		lblNewLabel.setFont(UIManager.getFont("Button.font"));
		lblNewLabel.setBounds(138, 10, 112, 42);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		lblNewLabel_1.setBounds(83, 99, 54, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\u5BC6  \u7801\uFF1A");
		lblNewLabel_2.setBounds(83, 149, 54, 15);
		contentPane.add(lblNewLabel_2);

		txtUserName = new JTextField();
		txtUserName.setBounds(169, 92, 120, 30);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		JButton btnLogin = new JButton("\u767B\u5F55");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final String userName = txtUserName.getText();
				@SuppressWarnings("deprecation")
				final String password = txtPassword.getText();
				// 简单的过滤
				if (!Tools.strIsOk(userName) || !Tools.strIsOk(password)) {
					JOptionPane.showMessageDialog(LoginFrame.this, "用户名或者密码无效！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				// 使用模型登录
				new Thread(new Runnable() {

					@Override
					public void run() {
						boolean sign = Model.login(userName, password, LoginFrame.this);
						if (sign) {
							MainFrame mainFrame = new MainFrame();
							mainFrame.setVisible(true);
							LoginFrame.this.dispose();
						}
					}

				}).start();

			}
		});
		btnLogin.setBounds(83, 214, 90, 30);
		contentPane.add(btnLogin);

		btnRegister = new JButton("\u6CE8\u518C");

		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterFrame frame = new RegisterFrame(getLocation());
				frame.setVisible(true);
				LoginFrame.this.dispose();
			}
		});
		btnRegister.setBounds(212, 214, 90, 30);
		contentPane.add(btnRegister);

		txtPassword = new JPasswordField();
		txtPassword.setBounds(169, 149, 120, 30);
		contentPane.add(txtPassword);

		setIconImage(new ImageIcon("image/zhifubao.jpg").getImage());
	}
}
