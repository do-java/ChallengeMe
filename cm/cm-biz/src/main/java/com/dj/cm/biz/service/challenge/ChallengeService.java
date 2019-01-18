package com.dj.cm.biz.service.challenge;

import com.dj.cm.model.entity.Challenge;

/**
 * ChallengesService - предоставление челленджа (версия 1)
 */
public interface ChallengeService {
    Iterable<Challenge> getAllChallenges();
    Challenge getChallengeById(Long id);
    void deleteChallengeById(Challenge challenge);
    Challenge createChallenge(Challenge challenge);
    Challenge updateChallenge(Challenge challenge);
}