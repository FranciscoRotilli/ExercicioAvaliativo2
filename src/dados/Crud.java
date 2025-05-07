package dados;

public abstract interface Crud {

	public abstract boolean create(Object o);

	public abstract Object retrieve(int codigo);

	public abstract boolean update(int codigo, Object o);

	public abstract boolean delete(int codigo);

}
