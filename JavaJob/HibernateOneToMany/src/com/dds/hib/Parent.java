package com.dds.hib;

import java.util.Collection;
import java.util.HashSet;

public class Parent 
{
	private Long id;
	private String name;
	private Collection children = new HashSet();
	public Collection getChildren() {
		return children;
	}
	public void setChildren(Collection children) {
		this.children = children;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
