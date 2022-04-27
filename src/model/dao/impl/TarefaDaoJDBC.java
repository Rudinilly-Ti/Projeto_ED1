package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.TarefaDao;
import model.entities.Usuario;
import model.entities.Tarefa;

public class TarefaDaoJDBC implements TarefaDao {

	private Connection conn;

	public TarefaDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Tarefa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("INSERT INTO Tarefa " + "(Name, Email, BirthDate, BaseSalary, UsuarioId) "
					+ "VALUES " + "(?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getCabecalho());
			st.setString(2, obj.getConteudo());
			st.setDate(3, new java.sql.Date(obj.getData().getTime()));
			st.setBoolean(4, obj.isFavorito());
			st.setInt(5, obj.getUsuario().getUsuarioId());

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error! No rows affected!");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void update(Tarefa obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("UPDATE Tarefa "
					+ "SET Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, UsuarioId = ? " + "WHERE Id = ?");

			st.setString(1, obj.getCabecalho());
			st.setString(2, obj.getConteudo());
			st.setDate(3, new java.sql.Date(obj.getData().getTime()));
			st.setBoolean(4, obj.isFavorito());
			st.setInt(5, obj.getUsuario().getUsuarioId());
			st.setInt(6, obj.getId());

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM Tarefa WHERE Id = ?");

			st.setInt(1, id);

			st.executeUpdate();
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}
	}

	@Override
	public Tarefa findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Tarefa.*,Usuario.Name as DepName " + "FROM Tarefa INNER JOIN Usuario "
							+ "ON Tarefa.UsuarioId = Usuario.Id " + "WHERE Tarefa.Id = ?");

			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Usuario dep = instantiateUsuario(rs);
				Tarefa obj = instantiateTarefa(rs, dep);
				return obj;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Tarefa instantiateTarefa(ResultSet rs, Usuario dep) throws SQLException {
		Tarefa obj = new Tarefa();
		obj.setId(rs.getInt("Id"));
		obj.setCabecalho(rs.getString("Name"));
		obj.setConteudo(rs.getString("Email"));
		obj.setFavorito(rs.getBoolean("BaseSalary"));
		obj.setData(new java.util.Date(rs.getTimestamp("BirthDate").getTime()));
		obj.setUsuario(dep);
		return obj;
	}

	private Usuario instantiateUsuario(ResultSet rs) throws SQLException {
		Usuario dep = new Usuario();
		dep.setUsuarioId(rs.getInt("UsuarioId"));
		dep.setNome(rs.getString("DepName"));
		return dep;
	}

	@Override
	public List<Tarefa> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Tarefa.*,Usuario.Name as DepName " + "FROM Tarefa INNER JOIN Usuario "
							+ "ON Tarefa.UsuarioId = Usuario.Id " + "ORDER BY Name");

			rs = st.executeQuery();

			List<Tarefa> list = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();

			while (rs.next()) {

				Usuario dep = map.get(rs.getInt("UsuarioId"));

				if (dep == null) {
					dep = instantiateUsuario(rs);
					map.put(rs.getInt("UsuarioId"), dep);
				}

				Tarefa obj = instantiateTarefa(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Tarefa> findByUsuario(Usuario Usuario) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT Tarefa.*,Usuario.Name as DepName " + "FROM Tarefa INNER JOIN Usuario "
							+ "ON Tarefa.UsuarioId = Usuario.Id " + "WHERE UsuarioId = ? " + "ORDER BY Name");

			st.setInt(1, Usuario.getUsuarioId());

			rs = st.executeQuery();

			List<Tarefa> list = new ArrayList<>();
			Map<Integer, Usuario> map = new HashMap<>();

			while (rs.next()) {

				Usuario dep = map.get(rs.getInt("UsuarioId"));

				if (dep == null) {
					dep = instantiateUsuario(rs);
					map.put(rs.getInt("UsuarioId"), dep);
				}

				Tarefa obj = instantiateTarefa(rs, dep);
				list.add(obj);
			}
			return list;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}
}