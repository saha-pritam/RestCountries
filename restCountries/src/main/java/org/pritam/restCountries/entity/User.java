package org.pritam.restCountries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="users")
@ApiModel
public class User {
	@Id
	@Column(name="username")
	@ApiModelProperty(example = "einstein")
	private String username;
	@Column(name="password")
	@ApiModelProperty(example = "einstein@123")
	private String password;
	@Column(name="enabled")
	@ApiModelProperty(example = "true")
	private boolean enabled;
	public User() {
	}
	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
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
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
