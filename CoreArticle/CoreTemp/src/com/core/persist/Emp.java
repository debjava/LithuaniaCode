package com.core.persist;

/**
 * This is a simple java bean.
 * @author Debadatta Mishra(PIKU)
 *
 */
public class Emp 
{
	private String name = null;
	private int age = 0;
	private String empId = null;
	public Emp()
	{
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
}



