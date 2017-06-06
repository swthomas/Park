package data;

import entities.ListerAddress;

public interface ListerAddressDAO {
	public ListerAddress show(Integer listerId);
	public ListerAddress update(Integer listerId, ListerAddress address);
}
