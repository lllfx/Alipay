package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import tools.Tools;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8765204956577010942L;

	private JPanel contentPane;

	private JSplitPane splitPane;

	private JPanel panel;
	private final JButton btn2 = new JButton("");
	private final JButton btn4 = new JButton("");
	private final JButton btn5 = new JButton("");
	private final JButton btn6 = new JButton("");
	private PayPanel payPanel = null;
	private BankPanel bankPanel = null;
	private MePanel mePanel = null;

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		splitPane = new JSplitPane();
		splitPane.setEnabled(false);
		contentPane.add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		// splitPane.setRightComponent(new JPanel());

		// int totalWidth = splitPane.getWidth();
		// double ratio = 151.0 / totalWidth;
		// splitPane.setDividerLocation(ratio);

		panel.setLayout(new GridLayout(9, 1));

		panel.add(btn2);
		btn2.setIcon(new javax.swing.ImageIcon("image//pay.png"));
		btn2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (payPanel == null) {
					payPanel = new PayPanel();
					payPanel.initData();
				}
				splitPane.setRightComponent(payPanel);
			}
		});
		btn4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (bankPanel == null) {
					bankPanel = new BankPanel();
					bankPanel.initData();
				}
				splitPane.setRightComponent(bankPanel);
			}
		});
		panel.add(btn4);
		btn4.setIcon(new javax.swing.ImageIcon("image//tie.png"));
		btn5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (mePanel == null) {
					mePanel = new MePanel();
					mePanel.initData();
				}
				splitPane.setRightComponent(mePanel);
			}
		});
		panel.add(btn5);
		btn5.setIcon(new javax.swing.ImageIcon("image//account.png"));
		btn6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 退出系统
				int sign = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION);
				if (sign == JOptionPane.YES_OPTION) {
					exit();
				}
			}
		});
		panel.add(btn6);
		btn6.setIcon(new javax.swing.ImageIcon("image//exit.png"));

		if (payPanel == null) {
			payPanel = new PayPanel();
			payPanel.initData();
		}
		splitPane.setRightComponent(payPanel);
		setIconImage(new ImageIcon("image/zhifubao.jpg").getImage());
		Tools.setCenterLoaction(this);

	}

	// 重写这个方法
	@Override
	protected void processWindowEvent(WindowEvent e) {
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			int sign = JOptionPane.showConfirmDialog(null, "确定要退出系统吗？", "退出系统", JOptionPane.YES_NO_OPTION);
			if (sign == JOptionPane.YES_OPTION) {
				exit();
			} else {
				return;
			}
		}
		super.processWindowEvent(e); // 该语句会执行窗口事件的默认动作(如：隐藏)
	}

	private void exit() {
		System.exit(0);
	}
}
