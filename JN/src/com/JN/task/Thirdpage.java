package com.JN.task;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument.Content;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Thirdpage extends JFrame{
	
	private String worktime;
	private String welder;
	public String weldernum;
	private String weldowner;
	public String weld;
	public String task = "";
	private Dimension screensize;
	private ArrayList<String> listarray21;
	private ArrayList<String> listarray22;
	private ArrayList<String> listarray3;
	private ArrayList<String> listarray4;
	private JPanel panel_1;
	public JLabel l11 = new JLabel("焊工姓名：   ");
	public JLabel l1111 = new JLabel("焊工姓名：");
	private JLabel l12 = new JLabel("焊工编号：   ");
	private JLabel l15 = new JLabel("所在班组：   ");
	private JLabel l16 = new JLabel();
	private JScrollPane sp2;
	private JList j2 = new JList();
	private JTable t2 = new JTable();
	private JButton b31 = new JButton("确认"){
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/butsur.png"));  
            Image img11 = icon.getImage();  
            g.drawImage(img11, 0, 0, getWidth(), getHeight(), this);  
        }  
	};
	private JButton b32 = new JButton("返回"){
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/butre.png"));  
            Image img11 = icon.getImage();  
            g.drawImage(img11, 0, 0, getWidth(), getHeight(), this);  
        }  
	};
	private ImageIcon img;
	private Thirdpage td;
	
	private IsnullUtil iutil;
	private JaxWsDynamicClientFactory dcf;
	public Client client;
	private String weldid;
	private String welderid;
	private ArrayList<String> listarrayta;
	private ArrayList<String> listarraywe;
	private String weldtype;
	private final JPanel panel = new JPanel() {
		@Override  
        protected void paintComponent(Graphics g) { 
		  	Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
            ImageIcon icon = new ImageIcon(getClass().getResource("/images/firsttop1.png"));  
            Image img11 = icon.getImage();  
            g.drawImage(img11, 50, 23, 530, 41, this);  
        }  
	};
	private final JLabel label = new JLabel("XXXX-XX-XX XX:XX:XX");
	private final JLabel label_1 = new JLabel("任务列表");
	private final JPanel panel_2 = new JPanel();
	private final JLabel label_2 = new JLabel("信息列表");
	private final JPanel panel_3 = new JPanel();
	public JLabel label_7 = new JLabel("任务编号:   ");
	public JLabel label_9= new JLabel("任务编号:   ");
	private final JLabel label_8 = new JLabel("焊机类型:   ");
	public JLabel label_3 = new JLabel("焊机编号:   ");
	public JLabel label_311 = new JLabel("焊机编号:   ");
	private final JPanel panel_4 = new JPanel();
	private final JLabel label_4 = new JLabel("焊机信息");
	private final JPanel panel_5 = new JPanel();
	private final JPanel panel_6 = new JPanel();
	private String limit;
	private Object[][] obj;
	private Secondpage sd;
	private Firstpage fp;
	private Clientconnect2 cc;
	
	public Thirdpage(String worktime1, String welder1, String weldernum1, String weldowner1, String weld1, Dimension screensize1, ArrayList<String> listarray221, ArrayList<String> listarray222, ArrayList<String> listarray31, ArrayList<String> listarray41, ImageIcon img1, Client client1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String weldid1, String welderid1,String weldtype1, Secondpage sd1, Clientconnect2 cc1){
		super("江南派工");
		label_4.setFont(new Font("宋体", Font.BOLD, 20));
		setFont(new Font("Dialog",1,20)); //标题字体
		
		worktime = worktime1;
		welder = welder1;
		weldernum = weldernum1;
		weldowner = weldowner1;
		weld = weld1;
		screensize = screensize1;
		listarray21 = listarray221;
		listarray22 = listarray222;
		listarray3 = listarray31;
		listarray4 = listarray41;
		img = img1;
		client = client1;
		listarraywe = listarraywe1;
		listarrayta = listarrayta1;
		weldid = weldid1;
		welderid = welderid1;
		weldtype = weldtype1;
		sd = sd1;
		cc = cc1;
		
		time(); //电子时钟
		initframe(); //绘制界面
		
		td = this;
	}

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
                String time = DateTools.format("yyyy-MM-dd HH:mm:ss",date);
                label.setText(time);
                repaint();
				
			}  
        }, 0 , 1000);
	}
	
	private void initframe() {
		// TODO Auto-generated method stub
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
		
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0,0,screensize.width,screensize.height);
		getContentPane().setBackground(new Color(20,51,105));
		getContentPane().setLayout(null);
		
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
		
		//table列名以及值
		/*if(limit.equals("false")){
			String[] cn = {"任务编号", "指定班组", "指定焊工"};  
			obj = new Object[listarray3.size()/4][3];
		}else if(limit.equals("true")){
			String[] cn = {"任务编号", "指定班组", "任务状态"};  
			obj = new Object[listarray3.size()/4][3];
		}*/
		
		/*for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner)){
				for(int j=0;j<4;j++){
					obj[count][j] = listarray3.get(i+j);
				}
				count++;
			}
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
		
		obj = new Object[listarraybuf1.size()/3][3];
		int count = 0; 
		
		for(int i=0;i<listarraybuf1.size();i+=3){
			for(int j=0;j<3;j++){
				obj[count][j] = listarraybuf1.get(i+j);
			}
			count++;
		}
		
		Dimension screensize2 = Toolkit.getDefaultToolkit().getScreenSize();
		panel.setLayout(null);
		panel.setBackground(new Color(20, 51, 105));
		panel.setBounds(0, 0, (int)screensize2.getWidth(), 81);
		
		getContentPane().add(panel);
		Dimension screensize3 = Toolkit.getDefaultToolkit().getScreenSize();
		label.setBounds((int)screensize3.getWidth()-300, 30, 265, 34);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		
		panel.add(label);
		
		panel_1 = new JPanel();
		panel_1.setBounds(10, 85, (int)screensize3.getWidth()-420, 53);
		panel_1.setBackground(new Color(251,129,54));
		panel_1.setVisible(true);
		getContentPane().add(panel_1);
		label_1.setFont(new Font("宋体", Font.BOLD, 20));
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(50)
					.addComponent(label_1)
					.addContainerGap(1366, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(label_1)
					.addContainerGap())
		);
		panel_1.setLayout(gl_panel_1);
		panel_2.setBounds((int)screensize3.getWidth()-400, 85, 390, 53);
		panel_2.setBackground(new Color(251,129,54));
		
		getContentPane().add(panel_2);
		label_2.setFont(new Font("宋体", Font.BOLD, 20));
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGap(44)
					.addComponent(label_2)
					.addContainerGap(262, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(label_2)
					.addContainerGap())
		);
		panel_2.setLayout(gl_panel_2);
		panel_3.setLayout(null);
		panel_3.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_3.setBackground(new Color(52, 88, 140));
		panel_3.setBounds((int)screensize3.getWidth()-400, 151, 390, 238);
		
		getContentPane().add(panel_3);
		label_7.setForeground(Color.WHITE);
		label_7.setFont(new Font("宋体", Font.BOLD, 17));
		label_7.setBounds(213, 174, 177, 22);
		
		panel_3.add(label_7);
		label_8.setForeground(Color.WHITE);
		label_8.setFont(new Font("宋体", Font.BOLD, 17));
		if(weldtype.equals("41")){
			label_8.setText("焊机类型:"+"MAG");
		}else if(weldtype.equals("42")){
			label_8.setText("焊机类型:"+"手工焊");
		}else{
			label_8.setText("焊机类型:"+"二氧焊机");
		}
		label_8.setBounds(213, 99, 177, 22);
		
		panel_3.add(label_8);
		l11.setBounds(14, 23, 200, 22);
		panel_3.add(l11);
		
		//焊工姓名
		l11.setText("焊工姓名:"+welder);
		l1111.setText("焊工姓名:"+welder);
		l1111.setFont(new Font("宋体", Font.BOLD, 15));
		l11.setFont(new Font("宋体", Font.BOLD, 17));
		l11.setForeground(Color.WHITE);
		l15.setBounds(14, 99, 200, 22);
		panel_3.add(l15);
		
		//所在班组
		l15.setText("所在班组:"+weldowner);
		l15.setFont(new Font("宋体", Font.BOLD, 17));
		l15.setForeground(Color.WHITE);
		l12.setBounds(14, 174, 200, 22);
		panel_3.add(l12);
		
		//焊工编号
		l12.setText("焊工编号:"+weldernum);
		l12.setFont(new Font("宋体", Font.BOLD, 17));
		l12.setForeground(Color.WHITE);
		
		label_3.setForeground(Color.WHITE);
		label_3.setFont(new Font("宋体", Font.BOLD, 17));
		label_3.setBounds(213, 26, 177, 22);
		label_3.setText("焊机编号:"+weld);
		label_311.setText("焊机编号:"+weld);
		label_311.setFont(new Font("宋体", Font.BOLD, 15));
		
		panel_3.add(label_3);
		panel_4.setBackground(new Color(251, 129, 54));
		panel_4.setBounds((int)screensize3.getWidth()-400, 402, 390, 53);
		
		getContentPane().add(panel_4);
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGap(0, 390, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(44)
					.addComponent(label_4)
					.addContainerGap(262, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGap(0, 53, Short.MAX_VALUE)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap(16, Short.MAX_VALUE)
					.addComponent(label_4)
					.addContainerGap())
		);
		panel_4.setLayout(gl_panel_4);
		panel_5.setLayout(null);
		panel_5.setFont(new Font("Dialog", Font.BOLD, 17));
		panel_5.setBackground(new Color(52, 88, 140));
		//panel_5.setBackground(Color.white);
		panel_5.setBounds((int)screensize3.getWidth()-400, 468, 390, (int)screensize3.getHeight()-393-160);
		
		getContentPane().add(panel_5);
		
		if(weldtype.equals("41")){
			img = new ImageIcon(getClass().getResource("/images/GL.png"));
			img.setImage(img.getImage().getScaledInstance(panel_5.getWidth()*5/8,panel_5.getHeight()*8/15,Image.SCALE_DEFAULT));
			l16.setBounds(panel_5.getWidth()*3/16, panel_5.getHeight()*7/30, panel_5.getWidth()*5/8, panel_5.getHeight()*8/15);
			l16.setIcon(img);
			panel_5.add(l16);
		}else if(weldtype.equals("42")){
			img = new ImageIcon(getClass().getResource("/images/AT.png"));
			img.setImage(img.getImage().getScaledInstance(panel_5.getWidth()*5/8,panel_5.getHeight()*8/15,Image.SCALE_DEFAULT));
			l16.setBounds(panel_5.getWidth()*3/16, panel_5.getHeight()*7/30, panel_5.getWidth()*5/8, panel_5.getHeight()*8/15);
			l16.setIcon(img);
			panel_5.add(l16);
		}else if(weldtype.equals("43")){
			img = new ImageIcon(getClass().getResource("/images/FR.png"));
			img.setImage(img.getImage().getScaledInstance(panel_5.getWidth()*5/8,panel_5.getHeight()*8/15,Image.SCALE_DEFAULT));
			l16.setBounds(panel_5.getWidth()*3/16, panel_5.getHeight()*7/30, panel_5.getWidth()*5/8, panel_5.getHeight()*8/15);
			l16.setIcon(img);
			panel_5.add(l16);
		}/*else if(weldtype.equals("伊萨")){
			img = new ImageIcon(getClass().getResource("/images/ESAB1.png"));
			img.setImage(img.getImage().getScaledInstance(panel_5.getWidth()*5/8,panel_5.getHeight()*8/15,Image.SCALE_DEFAULT));
			l16.setBounds(panel_5.getWidth()*3/16, panel_5.getHeight()*7/30, panel_5.getWidth()*5/8, panel_5.getHeight()*8/15);
			l16.setIcon(img);
			panel_5.add(l16);
		}*/
		
		panel_6.setLayout(null);
		panel_6.setBackground(new Color(20, 51, 105));
		panel_6.setBounds(0, (int)screensize3.getHeight()-80, (int)screensize3.getWidth(), 80);
		
		getContentPane().add(panel_6);
		b31.setBounds(((int)screensize3.getWidth()-300)/2, 13, 125, 40);
		panel_6.add(b31);
		b32.setBounds(((int)screensize3.getWidth()+70)/2, 13, 125, 40);
		panel_6.add(b32);
		b32.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//开启新视窗选择焊机
				new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4, client,listarraywe,listarrayta,welderid);
				
				setVisible(false);
			}
		});
		b31.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(task != ""){
					
					/*l14.setText("任务编号:  " + task);
					JOptionPane.showMessageDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.INFORMATION_MESSAGE);*/
					
					String taskid = "";

					String[] taskbuf = task.split(" ");
					if(taskbuf.length == 1){
						task = task;
					}else{
						task = taskbuf[2];
					}
					
					for(int i=0;i<listarrayta.size();i+=2){
						if(task.equals(listarrayta.get(i))){
							taskid = listarrayta.get(i+1);
							break;
						}
					}
					
					label_9.setText("任务编号:" + task);
					label_9.setFont(new Font("宋体",1,15));
					
					if(limit.equals("false")){
						int i = JOptionPane.showConfirmDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.YES_NO_OPTION);
						if(i!=0){
							label_7.setText("任务编号:  ");
						}else{
							
							JProcessBarDemo jpd = new JProcessBarDemo(screensize,td,weldid,welderid,taskid);
							jpd.setVisible(true);
							
							//JOptionPane.showMessageDialog(null, "领取成功,请尽快完成任务", "确认",JOptionPane.INFORMATION_MESSAGE);
							//new Firstpage();
						}
					}else{
						JProcessBarDemo jpd = new JProcessBarDemo(screensize,td,weldid,welderid,taskid);
						jpd.setVisible(true);
					}
					
					//JOptionPane.showMessageDialog(null, "请稍候...", "",JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(null, "无选择任务.", "  错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		if(limit.equals("false")){
			String[] cn = {"任务编号", "指定班组", "指定焊工"};  

			//绘图
			t2 = new JTable(obj,cn){
				public boolean isCellEditable(int row, int column)
	            {
	                   return false;//表格不允许被编辑
	            }
			};
			t2.setFont(new Font("Dialog",1,15));
			
		}else if(limit.equals("true")){
			String[] cn = {"任务编号", "指定班组", "任务状态"};  

			//绘图
			t2 = new JTable(obj,cn){
				public boolean isCellEditable(int row, int column)
	            {
	                   return false;//表格不允许被编辑
	            }
			};
			t2.setFont(new Font("Dialog",1,15));
			
		}
		
		
		/*try {
            DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer() {
            //重写getTableCellRendererComponent 方法
            @Override
            public Component getTableCellRendererComponent(JTable table,Object value, boolean isSelected, boolean hasFocus,int row, int column) {
            //##################### 这里是你需要看需求修改的部分
            if(row==1){
                setBackground(Color.GREEN);
                setForeground(Color.WHITE);
            }
            //######################
            return super.getTableCellRendererComponent(table, value,isSelected, hasFocus, row, column);
            }
            };
            //对每行的每一个单元格
            int columnCount = t2.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                t2.getColumn(t2.getColumnName(i)).setCellRenderer(dtcr);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
		
		//设置table宽高
		TableColumn column = null;  
        int colunms = t2.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = t2.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/ 
            if(i == 0){
            	column.setPreferredWidth(panel_1.getWidth()/2-50);
            }else{
            	column.setPreferredWidth(panel_1.getWidth()/4+23);
            }
        }  
        
        //居中显示，关闭自动编辑
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        t2.setDefaultRenderer(Object.class, r);
        t2.setRowHeight(50);
        t2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
		
		for(int i=0;i<listarraybuf1.size();i+=3){
			if(listarraybuf1.get(i+2).equals("已领取")){
		        setOneRowBackgroundColor(t2,(i+3)/3-1,Color.GREEN);
			}
		}
        
        //相应点击事件
        t2.addMouseListener(new java.awt.event.MouseAdapter(){
        	public void mouseClicked(java.awt.event.MouseEvent e) { 
        		if(t2.getValueAt(t2.getSelectedRow(),0)!=null){
        			task = (String) t2.getValueAt(t2.getSelectedRow(), 0);
        		}else{
        			task = "";
        		}
        	}
        });
        
		sp2 = new JScrollPane(t2);
		sp2.setBounds(10, (int)panel_1.getHeight()+panel_1.getY()+10, (int)panel_1.getWidth(), panel_6.getY()-10-((int)panel_1.getHeight()+panel_1.getY()+5));
		sp2.getViewport().setBackground(Color.white);
		getContentPane().add(sp2);
				
		
		
		//p2面板
		/*p2.setBorder(BorderFactory.createTitledBorder("焊机"));
		p2.setBounds(0, 100, screensize.width, screensize.height-300);
		p2.setFont(new Font("Dialog",1,17));
		p2.setBackground(Color.white);*/
		//p2.setPreferredSize(screensize);
		//add(p2);
		
		//JList
		/*DefaultListModel defaultListModel  = new DefaultListModel();
		for(int i=0;i<listarray3.size();i+=2){
			String task = "    " + listarray3.get(i) + "   " + listarray3.get(i+1);
			defaultListModel .addElement(task);
		}
		j2 = new JList(defaultListModel);
		j2.setBounds(0, 0, screensize.width, screensize.height-300);
		j2.setFont(new Font("Dialog",1,20));
		j2.setFixedCellHeight(60);
		j2.setCellRenderer(new DefaultListCellRenderer() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.black);
                g.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
            }
        });
        
		//JList加滑动
		sp2 = new JScrollPane(j2);
		sp2.setBounds(0, 100, screensize.width, screensize.height-300);
		sp2.setBackground(Color.white);
		add(sp2);
		repaint();*/
		//p2.setLayout(null);
		
		
	}
	
	public static void setOneRowBackgroundColor(JTable table, final int rowIndex,
		      final Color color) {
		    try {
		      DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
		 
		        public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus,
		            int row, int column) {
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
		      int columnCount = table.getColumnCount();
		      for (int i = 0; i < columnCount; i++) {
		        table.getColumn(table.getColumnName(i)).setCellRenderer(tcr);
		      }
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		  }

	
}
