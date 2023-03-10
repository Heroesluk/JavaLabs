package org.example;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Greeter greet = new Greeter(new MessageFormatter());
//        System.out.println(greet.greet("a"));

        SudokuBoard board1 = new SudokuBoard(9, 3);

        int[][] test = {{1, 0, 0, 0, 0, 0, 0, 0, 7}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 5, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0}, {6, 0, 0, 0, 0, 0, 0, 0, 9}};

        board1.fill_board(test);
        board1.print_out2d();
        board1.solve();


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
    private int[][] board;
    int size;
    int cells_per_row;
    int cell_size = 3;


    SudokuBoard(int size, int cells_per_row) {
        this.size = size;
        this.cells_per_row = cells_per_row;
        board = new int[size][size];
        fill_board();
    }


    int[][] copy_of_board() {
        int[][] cboard = new int[board.length][];
        for (int i = 0; i < board.length; i++)
            cboard[i] = board[i].clone();

        return cboard;
    }

    int[] random_order() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list.stream().mapToInt(i -> i).toArray();
    }


    void fill_board() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                board[y][x] = 0;
            }
        }
    }

    void fill_board(int[][] board) {
        for (int y = 0; y < size; y++) {
            System.arraycopy(board[y], 0, this.board[y], 0, size);
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

    void print_out2d() {
        for (int[] row : board) {
            for (int item : row) {
                System.out.print(item);
            }
            System.out.print("\n");
        }
    }


    int[][] get_cell(int cell_index) {
        int y = cell_index / cells_per_row;
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
        System.arraycopy(board[y_cordinate], 0, row, 0, size);
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

            if (!validate(get_row(i))) {
                return false;
            }

            if (!validate(cell_to_arr(get_cell(i)))) {
                return false;
            }


        }
        return true;
    }


    void solve() {
        if (solveSudoku(board, 0, 0)) {
            print_out2d();
        }


    }



    boolean solveSudoku(int[][] grid, int row, int col) {

        if (row == size - 1 && col == size) return true;

        if (col == size) {
            row++;
            col = 0;
        }

        if (grid[row][col] != 0) return solveSudoku(grid, row, col + 1);

        for (int num : random_order()) {
            grid[row][col] = num;
            if (validate_full()) {
                if (solveSudoku(grid, row, col + 1)) return true;
            }
            grid[row][col] = 0;
        }
        return false;
    }
}


