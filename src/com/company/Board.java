package com.company;

import java.util.ArrayList;

public class Board {

    private int size;
    private int[][] board;

    public Board(int size) {
        this.size = size;
        board=new int[size][size];
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] board) {
        this.board = board;
    }

    public void show()
    {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
