package com.cognizant.orm_learn.service;

import com.cognizant.orm_learn.model.Attempt;
import com.cognizant.orm_learn.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttemptService {
    @Autowired
    private AttemptRepository repo;
    public Attempt getAttempt(int userId, int attemptId) {
        return repo.getAttempt(userId, attemptId);
    }
}
