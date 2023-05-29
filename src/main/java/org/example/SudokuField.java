package org.example;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class SudokuField implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        SudokuField that = (SudokuField) o;

        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(value).toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("value", value)
                .toString();
    }
}
