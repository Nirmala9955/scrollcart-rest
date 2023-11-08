package com.sahu.scrollcart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Address extends Auditable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 50)
	private String name;

	@Column(length = 20)
	private Long phoneNo;

	@Column(length = 20)
	private Long alternativePhoneNo;

	@Column(length = 50)
	private String streetAddress;

	@Column(length = 6)
	private Integer postalCode;

	@Column(length = 50)
	private String locality;

	@Column(length = 50)
	private String district;

	@Column(length = 50)
	private String state;

	@Column(length = 50)
	private String LandMark;

	@Column(length = 10)
	private String addressType;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

}
