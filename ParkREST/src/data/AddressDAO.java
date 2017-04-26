package data;

import entities.Address;

public interface AddressDAO {
	public Address show(Integer id);
	public Boolean destroy(Integer id);
	public Address create(Integer userId, Address a);
	public Address update(Integer id, Address a);
}
