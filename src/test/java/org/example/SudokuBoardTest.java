package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {


    SudokuBoard test_board = new SudokuBoard(9, 3);


    @Test
    void constructor() {
        assertEquals(test_board.copy_of_board().length, 9);
        assertEquals(test_board.copy_of_board()[0].length, 9);

    }


    @Test
    void solve() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        int[][] test = {{1, 0, 0, 0, 0, 0, 0, 0, 7}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {6, 0, 0, 0, 0, 0, 0, 0, 9}};

        board1.fill_board(test);
        board1.solve();
        assertTrue(board1.validate_full());

        System.out.println(board1);


    }

    @Test
    void fields_test() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.set(0, 0, 9);
        assertEquals(board1.get(0, 0), 9);
        board1.set(0, 0, 0);
        assertEquals(board1.get(0, 0), 0);

    }

    @Test
    void test_col() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.set(0, 0, 1);
        board1.set(1, 0, 5);
        board1.set(2, 0, 7);
        board1.set(3, 0, 9);
        SudokuBoard.SudokuColumn col = board1.getCol(0);
        assertTrue(col.verify());

        board1.set(4, 0, 9);
        SudokuBoard.SudokuColumn col2 = board1.getCol(0);
        assertFalse(col2.verify());


    }

    @Test
    void test_row() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.set(0, 1, 1);
        board1.set(0, 2, 5);
        board1.set(0, 3, 7);
        board1.set(0, 4, 9);
        SudokuBoard.SudokuRow row = board1.getRow(0);
        assertTrue(row.verify());

        board1.set(0, 0, 9);
        SudokuBoard.SudokuRow row2 = board1.getRow(0);
        assertFalse(row2.verify());
    }

    @Test
    void test_box() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.set(3, 0, 1);
        board1.set(4, 0, 2);
        board1.set(5, 0, 3);
        board1.set(3, 1, 4);
        board1.set(4, 1, 5);
        board1.set(5, 1, 6);

        SudokuBoard.SudokuBox box = board1.getBox(3);
        assertTrue(box.verify());

        board1.set(3, 2, 1);
        SudokuBoard.SudokuBox box1 = board1.getBox(3);
        assertFalse(box1.verify());


    }


}