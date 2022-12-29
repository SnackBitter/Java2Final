package com.example.springproject.models;

import javax.persistence.*;

@Entity
@Table(name = "commit")
public class Commit {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "repo")
  private Integer repo;

  @Column(name = "time_in_day")
  private String timeInDay;

  @Column(name = "committed_at")
  private String committedAt;

  public Commit() {

  }

  public Commit(int id, int repo, String timeInDay, String committedAt) {
    this.id = id;
    this.repo = repo;
    this.timeInDay = timeInDay;
    this.committedAt = committedAt;
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

  public String getTimeInDay() {
    return timeInDay;
  }

  public void setTimeInDay(String timeInDay) {
    this.timeInDay = timeInDay;
  }

  public String getCommittedAt() {
    return committedAt;
  }

  public void setCommittedAt(String committedAt) {
    this.committedAt = committedAt;
  }

}