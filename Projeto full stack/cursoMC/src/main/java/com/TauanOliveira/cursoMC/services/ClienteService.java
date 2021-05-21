package com.TauanOliveira.cursoMC.services;

import java.util.List;
import java.util.Optional;

import com.TauanOliveira.cursoMC.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.TauanOliveira.cursoMC.domain.Cidade;
import com.TauanOliveira.cursoMC.domain.Cliente;
import com.TauanOliveira.cursoMC.domain.Endereco;
import com.TauanOliveira.cursoMC.domain.enums.TipoCliente;
import com.TauanOliveira.cursoMC.dto.ClienteDTO;
import com.TauanOliveira.cursoMC.dto.ClienteNewDTO;
import com.TauanOliveira.cursoMC.repositories.CidadeRepository;
import com.TauanOliveira.cursoMC.repositories.ClienteRepository;
import com.TauanOliveira.cursoMC.services.exception.DataIntegrityException;
import com.TauanOliveira.cursoMC.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	
	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) throws ObjectNotFoundException{
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOrCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()));
		Cidade cid = new Cidade(objDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(objDTO.getTelefone1());
		if(objDTO.getTelefone1() != null) {
			cli.getTelefones().add(objDTO.getTelefone2());
		}
		if(objDTO.getTelefone3() != null) {
			cli.getTelefones().add(objDTO.getTelefone3());
		}
		return cli;
	}

	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
	
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repo.save(newObj);
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj = repo.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não E possível Excluir porque há entidades relacionadas");
		}
	}

} 