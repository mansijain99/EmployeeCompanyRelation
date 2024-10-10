package org.example.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deptId;
	private String name;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "cmpId", name = "comapny_id")
	private Company company;

	
	
	public Department(Long deptId, String name, Company company) {
		super();
		this.deptId = deptId;
		this.name = name;
		this.company = company;
	}

	public Department() {
		// TODO Auto-generated constructor stub
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Department [deptId=" + deptId + ", name=" + name + ", company=" + company + "]";
	}
}
