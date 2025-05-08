package dados;

public abstract class Pet {
	private int codigo;
	private String nome;
	private double valorBase;

	public Pet(int codigo, String nome, double valorBase) {
		this.codigo = codigo;
		this.nome = nome;
		this.valorBase = valorBase;
	}

	public abstract double calculaValor();

	public int getCodigo() {
		return codigo;
	}
	public String getNome() {
		return nome;
	}
	public double getValorBase() {
		return valorBase;
	}
}
