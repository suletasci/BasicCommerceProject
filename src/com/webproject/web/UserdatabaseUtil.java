package com.webproject.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;





public class UserdatabaseUtil {
	public static DataSource dataSource;
	public  UserdatabaseUtil(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> users=new ArrayList<User>();
        Connection myConn=null;
        Statement myStmt=null;
        ResultSet myRs=null;
        try {
	        myConn=dataSource.getConnection();
	        String sql="select * from user";
	        myStmt=myConn.createStatement();
	        myRs=myStmt.executeQuery(sql);
	        while(myRs.next()) {
	            int id=myRs.getInt("id");;
	            String username=myRs.getString("name");
	            String password=myRs.getString("password");
	            User tempUser=new User(id, username, password);
	            users.add(tempUser);
	   }
	        
        return users;
    }
    finally {
        
        close(myConn,myStmt,myRs);
    }
	}

	public void registerUser(String name, String password) {
		Connection connect=null;
	    PreparedStatement preState=null;
	    
	    try {
	 	   connect=dataSource.getConnection();
	 	   
	 	   String sql = "insert into user "
					   + "(name,password) "
					   + "values (?,?)";
	 	   preState=connect.prepareStatement(sql);
	 	   preState.setString(1,name);
	 	   preState.setString(2,password);


	 	   
	 	   preState.execute();
	 	   
	 	   
	    }catch(Exception exc) {
	 	   exc.printStackTrace();
	    }finally {
	 	   close(connect,preState,null);
	    }
		
	}
	
    private static void close(Connection connect, Statement statement, ResultSet resultSet) {
		
		try{
			if(resultSet !=null) {
				resultSet.close();
			}
			if(statement !=null) {
				statement.close();
			}
			if(connect !=null) {
				connect.close();
			}
		}catch(Exception exc) {
			exc.printStackTrace();
		}
		
}

    public ArrayList<Product> getProducts() throws SQLException {
	
	ArrayList<Product> products=new ArrayList<Product>();


    Connection myConn=null;
    Statement myStmt=null;
    ResultSet myRs=null;

try {
   
    myConn=dataSource.getConnection();



    
    String sql="select * from products";
    myStmt=myConn.createStatement();
    
    myRs=myStmt.executeQuery(sql);

  
    while(myRs.next()) {
        int id=myRs.getInt("id");
        String name=myRs.getString("name");
        int price=myRs.getInt("price");;
        int stockNumber=myRs.getInt("stock");;
        String image=myRs.getString("image");

    
        Product tempProduct=new Product(id,name,price,stockNumber,image);
        products.add(tempProduct);
    }

    return products;
}
finally {
   
    close(myConn,myStmt,myRs);
}
}

    public Product getProductDetail(String id, int userId) throws Exception {
	
	Product product=null;
	int proId;
	Connection connect=null;
	PreparedStatement state=null;
	ResultSet resultSet=null;
	try {
		proId=Integer.parseInt(id);
		
		connect=dataSource.getConnection();  
		String sql="select * from products where id=?"; 
		state=connect.prepareStatement(sql);
		
		state.setInt(1, proId); 
		
		resultSet=state.executeQuery(); 
		 
		if(resultSet.next()) { 
			int proid=resultSet.getInt("id");
	        String name=resultSet.getString("name");
	        int price=resultSet.getInt("price");;
	        int stockNumber=resultSet.getInt("stock");;
	        String image=resultSet.getString("image");
			
			product=new Product(proid,name,price,stockNumber,image); 
		}else { 
			throw new Exception("could not find product with that id :"+proId);
		}
	   
		return product;
		
	}finally {
		close(connect,state,null);
	}
}

    public void addToBox(String productId, String name, int price, int usId) {
	
	 Connection connect=null;
     PreparedStatement preState=null;
     
     try {
  	   connect=dataSource.getConnection();
  	   
  	   String sql = "insert into box "
				   + "(id,productName,price,userId) "
				   + "values (?,?,?,?)";
  	   preState=connect.prepareStatement(sql);
  	   preState.setString(1,productId);
  	   preState.setString(2,name);
  	   preState.setInt(3,price);
  	   preState.setInt(4,usId);
  	   preState.execute(); 
     }catch(Exception exc) {
  	   exc.printStackTrace();
     }finally {
  	   close(connect,preState,null);
     }
	
	
}

	public ArrayList<Box> getBox(int id) throws SQLException {
		
		ArrayList<Box> box=new ArrayList<Box>();
    

        Connection myConn=null;
		PreparedStatement state=null;
		ResultSet resultSet=null;

    try {
    	
        //get a connection
        myConn=dataSource.getConnection();



        //create sql statement
        String sql="select * from box where userId=?";
        state=myConn.prepareStatement(sql);
        
        state.setInt(1, id); 
		
        resultSet=state.executeQuery();

        //process result set
        while(resultSet.next()) {

            //retrieve data drom result set row
            int boxId=resultSet.getInt("id");
            String name=resultSet.getString("productName");
            int price=resultSet.getInt("price");;
            int UserId=resultSet.getInt("userId");;


            //create new student object
        Box tempBox=new Box(boxId,name,price,UserId);

           //add it to the list of students



            box.add(tempBox);
        }

        return box;
    }
    finally {
        //close JDBC objects
        close(myConn,state,resultSet);
    }
	}

	public void updateStockNumber(int productId,int stockNumber) throws SQLException {
		
		Connection connect=null;
		  PreparedStatement state=null;
		  
		  try {
			  connect=dataSource.getConnection();
			  
			  String sql="update products set stock=? "
					  +"where id=?";
			  state=connect.prepareStatement(sql);
			  state.setInt(1,(stockNumber-1));
			  state.setInt(2, 1);
			  state.execute();
			  
		  }finally {
			  close(connect,state,null);
		  }
	}

	public void cleanBox(int userId) throws SQLException {
		Connection connect=null;
		  PreparedStatement state=null;
		  
		 
		  
		  try {
			  connect=dataSource.getConnection();
			  
			  String sql="delete from box "
					  +"where userId=?";
			  state=connect.prepareStatement(sql);
			  
			  state.setInt(1,userId);
			  state.execute();
			  
		  }finally {
			  close(connect,state,null);
		  }
		
	}

}





