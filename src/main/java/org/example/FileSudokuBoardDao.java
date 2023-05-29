package org.example;

import java.io.*;

public class FileSudokuBoardDao implements Dao<SudokuBoard> {

    private final String file_name;

    FileSudokuBoardDao(String fileName) {
        this.file_name = fileName;
    }

    @Override
    public void write(SudokuBoard boardObj) throws IOException {
        try (FileOutputStream fStream = new FileOutputStream(file_name);
             ObjectOutputStream oStream = new ObjectOutputStream(fStream))
        {
            oStream.writeObject(boardObj);
        }
    }

    @Override
    public SudokuBoard read() throws IOException, ClassNotFoundException {
        try (FileInputStream fStream = new FileInputStream(file_name);
             ObjectInputStream oStream = new ObjectInputStream(fStream)) {

            return (SudokuBoard) oStream.readObject();
        }
    }

    @Override
    public void close() {
        System.out.println("*******Closed stream*******\n");
    }
}
