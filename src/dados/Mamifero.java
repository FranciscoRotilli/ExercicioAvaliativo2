package dados;

public class Mamifero extends Pet {
	private double peso;
	private Pelo pelo;

	public Mamifero(String nome, double valorBase, double peso, Pelo pelo) {
        super(nome, valorBase);
        this.peso = peso;
		this.pelo = pelo;
	}

	@Override
	public double calculaValor() {
		return 0;
	}
}
