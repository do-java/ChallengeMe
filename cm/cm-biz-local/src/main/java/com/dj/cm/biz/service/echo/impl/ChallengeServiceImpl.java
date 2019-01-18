package com.dj.cm.biz.service.echo.impl;

import com.dj.cm.biz.service.challenge.ChallengeService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.model.entity.Challenge;
import com.dj.cm.persistence.repo.challenge.ChallengeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {
    @Autowired
    private ChallengeRepository challengeRepository;

    @Override
    public Challenge getChallengeById(Long id) {
        Optional<Challenge> challengeOptional = challengeRepository.findById(id);
        if (!challengeOptional.isPresent()) {
            throw new NotFoundBizException(String.format("Challenge with Id: %s not found", id));
        }
        return challengeOptional.get();
    }

    @Override
    public Iterable<Challenge> getAllChallenges() {
        return challengeRepository.findAll();
    }

    @Override
    public void deleteChallengeById(Challenge challenge) {
        challengeRepository.delete(challenge);
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge updateChallenge(Challenge challenge) {   //входной челлендж
        Challenge challengeToUpdate = challengeRepository.findChallengeById(challenge.getId());  //этот же челлендж в БД
        BeanUtils.copyProperties(challenge, challengeToUpdate, "id");  //заменяем переменные, кроме id. Из challenge в challengeToUpdate
        return challengeRepository.save(challengeToUpdate);
    }
}
