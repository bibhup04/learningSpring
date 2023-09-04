package com.assessment.secondassessment.repository;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.assessment.secondassessment.entity.Cycle;

public interface cycleRepository extends CrudRepository<Cycle, Integer>{

//	List<Cycle> findByAvailable(boolean available);
//	Cycle findById(int id);
}