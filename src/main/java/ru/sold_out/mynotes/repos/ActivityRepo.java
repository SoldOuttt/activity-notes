package ru.sold_out.mynotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.mynotes.entities.Activity;
import ru.sold_out.mynotes.entities.User;

import java.util.List;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {
	List<Activity> findByUser(User user);
	Activity findByUserAndId(User user, Long id);
}
