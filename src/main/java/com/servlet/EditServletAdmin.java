package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

	@WebServlet("/editurladmin")
	public class EditServletAdmin extends HttpServlet {
		private static final String query = "UPDATE CLIENT SET APPOINTMENT_DATE = ?,APPOINTMENT_TYPE = ?,DESCRIPTION =? WHERE APPOINTMENT_ID=?";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			
	        int id = Integer.parseInt(req.getParameter("id"));
	        String appointmentDateStr = req.getParameter("appointment_date");
	        String selectAppointment = req.getParameter("select_appointment");
	        String note = req.getParameter("note");

	        // Validate and parse the date
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	        Date appointmentDate = Date.valueOf(appointmentDateStr);

	        // JDBC connection and update logic
	        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/hospital;create=true", "root", "root");
	             PreparedStatement ps = con.prepareStatement(query)) {

	            // Use java.sql.Date directly in prepared statement
	            ps.setDate(1, appointmentDate);
	            ps.setString(2, selectAppointment);
	            ps.setString(3, note);
	            ps.setInt(4, id);

	            int count = ps.executeUpdate();

	            if (count == 1) {
	                res.sendRedirect("AdminAppointment"); 
	            } else {
	                pw.println("<a href=AdminAppointment>UPDATE NOT SUCCESSFUL</a>");
	            }

	        } catch (Exception e) {
	            e.printStackTrace(); // Log the error for debugging
	            pw.println("<h2>An error occurred. Please try again later.</h2>");
	        }

	        pw.print("<a href='./admin_login.jsp'>Login</a>");
	        pw.println("<br>");
	        pw.println("<a href='AdminAppointment'>Appointments:</a>");
	    }
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, res);
		}
}