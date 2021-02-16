package org.chembotula.app.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="TB_USERS")
public class AppUser extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Boolean active;
	
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
	
	public Boolean getActive() {
		return active;
	}
	
	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "\nUsername : " + username + "\nPassword : " + password + "\nActive : " + active;
	}
}
