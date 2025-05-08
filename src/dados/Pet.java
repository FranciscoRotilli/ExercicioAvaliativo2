package dados;

public abstract class Pet {
	private static int contador = 0;
	private int codigo;
	private String nome;
	private double valorBase;

	public Pet(String nome, double valorBase) {
		this.codigo = contador++;
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
