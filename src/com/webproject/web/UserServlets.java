package com.webproject.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;






@WebServlet("/UserServlets")
public class UserServlets extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public UserdatabaseUtil userDatabase;
	public static int userId;
	@Resource(name="jdbc/finalproject2")
	private DataSource dataSource;
	public UserServlets(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userDatabase=new UserdatabaseUtil(dataSource);
		
		}catch(Exception exc) {
			throw new ServletException(exc);
		}
	}
	
    public UserServlets() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String theCommand=request.getParameter("command");
			
			if(theCommand==null) {
				theCommand="LOGIN";
			}
			
			switch(theCommand) {
				case "LOGIN":
					loginUser(request,response);
					break;
				case "LOGOUT":
					logoutUser(request,response);
					break;
				case "REGISTER":
					registerUser(request,response);
					break;
				case "LISTPRODUCTS":
					listProducts(request,response);
					break;
				case "PRODUCTDETAILS":
					productDetail(request,response);
					break;
				case "ADDBOX":
					addToBox(request,response);
					break;
				case "LISTBOX":
					listBox(request,response);
					break;
				case "BUY":
					buyProducts(request,response);
					break;
				default:
					break;
			}
			
			loginUser(request, response);	
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
	}

	private void buyProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int productId=Integer.parseInt(request.getParameter("productId"));
		int userId=Integer.parseInt(request.getParameter("userId"));
		Product p=userDatabase.getProductDetail(String.valueOf(productId), userId);
		userDatabase.updateStockNumber(productId,p.getStockNumber());
		userDatabase.cleanBox(userId);
		
		request.setAttribute("productDetail",p);
		request.setAttribute("userId", userId);
		RequestDispatcher dispatcher=request.getRequestDispatcher("productDetail.jsp");
		dispatcher.forward(request, response);
		
	}

	private void listBox(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		int userId=Integer.parseInt(request.getParameter("userId"));
		String productId=request.getParameter("productId");
		ArrayList<Box> box=userDatabase.getBox(userId);
		
		request.setAttribute("box_list", box);
		request.setAttribute("productId",productId);
		request.setAttribute("userId",userId);
		RequestDispatcher dispatcher=request.getRequestDispatcher("box.jsp");
		dispatcher.forward(request, response);
		
	}

	private void addToBox(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int usId=Integer.parseInt(request.getParameter("userId"));
		String productId=request.getParameter("productId");
		Product p=userDatabase.getProductDetail(productId,usId);
		userDatabase.addToBox(productId,p.getName(),p.getPrice(),usId);
		  
		RequestDispatcher dispatcher=request.getRequestDispatcher("add_box_message.jsp");
		dispatcher.forward(request, response);
	}

	private void productDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id=request.getParameter("productId");
		int userId=Integer.parseInt(request.getParameter("userId"));
		
		Product product=userDatabase.getProductDetail(id,userId);
		request.setAttribute("productDetail", product);
		request.setAttribute("userId",userId);
		
		RequestDispatcher dispatcher=request.getRequestDispatcher("/productDetail.jsp");
		dispatcher.forward(request, response);
		
	}

	private void listProducts(HttpServletRequest request, HttpServletResponse response) {
		int userId=Integer.parseInt(request.getParameter("userId"));
		try {
			ArrayList<Product> product=userDatabase.getProducts();
			
			request.setAttribute("PRODUCTS", product);
			request.setAttribute("userId", userId);
			RequestDispatcher dispatcher=request.getRequestDispatcher("/product.jsp");
			dispatcher.forward(request, response);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private void registerUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name=request.getParameter("firstname");
	    String password=request.getParameter("password");
	    userDatabase.registerUser(name,password);
	    RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
	    dispatcher.forward(request, response);
		
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/login.jsp");
	    dispatcher.forward(request, response);
		
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response) {
		   String username=request.getParameter("firstname");
	       String password=request.getParameter("password");
	       int temp=0;
	       String url="";
			try {
				ArrayList<User> user=userDatabase.getUsers();
				
				
				for(User u: user) {
					if(u.getName().equals(username) && u.getPassword().equals(password)) {
						userId=u.getId();
						request.setAttribute("name", username);
						request.setAttribute("userId", userId);
						temp=1;
					} 
				}
				if(temp==0) {
					url="/register.jsp";
				}
				else if(temp!=0) {
					url="/welcomePage.jsp";
				}
				
				RequestDispatcher dispatcher=request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
	}

	
	
	
	
	

}
