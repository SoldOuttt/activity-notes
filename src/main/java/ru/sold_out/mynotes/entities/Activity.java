package ru.sold_out.mynotes.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Activity implements Serializable {
	@Serial
	private static final long serialVersionUID = 2L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private LocalDate date;

	@ManyToOne(
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
			}
	)
	private Subject subject;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Double spentTime;

	@Size(max = 2_048)
	@Column(nullable = false, length = 2_048)
	private String description;

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