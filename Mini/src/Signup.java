import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Signup extends JFrame implements ActionListener  
{
  JLabel Username,pwd,q,ans;
  JPasswordField t2;
  TextField t1,t3;
  JButton submit,back;
  JLabel background=new JLabel(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\watermark.jpg"));
  String[] ques= {"What is your Fav Color","First School","Which is your fav car"};
  JComboBox<String> que;
  Signup()
  {
	  setTitle("Sign Up Page");
	  addWindowListener(new Win(this));
	  add(background);
		  
	  back=new JButton("Back");
	  back.setBounds(20,10,70,20);
	  
	  Username=new JLabel("Login Id   :");
	  Username.setFont(new Font("TimesRoman",Font.BOLD,20));
	  pwd=new JLabel("Password :");
	  pwd.setFont(new Font("TimesRoman",Font.BOLD,20));
	  Username.setBounds(130,80,100,20);
	  pwd.setBounds(130,110,100,20);
	  
	  t1=new TextField();
	  t1.setBounds(230, 80, 100, 20);
	  t2=new JPasswordField();
	  t2.setBounds(230, 110, 100, 20);
		  
	  submit=new JButton("Submit");
	  submit.setBounds(200, 280, 90, 30);
	  que=new JComboBox<String>(ques);
	  q=new JLabel("Select Your question");
	  q.setBounds(130,145,200,20);
	  q.setFont(new Font("TimesRoman",Font.BOLD,20));
	  que.setBounds(130,170,220,30);
	  t3=new TextField();
	  t3.setBounds(130,232,220,25);
	  ans=new JLabel("Your Answer");
	  ans.setFont(new Font("TimesRoman",Font.BOLD,20));
	  ans.setBounds(130,200,220,30);
	  
	  
	  background.add(back);
	  background.add(Username);
	  background.add(pwd);
	  background.add(t1);
	  background.add(t2);
	  background.add(submit);
	  background.add(que);
	  background.add(q);
	  background.add(t3);
	  background.add(ans);
	  submit.addActionListener(this);
	  back.addActionListener(this);
  }
  
  
  
  void check(String t1,String t2,String ans,String que)
  {
	  String url="jdbc:mysql://localhost:3306/Mall_DB";
	  String username= "root";
	  String pwd="root";
	  Connection con;
	  
	  try 
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection(url,username,pwd);
		  Statement st=con.createStatement();
		  String query="select * from login where loginid='"+t1+"'";
		  ResultSet rs=st.executeQuery(query);
		  if(rs.next())
		  {
			  JOptionPane.showMessageDialog(this,"Username already Exist");
		  }
		  else
		  {
			JOptionPane.showMessageDialog(this,"Account Created");
			insert(t1,t2,ans,que);
			Login l=new Login();
			l.setLocation(540,200);
			l.setSize(500,500);
			l.setVisible(true);
			this.setVisible(false);
			
			System.out.println(que);
			     
			     
		  }
		  rs.close();
		  st.close();
		  con.close();
	  }
	  catch(Exception e) {}
	  
  }
  
  
  void insert(String t1,String t2,String ans,String que)
  {
	  String url="jdbc:mysql://localhost:3306/Mall_DB";
	  String username= "root";
	  String pwd="root";
	  Connection con;
	  
	  try 
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection(url,username,pwd);
		  Statement st=con.createStatement();
		  String query="insert into login values('"+t1+"','"+t2+"','"+ans+"','"+que+"')";
		  st.executeUpdate(query);
		  
		  st.close();
		  con.close();
	  }
	  catch(Exception e) {}
	  
  }

@Override
public void actionPerformed(ActionEvent e) 
  {
	// TODO Auto-generated method stub
	String str=e.getActionCommand();
	if(str.equals("Submit"))
	{
		String txt1=t1.getText();
		String txt2=t2.getText();
		String answer=t3.getText();
		String ques=que.getSelectedItem().toString();
		if(txt1.isEmpty()||txt2.isEmpty()||answer.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Insert all Fields");
		}
		else
			check(txt1,txt2,answer,ques);
	}
	if(str.equals("Back"))
	{
		dispose();
		Login l=new Login();
	     l.setLocation(540,200);
	     l.setSize(500,500); 
	     l.setVisible(true);
	}
}
	  
}
