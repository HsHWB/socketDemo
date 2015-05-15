package socket;

import java.awt.image.SinglePixelPackedSampleModel;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import Utils.Constans;

public class SocketServer{
	public static void main(String args[]){
		BufferedReader br; 
		PrintWriter pw;
		BufferedReader myIn;
		try{
			ServerSocket server = null;
			try{
				
				server = new ServerSocket(Constans.SERVER_PORT);//构造serversocket 使用端口为4700
				
			}catch(Exception e){
				
				System.out.println("无法监听port:"+Constans.SERVER_PORT);
				
			}
			
			Socket socket = null;
			//ServerSocket(int port, int backlog, InetAddress bindAddr);
			//Socket(String host, int port, InetAddress localAddr, int localPort)
			//其中address、host和port分别是双向连接中另一方的IP地址、主机名和端 口号，stream指明socket是流socket还是数据报socket
			//，localPort表示本地主机的端口号，localAddr和 bindAddr是本地机器的地址（ServerSocket的主机地址），impl是socket的父类，
			//既可以用来创建serverSocket又可 以用来创建Socket。count则表示服务端所能支持的最大连接数。
			try {
				
				socket = server.accept();//到这里阻塞状态
				//accept，当有客户请求的时候，产生一个socket对象(获得客户的socket对象)，并继续执行
//				BufferedReader myReader = new BufferedReader(new InputStreamReader(System.in));
//				if ( myReader.equals("end") ) {
//					System.out.println("关闭socket");
//					socket.close();//关闭socket
//					server.close();//关闭ServerSocket
//					System.out.println("socket over");
//				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			String line;
			System.out.println("888");
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//由Socket对象得到输入流，并构造相应的BufferedReader对象
			pw = new PrintWriter(socket.getOutputStream());
			//由Socket对象得到输出流，并构造PrintWritter对象（用于返回数据给客户？）
			myIn = new BufferedReader(new InputStreamReader(System.in));
			//由系统标准输入设备构造BR对象
			System.out.println("SocketServer   Client:  "+br.readLine());
			//在标准输出上打印从客户端读入的字符串
			line = myIn.readLine();
			//在标准输入读入字符串
			
			while (!line.equals("bye")) {
				//如果读到bye则停止
				pw.println(line);
				//向用户输出该字符串
				pw.flush();
				//刷新输出流，使客户马上收到该字符串
				System.out.println("SocketServer   Server:   "+line);
				//在系统标准输出上打印读入的字符串
				System.out.println("SocketServer   Client:   "+br.readLine());
				//从客户读入一字符串，并打印到标准输出上
				line = myIn.readLine();
				//从系统标准输入读入一字符串
			}
			
			pw.close();//关闭socket输出流
			br.close();//关闭Socket输入流
			socket.close();//关闭socket
			server.close();//关闭ServerSocket
			
		}catch(Exception e){
			
			System.out.println("Error : "+e);
			
		}
	}
}