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
 * Servlet implementation class Associazione
 */
public class Associazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Associazione() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operazione = request.getParameter("operazione"); 	   
        if("insert".equals(operazione)) {
        	associazione(request, response);
        } else if("delete".equals(operazione)) {
        	delete(request, response);
        }
	}
	/**
	 * Il metodo associazione è stato definito per effettuare 
	 * un'operazione di inserimento su database, nonchè registrare  
	 * nuove associazioni tra NDG del soggetto e idConto
	 * Viene definita la SessionFactory ovvero l'oggetto 
	 * responsabile dell’apertura delle sessioni  verso il database 
	 * @param request contiene il valore di una richiesta parametrica come
	 * una stringa, in questo caso idConto e NDG 
	 * @param response - in questo caso ritornerà un oggetto di tipo 
	 * PrintWriter che mostrerà in output il messaggio che conferma l'avvenuta
	 * associazione
	 * @throws ServletException 
	 * @throws IOException
	 * @exception ritorna un messaggio di errore se errata la queryString 
	 */
	protected void associazione (HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		try {	
		SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
		Session session = sessionFactory.openSession();
		String ndg = request.getParameter("ndg");
		String idConto = request.getParameter("idConto");	
		String queryString = "INSERT INTO Utenti_Associazione_Conto(ndg, idConto) VALUES ('" + ndg + "','" + idConto + "'" + ")";
		Query query = session.createSQLQuery(queryString);
		query.executeUpdate();
		PrintWriter out = response.getWriter();
	    String title="Associazione avvenuta con successo";
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
	 * sarà cancellata un'associazione tra un idConto e un NDG 
	 * Viene definita la SessionFactory ovvero l'oggetto 
	 * responsabile dell’apertura delle sessioni  verso il database 
	 * @param request contiene il valore di una richiesta parametrica come
	 * una stringa, in questo caso idConto e NDG 
	 * @param response - in questo caso ritornerà un oggetto di tipo 
	 * PrintWriter che mostrerà in output il messaggio che conferma l'avvenuta
	 * cancellazione
	 * @throws ServletException 
	 * @throws IOException
	 * @exception ritorna un messaggio di errore se errata la queryString 
	 */
	protected void delete (HttpServletRequest request, HttpServletResponse response) throws
	ServletException, IOException {
		try {
			SessionFactory sessionFactory = (SessionFactory)getServletContext().getAttribute("SessionFactory");
			Session session = sessionFactory.openSession();	
			String idConto = request.getParameter("idConto");
			String ndg = request.getParameter("ndg");
			Query q = session.createQuery("delete Utenti_Associazione_Conto where idConto = " + idConto + "and ndg= " + ndg);
			q.executeUpdate();
			//ritorna un messaggio di testo che ne conferma l'avvenuta cancellazione
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
