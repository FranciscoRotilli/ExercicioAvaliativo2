package dados;

public abstract class Pet {
	private final int codigo;
	private final String nome;
	private final double valorBase;

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
