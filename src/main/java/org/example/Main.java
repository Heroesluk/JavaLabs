package org.example;

import java.text.MessageFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
//        Greeter greet = new Greeter(new MessageFormatter());
//        System.out.println(greet.greet("a"));

        SudokuBoard board1 = new SudokuBoard(6);
        board1.fill_board();
        board1.print_out2d(board1.board);

        int[][] cell = board1.get_cell(1);
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

class MessageFormatter{
    String format(String message){
        return message;
    }
}


class SudokuBoard{
    //cells inside sudoku are numbered
    // 0 1 2
    // 3 4 5
    // 6 7 8

    //////////////////

    // 0 1
    // 2 3

    int[][] board;
    int size;
    int cell_size;
    int cells_per_row;


    SudokuBoard(int size){
        this.size = size;
        this.cell_size = 3;
        this.cells_per_row = (int) size/cell_size;
        board = new int[size][size];
    }

    void fill_board(){

        for(int y =0; y<size; y++){
            for(int x=0; x<size; x++){
                board[y][x] = 0;
            }

        }

        board[0][0] = 1;
        board[5][0] = 4;
        board[0][5] = 7;
        board[5][5] = 9;
    }

    void print_out2d(int[][] board2d){
        for(int[] row: board2d){
            for(int item: row){
                System.out.print(item);
            }
            System.out.print("\n");
        }
    }

    int[][] get_cell(int cell_index){
        int y = (int) cell_index/cells_per_row;
        //System.out.print(y);
        int x = cell_index % cells_per_row;
        //System.out.print(x);

        int[][] slice = new int[cell_size][cell_size];

        for(int i =(y*cell_size); i<(y*cell_size)+cell_size;i++){
            slice[i-(y*cell_size)] = Arrays.copyOfRange(board[i],x*cell_size,(x*cell_size)+cell_size );
        }

        // 0 -> 0:2 | 0:2 0 0
        // 1 -> 0:2 | 2:4 1 0
        // 2 -> 2:4 | 0:2 0 1
        // 3 -> 2:4 | 2:4 1 1


        return slice;



    };






}
