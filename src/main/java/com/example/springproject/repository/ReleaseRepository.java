package com.example.springproject.repository;

import com.example.springproject.models.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReleaseRepository extends JpaRepository<Release, Integer> {
}