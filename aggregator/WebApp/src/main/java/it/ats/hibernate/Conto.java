package it.ats.hibernate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Servlet implementation class Conto
 */

public class Conto extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public Conto() {      
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operazione = request.getParameter("operazione"); 	   
        if("insert".equals(operazione)) {
        	insert(request, response);
        } else if("delete".equals(operazione)) {
        	delete(request, response);
      
        }
	}         
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void insert(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try {
	SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
	Session session = sessionFactory.openSession();
	// Begin Transaction avvia una transazione di database	
	String idConto = request.getParameter("idConto");
	String numero = request.getParameter("numero");
	String dataApertura = request.getParameter("dataApertura");
	String dataChiusura = request.getParameter("dataChiusura");
	String saldo = request.getParameter("saldo");		
	String queryString = "INSERT INTO Contocorrente (idConto, numero, dataApertura, dataChiusura, saldo) VALUES ('" + idConto + "','" + numero + "','" + dataApertura + "','" + dataChiusura + "','" + saldo + "'" + ")";
	Query query = session.createSQLQuery(queryString);
	query.executeUpdate();
	PrintWriter out = response.getWriter();
    String title="Inserimento conto avvenuto con successo";
    out.println(
         "<html>\n" +
         "<head><title>" + title + "</title></head>\n" +
         "<body bgcolor = \"#f0f0f0\">\n" +
         "<h1 align = \"center\">" + title + "</h1>\n" +
         "<ul>\n" +
          "</ul>\n" +
          "</body>" +
          "</html>");
 } catch (Exception e) {
	 PrintWriter out = response.getWriter();
	 String title="Errore";
	    out.println(
	         "<html>\n" +
	         "<head><title>" + title + "</title></head>\n" +
	         "<body bgcolor = \"#f0f0f0\">\n" +
	         "<h1 align = \"center\">" + title + "</h1>\n" +
	         "<ul>\n" +
	          "</ul>\n" +
	          "</body>" +
	          "</html>");
		}
	}
	
	protected void delete (HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		try {
			 
			SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
			Session session = sessionFactory.openSession();	
			String idConto = request.getParameter("idConto");
			Query q = session.createQuery("delete Contocorrente where idConto = " + idConto);
			q.executeUpdate();
			PrintWriter out = response.getWriter();
			String title="Cancellazione avvenuta con successo";
			out.println(
			"<html>\n" +
			"<head><title>" + title + "</title></head>\n" +
			"<body bgcolor = \"#f0f0f0\">\n" +
			"<h1 align = \"center\">" + title + "</h1>\n" +
			"<ul>\n" +
			"</ul>\n" +
			"</body>" +
			 "</html>");
		 } catch (Exception e) {
			PrintWriter out = response.getWriter();
			String title="Errore";
			 out.println(
			"<html>\n" +
		    "<head><title>" + title + "</title></head>\n" +
		    "<body bgcolor = \"#f0f0f0\">\n" +
			"<h1 align = \"center\">" + title + "</h1>\n" +
			"<ul>\n" +
			"</ul>\n" +
			"</body>" +
			"</html>");
		}
} 
 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
