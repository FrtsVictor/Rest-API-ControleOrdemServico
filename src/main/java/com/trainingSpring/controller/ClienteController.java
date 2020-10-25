package com.trainingSpring.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingSpring.model.Cliente;
import com.trainingSpring.repository.ClienteRepository;
import com.trainingSpring.service.CadastroClienteService;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private CadastroClienteService cadastroCliente;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
//	return manager.createQuery("from Cliente", Cliente.class).getResultList();

	@GetMapping()
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> findId(@PathVariable Long id) {
		 Optional<Cliente> cliente =  clienteRepository.findById(id);
		 
		 if(cliente.isPresent()) {
			 return ResponseEntity.ok(cliente.get());
		 }
		 
		 return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(
										@Valid 
										@RequestBody Cliente cliente,
										@PathVariable Long id) {
				 
		 if(!clienteRepository.existsById(id)) {
			 return ResponseEntity.notFound().build();
		 }
		 
		 cliente.setId(id);
		 cliente = clienteRepository.save(cliente);
		 
		 return ResponseEntity.ok(cliente);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id){
		
		if(!clienteRepository.existsById(id)) {
			 return ResponseEntity.notFound().build();
		 }
		
		clienteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	
}


























