package ru.sold_out.mynotes.dto;


import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SubjectInfo implements Serializable {
	@Serial
	private static final long serialVersionUID = 8L;

	private String name;
}
