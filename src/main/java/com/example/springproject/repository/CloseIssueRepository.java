package com.example.springproject.repository;

import com.example.springproject.models.CloseIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloseIssueRepository extends JpaRepository<CloseIssue, Integer> {
}