package ru.sold_out.mynotes.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sold_out.mynotes.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	public User findByUsername(String username);
}
