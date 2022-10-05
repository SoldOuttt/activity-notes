package ru.sold_out.mynotes.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.mynotes.dto.SubjectInfo;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.services.SubjectService;
import ru.sold_out.mynotes.view_models.SubjectViewModel;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/subject")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class SubjectController {
	private final SubjectService subjectService;

	@GetMapping("/all")
	public String mainPage(@AuthenticationPrincipal User user, Map<String, Object> model) {
		List<SubjectViewModel> subjects = subjectService.findAllByUser(user);
		model.put("subjects", subjects);
		return "user/subject_page";
	}

	@PostMapping("/add")
	public String save(@AuthenticationPrincipal User user, SubjectInfo subjectInfo) {
		subjectService.save(subjectInfo, user);
		return "redirect:/subject/all";
	}

	@PostMapping("/deleteByName")
	public String deleteByName(
			@AuthenticationPrincipal User user,
			@RequestParam(required = false) String name
	) {
		subjectService.deleteByName(name, user);
		return "redirect:/subject/all";
	}
}
