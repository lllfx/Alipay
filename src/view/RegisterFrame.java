package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import model.Model;
import tools.Tools;
import java.awt.event.ActionListener;
import java.awt.Point;
import java.awt.event.ActionEvent;

public class RegisterFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7175858304883074675L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JPasswordField txtPassword1;
	private JPasswordField txtPassword2;

	/**
	 * Create the frame.
	 * 
	 * @param point
	 */
	public RegisterFrame(Point point) {
		setResizable(false);
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 300);
		setLocation(point);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u652F\u4ED8\u5B9D\u6A21\u62DF\u7CFB\u7EDF");
		label.setFont(UIManager.getFont("Button.font"));
		label.setBounds(145, 10, 112, 42);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u7528\u6237\u540D\uFF1A");
		label_1.setBounds(79, 79, 75, 15);
		contentPane.add(label_1);

		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(164, 72, 120, 30);
		contentPane.add(txtUserName);

		JLabel label_2 = new JLabel("\u5BC6 \u7801\uFF1A");
		label_2.setBounds(79, 130, 54, 15);
		contentPane.add(label_2);

		JButton button = new JButton("\u6CE8\u518C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String userName = txtUserName.getText();
				@SuppressWarnings("deprecation")
				String password1 = txtPassword1.getText();
				@SuppressWarnings("deprecation")
				String password2 = txtPassword2.getText();
				// 简单的过滤
				if (!Tools.strIsOk(userName) || !Tools.strIsOk(password1) || !Tools.strIsOk(password2)) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "用户名或者密码无效！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!password1.equals(password2)) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "两次输入的密码不一致！", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean sign = Model.register(userName, password1, RegisterFrame.this);
				if (sign) {
					JOptionPane.showMessageDialog(RegisterFrame.this, "注册成功！", "提示", JOptionPane.INFORMATION_MESSAGE);
					LoginFrame loginFrame=new LoginFrame();
					loginFrame.setVisible(true);
					RegisterFrame.this.dispose();
				}

			}
		});
		button.setBounds(151, 220, 90, 30);
		contentPane.add(button);

		JLabel label_3 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_3.setBounds(79, 170, 75, 20);
		contentPane.add(label_3);

		txtPassword1 = new JPasswordField();
		txtPassword1.setBounds(164, 123, 120, 30);
		contentPane.add(txtPassword1);

		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(164, 166, 120, 30);
		contentPane.add(txtPassword2);
		setIconImage(new ImageIcon("image/zhifubao.jpg").getImage());
	}
}
