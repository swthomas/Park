package data;

import entities.Lister;

public interface ListerDAO {
	public Lister show(Integer id);
	public Lister create(Integer userId, Lister lister);
	public Lister update(Integer id, Lister lister);
	public Boolean destroy(Integer id);
}
