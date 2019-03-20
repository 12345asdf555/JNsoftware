 	package com.JN.task;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;
import java.awt.Graphics;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class Secondpage extends JFrame{
	
	//private Clientconnect2 cc = new Clientconnect2(this,2);
	private JPanel p1 = new JPanel();
	private JPanel panel_1;
	public JLabel l11 = new JLabel("焊工姓名:   ");
	public JLabel l12 = new JLabel("焊工编号:   ");
	public JLabel l15 = new JLabel("所在班组:   ");
	public JLabel l13 = new JLabel("焊机编号:   ");
	public JLabel l14 = new JLabel("任务编号:");
	private JLabel l21;
	private JLabel l22;
	private JLabel l32 = new JLabel();
	private JComboBox jcb;
	private JPanel p2 = new JPanel();
	private JScrollPane sp2;
	private JTable t3 = new JTable();
	private JScrollPane sp3;
	private JButton b4 = new JButton("返回"){
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/butre.png"));  
            Image img = icon.getImage();  
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  
        }  
	};
	private JButton b5 = new JButton("确认"){
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/butsur.png"));  
            Image img = icon.getImage();  
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);  
        }  
	};
	
	private Firstpage firstpage;
	public String worktime;
	public String welder;
	public String weld = "";
	private Dimension screensize;
	public ArrayList<String> listarray21;
	public ArrayList<String> listarray22 = new ArrayList<String>();
	public ArrayList<String> listarray3;
	public ArrayList<String> listarray4 = new ArrayList<String>();
	public String weldernum;
	public String weldowner;
	public ImageIcon img;
	private Secondpage sd;
	public Client client;
	private ArrayList<String> listarraywe = new ArrayList<String>();
	private ArrayList<String> listarrayta;
	private String welderid;
    public ArrayList<String> websocketMachine = new ArrayList<String>();
    public ArrayList<String> secondpageMachine = new ArrayList<String>();
    public Timer tExit1;
	public String taskidnew;
	private final JPanel panel_2 = new JPanel();
	private final JPanel panel = new JPanel(){
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/firsttop1.png"));  
            Image img = icon.getImage();  
            g.drawImage(img, 50, 23, 530, 41, this);  
        }  
	};
	private final JLabel l111 = new JLabel("XXXX-XX-XX XX:XX:XX");
	private final JLabel label = new JLabel("信息列表");
	private final JPanel panel_3 = new JPanel();
	JLabel label_1 = new JLabel("焊机类型：   ");
	private final JLabel lblRenwu = new JLabel("任务列表");
	private final JPanel panel_4 = new JPanel();
	private String limit;
	private Firstpage fp;
	
	public Secondpage(String worktime1,String welder1,String weldernum1,String weldowner1, Dimension screensize1,ArrayList<String> listarray221, ArrayList<String> listarray222,ArrayList<String> listarray31, ArrayList<String> listarray41, Client client1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String welderid1, ArrayList<String> firstpageMachine, Firstpage context1){
		super("江南派工");
		fp = context1;
		sd = this;
		secondpageMachine = firstpageMachine;
		
		lblRenwu.setFont(new Font("宋体", Font.BOLD, 20));
		
		Dimension screensize11 = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel_3.setBounds((int)screensize11.getWidth()-400, 360, 390, 53);
		panel_3.setBackground(new Color(251, 129, 54));
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(40)
					.addComponent(lblRenwu)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(l32)
					.addContainerGap(179, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRenwu)
						.addComponent(l32, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		Dimension screensize2 = Toolkit.getDefaultToolkit().getScreenSize();
		panel.setBounds(0, 0, (int)screensize2.getWidth(), 81);
		panel.setBackground(new Color(20,51,105));
		panel.setLayout(null);
		
		Dimension screensize3 = Toolkit.getDefaultToolkit().getScreenSize();
		l111.setBounds((int)screensize3.getWidth()-300, 30, 265, 34);
		l111.setForeground(Color.WHITE);
		l111.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		
		panel.add(l111);
		setFont(new Font("Dialog",1,20)); //标题字体
		
		worktime = worktime1;
		welder = welder1;
		weldernum = weldernum1;
		weldowner = weldowner1;
		screensize = screensize1;
		listarray21 = listarray221;
		//listarray22 = listarray222;
		listarray3 = listarray31;
		//listarray4 = listarray41;
		client = client1;
		//listarraywe = listarraywe1;
		listarrayta = listarrayta1;
		welderid = welderid1;
		
		//new Thread(websocket).start();
		initframe(); //绘制界面
		time(); //电子时钟
		
	}

	public Secondpage() {
		// TODO Auto-generated constructor stub
	}

	/*Runnable websocket = new Runnable(){
		public void run(){
			cc.run();
		}
	};*/
	
	private void time() {
		tExit1 = new Timer();  
        tExit1.schedule(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//一秒刷新一次时钟
				Date date = new Date();
                String time = DateTools.format("yyyy-MM-dd HH:mm:ss",date);
                l111.setText(time);
                repaint();
				
			}  
        }, 0 , 1000);
	}
	
	private void initframe() {
		// TODO Auto-generated method stub
		//setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screensize.width,screensize.height);
		setResizable(false);
		getContentPane().setBackground(new Color(20,51,105));
		setVisible(true);
		
		try {
			  FileInputStream in = new FileInputStream("IPconfig.txt");  
	          InputStreamReader inReader = new InputStreamReader(in, "UTF-8");  
	          BufferedReader bufReader = new BufferedReader(inReader);  
	          String line = null; 
	          int writetime=0;
				
			    while((line = bufReader.readLine()) != null){ 
			    	if(writetime==0){
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
		
		try {
			//任务webservice
			String obj11 = "{\"CLASSNAME\":\"junctionWebServiceImpl\",\"METHOD\":\"getWeldedJunctionAll\"}";
			Object[] objects1 = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
					new Object[] { obj11 });
			String restr1 = objects1[0].toString();
	        JSONArray ary1 = JSONArray.parseArray(restr1);
	        listarray4.clear();
	        
	        ArrayList<String> listarraybuf = new ArrayList<String>();
	        
	        for(int i=0;i<ary1.size();i++){
		        String str = ary1.getString(i);
		        JSONObject js = JSONObject.fromObject(str);
		        
		        
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
            		        	listarray4.add(js.getString("ITEMNAME"));
	        	        		listarray4.add(js.getString("REWELDERNO"));
	        	        		listarray4.add(js.getString("REWELDERNAME"));
	        		        	listarray4.add(js.getString("MACHINENO"));
	        		        	listarray4.add(js.getString("TASKNO"));
	        		        	listarray4.add("焊接");
	        		        	listarray4.add(js.getString("STARTTIME"));
	        		        	listarray4.add(js.getString("TASKDES"));
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
		            		        	listarray4.add(js.getString("ITEMNAME"));
			        	        		listarray4.add(js.getString("REWELDERNO"));
			        	        		listarray4.add(js.getString("REWELDERNAME"));
			        		        	listarray4.add(js.getString("MACHINENO"));
			        		        	listarray4.add(js.getString("TASKNO"));
			        		        	listarray4.add("焊接");
			        		        	listarray4.add(js.getString("STARTTIME"));
			        		        	listarray4.add(js.getString("TASKDES"));
		            		        }
		        				}
		        			}
		        		}
	        		}
	        	}
		        
	        	/*if(js.getString("OPERATESTATUS").equals("0")  || js.getString("OPERATESTATUS").equals("2")){
		        	listarray4.add(js.getString("ITEMNAME"));
	        		listarray4.add(js.getString("REWELDERNO"));
	        		listarray4.add(js.getString("REWELDERNAME"));
		        	listarray4.add(js.getString("MACHINENO"));
		        	listarray4.add(js.getString("TASKNO"));
		        	listarray4.add("焊接");
		        	listarray4.add(js.getString("STARTTIME"));
		        	listarray4.add(js.getString("TASKDES"));
		        }*/
		        
		        String a = js.getString("MACHINENO");
	        }
		
			//焊机webservice
			String obj21 = "{\"CLASSNAME\":\"weldingMachineWebServiceImpl\",\"METHOD\":\"getWeldingMachineAll\"}";
			Object[] objects2 = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
					new Object[] { obj21 });
		    String restr2 = objects2[0].toString();
	        JSONArray ary2 = JSONArray.parseArray(restr2);
	        listarray22.clear();
	        listarraywe.clear();
	        for(int i=0;i<ary2.size();i++){
		        String str = ary2.getString(i);
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
	        	for(int i=0;i<listarray4.size();i+=8){
		        	for(int j=0;j<listarray22.size();j+=5){
		        		if(listarray4.get(i+3).equals(listarray22.get(j))){	
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
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
		p1.setBounds((int)screensize1.getWidth()-400, 148, 390, 205);
		
		//加载状态信息栏
		p1.setFont(new Font("Dialog",1,17));
		p1.setBackground(new Color(52,88,140));
		p1.setLayout(null);
		
		//焊工姓名
		l11.setText("焊工姓名:" + welder);
		l11.setFont(new Font("宋体", Font.BOLD, 17));
		l11.setForeground(Color.WHITE);
		l11.setBounds(14, 23, 200, 22);
		p1.add(l11);
		
		//焊工编号
		l12.setText("焊工编号:" + weldernum);
		l12.setFont(new Font("宋体", Font.BOLD, 17));
		l12.setForeground(Color.WHITE);
		l12.setBounds(14, 121, 400, 22);
		p1.add(l12);
		
		//所在班组
		l15.setText("所在班组:" + weldowner);
		l15.setFont(new Font("宋体", Font.BOLD, 17));
		l15.setForeground(Color.WHITE);
		l15.setBounds(14, 75, 200, 22);
		p1.add(l15);
		
		//焊机编号
		l13.setFont(new Font("宋体", Font.BOLD, 17));
		l13.setForeground(Color.WHITE);
		l13.setBounds(213, 23, 177, 22);
		p1.add(l13);
		
		//任务编号
		l14.setFont(new Font("宋体", Font.BOLD, 17));
		l14.setForeground(Color.WHITE);
		l14.setBounds(14, 167, 400, 22);
		p1.add(l14);
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		
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
		for(int i=0;i<arrString.length;i++){
			if(arrString[i].equals(weldowner)){
				arrString[i] = "  * 班组:" + arrString[i];
			}else{
				arrString[i] = "    班组:" + arrString[i];
			}
			
		}
		
		//加载焊机列表
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p2.setFont(new Font("Dialog",1,20));
		p2.setBackground(Color.white);
		screensize.setSize(screensize.width-440, screensize.height+1000);
		p2.setPreferredSize(screensize);
		//add(p2);
		
		//任务数
		int count = 0;
		for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner) && ( listarray3.get(i+2).equals(welder) || listarray3.get(i+2).equals(""))){
				count++;
			}
		}
		
		//把listarray3提取为只有任务号、焊工两个属性
		/*ArrayList<String> listarraybuf1 = new ArrayList<String>();
		for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner) && ( listarray3.get(i+2).equals(welder) || listarray3.get(i+2).equals(""))){
				listarraybuf1.add(listarray3.get(i));
				listarraybuf1.add(listarray3.get(i+2));
			}
		}
		
		//提取指定焊工到前列
		ArrayList<String> listarraybuf2 = new ArrayList<String>();
		ArrayList<String> listarraybuf3 = new ArrayList<String>();
		for(int i=0;i<listarraybuf1.size();i+=2){
			if(listarraybuf1.get(i+1).equals(welder)){
				listarraybuf2.add(" * " + listarraybuf1.get(i));
				listarraybuf2.add(" * " + listarraybuf1.get(i+1));
			}else{
				listarraybuf3.add(listarraybuf1.get(i));
				listarraybuf3.add(listarraybuf1.get(i+1));
			}
		}
		
		for(int i=0;i<listarraybuf3.size();i+=2){
			listarraybuf2.add(listarraybuf3.get(i));
			listarraybuf2.add(listarraybuf3.get(i+1));
		}*/
		
		ArrayList<String> listarraybuf1 = new ArrayList<String>();
		ArrayList<String> listarraybuf2 = new ArrayList<String>();
		int counttime = 0;
		for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner) && ( listarray3.get(i+2).equals(welder) || listarray3.get(i+2).equals(""))){
				if(limit.equals("false")){
					if(listarray3.get(i+2).equals(welder)){
						listarraybuf1.add(" * " + listarray3.get(i));
						listarraybuf1.add(" * " + listarray3.get(i+1));
						listarraybuf1.add(" * " + listarray3.get(i+2));
					}else{
						listarraybuf2.add(listarray3.get(i));
						listarraybuf2.add(listarray3.get(i+1));
						listarraybuf2.add(listarray3.get(i+2));
					}
				}else if(limit.equals("true")){
					
					for(int j=0;j<listarray4.size();j+=8){
						if(listarray4.get(j+4).equals(listarray3.get(i))){
							if(listarray3.get(i+2).equals(welder)){
								listarraybuf1.add(" * " + listarray3.get(i));
								listarraybuf1.add(" * " + listarray3.get(i+1));
								listarraybuf1.add("已领取");
								break;
							}else{
								listarraybuf2.add(listarray3.get(i));
								listarraybuf2.add(listarray3.get(i+1));
								listarraybuf2.add("已领取");
								break;
							}
						}else{
							counttime++;
							continue;
						}
					}
					
					if(counttime==listarray4.size()/8){
						if(listarray3.get(i+2).equals(welder)){
							listarraybuf1.add(" * " + listarray3.get(i));
							listarraybuf1.add(" * " + listarray3.get(i+1));
							listarraybuf1.add("未领取");
						}else{
							listarraybuf2.add(listarray3.get(i));
							listarraybuf2.add(listarray3.get(i+1));
							listarraybuf2.add("未领取");
						}
					}
					counttime = 0;
					
				}
			}
		}
		
		for(int i=0;i<listarraybuf2.size();i+=3){
			listarraybuf1.add(listarraybuf2.get(i));
			listarraybuf1.add(listarraybuf2.get(i+1));
			listarraybuf1.add(listarraybuf2.get(i+2));
		}
		
		//table列名以及值
		String[] cn = {"任务号", "任务状态"};  
		Object[][] obj = new Object[listarraybuf1.size()/3][2];  
		for(int i=0;i<listarraybuf1.size()/3;i++){
			for(int j=0;j<2;j++){
				if(j==0){
					obj[i][j] = listarraybuf1.get(i*3+j);
				}else if(j==1){
					obj[i][j] = listarraybuf1.get(i*3+j+1);
				}
			}
		}
		
		JTableHeader tableHeader = t3.getTableHeader();  
    	tableHeader.setReorderingAllowed(false);
		
		//绘图
		t3 = new JTable(obj,cn){
			public boolean isCellEditable(int row, int column)
            {
               return false;//表格不允许被编辑
            }
		};

        //居中显示，关闭自动编辑
        /*DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        t3.setDefaultRenderer(Object.class, r);
        t3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); */
		TableCellTextAreaRenderer r = new TableCellTextAreaRenderer();
		t3.getColumnModel().getColumn(0).setCellRenderer(r);
        
		t3.setFont(new Font("Dialog",1,15));
		
		
		//设置已领取任务背景颜色
		for(int i=0;i<listarraybuf1.size();i+=3){
			if(listarraybuf1.get(i+2).equals("已领取")){
		        setOneRowBackgroundColor(t3,(i+3)/3-1,Color.GREEN);
			}
		}
		
		//设置table宽高
		TableColumn column = null;  
        int colunms = t3.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = t3.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/ 
            if(i == 0){
            	column.setPreferredWidth((((int)panel_3.getWidth()))/9*7);
            }else if(i == 1){
            	column.setPreferredWidth((((int)panel_3.getWidth())-6)/9*2);
            }
        }  

		//设置table行高
        t3.setRowHeight(150);
        
        t3.addMouseListener(new MouseListener(){
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				String taskid = t3.getValueAt(t3.getSelectedRow(), 0).toString();
				l14.setText("任务编号:" + taskid);
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
        
        Dimension screensize11 = Toolkit.getDefaultToolkit().getScreenSize();
        
		sp3 = new JScrollPane(t3);
		sp3.setBounds((int)screensize11.getWidth()-400, 420, 390, (int)screensize11.getHeight()-420-90);
		sp3.setBackground(Color.white);
		repaint();
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 85, (int)screensize11.getWidth()-420, 53);
		panel_1.setBackground(new Color(251,129,54));
		panel_1.setVisible(true);
		panel_2.setBounds((int)screensize11.getWidth()-400, 85, 390, 53);
		panel_2.setBackground(new Color(251,129,54));
		
		JLabel lblNewLabel = new JLabel("可选焊机");
		lblNewLabel.setFont(new Font("宋体", Font.BOLD, 20));
		
		jcb = new JComboBox(arrString);
		jcb.setBackground(Color.white);
		jcb.setFont(new Font("Dialog",1,14));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(42)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
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
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
		);
		panel_1.setLayout(gl_panel_1);
		jcb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//响应按钮重绘界面
				sd.remove(p2);
				sd.remove(sp2);
				sd.repaint();
				
				//加载焊机列表
				p2.removeAll();// = new JPanel();
				p2.setBounds(20,400,400,400);
				p2.setFont(new Font("Dialog",1,20));
				p2.setBackground(Color.white);
				screensize.setSize(screensize.width, screensize.height);
				
				sp2 = new JScrollPane(p2);
				sp2.setBounds(10, (int)panel_1.getHeight()+panel_1.getY()+10, (int)panel_1.getWidth(), panel_4.getY()-10-((int)panel_1.getHeight()+panel_1.getY()+5));
				sp2.setBackground(Color.white);
				getContentPane().add(sp2);
				
				String[] a = jcb.getSelectedItem().toString().split(":");
				
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
						
						if(listarray22.get(i+2).equals(a[1])){
							if(limit.equals("true")){
/*								if(listarray4.size()!=0){
									for(int i1=0;i1<listarray4.size();i1+=8){
										if(labelname.equals(listarray4.get(i1+3))){
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
								if(secondpageMachine.size()!=0){
									for(int f=0;f<secondpageMachine.size();f+=2){
										if(labelname.equals(secondpageMachine.get(f))){
											if(listarray22.get(i+1).equals("41")){
    											if(secondpageMachine.get(f+1).equals("00")){
    												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("42")){
												if(secondpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/ATS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
    												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
    												l21.setIcon(img);
    												break;
    											}else{
    												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
    												l21.setIcon(img);
    												break;
    											}
											}else if(listarray22.get(i+1).equals("43")){
												if(secondpageMachine.get(f+1).equals("00")){
													img = new ImageIcon(getClass().getResource("/images/FRS.png"));
    												l21.setIcon(img);
    												break;
    											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
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
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
									if(e.getClickCount()==2){
										/*String weldid = "";
										weld = labelname;
										for(int i=0;i<listarraywe.size();i+=2){
											if(weld.equals(listarraywe.get(i))){
												weldid = listarraywe.get(i+1);
											}
										}
										
										if(weldtype.equals("41")){
											img = new ImageIcon(getClass().getResource("/images/GL.png"));
											l21.setIcon(img);
										}else if(weldtype.equals("42")){
											img = new ImageIcon(getClass().getResource("/images/AT.png"));
											l21.setIcon(img);
										}else if(weldtype.equals("43")){
											img = new ImageIcon(getClass().getResource("/images/FR.png"));
											l21.setIcon(img);
										}
										
										JWeldButton jb = new JWeldButton(weldposition,weldtype,weld,img,sd,listarraywe,listarrayta,weldid,welderid,cc);*/
										//jb.setVisible(true);
										
									}else if(e.getClickCount()==1){
										for(int i=0;i<p2.getComponentCount();i++){
											int afre = p2.getComponentCount();
											Component asdf = p2.getComponent(i);
											((JComponent) asdf).setBorder(null);
											System.out.println(asdf);
										}
										l21.setBorder(BorderFactory.createLineBorder(Color.RED,4));
										weld = labelname;
										if(listarray4.size()==0){
											l13.setText("焊机编号:" + weld);
											l14.setText("任务编号:");
											if(weldtype.equals("41")){
												label_1.setText("焊机类型:" + "MAG");
											}else if(weldtype.equals("42")){
												label_1.setText("焊机类型:"+"手工焊");
											}else{
												label_1.setText("焊机类型:"+"二氧焊机");
											}
										}
										for(int i=0;i<listarray4.size();i+=8){
											if(weld.equals(listarray4.get(i+3))){
												l13.setText("焊机编号:" + weld);
												l14.setText("任务编号:" + listarray4.get(i+4));
												if(weldtype.equals("41")){
													label_1.setText("焊机类型:" + "MAG");
												}else if(weldtype.equals("42")){
													label_1.setText("焊机类型:"+"手工焊");
												}else{
													label_1.setText("焊机类型:"+"二氧焊机");
												}
												break;
											}else{
												l13.setText("焊机编号:" + weld);
												l14.setText("任务编号:");
												if(weldtype.equals("41")){
													label_1.setText("焊机类型:" + "MAG");
												}else if(weldtype.equals("42")){
													label_1.setText("焊机类型:"+"手工焊");
												}else{
													label_1.setText("焊机类型:"+"二氧焊机");
												}
											}
										}
									}
									
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
							
							if(limit.equals("true")){
								for(int i1=0;i1<listarray4.size();i1+=8){
									if(labelname.equals(listarray4.get(i1+3))){
									}
								}
							}
							
							l21.setText(labelname);
							l22.setText("                 ");
							
							p2.add(l21);
							p2.add(l22);
						}
					}
				}
				
				sd.repaint();
			}
		});
		l32.setFont(new Font("宋体", Font.BOLD, 20));
		l32.setForeground(Color.BLACK);
		l32.setText("任务数:"+count);
		panel_4.setBounds(0, (int)screensize11.getHeight()-80, (int)screensize11.getWidth(), 80);
		panel_4.setLayout(null);
		b4.setFont(new Font("宋体", Font.BOLD, 17));
		b4.setBounds(((int)screensize11.getWidth()+200)/2, 3, 116, 35);
		b5.setFont(new Font("宋体", Font.BOLD, 17));
		b5.setBounds(((int)screensize11.getWidth()-300)/2, 3, 116, 35);
		panel_4.setBackground(new Color(20,51,105));
		panel_4.add(b4);
		panel_4.add(b5);
		b4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				fp.textField.setText("");
				fp.setVisible(true);;
				
				setVisible(false);
			}
		});
		b5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(l14.getText().toString().length() != 5){
					
					try{
						if(weld.length() != 0){
							/*l14.setText("任务编号:  " + task);
							JOptionPane.showMessageDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.INFORMATION_MESSAGE);*/
							
							//String task = l14.getText().toString();
							
							String task = t3.getValueAt(t3.getSelectedRow(), 0).toString();

							/*String[] taskbuf = task.split(":");
							task = taskbuf[1];*/
							
							for(int i=0;i<listarrayta.size();i+=2){
								if(task.equals(listarrayta.get(i))){
									taskidnew = listarrayta.get(i+1);
									break;
								}
							}
							
							/*label_9.setText("任务编号:" + task);
							label_9.setFont(new Font("宋体",1,15));*/
							
							String weldid = null;
							if(limit.equals("false")){
								int i = JOptionPane.showConfirmDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.YES_NO_OPTION);
								if(i!=0){
									//label_7.setText("任务编号:  ");
								}else{
									
									for(int i1=0;i1<listarraywe.size();i1+=2){
										if(weld.equals(listarraywe.get(i1))){
											weldid = listarraywe.get(i1+1);
										}
									}
									
									JProcessBarDemo jpd = new JProcessBarDemo(screensize,sd,weldid,welderid,taskidnew,fp);
									jpd.setVisible(true);
									
									//JOptionPane.showMessageDialog(null, "领取成功,请尽快完成任务", "确认",JOptionPane.INFORMATION_MESSAGE);
									//new Firstpage();
								}
							}else{
								for(int i1=0;i1<listarraywe.size();i1+=2){
									if(weld.equals(listarraywe.get(i1))){
										weldid = listarraywe.get(i1+1);
									}
								}
								JProcessBarDemo jpd = new JProcessBarDemo(screensize,sd,weldid,welderid,taskidnew,fp);
								jpd.setVisible(true);
							}
							
							//JOptionPane.showMessageDialog(null, "请稍候...", "",JOptionPane.INFORMATION_MESSAGE);
							
						}else{
							JOptionPane.showMessageDialog(null, "无选择焊机.", "  错误",JOptionPane.ERROR_MESSAGE);
						}
						
					}catch(Exception e1){
						JOptionPane.showMessageDialog(null, "无选择任务.", "  错误",JOptionPane.ERROR_MESSAGE);
					}
					
				}else{
					JOptionPane.showMessageDialog(null, "无选择任务.", "  错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		label.setFont(new Font("宋体", Font.BOLD, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(36)
					.addComponent(label)
					.addContainerGap(317, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(label)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().add(panel_1);
		getContentPane().add(sp3);
		getContentPane().add(panel_3);
		getContentPane().add(p1);
		
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("宋体", Font.BOLD, 17));
		label_1.setBounds(213, 75, 177, 22);
		p1.add(label_1);
		getContentPane().add(panel_2);
		getContentPane().add(panel_4);
		
		if(listarray22.size() == 0){
			JOptionPane.showMessageDialog(null, "无可选择焊机.", "  错误",JOptionPane.ERROR_MESSAGE);
		}else{
			
			/*int j = 0;
			String[] weldtotal = new String[100];
			for(int i =0;i<listarray22.size();i+=3){
				String labelname = listarray22.get(i);
				String weldtype = listarray22.get(i+1);
				String weldins = listarray22.get(i+2);
				if(j == 0){
					weldtotal[j] = weldins;
					j++;
				}else{
					for(int k=0;k<j;k++){
						if(!weldtotal[k].equals(weldins)){
							weldtotal[j] = weldins;
							j++;
						}else{
							break;
						}
					}
				}
			}*/
			
			for(int i=0;i<listarray22.size();i+=5){
				String labelname = listarray22.get(i);
				String weldtype = listarray22.get(i+1);
				String weldposition = listarray22.get(i+3);
				
				//不为被清除的焊机列表
				if(!labelname.equals("")){
					JLabel l21 = new JLabel();
					l21.setVerticalTextPosition(JLabel.BOTTOM); 
					l21.setHorizontalTextPosition(JLabel.CENTER);
					l22 = new JLabel();
					l21.setBounds(50, 100, 200, 200);
					l22.setBounds(70, 400, 200, 200);
					
					if(listarray22.get(i+2).equals(weldowner)){
						
						if(limit.equals("true")){
/*							if(listarray4.size()!=0){
								for(int i1=0;i1<listarray4.size();i1+=8){
									if(labelname.equals(listarray4.get(i1+3))){
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
							if(secondpageMachine.size()!=0){
								for(int f=0;f<secondpageMachine.size();f+=2){
									if(labelname.equals(secondpageMachine.get(f))){
										if(listarray22.get(i+1).equals("41")){
											if(secondpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/GLS.png"));
												l21.setIcon(img);
												break;
											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
												img = new ImageIcon(getClass().getResource("/images/GLW.png"));
												l21.setIcon(img);
												break;
											}else{
												img = new ImageIcon(getClass().getResource("/images/GLO.png"));
												l21.setIcon(img);
												break;
											}
										}else if(listarray22.get(i+1).equals("42")){
											if(secondpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/ATS.png"));
												l21.setIcon(img);
												break;
											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
												img = new ImageIcon(getClass().getResource("/images/ATW.png"));
												l21.setIcon(img);
												break;
											}else{
												img = new ImageIcon(getClass().getResource("/images/ATO.png"));
												l21.setIcon(img);
												break;
											}
										}else if(listarray22.get(i+1).equals("43")){
											if(secondpageMachine.get(f+1).equals("00")){
												img = new ImageIcon(getClass().getResource("/images/FRS.png"));
												l21.setIcon(img);
												break;
											}else if((secondpageMachine.get(f+1).equals("03"))||(secondpageMachine.get(f+1).equals("05"))||(secondpageMachine.get(f+1).equals("07"))){
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
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								
								if(e.getClickCount()==2){

									/*String weldid = "";
									weld = labelname;
									for(int i=0;i<listarraywe.size();i+=2){
										if(weld.equals(listarraywe.get(i))){
											weldid = listarraywe.get(i+1);
										}
									}
									
									if(weldtype.equals("41")){
										img = new ImageIcon(getClass().getResource("/images/GL.png"));
										l21.setIcon(img);
									}else if(weldtype.equals("42")){
										img = new ImageIcon(getClass().getResource("/images/AT.png"));
										l21.setIcon(img);
									}else if(weldtype.equals("43")){
										img = new ImageIcon(getClass().getResource("/images/FR.png"));
										l21.setIcon(img);
									}
									
									JWeldButton jb = new JWeldButton(weldposition,weldtype,weld,img,sd,listarraywe,listarrayta,weldid,welderid,cc);*/
									//jb.setVisible(true);
									
								}else if(e.getClickCount()==1){
									for(int i=0;i<p2.getComponentCount();i++){
										int afre = p2.getComponentCount();
										Component asdf = p2.getComponent(i);
										((JComponent) asdf).setBorder(null);
										System.out.println(asdf);
									}
									l21.setBorder(BorderFactory.createLineBorder(Color.RED,4));
									weld = labelname;
									if(listarray4.size()==0){
										l13.setText("焊机编号:" + weld);
										l14.setText("任务编号:");
										if(weldtype.equals("41")){
											label_1.setText("焊机类型:" + "MAG");
										}else if(weldtype.equals("42")){
											label_1.setText("焊机类型:"+"手工焊");
										}else{
											label_1.setText("焊机类型:"+"二氧焊机");
										}
									}
									for(int i=0;i<listarray4.size();i+=8){
										if(weld.equals(listarray4.get(i+3))){
											l13.setText("焊机编号:" + weld);
											l14.setText("任务编号:" + listarray4.get(i+4));
											if(weldtype.equals("41")){
												label_1.setText("焊机类型:" + "MAG");
											}else if(weldtype.equals("42")){
												label_1.setText("焊机类型:"+"手工焊");
											}else{
												label_1.setText("焊机类型:"+"二氧焊机");
											}
											break;
										}else{
											l13.setText("焊机编号:" + weld);
											l14.setText("任务编号:");
											if(weldtype.equals("41")){
												label_1.setText("焊机类型:" + "MAG");
											}else if(weldtype.equals("42")){
												label_1.setText("焊机类型:"+"手工焊");
											}else{
												label_1.setText("焊机类型:"+"二氧焊机");
											}
										}
									}
								}
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
						
						l21.setText(labelname);
						l22.setText("                 ");
						
						p2.add(l21);
						p2.add(l22);
						
						/*if(limit.equals("true")){
							l21.setBackground(Color.BLACK);
						}*/
						
					}
				}
			}
			
			sp2 = new JScrollPane(p2);
			
		
			double a = panel.getHeight();
			double b = panel_1.getHeight();
			panel_1.getY();
			
			sp2.setBounds(10, (int)panel_1.getHeight()+panel_1.getY()+10, (int)panel_1.getWidth(), panel_4.getY()-10-((int)panel_1.getHeight()+panel_1.getY()+5));
			sp2.setBackground(Color.white);
			getContentPane().add(sp2);
			
		}
		
		
	}

	public static void setOneRowBackgroundColor(JTable table, final int rowIndex,
		      final Color color) {
	    try {
	    	TableCellTextAreaRenderer tcr = new TableCellTextAreaRenderer() {
	 
	        public Component getTableCellRendererComponent(JTable table,
	            Object value, boolean isSelected, boolean hasFocus,
	            int row, int column) {
	        	
	          //改颜色	
	          if (row == rowIndex) {
	            setBackground(color);
	            setForeground(Color.BLACK);
	          }else{
	        	setBackground(Color.WHITE);
	            setForeground(Color.BLACK);
	          }
	          
	          return super.getTableCellRendererComponent(table, value,
	              isSelected, hasFocus, row, column);
	        }
	      };
	    } catch (Exception ex) {
	      ex.printStackTrace();
	    }
	}

	public void websocketdata(String arg0) {
		for(int i=0;i<websocketMachine.size();i+=3){
			if(websocketMachine.get(i).equals(Integer.valueOf(arg0.substring(4,8)).toString())){
				if(!secondpageMachine.contains(websocketMachine.get(i+1))){
					secondpageMachine.add(websocketMachine.get(i+1));
					secondpageMachine.add(arg0.substring(36,38));
					break;
				}else{
					int num = secondpageMachine.indexOf(websocketMachine.get(i+1));
					secondpageMachine.set(num+1, arg0.substring(36,38));
					break;
				}
			}
		}
/*		if(!secondpageMachine.contains(arg0.substring(4,8))){
			secondpageMachine.add(arg0.substring(4,8));
			secondpageMachine.add(arg0.substring(36,38));
		}else{
			int num = secondpageMachine.indexOf(arg0.substring(4,8));
			secondpageMachine.set(num+1, arg0.substring(36,38));
		}*/
	}
}
