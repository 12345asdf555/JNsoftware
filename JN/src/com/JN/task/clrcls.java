package com.JN.task;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JTextField;

import com.srplab.www.starcore.*;


class MyTask extends TimerTask{
	
	static StarCoreFactory starcore;
	static StarServiceClass Service;
	static StarSrvGroupClass SrvGroup;
	static StarObjectClass Class1;
	private Firstpage fp;
	public MyTask()
	{
		 starcore= StarCoreFactory.GetFactory();  
         Service=starcore._InitSimple("test","123",0,0);  
         SrvGroup = (StarSrvGroupClass)Service._Get("_ServiceGroup"); 
 		SrvGroup._InitRaw("csharp4",Service);
		
 		SrvGroup._LoadRawModule("csharp4","testcs","Test.dll",false);
 		
 	
 		
 		Class1 = Service._ImportRawContext("csharp4","testcs.Class1",true,"");
	}
  public MyTask(Firstpage fp) {
	// TODO 自动生成的构造函数存根
	  this.fp = fp;
	  starcore= StarCoreFactory.GetFactory();  
         Service=starcore._InitSimple("test","123",0,0);  
         SrvGroup = (StarSrvGroupClass)Service._Get("_ServiceGroup"); 
 		SrvGroup._InitRaw("csharp4",Service);
		
 		SrvGroup._LoadRawModule("csharp4","testcs","Test.dll",false);
 		
 	
 		
 		Class1 = Service._ImportRawContext("csharp4","testcs.Class1",true,"");
	}
	protected void finalize()

      {
		  SrvGroup._ClearService();
		   starcore._ModuleExit();
        //super.finalize();

        // other finalization code...

      }
  
	static String name="";
    @Override
    public void run() {
    	
    	System.out.print("time in");
    	
    	
    	///*
     
		
        
			

		
		
		
		String Result ="";
		
	
		
		
		StarObjectClass inst = Class1._New("","","cle value",44); 
	
		
	
		String empno1=(String)inst._Call("testfun"); 
		
		inst._Dispose();
		
		System.out.println(empno1);
		if((empno1!=null)&&(!empno1.equals("NULL"))){
			fp.textField.setText(empno1);
		}
		
	
		//
      
    }
}
/*public class clrcls {

	
	
	public static void main(String[] args) {
		
		
		Timer t=new Timer();
        //在3秒后执行MyTask类中的run方法
		t.scheduleAtFixedRate(new MyTask(), 3000, 30000);
        //t.schedule(new MyTask(), 3000);
       
       System.out.print("Game over");

	}

}*/
