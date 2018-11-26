package com.core.persist;

/**
 * @author Debadatta Mishra(PIKU)
 *
 */
public class Project 
{
	private int projectId = 0;
	private String pojectName = null;
	public Project()
	{
		super();
	}
	public String getPojectName() {
		return pojectName;
	}
	public void setPojectName(String pojectName) {
		this.pojectName = pojectName;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
}
