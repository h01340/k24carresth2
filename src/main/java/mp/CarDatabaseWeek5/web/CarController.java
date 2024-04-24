package mp.CarDatabaseWeek5.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import mp.CarDatabaseWeek5.domain.Car;
import mp.CarDatabaseWeek5.domain.CarRepository;
import mp.CarDatabaseWeek5.domain.OwnerRepository;

@Controller
public class CarController {

	private static final Logger log = LoggerFactory.getLogger(CarController.class);

	// DI: injektoidaan repository controller-luokan käyttöön
	// injektointi tehdään konstruktorin avulla
	private final CarRepository carRepository;
	private final OwnerRepository ownerRepository;

	public CarController(CarRepository carRepository, OwnerRepository ownerRepository) {
		this.carRepository = carRepository;
		this.ownerRepository = ownerRepository;
	}

	@GetMapping(value = { "/", "main" })
	public String showMainPage() {
		log.info("open main page");
		return "main";
	}

	@GetMapping("/carlist")
	public String showCars(Model model) {
		log.info("Read cars from database..");
		model.addAttribute("cars", carRepository.findAll());
		return "carList";
	}

	@GetMapping("/newcar")
	public String addCar(Model model) {
		log.info("Lets go to create a new car...." + ownerRepository.findAll());
		model.addAttribute("car", new Car());
		model.addAttribute("owners", ownerRepository.findAll());

		return "newCar";
	}

	@PostMapping("/saveCar")
	public String saveCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model) {
		log.info("CONTROLLER: Save some the car - check validation of car: " + car);
		if (bindingResult.hasErrors()) {
			log.info("some validation error happened, car: " + car);
			model.addAttribute("editCar", car);
			model.addAttribute("owners", ownerRepository.findAll());
			return "newCar";
		}
		carRepository.save(car);
		return "redirect:carlist";
	}

	@GetMapping("editCar/{id}")
	public String editCar(@PathVariable("id") Long id, Model model) {
		// public String editCar(@RequestParam(name = "id") Long id, Model model) {
		model.addAttribute("editCar", carRepository.findById(id));
		model.addAttribute("owners", ownerRepository.findAll());
		return "editCar";
	}

	@PostMapping("/saveEditedCar")
	public String saveEditedCar(@Valid @ModelAttribute("car") Car car, BindingResult bindingResult, Model model) {
		log.info("CONTROLLER: Save some the car - check validation of car: " + car);
		if (bindingResult.hasErrors()) {
			log.info("some validation error happened, car: " + car);
			model.addAttribute("editCar", car);
			model.addAttribute("owners", ownerRepository.findAll());
			return "editCar";
		}
		carRepository.save(car);
		return "redirect:carlist";
	}

	@GetMapping("delete/{id}")
	public String deleteCar(@PathVariable("id") Long id, Model model) {
		log.info("delete car");
		carRepository.deleteById(id);
		return "redirect:/carlist";
	}

}
