package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPart {
    protected List<SudokuField> fields;


    boolean validate(List<SudokuField> arr) {
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
