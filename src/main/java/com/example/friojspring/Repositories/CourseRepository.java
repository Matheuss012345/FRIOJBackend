package com.example.friojspring.Repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friojspring.Domain.SubmissionDTO;
import com.example.friojspring.Model.Course;
import com.example.friojspring.Model.Language;
import com.example.friojspring.Model.Problem;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long>{
	Course save(Course  course);

    @Modifying
	@Query(value="DELETE FROM course_user WHERE course_id=:courseId AND user_id=:userId", nativeQuery=true)
    void removeUserFromCourse(@Param("courseId")long courseId, @Param("userId")long userId );
	
    @Modifying
	@Query(value="INSERT INTO course_user VALUES(:courseId,:userId)", nativeQuery=true)
    void addUserToCourse(@Param("courseId")long courseId, @Param("userId")long userId );
}
