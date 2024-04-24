package mp.CarDatabaseWeek5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Car {
	@Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty(message = "car's brand cannot be empty.")
	@Size(min = 2, max = 250)
	private String brand;

	@Size(min = 2, max = 100)
	private String model;

	@ManyToOne
	@JoinColumn(name = "ownerid")
	private Owner owner;

	public Car() {
		super();
	
	}

	public Car(String brand, String model) {
		super();
		this.brand = brand;
		this.model = model;
	}

	public Car(String brand, String model, Owner owner) {
		super();
		this.brand = brand;
		this.model = model;
		this.owner = owner;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Car [id=" + id + ", brand=" + brand + ", model=" + model + ", owner=" + owner + "]";
	}

}
