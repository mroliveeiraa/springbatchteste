package com.example.batch.repository;

import com.example.batch.model.MinhaEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinhaEntidadeRepository extends JpaRepository<MinhaEntidade, Long> {
}
