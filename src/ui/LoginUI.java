package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.UIManager;

import control.Operator;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginUI {
	
	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	     
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceRavenGraphiteGlassLookAndFeel");  
		            //JFrame.setDefaultLookAndFeelDecorated(true);
		            //JDialog.setDefaultLookAndFeelDecorated(true);
					LoginUI window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Operator operator = new Operator();  
		
		frame = new JFrame();
		frame.setTitle("图书管理系统");
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
		
		JLabel label_account = new JLabel("账号：");
		label_account.setBounds(189, 79, 39, 16);
		frame.getContentPane().add(label_account);
		
		JLabel label_password = new JLabel("密码：");
		label_password.setBounds(189, 124, 39, 16);
		frame.getContentPane().add(label_password);
		
		JLabel lib = new JLabel("登录");
		lib.setBounds(283, 25, 30, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		frame.getContentPane().add(lib);
		
		JLabel hint = new JLabel("");
		hint.setBounds(240, 215, 132, 27);
		hint.setForeground(Color.RED);
		frame.getContentPane().add(hint);
		
		JComboBox comboBox = new JComboBox(new String[]
				 { "管理员","读者" });
		comboBox.setBounds(394, 75, 95, 27);
		frame.getContentPane().add(comboBox);
		
		JTextField account = new JTextField();
		account.setBounds(240, 73, 130, 29);
		frame.getContentPane().add(account);
		
		JPasswordField password = new JPasswordField();
		password.setBounds(240, 118, 130, 29);
		frame.getContentPane().add(password);
		
		operator.maxID=operator.getIDMax();
		
		JButton button = new JButton("登录");
		button.setBounds(267, 173, 75, 29);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sel =(String)comboBox.getSelectedItem();
				if(sel.equals("管理员"))
				{
                    boolean isRight = operator.isAdminPasswordRight(account.getText(), password.getText());
                    if(isRight)
                    {
                operator.global_account=new String(account.getText());
				AdminMenuUI AdminMenu = new AdminMenuUI();
				AdminMenu.frame.setVisible(true);
				frame.dispose();
                    }
                    else
                    hint.setText("账号或密码输入错误！");
				}
				else
				{
                    boolean isRight = operator.isReaderPasswordRight(account.getText(), password.getText());
                    if(isRight)
                    {
                operator.global_account=new String(account.getText());
				ReaderMenuUI readerMenu = new ReaderMenuUI();
				readerMenu.frame.setVisible(true);
				frame.dispose();
                    }
                    else
                    hint.setText("账号或密码输入错误！");
				}
			}
		});
		frame.getContentPane().add(button);
	}
}