package org.example;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    SudokuBoard test_board = new SudokuBoard(9);





    @Test
    void constructor(){
        assertEquals(test_board.board.length,9);
        assertEquals(test_board.board[0].length,9);

    }

    @Test
    void get_cell(){
        // for each 3x3 cell, there's one number, coresponding to cell index
        test_board.board[0][0] = 0;
        test_board.board[0][4] = 1;
        test_board.board[0][8] = 2;
        test_board.board[4][0] = 3;
        test_board.board[4][4] = 4;
        test_board.board[4][8] = 5;
        test_board.board[8][0] = 6;
        test_board.board[8][4] = 7;
        test_board.board[8][8] = 8;

        assertEquals(cell_sum(test_board.get_cell(0)),0);
        assertEquals(cell_sum(test_board.get_cell(1)),1);
        assertEquals(cell_sum(test_board.get_cell(2)),2);
        assertEquals(cell_sum(test_board.get_cell(3)),3);
        assertEquals(cell_sum(test_board.get_cell(4)),4);
        assertEquals(cell_sum(test_board.get_cell(5)),5);
        assertEquals(cell_sum(test_board.get_cell(6)),6);
        assertEquals(cell_sum(test_board.get_cell(7)),7);
        assertEquals(cell_sum(test_board.get_cell(8)),8);

    }

    @Test
    void get_column(){
        test_board.board[0][0]=2;
        test_board.board[4][0]=2;
        test_board.board[7][0]=2;
        test_board.board[8][0]=2;

        assertEquals(Arrays.stream(test_board.get_column(0)).sum(),8);
        assertEquals(Arrays.stream(test_board.get_column(1)).sum(),0);
    }

    @Test
    void get_row(){
        test_board.board[0][1]=2;
        test_board.board[0][4]=2;
        test_board.board[0][6]=2;
        test_board.board[0][8]=2;

        assertEquals(Arrays.stream(test_board.get_row(0)).sum(),8);
        assertEquals(Arrays.stream(test_board.get_row(1)).sum(),0);

    }


    int cell_sum(int[][] board2d){
        int val = 0;
        for(int[] row: board2d){
            for(int item: row){
                val+=item;
            }
        }
        return val;
    }

    @Test
    void validate(){

        assertTrue(test_board.validate(test_board.get_column(0)));

        test_board.board[0][0]=1;
        test_board.board[1][0]=2;
        test_board.board[2][0]=3;
        test_board.board[3][0]=4;
        test_board.board[4][0]=5;
        test_board.board[5][0]=6;
        test_board.board[6][0]=7;
        test_board.board[7][0]=8;
        test_board.board[8][0]=9;

        assertTrue(test_board.validate(test_board.get_column(0)));

        test_board.board[8][0]=8;
        assertFalse(test_board.validate(test_board.get_column(0)));

    }

    @Test
    void cell_to_arr(){
        test_board.board[0][0]=1;
        test_board.board[1][0]=1;
        test_board.board[0][1]=1;

        int[] arr = test_board.cell_to_arr(test_board.get_cell(0));
        assertEquals(Arrays.stream(arr).sum(),3);
    }



}