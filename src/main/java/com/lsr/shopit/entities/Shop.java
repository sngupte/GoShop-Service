package com.lsr.shopit.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "shops")
@Getter
@Setter
public class Shop {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "offer_title")
	private String offerTitle;

	@Column(name = "offer_desc")
	private String offerDesc;

	@Column(name = "state")
	private String state;

	@Column(name = "city")
	private String city;

	@Column(name = "location", nullable = false)
	private String location;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "photo_url")
	private String photoUrl;

	@Column(name = "type")
	private String type;

	@Column(name = "categories")
	private String categories;

	@Column(name = "opening_time")
	private String openingTime;

	@Column(name = "closing_time")
	private String closingTime;

	@Column(name = "rating")
	private float rating;

	@Column(name = "rating_count")
	private float ratingCount;

	@Column(name = "phone")
	private String phone;

	@Column(name = "parking_avail")
	private Boolean parkingAvail;

	@Column(name = "changing_room_avail")
	private Boolean changingRoomAvail;

	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "user_id", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;

	public void addRatings(float rating) {
		this.rating = this.rating + rating;
		this.ratingCount++;
	}
}
