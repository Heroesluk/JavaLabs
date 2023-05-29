package org.example;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

public class SudokuBox extends SudokuPart {
    SudokuBox(List<SudokuField> fields) {
        this.fields = fields;
    }

    public boolean verify() {

        return validate(fields);
    }


}
