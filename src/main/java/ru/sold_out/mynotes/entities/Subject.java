package ru.sold_out.mynotes.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(
			mappedBy = "subject",
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
					CascadeType.REMOVE
			}
	)
	private Set<Activity> activities;

	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
			}
	)
	private User user;
}
