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
import model.Reader;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class AdminChaReaderUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChaReaderUI window = new AdminChaReaderUI();
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
	public AdminChaReaderUI() {
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
		
		JLabel lib = new JLabel("修改读者基本信息");
		lib.setBounds(251, 6, 151, 22);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JLabel step = new JLabel("Step1：选择修改读者");
		step.setBounds(251, 28, 191, 22);
		panel.add(step);
		
		JLabel la_shaixuan = new JLabel("筛选：");
		la_shaixuan.setBounds(6, 56, 39, 16);
		panel.add(la_shaixuan);
		
		JComboBox chaoqi = new JComboBox();
		chaoqi.setModel(new DefaultComboBoxModel(new String[] {"", "是", "否"}));
		chaoqi.setBounds(128, 115, 63, 26);
		panel.add(chaoqi);
		
		JLabel la_chaoqi = new JLabel("超期图书未还：");
		la_chaoqi.setBounds(45, 114, 147, 26);
		panel.add(la_chaoqi);
		
		JLabel la_zhenghao = new JLabel("证号：");
		la_zhenghao.setBounds(45, 56, 39, 16);
		panel.add(la_zhenghao);
		
		JLabel la_xingming = new JLabel("姓名：");
		la_xingming.setBounds(267, 56, 52, 16);
		panel.add(la_xingming);
		
		JLabel la_ximing = new JLabel("系名：");
		la_ximing.setBounds(45, 90, 73, 16);
		panel.add(la_ximing);
		
		JTextField zhenghao = new JTextField();
		zhenghao.setBounds(83, 51, 172, 26);
		panel.add(zhenghao);
		zhenghao.setColumns(10);
		
		JTextField xingming = new JTextField();
		xingming.setBounds(301, 51, 116, 26);
		panel.add(xingming);
		xingming.setColumns(10);
		
		JComboBox ximing = new JComboBox();
		ximing.setModel(new DefaultComboBoxModel(new String[] {"", "计算系", "化学系", "生物系"}));
		ximing.setBounds(83, 89, 172, 20);
		panel.add(ximing);
		
		JLabel la_chubanriqi = new JLabel("年级：");
		la_chubanriqi.setBounds(267, 90, 73, 16);
		panel.add(la_chubanriqi);
		
		JLabel la_xingbie = new JLabel("性别：");
		la_xingbie.setBounds(433, 56, 39, 16);
		panel.add(la_xingbie);
		
		JComboBox nianji = new JComboBox();
		nianji.setModel(new DefaultComboBoxModel(new String[] {"", "大一", "大二", "大三", "大四"}));
		nianji.setBounds(301, 90, 116, 20);
		panel.add(nianji);
		
		JComboBox xingbie = new JComboBox();
		xingbie.setModel(new DefaultComboBoxModel(new String[] {"","男", "女"}));
		xingbie.setBounds(473, 51, 63, 26);
		panel.add(xingbie);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 162, 574, 119);
		panel.add(scrollPane);
		
		String readerNum = zhenghao.getText();
		String Name = xingming.getText();
		String sex = xingbie.getSelectedItem().toString();
		String grade = nianji.getSelectedItem().toString();
		String department = ximing.getSelectedItem().toString();
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Reader> selReader = operator.getReaderInfo(readerNum,Name,sex,grade,department);
		for(int i=0;i<selReader.size();i++)
		{   
			String s1 = new String(selReader.get(i).getreaderNum());
			String s2 = new String(selReader.get(i).getName());
			String s3 = new String(selReader.get(i).getsex());
			String s4 = new String(selReader.get(i).getgrade());
			String s5 = new String(selReader.get(i).getdepartment());
			String s6;
			if(operator.isReaderChaoqiweihuan(s1))
			{s6 = new String("是");}
			else
			{s6 = new String("否");}
			if(chaoqi.getSelectedItem().toString().equals(""))
			str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
			else if(chaoqi.getSelectedItem().toString().equals("是")&&s6.equals("是"))
			str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
			else if(chaoqi.getSelectedItem().toString().equals("否")&&s6.equals("否"))
				str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
		}
		String[] strArray = str.toArray(new String[str.size()]);
		JList list = new JList();
		ListModel jListModel =  new DefaultComboBoxModel(strArray);
		list.setModel(jListModel);
	    scrollPane.setViewportView(list);
		
		JButton ok = new JButton("确定");
		ok.setBounds(203, 115, 63, 26);
		panel.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String readerNum = zhenghao.getText();
				String Name = xingming.getText();
				String sex = xingbie.getSelectedItem().toString();
				String grade = nianji.getSelectedItem().toString();
				String department = ximing.getSelectedItem().toString();
				ArrayList<String> str = new ArrayList<String> ();
				ArrayList<Reader> selReader = operator.getReaderInfo(readerNum,Name,sex,grade,department);
				for(int i=0;i<selReader.size();i++)
				{   
					String s1 = new String(selReader.get(i).getreaderNum());
					String s2 = new String(selReader.get(i).getName());
					String s3 = new String(selReader.get(i).getsex());
					String s4 = new String(selReader.get(i).getgrade());
					String s5 = new String(selReader.get(i).getdepartment());
					String s6;
					if(operator.isReaderChaoqiweihuan(s1))
					{s6 = new String("是");}
					else
					{s6 = new String("否");}
					if(chaoqi.getSelectedItem().toString().equals(""))
					str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
					else if(chaoqi.getSelectedItem().toString().equals("是")&&s6.equals("是"))
					str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
					else if(chaoqi.getSelectedItem().toString().equals("否")&&s6.equals("否"))
						str.add(new String(String.format("%-24s%-20s%-12s%-20s%-13s%s",s1,s2,s3,s4,s5,s6)));
				}
				String[] strArray = str.toArray(new String[str.size()]);
				ListModel jListModel =  new DefaultComboBoxModel(strArray);
				list.setModel(jListModel);
			}
		});	
		
		JLabel label = new JLabel("证号");
		label.setBounds(6, 144, 39, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("姓名");
		label_1.setBounds(160, 144, 39, 16);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("性别");
		label_2.setBounds(255, 144, 39, 16);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("系名");
		label_3.setBounds(315, 144, 52, 16);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel(" 年级");
		label_4.setBounds(410, 144, 52, 16);
		panel.add(label_4);
		
		JLabel label_5 = new JLabel("超期图书未还");
		label_5.setBounds(485, 144, 96, 16);
		panel.add(label_5);
		
		JButton ok2 = new JButton("下一步");
		ok2.setBounds(267, 293, 96, 29);
		panel.add(ok2);
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> values= list.getSelectedValuesList();
        		String [] strArray = values.get(0).split(" ");
        		operator.reader_account=strArray[0];
				AdminChaReader2UI adminChaReader2=new AdminChaReader2UI();
				adminChaReader2.frame.setVisible(true);
				frame.dispose();
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
