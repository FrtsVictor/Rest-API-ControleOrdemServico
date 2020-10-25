package com.trainingSpring.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingSpring.DTO.OrdemServicoDTO;
import com.trainingSpring.DTO.OrdemServicoInput;
import com.trainingSpring.model.Cliente;
import com.trainingSpring.model.OrdemServico;
import com.trainingSpring.repository.OrdemServicoRepository;
import com.trainingSpring.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordem-servico")
public class OrdemServicoController {

	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired //para mapear dados de uma entidade para outra criando os dtos
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<OrdemServicoDTO> listar(){
		return toCollectionModelDTO(ordemServicoRepository.findAll());
	}
	
	@GetMapping("/{ordemServicoId}")
	public ResponseEntity<OrdemServicoDTO> findId(@PathVariable Long ordemServicoId) {
		 Optional<OrdemServico> ordemServico =  ordemServicoRepository.findById(ordemServicoId);
		 
		 if(ordemServico.isPresent()) {
			 OrdemServicoDTO modelDTO = toDTOModel(ordemServico.get());
			 return ResponseEntity.ok(modelDTO);
		 }
		 
		 return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDTO criar(@Valid @RequestBody OrdemServicoInput ordemServicoInput) {	
		OrdemServico ordemServico = DTOtoEntity(ordemServicoInput);
		return toDTOModel(gestaoOrdemServico.criar(ordemServico));
	}

	
	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) {
		gestaoOrdemServico.finalizar(ordemServicoId);
	}
	
	
	//Converte para DTO
	private OrdemServicoDTO toDTOModel(OrdemServico ordemServico) {
		return modelMapper.map(ordemServico, OrdemServicoDTO.class);
	}
	
	
	private List<OrdemServicoDTO> toCollectionModelDTO(List<OrdemServico> ordensServico){
		//stream returna um fluxo de elementos
		return ordensServico.stream()
				.map(ordemServico -> toDTOModel(ordemServico))
				.collect(Collectors.toList());
			//collectors capta os dados que foram percorridos pela stream e retorna o array modificado
	}
	
	
	private OrdemServico DTOtoEntity(OrdemServicoInput ordemServicoInput ) {
		return modelMapper.map(ordemServicoInput, OrdemServico.class);
	}
	
	
	
}
