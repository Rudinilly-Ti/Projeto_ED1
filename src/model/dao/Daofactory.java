package model.dao;
import db.*;
import model.dao.impl.TarefaDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class Daofactory {
  public static TarefaDao createTarefaDao() {
		return new TarefaDaoJDBC(DB.getConnection());
	}
	
	public static UsuarioDao createUsuarioDao() {
		return new UsuarioDaoJDBC(DB.getConnection());
	}
}
