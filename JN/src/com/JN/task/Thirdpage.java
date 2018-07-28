package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

	private JPanel p1 = new JPanel();
	public JLabel l11 = new JLabel("焊工姓名：   ");
	private JLabel l12 = new JLabel("焊工编号：   ");
	private JLabel l15 = new JLabel("所在班组：   ");
	public JLabel l13 = new JLabel("焊机编号：   ");
	public JLabel l14 = new JLabel("任务编号：   ");
	private JLabel l16 = new JLabel();
	private JScrollPane sp2;
	private JList j2 = new JList();
	private JTable t2 = new JTable();
	private JButton b31 = new JButton("确认");
	private JButton b32 = new JButton("返回");
	private ImageIcon img;
	private Thirdpage td;
	
	private IsnullUtil iutil;
	private JaxWsDynamicClientFactory dcf;
	public Client client;
	private String weldid;
	private String welderid;
	private ArrayList<String> listarrayta;
	private ArrayList<String> listarraywe;
	
	public Thirdpage(String worktime1, String welder1, String weldernum1, String weldowner1, String weld1, Dimension screensize1, ArrayList<String> listarray221, ArrayList<String> listarray222, ArrayList<String> listarray31, ArrayList<String> listarray41, ImageIcon img1, Client client1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String weldid1, String welderid1){
		super("江南派工");
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
		
		initframe(); //绘制界面
		
		td = this;
	}

	private void initframe() {
		// TODO Auto-generated method stub
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		setLayout(null);
		setVisible(true);
		
		//加载状态信息栏
		p1.setBorder(BorderFactory.createTitledBorder("信息"));
		p1.setBounds(0, 20, screensize.width, 200);
		p1.setFont(new Font("Dialog",1,17));
		p1.setBackground(Color.white);
		p1.setLayout(null);
		add(p1);
		
		//焊工姓名
		l11.setText("焊工姓名:  " + welder);
		l11.setFont(new Font("Dialog",1,17));
		l11.setForeground(Color.black);
		l11.setBounds(50, 35, 200, 22);
		p1.add(l11);
		
		//焊工编号
		l12.setText("焊工编号:  " + weldernum);
		l12.setFont(new Font("Dialog",1,17));
		l12.setForeground(Color.black);
		l12.setBounds(300, 35, 200, 22);
		p1.add(l12);
		
		//所在班组
		l15.setText("所在班组:  " + weldowner);
		l15.setFont(new Font("Dialog",1,17));
		l15.setForeground(Color.black);
		l15.setBounds(50, 110, 200, 22);
		p1.add(l15);
		
		//焊机编号
		l13.setText("焊机编号:  " + weld);
		l13.setFont(new Font("Dialog",1,17));
		l13.setForeground(Color.black);
		l13.setBounds(600, 35, 200, 22);
		p1.add(l13);
		
		//任务编号
		l14.setFont(new Font("Dialog",1,17));
		l14.setForeground(Color.black);
		l14.setBounds(300, 110, 200, 22);
		p1.add(l14);
		
		//焊机图片
		l16.setBounds(800, 0, 200, 200);
		l16.setIcon(img);
		p1.add(l16);
		
		//table列名以及值
		int count = 0;
		String[] cn = {"任务号", "指定班组", "指定焊工","任务描述"};  
		Object[][] obj = new Object[listarray3.size()/4][4];  
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
		for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner) && ( listarray3.get(i+2).equals(welder) || listarray3.get(i+2).equals(""))){
				if(listarray3.get(i+2).equals(welder)){
					listarraybuf1.add(" * " + listarray3.get(i));
					listarraybuf1.add(" * " + listarray3.get(i+1));
					listarraybuf1.add(" * " + listarray3.get(i+2));
					listarraybuf1.add(" * " + listarray3.get(i+3));
				}else{
					listarraybuf2.add(listarray3.get(i));
					listarraybuf2.add(listarray3.get(i+1));
					listarraybuf2.add(listarray3.get(i+2));
					listarraybuf2.add(listarray3.get(i+3));
				}
			}
		}
		
		for(int i=0;i<listarraybuf2.size();i+=4){
			listarraybuf1.add(listarraybuf2.get(i));
			listarraybuf1.add(listarraybuf2.get(i+1));
			listarraybuf1.add(listarraybuf2.get(i+2));
			listarraybuf1.add(listarraybuf2.get(i+3));
		}
		
		for(int i=0;i<listarraybuf1.size();i+=4){
			for(int j=0;j<4;j++){
				obj[count][j] = listarraybuf1.get(i+j);
			}
			count++;
		}
		
		//绘图
		t2 = new JTable(obj,cn){
			public boolean isCellEditable(int row, int column)
            {
                   return false;//表格不允许被编辑
            }
		};
		t2.setFont(new Font("Dialog",1,15));
		
		//设置table宽高
		TableColumn column = null;  
        int colunms = t2.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = t2.getColumnModel().getColumn(i);  
            /*将每一列的默认宽度设置为100*/ 
            if(i != 3){
            	column.setPreferredWidth(90);
            }else{
            	column.setPreferredWidth(1630);
            }
        }  
        
        //居中显示，关闭自动编辑
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();   
        r.setHorizontalAlignment(JLabel.CENTER);   
        t2.setDefaultRenderer(Object.class, r);
        t2.setRowHeight(50);
        t2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
		
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
		sp2.setBounds(0, 230, screensize.width, screensize.height-350);
		sp2.setBackground(Color.white);
		add(sp2);
		
		//确认任务
		b31.setBounds((screensize.width-300)/2, screensize.height-90, 100, 50);
		b31.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(task != ""){
					
					/*l14.setText("任务编号:  " + task);
					JOptionPane.showMessageDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.INFORMATION_MESSAGE);*/
					
					String taskid = "";
					if(task.length()!=8){
						task = task.substring(3,11);
					}
					for(int i=0;i<listarrayta.size();i+=2){
						if(task.equals(listarrayta.get(i))){
							taskid = listarrayta.get(i+1);
							break;
						}
					}
					
					l14.setText("任务编号:  " + task);
					int i = JOptionPane.showConfirmDialog(null, "焊工：" + welder + "\n焊机：" + weld + "\n任务：" + task, "确认",JOptionPane.YES_NO_OPTION);
					if(i!=0){
						l14.setText("任务编号:  ");
					}else{
						
						JProcessBarDemo jpd = new JProcessBarDemo(screensize,td,weldid,welderid,taskid);
						jpd.setVisible(true);
						
						//JOptionPane.showMessageDialog(null, "领取成功,请尽快完成任务", "确认",JOptionPane.INFORMATION_MESSAGE);
						//new Firstpage();
					}
					
					//JOptionPane.showMessageDialog(null, "请稍候...", "",JOptionPane.INFORMATION_MESSAGE);
					
				}else{
					JOptionPane.showMessageDialog(null, "无选择任务.", "  错误",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		add(b31);
		
		//返回
		b32.setBounds((screensize.width+100)/2, screensize.height-90, 100, 50);
		b32.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//开启新视窗选择焊机
				new Secondpage(worktime,welder,weldernum,weldowner,screensize,listarray21,listarray22,listarray3,listarray4, client,listarraywe,listarrayta,welderid);
				
				setVisible(false);
			}
		});
		add(b32);
		
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
}
