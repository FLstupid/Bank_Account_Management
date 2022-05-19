package DAO;

import java.util.List;

public interface DAO<T> {
	void save(T t);
	void update(T t);
	void delete(int id);
	T get(int id);
	List<T> getAll();
}
