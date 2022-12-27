package com.example.springproject.repository;

import com.example.springproject.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Integer> {
}