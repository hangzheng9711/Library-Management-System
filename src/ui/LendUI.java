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
import java.util.List;
import java.awt.event.ActionEvent;

public class LendUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LendUI window = new LendUI();
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
	public LendUI() {
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
		
		JLabel lib = new JLabel("借书");
		lib.setBounds(287, 6, 117, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JLabel la_shaixuan = new JLabel("筛选：");
		la_shaixuan.setBounds(6, 42, 39, 16);
		panel.add(la_shaixuan);
		
		JLabel la_shuhao = new JLabel("书号：");
		la_shuhao.setBounds(45, 42, 39, 16);
		panel.add(la_shuhao);
		
		JLabel la_shuming = new JLabel("书名：");
		la_shuming.setBounds(148, 42, 52, 16);
		panel.add(la_shuming);
		
		JLabel la_chubanshe = new JLabel("出版社：");
		la_chubanshe.setBounds(45, 70, 73, 16);
		panel.add(la_chubanshe);
		
		JTextField shuhao = new JTextField();
		shuhao.setBounds(83, 37, 53, 26);
		panel.add(shuhao);
		shuhao.setColumns(10);
		
		JTextField shuming = new JTextField();
		shuming.setBounds(190, 37, 214, 26);
		panel.add(shuming);
		shuming.setColumns(10);
		
		JTextField chubanshe = new JTextField();
		chubanshe.setColumns(10);
		chubanshe.setBounds(93, 65, 199, 26);
		panel.add(chubanshe);
		
		JLabel la_chubanriqi = new JLabel("出版日期：");
		la_chubanriqi.setBounds(304, 70, 73, 16);
		panel.add(la_chubanriqi);
		
		JTextField chubanriqi = new JTextField("单击选择日期");
		chubanriqi.setBounds(366, 65, 130, 26);
		chubanriqi.setColumns(10);
		DateChooser dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
		dateChooser1.register(chubanriqi);
		panel.add(chubanriqi);
		
		JLabel la_zuozhe = new JLabel("作者：");
		la_zuozhe.setBounds(416, 42, 39, 16);
		panel.add(la_zuozhe);
		
		JTextField zuozhe = new JTextField();
		zuozhe.setColumns(10);
		zuozhe.setBounds(454, 37, 100, 26);
		panel.add(zuozhe);
		
		JLabel la_zongshu = new JLabel("总数：");
		la_zongshu.setBounds(45, 98, 39, 16);
		panel.add(la_zongshu);
		
		JTextField zongshu = new JTextField();
		zongshu.setColumns(10);
		zongshu.setBounds(83, 93, 52, 26);
		panel.add(zongshu);
		
		JLabel la_mqzaiguanshu = new JLabel("目前在馆数：");
		la_mqzaiguanshu.setBounds(148, 98, 116, 16);
		panel.add(la_mqzaiguanshu);
		
		JTextField mqzaiguanshu = new JTextField();
		mqzaiguanshu.setColumns(10);
		mqzaiguanshu.setBounds(227, 93, 52, 26);
		panel.add(mqzaiguanshu);
		
		JLabel hint = new JLabel("");
		hint.setBounds(117, 287, 132, 27);
		hint.setForeground(Color.RED);
		panel.add(hint);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 155, 574, 120);
		panel.add(scrollPane);
		
		long bookNumm;
		if(shuhao.getText() == null || shuhao.getText().length() <= 0 || !panduan.isNumeric(shuhao.getText())) {bookNumm = -1;}
		else {bookNumm = Long.parseLong(shuhao.getText());}
		String bookNamee = shuming.getText();
		String authorr = zuozhe.getText();
		String pubHousee = chubanshe.getText();
		String pubDatee = chubanriqi.getText();
		int summ;
		if(zongshu.getText() == null || zongshu.getText().length() <= 0 || !panduan.isNumeric(zongshu.getText())) {summ = -1;}
		else {summ = Integer.parseInt(zongshu.getText());}
		int sumNoww;
		if(mqzaiguanshu.getText() == null || mqzaiguanshu.getText().length() <= 0 || !panduan.isNumeric(mqzaiguanshu.getText())) {sumNoww = -1;}
		else {sumNoww = Integer.parseInt(mqzaiguanshu.getText());}
		String order="书号";
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Book> selBook = operator.getBookInfo(bookNumm,bookNamee,authorr,pubHousee,pubDatee,sumNoww,summ,order);
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
		
		JButton ok2 = new JButton("确定借书");
		ok2.setBounds(257, 287, 96, 29);
		panel.add(ok2);
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isOk=JOptionPane.showConfirmDialog(null, "是否确定借书?", "借书", JOptionPane.YES_NO_OPTION);
				if(isOk==JOptionPane.YES_OPTION){
					List<String> values= list.getSelectedValuesList();
	        		String [] strArray = values.get(0).split(" ");
	        		String book_account=strArray[0];
	        		if(operator.isReaderChaoqiweihuan(operator.global_account))
					{
	        			hint.setText("还有未归还的超期书！");
					}
	        		else
	        		{
	        			int i = operator.lend(operator.global_account,Long.parseLong(book_account));
	        			if(i==2)
	        		{
	        			hint.setText("该书已被借完！");
	        		}
	        		else if(i==1)
	        		{
	        			ReaderSuccessUI success=new ReaderSuccessUI(8);
						success.frame.setVisible(true);
						frame.dispose();
	        		}
	        		}
			}
			}
		});	
		
		JButton ok = new JButton("确定");
		ok.setBounds(290, 94, 63, 26);
		panel.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				long bookNumm;
				if(shuhao.getText() == null || shuhao.getText().length() <= 0 || !panduan.isNumeric(shuhao.getText())) {bookNumm = -1;}
				else {bookNumm = Long.parseLong(shuhao.getText());}
				String bookNamee = shuming.getText();
				String authorr = zuozhe.getText();
				String pubHousee = chubanshe.getText();
				String pubDatee = chubanriqi.getText();
				int summ;
				if(zongshu.getText() == null || zongshu.getText().length() <= 0 || !panduan.isNumeric(zongshu.getText())) {summ = -1;}
				else {summ = Integer.parseInt(zongshu.getText());}
				int sumNoww;
				if(mqzaiguanshu.getText() == null || mqzaiguanshu.getText().length() <= 0 || !panduan.isNumeric(mqzaiguanshu.getText())) {sumNoww = -1;}
				else {sumNoww = Integer.parseInt(mqzaiguanshu.getText());}
				String order="书号";
				ArrayList<String> str = new ArrayList<String> ();
				ArrayList<Book> selBook = operator.getBookInfo(bookNumm,bookNamee,authorr,pubHousee,pubDatee,sumNoww,summ,order);
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
				ListModel jListModel =  new DefaultComboBoxModel(strArray);
				list.setModel(jListModel);
			}
		});	
		
		JLabel label = new JLabel("书号");
		label.setBounds(6, 131, 39, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("书名");
		label_1.setBounds(57, 131, 39, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("作者");
		label_2.setBounds(190, 131, 39, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("出版社");
		label_3.setBounds(267, 131, 52, 16);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("出版日期");
		label_4.setBounds(403, 131, 52, 16);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("总数");
		label_5.setBounds(466, 131, 39, 16);
		panel.add(label_5);
		
		JLabel label_6 = new JLabel("目前在馆数");
		label_6.setBounds(507, 131, 79, 16);
		panel.add(label_6);
		
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
