package ru.sold_out.mynotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.mynotes.entities.Subject;
import ru.sold_out.mynotes.entities.User;

import java.util.List;

@Repository
public interface SubjectRepo extends JpaRepository<Subject, Long> {
	Subject findByNameAndUser(String name, User user);
	List<Subject> findByUser(User user);
}
