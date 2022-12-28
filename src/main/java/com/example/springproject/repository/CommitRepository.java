package com.example.springproject.repository;

import com.example.springproject.models.Commit;
import com.example.springproject.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommitRepository extends JpaRepository<Commit, Integer> {
}