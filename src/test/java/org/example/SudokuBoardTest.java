package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {
    SudokuBoard test_board = new SudokuBoard(4);

    @Test
    void constructor(){
        assertEquals(test_board.board.length,4);
        assertEquals(test_board.board[0].length,4);



    }

}