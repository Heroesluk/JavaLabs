package org.example;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Greeter greet = new Greeter(new MessageFormatter());
//        System.out.println(greet.greet("a"));

        SudokuBoard board1 = new SudokuBoard(4, 2);

        board1.board[0][0] =1;
        board1.board[0][1] =4;
        board1.board[1][0] =3;
        board1.board[1][1] =2;

        board1.board[2][0] =4;
        board1.board[2][1] =1;
        board1.board[3][0] =2;
        board1.board[3][1] =3;

        board1.board[2][2] =2;
        board1.board[2][3] =3;
        board1.board[3][2] =4;
        board1.board[3][3] =1;

        board1.board[0][2] =3;
        board1.board[0][3] =2;
        board1.board[1][2] =1;
        board1.board[1][3] =4;

        board1.print_out2d(board1.board);
        System.out.print(board1.validate_full());



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
    int cells_per_row;
    int cell_size = 2;


    SudokuBoard(int size, int cells_per_row) {
        this.size = size;
        this.cells_per_row = cells_per_row;
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

    int[] cell_to_arr(int[][] cell) {
        int[] arr = new int[size];
        int index = 0;
        for (int[] row : cell) {
            for (int item : row) {
                arr[index] = item;
                index++;
            }


        }

        return arr;

    }

    int[] get_column(int x_cordinate) {
        int[] column = new int[size];
        for (int i = 0; i < size; i++) {
            column[i] = board[i][x_cordinate];
        }
        return column;
    }

    int[] get_row(int y_cordinate) {
        int[] row = new int[size];
        for (int i = 0; i < size; i++) {
            row[i] = board[y_cordinate][i];
        }
        return row;
    }

    boolean validate(int[] arr) {
        Set<Integer> temp = new HashSet<>();
        for (int item : arr) {
            if (item != 0) {
                Integer itm = item;
                if (!temp.add(itm)) {
                    return false;
                }
            }
        }
        return true;
    }


    boolean validate_full() {
        for (int i = 0; i < size; i++) {
            if (!validate(get_column(i))) {
                return false;
            }
            ;
            if (!validate(get_row(i))) {
                return false;
            }
            ;
            if (!validate(cell_to_arr(get_cell(i)))) {
                return false;
            }
            ;

        }
        return true;
    }

    void solve(int x, int y) {
        boolean found = false;

        for (int i = 1; i < 10; i++) {
            board[y][x] = i;
            if (validate_full()) {
                found = true;
                if (x == 8) {
                    x = 0;
                    if (y == 8) {
                        return;
                    }
                    y++;

                }
                else {
                    x++;
                }
                print_out2d(board);
                solve(x,y);
                break;
            }


        }


    }

}
