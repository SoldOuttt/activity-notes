package ru.sold_out.mynotes.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ActivityInfo implements Serializable {
	@Serial
	private static final long serialVersionUID = 9L;

	private String name;
	private String subjectName;
	private Double spentTime;
	private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date;
	private String description;
}
