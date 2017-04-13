/*	Server file which accept connection of multiple users by threading.
	written by :- Nitin Sharma
	created on :- 13/04/17
*/
import java.io.*;
import java.net.*;

class Server extends Thread{
	private Socket connection = null;
	public Server(Socket connection){
		this.connection = connection;
	}

	public void run(){
		try{
			DataInputStream din = new DataInputStream(connection.getInputStream());
			DataOutputStream dout = new DataOutputStream(connection.getOutputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String str="",str2="";
			while(!str.equals("stop")){
				str=din.readUTF();
				System.out.println(Thread.currentThread().getName()+" :- "+str);
				str2=br.readLine();
				dout.writeUTF(str2);
				dout.flush();
			}

			din.close();
			connection.close();
		}catch(Exception e){
			System.out.println(e);
		}
	}
}