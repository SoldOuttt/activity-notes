package ru.sold_out.mynotes.view_models;

import lombok.Data;
import ru.sold_out.mynotes.entities.Activity;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;

@Data
public class PeriodByDayStatistics implements Serializable {
	@Serial
	private static final long serialVersionUID = 12L;

	LocalDate dateFrom;
	LocalDate dateTo;
	Double totalSpentTime;
	Integer interval;
	Integer averageHours;
	Integer averageMinutes;
	List<LocalDate> dates;
	LinkedHashMap<LocalDate, Double> info;


	public PeriodByDayStatistics(LocalDate dateFrom, LocalDate dateTo, List<Activity> activities) {
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		info = new LinkedHashMap<>();
		dates = dateFrom.datesUntil(dateTo.plusDays(1)).toList();
		for (LocalDate date : dates) {
			info.put(date, 0.);
		}
		for (Activity activity : activities) {
			info.put(
					activity.getDate(),
					info.get(activity.getDate()) + activity.getSpentTime()
			);
		}
		totalSpentTime = info.values().stream().mapToDouble(Double::doubleValue).sum();
		interval = dates.size();
		averageHours = (int) (totalSpentTime / interval);
		averageMinutes = (int) (totalSpentTime / interval % 1 * 60);
	}
}
