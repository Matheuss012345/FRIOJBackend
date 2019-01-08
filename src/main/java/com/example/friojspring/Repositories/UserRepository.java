package com.example.friojspring.Repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

	User findByEmailIgnoreCase(String email);
	User findByUsername(String username);
	
	
	@Query(value="SELECT * FROM user u1 where u1.id IN "
			    	+ "(SELECT u.id FROM user u JOIN submission s ON u.id=s.user_id JOIN result r ON s.result_id=r.id "
			    	+ "JOIN problem p on s.problem_id=p.id "
			    	+ "WHERE r.code = 'AC' and p.id= :problemID)" , nativeQuery=true)
	List<User> findAllUsersWhoSolvedThisProblem(@Param("problemID") Long problemID);
	
	
	
	@Query(value="SELECT COUNT(*) FROM user u WHERE u.email=:email", nativeQuery=true)
	int getNumberOfUsersWithThisEmail(@Param("email")String email);
	
	@Query(value="SELECT COUNT(*) FROM user u WHERE u.username=:username", nativeQuery=true)
	int getNumberOfUsersWithThisUsername(@Param("username")String username);
	
	

}
