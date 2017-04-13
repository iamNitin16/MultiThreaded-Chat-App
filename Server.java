/*	Server file which accept connection of multiple users by threading.
	written by :- Nitin Sharma
	created on :- 13/04/17
*/
import java.io.*;
import java.net.*;

class Server{
	public static void main(String args[]) throws Exception{
		ServerSocket ss = new ServerSocket(12000);
		Socket s = ss.accept();
		DataInputStream din = new DataInputStream(s.getInputStream());
		DataOutputStream dout = new DataOutputStream(s.getOutputStream());
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str="",str2="";
		while(!str.equals("stop")){
			str=din.readUTF();
			System.out.println("user :- "+str);
			// bw.newLine();
			str2=br.readLine();
			dout.writeUTF(str2);
			dout.flush();
		}

		din.close();
		s.close();
		ss.close();
	}
}