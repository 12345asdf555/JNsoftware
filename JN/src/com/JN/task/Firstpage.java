package com.JN.task;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;

public class Firstpage extends JFrame{
	private String connet = "jdbc:mysql://192.168.3.231:3306/CIWJN?user=db_admin&password=PIJXmcLRa0QgOw2c&useUnicode=true&autoReconnect=true&characterEncoding=UTF8";
	private java.sql.Connection conn = null;
	private java.sql.Statement stmt =null;
    
	public ArrayList<String> listarray1 = new ArrayList<String>();
    public ArrayList<String> listarray21 = new ArrayList<String>();
    public ArrayList<String> listarray22 = new ArrayList<String>();
    public ArrayList<String> listarray3 = new ArrayList<String>();
    public ArrayList<String> listarray4 = new ArrayList<String>();
    public ArrayList<String> listarraywe = new ArrayList<String>();
    public ArrayList<String> listarrayta = new ArrayList<String>();
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JLabel l21 = new JLabel("XXXX-XX-XX XX:XX:XX");
	private JLabel l4 = new JLabel("正在与服务器通讯...");
	private JScrollPane s4;
	private JTable t4 = new JTable();
	
	public String time;
	public String worktime;
	public String welder;
	public String weldowner;
	public String weldernum = "";
	public Firstpage context;
	public Dimension screensize;
	
	private IsnullUtil iutil;
	private JaxWsDynamicClientFactory dcf;
	private Client client;
	public boolean first = true;
	
	public Firstpage(){
		super("江南派工");
		
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		label_6.setForeground(Color.WHITE);
		label_6.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		getContentPane().setBackground(new Color(20,51,105));
		
		
		new Thread(initdate).start();  //获取数据
		//initdate(); //获取数据
		initframe(); //绘制界面
		time(); //电子时钟
		
		context = this;
	}
	
	Runnable initdate = new Runnable(){
		private String ip;

		public void run(){
			
			try {
				  FileInputStream in = new FileInputStream("IPconfig.txt");  
		          InputStreamReader inReader = new InputStreamReader(in, "UTF-8");  
		          BufferedReader bufReader = new BufferedReader(inReader);  
		          String line = null; 
		          int writetime=0;
					
				    while((line = bufReader.readLine()) != null){ 
				    	if(writetime==0){
			                ip=line;
			                writetime++;
				    	}
		          }  

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			//任务webservice
			try {
				IsnullUtil iutil  =  new  IsnullUtil();
				JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
				client = dcf.createClient("http://" + ip + ":8080/CIWJN_Service/cIWJNWebService?wsdl");
				iutil.Authority(client);
				
				String obj1 = "{\"CLASSNAME\":\"junctionWebServiceImpl\",\"METHOD\":\"getWeldedJunctionAll\"}";
				Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
						new Object[] { obj1 });
				String restr = objects[0].toString();
		        JSONArray ary = JSONArray.parseArray(restr);
		        for(int i=0;i<ary.size();i++){
			        String str = ary.getString(i);
			        JSONObject js = JSONObject.fromObject(str);
			        
			        if(js.getString("OPERATESTATUS").equals("")){
			        	listarray3.add(js.getString("TASKNO"));
			        	listarray3.add(js.getString("ITEMNAME"));
			        	listarray3.add(js.getString("WELDERNAME"));
			        	listarray3.add(js.getString("TASKDES"));
			        	listarrayta.add(js.getString("TASKNO"));
			        	listarrayta.add(js.getString("ID"));
			        }else if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
			        	listarray4.add(js.getString("ITEMNAME"));
		        		listarray4.add(js.getString("REWELDERNO"));
		        		listarray4.add(js.getString("REWELDERNAME"));
			        	listarray4.add(js.getString("MACHINENO"));
			        	listarray4.add(js.getString("TASKNO"));
			        	listarray4.add("焊接");
			        	listarray4.add(js.getString("STARTTIME"));
			        	listarray4.add(js.getString("TASKDES"));
			        }
			        
			        String a = js.getString("MACHINENO");
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "服务器未开启.", "  错误",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
			Timer tExit = null; 
			tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {
	            	try {
	            		if(!first && (client != null)){
	            			context.t4.removeAll();
	            			context.s4.remove(t4);
	            			context.remove(s4);
	            			context.repaint();
	            			
	                		s4 = new JScrollPane(t4);
	                		s4.setBounds(10, (int)p2.getLocation().getY()+p2.getHeight()+3, screensize.width-20, screensize.height-((int)p2.getLocation().getY()+p2.getHeight())-10);
	            			s4.getViewport().setBackground(Color.white);
	            			getContentPane().add(s4);
	                		
	                		listarray3.clear();
	                		listarray4.clear();
	                		listarrayta.clear();
	                		
	                		String obj1 = "{\"CLASSNAME\":\"junctionWebServiceImpl\",\"METHOD\":\"getWeldedJunctionAll\"}";
	            			Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
	            					new Object[] { obj1 });
	            			String restr = objects[0].toString();
	            	        JSONArray ary = JSONArray.parseArray(restr);
	            	        for(int i=0;i<ary.size();i++){
	            		        String str = ary.getString(i);
	            		        JSONObject js = JSONObject.fromObject(str);
	            		        
	            		        if(js.getString("OPERATESTATUS").equals("")){
	            		        	listarray3.add(js.getString("TASKNO"));
	            		        	listarray3.add(js.getString("ITEMNAME"));
	            		        	listarray3.add(js.getString("WELDERNAME"));
	            		        	listarray3.add(js.getString("TASKDES"));
	            		        	listarrayta.add(js.getString("TASKNO"));
	            		        	listarrayta.add(js.getString("ID"));
	            		        }else if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
	            		        	listarray4.add(js.getString("ITEMNAME"));
	            	        		listarray4.add(js.getString("REWELDERNO"));
	            	        		listarray4.add(js.getString("REWELDERNAME"));
	            		        	listarray4.add(js.getString("MACHINENO"));
	            		        	listarray4.add(js.getString("TASKNO"));
	            		        	listarray4.add("焊接");
	            		        	listarray4.add(js.getString("STARTTIME"));
	            		        	listarray4.add(js.getString("TASKDES"));
	            		        }
	            		        
	            		        String a = js.getString("MACHINENO");
	            	        }
	            	        
	            	      //table列名以及值
	            			String[] cn = {"班组","焊工编号", "焊工", "焊机", "任务号","当前状态","开始时间","任务描述"};  
	            			Object[][] obj = new Object[listarray4.size()/8][8];  
	            			for(int i=0;i<listarray4.size()/8;i++){
	            				for(int j=0;j<8;j++){
	            					obj[i][j] = listarray4.get(i*8+j);
	            				}
	            			}
	            			//绘图
	            			t4 = new JTable(obj,cn){
	            				public boolean isCellEditable(int row, int column)
	            	            {
	            	                   return false;//表格不允许被编辑
	            	            }
	            			};
	            			t4.setFont(new Font("宋体",1,14));
	            			JTableHeader head = t4.getTableHeader(); // 创建表格标题对象
	            			head.setFont(new Font("宋体", 1, 16));
	            			Dimension size = head.getPreferredSize();
	            			size.height = 35;
	            			head.setPreferredSize(size);
	            			t4.setBackground(Color.white);
	            			
	            			//设置table宽高
	            			TableColumn column = null;  
	            	        int colunms = t4.getColumnCount();  
	            	        for(int i = 0; i < colunms; i++)  
	            	        {  
	            	            column = t4.getColumnModel().getColumn(i);
	            	            if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
	            	            	column.setPreferredWidth(100);
	            	            }else if(i == 6){
	            	            	column.setPreferredWidth(200);
	            	            }else if(i == 7){
	            	            	column.setPreferredWidth(screensize.width-823);
	            	            }
	            	        }  
	            	        
	            	        //居中显示，关闭自动编辑
	            	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
	            	        r.setHorizontalAlignment(JLabel.CENTER);   
	            	        t4.setDefaultRenderer(Object.class, r);
	            	        t4.setRowHeight(50);
	            	        t4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            	        
	            			context.repaint(); 
	            		}
	            		
	            		first = false;
	        	        
	        		} catch (Exception e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	            }
	        }, 0, 60000);
			
			//焊机webservice
			try {
				String obj2 = "{\"CLASSNAME\":\"weldingMachineWebServiceImpl\",\"METHOD\":\"getWeldingMachineAll\"}";
				Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
						new Object[] { obj2 });
			    String restr = objects[0].toString();
		        JSONArray ary = JSONArray.parseArray(restr);
		        for(int i=0;i<ary.size();i++){
			        String str = ary.getString(i);
			        JSONObject js = JSONObject.fromObject(str);
			        listarray22.add(js.getString("MACHINENO"));
			        listarray22.add(js.getString("MANUFACTURERNAME"));
			        listarray22.add(js.getString("INSFRAMEWORKNAME"));
			        listarray22.add(js.getString("POSITION"));
			        listarraywe.add(js.getString("MACHINENO"));
			        listarraywe.add(js.getString("ID"));
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//班组统计
			int count = 0;
			for(int i=0;i<listarray22.size();i+=4){
				if(listarray21.size() == 0){
					listarray21.add(listarray22.get(i+2));
				}else{
					for(int j=0;j<listarray21.size();j++){
						if(!listarray22.get(i+2).equals(listarray21.get(j))){
							count++;
						}
					}
					if(count == listarray21.size()){
						listarray21.add(listarray22.get(i+2));
						count = 0;
					}else{
						count = 0;
					}
				}
			}
			
			//table列名以及值
			String[] cn = {"班组", "焊工编号", "焊工", "焊机", "任务号","当前状态","开始时间","任务描述"};  
			Object[][] obj = new Object[listarray4.size()/8][8];  
			for(int i=0;i<listarray4.size()/8;i++){
				for(int j=0;j<8;j++){
					obj[i][j] = listarray4.get(i*8+j);
				}
			}
			//绘图
			t4 = new JTable(obj,cn){
				public boolean isCellEditable(int row, int column)
	            {
	                   return false;//表格不允许被编辑
	            }
			};
			t4.setFont(new Font("宋体",1,14));
			JTableHeader head = t4.getTableHeader(); // 创建表格标题对象
			head.setFont(new Font("宋体", 1, 16));
			Dimension size = head.getPreferredSize();
			size.height = 35;
			head.setPreferredSize(size);
			t4.setBackground(Color.white);
			
			//设置table宽高
			TableColumn column = null;  
	        int colunms = t4.getColumnCount();  
	        for(int i = 0; i < colunms; i++)  
	        {  
	        	column = t4.getColumnModel().getColumn(i);
	            if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
	            	column.setPreferredWidth(110);
	            }else if(i == 6){
	            	column.setPreferredWidth(200);
	            }else if(i == 7){
	            	column.setPreferredWidth(screensize.width-883);
	            }
	        }  
	        
	        //居中显示，关闭自动编辑
	        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
	        r.setHorizontalAlignment(JLabel.CENTER);   
	        t4.setDefaultRenderer(Object.class, r);
	        t4.setRowHeight(50);
	        t4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        s4 = new JScrollPane(t4);
	        s4.setBounds(10, (int)p2.getLocation().getY()+p2.getHeight()+3, screensize.width-20, screensize.height-((int)p2.getLocation().getY()+p2.getHeight())-15);
			s4.getViewport().setBackground(Color.white);
			context.getContentPane().add(s4);
	        
			if(client != null){
				l4.setText("     执行中的任务 ");
			}
			
			repaint(); 
			
		}
	};
	
	private final JLabel label_2 = new JLabel("");
	private final JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
	private final JLabel label = new JLabel("焊工 ：   ");
	private final JLabel label_1 = new JLabel("焊工编号：");
	private final JTextField textField = new JTextField(8);
	private final JLabel label_4 = new JLabel("编号：   ");
	private final JLabel label_5 = new JLabel("焊机 ：   ");
	private final JLabel label_6 = new JLabel("类型：   ");
	private final JLabel label_7 = new JLabel("任务：   ");

	private void time() {
		// TODO Auto-generated method stub
		Timer tExit1 = null; 
		tExit1 = new Timer();  
        tExit1.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//一秒刷新一次时钟
				Date date = new Date();
                time = DateTools.format("yyyy-MM-dd HH:mm:ss",date);
				l21.setText(time);
				
			}  
        }, 0 , 1000);
	}

	private void initframe() {
		// TODO Auto-generated method stub
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screensize.width,screensize.height);
		setVisible(true);
		
		//信息列表
		p1 = new JPanel(){
		  @Override  
	        protected void paintComponent(Graphics g) {  
	            ImageIcon icon = new ImageIcon(getClass().getResource("/images/firstmid.png"));  
	            Image img = icon.getImage();  
	            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  
	        }  
		};
		p1.setFont(new Font("Dialog",1,17));
		p1.setLayout(null);
		
		//时钟
		p2.setFont(new Font("Dialog",1,17));
		p2.setBackground(new Color(20,51,105));
		p2.setLayout(null);
		
		Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
		
		//显示时钟
		l21.setFont(new Font("微软雅黑", Font.PLAIN, 22));
		l21.setForeground(Color.white);
		l21.setBounds((int)screensize1.getWidth()-280, 4, 261, 23);
		//l21.setText("2018-10-10 10:10:10");
		p2.add(l21);
		
		JPanel panel = new JPanel(){
		  @Override  
	        protected void paintComponent(Graphics g) { 
			  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
	            ImageIcon icon = new ImageIcon(getClass().getResource("/images/firsttop1.png"));  
	            Image img = icon.getImage();  
	            g.drawImage(img, 50, 23, 530, 41, this);  
	            
	            ImageIcon icon1 = new ImageIcon(getClass().getResource("/images/firsttop2.png"));  
	            Image img1 = icon1.getImage();  
	            g.drawImage(img1, screensize1.width-550, 20, 490, 50, this); 
	        }  
		};
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1920, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(p1, GroupLayout.DEFAULT_SIZE, 1896, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(p2, GroupLayout.DEFAULT_SIZE, 1906, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(p1, GroupLayout.PREFERRED_SIZE, 379, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(p2, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addGap(574))
		);
		
		int width2 = ((int)screensize1.getWidth()-216)/2;
		
		l4.setForeground(Color.WHITE);
		l4.setBounds(width2, 0, 216, 29);
		p2.add(l4);
		l4.setFont(new Font("微软雅黑 Light", Font.BOLD, 23));
		
		JInternalFrame internalFrame = new JInternalFrame(){
			protected void paintComponent(Graphics g) {
		        // TODO Auto-generated method stub
				AlphaComposite cmp = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1);
		        Graphics2D g2d = (Graphics2D)g;
		        g2d.setComposite(cmp.derive(0.7f));
		        super.paintComponent(g2d);
			}
	    };
		int width1 = ((int)screensize1.getWidth()-590)/2;
		internalFrame.setBounds(width1, 37, 590, 298);
		internalFrame.setBackground(Color.black);
		internalFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrame.setOpaque(false);
		internalFrame.setVisible(true);
		
		BasicInternalFrameUI ui = (BasicInternalFrameUI)internalFrame.getUI();
		ui.setNorthPane(null);
		
		internalFrame_1.setBounds(width1, 37, 590, 298);
		p1.add(internalFrame_1);
		internalFrame_1.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		internalFrame_1.setBackground(new Color(0,0,0,0));
		internalFrame_1.setOpaque(false);
		internalFrame_1.setVisible(true);
		
		BasicInternalFrameUI ui1 = (BasicInternalFrameUI)internalFrame_1.getUI();
		ui1.setNorthPane(null);
		
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("微软雅黑 Light", Font.BOLD, 25));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("微软雅黑", Font.BOLD, 25));
		textField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				weldernum = textField.getText();
				if(weldernum.length() == 4){
					
					textField.updateUI();
					
					worktime = time;
					boolean exist = false;
					
					//查找焊工webservice
					try {
						String obj1 = "{\"CLASSNAME\":\"welderWebServiceImpl\",\"METHOD\":\"getWelderByNum\"}";
						String obj2 = "{\"WELDERNO\":\"" + weldernum + "\"}";
						
						Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterTheWS"),
								new Object[] { obj1,obj2 });
						
						String restr = objects[0].toString();
				        JSONArray ary = JSONArray.parseArray(restr);
				        if(ary.size() == 0){
				        	JOptionPane.showMessageDialog(null, "焊工编号错误.", "  错误",JOptionPane.ERROR_MESSAGE);
				        	textField.setCaretPosition(4);
				        }else{
					        String str = ary.getString(0);
					        JSONObject js = JSONObject.fromObject(str);
					        welder = js.getString("NAME");
							weldowner = js.getString("ITEMNAME");
							String welderid = js.getString("ID");
							
							boolean exiet = false;
							
							for(int i=0;i<listarray4.size();i+=8){
								if(weldernum.equals(listarray4.get(i+1))){
									exiet = true;
									break;
								}
							}
							
							if(!exiet){
								
								//开启新视窗选择焊机
								new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4,client,listarraywe,listarrayta,welderid);
								
								//关闭当前视窗
								setVisible(false);
							}else{
								JOptionPane.showMessageDialog(null, "正在执行任务.", "  错误",JOptionPane.ERROR_MESSAGE);
								textField.setCaretPosition(4);
							}
							
				        }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "服务器未开启,请稍候再试.", "  错误",JOptionPane.ERROR_MESSAGE);
						textField.setCaretPosition(4);
						e1.printStackTrace();
					}
					
					//查找焊工
					/*for(int i=0;i<listarray1.size();i+=3){
						if(weldernum.equals(listarray1.get(i))){
							welder = listarray1.get(i+1);
							weldowner = listarray1.get(i+2);
							exist = true;
							l11.setText("焊工姓名:  " + listarray1.get(i+1));
							l12.setText("焊工编号:  " + listarray1.get(i));
							l15.setText("所在班组:  " + listarray1.get(i+2));
							
							//开启新视窗选择焊机
							new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4);
							
							//关闭当前视窗
							setVisible(false);
							
							break;
							//焊工确认
							int j = JOptionPane.showConfirmDialog(null, "焊工姓名:" + welder + " 焊工编号:" + weldernum, "确认",JOptionPane.YES_NO_OPTION);
							JOptionPane.showMessageDialog(null, "确认选择" + weld + "号焊机" + "执行" + task + "任务?", "  确认",JOptionPane.INFORMATION_MESSAGE);
							if(j==0){
							}else{
								t32.setCaretPosition(4);
								break;
							}
						}
					}
					
					if(!exist){
						JOptionPane.showMessageDialog(null, "焊工编号错误.", "  错误",JOptionPane.ERROR_MESSAGE);
						t32.setCaretPosition(4);
					}*/
					
				}
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				weldernum = textField.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		
		JLabel label_3 = new JLabel("班组 ：   ");
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("微软雅黑 Light", Font.BOLD, 17));
		GroupLayout groupLayout_1 = new GroupLayout(internalFrame_1.getContentPane());
		groupLayout_1.setHorizontalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addGap(117)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
								.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE))
							.addGap(54)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
								.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)))
						.addGroup(Alignment.TRAILING, groupLayout_1.createSequentialGroup()
							.addComponent(label_1)
							.addGap(36)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
							.addGap(10)))
					.addGap(93))
		);
		groupLayout_1.setVerticalGroup(
			groupLayout_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout_1.createSequentialGroup()
					.addContainerGap(35, Short.MAX_VALUE)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(31)
					.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout_1.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout_1.createSequentialGroup()
									.addGap(42)
									.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
								.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout_1.createSequentialGroup()
							.addComponent(label)
							.addGap(18)
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)))
					.addGap(48))
		);
		internalFrame_1.getContentPane().setLayout(groupLayout_1);
		
		p1.add(internalFrame);
		getContentPane().setLayout(groupLayout);
		
		repaint();
		
		/*//table列名以及值
		String[] cn = {"班组", "焊工编号", "焊工", "焊机", "任务号","当前状态","开始时间","任务描述"};  
		Object[][] obj = new Object[listarray4.size()/8][8];  
		for(int i=0;i<listarray4.size()/8;i++){
			for(int j=0;j<8;j++){
				obj[i][j] = listarray4.get(i*8+j);
			}
		}
		//绘图
		t4 = new JTable(obj,cn){
			public boolean isCellEditable(int row, int column)
            {
                   return false;//表格不允许被编辑
            }
		};
		t4.setFont(new Font("Dialog",1,15));
		
		//设置table宽高
		TableColumn column = null;  
        int colunms = t4.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
        	column = t4.getColumnModel().getColumn(i);
            if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5){
            	column.setPreferredWidth(85);
            }else if(i == 6){
            	column.setPreferredWidth(180);
            }else if(i == 7){
            	column.setPreferredWidth(1200);
            }
        }  
        
        //居中显示，关闭自动编辑
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        t4.setDefaultRenderer(Object.class, r);
        t4.setRowHeight(50);
        t4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        s4 = new JScrollPane(t4);
		s4.setBounds(0, 510, screensize.width, screensize.height-530);
		s4.setBackground(Color.white);
		add(s4);
        
		repaint(); */
		        
		
		//确认按钮
		/*b33.setBounds((screensize.width+200)/2, 75, 100, 50);
		b33.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				worktime = time;
				weldernum = t32.getText();
				boolean exist = false;
				
				//查找焊工
				for(int i=0;i<listarray1.size();i+=3){
					if(weldernum.equals(listarray1.get(i))){
						l11.setText("焊工姓名:  " + listarray1.get(i+1));
						l12.setText("焊工编号:  " + listarray1.get(i));
						welder = listarray1.get(i+1);
						exist = true;
						
						//关闭当前视窗
						setVisible(false);
						
						//开启新视窗选择焊机
						new Secondpage(worktime,welder,weldernum,screensize,listarray2,listarray3);
						break;
					}
				}
				
				//焊工编号错误
				if(!exist){
					JOptionPane.showMessageDialog(null, "焊工编号错误.", "  错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		p3.add(b33);*/
		
	}
	
	public static void main(String[] args){
		//new Secondpage("","","",weldowner,screensize,listarray21,listarray22,listarray3,listarray4,client,listarraywe,listarrayta,welderid);
		
		new Firstpage();
	}
}
