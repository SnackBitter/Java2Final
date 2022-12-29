package com.example.springproject.service;

import com.example.springproject.models.*;
import com.example.springproject.repository.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.example.springproject.domain.Repo;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepoServiceImpl implements RepoService {
  private final CloseIssueRepository closeIssueRepository;
  private final CommitRepository commitRepository;
  private final DeveloperRepository developerRepository;
  private final OpenIssueRepository openIssueRepository;
  private final ReleaseRepository releaseRepository;

  @Autowired
  public RepoServiceImpl(CloseIssueRepository closeIssueRepository, CommitRepository commitRepository, DeveloperRepository developerRepository, OpenIssueRepository openIssueRepository, ReleaseRepository releaseRepository) {
    this.closeIssueRepository = closeIssueRepository;
    this.commitRepository = commitRepository;
    this.developerRepository = developerRepository;
    this.openIssueRepository = openIssueRepository;
    this.releaseRepository = releaseRepository;
  }

  @Override
  public Repo findInfo() {
    return new Repo();
  }

  @Override
  public void loadData(String repoName, int repoNum) {
    HttpClient client = HttpClient.newHttpClient();
    HttpRequest httpRequest;
    String url = "https://api.github.com/repos/" + repoName + "/commits?per_page=100&page=";
    int num = 1;

    //Develop && commit
    List<Developer> developerList = new ArrayList<>();
    List<Commit> commitList = new ArrayList<>();
    int developId = 0;
    int CommitId = 0;
    while (true) {
      httpRequest = HttpRequest.newBuilder().uri(URI.create(url + (num++))).setHeader("Authorization", "Bearer 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").build();
      HttpResponse<String> response = null;
      try {
        response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      assert response != null;
      String data = response.body();
      JSONArray jsonArray = JSONArray.parseArray(data);
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject temp = jsonArray.getJSONObject(i);
        if (temp.get("author") == null) {
          continue;
        }
        Developer developer = new Developer(developId++
                , repoNum
                , temp.getJSONObject("author").getString("login")
                , temp.getJSONObject("commit").getJSONObject("author").getString("email")
                , temp.getJSONObject("author").getString("id")
                , 0);

        Commit commit = new Commit(CommitId++
                , repoNum
                , temp.getJSONObject("commit").getJSONObject("author").getString("date").substring(11, 13)
                , temp.getJSONObject("commit").getJSONObject("author").getString("date"));
        developerList.add(developer);
        commitList.add(commit);
      }
      if (jsonArray.size() < 100) {
        break;
      }
    }
    List<Developer> countedDevelop = new ArrayList<>();
    developId = 0;
    boolean is;
    for (Developer value : developerList) {
      is = false;
      for (Developer developer : countedDevelop) {
        if (value.getGithubId().equals(developer.getGithubId())) {
          developer.setCommitTimes(developer.getCommitTimes() + 1);
          is = true;
          break;
        }
      }
      if (!is) {
        countedDevelop.add(new Developer(developId++, value.getRepo(), value.getUsername(), value.getEmail(), value.getGithubId(), 1));
      }
    }
    developerRepository.saveAll(countedDevelop);
    commitRepository.saveAll(commitList);

    //open_issue
    url = "https://api.github.com/repos/" + repoName;
    httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).setHeader("Authorization", "Bearer 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").build();
    HttpResponse<String> response2 = null;
    try {
      response2 = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    assert response2 != null;
    String data2 = response2.body();
    JSONObject jsonObject = JSONObject.parseObject(data2);
    OpenIssue openIssue = new OpenIssue(1, repoNum, jsonObject.getInteger("open_issues_count"));
    openIssueRepository.save(openIssue);

    //close issue
    List<CloseIssue> closeIssueList = new ArrayList<>();
    int CloseIssueID = 0;
    url = "https://api.github.com/repos/" + repoName + "/issues/events?per_page=100&page=";
    num = 1;
    while (true) {
      httpRequest = HttpRequest.newBuilder().uri(URI.create(url + (num++))).setHeader("Authorization", "Bearer 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").build();
      HttpResponse<String> response = null;
      try {
        response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      assert response != null;
      String data = response.body();
      JSONArray jsonArray = JSONArray.parseArray(data);
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject temp = jsonArray.getJSONObject(i);
        if (!temp.getString("event").equals("closed")) {
          continue;
        }
        CloseIssue closeIssue = new CloseIssue(CloseIssueID++
                , repoNum
                , temp.getJSONObject("issue").getString("created_at")
                , temp.getJSONObject("issue").getString("closed_at"));
        closeIssueList.add(closeIssue);
      }
      if (jsonArray.size() < 100) {
        break;
      }
    }
    closeIssueRepository.saveAll(closeIssueList);

    //release
    List<Release> releaseList = new ArrayList<>();
    url = "https://api.github.com/repos/" + repoName + "/releases?per_page=100&page=";
    num = 0;
    int ReleaseID = 0;
    while (true) {
      httpRequest = HttpRequest.newBuilder().uri(URI.create(url + (num++))).setHeader("Authorization", "Bearer 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").build();
      HttpResponse<String> response = null;
      try {
        response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
      assert response != null;
      String data = response.body();
      JSONArray jsonArray = JSONArray.parseArray(data);
      for (int i = 0; i < jsonArray.size(); i++) {
        JSONObject temp = jsonArray.getJSONObject(i);
        Release release = new Release(ReleaseID++
                , repoNum
                , temp.getString("published_at"));
        releaseList.add(release);
      }
      if (jsonArray.size() < 100) {
        break;
      }
    }
    releaseRepository.saveAll(releaseList);
    System.out.println("Finish!!");
  }
}
