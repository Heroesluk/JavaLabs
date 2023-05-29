package org.example;

import java.util.List;

public class SudokuColumn extends SudokuPart {
    SudokuColumn(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {
        return validate(fields);
    }
}
