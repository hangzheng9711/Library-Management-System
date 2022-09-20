package ui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import control.Operator;
import model.Reader;

public class AdminChaPsUI {

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
	public AdminChaPsUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
		Operator operator=new Operator();
		
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
		
		JLabel label = new JLabel("修改管理员密码");
		label.setBounds(18, 6, 154, 24);
		panel.add(label);
		
		JList adminAccount = new JList();
		adminAccount.setBounds(18, 29, 546, 17);
		adminAccount.setModel(new AbstractListModel() {
			String[] values = new String[] {operator.global_account};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		adminAccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount() == 2){
            		AdminChaAdminPsUI adminChaAdminPs = new AdminChaAdminPsUI();
                	adminChaAdminPs.frame.setVisible(true);
    				frame.dispose();
                }
            }
            });
		panel.add(adminAccount);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(18, 87, 546, 239);
		panel.add(scrollPane);
		
		JLabel label_1 = new JLabel("修改读者密码");
		label_1.setBounds(18, 60, 154, 24);
		panel.add(label_1);
		
		ArrayList<String> str = new ArrayList<String> ();
		ArrayList<Reader> allReader = operator.getReaderInfo("", "", "", "", "");
		for(int i=0;i<allReader.size();i++)
		{
			str.add(allReader.get(i).getreaderNum());
		}
		String[] strArray = str.toArray(new String[str.size()]);
		JList list = new JList();
		list.setBounds(18, 87, 196, 238);
		ListModel jListModel =  new DefaultComboBoxModel(strArray);
		list.setModel(jListModel);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount() == 2){
            		List<String> values= list.getSelectedValuesList();
            		operator.reader_account=new String(values.get(0));
            		AdminChaReaderPsUI adminChaReaderPs = new AdminChaReaderPsUI();
                	adminChaReaderPs.frame.setVisible(true);
    				frame.dispose();
                }
            }
            });
		scrollPane.setViewportView(list); 
		
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
