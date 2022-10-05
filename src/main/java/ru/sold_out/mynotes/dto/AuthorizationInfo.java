package ru.sold_out.mynotes.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class AuthorizationInfo implements Serializable {
	@Serial
	private static final long serialVersionUID = 4L;

	private String username;
	private String password;
}
