package ru.sold_out.mynotes.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.sold_out.mynotes.entities.additional_user_info.Role;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "usr")
@EqualsAndHashCode(of = "id")
public class User implements Serializable, UserDetails {
	@Serial
	private static final long serialVersionUID = 3L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean active;

	@ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
	@CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
	@Enumerated(EnumType.STRING)
	private Set<Role> roles;

	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
					CascadeType.REMOVE
			}
	)
	private Set<Activity> activities;

	@OneToMany(
			mappedBy = "user",
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.MERGE,
					CascadeType.DETACH,
					CascadeType.REFRESH,
					CascadeType.REMOVE
			}
	)
	private Set<Subject> subjects;

	public boolean isAdmin() {
		return roles.contains(Role.ADMIN);
	}

	public boolean isUser() {
		return roles.contains(Role.USER);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getRoles();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive();
	}
}
