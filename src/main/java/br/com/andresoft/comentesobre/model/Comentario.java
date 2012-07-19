package br.com.andresoft.comentesobre.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comentario {
	@Id
	@GeneratedValue
	private int id;
	private Calendar data;
	private String texto;
	private String email;
	private String assunto;

	public Comentario(Calendar data, String texto, String email, String assunto) {
		this.data = data;
		this.texto = texto;
		this.email = email;
		this.assunto = assunto;
	}

	public Comentario() {
		// TODO Auto-generated constructor stub
	}

	public Calendar getData() {
		return data;
	}

	public Comentario setData(Calendar data) {
		this.data = data;
		return this;
	}

	public String getTexto() {
		return texto;
	}

	public Comentario setTexto(String texto) {
		this.texto = texto;
		return this;
	}

	public String getEmail() {
		return email;
	}

	public Comentario setEmail(String email) {
		this.email = email;
		return this;
	}

	public String getAssunto() {
		return assunto;
	}

	public Comentario setAssunto(String assunto) {
		this.assunto = assunto;
		return this;
	}

	public int getId() {
		return id;
	}
}
