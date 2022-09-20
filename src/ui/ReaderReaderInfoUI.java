package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;

import control.Operator;
import model.Lend;
import model.Reader;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class ReaderReaderInfoUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReaderReaderInfoUI window = new ReaderReaderInfoUI();
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
	public ReaderReaderInfoUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Operator operator = new Operator();
		ArrayList<Reader> selReader = operator.getReaderInfo(operator.global_account,"","","","");
		
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
		
		JLabel lib = new JLabel("读者信息");
		lib.setBounds(267, 6, 67, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JTextField xingming = new JTextField();
		xingming.setText(selReader.get(0).getName());
		xingming.setBounds(256, 37, 121, 26);
		panel.add(xingming);
		xingming.setColumns(10);
		xingming.setEditable(false);
		
		JLabel la_xingming = new JLabel("姓      名：");
		la_xingming.setBounds(197, 41, 78, 19);
		panel.add(la_xingming);
		
		JLabel la_zhenghao = new JLabel("证      号：");
		la_zhenghao.setBounds(6, 41, 78, 19);
		panel.add(la_zhenghao);
		
		JLabel la_ximing = new JLabel("系      名：");
		la_ximing.setBounds(197, 72, 78, 16);
		panel.add(la_ximing);
		
		JLabel la_nianji = new JLabel("年      级：");
		la_nianji.setBounds(6, 72, 78, 16);
		panel.add(la_nianji);
		
		JLabel la_chaoqi = new JLabel("超期图书未还：");
		la_chaoqi.setBounds(389, 72, 128, 16);
		panel.add(la_chaoqi);
		
		JTextField zhenghao = new JTextField();
		zhenghao.setText(selReader.get(0).getreaderNum());
		zhenghao.setEditable(false);
		zhenghao.setColumns(10);
		zhenghao.setBounds(64, 37, 121, 26);
		panel.add(zhenghao);
		
		JTextField ximing = new JTextField();
		ximing.setText(selReader.get(0).getdepartment());
		ximing.setEditable(false);
		ximing.setColumns(10);
		ximing.setBounds(256, 68, 121, 26);
		panel.add(ximing);
		
		JTextField nianji = new JTextField();
		nianji.setText(selReader.get(0).getgrade());
		nianji.setEditable(false);
		nianji.setColumns(10);
		nianji.setBounds(64, 68, 121, 26);
		panel.add(nianji);
		
		JTextField chaoqi = new JTextField();
		if(operator.isReaderChaoqiweihuan(selReader.get(0).getreaderNum()))
		chaoqi.setText("是");
		else chaoqi.setText("否");
		chaoqi.setEditable(false);
		chaoqi.setColumns(10);
		chaoqi.setBounds(477, 68, 95, 26);
		panel.add(chaoqi);
		
		JLabel la_content = new JLabel("已借书籍列表：");
		la_content.setBounds(6, 100, 135, 19);
		panel.add(la_content);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 142, 574, 179);
		panel.add(scrollPane);
		
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Lend> allLend = operator.getHaveLent(selReader.get(0).getreaderNum());
		for(int i=0;i<allLend.size();i++)
		{   
			String s1 = new String(String.valueOf(allLend.get(i).getbookNum()));
			String s2 = new String(allLend.get(i).getbookName());
			String s3 = new String(allLend.get(i).getlendDate());
			String s4;
			if(allLend.get(i).getreturnDate()==null)
			{s4 = new String("     未还                      ");}
			else
			{s4 = new String(allLend.get(i).getreturnDate());}
			String s5 = new String(allLend.get(i).getretDeadline());
			String s6;
			if(operator.isBookChaoqi(allLend.get(i).getID()))
			{s6 = new String("是");}
			else
			{s6 = new String("否");}
			str.add(new String(String.format("%-10s%-19s%-22s%-25s%-19s%s",s1,s2,s3,s4,s5,s6)));
		}
		String[] strArray = str.toArray(new String[str.size()]);
		JList list = new JList();
		ListModel jListModel =  new DefaultComboBoxModel(strArray);
		list.setModel(jListModel);
		 list.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	if(e.getClickCount() == 2){
	            		List<String> values= list.getSelectedValuesList();
	            		String [] strArray = values.get(0).split(" ");
	            		operator.book_account=strArray[0];
	            		ReaderBookInfoUI bookInfo = new ReaderBookInfoUI();
	            		bookInfo.frame.setVisible(true);
	    				frame.dispose();
	                }
	            }
	            });
	        scrollPane.setViewportView(list);
		
		JLabel la_xingbie = new JLabel("性      别：");
		la_xingbie.setBounds(389, 41, 78, 19);
		panel.add(la_xingbie);
		
		JTextField xingbie = new JTextField();
		xingbie.setText(selReader.get(0).getsex());
		xingbie.setEditable(false);
		xingbie.setColumns(10);
		xingbie.setBounds(451, 37, 121, 26);
		panel.add(xingbie);
		
		JLabel label = new JLabel("书号");
		label.setBounds(6, 123, 61, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("书名");
		label_1.setBounds(64, 123, 61, 16);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("借书日期");
		lblNewLabel.setBounds(173, 123, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("还书日期");
		lblNewLabel_1.setBounds(301, 123, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("还书期限");
		lblNewLabel_2.setBounds(440, 123, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("超期");
		label_2.setBounds(546, 122, 26, 19);
		panel.add(label_2);
		
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
