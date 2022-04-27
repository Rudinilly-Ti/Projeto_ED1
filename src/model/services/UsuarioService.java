package model.services;

import java.util.List;

import model.dao.Daofactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

public class UsuarioService {
	
	private UsuarioDao dao = Daofactory.createUsuarioDao();
	
	public List<Usuario> findAll(){
		return dao.findAll();
	}
	
	public void saveOrUpdate(Usuario obj) {
		if (obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	
	public void remove(Usuario obj) {
		dao.deleteById(obj);
	}
}