package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.derby.iapi.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DoctLogin")
public class LoginAdmin  extends HttpServlet  {

		private static final String query = "SELECT * FROM ADMIN WHERE DOCTOR_EMAIL = ? and DOCTOR_PASSWORD = ?";
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			
			//GET THE INFORMATION IN SIGN UP
			
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			//LOAD JDBC 
			try {
				Class.forName("org.apache.derby.jdbc.ClientDriver");	
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			String host = "jdbc:derby://localhost:1527/hospital;create=true";
	        String uName = "root";
	        String uPass= "root";
			//generate the conn
			try(Connection con = DriverManager.getConnection(host, uName, uPass);

					PreparedStatement ps = con.prepareStatement(query);){
				
					ps.setString(1, email);
					ps.setString(2, password);
					java.sql.ResultSet rs = ps.executeQuery();
					 if (rs.next()) {
					        // Login successful - Redirect to a welcome page or dashboard
						 	res.sendRedirect("admin_dashboard.jsp");
					        // ... (code to redirect or store user information in session)
					      } else {
					        pw.println("<a href=admin_login.jsp>Invalid email or password. Please try again.</a>");
					      }

					
					
			}catch(Exception e) {
				e.printStackTrace();
				pw.println("<h1>"+ e.getMessage()+ "</h2>");
			}
//			pw.print("<a href='index.jsp'>Login</a>");
//			pw.println("<br>");
//			pw.println("<a href='Appointment'>Appointments:</a>");
		}
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, res);
		}
	}
