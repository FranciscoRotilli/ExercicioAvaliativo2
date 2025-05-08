package dados;

public class Ave extends Pet {
	private boolean voa;

	public Ave(String nome, double valorBase, boolean voa) {
		super(nome, valorBase);
		this.voa = voa;
	}

	@Override
	public double calculaValor() {
		return voa ? (getValorBase() * 1.3) : getValorBase();
	}
}
