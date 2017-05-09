package edu.cmpe275.termproject.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class User {
	
	@Column(name="ID_JS")
	
	private long id_jobSeeker;
	
	@Column(name="ID_CMP")
	
	private long id_company;
	
	@Id
	@Column(name="USERNAME", nullable=false, unique= true)
	private String username;
	
	@Column(name="PASSWORD")
	
	private String password;
	
	@Column(name="EMAIL", nullable=false, unique= true)
	
	private String emailID;
	
	@Column(name="AUTH_STATUS")
	
	private String isAuthenticated;
	
	@Column(name="USERTYPE")
	
	private int userType;
	
	
	
	public User(){}
	
	
	public User(long id_jobSeeker, long id_company, String username, String password, String emailID,
			String isAuthenticated, int userType) {
		super();
		this.id_jobSeeker = id_jobSeeker;
		this.id_company = id_company;
		this.username = username;
		this.password = password;
		this.emailID = emailID;
		this.isAuthenticated = isAuthenticated;
		this.userType = userType;
	}
	public long getId_jobSeeker() {
		return id_jobSeeker;
	}
	public void setId_jobSeeker(long id_jobSeeker) {
		this.id_jobSeeker = id_jobSeeker;
	}
	public long getId_company() {
		return id_company;
	}
	public void setId_company(long id_company) {
		this.id_company = id_company;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public String getIsAuthenticated() {
		return isAuthenticated;
	}
	public void setIsAuthenticated(String isAuthenticated) {
		this.isAuthenticated = isAuthenticated;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	

}
