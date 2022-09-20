package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import control.Operator;
import model.Book;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminDelBook2UI {

	JFrame frame;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDelBook2UI window = new AdminDelBook2UI();
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
	public AdminDelBook2UI() {
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
		
		JLabel lib = new JLabel("删除书籍");
		lib.setBounds(267, 6, 117, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JLabel step = new JLabel("Step2：选择删除数量");
		step.setBounds(251, 28, 191, 22);
		panel.add(step);
		
		JLabel la_shuliang = new JLabel("数量：");
		la_shuliang.setBounds(251, 171, 39, 16);
		panel.add(la_shuliang);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 98, 574, 40);
		panel.add(scrollPane);
		
		long bookNumm=Long.parseLong(operator.book_account);
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Book> selBook = operator.getBookInfo(bookNumm,"","","","",-1,-1,"书号");
		for(int i=0;i<selBook.size();i++)
		{   
			String s1 = new String(String.valueOf(selBook.get(i).getbookNum()));
			String s2 = new String(selBook.get(i).getbookName());
			String s3 = new String(selBook.get(i).getauthor());
			String s4 = new String(selBook.get(i).getpubHouse());
			String s5 = new String(selBook.get(i).getpubDate());
			String s6 = new String(String.valueOf(selBook.get(i).getsum()));
			String s7 = new String(String.valueOf(selBook.get(i).getsumNow()));
			str.add(new String(String.format("%-10s%-27s%-15s%-15s%-12s%-10s%s",s1,s2,s3,s4,s5,s6,s7)));
		}
		String[] strArray = str.toArray(new String[str.size()]);
		JList list = new JList();
		ListModel jListModel =  new DefaultComboBoxModel(strArray);
		list.setModel(jListModel);
	    scrollPane.setViewportView(list);
		
		textField = new JTextField();
		textField.setBounds(288, 165, 96, 29);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel hint = new JLabel("");
		hint.setBounds(260, 250, 132, 27);
		hint.setForeground(Color.RED);
		panel.add(hint);
	    
		JButton ok = new JButton("确定删除");
		ok.setBounds(267, 218, 96, 29);
		panel.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isOk=JOptionPane.showConfirmDialog(null, "是否确定删除?", "删除书籍", JOptionPane.YES_NO_OPTION);
				if(isOk==JOptionPane.YES_OPTION){
					int i = operator.delBook(bookNumm,Integer.parseInt(textField.getText()));
					if(i==1)
					{
					SuccessUI success=new SuccessUI(3);
					success.frame.setVisible(true);
					frame.dispose();
					}
					else if(i==3)
						hint.setText("删除数量大于总数量！");
					else if(i==2)
						hint.setText("还有未归还的书！");
				}
			}
		});	
		
		JLabel label = new JLabel("书号");
		label.setBounds(10, 79, 39, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("书名");
		label_1.setBounds(61, 79, 39, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("作者");
		label_2.setBounds(194, 79, 39, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("出版社");
		label_3.setBounds(271, 79, 52, 16);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("出版日期");
		label_4.setBounds(407, 79, 52, 16);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("总数");
		label_5.setBounds(470, 79, 39, 16);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("目前在馆数");
		label_6.setBounds(511, 79, 79, 16);
		panel.add(label_6);
		
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
