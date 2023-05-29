package org.example;

public class SudokuField {
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
