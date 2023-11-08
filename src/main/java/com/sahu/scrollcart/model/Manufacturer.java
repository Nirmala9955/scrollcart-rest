package com.sahu.scrollcart.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Manufacturer extends Auditable<Long>  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50)
	private String name;
	
	@ManyToMany
    @JoinTable(name = "manufacturer_category",
               joinColumns = @JoinColumn(name = "manufacturer_id"),
               inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;


}
