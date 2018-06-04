package com.douglas.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.douglas.cursomc.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Compulsory Filling")
	@Length(min = 5, max = 120, message = "The length has to be between 5 and 80 characters")
	private String name;
	
	@NotEmpty(message = "Compulsory Filling")
	@Email(message = "Invalid Email")
	private String email;
	
	@NotEmpty(message = "Compulsory Filling")
	private String cpfOrCnpj;
	
	private Integer type;
	
	@NotEmpty(message = "Compulsory Filling")
	private String password;
	
	@NotEmpty(message = "Compulsory Filling")
	private String patio;
	
	@NotEmpty(message = "Compulsory Filling")
	private String number;
	
	private String additional;
	private String district;
	
	@NotEmpty(message = "Compulsory Filling")
	private String zipCode;
	
	@NotEmpty(message = "Compulsory Filling")
	private String phone1;
	
	private String phone2;
	private String phone3;
	private Integer cityId;
	
	public ClientNewDTO() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOrCnpj() {
		return cpfOrCnpj;
	}

	public void setCpfOrCnpj(String cpfOrCnpj) {
		this.cpfOrCnpj = cpfOrCnpj;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPatio() {
		return patio;
	}

	public void setPatio(String patio) {
		this.patio = patio;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getPhone3() {
		return phone3;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	
	
}
