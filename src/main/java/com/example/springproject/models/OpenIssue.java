package com.example.springproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "open_issue")
public class OpenIssue {
  @Id
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "repo")
  private Integer repo;

  @Column(name = "num")
  private Integer num;

  public OpenIssue() {

  }

  public OpenIssue(int id, int repo, int num) {
    this.id = id;
    this.repo = repo;
    this.num = num;
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

  public Integer getNum() {
    return num;
  }

  public void setNum(Integer num) {
    this.num = num;
  }

}