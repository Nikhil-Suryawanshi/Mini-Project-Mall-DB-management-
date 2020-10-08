
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;


class Receipt extends JFrame //implements ActionListener,ItemListener
 {
  JLabel background=new JLabel(new ImageIcon("D:\\Eclipse Projects\\Mini\\src\\Rwatermark.jpg"));
  JLabel Rname,Date,Mobile_no,Addr,Gtotal,receipt;
  JLabel t1,t2,t3,t4,t5,receL;
  JTable table;
  JScrollPane scroll;
  DefaultTableModel model;
  int Rno;
  
  Receipt(String name ,String date,String mobile,String addr,int r)
  {
      setTitle("Receipt");
	  addWindowListener(new Win(this));
	  add(background);
	  
	  Rno=r;
	  
	
	  String receiptno=Integer.toString(Rno);
//	  JLabel background=new JLabel(new ImageIcon("D:\\Eclipse Projects\\Mini\\src\\Zmart.jpg"));
//	  add(background);
	  receipt=new JLabel("Receipt");
	  receipt.setBounds(20,50,100,20);
	  receL=new JLabel(receiptno);
	  receL.setBounds(80,50,100,20);
	  Rname=new JLabel("Name:");
	  Rname.setBounds(20, 50+30,100,20);
	  Date=new JLabel("Date:");
	  Date.setBounds(350,10,100,20);
	  t1=new JLabel(name);
	  t1.setBounds(80, 50+30 , 200,20);
	  t2=new JLabel(date);
	  t2.setBounds(390,10,100,20);
	  
	  
	  Mobile_no=new JLabel("Mobile No:");
	  Mobile_no.setBounds(20,80+30,100,20);
	  t3=new JLabel(mobile);
	  t3.setBounds(80,80+30,200,20);
	  
	  Addr=new JLabel("Address:");
	  Addr.setBounds(20, 110+30, 100 , 20);
	  t4=new JLabel(addr);
	  t4.setBounds(80,110+30,200,20);
	  
	  model=new DefaultTableModel() ;
	  table=new JTable(model);
	  scroll=new JScrollPane(table);
	  scroll.setSize(300, 300);
	  scroll.setLocation(50, 170+20);
	  table.setFillsViewportHeight(true);

	  model.addColumn("Item Id");
	  model.addColumn("Item Name");
	  model.addColumn("Quantity");
	  model.addColumn("Item Price");
	  
	  Gtotal=new JLabel("Grand Total:");
	  Gtotal.setBounds(210,470+20,100,20);
	  
	  
	  
	  background.add(receL);
	  background.add(receipt);
	  background.add(Rname);
	  background.add(Date);
	  background.add(t1);
	  background.add(t2);
	  background.add(Mobile_no);
	  background.add(t3);
	  background.add(Addr);
	  background.add(t4);
	  background.add(scroll);
	  background.add(Gtotal);
	  int total=getCart();
	  
	  t5=new JLabel(String.valueOf(total));//Or Integer.toString(total)    to convert int to String
	  t5.setBounds(280,470+20,100,20);
	  background.add(t5);
	  insert(name,mobile,Rno,date,total);
	  
  }
  
  
  //Fun 1
  int getCart()
  {
	  String url="jdbc:mysql://localhost:3306/Mall_DB";
	  String username= "root";
	  String pwd="root";
	  int total=0;
	  Connection con;
	  try 
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection(url,username,pwd);
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery("Select * from cart order by itemid1");
		  while(rs.next())
		  {
			  int cost=rs.getInt("price")*rs.getInt("qty1");
			  model.addRow(new Object[]{rs.getString("itemid1"),rs.getString("Iname1"),rs.getString("qty1"),rs.getString("price")});
			  total=total+cost;
		  }
		  st.executeUpdate("delete from cart");
	  }
	  
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  return total;
  }
  
  
  //fun 2
  void insert(String name,String mo_no,int receipt,String Date,int grandTotal)
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
		  st.executeUpdate("insert into receipt values('"+name+"','"+mo_no+"',"+receipt+",'"+Date+"',"+grandTotal+")");
	  }
	  
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  
	  
  }
  
}