package com.sahu.scrollcart.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
public class ProductVariant extends Auditable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String size;

	@ElementCollection
	@CollectionTable(name = "product_variant_images", joinColumns = @JoinColumn(name = "product_variant_id"))
	@Column(name = "image_video_url")
	private List<String> imageVideoUrls;

	@Column(length = 20)
	private String color;

	private Integer noOfStocks;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "availability")
	private AppParamValue availability;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
