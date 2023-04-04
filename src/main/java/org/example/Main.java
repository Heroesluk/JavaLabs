package org.example;

import java.util.*;


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


class PrintOut2d {
    public static void print_out2d(int[][] board2d) {

        for (int[] row : board2d) {
            for (int item : row) {
                System.out.print(item);
            }
            System.out.print("\n");
        }
    }
}

class SudokuBoard {
    class SudokuField {
        private Integer value;

        public void set_field_value(Integer val) {
            value = val;
        }

        public Integer get_field_value() {
            return value;
        }

        SudokuField(int value) {
            this.value = value;
        }


    }

    //cells inside sudoku are numbered
    // 0 1 2
    // 3 4 5
    // 6 7 8
    private int[][] board;
    private SudokuField[][] fields;

    int size;
    int cells_per_row;
    int cell_size = 3;


    @Override
    public String toString() {
        StringBuilder val = new StringBuilder();
        for (SudokuField[] row : fields) {
            for (SudokuField field : row) {
                val.append(field.get_field_value());
                val.append(" ");
            }
            val.append("\n");

        }
        return val.toString();
    }

    class SudokuPart {
        protected SudokuField[] fields;


        boolean validate(SudokuField[] arr) {
            Set<Integer> temp = new HashSet<>();
            for (SudokuField item : arr) {
                if (item.get_field_value() != 0) {
                    Integer itm = item.get_field_value();
                    if (!temp.add(itm)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    class SudokuRow extends SudokuPart {
        SudokuRow(SudokuField[] fields) {
            this.fields = fields;
        }

        public boolean verify() {
            return validate(fields);
        }
    }

    class SudokuColumn extends SudokuPart {
        SudokuColumn(SudokuField[] fields) {
            this.fields = fields;
        }

        public boolean verify() {
            return validate(fields);
        }
    }

    class SudokuBox extends SudokuPart {
        SudokuBox(SudokuField[] fields) {
            this.fields = fields;
        }

        public boolean verify() {

            return validate(fields);
        }
    }


    SudokuBoard(int size, int cells_per_row) {
        this.size = size;
        this.cells_per_row = cells_per_row;
        this.fields = new SudokuField[size][size];
        for (int i = 0; i < size; i++) {
            for (int x = 0; x < size; x++) {
                this.fields[i][x] = new SudokuField(0);

            }
        }


        board = new int[size][size];
        fill_board();
    }



    void set(int posy, int posx, int num) {
        board[posy][posx] = num;


    }

    Integer getF(int posy, int posx) {
        return fields[posy][posx].get_field_value();

    }

    void setF(int posy, int posx, Integer num) {
        fields[posy][posx].set_field_value(num);
    }

    boolean check_if_legal(int posy, int posx, int num) {
        setF(posy, posx, num);
        boolean test = validate_full();
        setF(posy, posx, 0);
        return test;
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


    boolean validate_full() {
        for (int i = 0; i < size; i++) {

            if (!getCol(i).verify()) {
                return false;
            }

            if (!getRow(i).verify()) {
                return false;
            }

            if (!getBox(i).verify()) {
                return false;
            }


        }
        return true;
    }

//    SudokuRow getRow(Integer column){
//        return new SudokuRow()
//    }


    void solve() {
        BacktrackingSudokuSolver.solve(this);
    }

    SudokuColumn getCol(Integer x) {
        SudokuField[] fields = new SudokuField[9];
        for (int y = 0; y < size; y++) {
            fields[y] = new SudokuField(getF(y, x));
        }

        return new SudokuColumn(fields);
    }

    SudokuRow getRow(Integer y) {
        SudokuField[] fields = new SudokuField[9];
        for (int x = 0; x < size; x++) {
            fields[x] = new SudokuField(getF(y, x));
        }

        return new SudokuRow(fields);
    }

    SudokuBox getBox(Integer index) {
        SudokuField[] fields = new SudokuField[9];


        int y = index / cells_per_row;
        int x = index % cells_per_row;

        int ind = 0;

        for (int i = (y * cell_size); i < (y * cell_size) + cell_size; i++) {
            for (int j = x * cell_size; j < (x * cell_size) + cell_size; j++) {
                fields[ind] = new SudokuField(getF(i, j));
                ind++;

            }
        }

        return new SudokuBox(fields);

    }


    static class BacktrackingSudokuSolver implements SudokuSolver {

        public static boolean solve(SudokuBoard brd) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                list.add(i);
            }
            Collections.shuffle(list);

            for (int row = 0; row < brd.size; row++) {
                for (int col = 0; col < brd.size; col++) {
                    if (brd.getF(row, col) == 0) {


                        for (Integer num: list) {
                            if (brd.check_if_legal(row, col, num)) {
                                brd.setF(row, col, num);

                                if (solve(brd)) {
                                    return true;
                                } else {
                                    brd.setF(row, col, num);
                                }
                            }
                        }
                        return false;
                    }
                }
            }
            return true;
        }


    }


    interface SudokuSolver {
        static boolean solve(SudokuBoard brd) {
            return false;
        }
    }

}


