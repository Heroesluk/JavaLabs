package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SudokuPart {
    protected List<SudokuField> fields;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SudokuPart that = (SudokuPart) o;

        return new EqualsBuilder().append(fields, that.fields).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(fields).toHashCode();
    }

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
    @Override
    public String toString() {
        StringBuilder b1 = new StringBuilder();
        for(SudokuField f: fields){
            b1.append(f.get_field_value());
            b1.append(" ");
        }

        return b1.toString();
    }

}
