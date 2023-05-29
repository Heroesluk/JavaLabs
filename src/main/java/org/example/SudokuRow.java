package org.example;

import java.util.List;

public class SudokuRow extends SudokuPart {
    SudokuRow(List<SudokuField> fields) {
        this.fields = fields;


    }

    public boolean verify() {
        return validate(fields);
    }
}



