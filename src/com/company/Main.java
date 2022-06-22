package com.company;
import java.util.*;
public class Main {

    public static void main(String[] args) {

        Scanner scanner=new Scanner(System.in);

        System.out.println("Enter size: ");
        int size=scanner.nextInt();
        Board board=new Board(size);

        System.out.println("Enter values: ");
        int[][] b = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j]=scanner.nextInt();
            }
            System.out.println();
        }

        board.setBoard(b);
        System.out.println("Initial State:");
        board.show();

        SudokuSolver sudokuSolver=new SudokuSolver(board);
        sudokuSolver.solveSudoku();
        System.out.println();
        System.out.println("Final State:");
        board.show();
        System.out.println(sudokuSolver.count);
    }


}
