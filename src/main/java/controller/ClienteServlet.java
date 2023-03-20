package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Cliente;
import persistence.ClienteDao;
import persistence.GenericDao;
import persistence.IClienteDao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ClienteServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cpf = request.getParameter("cpf");
    	String nome = request.getParameter("nome");
    	String email = request.getParameter("email");
    	String limiteCreditoStr = request.getParameter("limiteCredito");
    	String dataNascimentoStr = request.getParameter("dataNascimento");
    	String botao = request.getParameter("botao");
    	
    	Cliente c = new Cliente();
    	
    	GenericDao gDao = new GenericDao();
    	IClienteDao cDao = new ClienteDao(gDao);
    	String erro = "";
    	String saida = "";
    	List<Cliente> clientes = new ArrayList<Cliente>();
    	
    	try {
    		if (botao.equals("Listar")) {
    			clientes = cDao.consultaClientes();
    		}
    		if (botao.equals("Inserir")) {
    			c = validar(cpf, nome, email, limiteCreditoStr, dataNascimentoStr, botao);
    			saida = cDao.insereCliente(c);
    			c = new Cliente();
    		}
    		if (botao.equals("Atualizar")) {
    			c = validar(cpf, nome, email, limiteCreditoStr, dataNascimentoStr, botao);
    			saida = cDao.atualizaCliente(c);
    			c = new Cliente();
    		}
    		if (botao.equals("Excluir")) {
    			c = validar(cpf, nome, email, limiteCreditoStr, dataNascimentoStr, botao);
    			saida = cDao.excluiCliente(c);
    			c = new Cliente();
    		}
    		if (botao.equals("Buscar")) {
    			c = validar(cpf, nome, email, limiteCreditoStr, dataNascimentoStr, botao);
    			c = cDao.buscaCliente(c);
    		}
    		
    	} catch(SQLException | ClassNotFoundException | IOException e) {
    		erro = e.getMessage();
    	} finally {
    		RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
    		request.setAttribute("cliente", c);
    		request.setAttribute("clientes", clientes);
    		request.setAttribute("erro", erro);
    		request.setAttribute("saida", saida);
    		rd.forward(request, response);
    	}
    }

    private Cliente validar(String cpf, String nome, String email, String limiteCredito, String dataNascimento, String botao) throws IOException {
    	Cliente c = new Cliente();
    	
    	if (botao.equals("Inserir")) {
    		if (cpf.equals("") || nome.equals("") || email.equals("") || limiteCredito.equals("") || dataNascimento.equals("")) {
    			throw new IOException("Preencha todos os campos");
    		} else {
    			c.setCpf(cpf);
    			c.setNome(nome);
    			c.setEmail(email);
    			c.setLimiteCredito(Double.parseDouble(limiteCredito));
    			c.setDataNascimento(dataNascimento);
    		}
    	}
    	
    	if (botao.equals("Atualizar")) {
    		if (cpf.equals("") || nome.equals("") || email.equals("") || limiteCredito.equals("") || dataNascimento.equals("")) {
    			throw new IOException("Preencha todos os campos");
    		} else {
    			c.setCpf(cpf);
    			c.setNome(nome);
    			c.setEmail(email);
    			c.setLimiteCredito(Double.parseDouble(limiteCredito));
    			c.setDataNascimento(dataNascimento);
    		}
    	}
    	
    		if (botao.equals("Excluir")) {
    			if (cpf.equals("")) {
    				throw new IOException("Preencha o CPF");
    			} else {
    				c.setCpf(cpf);
    			}
    		}
    		
    		if (botao.equals("Buscar")) {
    			if (cpf.equals("")) {
    				throw new IOException("Preencha o CPF");
    			} else {
    				c.setCpf(cpf);
    			}
    		}
    		
    		return c;
    	}
}
