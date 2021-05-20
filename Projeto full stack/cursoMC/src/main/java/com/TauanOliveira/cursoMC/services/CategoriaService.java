package com.TauanOliveira.cursoMC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.TauanOliveira.cursoMC.domain.Categoria;
import com.TauanOliveira.cursoMC.dto.CategoriaDTO;
import com.TauanOliveira.cursoMC.repositories.CategoriaRepository;
import com.TauanOliveira.cursoMC.services.exception.DataIntegrityException;
import com.TauanOliveira.cursoMC.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) throws ObjectNotFoundException{
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Categoria fromDTO(CategoriaDTO objDTO) {
		return new Categoria(objDTO.getId(), objDTO.getNome());
	}

	public Categoria update(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não E possível Excluir uma categoria que possui produto");
		}
	}
	
	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());
	}
	
}