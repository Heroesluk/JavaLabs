package org.example;

import java.text.MessageFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Greeter greet = new Greeter(new MessageFormatter());
//        System.out.println(greet.greet("a"));

        SudokuBoard board1 = new SudokuBoard(9);
        board1.print_out2d(board1.board);

        int[][] cell = board1.get_cell(7);
        board1.print_out2d(cell);
    }
}

class Greeter {
    MessageFormatter formatter;

    Greeter(MessageFormatter formatter) {
        this.formatter = formatter;

    }

    String greet(String who) {
        return formatter.format(who);
    }


}

class MessageFormatter {
    String format(String message) {
        return message;
    }
}


class SudokuBoard {
    //cells inside sudoku are numbered
    // 0 1 2
    // 3 4 5
    // 6 7 8
    int[][] board;
    int size;
    int cell_size =3;
    int cells_per_row=3;

    SudokuBoard(int size) {
        this.size = size;
        board = new int[size][size];
        fill_board();
    }

    void fill_board() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = 0;
            }
        }
    }

    void print_out2d(int[][] board2d) {
        for (int[] row : board2d) {
            for (int item : row) {
                System.out.print(item);
            }
            System.out.print("\n");
        }
    }

    int[][] get_cell(int cell_index) {
        int y = (int) cell_index / cells_per_row;
        int x = cell_index % cells_per_row;
        int[][] slice = new int[cell_size][cell_size];

        for (int i = (y * cell_size); i < (y * cell_size) + cell_size; i++) {
            slice[i - (y * cell_size)] = Arrays.copyOfRange(board[i], x * cell_size, (x * cell_size) + cell_size);
        }

        return slice;
    }

    int[] get_column(int x_cordinate){
        int[] column = new int[size];
        for(int i=0;i<size;i++) {
            column[i] = board[i][x_cordinate];
        }
        return column;
    }

    int[] get_row(int y_cordinate){
        int[] row = new int[size];
        for(int i=0;i<size;i++) {
            row[i] = board[y_cordinate][i];
        }
        return row;
    }




}
