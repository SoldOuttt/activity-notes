package ru.sold_out.mynotes.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sold_out.mynotes.dto.SubjectInfo;
import ru.sold_out.mynotes.entities.Subject;
import ru.sold_out.mynotes.entities.User;
import ru.sold_out.mynotes.mappingUtils.SubjectMappingUtil;
import ru.sold_out.mynotes.repos.SubjectRepo;
import ru.sold_out.mynotes.view_models.SubjectViewModel;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {
	private final SubjectRepo subjectRepo;

	public List<SubjectViewModel> findAllByUser(User user) {
		return subjectRepo.findByUser(user)
				.stream()
				.map(SubjectMappingUtil::mapToViewModel)
				.sorted(Comparator.comparingDouble(SubjectViewModel::getTotalTime).reversed())
				.toList();
	}

	public boolean save(SubjectInfo subjectInfo, User user) {
		Subject subjectByName = subjectRepo.findByNameAndUser(subjectInfo.getName(), user);
		if (subjectInfo.getName().isBlank() || subjectByName != null) {
			return false;
		}
		Subject subject = SubjectMappingUtil.mapToEntity(subjectInfo);
		subject.setUser(user);
		subjectRepo.save(subject);
		return true;
	}

	public void deleteByName(String name, User user) {
		Subject subjectSearchResult = subjectRepo.findByNameAndUser(name, user);
		if (subjectSearchResult == null)
			return;
		subjectRepo.delete(subjectSearchResult);
	}

//	public boolean isExists(String name) {
//		Subject subjectSearchResult = subjectRepo.findByName(name);
//		return subjectSearchResult != null;
//	}
}
