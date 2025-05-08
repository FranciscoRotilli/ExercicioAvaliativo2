package dados;
import java.util.ArrayList;

public class Bicharada implements Crud {
	private ArrayList<Pet> pets;

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
}
