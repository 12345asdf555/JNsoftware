package com.JN.task;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.border.EmptyBorder;

public class JWeldButton extends JFrame{


	private String weldposition;
	private String weldtype;
	private String weld;
	private ImageIcon img;
	
	private JLabel l11 = new JLabel();
	private JLabel l12 = new JLabel();
	private JLabel l13 = new JLabel();
	private JLabel l14 = new JLabel();
	private JButton b4 = new JButton("确认");
	private JButton b5 = new JButton("返回");
	private JWeldButton jb;
	private Secondpage sd;
	private ArrayList<String> listarraywe;
	private ArrayList<String> listarrayta;
	private String weldid;
	private String welderid;

	public JWeldButton(String weldposition1,String weldtype1,String weld1,ImageIcon img1, Secondpage sd1, ArrayList<String> listarraywe1, ArrayList<String> listarrayta1, String weldid1, String welderid1){
		
		jb = this;
		sd = sd1;
		
		weldposition = weldposition1;
		weldtype = weldtype1;
		weld = weld1;
		img = img1;
		listarraywe = listarraywe1;
		listarrayta = listarrayta1;
		weldid = weldid1;
		welderid = welderid1;
		
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		
		setTitle("确认焊机");
		setBounds((screensize.width-270)/2, (screensize.height-380)/2-100, 270, 380);// 设置窗体的位置和大小
		setUndecorated(true);
		this.getRootPane().setWindowDecorationStyle(JRootPane.INFORMATION_DIALOG);
		this.setBackground(Color.white);
		
		JPanel contentPane = new JPanel();   // 创建内容面板
		contentPane.setBorder(new EmptyBorder(25, 25, 25, 25));// 设置内容面板边框
		setContentPane(contentPane);// 应用(使用)内容面板
		
		l11.setText("焊机编号:  " + weld);
		l11.setFont(new Font("Dialog",1,17));
		l11.setForeground(Color.black);
		contentPane.add(l11);

		l12.setText("焊机类型:  " + weldtype);
		l12.setFont(new Font("Dialog",1,17));
		l12.setForeground(Color.black);
		contentPane.add(l12);
		
		l13.setText("焊机位置:  " + weldposition);
		l13.setFont(new Font("Dialog",1,17));
		l13.setForeground(Color.black);
		contentPane.add(l13);
		
		l14.setIcon(img);
		contentPane.add(l14);
		
		b4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//开启选择任务视窗
				new Thirdpage(sd.worktime,sd.welder,sd.weldernum,sd.weldowner,weld,screensize,sd.listarray21,sd.listarray22,sd.listarray3,sd.listarray4,img,sd.client,listarraywe,listarrayta,weldid,welderid);
				
				jb.setVisible(false);
				sd.setVisible(false);
			}
		});
		contentPane.add(b4);
		
		b5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jb.setVisible(false);
			}
		});
		contentPane.add(b5);
		
	}
}
