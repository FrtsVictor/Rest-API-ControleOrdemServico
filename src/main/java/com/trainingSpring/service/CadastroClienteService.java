package com.trainingSpring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingSpring.exception.NegocioException;
import com.trainingSpring.model.Cliente;
import com.trainingSpring.repository.ClienteRepository;

@Service
public class CadastroClienteService {

	@Autowired
	public ClienteRepository clienteRepository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if(clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já estie um cliente cadastrado com este email");
		}
		return clienteRepository.save(cliente);
	}
	
	
	public void excluir(Long id) {
		clienteRepository.deleteById(id);
	}
	
	
}
