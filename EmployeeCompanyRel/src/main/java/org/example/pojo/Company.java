package org.example.pojo;

import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cmpId;
	private String name;
	private String address;
	private String city;
	private String state;
	private String country;
	@Nullable
	private int zipCode;
	
	public Company(Long cmpId, String name, String address, String city, String state, String country, int zipCode) {
		super();
		this.cmpId = cmpId;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.country = country;
		this.zipCode = zipCode;
	}

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public Long getCmpId() {
		return cmpId;
	}

	public void setCmpId(Long cmpId) {
		this.cmpId = cmpId;
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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "Company [cmpId=" + cmpId + ", name=" + name + ", address=" + address + ", city=" + city + ", state="
				+ state + ", country=" + country + ", zipCode=" + zipCode + "]";
	}

}
