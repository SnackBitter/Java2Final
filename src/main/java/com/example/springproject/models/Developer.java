package com.example.springproject.models;

import javax.persistence.*;

@Entity
@Table(name = "developers")
public class Developer {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "repo")
    private Integer repo;

    
    @Column(name = "username")
    private String username;


    @Column(name = "email")
    private String email;


    @Column(name = "github_id")
    private String githubId;

    @Column(name = "commit_times")
    private Integer commitTimes;

    public Developer(){

    }

    public Developer(int id, int repo, String username, String email,String githubId, int commitTimes){
        this.id = id;
        this.repo = repo;
        this.username = username;
        this.email = email;
        this.githubId = githubId;
        this.commitTimes = commitTimes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRepo() {
        return repo;
    }

    public void setRepo(Integer repo) {
        this.repo = repo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGithubId() {
        return githubId;
    }

    public void setGithubId(String githubId) {
        this.githubId = githubId;
    }

    public Integer getCommitTimes() {
        return commitTimes;
    }

    public void setCommitTimes(Integer commitTimes) {
        this.commitTimes = commitTimes;
    }

}