package ru.sold_out.mynotes.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sold_out.mynotes.dto.TimePeriod;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.services.StatisticsService;
import ru.sold_out.mynotes.view_models.DayStatistics;
import ru.sold_out.mynotes.view_models.PeriodByDayStatistics;

import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping(value = "/statistics")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('USER')")
public class StatisticsController {
	private final StatisticsService statisticsService;

	@GetMapping("/day")
	public String dayStatistics(
			Map<String, Object> model,
			@AuthenticationPrincipal User user,
			@RequestParam(required = false) @DateTimeFormat() LocalDate date
	) {
		if (date == null) {
			date = LocalDate.now();
		}
		DayStatistics dayStatistics = statisticsService.getDayStatistics(user, date);
		model.put("dayStatistics", dayStatistics);
		return "user/day_statistics_page";
	}

	@GetMapping("/period")
	public String periodStatistics(
			Map<String, Object> model,
			@AuthenticationPrincipal User user,
			TimePeriod timePeriod
	) {
		if (timePeriod.getDateFrom() != null && timePeriod.getDateTo() == null) {
			timePeriod.setDateTo(LocalDate.now());
		}
		if (timePeriod.getDateFrom() == null) {
			timePeriod.setDateFrom(LocalDate.now());
		}
		if (timePeriod.getDateTo() == null || timePeriod.getDateFrom().isAfter(timePeriod.getDateTo())) {
			model.put("dayStatistics", null);
			return "user/day_statistics_page";
		}
		if (timePeriod.getDateFrom().isEqual(timePeriod.getDateTo())) {
			return dayStatistics(model, user, timePeriod.getDateFrom());
		}
//		long interval = ChronoUnit.DAYS.between(timePeriod.getDateFrom(), timePeriod.getDateTo());
//		if (interval < 28)
//			return ...
//		if (interval < 124)
//			return ...
//		return ...;
		PeriodByDayStatistics periodByDayStatistics = statisticsService.getPeriodByDayStatistics(
				user,
				timePeriod.getDateFrom(),
				timePeriod.getDateTo()
		);
		model.put("periodStatistics", periodByDayStatistics);
		return "user/period_statistics_page";
	}

//	@GetMapping("/period")
//	public String period(
//			@AuthenticationPrincipal User user,
//			TimePeriod timePeriod,
//			Map<String, Object> model
//	) {
//		if (timePeriod.get_from().isAfter(timePeriod.get_to())) {
//			return null; // rewrite
//		}
//		List<ActivityViewModel> activities = activityService.findAllByUser(user)
//				.stream()
//				.filter(activity -> !(activity.getDate().isBefore(timePeriod.get_from())
//						|| activity.getDate().isAfter(timePeriod.get_to())))
//				.toList();
//		model.put("activities", activities);
//		return "user/statistics";
//	}
//
//	@GetMapping("/day")
//	public String day(
//			@RequestParam(required = false) @DateTimeFormat LocalDate localDate,
//			Map<String, Object> model) {
//		List<ActivityViewModel> activities = activityService.
//	}
}
