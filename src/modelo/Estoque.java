package modelo;

public class Estoque {
	
	
	private int idestoque;
	private String descricao;
	private int qtde;
	private double valor;
	
	public Estoque() {
		super();
	}

	public int getIdestoque() {
		return idestoque;
	}

	public void setIdestoque(int idestoque) {
		this.idestoque = idestoque;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtde() {
		return qtde;
	}

	public void setQtde(int qtde) {
		this.qtde = qtde;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	
	

}
