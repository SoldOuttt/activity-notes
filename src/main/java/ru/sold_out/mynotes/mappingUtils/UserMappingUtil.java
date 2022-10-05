package ru.sold_out.mynotes.mappingUtils;

import ru.sold_out.mynotes.dto.AccessRights;
import ru.sold_out.mynotes.dto.AuthorizationInfo;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.entities.additional_user_info.Role;
import ru.sold_out.mynotes.view_models.UserViewModel;

import java.util.HashSet;
import java.util.Set;

public class UserMappingUtil {
	public static User mapToEntity(AuthorizationInfo authorizationInfo) {
		User user = new User();
		user.setUsername(authorizationInfo.getUsername());
		user.setPassword(authorizationInfo.getPassword());
		return user;
	}

	public static User mapToEntity(AuthorizationInfo authorizationInfo, AccessRights accessRights) {
		User user = mapToEntity(authorizationInfo);
		Set<Role> roles = new HashSet<>();
		if (accessRights.isUSER())
			roles.add(Role.USER);
		if (accessRights.isADMIN())
			roles.add(Role.ADMIN);
		user.setRoles(roles);
		return user;
	}

	public static UserViewModel mapToViewModel(User user) {
		UserViewModel userViewModel = new UserViewModel();
		userViewModel.setId(user.getId());
		userViewModel.setUsername(user.getUsername());
		userViewModel.setPassword(user.getPassword());
		userViewModel.setActive(user.isActive());
		userViewModel.setRoles(user.getRoles());
		return userViewModel;
	}
}
