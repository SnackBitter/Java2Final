package com.example.springproject.repository;

import com.example.springproject.models.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, Integer> {
}