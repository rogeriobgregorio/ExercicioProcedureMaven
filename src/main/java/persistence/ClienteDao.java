package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {
	
	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereCliente(Cliente c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		
		String sql = "{CALL sp_inserir_cliente(?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, c.getCpf());
		cs.setString(2, c.getNome());
		cs.setString(3, c.getEmail());
		cs.setDouble(4, c.getLimiteCredito());
		cs.setDate(5, (Date) c.getDataNascimento());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		con.close();
		
		return saida;
	}

	@Override
	public String atualizaCliente(Cliente c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		
		String sql = "{CALL sp_atualizar_cliente(?,?,?,?,?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, c.getCpf());
		cs.setString(2, c.getNome());
		cs.setString(3, c.getEmail());
		cs.setDouble(4, c.getLimiteCredito());
		cs.setDate(5, (Date) c.getDataNascimento());
		cs.registerOutParameter(6, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(6);
		
		cs.close();
		con.close();
		
		return saida;
	}

	@Override
	public String excluiCliente(Cliente c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		
		String sql = "{CALL sp_deletar_cliente(?,?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, c.getCpf());
		cs.registerOutParameter(2, Types.VARCHAR);
		cs.execute();
		
		String saida = cs.getString(2);
		
		cs.close();
		con.close();
		
		return saida;
	}

	@Override
	public Cliente buscaCliente(Cliente c) throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		
		String sql = "{CALL sp_buscar_cliente(?,?)}";
		
		CallableStatement cs = con.prepareCall(sql);
		cs.setString(1, c.getCpf());
		cs.registerOutParameter(2, Types.VARCHAR);
		ResultSet rs = cs.executeQuery();
		
		if (rs.next()) {
            c.setCpf(rs.getString("cpf"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setLimiteCredito(rs.getDouble("limite_de_credito"));
            c.setDataNascimento(rs.getDate("dt_nascimento"));
		}
		
		String saida = cs.getString(2);
		System.out.println(saida);
		
		rs.close();
		cs.close();
		con.close();
		
		return c;
	}

	@Override
	public List<Cliente> consultaClientes() throws SQLException, ClassNotFoundException {
		Connection con = gDao.getConnection();
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		String sql = "{CALL sp_listar_clientes(?)}";
		CallableStatement cs = con.prepareCall(sql);
		cs.registerOutParameter(1, Types.VARCHAR);
		ResultSet rs = cs.executeQuery();
		
		while (rs.next()) {
            Cliente c = new Cliente();
            c.setCpf(rs.getString("cpf"));
            c.setNome(rs.getString("nome"));
            c.setEmail(rs.getString("email"));
            c.setLimiteCredito(rs.getDouble("limite_de_credito"));
            c.setDataNascimento(rs.getDate("dt_nascimento"));

            clientes.add(c);
        }
		
		String saida = cs.getString(1);
		System.out.println(saida);
		
		rs.close();
		cs.close();
		con.close();
		
		return clientes;
	}

}