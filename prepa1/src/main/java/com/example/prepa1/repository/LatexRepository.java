package com.example.prepa1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.prepa1.entity.Question;



@Repository
public interface LatexRepository extends JpaRepository<Question, Long>{
	List<Question> findByChap(String chap);

}
