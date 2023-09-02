package com.learning.sudokuweb.repository;

import org.springframework.data.repository.CrudRepository;

import com.learning.sudokuweb.entity.Sudoku;

public interface sudokuRepository extends CrudRepository<Sudoku, Integer>{
	Sudoku findById(int id);

}
