package com.JN.task;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;

import org.java_websocket.WebSocket.READYSTATE;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

public class Clientconnect2{  
  private EventLoopGroup loop = new NioEventLoopGroup();
  private String ip;
  private String fitemid; 
  public Bootstrap bootstrap = new Bootstrap();
  private Firstpage fp;
  private Secondpage sp;
private int page;
  public static WebSocketClient client;


  public Clientconnect2(Firstpage firstpage,Secondpage secondpage) {
	// TODO Auto-generated constructor stub
	  fp = firstpage;
	  sp = secondpage;
  }
  
  public Clientconnect2(Secondpage secondpage,int i) {
	// TODO Auto-generated constructor stub
	  sp = secondpage;
	  page = i;
  }

public Clientconnect2(Firstpage firstpage, int i) {
	// TODO Auto-generated constructor stub
	  fp = firstpage;
	  page = i;
}

public void run() {  
	//读取IPconfig配置文件获取ip地址和数据库配置
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
	  
	  try {
			client = new WebSocketClient(new URI("ws://"+ip+":5550")) {

		        @Override
		        public void onOpen(ServerHandshake arg0) {
		        }

		        @Override
		        public void onMessage(String arg0) {
 		        	if(page == 1){
			        	fp.websocdata(arg0);
		        	}else if(page == 2){
			        	sp.websocketdata(arg0);
		        	}
		            System.out.println("page2:"+arg0);
		        }

		        @Override
		        public void onError(Exception arg0) {
		            arg0.printStackTrace();
		            System.out.println("发生错误已关闭");
		        }

		        @Override
		        public void onClose(int arg0, String arg1, boolean arg2) {
		            System.out.println("链接已关闭");
		        }

		        @Override
		        public void onMessage(ByteBuffer bytes) {
		            try {
		                System.out.println(new String(bytes.array(),"utf-8"));
		            } catch (UnsupportedEncodingException e) {
		                e.printStackTrace();
		            }
		        }


		    };
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    client.connect();
  	}  
}
