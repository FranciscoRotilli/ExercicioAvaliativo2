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
		return pelo == Pelo.CURTO ? (getValorBase() * 1.1) : pelo == Pelo.MEDIO ? (getValorBase() * 1.2) : getValorBase() * 1.6;
	}
}
