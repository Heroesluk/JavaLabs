package org.example;

import java.util.*;


class FixedSizeList<T> extends ArrayList<T> {
    public FixedSizeList(int size) {
        super(size);
        for (int i = 0; i < size; i++) {
            super.add(null);
        }

    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Elements may not be cleared from a fixed size List.");
    }

    @Override
    public boolean add(T o) {
        throw new UnsupportedOperationException("Elements may not be added to a fixed size List, use set() instead.");
    }

    @Override
    public void add(int index, T element) {
        throw new UnsupportedOperationException("Elements may not be added to a fixed size List, use set() instead.");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Elements may not be removed from a fixed size List.");
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
    private SudokuField[][] fields;
    private FixedSizeList<FixedSizeList<SudokuField>> fields2;

    int size;
    int cells_per_row;
    int cell_size = 3;

    StringBuilder val;


    @Override
    public String toString() {
        val = new StringBuilder();
        for (FixedSizeList<SudokuField> row : fields2) {
            for (SudokuField field : row) {
                val.append(field.get_field_value());
                val.append(" ");
            }
            val.append("\n");

        }
        return val.toString();
    }

    class SudokuPart {
        protected FixedSizeList<SudokuField> fields;


        boolean validate(FixedSizeList<SudokuField> arr) {
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
        SudokuRow(FixedSizeList<SudokuField> fields) {
            this.fields = fields;
        }

        public boolean verify() {
            return validate(fields);
        }
    }

    class SudokuColumn extends SudokuPart {
        SudokuColumn(FixedSizeList<SudokuField> fields) {
            this.fields = fields;
        }

        public boolean verify() {
            return validate(fields);
        }
    }

    class SudokuBox extends SudokuPart {
        SudokuBox(FixedSizeList<SudokuField> fields) {
            this.fields = fields;
        }

        public boolean verify() {

            return validate(fields);
        }
    }


    SudokuBoard(int size, int cells_per_row) {
        this.size = size;
        this.cells_per_row = cells_per_row;

        this.fields2 = new FixedSizeList<>(9);

        for (int i = 0; i < 9; i++) {
            FixedSizeList<SudokuField> field1 = new FixedSizeList<>(9);
            fields2.set(i, field1);
        }


        for (int i = 0; i < size; i++) {
            for (int x = 0; x < size; x++) {
                this.fields2.get(i).set(x, new SudokuField(0));

            }
        }



    }


    Integer get(int posy, int posx) {
//        return fields[posy][posx].get_field_value();
        return fields2.get(posy).get(posx).get_field_value();


    }

    void set(int posy, int posx, Integer num) {
        fields2.get(posy).get(posx).set_field_value(num);

    }

    boolean check_if_legal(int posy, int posx, int num) {
        set(posy, posx, num);
        boolean test = validate_full();
        set(posy, posx, 0);
        return test;
    }


    void fill_board(int[][] board) {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                this.fields2.get(y).get(x).set_field_value(board[y][x]);


            }

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


    void solve() {
        BacktrackingSudokuSolver.solve(this);
    }

    SudokuColumn getCol(Integer x) {
        FixedSizeList<SudokuField> fields = new FixedSizeList<>(9);
        for (int y = 0; y < size; y++) {
            fields.set(y,new SudokuField(get(y, x)));

        }

        return new SudokuColumn(fields);
    }

    SudokuRow getRow(Integer y) {
        FixedSizeList<SudokuField> fields = new FixedSizeList<>(9);
        for (int x = 0; x < size; x++) {
            fields.set(x,new SudokuField(get(y, x)));
        }

        return new SudokuRow(fields);
    }

    SudokuBox getBox(Integer index) {
        FixedSizeList<SudokuField> fields = new FixedSizeList<>(9);


        int y = index / cells_per_row;
        int x = index % cells_per_row;

        int ind = 0;

        for (int i = (y * cell_size); i < (y * cell_size) + cell_size; i++) {
            for (int j = x * cell_size; j < (x * cell_size) + cell_size; j++) {
                fields.set(ind,new SudokuField(get(i, j)));
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


