package data;

import entities.ParkingSpotAddress;

public interface ParkingSpotAddressDAO {
	public ParkingSpotAddress show(Integer id);
	public Boolean destroy(Integer id);
//	public ParkingSpotAddress create(Integer listerId, ParkingSpotAddress a);
	public ParkingSpotAddress update(Integer id, ParkingSpotAddress a);
}
