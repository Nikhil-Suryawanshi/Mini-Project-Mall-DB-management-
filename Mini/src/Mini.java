import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


class Win extends WindowAdapter 
{
  Frame frm;

  Win(Frame f)
   {
     frm=f;
   }
/*
  public void windowClosing(WindowEvent we)
   {
      frm.setVisible(false);
      frm.dispose();
   }
*/
}

class ItemFrame extends JFrame implements ActionListener, ItemListener
{
  JLabel L1,L2,L3,L4,L5,L6,background;
  TextField t1,t2,t3,t4,t5,t6;
  Panel p1=new Panel();
  Panel p2=new Panel();
  Panel p3=new Panel();
  Panel p4=new Panel();

   List Lst;

   JButton badd, bmodify, bdelete, bsave, bcancel, bexit;
   String flag=""; 

   ItemFrame()
    {
      setTitle("Item Details");
      addWindowListener(new Win(this));
      p1.setLayout(new GridLayout(3,2));

      L1=new JLabel("Enter Item Id"); 
      L2=new JLabel("Enter Name"); 
      L3=new JLabel("Enter Price");
      L4=new JLabel("Enter Description");
      L5=new JLabel("Enter Type");
      L6=new JLabel("Enter Stock");
      background=new JLabel(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\white.jpg"));

      t1=new TextField(10);  
      t2=new TextField(10);  
      t3=new TextField(10);  
      t4=new TextField(10);
      t5=new TextField(10);
      t6=new TextField(10);

       Lst=new List(5, false);

        badd=new JButton("Add");
        bmodify=new JButton("Modify");
        bdelete=new JButton("Delete");
        bsave=new JButton("Save");
        bcancel=new JButton("Cancel");
        bexit=new JButton("Exit");       

       p1.add(L1);
       p1.add(t1);
       p1.add(L2);
       p1.add(t2);
       p1.add(L3);
       p1.add(t3);
       p1.add(L4);
       p1.add(t4);
       p1.add(L5);
       p1.add(t5);
       p1.add(L6);
       p1.add(t6);

       p2.add(Lst);

       
       
       p3.add(p1);
       p3.add(p2);
       p3.add(p4);
       add(p3);

       p4.add(badd);
       p4.add(bmodify);
       p4.add(bdelete);
       p4.add(bsave);
       p4.add(bcancel);
       p4.add(bexit);


      // p3.setBackground(Color.white);

       p1.enable(false);

       bmodify.enable(false);
       bdelete.enable(false);
       bsave.enable(false);
       bcancel.enable(false);

       badd.addActionListener(this);
       bmodify.addActionListener(this);
       bdelete.addActionListener(this);
       bsave.addActionListener(this);  
       bcancel.addActionListener(this);
       bexit.addActionListener(this);

       Lst.addItemListener(this);

       fillList();
    }

    //function 1
  void fillList()
   {
     Lst.clear();

     Connection con=null;

     try
       {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");

         String query="Select * from item order by itemid";

         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(query);

          while(rs.next())
           {
             Lst.add(rs.getString("itemid") + " " + rs.getString("Iname"));
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
  //function1 ends here

    //function 2
  void getStud(String itemid)
   {
     Connection con=null;

     try
       {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
         String query="Select * from item where itemid='" + itemid+"'";

         Statement stmt=con.createStatement();
         ResultSet rs=stmt.executeQuery(query);

          if(rs.next())
           {
             t1.setText(rs.getString("itemid"));
             t2.setText(rs.getString("Iname"));
             t3.setText(rs.getString("price"));
             t4.setText(rs.getString("description"));
             t5.setText(rs.getString("Type"));
             t6.setText(rs.getString("qty"));
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
  //function2 ends here

  //Function 3
   @SuppressWarnings("deprecation")
void enabled()
    {
      badd.enable(false);
      bmodify.enable(false);
      bdelete.enable(false);
      bsave.enable(true);
      bcancel.enable(true); 
    }
  //Function 3 ends here

  //Function 4
   void cancel()
    {
      t1.setText("");
      t2.setText("");
      t3.setText("");
      t4.setText("");
      t5.setText("");
      t6.setText("");
    
      p1.enable(false); 

      badd.enable(true);
      bmodify.enable(false);
      bdelete.enable(false);
      bsave.enable(false);
      bcancel.enable(false);      
    }
  //Function 4 ends here

    //function 5
  void insertStud(String itemid, String Iname, int price,String desc,String type,int qty)
   {
     Connection con=null;

     try
       {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
         String query="Insert into item values('" + itemid + "','" + Iname + "'," + price + ",'"+desc+"','"+type+"',"+qty+")";

         Statement stmt=con.createStatement();
         stmt.executeUpdate(query);

         stmt.close();
         con.close(); 
       }
    catch(Exception e)
       {
         System.out.println(e);
       }      
   }
  //function5 ends here

  
  
    //function 6
  void updateStud(String itemid, String Iname, int price,String desc,String type,int qty)
   {
     Connection con=null;

     try
       {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
         String query="Update item set Iname='" + Iname + "', price=" + price + ",description='"+desc+"',type='"+type+"',qty="+qty+" where itemid='" + itemid+"'";

         Statement stmt=con.createStatement();
         stmt.executeUpdate(query);

         stmt.close();
         con.close(); 
       }
    catch(Exception e)
       {
         System.out.println(e);
       }      
   }
  //function6 ends here

    //function 7
  void deleteStud(String itemid)
   {
     Connection con=null;

     try
       {
         Class.forName("com.mysql.jdbc.Driver");
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Mall_DB", "root", "root");
         String query="Delete from item where itemid='"+ itemid+"'";

         Statement stmt=con.createStatement();
         stmt.executeUpdate(query);

         stmt.close();
         con.close(); 
       }
    catch(Exception e)
       {
         System.out.println(e);
       }      
   }
  //function7 ends here

  public void actionPerformed(ActionEvent ae)
   {
     String str=ae.getActionCommand();

     if(str.equals("Add"))
      {
        t1.setFocusable(true);
        flag="Add";
        p1.enable(true);
        enabled(); 
      } 

     if(str.equals("Cancel"))
      {
        cancel(); 
      }

     if(str.equals("Exit"))
      {
        dispose();
      }

     if(str.equals("Save"))
      {
           String sno=t1.getText();
           String iname=t2.getText(); 
           int price=Integer.parseInt(t3.getText());
           String desc=t4.getText();
           String type=t5.getText();
           int qty=Integer.parseInt(t6.getText());

        if(flag.equals("Add"))
          {
            insertStud(sno,iname,price,desc,type,qty);
            JOptionPane.showMessageDialog(this,"Record added succesfully");    
           }

        if(flag.equals("Modify"))
          {
            updateStud(sno,iname,price,desc,type,qty);
            JOptionPane.showMessageDialog(this,"Record updated succesfully");    
           }

        fillList();
        cancel();
      }

     if(str.equals("Modify"))
      {
        flag="Modify";
        p1.enable(true);
        enabled(); 
      }

     if(str.equals("Delete"))
      {
        //JOptionPane.showMessageDialog(this,"Click_in");    
        int i=JOptionPane.showConfirmDialog(this,"Are you sure you want to delete this record?");    

        System.out.println(i); 

        if(i==0)  
          {
            String sno=t1.getText();
            deleteStud(sno);
            JOptionPane.showMessageDialog(this,"Record deleted succesfully");    
            fillList();
            cancel();
          }
      }

   }

  public void itemStateChanged(ItemEvent ie)
   {
     badd.enable(false);
     bmodify.enable(true);
     bdelete.enable(true);
     bsave.enable(false);
     bcancel.enable(true);

     String item=Lst.getSelectedItem();
     String arr[ ]=item.split(" ");
     String sno=arr[0];
     getStud(sno);
     System.out.println(arr[1]+arr[2]);
   }
}





class MainFrame extends JFrame implements ActionListener
{
 JButton b1,b2,logout;
  MainFrame()
   {
     addWindowListener(new Win(this));
     MenuBar mbar=new MenuBar();
     
     setMenuBar(mbar);     
     
     logout=new JButton(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\logout.jpg"));
     logout.setBounds(20,20,50,50);
     b1=new JButton(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\cart.jpg"));
     b2=new JButton(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\ItemEntry.jpg"));
     b1.setBounds(420+50,130,150,60);
     b2.setBounds(120+50,130,150,60);
     
     JLabel background=new JLabel(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\Zmart.jpg"));
     
     
     add(background);
     background.add(logout);
     background.add(b1);
     background.add(b2);
     
     
     Menu m1=new Menu("Master");
     MenuItem m11=new MenuItem("Item Entry");
     MenuItem m12=new MenuItem("Staff");
     m1.add(m11);
     m1.add(m12);
     mbar.add(m1);
     
     Menu m2=new Menu("Detail");
     
     MenuItem m23=new MenuItem("Cart");
     
     m2.add(m23);
     mbar.add(m2);
 
     m11.addActionListener(this);
     m12.addActionListener(this);
     b1.addActionListener(this);
     b2.addActionListener(this);
     logout.addActionListener(this);
     m23.addActionListener(this);
   }

  

  
  
  public void actionPerformed(ActionEvent ae)
   {
     String str=ae.getActionCommand();
     System.out.println(str);
     System.out.println(ae.getSource());
     if(str.equals("Item Entry"))
      {
        ItemFrame sf=new ItemFrame();
        sf.setLocation(540,200);
        sf.setSize(500,500); 
        sf.setVisible(true);        
      }
    
     else if(str.equals("Cart"))
     {  
		  
        CreateCart cc=new CreateCart();
        cc.setLocation(540,200);
        cc.setSize(800,500);
        cc.setVisible(true);
        System.out.println(cc.getOwner());
     }
     else if(str.equals("Exit"))
      {
    	 
        this.dispose();        
        
      }
     if(ae.getSource()==b2)
     {
       ItemFrame sf=new ItemFrame();
       sf.setLocation(540,200);
       sf.setSize(500,500); 
       sf.setVisible(true);        
     }
   
    else if(ae.getSource()==b1)
    {  
       CreateCart cc=new CreateCart();
       cc.setLocation(540,200);
       cc.setSize(800,500);
       cc.setVisible(true);
       System.out.println(cc.getOwner());
    }
    else if(ae.getSource()==logout)
    {  
    	Login l=new Login();
        l.setLocation(540,200);
        l.setSize(500,500); 
        l.setVisible(true);
        dispose();
    }
   }
}


class Login extends JFrame implements ActionListener 
{
  JLabel Username,pwd;
  JPasswordField t2;
  TextField t1;
  JButton submit,signup,Forgotpwd;
  JLabel background=new JLabel(new ImageIcon("D:\\Coding\\Eclipse Projects\\Mini\\src\\watermark.jpg"));
  
  Login()
  {
	  setTitle("Login Page");
	  addWindowListener(new Win(this));
	  add(background);
	  
	  
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
	  submit.setBounds(200, 160, 90, 30);
	  submit.setFont(new Font("TimesRoman",Font.PLAIN,20));
	  signup=new JButton("Sign Up");
	  signup.setBounds(200, 200, 90, 30);
	  signup.setFont(new Font("TimesRoman",Font.PLAIN,16));
	  Forgotpwd=new JButton("Forgot Password");
	  Forgotpwd.setBounds(175, 240, 140, 30);
	  Forgotpwd.setFont(new Font("TimesRoman",Font.PLAIN,16));
	  
	  
	  background.add(Username);
	  background.add(pwd);
	  background.add(t1);
	  background.add(t2);
	  background.add(submit);
	  background.add(signup);
	  background.add(Forgotpwd);
	  
	  submit.addActionListener(this);
	  signup.addActionListener(this);
	  Forgotpwd.addActionListener(this);
  }

  //fun1
  void checkInfo(String t1,String t2)
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
		  String query="select * from login where loginid='"+t1+"' && pwd='"+t2+"'";
		  ResultSet rs=st.executeQuery(query);
		  if(rs.next())
		  {
			  JOptionPane.showMessageDialog(this,"Login Successfull");
			  MainFrame mf=new MainFrame();
			     mf.setLocation(540,200);
			     mf.setSize(800,500); 
			     mf.setVisible(true);
			     dispose();
		  }
		  else
		  {
			  JOptionPane.showMessageDialog(this,"Invalid Username or Password");
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
  
  
  
  public void actionPerformed(ActionEvent ae)
  {
	  String str=ae.getActionCommand();
	  if(str.equals("Submit"))
	  {
		  String text1=t1.getText();
		  String text2=t2.getText();
		  checkInfo(text1,text2);
	  }
	  if(str.equals("Sign Up"))
	  {
		  Signup s=new Signup();
		  s.setVisible(true);
		  this.setVisible(false);
		  s.setLocation(540,200);
		  s.setSize(500,500);
	  }
	  if(str.equals("Forgot Password"))
	  {
		  Forgot f=new Forgot();
		  f.setVisible(true);
		  this.setVisible(false);
		  f.setLocation(540,200);
		  f.setSize(500,500);
	  }
  }

}








class Mini
{
  public static void main(String args[ ])
   {
     Login l=new Login();
     l.setLocation(540,200);
     l.setSize(500,500); 
     l.setVisible(true);
     //mf. setExtendedState(Frame.MAXIMIZED_BOTH);
   }
}

