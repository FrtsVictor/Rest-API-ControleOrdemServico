package com.trainingSpring.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trainingSpring.DTO.ComentarioDTO;
import com.trainingSpring.DTO.ComentarioInput;
import com.trainingSpring.exception.EntidadeNaoEncontradaException;
import com.trainingSpring.model.Comentario;
import com.trainingSpring.model.OrdemServico;
import com.trainingSpring.repository.OrdemServicoRepository;
import com.trainingSpring.service.GestaoOrdemServicoService;

@RestController
@RequestMapping("/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

	
	@Autowired
	private GestaoOrdemServicoService gestaoOrdemServico;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ComentarioDTO adicionar(
									@PathVariable Long ordemServicoId,
									@Valid @RequestBody ComentarioInput comentarioInput) {
		
		Comentario comentario = gestaoOrdemServico.adicionarComentario(
				ordemServicoId, comentarioInput.getDescricao());
		return DTOtoModel(comentario);
	}
	
	
	@GetMapping
	public List<ComentarioDTO> listar(@PathVariable Long ordemServicoId){
		OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de servico nao encontrada"));
		//lista de comentarios pega do nosso banco a lista referente ao id
		return toCollectionDTO(ordemServico.getComentarios());
	}	
			
	
	private ComentarioDTO DTOtoModel(Comentario comentario) {
		return modelMapper.map(comentario, ComentarioDTO.class); 
	}
	
	
	private List<ComentarioDTO> toCollectionDTO (List<Comentario> comentarios){
		return comentarios.stream()
				.map(comentario -> DTOtoModel(comentario))
				.collect(Collectors.toList());
	}
	
}
