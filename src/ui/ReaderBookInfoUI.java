package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class ReaderBookInfoUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderBookInfoUI window = new ReaderBookInfoUI();
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
	public ReaderBookInfoUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Operator operator = new Operator();
		ArrayList<Book> selBook = operator.getBookInfo(Long.parseLong(operator.book_account),"","","","",-1,-1,"书号");
		
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
		
		JLabel lib = new JLabel("书籍信息");
		lib.setBounds(267, 6, 67, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JTextField shuming = new JTextField();
		shuming.setText(selBook.get(0).getbookName());
		shuming.setBounds(379, 37, 168, 26);
		panel.add(shuming);
		shuming.setColumns(10);
		shuming.setEditable(false);
		
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
		
		JLabel la_zongshu = new JLabel("总数：");
		la_zongshu.setBounds(323, 109, 61, 16);
		panel.add(la_zongshu);
		
		JTextField shuhao = new JTextField();
		shuhao.setText(String.valueOf(selBook.get(0).getbookNum()));
		shuhao.setEditable(false);
		shuhao.setColumns(10);
		shuhao.setBounds(143, 37, 168, 26);
		panel.add(shuhao);
		
		JTextField chubanshe = new JTextField();
		chubanshe.setText(selBook.get(0).getpubHouse());
		chubanshe.setEditable(false);
		chubanshe.setColumns(10);
		chubanshe.setBounds(379, 71, 168, 26);
		panel.add(chubanshe);
		
		JTextField chubanriqi = new JTextField();
		chubanriqi.setText(selBook.get(0).getpubDate());
		chubanriqi.setEditable(false);
		chubanriqi.setColumns(10);
		chubanriqi.setBounds(143, 109, 168, 26);
		panel.add(chubanriqi);
		
		JTextField zongshu = new JTextField();
		zongshu.setText(String.valueOf(selBook.get(0).getsum()));
		zongshu.setEditable(false);
		zongshu.setColumns(10);
		zongshu.setBounds(351, 104, 55, 26);
		panel.add(zongshu);
		
		JLabel la_mqzaiguanshu = new JLabel("目前在馆数：");
		la_mqzaiguanshu.setBounds(426, 109, 86, 16);
		panel.add(la_mqzaiguanshu);
		
		JTextField mqzaiguanshu = new JTextField();
		mqzaiguanshu.setText(String.valueOf(selBook.get(0).getsumNow()));
		mqzaiguanshu.setEditable(false);
		mqzaiguanshu.setColumns(10);
		mqzaiguanshu.setBounds(492, 104, 55, 26);
		panel.add(mqzaiguanshu);
		
		JLabel la_content = new JLabel("内容摘要：");
		la_content.setBounds(87, 147, 78, 19);
		panel.add(la_content);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(87, 168, 460, 150);
		panel.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setText(selBook.get(0).getcontent());
		
		JLabel la_zuozhe = new JLabel("作      者：");
		la_zuozhe.setBounds(87, 78, 78, 19);
		panel.add(la_zuozhe);
		
		JTextField zuozhe = new JTextField();
		zuozhe.setText(selBook.get(0).getauthor());
		zuozhe.setEditable(false);
		zuozhe.setColumns(10);
		zuozhe.setBounds(143, 73, 168, 26);
		panel.add(zuozhe);
		
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
