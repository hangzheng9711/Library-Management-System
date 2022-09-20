package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.Operator;
import model.Reader;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AdminChaReader2UI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminChaReader2UI window = new AdminChaReader2UI();
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
	public AdminChaReader2UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Operator operator = new Operator();
		ArrayList<Reader> selReader = operator.getReaderInfo(operator.reader_account,"","","","");
		
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
		
		JLabel step = new JLabel("Step2：修改基本信息");
		step.setBounds(251, 28, 191, 22);
		panel.add(step);
		
		JTextField xingming = new JTextField();
		xingming.setText(selReader.get(0).getName());
		xingming.setBounds(267, 98, 173, 26);
		panel.add(xingming);
		xingming.setColumns(10);
		
		JLabel la_xingming = new JLabel("姓      名：");
		la_xingming.setBounds(197, 102, 78, 19);
		panel.add(la_xingming);
		
		JLabel la_zhenghao = new JLabel("证      号：");
		la_zhenghao.setBounds(197, 61, 78, 19);
		panel.add(la_zhenghao);
		
		JLabel la_ximing = new JLabel("系      名：");
		la_ximing.setBounds(197, 228, 78, 19);
		panel.add(la_ximing);
		
		JLabel la_nianji = new JLabel("年      级：");
		la_nianji.setBounds(197, 187, 78, 19);
		panel.add(la_nianji);
		
		JTextField zhenghao = new JTextField();
		zhenghao.setText(selReader.get(0).getreaderNum());
		zhenghao.setEditable(false);
		zhenghao.setColumns(10);
		zhenghao.setBounds(267, 57, 173, 26);
		panel.add(zhenghao);
		
		JLabel la_xingbie = new JLabel("性      别：");
		la_xingbie.setBounds(197, 146, 78, 19);
		panel.add(la_xingbie);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"男", "女"}));
		comboBox.setSelectedItem(selReader.get(0).getsex());
		comboBox.setBounds(267, 143, 173, 26);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"大一", "大二", "大三", "大四"}));
		comboBox_1.setSelectedItem(selReader.get(0).getgrade());
		comboBox_1.setBounds(267, 184, 173, 26);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"计算系", "化学系", "生物系"}));
		comboBox_2.setSelectedItem(selReader.get(0).getdepartment());
		comboBox_2.setBounds(267, 225, 173, 26);
		panel.add(comboBox_2);
		
		JButton ok = new JButton("确定修改");
		ok.setBounds(267, 259, 96, 29);
		panel.add(ok);
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isOk=JOptionPane.showConfirmDialog(null, "是否确定修改读者基本信息?", "修改读者基本信息", JOptionPane.YES_NO_OPTION);
				if(operator.chReader(zhenghao.getText(),xingming.getText(),comboBox_2.getSelectedItem().toString(),comboBox_1.getSelectedItem().toString(),comboBox.getSelectedItem().toString()))
				{
				SuccessUI success=new SuccessUI(7);
				success.frame.setVisible(true);
				frame.dispose();
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
