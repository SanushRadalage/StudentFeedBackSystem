package com.feedback.model;

/**
 * This class provide admin variables. Inherit by Lecturer class.<br><br>
 * Find the repository - https://github.com/SanushRadalage/StudentFeedbackSystem
 * 
 * @author Maleeka Sanush Radalage
 */
public class Admin extends Lecturer {

	private static final long serialVersionUID = -2567828114301586478L;
	private String password;

	public Admin(int staffID, String name, String password) {
		super(staffID, name);
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
