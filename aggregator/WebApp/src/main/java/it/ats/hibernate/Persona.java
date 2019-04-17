package it.ats.hibernate;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
	 * Il metodo censimento è stato definito per effettuare 
	 * un'operazione di inserimento su database, in questo caso
	 * verranno inserite le generiche di un cliente
	 * Viene definita la SessionFactory ovvero l'oggetto 
	 * responsabile dell’apertura delle sessioni verso il database 
	 * @param request contiene il valore di una richiesta parametrica come
	 * una stringa, in questo caso: ndg, nome, cognome, dataNascita e codFiscale
	 * @param response - in questo caso ritornerà un oggetto di tipo 
	 * PrintWriter che mostrerà in output il messaggio che conferma l'avvenuto
	 * inserimento
	 * @throws ServletException 
	 * @throws IOException
	 * @exception ritorna un messaggio di errore se errata la queryString 
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
	  /**
		 * Il metodo delete è stato definito per effettuare 
		 * un'operazione di cancellazione su database, in questo caso 
		 * verrà cancellata la registrazione di un cliente 
		 * Viene definita la SessionFactory ovvero l'oggetto 
		 * responsabile dell’apertura delle sessioni  verso il database 
		 * @param request contiene il valore di una richiesta parametrica come
		 * una stringa, in questo caso ndg 
		 * @param response - in questo caso ritornerà un oggetto di tipo 
	     * PrintWriter che mostrerà in output il messaggio che conferma l'avvenuta
	     * cancellazione
		 * @throws ServletException 
		 * @throws IOException
		 * @exception ritorna un messaggio di errore se errata la queryString 
		 */
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
	   /**
		 * Il metodo update è stato definito per effettuare 
		 * un'operazione di modifica/aggiornamento
		 * Viene definita la SessionFactory ovvero l'oggetto 
		 * responsabile dell’apertura delle sessioni  verso il database 
		 * @param request contiene il valore di una richiesta parametrica come
		 * una stringa, in questo caso idConto e NDG e nome
		 * @param response - in questo caso ritornerà un oggetto di tipo 
	     * PrintWriter che mostrerà in output il messaggio che conferma l'avvenuta
	     * modifica
		 * @throws ServletException 
		 * @throws IOException
		 * @exception ritorna un messaggio di errore se errata la queryString 
		 */
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);	
	}	
}
