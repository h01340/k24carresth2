package mp.CarDatabaseWeek5.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Demo {

    @Id
	// @GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
    private String name, address;

    
    public Demo() {
    }


    public Demo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "DemoClass [id=" + id + ", name=" + name + ", address=" + address + "]";
    }

    

}
