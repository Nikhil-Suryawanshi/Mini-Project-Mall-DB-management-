import java.awt.*; 
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
import javax.swing.table.*;

class CreateCart extends JFrame implements KeyListener,ActionListener,ItemListener
 {
  
  JLabel l1;
  JLabel background;
  JButton b1,b2,bremove;
  JTextField t1;
  List lst1,lst2;
  JTable cartTable;
  JScrollPane scroll;
  DefaultTableModel model;

  CreateCart()
  {
	  setTitle("Cart");
	  addWindowListener(new Win(this));

	  background=new JLabel(new ImageIcon("D:\\Eclipse Projects\\Mini\\src\\white.jpg"));
	  
	  l1=new JLabel("Search");
	  l1.setBounds(100,20,50,20);
	  
	  t1=new JTextField(10);
	  t1.setBounds(150,20,130,20);
	  
	  lst1=new List(5);
	  lst1.setBounds(150,45,130,60);
	  
	  lst2=new List(5);
	  lst2.setBounds(400,20,130,85);
	  
	  b1=new JButton("Add");
	  b1.setBounds(150,110,70,30);
	  
	  b2=new JButton("Check Out");
	  b2.setBounds(550,400,100,30);
	  
	  bremove=new JButton("Remove");
	  bremove.setBounds(400,110,100,30);
	  
	  model=new DefaultTableModel();
	  cartTable=new JTable(model);
	  scroll=new JScrollPane(cartTable);
	  scroll.setLocation(150,180);
	  scroll.setSize(500,200);
	  
	  cartTable.setFillsViewportHeight(true);
	  model.addColumn("Item Id");
	  model.addColumn("Item Name");
	  model.addColumn("Item Cost");
	  model.addColumn("Description");
	  model.addColumn("Type");
	  model.addColumn("Quantity");
	  
	  
	  
	  add(background);
	 
	  
	  background.add(l1);
	  background.add(t1);
	  
	  background.add(lst1);
	  background.add(b1);
	  
	  background.add(lst2);
	  background.add(b2);
	  background.add(bremove);
	  background.add(scroll);
	  
	  t1.addKeyListener(this);
	  lst1.addItemListener(this);
	  b1.addActionListener(this);
	  b2.addActionListener(this);
	  bremove.addActionListener(this);
	  
	    
  }

  //fun1
  void getItem(String itemname)
  {
	     Connection con=null;
	     String query;
	     try
	       {
	         Class.forName("com.mysql.jdbc.Driver");
	         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
	         if(itemname.isEmpty())
	          query="Select * from item where Iname='"+itemname+"'";
	         else
	          query="Select * from item where Iname like '" + itemname+"%'";
	          System.out.println(query);

	         Statement stmt=con.createStatement();
	         ResultSet rs=stmt.executeQuery(query);

	          while(rs.next())
	          {
	        	  lst1.add(rs.getString("Iname")+"  "+rs.getString("price"));
	        	  
	          }

	         rs.close();
	         stmt.close();
	         con.close(); 
	       }
	    catch(Exception e)
	       {
	         System.out.println(e);
	       }   
  }

    
  //fun2
  void addList2(String name)
  {
	  Connection con=null;

	     try
	       {
	         Class.forName("com.mysql.jdbc.Driver");
	         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
	         String query="Select * from item where Iname='"+name+"'";

	         Statement stmt=con.createStatement();
	         ResultSet rs=stmt.executeQuery(query);

	          if(rs.next())
	          {
	        	  lst2.add(rs.getString("itemid")+" "+name+" "+rs.getString("price"));
	        	  
	        	  String itemid=rs.getString("itemid"),Iname=rs.getString("Iname"),desc=rs.getString("description");
				  String type=rs.getString("type");
				  int price=rs.getInt("price");
	        	  check(itemid,Iname,price,desc,type);
	        	  checkOut(name);
	        	   
	          }

	         rs.close();
	         stmt.close();
	         con.close(); 
	       }
	    catch(Exception e)
	       {
	         System.out.println(e);
	       }   

	  
  }
  
  //fun3
  void checkOut(String item)
  {
	  Connection con=null;
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
		  String query="select * from item where Iname='"+item+"'";
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);		  
		  
		   if(rs.next())
		  {
			  int qty=rs.getInt("qty");
			  if(qty>0)
			  {
				  qty=qty-1;
				  System.out.println(qty);
				  String up="Update item set qty='"+qty+"' where itemid='" + rs.getString("itemid")+"'";
				  st.executeUpdate(up);
			  }
	     }  
		
		  rs.close();
		  st.close();
		  con.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
   }
  
  //fun4
  void remove(String itemid)
  {
	  checkRemove(itemid);
	  upremove(itemid);
  }
  		//fun4.1
  		void checkRemove(String itemid)
  		{
  			Connection con=null;
  		  try
  		  {
  			  Class.forName("com.mysql.jdbc.Driver");
  			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
  			  String query="update cart set qty1=qty1-1 where itemid1='"+itemid+"';";
  			  Statement st=con.createStatement();
  			  ResultSet rs=st.executeQuery("select * from cart where itemid1='"+itemid+"'");
  			  rs.next();

  			  int ch=rs.getInt("qty1");
  			  if(ch>1)
  				  st.executeUpdate(query);
  			  else
  				  st.executeUpdate("delete from cart where itemid1='"+itemid+"'");
  			  
  			  
  			  lst2.remove(lst2.getSelectedItem());
  			 
  			  rs.close();
  			  st.close();
  			  con.close();
  			  
  		  }
  		  catch(Exception e)
  		  {
  			  System.out.println(e);
  		  }
  			
  		}
  		
  		//fun4.2
  		void upremove(String itemid)
  		{

  			Connection con=null;
    		  try
    		  {
    			  Class.forName("com.mysql.jdbc.Driver");
    			  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
    			  Statement st=con.createStatement();
    			  st.executeUpdate("update item set qty=qty+1 where itemid='"+itemid+"'");
    			  
  			
    			  st.close();
    			  con.close();
 			  
 		  }
 		  catch(Exception e)
 		  {
 			  System.out.println(e);
 		  }
 			
  		}
 
  //fun5
  void check(String itemid,String Iname,int price,String desc,String type)
  {
	  Connection con=null;
	  
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
		  String query="select * from cart where itemid1='"+itemid+"'";
		  String insert="insert into cart values('"+itemid+"','"+Iname+"',"+price+",'"+desc+"','"+type+"',1)";
		  Statement st=con.createStatement();
		  ResultSet rs=st.executeQuery(query);
		  
		  if(rs.next())
		  {
			  cartUpdate(itemid);
		  }
		 else
		  {
			 cartInsert(itemid,Iname,price,desc,type,1);
			 
		  }
		
		    
		rs.close();
		st.close();
		con.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  
  }
  

 //fun6
  void cartInsert(String itemid,String Iname,int price,String desc,String type,int qty)
  {
	  Connection con=null;
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
		  
		  String insert="insert into cart values('"+itemid+"','"+Iname+"',"+price+",'"+desc+"','"+type+"',"+qty+")";
		  
		  Statement st=con.createStatement();
		  

		  st.executeUpdate(insert);
		  
		  st.close();
		  con.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
	  model.addRow(new Object[]{itemid,Iname,price,desc,type,qty});
  }
  
  //fun7
  void cartUpdate(String itemid)
  {
	  Connection con=null;
	  try
	  {
		  Class.forName("com.mysql.jdbc.Driver");
		  con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
		  
		  String update="update cart set qty1=qty1+1 where itemid1='"+itemid+"'";
		  Statement st=con.createStatement();
		  st.executeUpdate(update);
		  
		  st.close();
		  con.close();
	  }
	  catch(Exception e)
	  {
		  System.out.println(e);
	  }
  }
  
    
  
  
  public void keyPressed(KeyEvent ke)
  {
	  
	  
   
  }
 
 public void keyReleased(KeyEvent ke)
  {
	 String itemname=t1.getText();
	 getItem(itemname);
   
  }

 public void keyTyped(KeyEvent ke)
  {
	 
	 lst1.clear();
  }
  
  public void actionPerformed(ActionEvent ae)
  {
	  String str=ae.getActionCommand();
	  String itemname="";
	if(str.equals("Add"))
	  {
		  String item=lst1.getSelectedItem();
		  
		  String arr[ ]=item.split(" ");
		  itemname=arr[0];
		  addList2(itemname);
	  }
	  else if(str.equals("Check Out"))
	  {
		  
		  //setVisible(false);
		  ReceiptDetails r=new ReceiptDetails();
		  r.setVisible(true);
		  r.setSize(800,500);
		  r.setLocation(540,200);
		  
	  }
	  else if(str.equals("Remove"))
	  {
		  String item=lst2.getSelectedItem();
		  String arr[]=item.split(" ");
		  itemname=arr[0];
		  remove(itemname);
	  }
  }
  
  public void itemStateChange(ItemEvent ie)
  {
	  b1.enable(true);
	  String item=lst1.getSelectedItem();
	  String arr[ ]=item.split(" ");
	  String itemname=arr[0];
	 
  }
 
  public static void main(String argsp[])
  {
	  CreateCart c=new CreateCart();
	  c.setSize(800,500);
	  c.setVisible(true);
	  
	  
  }
  
  
}
