package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

	@WebServlet("/UserAppointment")
	public class ViewappointmentbyUser extends HttpServlet {
		private static final String query = "SELECT PATIENT_NAME ,PATIENT_EMAIL, APPOINTMENT_TYPE, APPOINTMENT_DATE, DESCRIPTION FROM CLIENT \r\n"
				+ "WHERE APPOINTMENT_ID = ? ";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			PrintWriter pw = res.getWriter();
			res.setContentType("text/html");
			
			//Retrive the user id from session
			
			HttpSession session = req.getSession(false); // Don't create a new session if not existing
	        if (session != null) {
	            Integer APPOINTMENT_ID = (Integer) session.getAttribute("APPOINTMENT_ID");
		      
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
				
				pw.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet'>");
				
				PrintWriter out = res.getWriter();
				
				out.println("<head>");
				out.println("<meta charset=\"UTF-8\">");
				out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
				out.println("<title>Docalendar</title>");
				out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
				out.println("</head>");
				out.println("<body>");
				
				out.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">");
				out.println("<a class=\"navbar-brand\" href=\"user_dashboard.jsp\">Docalendar</a>");
				out.println("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
				out.println("<span class=\"navbar-toggler-icon\"></span>");
				out.println("</button>");
				out.println("<div class=\"collapse navbar-collapse d-flex flex-row-reverse\" id=\"navbarNav\">");
				out.println("<ul class=\"navbar-nav\">");
				out.println("<li class=\"nav-item\">");
				out.println("<a class=\"nav-link\" href=\"./Index.jsp\">Log out</a>");	
				out.println("</nav>");;
				
				if (APPOINTMENT_ID != null) {
				      // Set the retrieved appointment ID as the parameter for the query
				      ps.setInt(1, APPOINTMENT_ID);
				      java.sql.ResultSet rs = ps.executeQuery();
				    
				      pw.println("<div class='container'>"); // Start container div
				      pw.println("<div class='row justify-content-center mt-4'>"); // Center the content horizontally
				      pw.println("<div class='col-md-11 flex'>"); // Adjust column width to occupy the entire container

				      pw.println("<div class='text-right mb-2'>"); // Align content to the right
				      pw.println("<a href='user_dashboard.jsp' class='btn btn-secondary'>Back</a>");
				      pw.println("</div>");

				      pw.println("<div class='table-responsive'>"); // Make the table responsive
				      pw.println("<table class='table table-striped table-hover table-bordered table-responsive'>");
				      pw.println("<thead>");
				      pw.println("<tr>");
				      pw.println("<th>Appointment Id</th>");
				      pw.println("<th>Patient Name</th>");
				      pw.println("<th>Patient Email</th>");
				      pw.println("<th>Appointment Type</th>");
				      pw.println("<th>Appointment Date</th>");
				      pw.println("<th>Description</th>");
				      pw.println("<th>EDIT</th>");
				      pw.println("<th>DELETE</th>");
				      pw.println("</tr>");
				      pw.println("</thead>");
				      pw.println("<tbody>");

				      while(rs.next()) {
				          pw.println("<tr>");
				          pw.println("<td>" + APPOINTMENT_ID + "</td>");
				          pw.println("<td>" + rs.getString(1) + "</td>");
				          pw.println("<td>" + rs.getString(2) + "</td>");
				          pw.println("<td>" + rs.getString(3) + "</td>");
				          pw.println("<td>" + rs.getString(4) + "</td>");
				          pw.println("<td>" + rs.getString(5) + "</td>");
				          pw.println("<td><a href='UpdateAppointment?id=" + APPOINTMENT_ID + "' class='btn btn-primary'>EDIT</a></td>");
				          pw.println("<td><a href='deleteurl?id=" + APPOINTMENT_ID + "' class='btn btn-danger'>DELETE</a></td>");
				          pw.println("</tr>");
				      }

				      pw.println("</tbody>");
				      pw.println("</table>");
				      pw.println("</div>"); // Close table-responsive div

				      pw.println("</div>"); // Close column div
				      pw.println("</div>"); // Close row div
				      pw.println("</div>"); // Close container div

				      out.println("</body>");
				      out.println("</html>");

        		
				} else {
                    pw.println("<h1>No appointment ID found in session.</h1>");
                }
            } catch (Exception e) {
                e.printStackTrace();
                pw.println("<h1>" + e.getMessage() + "</h2>");
            }
        } else {
            // Handle scenario where user ID is not found in session
            pw.println("<h1>Please Login to view appointments</h1>");
        }
	        
    }
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, res);
		}
}