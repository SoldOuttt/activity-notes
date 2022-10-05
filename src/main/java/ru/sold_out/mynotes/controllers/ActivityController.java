package ru.sold_out.mynotes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.mynotes.dto.ActivityInfo;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.services.ActivityService;
import ru.sold_out.mynotes.view_models.ActivityViewModel;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/activity")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class ActivityController {
	private final ActivityService activityService;

	@GetMapping("/all")
	public String mainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		List<ActivityViewModel> activities = activityService.findAllByUser(user);
		model.put("activities", activities);
		return "user/activity_page";
	}

	@PostMapping("/add")
	public String add(@AuthenticationPrincipal User user, ActivityInfo activityInfo) {
		activityService.save(user, activityInfo);
		return "redirect:/activity/all";
	}

	@PostMapping("/deleteById")
	public String deleteById(
			@AuthenticationPrincipal User user,
			@RequestParam(required = false) Long id) {
		activityService.deleteById(id, user);
		return "redirect:/activity/all";
	}
}
