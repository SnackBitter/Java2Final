package com.example.springproject.service;

import com.example.springproject.domain.Repo;

import java.util.List;

public interface RepoService {

    public Repo findInfo();

    public void loadData(String repoName, int repoNum);
}
