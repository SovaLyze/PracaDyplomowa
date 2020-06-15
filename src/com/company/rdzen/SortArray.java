package com.company.rdzen;

public interface SortArray {

    public int length();

    public int compare(int i, int j);

    public void swap(int i, int j);

    public boolean compareAndSwap(int i, int j);

    public void shuffle();

    public void setElement(int index, ElementState state);

    public void setRange(int start, int end, ElementState state);

    public enum ElementState {
        ACTIVE, INACTIVE, COMPARING, DONE;
    }
}
