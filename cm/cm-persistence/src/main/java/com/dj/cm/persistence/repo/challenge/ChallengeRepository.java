package com.dj.cm.persistence.repo.challenge;

import com.dj.cm.model.entity.Challenge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChallengeRepository extends CrudRepository<Challenge, Long> {
    List<Challenge> findAll();
    Challenge findChallengeById(Long id);
}
