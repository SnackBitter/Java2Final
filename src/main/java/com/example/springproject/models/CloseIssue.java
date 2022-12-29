package com.example.springproject.models;

import javax.persistence.*;

@Entity
@Table(name = "close_issue")
public class CloseIssue {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "repo")
  private Integer repo;

  @Column(name = "created_at")
  private String createdAt;

  @Column(name = "closed_at")
  private String closedAt;

  public CloseIssue() {

  }

  public CloseIssue(int id, int repo, String createdAt, String closedAt) {
    this.id = id;
    this.repo = repo;
    this.createdAt = createdAt;
    this.closedAt = closedAt;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getClosedAt() {
    return closedAt;
  }

  public void setClosedAt(String closedAt) {
    this.closedAt = closedAt;
  }

}