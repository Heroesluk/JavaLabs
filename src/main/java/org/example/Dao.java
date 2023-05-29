package org.example;

import java.io.IOException;

public interface Dao<T> extends AutoCloseable {

    T read() throws IOException, ClassNotFoundException;

    void write(T object) throws IOException;
}

