package com.company.helper;

import com.company.rdzen.AbstractSortArray;

final class SimpleSortArray extends AbstractSortArray implements Cloneable {

    public long comparisonCount;
    public long swapCount;

    public SimpleSortArray(int size) {
        super(size);
        shuffle();
        comparisonCount = 0;
        swapCount = 0;
    }

    public int compare(int i, int j) {
        comparisonCount++;
        return super.compare(i, j);
    }

    public void swap(int i, int j) {
        swapCount++;
        super.swap(i, j);
    }


    public SimpleSortArray clone() {
        try {
            SimpleSortArray result = (SimpleSortArray)super.clone();
            result.values = result.values.clone();
            return result;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
