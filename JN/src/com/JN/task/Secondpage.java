package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
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

	private JPanel p1 = new JPanel();
	private JPanel panel_1;
	private JLabel l11 = new JLabel("焊工姓名：   ");
	private JLabel l12 = new JLabel("焊工编号：   ");
	private JLabel l15 = new JLabel("所在班组：   ");
	private JLabel l13 = new JLabel("焊机编号：   ");
	private JLabel l14 = new JLabel("任务编号：   ");
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
	
	private Firstpage firstpage;
	public String worktime;
	public String welder;
	public String weld;
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
	private final JLabel lblRenwu = new JLabel("任务列表");
	private final JPanel panel_4 = new JPanel();
	
	public Secondpage(String worktime1,String welder1,String weldernum1,String weldowner1, Dimension screensize1,ArrayList<String> listarray221, ArrayList<String> listarray222,ArrayList<String> listarray31, ArrayList<String> listarray41, Client client1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String welderid1){
		super("江南派工");
		lblRenwu.setFont(new Font("宋体", Font.BOLD, 20));
		
		Dimension screensize11 = Toolkit.getDefaultToolkit().getScreenSize();
		
		panel_3.setBounds((int)screensize11.getWidth()-400, 333, 390, 53);
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
		
		initframe(); //绘制界面
		time(); //电子时钟
		
		sd = this;
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
                l111.setText(time);
                repaint();
				
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
		getContentPane().setBackground(new Color(20,51,105));
		setVisible(true);
		
		try {
			//任务webservice
			String obj11 = "{\"CLASSNAME\":\"junctionWebServiceImpl\",\"METHOD\":\"getWeldedJunctionAll\"}";
			Object[] objects1 = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterNoParamWs"),
					new Object[] { obj11 });
			String restr1 = objects1[0].toString();
	        JSONArray ary1 = JSONArray.parseArray(restr1);
	        listarray4.clear();
	        for(int i=0;i<ary1.size();i++){
		        String str = ary1.getString(i);
		        JSONObject js = JSONObject.fromObject(str);
		        
		        if(js.getString("OPERATESTATUS").equals("0")){
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
		        listarray22.add(js.getString("MANUFACTURERNAME"));
		        listarray22.add(js.getString("INSFRAMEWORKNAME"));
		        listarray22.add(js.getString("POSITION"));
		        listarray22.add(js.getString("STATUS"));
		        listarraywe.add(js.getString("MACHINENO"));
		        listarraywe.add(js.getString("ID"));
	        }
	        
	        //筛选去除正在工作的焊机、故障焊机
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
	        
	        for(int j=0;j<listarray22.size();j+=5){
        		if(!listarray22.get(j+4).equals("31")){
        			listarray22.set(j, "");
        			listarray22.set(j+1, "");
        			listarray22.set(j+2, "");
        			listarray22.set(j+3, "");
        			listarray22.set(j+4, "");
        		}
        	}
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Dimension screensize1 = Toolkit.getDefaultToolkit().getScreenSize();
		p1.setBounds((int)screensize1.getWidth()-400, 148, 390, 178);
		
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
		l12.setBounds(14, 126, 200, 22);
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
		l14.setBounds(213, 126, 177, 22);
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
		ArrayList<String> listarraybuf1 = new ArrayList<String>();
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
		}
		
		//table列名以及值
		String[] cn = {"任务号", "指定焊工"};  
		Object[][] obj = new Object[listarray3.size()/2][2];  
		for(int i=0;i<listarraybuf2.size()/2;i++){
			for(int j=0;j<2;j++){
				obj[i][j] = listarraybuf2.get(i*2+j);
			}
		}
		//绘图
		t3 = new JTable(obj,cn){
			public boolean isCellEditable(int row, int column)
            {
               return false;//表格不允许被编辑
            }
		};
		t3.setFont(new Font("Dialog",1,15));
		
		//设置table宽高
		
		TableColumn column = null;  
        int colunms = t3.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = t3.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/ 
            if(i != 3){
            	column.setPreferredWidth((((int)panel_3.getWidth())-20)/2);
            }else{
            	column.setPreferredWidth(1630);
            }
        }  
        
        //居中显示，关闭自动编辑
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        t3.setDefaultRenderer(Object.class, r);
        t3.setRowHeight(50);
        t3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
		
        Dimension screensize11 = Toolkit.getDefaultToolkit().getScreenSize();
        
		sp3 = new JScrollPane(t3);
		sp3.setBounds((int)screensize11.getWidth()-400, 393, 390, (int)screensize11.getHeight()-393-90);
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
							if(listarray22.get(i+1).equals("OTC")){
								img = new ImageIcon(getClass().getResource("/images/OTC.png"));
								l21.setIcon(img);
							}else if(listarray22.get(i+1).equals("米勒")){
								img = new ImageIcon(getClass().getResource("/images/MILLER.png"));
								l21.setIcon(img);
							}else if(listarray22.get(i+1).equals("威特力")){
								img = new ImageIcon(getClass().getResource("/images/WTL.png"));
								l21.setIcon(img);
							}else if(listarray22.get(i+1).equals("伊萨")){
								img = new ImageIcon(getClass().getResource("/images/ESAB.png"));
								l21.setIcon(img);
							}
							l21.addMouseListener(new MouseListener(){
	
								//图片点击监听
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
									weld = labelname;
									
									String weldid = "";
									weld = labelname;
									for(int i=0;i<listarraywe.size();i+=2){
										if(weld.equals(listarraywe.get(i))){
											weldid = listarraywe.get(i+1);
										}
									}
									
									if(weldtype.equals("OTC")){
										img = new ImageIcon(getClass().getResource("/images/OTC.png"));
										l21.setIcon(img);
									}else if(weldtype.equals("米勒")){
										img = new ImageIcon(getClass().getResource("/images/MILLER.png"));
										l21.setIcon(img);
									}else if(weldtype.equals("威特力")){
										img = new ImageIcon(getClass().getResource("/images/WTL.png"));
										l21.setIcon(img);
									}else if(weldtype.equals("伊萨")){
										img = new ImageIcon(getClass().getResource("/images/ESAB.png"));
										l21.setIcon(img);
									}
									
									//弹窗
									/*int j = JOptionPane.showConfirmDialog(null, "是否选择" + weld + " 号焊机", "确认",JOptionPane.YES_NO_OPTION);
									//JOptionPane.showMessageDialog(null, "确认选择" + weld + "号焊机" + "执行" + task + "任务?", "  确认",JOptionPane.INFORMATION_MESSAGE);
									if(j==0){
										
										l13.setText("焊机编号:  " + labelname);
										
										//开启选择任务视窗
										new Thirdpage(worktime,welder,weldernum,weldowner,weld,screensize,listarray21,listarray22,listarray3,listarray4,img);
										
										//关闭当前视窗
										setVisible(false);
									}*/
									
									JWeldButton jb = new JWeldButton(weldposition,weldtype,weld,img,sd,listarraywe,listarrayta,weldid,welderid);
									jb.setVisible(true);
									
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
		b4.setBounds(((int)screensize11.getWidth()-120)/2, 18, 125, 40);
		panel_4.setBackground(new Color(20,51,105));
		panel_4.add(b4);
		b4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Firstpage();
				
				setVisible(false);
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
		
		JLabel label_1 = new JLabel("焊机类型：   ");
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
						if(listarray22.get(i+1).equals("OTC")){
							img = new ImageIcon(getClass().getResource("/images/OTC.png"));
							l21.setIcon(img);
						}else if(listarray22.get(i+1).equals("米勒")){
							img = new ImageIcon(getClass().getResource("/images/MILLER.png"));
							l21.setIcon(img);
						}else if(listarray22.get(i+1).equals("威特力")){
							img = new ImageIcon(getClass().getResource("/images/WTL.png"));
							l21.setIcon(img);
						}else if(listarray22.get(i+1).equals("伊萨")){
							img = new ImageIcon(getClass().getResource("/images/ESAB.png"));
							l21.setIcon(img);
						}
						
						l21.addMouseListener(new MouseListener(){
	
							//图片点击监听
							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								
								String weldid = "";
								weld = labelname;
								for(int i=0;i<listarraywe.size();i+=2){
									if(weld.equals(listarraywe.get(i))){
										weldid = listarraywe.get(i+1);
									}
								}
								
								if(weldtype.equals("OTC")){
									img = new ImageIcon(getClass().getResource("/images/OTC.png"));
									l21.setIcon(img);
								}else if(weldtype.equals("米勒")){
									img = new ImageIcon(getClass().getResource("/images/MILLER.png"));
									l21.setIcon(img);
								}else if(weldtype.equals("威特力")){
									img = new ImageIcon(getClass().getResource("/images/WTL.png"));
									l21.setIcon(img);
								}else if(weldtype.equals("伊萨")){
									img = new ImageIcon(getClass().getResource("/images/ESAB.png"));
									l21.setIcon(img);
								}
								
								//弹窗
								/*int j = JOptionPane.showConfirmDialog(null, "是否选择" + weld + " 号焊机", "确认",JOptionPane.YES_NO_OPTION);
								//JOptionPane.showMessageDialog(null, "确认选择" + weld + "号焊机" + "执行" + task + "任务?", "  确认",JOptionPane.INFORMATION_MESSAGE);
								if(j==0){
									
									l13.setText("焊机编号:  " + labelname);
									
									//开启选择任务视窗
									new Thirdpage(worktime,welder,weldernum,weldowner,weld,screensize,listarray21,listarray22,listarray3,listarray4,img);
									
									//关闭当前视窗
									setVisible(false);
								}*/
								
								JWeldButton jb = new JWeldButton(weldposition,weldtype,weld,img,sd,listarraywe,listarrayta,weldid,welderid);
								jb.setVisible(true);
								
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
}
