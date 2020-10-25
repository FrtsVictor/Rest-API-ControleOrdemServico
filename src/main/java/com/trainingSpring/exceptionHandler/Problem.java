package com.trainingSpring.exceptionHandler;


import java.time.OffsetDateTime;
import java.util.List;
import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.util.ArrayList;
import java.util.Collections;

@JsonInclude(Include.NON_NULL)
public class Problem {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Campo> campos;



	@Generated("SparkTools")
	private Problem(Builder builder) {
		this.status = builder.status;
		this.dataHora = builder.dataHora;
		this.titulo = builder.titulo;
		this.campos = builder.campos;
	}
	
	
	
	public Problem() {
		// TODO Auto-generated constructor stub
	}



	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public OffsetDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(OffsetDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Campo> getCampos() {
		return campos;
	}


	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}


	static class Campo{
		private String nome;
		private String mensagem;
		
		
		
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}
	}


	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	@Generated("SparkTools")
	public static final class Builder {
		private Integer status;
		private OffsetDateTime dataHora;
		private String titulo;
		private List<Campo> campos = Collections.emptyList();

		private Builder() {
		}

		public Builder Status(Integer status) {
			this.status = status;
			return this;
		}

		public Builder DataHora(OffsetDateTime dataHora) {
			this.dataHora = dataHora;
			return this;
		}

		public Builder Titulo(String titulo) {
			this.titulo = titulo;
			return this;
		}

		public Builder Campos(List<Campo> campos) {
			this.campos = campos;
			return this;
		}

		public Problem build() {
			return new Problem(this);
		}

		public Builder Campos(ArrayList<Object> campos2) {
			// TODO Auto-generated method stub
			return null;
		}
	}



}
