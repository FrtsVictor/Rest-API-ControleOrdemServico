package com.trainingSpring.DTO;

import com.sun.istack.NotNull;

public class ComentarioInput {

	
	@NotNull
	private String descricao;


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
	
}
