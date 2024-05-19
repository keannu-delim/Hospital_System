package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/DoctRegister")
public class RegisterAdmin extends HttpServlet {
	
	private static final String query = "INSERT INTO ADMIN(DOCTOR_NAME ,DOCTOR_EMAIL,DOCTOR_PASSWORD) VALUES(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//GET THE INFORMATION IN SIGN UP
		
		String doctor_name = req.getParameter("docname");
		String doctor_email = req.getParameter("docemail");
		String doctor_password = req.getParameter("docpassword");
		
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
				ps.setString(1, doctor_name);
				ps.setString(2, doctor_email);
				ps.setString(3, doctor_password);
				int count = ps.executeUpdate();
				if(count==1) {
					res.sendRedirect("admin_login.jsp");
				}else {
					pw.print("<a href=admin_signup.jsp>RECORD IS NOT REGISTERED!!! </a>");
				}
				
				
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+ e.getMessage()+ "</h2>");
		}
//		pw.print("<a href='index.jsp'>Login</a>");
//		pw.println("<br>");
//		pw.println("<a href='Appointment'>Appointments:</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
