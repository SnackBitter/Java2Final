package com.example.springproject.repository;

import com.example.springproject.models.OpenIssue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenIssueRepository extends JpaRepository<OpenIssue, Integer> {
}