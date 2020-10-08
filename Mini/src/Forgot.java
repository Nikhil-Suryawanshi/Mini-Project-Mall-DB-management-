import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Forgot extends JFrame implements ActionListener,KeyListener
{
  JLabel Username,pwd,ans;
  JPasswordField t2;
  TextField t1,t3;
  JTextPane q;
  JButton submit,check,back;
  JLabel background=new JLabel(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\watermark.jpg"));
  String[] ques= {"What is your Fav Color","First School","What is your oldest cousin’s first name"};
  JComboBox<String> que;
  Forgot()
  {
	  setTitle("Forgot Password");
	  addWindowListener(new Win(this));
	  add(background);
		  
	  back=new JButton("Back");
	  back.setBounds(20,10,70,20);
	  
	  Username=new JLabel("Login Id   :");
	  Username.setFont(new Font("TimesRoman",Font.BOLD,20));
	  Username.setBounds(130,80,100,20);
	  
	  
	  t1=new TextField();
	  t1.setBounds(230, 80, 100, 20);
	    
	  submit=new JButton("Submit");
	  submit.setBounds(200, 280+50, 90, 30);
	  t3 =new TextField();
	  t3.setBounds(130,140+50,150,20);
	  check=new JButton("Check");
	  check.setBounds(130,170+50,90,30);
	  
	  
	  pwd =new JLabel("Enter new password");
	  pwd.setBounds(130,200+50,200,20);
	  pwd.setFont(new Font("TimesRoman",Font.BOLD,20));
	  t2=new JPasswordField();
	  t2.setBounds(130,230+50,150,20);
	  
	  q=new JTextPane();
	  q.setBounds(130,110,200,55);
	  q.setFont(new Font("TimesRoman",Font.BOLD,20));
	  q.enable(false);
	  background.add(q);
	  
	  background.add(back);
	  background.add(pwd);
	  background.add(t2);
	  background.add(check);
	  background.add(t3);
	  background.add(Username);
	  background.add(t1);
	  background.add(submit);
	  t2.enable(false);
	  pwd.enable(false);
	  t3.enable(false);
	  check.enable(false);
	  submit.enable(false);
	  
	  submit.addActionListener(this);
	  t1.addKeyListener(this);
	  check.addActionListener(this);
	  back.addActionListener(this);
	  
	  
  }
  
  
  
  void check(String t1)
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
			  String que=rs.getString("que");
			  
			  q.setText(que);
			  q.enable(true);
			  
			  JOptionPane.showMessageDialog(this, "Type the Answer");
			  check.enable(true);
			  t3.enable(true);
		  }
		 
		  rs.close();
		  st.close();
		  con.close();
	  }
	  catch(Exception e) {}
	  
  }
  
  
  void update(String t,String t1)
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
		  String query="update login set pwd='"+t1+"' where loginid='"+t+"'";
		  st.execute(query);
		  System.out.println(t1+t);
		  
		  st.close();
		  con.close();
	  }
	  catch(Exception e) {}
	  
  }

  
   void check2(String t1)
   {
		  String url="jdbc:mysql://localhost:3306/Mall_DB";
		  String username= "root";
		  String pd="root";
		  Connection con;
		  
		  try 
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection(url,username,pd);
			  Statement st=con.createStatement();
			  String query="Select * from login where loginid='"+t1+"'";
			  ResultSet rs =st.executeQuery(query);
			  if(rs.next())
			  {
				  String check=rs.getString("ans");
				  if(check.equals(t3.getText()))
				  {
					  t2.enable(true);
					  pwd.enable(true);
					  submit.enable(true);
					  JOptionPane.showMessageDialog(this, "Enter new Password");
					  
					  System.out.println(check);
				  }
				  else 
				  {
					  JOptionPane.showMessageDialog(this, "Wrong Answer");
					  System.out.println(check);
				  }
			  }
			  rs.close();
			  st.close();
			  con.close();
		  }
		  catch(Exception e) {}
	   
   }
   
   
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	String str=e.getActionCommand();
	String txt1=t1.getText();
	if(str.equals("Submit"))
	{
		
		String txt2=t3.getText();
		String txt3=t2.getText();
		
		if(txt3.isEmpty())
		{
			JOptionPane.showMessageDialog(this,"Insert all Fields");
		}
		else
		{
			update(txt1,txt3);
			dispose();
			Login l=new Login();
			l.setLocation(540,200);
		    l.setSize(500,500); 
		    l.setVisible(true);
		}
	}
	if(str.equals("Check"))
	{
		//String txt2=t3.getText();
		check2(txt1);
		background.add(submit);
		System.out.println("Inside ");
		
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



@Override
public void keyTyped(KeyEvent e) {
	String txt1=t1.getText();
	check(txt1);
	
	
}



@Override
public void keyPressed(KeyEvent e) {
	// TODO Auto-generated method stub
	
}



@Override
public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
}
	  
}
