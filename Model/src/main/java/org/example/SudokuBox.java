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

    @Override
    public String toString() {
        ToStringBuilder b1 = new ToStringBuilder(this).append(super.toString());
        return b1.toString();
    }


}
