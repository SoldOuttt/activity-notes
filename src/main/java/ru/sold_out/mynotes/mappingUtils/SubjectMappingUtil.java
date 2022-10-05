package ru.sold_out.mynotes.mappingUtils;

import ru.sold_out.mynotes.dto.SubjectInfo;
import ru.sold_out.mynotes.entities.Activity;
import ru.sold_out.mynotes.entities.Subject;
import ru.sold_out.mynotes.view_models.SubjectViewModel;

public class SubjectMappingUtil {

	public static Subject mapToEntity(SubjectInfo subjectInfo) {
		Subject subject = new Subject();
		subject.setName(subjectInfo.getName());
		return subject;
	}

	public static SubjectViewModel mapToViewModel(Subject subject) {
		SubjectViewModel subjectViewModel = new SubjectViewModel();
		subjectViewModel.setName(subject.getName());
		Double totalTime = subject.getActivities()
				.stream()
				.map(Activity::getSpentTime)
				.mapToDouble(Double::doubleValue)
				.sum();
		subjectViewModel.setTotalTime(totalTime);
		return subjectViewModel;
	}
}
