package com.learning.sudokuweb.controller.binding;


import lombok.Data;

@Data
public class createSudokuBoard {
	private int sudokuRow;
	private int sudokuCol;
	private int sudokuNum;
	
	 public int getSudokuRow() {
	        return sudokuRow;
	    }

	    public void setSudokuRow(int sudokuRow) {
	        this.sudokuRow = sudokuRow;
	    }

	    public int getSudokuCol() {
	        return sudokuCol;
	    }

	    public void setSudokuCol(int sudokuCol) {
	        this.sudokuCol = sudokuCol;
	    }

	    public int getSudokuNum() {
	        return sudokuNum;
	    }

	    public void setSudokuNum(int sudokuNum) {
	        this.sudokuNum = sudokuNum;
	    }

}
