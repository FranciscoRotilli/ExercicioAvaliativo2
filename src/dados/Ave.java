package dados;

public class Ave extends Pet {
	private boolean voa;

	public Ave(int codigo, String nome, double valorBase, boolean voa) {
		super(codigo, nome, valorBase);
		this.voa = voa;
	}

	@Override
	public double calculaValor() {
		return voa ? (getValorBase() * 1.3) : getValorBase();
	}

	public boolean getVoa() {
		return voa;
	}
}
