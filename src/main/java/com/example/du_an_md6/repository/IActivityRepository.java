package com.example.du_an_md6.repository;

import com.example.du_an_md6.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IActivityRepository extends JpaRepository<Activity, Long> {
}
