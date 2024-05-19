package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteurladmin")
public class DeleteServletAdmin extends HttpServlet {
	
	
	private static final String query = "DELETE FROM CLIENT WHERE APPOINTMENT_ID=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		
        int id = Integer.parseInt(req.getParameter("id"));

        // JDBC connection and update logic
        try (Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/hospital;create=true", "root", "root");
             PreparedStatement ps = con.prepareStatement(query)) {

            // Use java.sql.Date directly in prepared statement
        	ps.setInt(1, id);
            int count = ps.executeUpdate();

            if (count == 1) {
                res.sendRedirect("admin_login.jsp");
            } else {
                pw.println("<a href=AdminAppointment>DELETION NOT SUCCESSFUL</a>");
            }

        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            pw.println("<h2>An error occurred. Please try again later.</h2>");
        }

        pw.print("<a href='./admin_login.jsp'>Login</a>");
        pw.println("<br>");
    }

}
