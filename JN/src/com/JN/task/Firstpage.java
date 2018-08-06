package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;

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
	private JLabel l11 = new JLabel("焊工姓名：   ");
	private JLabel l12 = new JLabel("焊工编号：   ");
	private JLabel l15 = new JLabel("所在班组：   ");
	private JLabel l13 = new JLabel("焊机编号：   ");
	private JLabel l14 = new JLabel("任务编号：   ");
	private JPanel p2 = new JPanel();
	private JLabel l21 = new JLabel("XXXX-XX-XX XX:XX:XX");
	private JPanel p3 = new JPanel();
	private JLabel l31 = new JLabel("焊工编号：");
	private JLabel l4 = new JLabel("正在与服务器通讯...   ");
	private JTextField t32 = new JTextField(8);
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
		setFont(new Font("Dialog",1,20)); //标题字体
		
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
				iutil  =  new  IsnullUtil();
				dcf = JaxWsDynamicClientFactory.newInstance();
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
	            			s4.setBounds(0, 510, screensize.width, screensize.height-530);
	            			s4.setBackground(Color.white);
	            			add(s4);
	                		
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
			context.add(s4);
	        
			if(client != null){
				l4.setText("正在执行的任务：");
			}
			
			repaint(); 
			
		}
		

		//查数据库缓存数据
        /*Class.forName("com.mysql.jdbc.Driver");  
        conn = DriverManager.getConnection(connet);
        stmt= conn.createStatement();*/

        //String sql1 = "SELECT fwelder_no,fname,Fowner FROM tb_welder";
        //String sql21 = "SELECT finsframework_id FROM tb_welding_machine GROUP BY finsframework_id";
        //String sql22 = "SELECT fequipment_no,fmanufacturer_id,finsframework_id,fposition FROM tb_welding_machine";
        //String sql3 = "SELECT fwelded_junction_no,fserial_no,fitemId,tb_welder.fname FROM tb_welded_junction INNER JOIN tb_welder ON tb_welder.fid = tb_welded_junction.fdyne";
        //String sql4 = "SELECT tb_welder.fname,tb_welding_machine.fequipment_no,tb_welded_junction.fwelded_junction_no,tb_taskresult.foperatetype,tb_welded_junction.fstart_time,tb_welded_junction.fserial_no,tb_welder.Fowner FROM tb_taskresult INNER JOIN tb_welder ON tb_taskresult.fwelderid = tb_welder.fid INNER JOIN tb_welding_machine ON tb_taskresult.fmachineid = tb_welding_machine.fid INNER JOIN tb_welded_junction ON tb_taskresult.ftaskid = tb_welded_junction.fid WHERE tb_taskresult.foperatetype = 1";
        
        //焊工
        /*ResultSet rs1 =stmt.executeQuery(sql1);
        while(rs1.next()){
        	String fwelder_no = rs1.getString("fwelder_no");
        	String fname = rs1.getString("fname");
         	String Fowner = rs1.getString("Fowner");
         	listarray1.add(fwelder_no);
         	listarray1.add(fname);
         	listarray1.add(Fowner);
        }*/
        
        //班组
        /*ResultSet rs21 =stmt.executeQuery(sql21);
        while(rs21.next()){
        	String finsframework_id = rs21.getString("finsframework_id");
        	finsframework_id = finsframework_id;
         	listarray21.add(finsframework_id);
        }*/
        
        //焊机
        /*ResultSet rs22 =stmt.executeQuery(sql22);
        while(rs22.next()){
        	String fequipment_no = rs22.getString("fequipment_no");
        	String fmanufacturer_id = rs22.getString("fmanufacturer_id");
        	String finsframework_id = rs22.getString("finsframework_id");
        	String fposition = rs22.getString("fposition");
        	listarray22.add(fequipment_no);
         	listarray22.add(fmanufacturer_id);
         	listarray22.add(finsframework_id);
         	listarray22.add(fposition);
        }*/
        
        //任务表
        /*ResultSet rs3 =stmt.executeQuery(sql3);
        while(rs3.next()){
        	String fwelded_junction_no = rs3.getString("fwelded_junction_no");
        	String fitemId = rs3.getString("fitemId");
        	String fname = rs3.getString("fname");
        	String fserial_no = rs3.getString("fserial_no");
         	listarray3.add(fwelded_junction_no);
         	listarray3.add(fitemId);
         	listarray3.add(fname);
         	listarray3.add(fserial_no);
        }*/
        
        //任务执行表
        /*ResultSet rs4 =stmt.executeQuery(sql4);
        while(rs4.next()){
        	String fname = rs4.getString("fname");
        	String fequipment_no = rs4.getString("fequipment_no");
        	String fwelded_junction_no = rs4.getString("fwelded_junction_no");
        	String foperatetype = rs4.getString("foperatetype");
        	String fstart_time = rs4.getString("fstart_time");
        	String fserial_no = rs4.getString("fserial_no");
        	String Fowner = rs4.getString("Fowner");
        	listarray4.add(Fowner);
         	listarray4.add(fname);
         	listarray4.add(fequipment_no);
         	listarray4.add(fwelded_junction_no);
         	listarray4.add(foperatetype);
         	listarray4.add(fstart_time);
         	listarray4.add(fserial_no);
        }*/
		
	};
		

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
		setLayout(null);
		setVisible(true);
		
		//信息列表
		p1.setBorder(BorderFactory.createTitledBorder("焊工信息"));
		p1.setBounds(0, 20, screensize.width, 60);
		p1.setFont(new Font("Dialog",1,17));
		p1.setBackground(Color.white);
		p1.setLayout(null);
		add(p1);
		
		//焊工姓名
		l11.setFont(new Font("Dialog",1,17));
		l11.setForeground(Color.black);
		l11.setBounds(50, 22, 200, 22);
		p1.add(l11);
		
		//焊工编号
		l12.setFont(new Font("Dialog",1,17));
		l12.setForeground(Color.black);
		l12.setBounds(300, 22, 200, 22);
		p1.add(l12);
		
		//所在班组
		l15.setFont(new Font("Dialog",1,17));
		l15.setForeground(Color.black);
		l15.setBounds(600, 22, 200, 22);
		p1.add(l15);
		
		//焊机编号
		l13.setFont(new Font("Dialog",1,17));
		l13.setForeground(Color.black);
		l13.setBounds(900, 22, 200, 22);
		p1.add(l13);
		
		//任务编号
		l14.setFont(new Font("Dialog",1,17));
		l14.setForeground(Color.black);
		l14.setBounds(1200, 22, 200, 22);
		p1.add(l14);
		
		//时钟
		p2.setBorder(BorderFactory.createTitledBorder("当前时间"));
		p2.setBounds(0, 100, screensize.width, 150);
		p2.setFont(new Font("Dialog",1,17));
		p2.setBackground(Color.white);
		p2.setLayout(null);
		add(p2);
		
		//显示时钟
		l21.setFont(new Font("Dialog",1,30));
		l21.setForeground(Color.black);
		l21.setBounds((screensize.width-340)/2, 50, 340, 50);
		l21.setText("2018-10-10 10:10:10");
		p2.add(l21);
		
		//焊工号
		p3.setBorder(BorderFactory.createTitledBorder("焊工信息录入"));
		p3.setBounds(0, 270, screensize.width, 200);
		p3.setFont(new Font("Dialog",1,17));
		p3.setBackground(Color.white);
		p3.setLayout(null);
		add(p3);
		
		//焊工编号
		l31.setFont(new Font("Dialog",1,25));
		l31.setForeground(Color.black);
		l31.setBounds((screensize.width-700)/2, 70, 200, 50);
		p3.add(l31);
		
		//焊工号输入框
		t32.setFont(new Font("Dialog",1,30));
		t32.setHorizontalAlignment(JTextField.CENTER);
		t32.setBounds((screensize.width-400)/2, 70, 400, 60);
		t32.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
				weldernum = t32.getText();
				if(weldernum.length() == 4){
					
					t32.updateUI();
					
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
							t32.setCaretPosition(4);
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
								l11.setText("焊工姓名:  " + welder);
								l12.setText("焊工编号:  " + weldernum);
								l15.setText("所在班组:  " + weldowner);
								
								//开启新视窗选择焊机
								new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4,client,listarraywe,listarrayta,welderid);
								
								//关闭当前视窗
								setVisible(false);
							}else{
								JOptionPane.showMessageDialog(null, "正在执行任务.", "  错误",JOptionPane.ERROR_MESSAGE);
								t32.setCaretPosition(4);
							}
							
				        }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "服务器未开启,请稍候再试.", "  错误",JOptionPane.ERROR_MESSAGE);
						t32.setCaretPosition(4);
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
				weldernum = t32.getText();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
			}
			
		});
		p3.add(t32);
		
		l4.setBounds(10, 480, 200, 20);
		l4.setFont(new Font("Dialog",1,13));
		add(l4);
		
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
		new Firstpage();
	}
}
