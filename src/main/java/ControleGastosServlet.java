

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.upinvestimentos.dao.GastosDAO;
import br.com.upinvestimentos.dao.GastosEntradasDAO;
import br.com.upinvestimentos.dao.GastosSaidasDAO;
import br.com.upinvestimentos.model.ControleGastosEntradasModel;
import br.com.upinvestimentos.utils.Parsers;

/**
 * Servlet implementation class ControleGastosServlet
 */
@WebServlet("/ControleGastosServlet")
public class ControleGastosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private GastosDAO GastoDAO;
	private GastosEntradasDAO GastosEntradaDAO;
	private GastosSaidasDAO GastosSaidaDAO;
	
    public ControleGastosServlet() {
        super();
        Gastodao = new GastosDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("ControleFinanceiro.jsp").forward(request, response);
    }
    
    // Adicionar nova transacao no controle financeiro
    // Parametros de entrada da tela de controle financeiro
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       	Date dataTransacao = Parsers.parseStringToDate(request.getParameter("Data"));
    	String TipoTransacao = request.getParameter("Tipo de transacao");
    	String descricao = request.getParameter("Descricao");
    	String categoria = request.getParameter("Categoria");
    	double valor = Parsers.parseBrazilianCurrency(request.getParameter("valor"));
        
   // Executa a inserção de dados
   //Salva valores de -> ENTRADA <-
   if ("Entrada".equals(TipoTransacao)) {
	   ControleGastosEntradasModel entrada = new ControleGastosEntradasModel (descricao, valor);
	   GastosEntradaDAO.
	   
   }
   else if ("Saída".equals(TipoTransacao)) {
	   
	   
   }
   }
    	
    	
    	
    	
	

}
