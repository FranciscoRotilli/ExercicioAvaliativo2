package dados;

import java.util.List;
import java.util.Collection;

public class Bicharada implements Crud {

	private List<Pet> pets;

	private Collection<Pet> pet;

	public Bicharada() {

	}


	/**
	 * @see dados.Crud#create(java.lang.Object)
	 */
	public boolean create(Object o) {
		return false;
	}


	/**
	 * @see dados.Crud#retrieve(int)
	 */
	public Object retrieve(int codigo) {
		return null;
	}


	/**
	 * @see dados.Crud#update(int, java.lang.Object)
	 */
	public boolean update(int codigo, Object o) {
		return false;
	}


	/**
	 * @see dados.Crud#delete(int)
	 */
	public boolean delete(int codigo) {
		return false;
	}

}
