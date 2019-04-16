package it.ats.hibernate;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.relational.SimpleAuxiliaryDatabaseObject;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.List;
/**
 * Servlet implementation class Persona
 */
public class Persona extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * Default constructor. 
     */
    public Persona() { 
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operazione = request.getParameter("operazione"); 	   
        if("update".equals(operazione)) {
        	update(request, response);
        } else if("delete".equals(operazione)) {
        	delete(request, response);
        } else if("insert".equals(operazione)) {
        	censimento(request, response);
        }
	}   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	  protected void censimento(HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {
		  try {
		SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
		Session session = sessionFactory.openSession();
		String ndg = request.getParameter("ndg");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String dataNascita = request.getParameter("dataNascita");
		String codFiscale = request.getParameter("codFiscale");		
		String queryString = "INSERT INTO utenti (ndg, nome, cognome, dataNascita, codFiscale) VALUES ('" + ndg + "','" + nome + "','" + cognome + "','" + dataNascita + "','" + codFiscale + "'" + ")";
		Query query = session.createSQLQuery(queryString);
		query.executeUpdate();
		PrintWriter out = response.getWriter();
	    String title="Censimento avvenuto con successo";
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
		

	 protected void delete(HttpServletRequest request, HttpServletResponse response) throws
		ServletException, IOException {
		 try {	
		SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
		Session session = sessionFactory.openSession();	
		String ndg = request.getParameter("ndg");
		Query q = session.createQuery("delete Utenti where NDG = " + ndg);
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
	 
	protected void update(HttpServletRequest request, HttpServletResponse response) throws
			ServletException, IOException {
		SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
		Session session = sessionFactory.openSession();
		String ndg = request.getParameter("ndg"); 
		String nome = request.getParameter("nome");
		Query query = session.createSQLQuery(
		    "update Utenti set nome = '"+ nome + "' where ndg = " + ndg);
	     query.executeUpdate();
	     PrintWriter out = response.getWriter();
		    String title="Modifica avvenuta con successo";
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}	
}
