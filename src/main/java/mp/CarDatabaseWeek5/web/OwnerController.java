package mp.CarDatabaseWeek5.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import mp.CarDatabaseWeek5.domain.CarRepository;
import mp.CarDatabaseWeek5.domain.Owner;
import mp.CarDatabaseWeek5.domain.OwnerRepository;

@Controller
public class OwnerController {
	private static final Logger log = LoggerFactory.getLogger(OwnerController.class);

	@Autowired
	OwnerRepository ownerRepository;

	@Autowired
	CarRepository carRepository;

	@GetMapping("owner/ownerlist")
	public String getOwners(Model model) {
		log.info("show all owners");
		model.addAttribute("owners", ownerRepository.findAll());
		return "/owner/ownerList";
	}

	@GetMapping("owner/addOwner")
	public String addOwner(Model model) {
		model.addAttribute("uusiOmistaja", new Owner());
		return "owner/addOwner";
	}

	@PostMapping("owner/saveOwner")
	public String saveOwner(@Valid @ModelAttribute("uusiOmistaja") Owner owner, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("some error happened");
			return "/owner/addOwner";
		}
		ownerRepository.save(owner);
		return "redirect:/owner/ownerlist";
	}

	@GetMapping("owner/deleteOwner/{id}")
	public String deleteOwner(@PathVariable("id") Long id, Model model) {
		System.out.println("delete owner " + id);
		if (ownerRepository.findById(id).get().getCars().isEmpty()) {
			ownerRepository.deleteById(id);
		} else {
			System.out.println("Can't remove a owner that has cars.");
		}

		return "redirect:/owner/ownerlist";
	}

}
