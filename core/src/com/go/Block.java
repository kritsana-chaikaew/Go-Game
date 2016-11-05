package com.go;

public class Block {
    public static final int BLOCK_SIZE = 64;

    protected int row;
    protected int column;

    public Block(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow () {
        return row;
    }

    public int getColumn () {
        return column;
    }

    public void setRow (int row) {
        this.row = row;
    }

    public void setColumn (int column) {
        this.column = column;
    }
}
