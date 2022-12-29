package com.example.springproject.models;

import javax.persistence.*;

@Entity
@Table(name = "release")
public class Release {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "repo")
  private Integer repo;


  @Column(name = "released_at")
  private String releasedAt;

  public Release() {

  }

  public Release(int id, int repo, String releasedAt) {
    this.id = id;
    this.repo = repo;
    this.releasedAt = releasedAt;
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

  public String getReleasedAt() {
    return releasedAt;
  }

  public void setReleasedAt(String releasedAt) {
    this.releasedAt = releasedAt;
  }

}