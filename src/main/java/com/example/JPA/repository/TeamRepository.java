package com.example.JPA.repository;

import com.example.JPA.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository<엔티티, pk 타입>
public interface TeamRepository extends JpaRepository<Team, Long> {
}
