package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

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

import org.apache.cxf.endpoint.Client;

public class Secondpage extends JFrame{

	private JPanel p1 = new JPanel();
	private JLabel l11 = new JLabel("焊工姓名：   ");
	private JLabel l12 = new JLabel("焊工编号：   ");
	private JLabel l15 = new JLabel("所在班组：   ");
	private JLabel l13 = new JLabel("焊机编号：   ");
	private JLabel l14 = new JLabel("任务编号：   ");
	private JLabel l21;
	private JLabel l22;
	private JLabel l31 = new JLabel();
	private JLabel l32 = new JLabel();
	private JComboBox jcb;
	private JPanel p2 = new JPanel();
	private JScrollPane sp2;
	private JTable t3 = new JTable();
	private JScrollPane sp3;
	private JButton b4 = new JButton("返回");
	
	private Firstpage firstpage;
	public String worktime;
	public String welder;
	public String weld;
	private Dimension screensize;
	public ArrayList<String> listarray21;
	public ArrayList<String> listarray22;
	public ArrayList<String> listarray3;
	public ArrayList<String> listarray4;
	public String weldernum;
	public String weldowner;
	public ImageIcon img;
	private Secondpage sd;
	public Client client;
	private ArrayList<String> listarraywe;
	private ArrayList<String> listarrayta;
	private String welderid;
	
	public Secondpage(String worktime1,String welder1,String weldernum1,String weldowner1, Dimension screensize1,ArrayList<String> listarray221, ArrayList<String> listarray222,ArrayList<String> listarray31, ArrayList<String> listarray41, Client client1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String welderid1){
		super("江南派工");
		setFont(new Font("Dialog",1,20)); //标题字体
		
		worktime = worktime1;
		welder = welder1;
		weldernum = weldernum1;
		weldowner = weldowner1;
		screensize = screensize1;
		listarray21 = listarray221;
		listarray22 = listarray222;
		listarray3 = listarray31;
		listarray4 = listarray41;
		client = client1;
		listarraywe = listarraywe1;
		listarrayta = listarrayta1;
		welderid = welderid1;
		
		initframe(); //绘制界面
		
		sd = this;
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
		p1.setBorder(BorderFactory.createTitledBorder("焊工信息"));
		p1.setBounds(0, 20, screensize.width-400, 60);
		p1.setFont(new Font("Dialog",1,17));
		p1.setBackground(Color.white);
		p1.setLayout(null);
		add(p1);
		
		//焊工姓名
		l11.setText("焊工姓名:  " + welder);
		l11.setFont(new Font("Dialog",1,17));
		l11.setForeground(Color.black);
		l11.setBounds(50, 22, 200, 22);
		p1.add(l11);
		
		//焊工编号
		l12.setText("焊工编号:  " + weldernum);
		l12.setFont(new Font("Dialog",1,17));
		l12.setForeground(Color.black);
		l12.setBounds(300, 22, 200, 22);
		p1.add(l12);
		
		//所在班组
		l15.setText("所在班组:  " + weldowner);
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
				arrString[i] = "  * 班组：" + arrString[i];
			}else{
				arrString[i] = "    班组：" + arrString[i];
			}
			
		}
		jcb = new JComboBox(arrString);
		jcb.setBackground(Color.white);
		jcb.setFont(new Font("Dialog",1,17));
		jcb.setBounds(screensize.width-350, 30, 300, 40);
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
				p2.setBorder(BorderFactory.createTitledBorder("可选焊机"));
				p2.setBounds(0, 100, screensize.width, screensize.height-300);
				p2.setFont(new Font("Dialog",1,20));
				p2.setBackground(Color.white);
				screensize.setSize(screensize.width, screensize.height);
				
				sp2 = new JScrollPane(p2);
				sp2.setBounds(0, 100, screensize.width+100, screensize.height-300);
				sp2.setBackground(Color.white);
				add(sp2);
				
				String[] a = jcb.getSelectedItem().toString().split("：");
				
				for(int i=0;i<listarray22.size();i+=4){
					String labelname = listarray22.get(i);
					String weldtype = listarray22.get(i+1);
					String weldposition = listarray22.get(i+3);
					JLabel l21 = new JLabel();
					JLabel l22 = new JLabel();
					l21.setBounds(50, 100, 200, 200);
					l22.setBounds(70, 400, 200, 200);
					
					if(listarray22.get(i+2).equals(a[1])){
						if(listarray22.get(i+1).equals("144")){
							img = new ImageIcon(getClass().getResource("/images/otc.jpg"));
							l21.setIcon(img);
						}else if(listarray22.get(i+1).equals("米勒")){
							img = new ImageIcon(getClass().getResource("/images/miller.jpg"));
							l21.setIcon(img);
						}else if(listarray22.get(i+1).equals("威特力")){
							img = new ImageIcon(getClass().getResource("/images/wtl.jpg"));
							l21.setIcon(img);
						}else{
							img = new ImageIcon(getClass().getResource("/images/esab.jpg"));
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
									img = new ImageIcon(getClass().getResource("/images/otc.jpg"));
									l21.setIcon(img);
								}else if(weldtype.equals("米勒")){
									img = new ImageIcon(getClass().getResource("/images/miller.jpg"));
									l21.setIcon(img);
								}else if(weldtype.equals("威特力")){
									img = new ImageIcon(getClass().getResource("/images/wtl.jpg"));
									l21.setIcon(img);
								}else{
									img = new ImageIcon(getClass().getResource("/images/esab.jpg"));
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
						
						l22.setText(labelname);
						
						p2.add(l21);
						p2.add(l22);
					}
				}
				
			
				sd.repaint();
			}
		});
		add(jcb);
		
		//加载焊机列表
		p2.setBorder(BorderFactory.createTitledBorder("可选焊机"));
		p2.setFont(new Font("Dialog",1,20));
		p2.setBackground(Color.white);
		screensize.setSize(screensize.width-400, screensize.height);
		p2.setPreferredSize(screensize);
		//add(p2);
		
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
			
			for(int i=0;i<listarray22.size();i+=4){
				String labelname = listarray22.get(i);
				String weldtype = listarray22.get(i+1);
				String weldposition = listarray22.get(i+3);
				l21 = new JLabel();
				l22 = new JLabel();
				l21.setBounds(50, 100, 200, 200);
				l22.setBounds(70, 400, 200, 200);
				
				if(listarray22.get(i+2).equals(weldowner)){
					if(listarray22.get(i+1).equals("OTC")){
						img = new ImageIcon(getClass().getResource("/images/otc.jpg"));
						l21.setIcon(img);
					}else if(listarray22.get(i+1).equals("米勒")){
						img = new ImageIcon(getClass().getResource("/images/miller.jpg"));
						l21.setIcon(img);
					}else if(listarray22.get(i+1).equals("威特力")){
						img = new ImageIcon(getClass().getResource("/images/wtl.jpg"));
						l21.setIcon(img);
					}else{
						img = new ImageIcon(getClass().getResource("/images/esab.jpg"));
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
								img = new ImageIcon(getClass().getResource("/images/otc.jpg"));
								l21.setIcon(img);
							}else if(weldtype.equals("米勒")){
								img = new ImageIcon(getClass().getResource("/images/miller.jpg"));
								l21.setIcon(img);
							}else if(weldtype.equals("威特力")){
								img = new ImageIcon(getClass().getResource("/images/wtl.jpg"));
								l21.setIcon(img);
							}else{
								img = new ImageIcon(getClass().getResource("/images/esab.jpg"));
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
					
					l22.setText(labelname);
					
					p2.add(l21);
					p2.add(l22);
				}
			}
			sp2 = new JScrollPane(p2);
			sp2.setBounds(0, 100, screensize.width+100, screensize.height-300);
			sp2.setBackground(Color.white);
			add(sp2);
		}
		
		//班组
		l31.setFont(new Font("Dialog",1,17));
		l31.setForeground(Color.black);
		l31.setBounds(1660, 110, 150, 20);
		l31.setText(weldowner);
		add(l31);
		
		//任务数
		int count = 0;
		l32.setFont(new Font("Dialog",1,17));
		l32.setForeground(Color.black);
		l32.setBounds(1790, 110, 70, 20);
		for(int i=0;i<listarray3.size();i+=4){
			if(listarray3.get(i+1).equals(weldowner) && ( listarray3.get(i+2).equals(welder) || listarray3.get(i+2).equals(""))){
				count++;
			}
		}
		l32.setText("任务数:"+count);
		add(l32);
		
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
            	column.setPreferredWidth(130);
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
		
		sp3 = new JScrollPane(t3);
		sp3.setBounds(1630, 150, 280, screensize.height-350);
		sp3.setBackground(Color.white);
		add(sp3);
		
		/*sp3.setBorder(BorderFactory.createTitledBorder("任务信息"));
		sp3.setBounds(1630, 100, 280, screensize.height-300);
		sp3.setFont(new Font("Dialog",1,17));
		sp3.setBackground(Color.white);
		sp3.setLayout(null);
		add(sp3);*/
		
		//返回按钮
		b4.setBounds((screensize.width+200)/2, screensize.height-140, 100, 50);
		b4.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new Firstpage();
				
				setVisible(false);
			}
		});
		add(b4);
		
	}
}
