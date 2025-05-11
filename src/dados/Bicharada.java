package dados;
import java.util.ArrayList;

public class Bicharada implements Crud {
	private final ArrayList<Pet> pets;

	public Bicharada() {
		pets = new ArrayList<>();
	}

	@Override
	public boolean create(Object o) {
		return pets.add((Pet) o);
	}

	@Override
	public Object retrieve(int codigo) {
		for (Pet pet : pets) {
			if (pet.getCodigo() == codigo) {
				return pet;
			}
		}
		return null;
	}

	@Override
	public boolean update(int codigo, Object o) {
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getCodigo() == codigo) {
				pets.set(i, (Pet) o);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean delete(int codigo) {
		for (int i = 0; i < pets.size(); i++) {
			if (pets.get(i).getCodigo() == codigo) {
				pets.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean verificaCodigoUnico(int codigo) {
		for (Pet pet : pets) {
			if (pet.getCodigo() == codigo) return false;
		}
		return true;
	}

	public ArrayList<Double> getValoresPets() {
		ArrayList<Double> valores = new ArrayList<>();
        for (Pet pet : pets) {
			valores.add(pet.calculaValor());
        }
		return valores;
	}

	public boolean isEmpty() {
		return pets.isEmpty();
	}

	public Pet getMaiorValorPet() {
		if (pets.isEmpty()) return null;

		double maiorValor = 0;
		Pet maiorPet = pets.getFirst();
		for (Pet pet : pets) {
			double valor = pet.getValorBase();
			if (valor > maiorValor) {
				maiorValor = valor;
				maiorPet = pet;
			}
		}
		return maiorPet;
	}

	public int qtdAvesVoam() {
		int qtd = 0;
		for (Pet pet : pets) {
			if (pet instanceof Ave ave && ave.getVoa()) qtd++;
		}
		return qtd;
	}

	public Mamifero mamiferoPeloCurtoMaisPesado() {
		if (pets.isEmpty()) return null;
		double peso = 0;
		Mamifero mamifero = null;
		for (Pet pet : pets) {
			if (pet instanceof Mamifero mam && mam.getPelo() == Pelo.CURTO && mam.getPeso() > peso) {
				peso = mam.getPeso();
				mamifero = mam;
			}
		}
		return mamifero;
	}

	public int contPelo(Pelo pelo) {
		int cont = 0;
		for (Pet pet : pets) {
			if (pet instanceof Mamifero mam && mam.getPelo() == pelo) cont++;
		}
		return cont;
	}
}
