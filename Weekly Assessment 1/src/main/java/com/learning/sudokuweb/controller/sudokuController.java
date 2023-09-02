package com.learning.sudokuweb.controller;

import org.springframework.stereotype.Controller; 
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.learning.sudokuweb.controller.binding.createSudokuBoard;
import com.learning.sudokuweb.entity.Sudoku;
import com.learning.sudokuweb.repository.sudokuRepository;
import com.prodapt.learningSpring.controller.exception.ResourceNotFoundException;



@Controller
public class sudokuController {
	
	@Autowired
	private sudokuRepository sudokurepository;
	
	 ArrayList<ArrayList> board = new ArrayList<>();
	 ArrayList<ArrayList<Integer>> sudokuGrid = new ArrayList<>();
	 boolean start = true;
	 boolean gameStart = true;

	 List<Sudoku> sudokuList = new ArrayList<>();
	 public void init() {
		 System.out.println("Inside init------------------");
		 for (int i = 0; i < 9; i++) {
	         ArrayList<Integer> row = new ArrayList<>();
	         for (int j = 0; j < 9; j++) {
	             row.add(0);
	         }
	         board.add(row);
	     }
		 System.out.println(board); 
	 }

	 	public int addDataToDB() {
	 		int id=-1;
	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                Sudoku sudoku = new Sudoku();
	                if(i==0 && j == 0) {
	                	id = sudoku.getId();
	                	System.out.println("first id - "+ id);
	                }
	                sudoku.setSudokuRow(i);
	                sudoku.setSudokuCol(j);
	                sudoku.setSudokuNum((int) board.get(i).get(j));
	                sudokuList.add(sudoku);
	                sudoku = sudokurepository.save(sudoku);

	                if (i == 0 && j == 0) {
	                    id = sudoku.getId();
	                    System.out.println("First id - " + id);
	                }
	            }
	        }
	        return id;
	    }
	
	
	@GetMapping("/sudokuForm")
    public String showRentForm(Model model) {
		if(start) {
			init();
			start = false;
		}
		model.addAttribute("board", board);
		model.addAttribute("createSudokuBoard", new createSudokuBoard());
        return  "sudokuForm";
    }
	
	  @PostMapping("/sudokuForm/add")
	    public String createSudokuBoard(@ModelAttribute("createSudokuBoard") createSudokuBoard createSudokuBoard) {

		  System.out.println("inside get");
	      int row =   createSudokuBoard.getSudokuRow();
	      int col =   createSudokuBoard.getSudokuCol();
	      int value =   createSudokuBoard.getSudokuNum();
	      
	        System.out.println("Row: " + row);
	        System.out.println("Col: " + col);
	        System.out.println("Num: " + value);
	        System.out.println(isValidMove(row, col, value));
	        if(isValidMove(row, col, value)) {
	        	board.get(row).set(col, value);
	        }

	        return "redirect:/sudokuForm";
	    }
	  
	  @PostMapping("/sudokuForm/complete")
	  public String finishSudokuBoard() {
		  System.out.println("inside finish");
		  
		  System.out.println("url for this game is - 'http://localhost:8080/sudokuForm/"+ addDataToDB()+"'");
		  return "redirect:/sudokuForm";
	  }
	  
	  @GetMapping("/sudokuForm/{id}")
	    public String playSudokuForm(@PathVariable int id,Model model) throws ResourceNotFoundException{
		  Optional<Sudoku> sudoku = Optional.ofNullable(sudokurepository.findById(id));
		    if (sudoku.isEmpty()) {
		      throw new ResourceNotFoundException("No post with the requested ID");
		    }
		    if(gameStart)
		    	getBoard(id);
		    model.addAttribute("board", sudokuGrid);
		   
		  System.out.println("its working");
	        return  "sudokuPlay";
	    }
	  
	  public void getBoard(int id) {
		  sudokuGrid.clear();
		    for (int i = 0; i < 9; i++) {
		        ArrayList<Integer> row = new ArrayList<>();
		        for (int j = 0; j < 9; j++) {
		            row.add(0); // Initialize all cells with 0
		        }
		        sudokuGrid.add(row);
		    }

		    
		    for (int i = id; i < id + 81; i++) {
		    	Optional<Sudoku> sudokuEntry = Optional.ofNullable(sudokurepository.findById(id));
		        if (sudokuEntry.isPresent()) {
		            Sudoku entry = sudokuEntry.get();
		            int row = entry.getSudokuRow();
		            int col = entry.getSudokuCol();
		            int num = entry.getSudokuNum();

		            sudokuGrid.get(row).set(col, num);
		        }
		    }
	  }
	  
	  

	  
	  public boolean isValidMove( int row, int col, int value) {

	        if (value < 1 || value > 9) {
	            return false;
	        }


	        ArrayList<Integer> rowList = board.get(row);
	        if (rowList.contains(value)) {
	            return false;
	        }


	        for (ArrayList<Integer> rowArrayList : board) {
	            if (rowArrayList.get(col) == value) {
	                return false;
	            }
	        }

	        int subgridStartRow = (row / 3) * 3;
	        int subgridStartCol = (col / 3) * 3;
	        for (int i = subgridStartRow; i < subgridStartRow + 3; i++) {
	            for (int j = subgridStartCol; j < subgridStartCol + 3; j++) {
	                if (board.get(i).get(j).equals(value)) {
	                    return false;
	                }
	            }
	        }
	        

	        return true;
	    }
}

