package ru.sold_out.mynotes.view_models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class SubjectViewModel implements Serializable {
	@Serial
	private static final long serialVersionUID = 7L;

	String name;
	Double totalTime;
}
