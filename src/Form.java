
/* A servlet to display the contents of the MySQL movieDB database */

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

// Declaring a WebServlet called FormServlet, which maps to url "/form"
@WebServlet(name = "FormServlet", urlPatterns = "/form")

public class Form extends HttpServlet {

    public String getServletInfo() {
        return "Servlet connects to MySQL database and displays result of a SELECT";
    }

    // Use http GET
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String loginUser = "mytestuser";
        String loginPasswd = "mypassword";
        String loginUrl = "jdbc:mysql://localhost:3306/moviedb";

        response.setContentType("text/html");    // Response mime type

        // Output stream to STDOUT
        PrintWriter out = response.getWriter();

        // building page head with title
        out.println("<html><head><title>MovieDB: Found Records</title></head>");

        // building page body
        out.println("<body><h1>MovieDB: Found Records</h1>");


        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Create a new connection to database
            Connection dbCon = DriverManager.getConnection(loginUrl, loginUser, loginPasswd);

            // Declare a new statement
            Statement statement = dbCon.createStatement();

            // Retrieve parameter "name" from request, which refers to the value of <input name="name"> in index.html
            String name = request.getParameter("name");

            // Generate a SQL query
            String query = String.format("SELECT * from stars where name like '%s'", name);

            // Perform the query
            ResultSet rs = statement.executeQuery(query);

            // Create a html <table>
            out.println("<table border>");

            // Iterate through each row of rs and create a table row <tr>
            out.println("<tr><td>ID</td><td>Name</td></tr>");
            while (rs.next()) {
                String m_ID = rs.getString("ID");
                String m_Name = rs.getString("name");
                out.println(String.format("<tr><td>%s</td><td>%s</td></tr>", m_ID, m_Name));
            }
            out.println("</table>");


            // Close all structures
            rs.close();
            statement.close();
            dbCon.close();

        } catch (Exception ex) {

            // Output Error Massage to html
            out.println(String.format("<html><head><title>MovieDB: Error</title></head>\n<body><p>SQL error in doGet: %s</p></body></html>", ex.getMessage()));
            return;
        }
        out.close();
    }
}
