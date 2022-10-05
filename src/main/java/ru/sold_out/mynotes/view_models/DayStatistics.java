package ru.sold_out.mynotes.view_models;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.sold_out.mynotes.entities.Activity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
public class DayStatistics {
	LocalDate date;
	Double totalSpentTime;
	Map<String, Double> info;

	public DayStatistics(List<Activity> activities) {
		info = new HashMap<>();
		date = activities.get(0).getDate();
		totalSpentTime = activities.stream()
				.map(Activity::getSpentTime)
				.mapToDouble(Double::doubleValue)
				.sum();
		for (Activity activity : activities) {
			if (info.containsKey(activity.getSubject().getName())) {
				info.put(
						activity.getSubject().getName(),
						info.get(activity.getSubject().getName()) + activity.getSpentTime()
				);
			} else {
				info.put(activity.getSubject().getName(), activity.getSpentTime());
			}
		}
	}
}
