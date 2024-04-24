package mp.CarDatabaseWeek5;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import mp.CarDatabaseWeek5.domain.Car;
import mp.CarDatabaseWeek5.domain.CarRepository;
import mp.CarDatabaseWeek5.domain.Demo;
import mp.CarDatabaseWeek5.domain.DemoRepository;
import mp.CarDatabaseWeek5.domain.Owner;
import mp.CarDatabaseWeek5.domain.OwnerRepository;

@SpringBootApplication
public class CarDatabaseWeek5Application {

	private static final Logger log = LoggerFactory.getLogger(CarDatabaseWeek5Application.class);

	public static void main(String[] args) {
		SpringApplication.run(CarDatabaseWeek5Application.class, args);
	}

	@Bean
	public CommandLineRunner demoData(CarRepository carRepository
	, OwnerRepository ownerRepository
	, DemoRepository demoRepository) {
		return (args) -> {

			log.info("save owners");
			ownerRepository.save(new Owner("Kia", "Watson", 1978));
			ownerRepository.save(new Owner("Aku", "Ankka", 1940));
			ownerRepository.save(new Owner("Iines", "Ankka", 1941));
			log.info("tulostetaan omistajat");
			for (Owner owner : ownerRepository.findAll()) {
				log.info(owner.toString());
			}

			log.info("save some cars");
			carRepository.save(new Car("Ford", "Mustang", ownerRepository.findByLastName("Watson").get(0)));
			carRepository.save(new Car("Nissan", "Leaf", ownerRepository.findByLastName("Watson").get(0)));
			carRepository.save(new Car("Toyota", "Prius", ownerRepository.findByLastName("Ankka").get(0)));
			carRepository.save(new Car("Toyota", "Prius"));

			log.info("tulostetaan autot");
			for (Car car : carRepository.findAll()) {
				log.info(car.toString());
			}

			//This one is just for demo purposes
			demoRepository.save(new Demo("Minna", "Helsinki"));
			demoRepository.save(new Demo("Sari", "Vaattojärvi"));

		};
	}
}
