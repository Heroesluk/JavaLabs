package org.example;

import java.util.*;



public class SudokuBoard {

    //cells inside sudoku are numbered
    // 0 1 2
    // 3 4 5
    // 6 7 8
    private SudokuField[][] fields;
    private LinkedHashMap<Integer, SudokuField> fields2 = new LinkedHashMap<>();

    int size;
    int cells_per_row;
    int cell_size = 3;





    StringBuilder val;


    @Override
    public String toString() {
        val = new StringBuilder();
        for (Map.Entry<Integer, SudokuField> entry : fields2.entrySet()) {
            if (entry.getKey() % 9 == 0 && entry.getKey() != 0) {
                val.append("\n");

            }
            val.append(entry.getValue().get_field_value());

            val.append(" ");


        }
        return val.toString();
    }


    SudokuBoard(int size, int cells_per_row) {
        this.size = size;
        this.cells_per_row = cells_per_row;

        for (int i = 0; i < 81; i++) {
            fields2.put(i, new SudokuField(0));
        }


    }


    Integer get(int posy, int posx) {
        return fields2.get((posy * 9) + posx).get_field_value();

    }

    void set(int posy, int posx, Integer num) {
        fields2.get((posy * 9) + posx).set_field_value(num);

    }

    boolean check_if_legal(int posy, int posx, int num) {
        set(posy, posx, num);
        boolean test = validate_full();
        set(posy, posx, 0);
        return test;
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


    void solve() {
        BacktrackingSudokuSolver.solve(this);
    }

    SudokuColumn getCol(Integer x) {
        List<SudokuField> fields = new ArrayList<>();
        for (int y = 0; y < size; y++) {
            fields.add(new SudokuField(get(y, x)));

        }

        return new SudokuColumn(fields);
    }

    SudokuRow getRow(Integer y) {
        List<SudokuField> fields = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            fields.add(new SudokuField(get(y, x)));
        }

        return new SudokuRow(fields);
    }

    SudokuBox getBox(Integer index) {
        List<SudokuField> fields = new ArrayList<>();

        int y = index / cells_per_row;
        int x = index % cells_per_row;

        for (int i = (y * cell_size); i < (y * cell_size) + cell_size; i++) {
            for (int j = x * cell_size; j < (x * cell_size) + cell_size; j++) {
                fields.add(new SudokuField(get(i, j)));

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
                    if (brd.get(row, col) == 0) {


                        for (Integer num : list) {
                            if (brd.check_if_legal(row, col, num)) {
                                brd.set(row, col, num);

                                if (solve(brd)) {
                                    return true;
                                } else {
                                    brd.set(row, col, 0);
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


