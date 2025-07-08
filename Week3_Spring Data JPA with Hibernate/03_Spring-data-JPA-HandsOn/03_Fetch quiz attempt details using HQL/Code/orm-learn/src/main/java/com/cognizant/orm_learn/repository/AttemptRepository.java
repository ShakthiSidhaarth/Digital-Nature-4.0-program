package com.cognizant.orm_learn.repository;

import com.cognizant.orm_learn.model.Attempt;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Integer> {
    @Query("""
        SELECT a FROM Attempt a
        JOIN FETCH a.user u
        JOIN FETCH a.attemptQuestionList aq
        JOIN FETCH aq.question q
        JOIN FETCH aq.attemptOptionList ao
        JOIN FETCH ao.option o
        WHERE u.id = :userId AND a.id = :attemptId
    """)
    Attempt getAttempt(@Param("userId") int userId, @Param("attemptId") int attemptId);
}
