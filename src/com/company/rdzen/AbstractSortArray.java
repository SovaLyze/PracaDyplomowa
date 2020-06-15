package com.company.rdzen;

import java.util.Random;

public abstract class AbstractSortArray implements SortArray{

    protected int[] values;

    public AbstractSortArray(int size) {
        if(size < 0)
            throw new IllegalArgumentException();

        values = new int[size];
        for(int i = 0; i < values.length; i++)
            values[i] = i;
    }

    public int length() {
        return values.length;
    }

    public int compare(int i, int j) {
        if(i < 0 || j < 0 || i >= values.length || j >= values.length)
            throw new IndexOutOfBoundsException();
        return Integer.compare(values[i], values[j]);
    }

    public void swap(int i, int j) {
        if(i < 0 || j < 0 || i >= values.length || j >= values.length)
            throw new IndexOutOfBoundsException();
        int temp = values[i];
        values[i] = values[j];
        values[j] = temp;
    }

    public boolean compareAndSwap(int i, int j) {
        if(compare(j, i) < 0) {
            swap(i, j);
            return true;
        } else
            return false;
    }

    public void shuffle() {
        for(int i = length() - 1; i > 0; i--)
            swap(i, random.nextInt(i + 1));
    }

    public void setElement(int index, SortArray.ElementState state) {

    }

    public void setRange(int start, int end, SortArray.ElementState state) {

    }

    public static final Random random = new Random();
}
