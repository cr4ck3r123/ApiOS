package modelo;

import javax.persistence.Entity;

@Entity
public class Mensagens {
	
	private String mensagens;
	private String usuarios;
	
	public Mensagens() {
		// TODO Auto-generated constructor stub
	}

	public String getMensagens() {
		return mensagens;
	}

	public void setMensagens(String mensagens) {
		this.mensagens = mensagens;
	}

	public String getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(String usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
