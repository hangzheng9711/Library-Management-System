package ui;

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
import javax.swing.ListModel;

import control.Operator;
import model.Book;
import model.Lend;
import model.Reader;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class RetuUI {

	JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RetuUI window = new RetuUI();
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
	public RetuUI() {
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
		
		JLabel lib = new JLabel("还书");
		lib.setBounds(287, 6, 117, 19);
		lib.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		panel.add(lib);
		
		JLabel la_content = new JLabel("已借书籍列表：");
		la_content.setBounds(6, 35, 135, 19);
		panel.add(la_content);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 87, 574, 179);
		panel.add(scrollPane);
		
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Lend> allLend = operator.getHaveLent(selReader.get(0).getreaderNum());
		ArrayList<Integer> lendID = new ArrayList<Integer>();
		for(int i=0;i<allLend.size();i++)
		{   
			String s4;
			if(allLend.get(i).getreturnDate()!=null)
			{continue;}
			else
			{s4 = new String("     未还                      ");
			String s1 = new String(String.valueOf(allLend.get(i).getbookNum()));
			String s2 = new String(allLend.get(i).getbookName());
			String s3 = new String(allLend.get(i).getlendDate());
			
			String s5 = new String(allLend.get(i).getretDeadline());
			String s6;
			if(operator.isBookChaoqi(allLend.get(i).getID()))
			{s6 = new String("是");}
			else
			{s6 = new String("否");}
			str.add(new String(String.format("%-10s%-19s%-22s%-25s%-19s%s",s1,s2,s3,s4,s5,s6)));
			lendID.add(new Integer(allLend.get(i).getID()));
			}
		}
		String[] strArray = str.toArray(new String[str.size()]);
		JList list = new JList();
		ListModel jListModel =  new DefaultComboBoxModel(strArray);
		list.setModel(jListModel);
	    scrollPane.setViewportView(list);  
		
		JLabel label = new JLabel("书号");
		label.setBounds(6, 65, 61, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("书名");
		label_1.setBounds(64, 65, 61, 16);
		panel.add(label_1);
		
		JLabel lblNewLabel = new JLabel("借书日期");
		lblNewLabel.setBounds(173, 65, 61, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("还书日期");
		lblNewLabel_1.setBounds(301, 65, 61, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("还书期限");
		lblNewLabel_2.setBounds(440, 65, 61, 16);
		panel.add(lblNewLabel_2);
		
		JLabel label_2 = new JLabel("超期");
		label_2.setBounds(546, 65, 26, 19);
		panel.add(label_2);
		
		JButton ok2 = new JButton("确定还书");
		ok2.setBounds(257, 287, 96, 29);
		panel.add(ok2);
		ok2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int isOk=JOptionPane.showConfirmDialog(null, "是否确定还书?", "还书", JOptionPane.YES_NO_OPTION);
				if(isOk==JOptionPane.YES_OPTION){
					List<String> values= list.getSelectedValuesList();
	        		String [] strArray = values.get(0).split("\\s+");
	        		String retDeadline=strArray[4];
	        		
					int lend_ID=lendID.get(list.getSelectedIndex()).intValue();

					if(operator.isBookChaoqi(lend_ID))
						{
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
						Date toda = new Date();
						Date retDeadlin = new Date();
						try {
							retDeadlin = df.parse(retDeadline);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						long diff = toda.getTime() - retDeadlin.getTime();
						long days = diff / (1000 * 60 * 60 * 24);
						JOptionPane.showMessageDialog(null,"已超期"+days+"天！","警告",JOptionPane.WARNING_MESSAGE);
						if(operator.ret(lend_ID))
						{
		        			ReaderSuccessUI success=new ReaderSuccessUI(9);
							success.frame.setVisible(true);
							frame.dispose();
		        		}
						}
					else
					{
	        		if(operator.ret(lend_ID))
					{
	        			ReaderSuccessUI success=new ReaderSuccessUI(9);
						success.frame.setVisible(true);
						frame.dispose();
	        		}
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
