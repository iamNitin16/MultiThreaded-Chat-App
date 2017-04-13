/*	MultiThreaded Server file to run multi servers on different threads.
	written by :- Nitin Sharma
	created on :- 13/04/17
*/

import java.net.*;
import java.io.*;

class ThreadedServer{
	public static void main(String args[]){
		try{
			ServerSocket ss = new ServerSocket(12000);
			while(true){
				Socket s = ss.accept();
				Server server = new Server(s);
				server.start();
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}
}