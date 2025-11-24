package com.example.prepa1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prepa1.entity.LatexEntity;

@Repository
public interface LatexRepository extends JpaRepository<LatexEntity, Long>{

}
