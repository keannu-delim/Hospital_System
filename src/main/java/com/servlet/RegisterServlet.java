package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.derby.client.am.SqlException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private static final String query = "INSERT INTO CLIENT(PATIENT_NAME,PATIENT_EMAIL,PATIENT_PASSWORD, APPOINTMENT_TYPE, APPOINTMENT_DATE, DESCRIPTION) VALUES(?,?,?,?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
		//GET THE INFORMATION IN SIGN UP
		
		String username = req.getParameter("username");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String appointment_type = req.getParameter("typeOfAppointment");
		String appointment_date = req.getParameter("appointment_date");		
        String note = req.getParameter("note");		
		
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
				ps.setString(1, username);
				ps.setString(2, email);
				ps.setString(3, password);
				ps.setString(5, appointment_date);
				ps.setString(4, appointment_type);
				ps.setString(6, note);
				int count = ps.executeUpdate();
				if(count==1) {
					res.sendRedirect("Index.jsp"); 
				}else {
					pw.print("<a href=Index.jsp>RECORD IS NOT REGISTERED!!! </a>");
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
