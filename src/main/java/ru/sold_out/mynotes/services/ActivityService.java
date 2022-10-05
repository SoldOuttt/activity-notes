package ru.sold_out.mynotes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.mynotes.dto.ActivityInfo;
import ru.sold_out.mynotes.entities.Activity;
import ru.sold_out.mynotes.entities.Subject;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.mappingUtils.ActivityMappingUtil;
import ru.sold_out.mynotes.repos.ActivityRepo;
import ru.sold_out.mynotes.repos.SubjectRepo;
import ru.sold_out.mynotes.view_models.ActivityViewModel;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {
	private final ActivityRepo activityRepo;
	private final SubjectRepo subjectRepo;

	public List<ActivityViewModel> findAllByUser(User user) {
		return activityRepo.findByUser(user)
				.stream()
				.map(ActivityMappingUtil::mapToViewModel)
				.sorted(Comparator.comparing((ActivityViewModel::getDate)).reversed())
				.toList();
	}

	public boolean save(User user, ActivityInfo activityInfo) {
		Subject subjectSearchResult = subjectRepo.findByNameAndUser(activityInfo.getSubjectName(), user);
		if (activityInfo.getName().isBlank()
				|| activityInfo.getSubjectName().isBlank()
				|| activityInfo.getSpentTime() == null
				|| subjectSearchResult == null) {
			return false;
		}
		if (activityInfo.getDate() == null) {
			activityInfo.setDate(LocalDate.now());
		}
		Activity activity = ActivityMappingUtil.mapToEntity(activityInfo);
		activity.setUser(user);
		activity.setSubject(subjectSearchResult);
		activityRepo.save(activity);
		return true;
	}

	public void deleteById(Long id, User user) {
		Activity activitySearchResult = activityRepo.findByUserAndId(user, id);
		if (activitySearchResult == null)
			return;
		activityRepo.delete(activitySearchResult);
	}
}
