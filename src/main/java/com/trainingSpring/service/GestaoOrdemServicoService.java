package com.trainingSpring.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trainingSpring.exception.EntidadeNaoEncontradaException;
import com.trainingSpring.exception.NegocioException;
import com.trainingSpring.model.Cliente;
import com.trainingSpring.model.Comentario;
import com.trainingSpring.model.OrdemServico;
import com.trainingSpring.model.StatusOrdemServico;
import com.trainingSpring.repository.ClienteRepository;
import com.trainingSpring.repository.ComentarioRepository;
import com.trainingSpring.repository.OrdemServicoRepository;

@Service
public class GestaoOrdemServicoService {

	@Autowired
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(()-> new NegocioException("Cliente nao encontrado"));		
		ordemServico.setCliente(cliente);
		
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return ordemServicoRepository.save(ordemServico);
	}
	
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);		
		Comentario comentario = new Comentario();
		
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
	}


	private OrdemServico buscar(Long ordemServicoId) {
		OrdemServico ordemServico= ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(()-> new EntidadeNaoEncontradaException("Ordem servico nao encontrado"));
		return ordemServico;
	}
	
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		ordemServicoRepository.save(ordemServico);
	}
	
	
}
