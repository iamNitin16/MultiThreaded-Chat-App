/*	Client.java is java side script with is used to connect to server and chat with anyone.
	written by :- Nitin Sharma
	created on :- 13/04/17
*/

import java.io.*;
import java.net.*;

class Client{
	public static void main(String args[]) throws Exception{
		Socket s = new Socket("localhost",12000);
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str="",str2="";
		while(!str.equals("stop")){
			str=br.readLine();
			dout.writeUTF(str);
			dout.flush();
			str2=din.readUTF();
			System.out.println("server :- "+str2);
		}

		dout.close();
		s.close();
	}
}