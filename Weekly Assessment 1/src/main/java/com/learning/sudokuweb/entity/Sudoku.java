package com.learning.sudokuweb.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Sudoku {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

	private int sudokuRow;

	private int sudokuCol;

	private int sudokuNum;
	
	public int getId() {
        return id;
    }
	
    public int getSudokuRow() {
        return sudokuRow;
    }

    // Setter for sudokuRow
    public void setSudokuRow(int sudokuRow) {
        this.sudokuRow = sudokuRow;
    }

    // Getter for sudokuCol
    public int getSudokuCol() {
        return sudokuCol;
    }

    // Setter for sudokuCol
    public void setSudokuCol(int sudokuCol) {
        this.sudokuCol = sudokuCol;
    }

    // Getter for sudokuNum
    public int getSudokuNum() {
        return sudokuNum;
    }

    // Setter for sudokuNum
    public void setSudokuNum(int sudokuNum) {
        this.sudokuNum = sudokuNum;
    }
}
