package com.dj.cm.biz.service.challenge.impl;

import com.dj.cm.biz.service.challenge.ChallengeService;
import com.dj.cm.biz.service.exception.NotFoundBizException;
import com.dj.cm.biz.service.filestorage.FileStorageService;
import com.dj.cm.model.entity.Challenge;
import com.dj.cm.persistence.repo.challenge.ChallengeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class ChallengeServiceImpl implements ChallengeService {

    @Autowired
    private ChallengeRepository challengeRepository;

    @Autowired
    private FileStorageService fileStorageService;

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
    public void deleteChallengeById(Long id) {
        Challenge challengeToDelete = getChallengeById(id);
        if (challengeToDelete == null) {
            return;
        }

        challengeRepository.deleteById(id);

        // Cleanup challenge picture from store
        if (!StringUtils.isEmpty(challengeToDelete.getPictureFilename())) {
            // Remove picture from the store
            fileStorageService.delete(challengeToDelete.getPictureFilename());
        }
    }

    @Override
    public Challenge createChallenge(Challenge challenge) {
        return challengeRepository.save(challenge);
    }

    @Override
    public Challenge updateChallenge(Challenge challenge) {   //входной челлендж
        Challenge challengeToUpdate = challengeRepository.findChallengeById(challenge.getId());  //этот же челлендж в БД
        String oldChallengePictureFilename = challengeToUpdate.getPictureFilename();
        BeanUtils.copyProperties(challenge, challengeToUpdate, "id");  //заменяем переменные, кроме id. Из challenge в challengeToUpdate

        Challenge result = challengeRepository.save(challengeToUpdate);

        // Cleanup challenge picture from store if exist and updated or deleted
        if (!StringUtils.isEmpty(oldChallengePictureFilename)
                && !oldChallengePictureFilename.equalsIgnoreCase(challenge.getPictureFilename())) {
            // Remove picture from the store
            fileStorageService.delete(oldChallengePictureFilename);
        }

        return result;
    }
}
