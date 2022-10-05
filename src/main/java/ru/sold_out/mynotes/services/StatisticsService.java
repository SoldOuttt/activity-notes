package ru.sold_out.mynotes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.mynotes.entities.Activity;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.repos.ActivityRepo;
import ru.sold_out.mynotes.view_models.DayStatistics;
import ru.sold_out.mynotes.view_models.PeriodByDayStatistics;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
	private final ActivityRepo activityRepo;

	public DayStatistics getDayStatistics(User user, LocalDate date) {
		List<Activity> dayActivities = activityRepo.findByUser(user)
				.stream()
				.filter(activity -> activity.getDate().isEqual(date))
				.toList();
		if (dayActivities.isEmpty()) {
			return null;
		}
		DayStatistics dayStatistics = new DayStatistics(dayActivities);
		return dayStatistics;
	}

	public  PeriodByDayStatistics getPeriodByDayStatistics(User user, LocalDate _from, LocalDate _to) {
		List<Activity> activities = activityRepo.findByUser(user)
				.stream()
				.filter(activity -> !(activity.getDate().isBefore(_from) || activity.getDate().isAfter(_to)))
				.toList();
		if (activities.isEmpty()) {
			return null;
		}
		return new PeriodByDayStatistics(_from, _to, activities);
	}
}
