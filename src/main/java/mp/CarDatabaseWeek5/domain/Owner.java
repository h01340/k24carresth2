package mp.CarDatabaseWeek5.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Size;

@Entity
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 1, max = 30)
	@Column(name = "firstname")
	private String firstName;
	@Size(min = 5, max = 30)
	@Column(name = "lastname")
	private String lastName;

	@Max(value = 2024, message = "max value is 2024")
	//avoid using reserved words (db), like year and date
	private int yob;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
	@JsonIgnore
	private List<Car> cars;

	public Owner() {
		super();
	}

	public Owner(String firstName, String lastName, int yob) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.yob = yob;
	}

	public Owner(String firstName, String lastName, int yob, List<Car> cars) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.yob = yob;
		this.cars = cars;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getYob() {
		return yob;
	}

	public void setYob(int yob) {
		this.yob = yob;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", yob=" + yob + "]";
	}


/* 	@Override
	public String toString() {
		return "Owner [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", yob=" + yob + ", cars="
				+ cars + "]";
	} */

}
