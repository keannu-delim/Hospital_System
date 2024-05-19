package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/UpdateAppointment")
public class UpdateServlet extends HttpServlet {
	private static final String query = "SELECT APPOINTMENT_TYPE, APPOINTMENT_DATE, DESCRIPTION FROM CLIENT WHERE APPOINTMENT_ID=?";
	@Override
	//UPDATE CLIENT SET TYPE_OF_APPOINTMENT = ?, APPOINTMENT_DATE = ?, DESCRIPTION = ? WHERE APPOINTMENT_ID=?
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter pw = res.getWriter();
		res.setContentType("text/html");
		//get the id
		int id =Integer.parseInt(req.getParameter("id"));
//		String Type = req.getParameter("typeOfAppointment");
//		String Date = req.getParameter("date");
//		String note = req.getParameter("note");
		
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
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
		
			
			if (rs.next()) {
				
				pw.println("<!DOCTYPE html>");
                pw.println("<html lang=\"en\">");
                pw.println("<head>");
                pw.println("<meta charset=\"UTF-8\">");
                pw.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
                pw.println("<title>Edit Appointment</title>");
                // Include necessary CSS and JS files
                pw.println("<!-- Include Bootstrap CSS -->");
                pw.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
                pw.println("<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\">");
                pw.println("<link href=\"https://unpkg.com/gijgo@1.9.14/css/gijgo.min.css\" rel=\"stylesheet\" type=\"text/css\" />");
                pw.println("</head>");
                pw.println("<body>");

                // Navigation bar
                pw.println("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">");
                pw.println("<a class=\"navbar-brand\" href=\"UserAppointment\">Docalendar</a>");
                pw.println("<button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNav\" aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">");
                pw.println("<span class=\"navbar-toggler-icon\"></span>");
                pw.println("</button>");
                pw.println("<div class=\"collapse navbar-collapse d-flex flex-row-reverse\" id=\"navbarNav\">");
                pw.println("<ul class=\"navbar-nav\">");
                pw.println("<li class=\"nav-item\">");
                pw.println("</li>");
                pw.println("</ul>");
                pw.println("</div>");
                pw.println("</nav>");
				
				pw.println("<div class=\"container py-5\">");
                pw.println("<div class=\"row justify-content-center\">");
                pw.println("<div class=\"col-md-6\">");
                
                pw.println("<div class='text-right mb-2'>"); // Align content to the right
                pw.println("<a href='UserAppointment' class='btn btn-secondary'>Back</a>");
                pw.println("</div>");
                
                pw.println("<div class=\"card\">");
                pw.println("<div class=\"card-header \">");
                pw.println("Edit Appointment");
                pw.println("</div>");
                pw.println("<div class=\"card-body\">");
                
                pw.println("<form action='editurl?id="+id+"' method='post'>");
                pw.println("<div class=\"form-group\">");
                pw.println("<label for=\"typeOfAppointment\">Select Appointment type</label>");
                pw.println("<select  class=\"form-control\" id=\"typeOfAppointment\" name=\"select_appointment\">");
                pw.println("<option value=\"general_checkup:James Olson\">General Checkup ( James Olson )</option>");
                pw.println("<option value=\"follow_up_checkup:Clark Kent\">Followup Checkup ( Clark Kent )</option>");
                pw.println("<option value=\"specialized_checkup:Lois Lane\">Specialized Checkup ( Lois Lane )</option>");
                pw.println("<option value=\"annual_checkup:Cory Powers\">Annual Checkup ( Cory Powers )</option>");
                pw.println("</select>");
                pw.println("</div>");
                pw.println("<script>");
                pw.println("document.addEventListener('DOMContentLoaded', function() {");
                pw.println("var typeOfAppointment = document.getElementById('typeOfAppointment');");
                pw.println("var appointmentType = '" + rs.getString(1) + "';");
                pw.println("for (var i = 0; i < typeOfAppointment.options.length; i++) {");
                pw.println("if (typeOfAppointment.options[i].value === appointmentType) {");
                pw.println("typeOfAppointment.selectedIndex = i;");
                pw.println("break;");
                pw.println("}");
                pw.println("}");
                pw.println("});");
                pw.println("</script>");
                
                pw.println("<div class=\"form-group\">");
                pw.println("<label for=\"datepicker\">Appointment date</label>");
                pw.println("<input id=\"datepicker\" name=\"appointment_date\" class=\"form-control\" value='" + rs.getString(2) + "'/>");
                pw.println("</div>");
                pw.println("<script>");
                pw.println("document.addEventListener('DOMContentLoaded', function() {");
                pw.println("var appointmentDate = '" + rs.getString(2) + "';");
                pw.println("$('#datepicker').datepicker({");
                pw.println("  uiLibrary: 'bootstrap5',");
                pw.println("  value: appointmentDate"); // Set the value here
                pw.println("});");
                pw.println("});");
                pw.println("</script>");

                
                pw.println("<div class=\"form-group\">");
                pw.println("<label for=\"note\">Note for your doctor</label>");
                pw.println("<input type='text' name='note' class='form-control' value='" + rs.getString(3) + "'>");
                pw.println("</div>");
                pw.println("<div class=\"form-group\">");
                pw.println("<input type='submit' class='btn btn-primary' value='UPDATE'>");
                pw.println("<input type='reset' class='btn btn-secondary' value='CANCEL'>");
                pw.println("</div>");

                pw.println("</form>");
                pw.println("</div>");
                pw.println("</div>");
                pw.println("</div>");
                pw.println("</div>");
                pw.println("</div>");

                // Include necessary JavaScript
                pw.println("<script src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"></script>");
                pw.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>");
                pw.println("<script src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>");
                pw.println("<script src=\"https://unpkg.com/gijgo@1.9.14/js/gijgo.min.js\" type=\"text/javascript\"></script>");
                pw.println("<script>");
                pw.println("$('#datepicker').datepicker({");
                pw.println("uiLibrary: 'bootstrap5',");
                pw.println("format: 'yyyy-mm-dd',");
                pw.println("showTrigger: true // Keep the date icon visible");
                pw.println("});");
                pw.println("</script>");
                pw.println("</body>");
                pw.println("</html>");
				}
			
			
			

			

//			ps.setString(1, Type);
//			ps.setString(2, Date);
//			ps.setString(3, note);
			
			
//			int rs = ps.executeUpdate();
//		
//			if (rs > 0) {
//			    pw.println("An existing user was updated successfully!");
//			}else {
//				pw.println("An existing user was updated unsuccessfully!");
//			}
			
//			if(count==1) {
//				pw.print("<h2>RECORD IS UPDATED SUCCESSFULLY</h2><br>");
//			}else {
//				pw.print("<h2>RECORD IS NOT UPDATED!!");
//			}
//			
		}catch(Exception e) {
			e.printStackTrace();
			pw.println("<h1>"+ e.getMessage()+ "</h2>");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}
}