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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.srplab.www.starcore.StarCoreFactory;
import com.srplab.www.starcore.StarObjectClass;
import com.srplab.www.starcore.StarServiceClass;
import com.srplab.www.starcore.StarSrvGroupClass;

import net.sf.json.JSONObject;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import com.JN.task.*;

public class Firstpage extends JFrame{
	private String connet = "jdbc:mysql://192.168.3.231:3306/CIWJN?user=db_admin&password=PIJXmcLRa0QgOw2c&useUnicode=true&autoReconnect=true&characterEncoding=UTF8";
	public Secondpage sp = new Secondpage();
	public Clientconnect1 cc = new Clientconnect1(this,1);
	public MyTask mt = new MyTask(this);
	public String webdata;
	private java.sql.Connection conn = null;
	private java.sql.Statement stmt =null;
	private JPanel panel_1;
	private JComboBox jcb;
	private JComboBox jcbsup;
	private String[] itemArray;
	private String[] itemArraysup;
	private JScrollPane sp2;
	private final JPanel panel_4 = new JPanel();
	public Timer tExit;
	public Timer t;
	public Timer tExit1;
    
	public ArrayList<String> listarray1 = new ArrayList<String>();
    public ArrayList<String> listarray21 = new ArrayList<String>();
    public ArrayList<String> listarray22 = new ArrayList<String>();
    public ArrayList<String> listarray3 = new ArrayList<String>();
    public ArrayList<String> listarray4 = new ArrayList<String>();
    public ArrayList<String> listarraywe = new ArrayList<String>();
    public ArrayList<String> listarrayta = new ArrayList<String>();
    public ArrayList<String> websocketMachine = new ArrayList<String>();
    public ArrayList<String> firstpageMachine = new ArrayList<String>();
    public ArrayList<String> listarrayins = new ArrayList<String>();
    public ArrayList<String> listarraysupins = new ArrayList<String>();
    public HashMap<String, String> insmap = new HashMap<String, String>();
	
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	private JPanel p3 = new JPanel();
	private JLabel l21 = new JLabel("XXXX-XX-XX XX:XX:XX");
	private JLabel l4 = new JLabel("正在与服务器通讯...");
	private JScrollPane s4;
	private JTable t4 = new JTable();
	private JLabel l22;
	
	public String time;
	public String worktime;
	public String welder;
	public String weldowner;
	public String weldernum = "";
	public Firstpage context;
	public Dimension screensize;
	public ImageIcon img;
	
	public String limit;
	
	private IsnullUtil iutil;
	private JaxWsDynamicClientFactory dcf;
	private Client client;
	public boolean first = true;
	
	public StarCoreFactory starcore;
	public StarServiceClass Service;
	public StarSrvGroupClass SrvGroup;
	
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
		//new Thread(card).start();  //获取数据
		//initdate(); //获取数据
		initframe(); //绘制界面
		time(); //电子时钟
		time1(); //读卡
		
/*		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		new Thread(websocket).start();  //获取数据
		
		
		context = this;
	}
	
	/*Runnable card = new Runnable(){
		public void run(){
			ActiveXComponent card = new ActiveXComponent("C3CardRead.Card");
			Dispatch disp = card.getObject();
			textField.setText(Dispatch.call(disp, "EmpNo").getString());
			ActiveXComponent card = new ActiveXComponent("axReadCard1");
			Dispatch disp = card.getObject();
			Dispatch.call(disp, "ReadCard").getString();
			System.out.println(Dispatch.call(disp, "ReadCard").getString());
		}
	};*/
	
	Runnable websocket = new Runnable(){
		public void run(){
			cc.run();
		}
	};
	
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
				    	}else if(writetime==1){
				    		writetime++;
				    	}else if(writetime==2){
				    		limit = line;
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
	        	ArrayList<String> listarraybuf = new ArrayList<String>();
	        	
		        for(int i=0;i<ary.size();i++){
			        String str = ary.getString(i);
			        JSONObject js = JSONObject.fromObject(str);
			        
			        if(limit.equals("false")){
    		        	if(js.getString("OPERATESTATUS").equals("")){
        		        	listarray3.add(js.getString("TASKNO"));
        		        	listarray3.add(js.getString("ITEMNAME"));
        		        	listarray3.add(js.getString("WELDERNAME"));
        		        	listarray3.add(js.getString("TASKDES"));
        		        	listarrayta.add(js.getString("TASKNO"));
        		        	listarrayta.add(js.getString("ID"));
        		        }else if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
        		        	listarray4.add(js.getString("TASKNO"));
        		        	listarray4.add(js.getString("ITEMNAME"));
        	        		listarray4.add(js.getString("REWELDERNO"));
        	        		listarray4.add(js.getString("REWELDERNAME"));
        		        	listarray4.add(js.getString("MACHINENO"));
        		        	listarray4.add("焊接");
        		        	listarray4.add(js.getString("STARTTIME"));
        		        	//listarray4.add(js.getString("TASKDES"));
        		        }
    		        }else if(limit.equals("true")){
    		        	
    		        	if(js.getString("OPERATESTATUS").equals("1")){
    		        		listarraybuf.add(js.getString("ID"));
    		        	}else{
    		        		
    		        		if(listarraybuf.size()==0){
    		        			int count1 = 0;
								count1++;
		        				if(count1==listarraybuf.size() || listarraybuf.size()==0){
		        					int count=0;
		        		        	if(listarray3.size()==0){
		        		        		listarray3.add(js.getString("TASKNO"));
		    	    		        	listarray3.add(js.getString("ITEMNAME"));
		    	    		        	listarray3.add(js.getString("WELDERNAME"));
		    	    		        	listarray3.add(js.getString("TASKDES"));
		    	    		        	listarrayta.add(js.getString("TASKNO"));
		    	    		        	listarrayta.add(js.getString("ID"));
		        		        	}else{
		        		        		for(int k=0;k<listarray3.size();k+=4){
		                		        	if(js.getString("TASKNO").equals(listarray3.get(k))){
		                		        		break;
		                		        	}else{
		                		        		count++;
		                		        		if(count == listarray3.size()/4){
		                	    		        	listarray3.add(js.getString("TASKNO"));
		                	    		        	listarray3.add(js.getString("ITEMNAME"));
		                	    		        	listarray3.add(js.getString("WELDERNAME"));
		                	    		        	listarray3.add(js.getString("TASKDES"));
		                	    		        	listarrayta.add(js.getString("TASKNO"));
		                	    		        	listarrayta.add(js.getString("ID"));
		                		        		}
		                		        	}
		            		        	}
		        		        	}
		        		        	
		            		        if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
		            		        	listarray4.add(js.getString("TASKNO"));
		            		        	listarray4.add(js.getString("ITEMNAME"));
		            	        		listarray4.add(js.getString("REWELDERNO"));
		            	        		listarray4.add(js.getString("REWELDERNAME"));
		            		        	listarray4.add(js.getString("MACHINENO"));
		            		        	listarray4.add("焊接");
		            		        	listarray4.add(js.getString("STARTTIME"));
		            		        	//listarray4.add(js.getString("TASKDES"));
		            		        }
		        				}
    		        		}else{
    		        			int count1=0;
        		        		for(int l=0;l<listarraybuf.size();l++){
        		        			if(listarraybuf.get(l).equals(js.getString("ID"))){
        		        				break;
        		        			}else{
        		        				count1++;
        		        				if(count1==listarraybuf.size() || listarraybuf.size()==0){
        		        					int count=0;
        		        		        	if(listarray3.size()==0){
        		        		        		listarray3.add(js.getString("TASKNO"));
        		    	    		        	listarray3.add(js.getString("ITEMNAME"));
        		    	    		        	listarray3.add(js.getString("WELDERNAME"));
        		    	    		        	listarray3.add(js.getString("TASKDES"));
        		    	    		        	listarrayta.add(js.getString("TASKNO"));
        		    	    		        	listarrayta.add(js.getString("ID"));
        		        		        	}else{
        		        		        		for(int k=0;k<listarray3.size();k+=4){
        		                		        	if(js.getString("TASKNO").equals(listarray3.get(k))){
        		                		        		break;
        		                		        	}else{
        		                		        		count++;
        		                		        		if(count == listarray3.size()/4){
        		                	    		        	listarray3.add(js.getString("TASKNO"));
        		                	    		        	listarray3.add(js.getString("ITEMNAME"));
        		                	    		        	listarray3.add(js.getString("WELDERNAME"));
        		                	    		        	listarray3.add(js.getString("TASKDES"));
        		                	    		        	listarrayta.add(js.getString("TASKNO"));
        		                	    		        	listarrayta.add(js.getString("ID"));
        		                		        		}
        		                		        	}
        		            		        	}
        		        		        	}
        		        		        	
        		            		        if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
        		            		        	listarray4.add(js.getString("TASKNO"));
        		            		        	listarray4.add(js.getString("ITEMNAME"));
        		            	        		listarray4.add(js.getString("REWELDERNO"));
        		            	        		listarray4.add(js.getString("REWELDERNAME"));
        		            		        	listarray4.add(js.getString("MACHINENO"));
        		            		        	listarray4.add("焊接");
        		            		        	listarray4.add(js.getString("STARTTIME"));
        		            		        	//listarray4.add(js.getString("TASKDES"));
        		            		        }
        		        				}
        		        			}
        		        		}
    		        		}
    		        	}
    		        	
    		        	/*int count=0;
    		        	if(listarray3.size()==0){
    		        		listarray3.add(js.getString("TASKNO"));
	    		        	listarray3.add(js.getString("ITEMNAME"));
	    		        	listarray3.add(js.getString("WELDERNAME"));
	    		        	listarray3.add(js.getString("TASKDES"));
	    		        	listarrayta.add(js.getString("TASKNO"));
	    		        	listarrayta.add(js.getString("ID"));
    		        	}else{
    		        		for(int k=0;k<listarray3.size();k+=4){
            		        	if(js.getString("TASKNO").equals(listarray3.get(k))){
            		        		break;
            		        	}else{
            		        		count++;
            		        		if(count == listarray3.size()/4){
            	    		        	listarray3.add(js.getString("TASKNO"));
            	    		        	listarray3.add(js.getString("ITEMNAME"));
            	    		        	listarray3.add(js.getString("WELDERNAME"));
            	    		        	listarray3.add(js.getString("TASKDES"));
            	    		        	listarrayta.add(js.getString("TASKNO"));
            	    		        	listarrayta.add(js.getString("ID"));
            		        		}
            		        	}
        		        	}
    		        	}
    		        	
        		        if(js.getString("OPERATESTATUS").equals("0") || js.getString("OPERATESTATUS").equals("2")){
        		        	listarray4.add(js.getString("TASKNO"));
        		        	listarray4.add(js.getString("ITEMNAME"));
        	        		listarray4.add(js.getString("REWELDERNO"));
        	        		listarray4.add(js.getString("REWELDERNAME"));
        		        	listarray4.add(js.getString("MACHINENO"));
        		        	listarray4.add("焊接");
        		        	listarray4.add(js.getString("STARTTIME"));
        		        	//listarray4.add(js.getString("TASKDES"));
        		        }*/
    		        }
			        
			        String a = js.getString("MACHINENO");
		        }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "服务器未开启.", "  错误",JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	        
			tExit = null; 
			tExit = new Timer();  
	        tExit.schedule(new TimerTask() {  
	            @Override  
	            public void run() {
	            	try {
	            		if(!first && (client != null)){
	            			listarray22.clear();
	            			String obj2 = "{\"CLASSNAME\":\"weldingMachineWebServiceImpl\",\"METHOD\":\"getWeldingMachineAll\"}";
	            			Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
	            					new Object[] { obj2 });
	            		    String restr = objects[0].toString();
	            	        JSONArray ary = JSONArray.parseArray(restr);
	            	        for(int i=0;i<ary.size();i++){
	            		        String str = ary.getString(i);
	            		        JSONObject js = JSONObject.fromObject(str);
	            		        listarray22.add(js.getString("MACHINENO"));
	            		        listarray22.add(js.getString("TYPE"));
	            		        listarray22.add(js.getString("INSFRAMEWORKNAME"));
	            		        listarray22.add(js.getString("POSITION"));
	            		        listarray22.add(js.getString("STATUS"));
	            		        listarraywe.add(js.getString("MACHINENO"));
	            		        listarraywe.add(js.getString("ID"));
	            		        websocketMachine.add(js.getString("ID"));
	            		        websocketMachine.add(js.getString("MACHINENO"));
	            		        websocketMachine.add(js.getString("STATUS"));
	            	        }
	            	        
	            	        //组织机构二级联动
	            	        for(int i=0;i<ary.size();i++){
	            		        String str = ary.getString(i);
	            		        JSONObject js = JSONObject.fromObject(str);
            		        	listarrayins.add(js.getString("SUPINS"));
            		        	listarrayins.add(js.getString("INSFRAMEWORKNAME"));
	            	        }
	            	        for(int i=0;i<listarrayins.size();i+=2){
	            	        	if(listarraysupins.size()==0){
	            	        		listarraysupins.add(listarrayins.get(i));
	            	        	}else{
	            	        		if(listarraysupins.indexOf(listarrayins.get(i))==-1){
	            	        			listarraysupins.add(listarrayins.get(i));
	            	        		}
	            	        	}
	            	        }
	            	        for(int i=0;i<listarraysupins.size();i++){
	            	        	String ins = "";
	            	            ArrayList<String> listarraybuf = new ArrayList<String>();
	            	        	for(int i1=0;i1<listarrayins.size();i1+=2){
	            	        		if(listarraysupins.get(i).equals(listarrayins.get(i1)) && listarraybuf.indexOf(listarrayins.get(i1+1))==-1){
	            	        			ins = ins + listarrayins.get(i1+1)+",";
	            	        			listarraybuf.add(listarrayins.get(i1+1));
	            	        		}
	            	        	}
	            	        	insmap.put(listarraysupins.get(i), ins.substring(0,ins.length()-1));
	            	        }
	            	        
	            	        //筛选去除正在工作的焊机、故障焊机
	            	        if(limit.equals("false")){
	            	        	for(int i=0;i<listarray4.size();i+=7){
	            		        	for(int j=0;j<listarray22.size();j+=5){
	            		        		if(listarray4.get(i+4).equals(listarray22.get(j))){	
	            		        			listarray22.set(j, "");
	            		        			listarray22.set(j+1, "");
	            		        			listarray22.set(j+2, "");
	            		        			listarray22.set(j+3, "");
	            		        			listarray22.set(j+4, "");
	            		        		}
	            		        	}
	            		        }
	            	        }
	            	        
	            	        for(int j=0;j<listarray22.size();j+=5){
	                    		if(!listarray22.get(j+4).equals("31")){
	                    			listarray22.set(j, "");
	                    			listarray22.set(j+1, "");
	                    			listarray22.set(j+2, "");
	                    			listarray22.set(j+3, "");
	                    			listarray22.set(j+4, "");
	                    		}
	                    	}
	            	        for(int j=0;j<websocketMachine.size();j+=3){
	                    		if(!websocketMachine.get(j+2).equals("31")){
	                    			websocketMachine.set(j, "");
	                    			websocketMachine.set(j+1, "");
	                    			websocketMachine.set(j+2, "");
	                    		}
	                    	}
	            			Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
	            			p3 = new JPanel();
//	            			machineList();
	        				//响应按钮重绘界面
	        				context.remove(p3);
	        				context.remove(sp2);
	        				if(s4 != null){
		        				context.remove(s4);
	        				}
	        				
	        				//加载焊机列表
							p3.removeAll();// = new JPanel();
							p3.setBounds(20,400,400,400);
							p3.setFont(new Font("Dialog",1,20));
							p3.setBackground(Color.white);
							p3.setLayout(new FlowLayout(FlowLayout.LEFT));
							screensize.setSize(screensize.width, screensize.height);
							
			        		s4 = new JScrollPane(p3);
//				        		s4.setBounds(10, (int)p2.getLocation().getY()+p2.getHeight()+3, screensize.width-20, screensize.height-((int)p2.getLocation().getY()+p2.getHeight())-10);
			        		s4.setBounds(10, 510,screensize.width-20, screensize.height-530);
			        		s4.getViewport().setBackground(Color.white);
			        		context.add(s4);
							
							String a = jcb.getSelectedItem().toString();
							
							for(int i=0;i<listarray22.size();i+=5){
								String labelname = listarray22.get(i);
								String weldtype = listarray22.get(i+1);
								String weldposition = listarray22.get(i+3);
								
								//不为被清除的焊机列表
								if(!labelname.equals("")){
									JLabel l21 = new JLabel();
									l21.setVerticalTextPosition(JLabel.BOTTOM); 
									l21.setHorizontalTextPosition(JLabel.CENTER);
									JLabel l22 = new JLabel();
									l21.setBounds(50, 100, 200, 200);
									l22.setBounds(70, 400, 200, 200);
									if(a.equals("全部班组")){

										if(limit.equals("true")){
			/*								if(listarray4.size()!=0){
												for(int i1=0;i1<listarray4.size();i1+=7){
													if(labelname.equals(listarray4.get(i1+4))){
														if(listarray22.get(i+1).equals("41")){
															img = new ImageIcon(getClass().getResource("/images/GLW.png"));
															l21.setIcon(img);
															break;
														}else if(listarray22.get(i+1).equals("42")){
															img = new ImageIcon(getClass().getResource("/images/ATW.png"));
															l21.setIcon(img);
															break;
														}else if(listarray22.get(i+1).equals("43")){
															img = new ImageIcon(getClass().getResource("/images/FRW.png"));
															l21.setIcon(img);
															break;
														}
													}else{
														if(listarray22.get(i+1).equals("41")){
															img = new ImageIcon(getClass().getResource("/images/GL.png"));
															l21.setIcon(img);
														}else if(listarray22.get(i+1).equals("42")){
															img = new ImageIcon(getClass().getResource("/images/AT.png"));
															l21.setIcon(img);
														}else if(listarray22.get(i+1).equals("43")){
															img = new ImageIcon(getClass().getResource("/images/FR.png"));
															l21.setIcon(img);
														}
													}
												}
											}else{
												if(listarray22.get(i+1).equals("41")){
													img = new ImageIcon(getClass().getResource("/images/GL.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("42")){
													img = new ImageIcon(getClass().getResource("/images/AT.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("43")){
													img = new ImageIcon(getClass().getResource("/images/FR.png"));
													l21.setIcon(img);
												}
											}*/
											if(firstpageMachine.size()!=0){
												for(int f=0;f<firstpageMachine.size();f+=2){
													if(labelname.equals(firstpageMachine.get(f))){
														if(listarray22.get(i+1).equals("41")){
			    											if(firstpageMachine.get(f+1).equals("00")){
			    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}else if(listarray22.get(i+1).equals("42")){
															if(firstpageMachine.get(f+1).equals("00")){
																img = new ImageIcon(getClass().getResource("/images/ATS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}else if(listarray22.get(i+1).equals("43")){
															if(firstpageMachine.get(f+1).equals("00")){
																img = new ImageIcon(getClass().getResource("/images/FRS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}
													}else{
			        									if(listarray22.get(i+1).equals("41")){
			        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
			        										l21.setIcon(img);
			        									}else if(listarray22.get(i+1).equals("42")){
			        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
			        										l21.setIcon(img);
			        									}else if(listarray22.get(i+1).equals("43")){
			        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
			        										l21.setIcon(img);
			        									}
													}
												}
											}else{
												if(listarray22.get(i+1).equals("41")){
													img = new ImageIcon(getClass().getResource("/images/GL.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("42")){
													img = new ImageIcon(getClass().getResource("/images/AT.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("43")){
													img = new ImageIcon(getClass().getResource("/images/FR.png"));
													l21.setIcon(img);
												}
											}
										}else if(limit.equals("false")){
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
										l21.addMouseListener(new MouseListener(){
				
											//图片点击监听
											@Override
											public void mouseClicked(MouseEvent e) {}
											@Override
											public void mousePressed(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseReleased(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseEntered(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseExited(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
										});
										
										if(limit.equals("true")){
											for(int i1=0;i1<listarray4.size();i1+=7){
												if(labelname.equals(listarray4.get(i1+4))){
												}
											}
										}
										
										l21.setText(labelname);
										//l22.setText("       ");
										
										p3.add(l21);
										p3.add(l22);
									
									}else{
										if(listarray22.get(i+2).equals(a)){
										if(limit.equals("true")){
			/*								if(listarray4.size()!=0){
												for(int i1=0;i1<listarray4.size();i1+=7){
													if(labelname.equals(listarray4.get(i1+4))){
														if(listarray22.get(i+1).equals("41")){
															img = new ImageIcon(getClass().getResource("/images/GLW.png"));
															l21.setIcon(img);
															break;
														}else if(listarray22.get(i+1).equals("42")){
															img = new ImageIcon(getClass().getResource("/images/ATW.png"));
															l21.setIcon(img);
															break;
														}else if(listarray22.get(i+1).equals("43")){
															img = new ImageIcon(getClass().getResource("/images/FRW.png"));
															l21.setIcon(img);
															break;
														}
													}else{
														if(listarray22.get(i+1).equals("41")){
															img = new ImageIcon(getClass().getResource("/images/GL.png"));
															l21.setIcon(img);
														}else if(listarray22.get(i+1).equals("42")){
															img = new ImageIcon(getClass().getResource("/images/AT.png"));
															l21.setIcon(img);
														}else if(listarray22.get(i+1).equals("43")){
															img = new ImageIcon(getClass().getResource("/images/FR.png"));
															l21.setIcon(img);
														}
													}
												}
											}else{
												if(listarray22.get(i+1).equals("41")){
													img = new ImageIcon(getClass().getResource("/images/GL.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("42")){
													img = new ImageIcon(getClass().getResource("/images/AT.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("43")){
													img = new ImageIcon(getClass().getResource("/images/FR.png"));
													l21.setIcon(img);
												}
											}*/
											if(firstpageMachine.size()!=0){
												for(int f=0;f<firstpageMachine.size();f+=2){
													if(labelname.equals(firstpageMachine.get(f))){
														if(listarray22.get(i+1).equals("41")){
			    											if(firstpageMachine.get(f+1).equals("00")){
			    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}else if(listarray22.get(i+1).equals("42")){
															if(firstpageMachine.get(f+1).equals("00")){
																img = new ImageIcon(getClass().getResource("/images/ATS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}else if(listarray22.get(i+1).equals("43")){
															if(firstpageMachine.get(f+1).equals("00")){
																img = new ImageIcon(getClass().getResource("/images/FRS.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
			    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
			    												l21.setIcon(img);
			    												break;
			    											}else{
			    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
			    												l21.setIcon(img);
			    												break;
			    											}
														}
													}else{
			        									if(listarray22.get(i+1).equals("41")){
			        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
			        										l21.setIcon(img);
			        									}else if(listarray22.get(i+1).equals("42")){
			        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
			        										l21.setIcon(img);
			        									}else if(listarray22.get(i+1).equals("43")){
			        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
			        										l21.setIcon(img);
			        									}
													}
												}
											}else{
												if(listarray22.get(i+1).equals("41")){
													img = new ImageIcon(getClass().getResource("/images/GL.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("42")){
													img = new ImageIcon(getClass().getResource("/images/AT.png"));
													l21.setIcon(img);
												}else if(listarray22.get(i+1).equals("43")){
													img = new ImageIcon(getClass().getResource("/images/FR.png"));
													l21.setIcon(img);
												}
											}
										}else if(limit.equals("false")){
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
										l21.addMouseListener(new MouseListener(){
				
											//图片点击监听
											@Override
											public void mouseClicked(MouseEvent e) {}
											@Override
											public void mousePressed(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseReleased(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseEntered(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
											@Override
											public void mouseExited(MouseEvent e) {
												// TODO Auto-generated method stub
												
											}
										});
										
										if(limit.equals("true")){
											for(int i1=0;i1<listarray4.size();i1+=7){
												if(labelname.equals(listarray4.get(i1+4))){
												}
											}
										}
										
										l21.setText(labelname);
										//l22.setText("       ");
										
										p3.add(l21);
										p3.add(l22);
									}
								}
								}
							}
							
							p3.repaint();
	            		}
	            		
	            		first = false;
	        	        
	        		} catch (Exception e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	            }
	        }, 5000, 30000);
	        machineList();
		}
	};
	
	private final JLabel label_2 = new JLabel("");
	private final JInternalFrame internalFrame_1 = new JInternalFrame("New JInternalFrame");
	private final JLabel label = new JLabel("焊工 ：   ");
	private final JLabel label_1 = new JLabel("焊工编号：");
	public final JTextField textField = new JTextField(8);
	private final JLabel label_4 = new JLabel("编号：   ");
	private final JLabel label_5 = new JLabel("焊机 ：   ");
	private final JLabel label_6 = new JLabel("类型：   ");
	private final JLabel label_7 = new JLabel("任务：   ");

	private void time() {
		// TODO Auto-generated method stub
		tExit1 = null; 
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

	private void time1() {
		// TODO Auto-generated method stub
		t=new Timer();
        //在3秒后执行MyTask类中的run方法
		//t.scheduleAtFixedRate(mt(context), 3000, 10000);
		t.schedule(new TimerTask(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mt.run();
			}
		}, 0,5000);
        //t.schedule(new MyTask(), 3000);
       
       System.out.print("Game over");
	}
	
	private void initframe() {
		// TODO Auto-generated method stub
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screensize.width,screensize.height);
		//setSize(screensize.width,screensize.height-10);
		setResizable(false);
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
		l21.setBounds((int)30, 4, 261, 23);
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
		textField.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
/*				ActiveXComponent card = new ActiveXComponent("C3CardRead.Card");
				Dispatch disp = card.getObject();
				card.setProperty("ComSet", new Variant("1"));
//				Dispatch.put(disp,"ComSet",new Variant("1"));
				String Cardid = Dispatch.call(disp, "ReadCard").getString();
//				String welderNumber = Dispatch.call(disp, "EmpNo").getString();
				String welderNumber = Dispatch.get(disp, "EmpNo").toString();
				if(welderNumber==null||("").equals(welderNumber)){
					JOptionPane.showMessageDialog(null, "打开串口错误，未能识别读卡器!.", "  错误",JOptionPane.ERROR_MESSAGE);
				}else{
					textField.setText(welderNumber);
				}*/
//				StarCoreFactory starcore= StarCoreFactory.GetFactory();  
//		        StarServiceClass Service=starcore._InitSimple("test","123",0,0);  
//		        StarSrvGroupClass SrvGroup = (StarSrvGroupClass)Service._Get("_ServiceGroup");
//				SrvGroup._InitRaw("csharp4",Service);
//				//--load csharp module ---*/
//				SrvGroup._LoadRawModule("csharp4","testcs","Test.dll",false);
//				StarObjectClass Class1 = Service._ImportRawContext("csharp4","testcs.Class1",true,"");
//				StarObjectClass inst = Class1._New("","","cle value",44);
//				String empno1=(String)inst._Call("testfun"); 
//				System.out.println(empno1);
//				SrvGroup._ClearService();
//				starcore._ModuleExit();
//				textField.setText(empno1);
				
/*				SrvGroup._InitRaw("csharp4",Service);
				//--load csharp module ---
				SrvGroup._LoadRawModule("csharp4","testcs","Test.dll",false);
				StarObjectClass Class1 = Service._ImportRawContext("csharp4","testcs.Class1",true,"");
				StarObjectClass inst = Class1._New("","","cle value",44);
				String empno1=(String)inst._Call("testfun"); 
				System.out.println(empno1);
				//SrvGroup._ClearService();
				//starcore._ModuleExit();
				if(empno1!=null && !empno1.equals("NULL")){
					textField.setText(empno1);
				}*/
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		textField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				try{
				weldernum = textField.getText();
				if(weldernum.length() == 8){
					
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
				        	textField.setCaretPosition(8);
				        }else{
					        String str = ary.getString(0);
					        JSONObject js = JSONObject.fromObject(str);
					        welder = js.getString("NAME");
							weldowner = js.getString("ITEMNAME");
							String welderid = js.getString("ID");
							
							boolean exiet = false;
							
							if(limit.equals("false")){
								for(int i=0;i<listarray4.size();i+=7){
									if(weldernum.equals(listarray4.get(i+2))){
										exiet = true;
										break;
									}
								}
							}
							
							if(!exiet){
								//开启新视窗选择焊机
								new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4,client,listarraywe,listarrayta,welderid,firstpageMachine,context,itemArray,itemArraysup,insmap);
								
								//关闭当前视窗
								setVisible(false);
							}else{
								JOptionPane.showMessageDialog(null, "正在执行任务.", "  错误",JOptionPane.ERROR_MESSAGE);
								textField.setCaretPosition(8);
							}
							
				        }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "服务器未开启,请稍候再试.", "  错误",JOptionPane.ERROR_MESSAGE);
						textField.setCaretPosition(8);
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
				} catch (Exception e1) {
					// TODO Auto-generated catch block
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
	
	public void machineList(){
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
		        listarray22.add(js.getString("TYPE"));
		        listarray22.add(js.getString("INSFRAMEWORKNAME"));
		        listarray22.add(js.getString("POSITION"));
		        listarray22.add(js.getString("STATUS"));
		        listarraywe.add(js.getString("MACHINENO"));
		        listarraywe.add(js.getString("ID"));
		        websocketMachine.add(js.getString("ID"));
		        websocketMachine.add(js.getString("MACHINENO"));
		        websocketMachine.add(js.getString("STATUS"));
	        }
	        //筛选去除正在工作的焊机、故障焊机
	        if(limit.equals("false")){
	        	for(int i=0;i<listarray4.size();i+=7){
		        	for(int j=0;j<listarray22.size();j+=5){
		        		if(listarray4.get(i+4).equals(listarray22.get(j))){	
		        			listarray22.set(j, "");
		        			listarray22.set(j+1, "");
		        			listarray22.set(j+2, "");
		        			listarray22.set(j+3, "");
		        			listarray22.set(j+4, "");
		        		}
		        	}
		        }
	        }
	        
	        for(int j=0;j<listarray22.size();j+=5){
        		if(!listarray22.get(j+4).equals("31")){
        			listarray22.set(j, "");
        			listarray22.set(j+1, "");
        			listarray22.set(j+2, "");
        			listarray22.set(j+3, "");
        			listarray22.set(j+4, "");
        		}
        	}
	        
	        for(int j=0;j<websocketMachine.size();j+=3){
        		if(!websocketMachine.get(j+2).equals("31")){
        			websocketMachine.set(j, "");
        			websocketMachine.set(j+1, "");
        			websocketMachine.set(j+2, "");
        		}
        	}
	        
	      //组织机构二级联动
	        for(int i=0;i<ary.size();i++){
		        String str = ary.getString(i);
		        JSONObject js = JSONObject.fromObject(str);
	        	listarrayins.add(js.getString("SUPINS"));
	        	listarrayins.add(js.getString("INSFRAMEWORKNAME"));
	        }
	        for(int i=0;i<listarrayins.size();i+=2){
	        	if(listarraysupins.size()==0){
	        		listarraysupins.add(listarrayins.get(i));
	        	}else{
	        		if(listarraysupins.indexOf(listarrayins.get(i))==-1){
	        			listarraysupins.add(listarrayins.get(i));
	        		}
	        	}
	        }
	        for(int i=0;i<listarraysupins.size();i++){
	        	String ins = "";
	            ArrayList<String> listarraybuf = new ArrayList<String>();
	        	for(int i1=0;i1<listarrayins.size();i1+=2){
	        		if(listarraysupins.get(i).equals(listarrayins.get(i1)) && listarraybuf.indexOf(listarrayins.get(i1+1))==-1){
	        			ins = ins + listarrayins.get(i1+1)+",";
	        			listarraybuf.add(listarrayins.get(i1+1));
	        		}
	        	}
	        	insmap.put(listarraysupins.get(i), ins.substring(0,ins.length()-1));
	        }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		itemArraysup = new String[listarraysupins.size()+1];
		for(int i=0;i<itemArraysup.length;i++){
			if(i==0){
				itemArraysup[i]="全部作业区";
			}else{
				itemArraysup[i]=listarraysupins.get(i-1);
			}
		}
		
		itemArray = new String[1];
		itemArray[0] = "全部班组";
		
		
		//二层组织机构选择
		
		
		//单组织机构选择
		/*//班组统计
		int count = 0;
		for(int i=0;i<listarray22.size();i+=5){
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
		//班组下拉选择
		String[] arrString = (String[])listarray21.toArray(new String[0]) ;
		for(int i=0;i<arrString.length;i++){
			if(arrString[i].equals(weldowner) && i!=0){
				String a = arrString[0];
				arrString[0] = weldowner;
				arrString[i] = a;
				break;
			}
		}
		String[] itemArray = new String[arrString.length+1];
		for(int i=0;i<arrString.length;i++){
			if(i==0){
				itemArray[i] = "  * 班组:" + "全部";
				itemArray[i+1] = "    班组:" + arrString[i];
			}else{
				itemArray[i+1] = "    班组:" + arrString[i];
			}
			
		}*/
		
		
		
/*			//table列名以及值
		String[] cn = {"任务编号", "班组", "焊工编号", "焊工姓名", "焊机编号", "当前状态", "开始时间"};  
		Object[][] obj = new Object[listarray4.size()/7][7];  
		for(int i=0;i<listarray4.size()/7;i++){
			for(int j=0;j<7;j++){
				obj[i][j] = listarray4.get(i*7+j);
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
            if(i == 0 || i == 1 || i == 2 || i == 3 || i == 4 || i == 5 || i == 6){
            	column.setPreferredWidth(screensize.width/7-3);
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
		
		repaint(); */
		
        Dimension screensize11 = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel_1 = new JPanel();
		panel_1.setBounds((int)screensize11.getWidth()-310, 0, 310, 50);
		panel_1.setBackground(new Color(20,51,105));
		panel_1.setVisible(true);
		JLabel lblNewLabel = new JLabel("焊机列表");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		
		//jcb二级联动
		jcbsup = new JComboBox(itemArraysup);
		jcbsup.setBackground(Color.white);
		jcbsup.setFont(new Font("Dialog",1,15));
		jcbsup.setBounds((int)screensize11.getWidth()-450, 0, 200, 32);
		
		jcb = new JComboBox(itemArray);
		jcb.setBackground(Color.white);
		jcb.setFont(new Font("Dialog",1,15));
		jcb.setBounds((int)screensize11.getWidth()-230, 0, 200, 32);
		
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(13)
//					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 1177, Short.MAX_VALUE)
					.addComponent(jcb, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(jcb, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
//						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						))
		);
		panel_1.setLayout(gl_panel_1);
		p2.add(jcbsup);
		p2.add(jcb);
		p2.repaint();
		
		p2.remove(l4);
		int width2 = ((int)screensize11.getWidth())/2-50;
		l4 = new JLabel("设备运行");
		l4.setForeground(Color.WHITE);
		l4.setBounds(width2, 0, 216, 29);
		p2.add(l4);
		
		l4.setFont(new Font("微软雅黑 Light", Font.BOLD, 23));
		
		//二级响应
		jcbsup.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String supins = (String) jcbsup.getSelectedItem();
				if(supins.equals("全部作业区")){
					p2.remove(jcb);
					p2.repaint();
					jcb = new JComboBox();
					jcb.setBackground(Color.white);
					jcb.setFont(new Font("Dialog",1,17));
					jcb.setBounds((int)screensize11.getWidth()-230, 0, 200, 32);
					jcb.addItem("全部班组");
					p2.add(jcb);
				}else{
					String ins = insmap.get(supins);
					p2.remove(jcb);
					p2.repaint();
					jcb = new JComboBox();
					jcb.setBackground(Color.white);
					jcb.setFont(new Font("Dialog",1,17));
					jcb.setBounds((int)screensize11.getWidth()-230, 0, 200, 32);
					if(ins!=null){
						String[] insbuf = ins.split(",");
						for(int i=0;i<insbuf.length;i++){
							jcb.addItem(insbuf[i]);
						}
					}
					p2.add(jcb);
				}
				jcb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						//响应按钮重绘界面
						context.remove(p3);
						context.remove(sp2);
						context.repaint();
						
						//加载焊机列表
						p3.removeAll();// = new JPanel();
						p3.setBounds(20,400,400,400);
						p3.setFont(new Font("Dialog",1,20));
						p3.setBackground(Color.white);
						p3.setLayout(new FlowLayout(FlowLayout.LEFT));
						screensize.setSize(screensize.width, screensize.height);
						
		        		s4 = new JScrollPane(p3);
//			        		s4.setBounds(10, (int)p2.getLocation().getY()+p2.getHeight()+3, screensize.width-20, screensize.height-((int)p2.getLocation().getY()+p2.getHeight())-10);
		        		s4.setBounds(10, 510,screensize.width-20, screensize.height-530);
		        		s4.getViewport().setBackground(Color.white);
		    			getContentPane().add(s4);
						
						String a = jcb.getSelectedItem().toString();
						
						for(int i=0;i<listarray22.size();i+=5){
							String labelname = listarray22.get(i);
							String weldtype = listarray22.get(i+1);
							String weldposition = listarray22.get(i+3);
							
							//不为被清除的焊机列表
							if(!labelname.equals("")){
								JLabel l21 = new JLabel();
								l21.setVerticalTextPosition(JLabel.BOTTOM); 
								l21.setHorizontalTextPosition(JLabel.CENTER);
								JLabel l22 = new JLabel();
								l21.setBounds(50, 100, 200, 200);
								l22.setBounds(70, 400, 200, 200);
								if(a.equals("全部班组")){

									if(limit.equals("true")){
		/*								if(listarray4.size()!=0){
											for(int i1=0;i1<listarray4.size();i1+=7){
												if(labelname.equals(listarray4.get(i1+4))){
													if(listarray22.get(i+1).equals("41")){
														img = new ImageIcon(getClass().getResource("/images/GLW.png"));
														l21.setIcon(img);
														break;
													}else if(listarray22.get(i+1).equals("42")){
														img = new ImageIcon(getClass().getResource("/images/ATW.png"));
														l21.setIcon(img);
														break;
													}else if(listarray22.get(i+1).equals("43")){
														img = new ImageIcon(getClass().getResource("/images/FRW.png"));
														l21.setIcon(img);
														break;
													}
												}else{
													if(listarray22.get(i+1).equals("41")){
														img = new ImageIcon(getClass().getResource("/images/GL.png"));
														l21.setIcon(img);
													}else if(listarray22.get(i+1).equals("42")){
														img = new ImageIcon(getClass().getResource("/images/AT.png"));
														l21.setIcon(img);
													}else if(listarray22.get(i+1).equals("43")){
														img = new ImageIcon(getClass().getResource("/images/FR.png"));
														l21.setIcon(img);
													}
												}
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}*/
										if(firstpageMachine.size()!=0){
											for(int f=0;f<firstpageMachine.size();f+=2){
												if(labelname.equals(firstpageMachine.get(f))){
													if(listarray22.get(i+1).equals("41")){
		    											if(firstpageMachine.get(f+1).equals("00")){
		    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}else if(listarray22.get(i+1).equals("42")){
														if(firstpageMachine.get(f+1).equals("00")){
															img = new ImageIcon(getClass().getResource("/images/ATS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}else if(listarray22.get(i+1).equals("43")){
														if(firstpageMachine.get(f+1).equals("00")){
															img = new ImageIcon(getClass().getResource("/images/FRS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}
												}else{
		        									if(listarray22.get(i+1).equals("41")){
		        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
		        										l21.setIcon(img);
		        									}else if(listarray22.get(i+1).equals("42")){
		        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
		        										l21.setIcon(img);
		        									}else if(listarray22.get(i+1).equals("43")){
		        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
		        										l21.setIcon(img);
		        									}
												}
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
									}else if(limit.equals("false")){
										if(listarray22.get(i+1).equals("41")){
											img = new ImageIcon(getClass().getResource("/images/GL.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("42")){
											img = new ImageIcon(getClass().getResource("/images/AT.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("43")){
											img = new ImageIcon(getClass().getResource("/images/FR.png"));
											l21.setIcon(img);
										}
									}
									context.repaint();
									l21.addMouseListener(new MouseListener(){
			
										//图片点击监听
										@Override
										public void mouseClicked(MouseEvent e) {}
										@Override
										public void mousePressed(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseReleased(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseEntered(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseExited(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
									});
									
									if(limit.equals("true")){
										for(int i1=0;i1<listarray4.size();i1+=7){
											if(labelname.equals(listarray4.get(i1+4))){
											}
										}
									}
									
									l21.setText(labelname);
									//l22.setText("       ");
									
									p3.add(l21);
									p3.add(l22);
								
								}else{
									if(listarray22.get(i+2).equals(a)){
									if(limit.equals("true")){
		/*								if(listarray4.size()!=0){
											for(int i1=0;i1<listarray4.size();i1+=7){
												if(labelname.equals(listarray4.get(i1+4))){
													if(listarray22.get(i+1).equals("41")){
														img = new ImageIcon(getClass().getResource("/images/GLW.png"));
														l21.setIcon(img);
														break;
													}else if(listarray22.get(i+1).equals("42")){
														img = new ImageIcon(getClass().getResource("/images/ATW.png"));
														l21.setIcon(img);
														break;
													}else if(listarray22.get(i+1).equals("43")){
														img = new ImageIcon(getClass().getResource("/images/FRW.png"));
														l21.setIcon(img);
														break;
													}
												}else{
													if(listarray22.get(i+1).equals("41")){
														img = new ImageIcon(getClass().getResource("/images/GL.png"));
														l21.setIcon(img);
													}else if(listarray22.get(i+1).equals("42")){
														img = new ImageIcon(getClass().getResource("/images/AT.png"));
														l21.setIcon(img);
													}else if(listarray22.get(i+1).equals("43")){
														img = new ImageIcon(getClass().getResource("/images/FR.png"));
														l21.setIcon(img);
													}
												}
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}*/
										if(firstpageMachine.size()!=0){
											for(int f=0;f<firstpageMachine.size();f+=2){
												if(labelname.equals(firstpageMachine.get(f))){
													if(listarray22.get(i+1).equals("41")){
		    											if(firstpageMachine.get(f+1).equals("00")){
		    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}else if(listarray22.get(i+1).equals("42")){
														if(firstpageMachine.get(f+1).equals("00")){
															img = new ImageIcon(getClass().getResource("/images/ATS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}else if(listarray22.get(i+1).equals("43")){
														if(firstpageMachine.get(f+1).equals("00")){
															img = new ImageIcon(getClass().getResource("/images/FRS.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
		    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
		    												l21.setIcon(img);
		    												break;
		    											}else{
		    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
		    												l21.setIcon(img);
		    												break;
		    											}
													}
												}else{
		        									if(listarray22.get(i+1).equals("41")){
		        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
		        										l21.setIcon(img);
		        									}else if(listarray22.get(i+1).equals("42")){
		        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
		        										l21.setIcon(img);
		        									}else if(listarray22.get(i+1).equals("43")){
		        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
		        										l21.setIcon(img);
		        									}
												}
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
									}else if(limit.equals("false")){
										if(listarray22.get(i+1).equals("41")){
											img = new ImageIcon(getClass().getResource("/images/GL.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("42")){
											img = new ImageIcon(getClass().getResource("/images/AT.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("43")){
											img = new ImageIcon(getClass().getResource("/images/FR.png"));
											l21.setIcon(img);
										}
									}
									context.repaint();
									l21.addMouseListener(new MouseListener(){
			
										//图片点击监听
										@Override
										public void mouseClicked(MouseEvent e) {}
										@Override
										public void mousePressed(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseReleased(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseEntered(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
										@Override
										public void mouseExited(MouseEvent e) {
											// TODO Auto-generated method stub
											
										}
									});
									
									if(limit.equals("true")){
										for(int i1=0;i1<listarray4.size();i1+=7){
											if(labelname.equals(listarray4.get(i1+4))){
											}
										}
									}
									
									l21.setText(labelname);
									//l22.setText("       ");
									
									p3.add(l21);
									p3.add(l22);
								}
							}
							}
						}
						
						context.repaint();
					}
				});
				
				//响应按钮重绘界面
				context.remove(p3);
				context.remove(sp2);
				context.repaint();
				
				//加载焊机列表
				p3.removeAll();// = new JPanel();
				p3.setBounds(20,400,400,400);
				p3.setFont(new Font("Dialog",1,20));
				p3.setBackground(Color.white);
				p3.setLayout(new FlowLayout(FlowLayout.LEFT));
				screensize.setSize(screensize.width, screensize.height);
				
        		s4 = new JScrollPane(p3);
//	        		s4.setBounds(10, (int)p2.getLocation().getY()+p2.getHeight()+3, screensize.width-20, screensize.height-((int)p2.getLocation().getY()+p2.getHeight())-10);
        		s4.setBounds(10, 510,screensize.width-20, screensize.height-530);
        		s4.getViewport().setBackground(Color.white);
    			getContentPane().add(s4);
				
				String a = jcb.getSelectedItem().toString();
				
				for(int i=0;i<listarray22.size();i+=5){
					String labelname = listarray22.get(i);
					String weldtype = listarray22.get(i+1);
					String weldposition = listarray22.get(i+3);
					
					//不为被清除的焊机列表
					if(!labelname.equals("")){
						JLabel l21 = new JLabel();
						l21.setVerticalTextPosition(JLabel.BOTTOM); 
						l21.setHorizontalTextPosition(JLabel.CENTER);
						JLabel l22 = new JLabel();
						l21.setBounds(50, 100, 200, 200);
						l22.setBounds(70, 400, 200, 200);
						if(a.equals("全部班组")){

							if(limit.equals("true")){
/*								if(listarray4.size()!=0){
									for(int i1=0;i1<listarray4.size();i1+=7){
										if(labelname.equals(listarray4.get(i1+4))){
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
												l21.setIcon(img);
												break;
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
												l21.setIcon(img);
												break;
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
												l21.setIcon(img);
												break;
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
									}
								}else{
									if(listarray22.get(i+1).equals("41")){
										img = new ImageIcon(getClass().getResource("/images/GL.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("42")){
										img = new ImageIcon(getClass().getResource("/images/AT.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("43")){
										img = new ImageIcon(getClass().getResource("/images/FR.png"));
										l21.setIcon(img);
									}
								}*/
								if(firstpageMachine.size()!=0){
									for(int f=0;f<firstpageMachine.size();f+=2){
										if(labelname.equals(firstpageMachine.get(f))){
											if(listarray22.get(i+1).equals("41")){
    											if(firstpageMachine.get(f+1).equals("00")){
    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("42")){
												if(firstpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/ATS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("43")){
												if(firstpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/FRS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}
										}else{
        									if(listarray22.get(i+1).equals("41")){
        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
        										l21.setIcon(img);
        									}else if(listarray22.get(i+1).equals("42")){
        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
        										l21.setIcon(img);
        									}else if(listarray22.get(i+1).equals("43")){
        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
        										l21.setIcon(img);
        									}
										}
									}
								}else{
									if(listarray22.get(i+1).equals("41")){
										img = new ImageIcon(getClass().getResource("/images/GL.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("42")){
										img = new ImageIcon(getClass().getResource("/images/AT.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("43")){
										img = new ImageIcon(getClass().getResource("/images/FR.png"));
										l21.setIcon(img);
									}
								}
							}else if(limit.equals("false")){
								if(listarray22.get(i+1).equals("41")){
									img = new ImageIcon(getClass().getResource("/images/GL.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("42")){
									img = new ImageIcon(getClass().getResource("/images/AT.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("43")){
									img = new ImageIcon(getClass().getResource("/images/FR.png"));
									l21.setIcon(img);
								}
							}
							context.repaint();
							l21.addMouseListener(new MouseListener(){
	
								//图片点击监听
								@Override
								public void mouseClicked(MouseEvent e) {}
								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							if(limit.equals("true")){
								for(int i1=0;i1<listarray4.size();i1+=7){
									if(labelname.equals(listarray4.get(i1+4))){
									}
								}
							}
							
							l21.setText(labelname);
							//l22.setText("       ");
							
							p3.add(l21);
							p3.add(l22);
						
						}else{
							if(listarray22.get(i+2).equals(a)){
							if(limit.equals("true")){
/*								if(listarray4.size()!=0){
									for(int i1=0;i1<listarray4.size();i1+=7){
										if(labelname.equals(listarray4.get(i1+4))){
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
												l21.setIcon(img);
												break;
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
												l21.setIcon(img);
												break;
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
												l21.setIcon(img);
												break;
											}
										}else{
											if(listarray22.get(i+1).equals("41")){
												img = new ImageIcon(getClass().getResource("/images/GL.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("42")){
												img = new ImageIcon(getClass().getResource("/images/AT.png"));
												l21.setIcon(img);
											}else if(listarray22.get(i+1).equals("43")){
												img = new ImageIcon(getClass().getResource("/images/FR.png"));
												l21.setIcon(img);
											}
										}
									}
								}else{
									if(listarray22.get(i+1).equals("41")){
										img = new ImageIcon(getClass().getResource("/images/GL.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("42")){
										img = new ImageIcon(getClass().getResource("/images/AT.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("43")){
										img = new ImageIcon(getClass().getResource("/images/FR.png"));
										l21.setIcon(img);
									}
								}*/
								if(firstpageMachine.size()!=0){
									for(int f=0;f<firstpageMachine.size();f+=2){
										if(labelname.equals(firstpageMachine.get(f))){
											if(listarray22.get(i+1).equals("41")){
    											if(firstpageMachine.get(f+1).equals("00")){
    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("42")){
												if(firstpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/ATS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("43")){
												if(firstpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/FRS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}
										}else{
        									if(listarray22.get(i+1).equals("41")){
        										img = new ImageIcon(getClass().getResource("/images/GL.png"));
        										l21.setIcon(img);
        									}else if(listarray22.get(i+1).equals("42")){
        										img = new ImageIcon(getClass().getResource("/images/AT.png"));
        										l21.setIcon(img);
        									}else if(listarray22.get(i+1).equals("43")){
        										img = new ImageIcon(getClass().getResource("/images/FR.png"));
        										l21.setIcon(img);
        									}
										}
									}
								}else{
									if(listarray22.get(i+1).equals("41")){
										img = new ImageIcon(getClass().getResource("/images/GL.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("42")){
										img = new ImageIcon(getClass().getResource("/images/AT.png"));
										l21.setIcon(img);
									}else if(listarray22.get(i+1).equals("43")){
										img = new ImageIcon(getClass().getResource("/images/FR.png"));
										l21.setIcon(img);
									}
								}
							}else if(limit.equals("false")){
								if(listarray22.get(i+1).equals("41")){
									img = new ImageIcon(getClass().getResource("/images/GL.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("42")){
									img = new ImageIcon(getClass().getResource("/images/AT.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("43")){
									img = new ImageIcon(getClass().getResource("/images/FR.png"));
									l21.setIcon(img);
								}
							}
							context.repaint();
							l21.addMouseListener(new MouseListener(){
	
								//图片点击监听
								@Override
								public void mouseClicked(MouseEvent e) {}
								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							if(limit.equals("true")){
								for(int i1=0;i1<listarray4.size();i1+=7){
									if(labelname.equals(listarray4.get(i1+4))){
									}
								}
							}
							
							l21.setText(labelname);
							//l22.setText("       ");
							
							p3.add(l21);
							p3.add(l22);
						}
					}
					}
				}
				
				context.repaint();
				
				p3.repaint();
			}
		});
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		p3 = new JPanel();
		//加载焊机列表
		p3.removeAll();// = new JPanel();
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.setBounds(20,400,400,400);
		p3.setFont(new Font("Dialog",1,20));
		p3.setBackground(Color.white);
		screensize.setSize(screensize.width, screensize.height);
		
		sp2 = new JScrollPane(p3);
		
		if(listarray22.size() == 0){
			JOptionPane.showMessageDialog(null, "无可选择焊机.", "  错误",JOptionPane.ERROR_MESSAGE);
		}else{				
			for(int i=0;i<listarray22.size();i+=5){
				String labelname = listarray22.get(i);
				String weldtype = listarray22.get(i+1);
				String weldposition = listarray22.get(i+3);
				
				//不为被清除的焊机列表
				if(!labelname.equals("")){
					JLabel l21 = new JLabel();
					l21.setVerticalTextPosition(JLabel.BOTTOM); 
					l21.setHorizontalTextPosition(JLabel.CENTER);
					JLabel l22 = new JLabel();
					l21.setBounds(50, 100, 200, 200);
					l22.setBounds(70, 400, 200, 200);
					
//					if(listarray22.get(i+2).equals(weldowner)){
						
						if(limit.equals("true")){
/*							if(listarray4.size()!=0){
								for(int i1=0;i1<listarray4.size();i1+=7){
									if(labelname.equals(listarray4.get(i1+4))){
										if(listarray22.get(i+1).equals("41")){
											img = new ImageIcon(getClass().getResource("/images/GLW.png"));
											l21.setIcon(img);
											break;
										}else if(listarray22.get(i+1).equals("42")){
											img = new ImageIcon(getClass().getResource("/images/ATW.png"));
											l21.setIcon(img);
											break;
										}else if(listarray22.get(i+1).equals("43")){
											img = new ImageIcon(getClass().getResource("/images/FRW.png"));
											l21.setIcon(img);
											break;
										}
									}else{
										if(listarray22.get(i+1).equals("41")){
											img = new ImageIcon(getClass().getResource("/images/GL.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("42")){
											img = new ImageIcon(getClass().getResource("/images/AT.png"));
											l21.setIcon(img);
										}else if(listarray22.get(i+1).equals("43")){
											img = new ImageIcon(getClass().getResource("/images/FR.png"));
											l21.setIcon(img);
										}
									}
								}
							}else{
								if(listarray22.get(i+1).equals("41")){
									img = new ImageIcon(getClass().getResource("/images/GL.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("42")){
									img = new ImageIcon(getClass().getResource("/images/AT.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("43")){
									img = new ImageIcon(getClass().getResource("/images/FR.png"));
									l21.setIcon(img);
								}
							}*/
							if(firstpageMachine.size()!=0){
								for(int f=0;f<firstpageMachine.size();f+=2){
									if(labelname.equals(firstpageMachine.get(f))){
										if(listarray22.get(i+1).equals("41")){
											if(firstpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
												l21.setIcon(img);
												break;
											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
												l21.setIcon(img);
												break;
											}else{
												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
												l21.setIcon(img);
												break;
											}
										}else if(listarray22.get(i+1).equals("42")){
											if(firstpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/ATS.png"));
												l21.setIcon(img);
												break;
											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
												l21.setIcon(img);
												break;
											}else{
												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
												l21.setIcon(img);
												break;
											}
										}else if(listarray22.get(i+1).equals("43")){
											if(firstpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/FRS.png"));
												l21.setIcon(img);
												break;
											}else if((firstpageMachine.get(f+1).equals("03"))||(firstpageMachine.get(f+1).equals("05"))||(firstpageMachine.get(f+1).equals("07"))){
												img = new ImageIcon(getClass().getResource("/images/FRW.png"));
												l21.setIcon(img);
												break;
											}else{
												img = new ImageIcon(getClass().getResource("/images/FRO.png"));
												l21.setIcon(img);
												break;
											}
										}
									}else{
    									if(listarray22.get(i+1).equals("41")){
    										img = new ImageIcon(getClass().getResource("/images/GL.png"));
    										l21.setIcon(img);
    									}else if(listarray22.get(i+1).equals("42")){
    										img = new ImageIcon(getClass().getResource("/images/AT.png"));
    										l21.setIcon(img);
    									}else if(listarray22.get(i+1).equals("43")){
    										img = new ImageIcon(getClass().getResource("/images/FR.png"));
    										l21.setIcon(img);
    									}
									}
								}
							}else{
								if(listarray22.get(i+1).equals("41")){
									img = new ImageIcon(getClass().getResource("/images/GL.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("42")){
									img = new ImageIcon(getClass().getResource("/images/AT.png"));
									l21.setIcon(img);
								}else if(listarray22.get(i+1).equals("43")){
									img = new ImageIcon(getClass().getResource("/images/FR.png"));
									l21.setIcon(img);
								}
							}
						}else if(limit.equals("false")){
							if(listarray22.get(i+1).equals("41")){
								img = new ImageIcon(getClass().getResource("/images/GL.png"));
								l21.setIcon(img);
							}else if(listarray22.get(i+1).equals("42")){
								img = new ImageIcon(getClass().getResource("/images/AT.png"));
								l21.setIcon(img);
							}else if(listarray22.get(i+1).equals("43")){
								img = new ImageIcon(getClass().getResource("/images/FR.png"));
								l21.setIcon(img);
							}
						}
						
						l21.setText(labelname);
						//l22.setText("       ");
						
						p3.add(l21);
						p3.add(l22);
						
						/*if(limit.equals("true")){
							l21.setBackground(Color.BLACK);
						}*/
				}
			}
			
			double b = panel_1.getHeight();
			panel_1.getY();
			
			sp2.setBounds(10, 510,screensize.width-20, screensize.height-530);
			sp2.setBackground(Color.white);
//			s4.setBounds(0, 510, screensize.width, screensize.height-530);
			getContentPane().add(sp2);
			
		}
	}
	
	public static void main(String[] args){
		//new Secondpage("","","",weldowner,screensize,listarray21,listarray22,listarray3,listarray4,client,listarraywe,listarrayta,welderid);
		
		new Firstpage();
	}

	public void websocdata(String arg0) {
		// TODO Auto-generated method stub
		for(int i=0;i<websocketMachine.size();i+=3){
/*			System.out.println(websocketMachine.get(i));
			System.out.println(Integer.valueOf(arg0.substring(4,8).toString()));*/
			if(websocketMachine.get(i).equals(Integer.valueOf(arg0.substring(4,8)).toString())){
				if(!firstpageMachine.contains(websocketMachine.get(i+1))){
					firstpageMachine.add(websocketMachine.get(i+1));
					firstpageMachine.add(arg0.substring(36,38));
					break;
				}else{
					int num = firstpageMachine.indexOf(websocketMachine.get(i+1));
					firstpageMachine.set(num+1, arg0.substring(36,38));
					break;
				}
			}
		}
	}
}
