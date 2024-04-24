package mp.CarDatabaseWeek5.web;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mp.CarDatabaseWeek5.domain.Car;
import mp.CarDatabaseWeek5.domain.CarRepository;
import mp.CarDatabaseWeek5.domain.OwnerRepository;

@RestController
public class RestCarController {

	private static final Logger log = LoggerFactory.getLogger(RestCarController.class);

	@Autowired
	CarRepository carRepository;
	@Autowired
	OwnerRepository ownerRepository;

	@GetMapping("/cars")
	public Iterable<Car> getCars() {
		log.info("fetch cars from db and return to client as json");
		return carRepository.findAll();
	}

	@GetMapping("/car/{id}")
	public Optional<Car> getOneCar(@PathVariable("id") Long carId) {
		log.info("fetch one car from db and return to client as json " + carId);
		return carRepository.findById(carId);
	}

	@PostMapping("/car")
	Car newCar(@RequestBody Car newCar) {
		log.info("save a new car " + newCar);
		return carRepository.save(newCar);
	}

	@PutMapping("/car/{id}")
	Car editCar(@RequestBody Car editedCar, @PathVariable Long id) {
		log.info("editCar = " + editedCar);
		log.info("edit car, id = " + id);
		editedCar.setId(id);
		log.info("editCar = " + editedCar);
		return carRepository.save(editedCar);
	}

	@DeleteMapping("/car/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable("id") Long id) {
		String message = "";
        if (!carRepository.existsById(id)) {
			message = "In database is not car with id " + id;
			//return ResponseEntity.badRequest().body(message);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        carRepository.deleteById(id);
		message = "Car with ID " + id + " deleted successfully";
        return ResponseEntity.ok().body(message);
    }

}
