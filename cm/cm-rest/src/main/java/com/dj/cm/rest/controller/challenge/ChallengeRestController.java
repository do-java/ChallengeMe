package com.dj.cm.rest.controller.challenge;

import com.dj.cm.biz.service.challenge.ChallengeService;
import com.dj.cm.model.entity.Challenge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Usage:
 * <p>
 * GET http://localhost:8080/rest/challenges
 * GET http://localhost:8080/rest/challenges/1
 * fetch('/rest/challenges', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({name:'sport Event', type:'sport', description:'UEFA championship', priority:4, max_members:8, acess:'PRIVATE', status:'BLOCKED', startDate: "2012-03-19T07:22Z", endDate: "2012-03-19T07:22Z", id: null}) }).then(result => result.json().then(console.log))
 * fetch('/rest/challenges/1', { method: 'PUT', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify({name:'sport Event', type:'sport', description:'UEFA championship', priority:4, max_members:8, acess:'PRIVATE', status:'BLOCKED', startDate: "2012-03-19T07:22Z", endDate: "2012-03-19T07:22Z", id: 1}) }).then(result => result.json().then(console.log))
 * fetch('/rest/challenges/9', { method: 'DELETE' }).then(result => console.log(result))
 *
 */
@RestController
@RequestMapping("/rest/challenges")
public class ChallengeRestController {

    @Autowired
    private ChallengeService challengesService;

    @GetMapping()
    public Iterable<Challenge> getAllChallenges(){
        return challengesService.getAllChallenges();
    }

    @GetMapping("{id}")
    public Challenge getChallengeById(@PathVariable Long id){
        return challengesService.getChallengeById(id);
    }

    @PostMapping
    public Challenge createChallenge(@RequestBody Challenge challenge){
        return challengesService.createChallenge(challenge);
    }

    @PutMapping("{id}")
    public Challenge update(@RequestBody Challenge challenge){
        return challengesService.updateChallenge(challenge);
    }

    @PutMapping("/update2/{id}")
    public Challenge update2(@RequestParam("file") MultipartFile file, @RequestParam("challenge") Challenge challenge) {
        return challengesService.updateChallenge(challenge);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable("id") Challenge challenge){
        challengesService.deleteChallengeById(challenge);
    }
}
