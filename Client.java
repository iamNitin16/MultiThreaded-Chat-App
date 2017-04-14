/*	Client.java is java side script with is used to connect to server and chat with anyone.
	written by :- Nitin Sharma
	created on :- 13/04/17
*/

import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

class Client extends JFrame implements ActionListener{
	/*GUI for Client*/
	private JLabel l_server_ip,l_server_port,l_username,l_message;
	private JTextField txt_server_ip,txt_server_port,txt_username,txt_message;
	private JList chat_box,online_users;
	private JButton send_message,connect;
	private Socket socket=null;
	private DataInputStream din;
	private DataOutputStream dout;
	private JOptionPane joptionpane;
	private DefaultListModel<String> messages;

	Client(){
		messages = new DefaultListModel<>();

		l_server_ip = new JLabel("Server IP :- ");
		l_server_ip.setBounds(50,20,100,30);

		txt_server_ip = new JTextField();
		txt_server_ip.setBounds(160,20,200,30);

		l_server_port = new JLabel("Server Port :- ");
		l_server_port.setBounds(400,20,100,30);

		txt_server_port = new JTextField();
		txt_server_port.setBounds(510,20,200,30);

		connect = new JButton("Connect");
		connect.setBounds(750,20,100,30);
		connect.addActionListener(this);

		l_username = new JLabel("Username :- ");
		l_username.setBounds(210,80,100,30);

		txt_username = new JTextField();
		txt_username.setBounds(330,80,200,30);

		chat_box = new JList(messages);
		chat_box.setBounds(100,150,500,400);
		// chat_box.setEditable(false);

		online_users = new JList();
		online_users.setBounds(700,150,200,400);

		l_message = new JLabel("Message :- ");
		l_message.setBounds(150,600,100,30);

		txt_message = new JTextField();
		txt_message.setBounds(260,600,450,30);

		send_message = new JButton("Send");
		send_message.setBounds(740,600,100,30);
		send_message.addActionListener(this);
		send_message.setEnabled(false);

		//init JFrame
		setVisible(true);
		setTitle("MultiThreaded-Chat-App");
		setLayout(null);
		setSize(1000,700);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(l_server_ip);
		add(txt_server_ip);
		add(l_server_port);
		add(txt_server_port);
		add(l_username);
		add(txt_username);
		add(chat_box);
		add(online_users);
		add(l_message);
		add(txt_message);
		add(send_message);
		add(connect);
	}

	public boolean isConnected(){
		if(socket == null){
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent ae){
		if(send_message == ae.getSource()){
			String message = txt_message.getText();
			try{
				dout.writeUTF(message);
				txt_message.setText(null);
				messages.addElement("me :- "+message);
				dout.flush();
			}catch(Exception e){
				System.out.println(e);
			}
		}
		else if(connect == ae.getSource()){
			String host_ip = txt_server_ip.getText();
			int host_port = Integer.parseInt(txt_server_port.getText());
			try{
				socket = new Socket(host_ip,host_port);
				din = new DataInputStream(socket.getInputStream());
				dout = new DataOutputStream(socket.getOutputStream());
			}catch(Exception e){
				System.out.println(e);
			}
			if(isConnected()){
				System.out.println("Connected");
				send_message.setEnabled(true);
			}
			else{
				System.out.println("Not Connected");
				joptionpane.showMessageDialog(null, "Unable to Connect...please check your server ip and port and try again......");
			}
		}
	}

	public static void main(String args[]){
		Client obj = new Client();
	}
}