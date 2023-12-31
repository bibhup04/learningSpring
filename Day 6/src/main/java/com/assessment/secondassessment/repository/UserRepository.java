package com.assessment.secondassessment.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.assessment.secondassessment.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUserId(String userId);

}