package ru.sold_out.mynotes.view_models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActivityViewModel implements Serializable {
	@Serial
	private static final long serialVersionUID = 10L;

	private Long id;
	private String name;
	private String subjectName;
	private Double spentTime;
	private LocalDate date;
	private String description;
}
