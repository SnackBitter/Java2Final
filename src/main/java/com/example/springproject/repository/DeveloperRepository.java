package com.example.springproject.repository;

import com.example.springproject.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
    @Query("select d from Developer d where d.githubId = ?1")
    List<Developer> findByGithubId(String githubId);
}