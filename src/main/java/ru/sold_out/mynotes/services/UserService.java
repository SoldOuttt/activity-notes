package ru.sold_out.mynotes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sold_out.mynotes.dto.AccessRights;
import ru.sold_out.mynotes.dto.AuthorizationInfo;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.mappingUtils.UserMappingUtil;
import ru.sold_out.mynotes.repos.UserRepo;
import ru.sold_out.mynotes.view_models.UserViewModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
	private final UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepo.findByUsername(username);
	}

	public boolean save(AuthorizationInfo authorizationInfo, AccessRights accessRights) {
		User userByUsername = userRepo.findByUsername(authorizationInfo.getUsername());
		if (authorizationInfo.getUsername().isBlank()
				|| authorizationInfo.getPassword().isBlank()
				|| userByUsername != null) {
			return false;
		}
		User user = UserMappingUtil.mapToEntity(authorizationInfo, accessRights);
		user.setActive(true);
		userRepo.save(user);
		return true;
	}

	public boolean save(AuthorizationInfo authorizationInfo) {
		AccessRights accessRights = new AccessRights();
		accessRights.setUSER(true);
		return save(authorizationInfo, accessRights);
	}

	public List<UserViewModel> findAll() {
		return userRepo.findAll()
				.stream()
				.map(UserMappingUtil::mapToViewModel)
				.sorted(Comparator.comparingLong(UserViewModel::getId).reversed())
				.toList();
	}

	public void deleteById(Long id) {
		if (id == null)
			return;
		Optional<User> userSearchResult = userRepo.findById(id);
		if (userSearchResult.isEmpty())
			return;
		userRepo.deleteById(id);
	}

	public UserViewModel findByUsername(String username) {
		User user = userRepo.findByUsername(username);
		if (user == null)
			return null;
		return UserMappingUtil.mapToViewModel(user);
	}

	public UserViewModel findById(Long id) {
		if (id == null)
			return null;
		Optional<User> searchResult = userRepo.findById(id);
		if (searchResult.isEmpty())
			return null;
		return UserMappingUtil.mapToViewModel(searchResult.get());
	}

	public void deleteByUsername(String username) {
		User userSearchResult = userRepo.findByUsername(username);
		if (userSearchResult == null)
			return;
		userRepo.delete(userSearchResult);
	}
}
