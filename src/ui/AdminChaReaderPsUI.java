package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import control.Operator;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class AdminChaReaderPsUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenuUI window = new AdminMenuUI();
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
	public AdminChaReaderPsUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		Operator operator = new Operator();
		
		frame = new JFrame();
		frame.setTitle("图书管理系统——管理员");
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
		hint.setBounds(248, 217, 132, 27);
		hint.setForeground(Color.RED);
		panel.add(hint);
		
		JPasswordField password2 = new JPasswordField();
		password2.setBounds(250, 75, 130, 26);
		panel.add(password2);
		
		JPasswordField password3 = new JPasswordField();
		password3.setBounds(250, 125, 130, 26);
		panel.add(password3);
		
		JLabel label2 = new JLabel("新密码：");
		label2.setBounds(177, 80, 61, 16);
		panel.add(label2);
		
		JLabel label3 = new JLabel("新密码：");
		label3.setBounds(177, 130, 61, 16);
		panel.add(label3);
		
		JButton button = new JButton("确定");
		button.setBounds(277, 176, 75, 29);
		panel.add(button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                if(!password2.getText().equals(password3.getText()))
                {
                	hint.setText("新密码输入不一致！");
                }
                else if(password2.getText() == null || password2.getText().length() <= 0)
                {
                	hint.setText("新密码不能为空！");
                }
                else
                {
                	boolean ret = operator.chReaderPassword(operator.reader_account, password2.getText());
                    if(ret)
                    {
                ChaPsSuccessUI chaPsSuccess = new ChaPsSuccessUI();
    			chaPsSuccess.frame.setVisible(true);
    			frame.dispose();
			}
          }
         }
		});	
		
		JButton ckbook = new JButton("查看书籍信息");
		ckbook.setBounds(8, 8, 132, 29);
		frame.getContentPane().add(ckbook);
		
		JButton ckreader = new JButton("查看读者信息");
		ckreader.setBounds(308, 8, 132, 29);
		frame.getContentPane().add(ckreader);
		
		JButton glbook = new JButton("管理书籍信息");
		glbook.setBounds(158, 8, 132, 29);
		frame.getContentPane().add(glbook);
		
		JButton glreader = new JButton("管理读者信息");
		glreader.setBounds(458, 8, 132, 29);
		frame.getContentPane().add(glreader);
		
		JButton exit = new JButton("退出登录");
		exit.setBounds(494, 384, 96, 29);
		frame.getContentPane().add(exit);
		
		JButton chaps = new JButton("修改密码");
		chaps.setBounds(387, 384, 96, 29);
		frame.getContentPane().add(chaps);
		
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginUI Login = new LoginUI();
				Login.frame.setVisible(true);
				frame.dispose();
			}
		});
		chaps.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminChaPsUI adminChaPs = new AdminChaPsUI();
				adminChaPs.frame.setVisible(true);
				frame.dispose();
			}
		});	
	 ckreader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminCkReaderUI adminCkReader = new AdminCkReaderUI();
				adminCkReader.frame.setVisible(true);
				frame.dispose();
			}
		});	
	glbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGlBookUI adminGlBook = new AdminGlBookUI();
				adminGlBook.frame.setVisible(true);
				frame.dispose();
			}
		});	
	glreader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGlReaderUI adminGlReader = new AdminGlReaderUI();
				adminGlReader.frame.setVisible(true);
				frame.dispose();
			}
		});
	ckbook.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			AdminMenuUI adminMenu = new AdminMenuUI();
			adminMenu.frame.setVisible(true);
			frame.dispose();
		}
	});
	}
}
