package com.kirandeep.ecommerce.customer.entity;

import com.kirandeep.ecommerce.authentication.entity.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Data
@Table (name="customers")
@Entity
public class Customer {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phoneNumber;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="app_user_id")
	private AppUser appUser;

	@OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
	private List<Address> addresses;
}
