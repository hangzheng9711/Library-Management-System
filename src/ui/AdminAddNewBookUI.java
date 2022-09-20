package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import control.Operator;
import model.Book;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminAddNewBookUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminAddNewBookUI window = new AdminAddNewBookUI();
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
	public AdminAddNewBookUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
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
		
		JLabel lib = new JLabel("增添新书籍");
		lib.setBounds(267, 6, 117, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JTextField shuming = new JTextField();
		shuming.setBounds(379, 37, 168, 26);
		panel.add(shuming);
		shuming.setColumns(10);
		
		JLabel la_shuming = new JLabel("书      名：");
		la_shuming.setBounds(323, 42, 78, 19);
		panel.add(la_shuming);
		
		JLabel la_shuhao = new JLabel("书      号：");
		la_shuhao.setBounds(87, 42, 78, 19);
		panel.add(la_shuhao);
		
		JLabel la_chubanshe = new JLabel("出 版  社：");
		la_chubanshe.setBounds(323, 76, 78, 16);
		panel.add(la_chubanshe);
		
		JLabel la_chubanriqi = new JLabel("出版日期：");
		la_chubanriqi.setBounds(87, 114, 78, 16);
		panel.add(la_chubanriqi);
		
		JLabel la_zongshu = new JLabel("增加数量：");
		la_zongshu.setBounds(323, 114, 78, 21);
		panel.add(la_zongshu);
		
		JTextField shuhao = new JTextField();
		ArrayList<Book> selBook = operator.getBookInfo(-1,"","","","",-1,-1,"书号");
		shuhao.setText(String.valueOf(selBook.size()+1));
		shuhao.setEditable(false);
		shuhao.setColumns(10);
		shuhao.setBounds(143, 37, 168, 26);
		panel.add(shuhao);
		
		JTextField chubanshe = new JTextField();
		chubanshe.setColumns(10);
		chubanshe.setBounds(379, 71, 168, 26);
		panel.add(chubanshe);
		
		JTextField chubanriqi = new JTextField("单击选择日期");
		chubanriqi.setColumns(10);
		chubanriqi.setBounds(143, 109, 168, 26);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(chubanriqi);
		panel.add(chubanriqi);
		
		JTextField zongshu = new JTextField();
		zongshu.setColumns(10);
		zongshu.setBounds(379, 109, 168, 26);
		panel.add(zongshu);
		
		JLabel la_content = new JLabel("内容摘要：");
		la_content.setBounds(87, 147, 78, 19);
		panel.add(la_content);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 168, 460, 125);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		
		JLabel la_zuozhe = new JLabel("作      者：");
		la_zuozhe.setBounds(87, 78, 78, 19);
		panel.add(la_zuozhe);
		
		JTextField zuozhe = new JTextField();
		zuozhe.setColumns(10);
		zuozhe.setBounds(143, 73, 168, 26);
		panel.add(zuozhe);
		
		JButton ok = new JButton("确定增添");
		ok.setBounds(270, 302, 96, 29);
		panel.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isOk=JOptionPane.showConfirmDialog(null, "是否确定增添?", "增添新书籍", JOptionPane.YES_NO_OPTION);
				if(isOk==JOptionPane.YES_OPTION){
					if(operator.addNewBook(operator.maxID+1,Long.parseLong(shuhao.getText()),Integer.parseInt(zongshu.getText()),shuming.getText(),zuozhe.getText(),chubanshe.getText(),chubanriqi.getText(),textArea.getText()))
					{
					operator.maxID++;
					SuccessUI success=new SuccessUI(1);
					success.frame.setVisible(true);
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
