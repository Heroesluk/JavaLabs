package org.example;

import java.util.List;

public class SudokuBox extends SudokuPart {
    SudokuBox(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {

        return validate(fields);
    }
}
