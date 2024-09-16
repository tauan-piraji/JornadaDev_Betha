package com.tauanoliveira.cursoMC.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.tauanoliveira.cursoMC.domain.Cidade;
import com.tauanoliveira.cursoMC.domain.Cliente;
import com.tauanoliveira.cursoMC.domain.Endereco;
import com.tauanoliveira.cursoMC.domain.enums.Perfil;
import com.tauanoliveira.cursoMC.domain.enums.TipoCliente;
import com.tauanoliveira.cursoMC.dto.ClienteDTO;
import com.tauanoliveira.cursoMC.dto.ClienteNewDTO;
import com.tauanoliveira.cursoMC.repositories.ClienteRepository;
import com.tauanoliveira.cursoMC.repositories.EnderecoRepository;
import com.tauanoliveira.cursoMC.segurity.UserSS;
import com.tauanoliveira.cursoMC.services.exception.AuthorizationException;
import com.tauanoliveira.cursoMC.services.exception.DataIntegrityException;
import com.tauanoliveira.cursoMC.services.exception.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCrypt;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private S3Service s3Service;

	@Autowired
	private ImageService imageService;

	@Value("${img.prefix.client.profile}")
	private String prefix;

	@Value("${img.profile.size}")
	private Integer size;

	public Cliente find(Integer id) throws ObjectNotFoundException{
		UserSS user = UserService.authenticated();//usuario logado
		if(user == null || !user.hasRole(Perfil.ADMIN) && !id.equals(user.getId())) {
			throw new AuthorizationException("Acesso negado");
		}

		Optional<Cliente> obj = clienteRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}

	public Cliente fromDTO(ClienteDTO objDTO) {
		return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO objDTO) {
		Cliente cli = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipoCliente()), bCrypt.encode(objDTO.getSenha()));
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
		return clienteRepository.save(newObj);
	}

	@Transactional
	public Cliente insert(Cliente obj) {
		obj = clienteRepository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		}catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não E possível Excluir porque há dominios relacionadas");
		}
	}
	
	public URI uploadProfilePicture(MultipartFile multipartFile) {//upload imagem de perfil no s3
		UserSS user = UserService.authenticated();//verifica se usuario esta logado
		if(user == null) {
			throw new AuthorizationException("acesso negado");
 		}
		
		BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);//arquivo da imagem
		jpgImage = imageService.cropSquare(jpgImage);//busca o metodo que recorta a img
		jpgImage = imageService.resize(jpgImage, size);//Busca o metodo que ajusta a dimenção da img
		String fileName = prefix + user.getId() + ".jpg";//cria o nome da img

		return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");//salva img no s3
	}
} 