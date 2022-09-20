package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import control.Operator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReaderChaPsUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderChaPsUI window = new ReaderChaPsUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReaderChaPsUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Operator operator = new Operator();
		
		frame = new JFrame();
		frame.setTitle("图书管理系统——读者");
		frame.setBounds(100, 100, 600, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		int windowWidth = frame.getWidth(); //获得窗口宽
		int windowHeight = frame.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		frame.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 44, 586, 337);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lib = new JLabel("修改密码");
		lib.setBounds(277, 26, 65, 27);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JLabel hint = new JLabel("");
		hint.setBounds(250, 247, 132, 27);
		hint.setForeground(Color.RED);
		panel.add(hint);
		
		JPasswordField password1 = new JPasswordField();
		password1.setBounds(250, 65, 130, 26);
		panel.add(password1);
		
		JPasswordField password2 = new JPasswordField();
		password2.setBounds(250, 115, 130, 26);
		panel.add(password2);
		
		JPasswordField password3 = new JPasswordField();
		password3.setBounds(250, 163, 130, 26);
		panel.add(password3);
		
		JLabel label1 = new JLabel("旧密码：");
		label1.setBounds(177, 70, 61, 16);
		panel.add(label1);
		
		JLabel label2 = new JLabel("新密码：");
		label2.setBounds(177, 120, 61, 16);
		panel.add(label2);
		
		JLabel label3 = new JLabel("新密码：");
		label3.setBounds(177, 168, 61, 16);
		panel.add(label3);
		
		JButton button = new JButton("确定");
		button.setBounds(277, 206, 75, 29);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isRight = operator.isReaderPasswordRight(operator.global_account, password1.getText());
                if(!isRight)
                {
                hint.setText("旧密码输入错误！");
                }
                else if(!password2.getText().equals(password3.getText()))
                {
                	hint.setText("新密码输入不一致！");
                }
                else if(password2.getText() == null || password2.getText().length() <= 0)
                {
                	hint.setText("新密码不能为空！");
                }
                else
                {
                	boolean ret = operator.chReaderPassword(operator.global_account, password2.getText());
                    if(ret)
                    {
        				ReaderChaPsSuccessUI chaPsSuccess = new ReaderChaPsSuccessUI();
        				chaPsSuccess.frame.setVisible(true);
        				frame.dispose();
                    }
                }

			}
		});	
		
		JButton exit = new JButton("退出登录");
		exit.setBounds(494, 384, 96, 29);
		frame.getContentPane().add(exit);
		
		JButton chaps = new JButton("修改密码");
		chaps.setBounds(387, 384, 96, 29);
		frame.getContentPane().add(chaps);
		
		JButton ckbook = new JButton("查看书籍信息");
		ckbook.setBounds(8, 8, 132, 29);
		frame.getContentPane().add(ckbook);
		
		JButton lend = new JButton("借书");
		lend.setBounds(308, 8, 132, 29);
		frame.getContentPane().add(lend);
		
		JButton ckreader = new JButton("查看读者信息");
		ckreader.setBounds(158, 8, 132, 29);
		frame.getContentPane().add(ckreader);
		
		JButton retu = new JButton("还书");
		retu.setBounds(458, 8, 132, 29);
		frame.getContentPane().add(retu);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI Login = new LoginUI();
				Login.frame.setVisible(true);
				frame.dispose();
			}
		});
	   chaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderChaPsUI readerChaPs = new ReaderChaPsUI();
				readerChaPs.frame.setVisible(true);
				frame.dispose();
			}
		});	
	   ckreader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderReaderInfoUI readerInfo = new ReaderReaderInfoUI();
				readerInfo.frame.setVisible(true);
				frame.dispose();
			}
		});	
	   ckbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderMenuUI readerMenu = new ReaderMenuUI();
				readerMenu.frame.setVisible(true);
				frame.dispose();
			}
		});	
    	lend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LendUI lendUi = new LendUI();
				lendUi.frame.setVisible(true);
				frame.dispose();
			}
		});	
		retu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RetuUI retuUi = new RetuUI();
				retuUi.frame.setVisible(true);
				frame.dispose();
			}
		});	
	}
}
