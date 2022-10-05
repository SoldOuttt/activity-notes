package ru.sold_out.mynotes.mappingUtils;

import ru.sold_out.mynotes.dto.ActivityInfo;
import ru.sold_out.mynotes.entities.Activity;
import ru.sold_out.mynotes.view_models.ActivityViewModel;

public class ActivityMappingUtil {
	public static Activity mapToEntity(ActivityInfo activityInfo) {
		Activity activity = new Activity();
		activity.setName(activityInfo.getName());
		activity.setSpentTime(activityInfo.getSpentTime());
		activity.setDate(activityInfo.getDate());
		activity.setDescription(activityInfo.getDescription());
		return activity;
	}

	public static ActivityViewModel mapToViewModel(Activity activity) {
		ActivityViewModel activityViewModel = new ActivityViewModel();
		activityViewModel.setId(activity.getId());
		activityViewModel.setName(activity.getName());
		activityViewModel.setSubjectName(activity.getSubject().getName());
		activityViewModel.setSpentTime(activity.getSpentTime());
		activityViewModel.setDate(activity.getDate());
		activityViewModel.setDescription(activity.getDescription());
		return activityViewModel;
	}
}
