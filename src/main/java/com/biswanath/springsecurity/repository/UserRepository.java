package com.biswanath.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.biswanath.springsecurity.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	/*
	 * @Query("Count(u) from USERS u where  u.username=?1 and u.password=?2") int
	 * isPresent(String username, String password);
	 */
	@Query("Select u from USERS u INNER JOIN ROLES r ON r.id=u.id where u.username=?1")
	User getUserByAuthentication(String username);
	
	User findByUsername(String userName);

}
