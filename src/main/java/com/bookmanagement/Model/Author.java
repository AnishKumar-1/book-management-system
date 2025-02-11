package com.bookmanagement.Model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false,unique = true)
	private String name;
	@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
	//here if i do not mentioned mappedBy spring will automatically create 3rd table for it 
	// and store both table relationship primary key into 3rd table
//	@JsonManagedReference
	private List<Books> books;
}
