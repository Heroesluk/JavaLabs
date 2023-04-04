package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {


    SudokuBoard test_board = new SudokuBoard(9, 3);

    int[][] get_cell_input =
            {{1, 0, 0, 0, 2, 0, 0, 3, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {4, 0, 0, 0, 5, 0, 0, 0, 6},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0},
                    {7, 0, 0, 0, 8, 0, 0, 0, 9},
                    {0, 0, 0, 0, 0, 0, 0, 0, 0}};

    int[][] get_col_row_input =
            {{0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {1, 1, 2, 1, 1, 1, 1, 1, 1},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 5, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0},
                    {0, 0, 1, 0, 8, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0, 0, 0, 0}};

    int[][] validate_input =
            {{0, 0, 1, 0, 0, 0, 0, 7, 1},
                    {1, 2, 3, 4, 5, 6, 7, 8, 9},
                    {1, 2, 3, 4, 5, 6, 7, 9, 9},
                    {0, 0, 1, 9, 8, 7, 0, 6, 2},
                    {0, 0, 1, 6, 5, 4, 0, 5, 2},
                    {0, 0, 1, 1, 2, 3, 0, 4, 3},
                    {0, 0, 1, 0, 0, 0, 0, 3, 3},
                    {0, 0, 1, 0, 8, 0, 0, 2, 4},
                    {0, 0, 1, 0, 0, 0, 0, 1, 6}};

    int cell_sum(int[][] board2d) {
        int val = 0;
        for (int[] row : board2d) {
            for (int item : row) {
                val += item;
            }
        }
        return val;
    }


    @Test
    void constructor() {
        assertEquals(test_board.copy_of_board().length, 9);
        assertEquals(test_board.copy_of_board()[0].length, 9);

    }

    @Test
    void get_cell() {
        // for each 3x3 cell, there's one number, coresponding to cell index
        test_board.fill_board(get_cell_input);
        PrintOut2d.print_out2d(test_board.copy_of_board());

        assertEquals(cell_sum(test_board.get_cell(0)), 1);
        assertEquals(cell_sum(test_board.get_cell(1)), 2);
        assertEquals(cell_sum(test_board.get_cell(2)), 3);
        assertEquals(cell_sum(test_board.get_cell(3)), 4);
        assertEquals(cell_sum(test_board.get_cell(4)), 5);
        assertEquals(cell_sum(test_board.get_cell(5)), 6);
        assertEquals(cell_sum(test_board.get_cell(6)), 7);
        assertEquals(cell_sum(test_board.get_cell(7)), 8);
        assertEquals(cell_sum(test_board.get_cell(8)), 9);

    }

    @Test
    void get_column() {
        test_board.fill_board(get_col_row_input);

        assertEquals(Arrays.stream(test_board.get_column(2)).sum(), 10);
        assertEquals(Arrays.stream(test_board.get_column(0)).sum(), 1);
    }

    @Test
    void get_row() {
        test_board.fill_board(get_col_row_input);

        assertEquals(Arrays.stream(test_board.get_row(1)).sum(), 10);
        assertEquals(Arrays.stream(test_board.get_row(0)).sum(), 1);

    }


    @Test
    void validate() {

        test_board.fill_board(validate_input);

        assertTrue(test_board.validate(test_board.get_column(7)));
        assertFalse(test_board.validate(test_board.get_column(8)));

        assertTrue(test_board.validate(test_board.get_row(1)));
        assertFalse(test_board.validate(test_board.get_row(2)));

        assertTrue(test_board.validate(test_board.cell_to_arr(test_board.get_cell(4))));
        assertFalse(test_board.validate(test_board.cell_to_arr(test_board.get_cell(2))));

    }

    @Test
    void cell_to_arr() {

        test_board.fill_board(validate_input);
        int[] arr = test_board.cell_to_arr(test_board.get_cell(4));
        assertEquals(Arrays.stream(arr).sum(), 45);
    }

    @Test
    void solve() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        int[][] test = {{1, 0, 0, 0, 0, 0, 0, 0, 7}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {6, 0, 0, 0, 0, 0, 0, 0, 9}};

        board1.fill_board(test);
        PrintOut2d.print_out2d(board1.copy_of_board());
        board1.solve();
        assertTrue(board1.validate_full());


    }

    @Test
    void fields_test() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.setF(0, 0, 9);
        assertEquals(board1.getF(0, 0), 9);
        board1.setF(0, 0, 0);
        assertEquals(board1.getF(0, 0), 0);

    }

    @Test
    void test_col() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.setF(0, 0, 1);
        board1.setF(1, 0, 5);
        board1.setF(2, 0, 7);
        board1.setF(3, 0, 9);
        SudokuBoard.SudokuColumn col = board1.getCol(0);
        assertTrue(col.verify());

        board1.setF(4, 0, 9);
        SudokuBoard.SudokuColumn col2 = board1.getCol(0);
        assertFalse(col2.verify());


    }

    @Test
    void test_row() {
        SudokuBoard board1 = new SudokuBoard(9, 3);
        board1.setF(0, 1, 1);
        board1.setF(0, 2, 5);
        board1.setF(0, 3, 7);
        board1.setF(0, 4, 9);
        SudokuBoard.SudokuRow row = board1.getRow(0);
        assertTrue(row.verify());

        board1.setF(0, 0, 9);
        SudokuBoard.SudokuRow row2 = board1.getRow(0);
        assertFalse(row2.verify());
    }

    @Test
    void test_box(){
        SudokuBoard board1 = new SudokuBoard(9,3);
        board1.setF(3, 0, 1);
        board1.setF(4, 0, 2);
        board1.setF(5, 0, 3);
        board1.setF(3, 1, 4);
        board1.setF(4, 1, 5);
        board1.setF(5, 1, 6);

        SudokuBoard.SudokuBox box = board1.getBox(3);
        assertTrue(box.verify());

        board1.setF(3,2,1);
        SudokuBoard.SudokuBox box1 = board1.getBox(3);
        assertFalse(box1.verify());


    }




}