package com.company;

import java.util.ArrayList;

public class SudokuSolver {

    int count=0;
    boolean firstRun=true;
    private Board board;

    public SudokuSolver(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }


    public boolean solveSudoku()
    {
        count++;
        if (isCompleted())
        {
            return true;
        }
        else
        {
            int[] chosenSquare= chooseSquare();
            int row=chosenSquare[0];
            int column=chosenSquare[1];

            //FC
            // check is the size of domain is zero
            if (sizeOfDomain(row,column)==0)
            {
                return false;
            }

            //FC
            //do not see all the values. it only sees valid values
            //from 1 to size
            for (int i = 1; i <= board.getSize() ; i++) {
                if (isValidPlacement(i , row , column))
                {
                    board.getBoard()[row][column]=i;

                    firstRun=false;
                    boolean flag=solveSudoku();
                    if (flag)
                    {
                        return true;
                    }
                    else
                    {
                        board.getBoard()[row][column]=0;
                    }

                }
            }
            return false;
        }

    }


    private boolean isCompleted()
    {
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j]==0)
                {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] chooseSquare2()
    {
        int[] finalSquare=new int[2];
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getBoard()[i][j]==0)
                {
                    finalSquare[0]=i;
                    finalSquare[1]=j;
                    return finalSquare;
                }
            }
        }
        return finalSquare;
    }




    private int[] chooseSquare()
    {
        /*using mrv:  instead of choosing the first empty square, the square with the least number of possible
        values is chosen.*/

        //using degree for the fist square
        if (firstRun)
        {
            //finding maximum degree
            int maxDegree=degree(0,0);
            int maxRow=0;
            int maxColumn=0;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (board.getBoard()[i][j]==0)
                    {
                        int tempDegree=degree(i,j);
                        if (tempDegree>maxDegree)
                        {
                            maxDegree=tempDegree;
                            maxRow=i;
                            maxColumn=j;
                        }
                    }
                }
            }

            int[] finalSquare=new int[2];
            finalSquare[0]=maxRow;
            finalSquare[1]=maxColumn;
            return finalSquare;
        }

        //using mrv
        else
        {
            int minSize= board.getSize()+1;
            int minRow=0;
            int minColumn=0;
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    if (board.getBoard()[i][j]==0)
                    {
                        int tempSize=sizeOfDomain(i , j);
                        if (tempSize<minSize)
                        {
//                            sizeOfDomain=tempSize;
                            minSize=tempSize;
                            minRow=i;
                            minColumn=j;
                        }
                    }
                }
            }

            int[] finalSquare=new int[2];
            finalSquare[0]=minRow;
            finalSquare[1]=minColumn;
            return finalSquare;
        }


    }

    private int degree( int row , int column)
    {
        int sum=0;
        //check row
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getBoard()[row][i]==0)
            {
                sum++;
            }
        }
        sum--;

        //check column
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getBoard()[i][column]==0)
            {
                sum++;
            }
        }
        sum--;

        return sum;
    }



    private boolean isNumberInColumn(int number , int column)
    {
        for (int i = 0; i < board.getSize(); i++) {
            if(number==board.getBoard()[i][column])
            {
                return true;
            }
        }
        return false;
    }

    private boolean isNumberInRow(int number , int row)
    {
        for (int i = 0; i < board.getSize(); i++) {
            if(number==board.getBoard()[row][i])
            {
                return true;
            }
        }
        return false;
    }

    private int sizeOfDomain(  int row ,int column)
    {
        ArrayList<Integer> usedNumbers=new ArrayList<>();


        for (int i = 0; i < board.getSize(); i++) {
            //check row
            if (board.getBoard()[row][i] !=0 )
            {
                if (usedNumbers.indexOf(board.getBoard()[row][i])==-1)
                {
                    usedNumbers.add(board.getBoard()[row][i]);
                }
            }

            //check column
            if (board.getBoard()[i][column] !=0 )
            {
                if (usedNumbers.indexOf(board.getBoard()[i][column])==-1)
                {
                    usedNumbers.add(board.getBoard()[i][column]);
                }
            }
        }

        return board.getSize()-usedNumbers.size();
    }


    private boolean isValidPlacement(int number , int row , int column)
    {
        return !isNumberInColumn(number,column) && !isNumberInRow(number , row);
    }


}
