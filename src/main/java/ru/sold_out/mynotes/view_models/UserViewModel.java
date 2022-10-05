package ru.sold_out.mynotes.view_models;

import lombok.Data;
import ru.sold_out.mynotes.entities.additional_user_info.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

@Data
public class UserViewModel implements Serializable {
	@Serial
	private static final long serialVersionUID = 5L;

	private Long id;
	private String username;
	private String password;
	private boolean active;
	private Set<Role> roles;
}
