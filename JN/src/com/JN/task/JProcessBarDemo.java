package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument.Content;
import javax.xml.namespace.QName;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import com.alibaba.fastjson.JSONArray;

import net.sf.json.JSONObject;

public class JProcessBarDemo extends JFrame{

	public JProgressBar jpb;
	public JButton b = new JButton();
	public Dimension screensize;
	private boolean cancel = false;
	private Thirdpage td;
	public String weldernum;
	public String task;
	public String weld;
	private Client client;
	
	public JLabel l11;
	public JLabel l13;
	public JLabel l14;
	private String weldid;
	private String welderid;
	private String taskid;
	
	public JProcessBarDemo(Dimension screensize1, Thirdpage td1, String weldid1, String welderid1, String taskid1){
		
		screensize = screensize1;
		td = td1;
		l11 = td1.l1111;
		l13 = td1.label_311;
		l14 = td1.label_9;
		weldernum = td1.weldernum;
		task = td1.task;
		weld = td1.weld;
		client = td1.client;
		weldid = weldid1;
		welderid = welderid1;
		taskid = taskid1;
		
		setTitle("确认中");		//设置窗体标题
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗体退出的操作
		setBounds((screensize.width-250)/2, (screensize.height-200)/2, 250, 180);// 设置窗体的位置和大小
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		setUndecorated(true);	
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		JPanel contentPane = new JPanel();   // 创建内容面板
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));// 设置内容面板边框
		setContentPane(contentPane);// 应用(使用)内容面板
		//contentPane.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));// 设置为流式布局
		//contentPane.setVisible(true);
		
		jpb = new JProgressBar();// 创建进度条
		jpb.setStringPainted(true);// 设置进度条上的字符串显示，false则不能显示
		jpb.setBackground(Color.white);
		
		/*b.setText("取消");
		b.setBounds(10, 10, 100, 10);
		b.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				cancel = true;
				setVisible(false);
			}
		});*/
		
		// 创建线程显示进度
		new Thread(){
			private String settime;

			public void run(){
				
				try {
					Date date = new Date();
	                String time = DateTools.format("yyyy-MM-dd HH:mm:ss",date);
	                
					String obj1 = "{\"CLASSNAME\":\"junctionWebServiceImpl\",\"METHOD\":\"giveToServer\"}";
					String obj2 = "{\"TASKNO\":\"" + task + "\",\"WELDERNO\":\"" + weldernum +"\",\"MACHINENO\":\"" + weld +"\",\"STATUS\":\"0\",\"TASKID\":\"" + taskid +"\",\"WELDERID\":\"" + welderid +"\",\"MACHINEID\":\"" + weldid +"\",\"STARTTIME\":\"" + time +"\"}";
				    Object[] objects = client.invoke(new QName("http://webservice.ssmcxf.sshome.com/", "enterTheWS"),
				          new Object[] { obj1,obj2 });
				    
				    try {
						  FileInputStream in = new FileInputStream("IPconfig.txt");  
				          InputStreamReader inReader = new InputStreamReader(in, "UTF-8");  
				          BufferedReader bufReader = new BufferedReader(inReader);  
				          String line = null; 
				          int writetime=0;
							
						    while((line = bufReader.readLine()) != null){ 
						    	if(writetime==0){
					                writetime++;
						    	}
						    	else{
						    		settime=line;
						    		writetime=0;
						    	}
				          }  

						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    
				    for (int i = 0; i < 101; i++) {
						try {
							Thread.sleep(Integer.valueOf(settime)*10);  //   让当前线程休眠
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						jpb.setValue(i);	// 设置进度条数值
					}
					
				    
				    String restr = objects[0].toString();
				    if(restr.equals("true")){
						jpb.setString("确认成功");// 设置提示信息
						try {
							Thread.sleep(1000);  //   让当前线程休眠0.1ms
						} catch (InterruptedException e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						setVisible(false);
						
						if(!cancel){
							new Firstpage();
							
							td.setVisible(false);
						}
				    }else if(restr.equals("false")){
				    	jpb.setString("失败，请取消重试");// 设置提示信息
				    }
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}.start(); //  启动进度条线程
		
		contentPane.add(l11);
		contentPane.add(l13);
		contentPane.add(l14);
		contentPane.add(jpb);// 向面板添加进度控件
		//contentPane.add(b);
	}
}
