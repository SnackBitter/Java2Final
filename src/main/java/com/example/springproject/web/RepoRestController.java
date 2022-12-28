package com.example.springproject.web;

import com.alibaba.fastjson2.JSONObject;
import com.example.springproject.models.Developer;
import com.example.springproject.repository.CommitRepository;
import com.example.springproject.repository.DeveloperRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RepoRestController {
    private final DeveloperRepository developerRepository;
    private final CommitRepository commitRepository;

    public RepoRestController(DeveloperRepository developerRepository,
                              CommitRepository commitRepository) {
        this.developerRepository = developerRepository;
        this.commitRepository = commitRepository;
    }

    @GetMapping
    public List<Developer> getDevelopByID(@RequestParam(value = "GithubID")
                                       Optional<String> id){
        return id.map(developerRepository::findByGithubId).orElse(null);
    }

    @GetMapping(path = "/CommitCount")
    public int getCommitCount(){
        return commitRepository.findAll().size();
    }

    @GetMapping(path = "/RepoInfo")
    public String getRepoInfo(){
        JSONObject a = new JSONObject();
        a.put("Name","gnembon/carpet-extra");
        return a.toString();
    }
}
