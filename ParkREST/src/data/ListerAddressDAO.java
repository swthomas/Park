package data;

import entities.ListerAddress;

public interface ListerAddressDAO {
	public ListerAddress show(Integer id);
	public Boolean destroy(Integer id);
	public ListerAddress createListerAddress(Integer listerId, ListerAddress a);
//	public Address createParkingSpotAddress(Integer parkingSpotId, Address a);
	public ListerAddress update(Integer id, ListerAddress a);
}
