package ru.sold_out.mynotes.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Data
public class TimePeriod implements Serializable {
	@Serial
	private static final long serialVersionUID = 11L;

	private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateFrom;
	private @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTo;
}
