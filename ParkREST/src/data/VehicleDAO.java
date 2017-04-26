package data;

import java.util.List;

import entities.Vehicle;

public interface VehicleDAO {
	public List<Vehicle> index();

	public Vehicle show(int id);

	public Vehicle create(Vehicle v, Integer userId);

	public Vehicle update(int id, Vehicle v);

	public boolean destroy(int id);
}
