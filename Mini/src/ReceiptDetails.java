import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

public class ReceiptDetails extends JFrame implements ActionListener   
{
	
	  JButton proceed,back;
	  JLabel Rname,Date,Mobile_no,Addr;
	  JTextField t1,t2,t3;
	  JTextPane t4;
	  JLabel background=new JLabel(new ImageIcon("D:\\Eclipse Projects\\Mini\\src\\white.jpg"));
	  int Rno;
	  
	  ReceiptDetails()
	  {
	      setTitle("Receipt Entries");
		  addWindowListener(new Win(this));
		  setBackground(Color.white);
		  add(background);
		  
		  
//		  JLabel background=new JLabel(new ImageIcon("D:\\Eclipse Projects\\Mini\\src\\Zmart.jpg"));
//		  add(background);
		  
		  back=new JButton("Back");
		  back.setBounds(20,10,70,20);
		  LocalDate d=LocalDate.now();
		  
		  
		  Rname=new JLabel("Name:");
		  Rname.setBounds(240, 100 ,100,20);
		  Rname.setFont(new Font("TimesRoman",Font.BOLD,20));
		  
		  Date=new JLabel("Date:");
		  Date.setBounds(620,10,100,20);
		  Date.setFont(new Font("TimesRoman",Font.BOLD,20));
		  t1=new JTextField();
		  t1.setBounds(350, 100 , 200,20);
		  
		  t2=new JTextField();
		  t2.setBounds(670, 11 ,100,20);
		  t2.setText(d.toString());
		  
		  proceed=new JButton("Proceed");
		  proceed.setBounds(340,300,100,50);
		  proceed.setFont(new Font("TimesRoman",Font.PLAIN,20));
		  
		  Mobile_no=new JLabel("Mobile No:");
		  Mobile_no.setBounds(240,150,100,20);
		  Mobile_no.setFont(new Font("TimesRoman",Font.BOLD,20));
		  t3=new JTextField();
		  t3.setBounds(350,150,200,20);
		  
		  Addr=new JLabel("Address:  ");
		  Addr.setBounds(240,200, 100 , 20);
		  Addr.setFont(new Font("TimesRoman",Font.BOLD,20));
		  t4=new JTextPane();
		  t4.setBounds(350,200,200,50);
		  
		  
		  background.add(back);
		  background.add(Rname);
		  background.add(Date);
		  background.add(t1);
		  background.add(t2);
		  background.add(Mobile_no);
		  background.add(t3);
		  background.add(Addr);
		  background.add(t4);
		  background.add(proceed);
		  
		  proceed.addActionListener(this);
		  back.addActionListener(this);
		  
	  }
	  
	  
	  int checkno()
	  {
		  String url="jdbc:mysql://localhost:3306/Mall_DB";
		  String username= "root";
		  String pwd="root";
		  int Rno=0;
		  Connection con;
		  try 
		  {
			  Class.forName("com.mysql.jdbc.Driver");
			  con=DriverManager.getConnection(url,username,pwd);
			  Statement st=con.createStatement();
			  ResultSet rs=st.executeQuery("Select * from receipt order by Receipt");
			  while(rs.next())
			  {
				 Rno=rs.getInt("Receipt"); 
			  }
		  }
		  
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  return Rno+1;
	  }
	  
	  
	  
	  
	  public void actionPerformed(ActionEvent ae)
	  {
		  String str=ae.getActionCommand();
		  String name,date,addr,no;
		  
		  name=t1.getText();
		  date=t2.getText();
		  addr=t4.getText();
		  no=t3.getText();
		  int len = no.length();
		  
		  
		if(str.equals("Proceed"))
		  {
			Rno=checkno();
			if(len!=10)
			  {
				  JOptionPane.showMessageDialog(this,"Enter valid Mobile Number");
			  }
			else
			{
			if(name.isEmpty()||date.isEmpty()||addr.isEmpty()||no.isEmpty())
			{
				JOptionPane.showMessageDialog(this,"Please fill all the details");
			}	
			else
			    {	
					JOptionPane.showMessageDialog(this, "Records Added!");
					Receipt r=new Receipt(name,date,no,addr,Rno);
					r.setLocation(540,200);
					r.setVisible(true);
					r.setSize(480,800); 
					dispose();
				}
			}
		  }
		else if(str.equals("Back"))
		{
			//dispose();
			setVisible(false);
			
		}
	  }
	  
	  
	  
	  public static void main(String args[])
	  {
		  ReceiptDetails r=new ReceiptDetails();
		  r.setVisible(true);
		  r.setSize(800,500);
		  r.setLocation(540,200);
		  
	  }
}