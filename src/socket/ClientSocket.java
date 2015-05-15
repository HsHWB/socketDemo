package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.Buffer;

import Utils.Constans;

public class ClientSocket {

	public static void main(String args[]){
		try {
			Socket socket = new Socket(Constans.SERVER_HOST, Constans.SERVER_PORT);
			//向本机发送请求
			
			BufferedReader myIn = new BufferedReader(new InputStreamReader(System.in));
			//从系统标准输入设备构造BR对象
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			//由socket对象得到输出流，并构造PrintWritter对象
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//从Socket对象得到输入流，并构造相应的br对象
			String readLine;
			System.out.println("666");
			readLine = myIn.readLine();//从系统标准输入读入的一字符串
			System.out.println("ClientSocket   Client: "+readLine);
			
			while (!readLine.equals("bye")) {
				
				pw.println(readLine);
				//把客户输入的数据输出给Server
				pw.flush();
				//刷新输出流，使Server马上受到该字符串
				System.out.println("ClientSocket   Client: "+readLine);
				//在系统标准输出上打印输入的字符串
				System.out.println("ClientSocket   Server: "+br.readLine());
				//从Server读入一字符串，并打印到标准输出上
				readLine = myIn.readLine();//从系统标准输入读入一字符串
			
			}
			
			pw.close();
			br.close();
			socket.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in ClientSocket  :"+e);
		}
	}
	
	
}
