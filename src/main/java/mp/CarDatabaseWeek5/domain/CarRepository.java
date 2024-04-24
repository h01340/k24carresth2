package mp.CarDatabaseWeek5.domain;

import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {

	public Car findByOwnerId(Long id);

}
