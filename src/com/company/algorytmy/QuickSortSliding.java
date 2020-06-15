package com.company.algorytmy;

import com.company.rdzen.AbstractSortAlgorithm;
import com.company.rdzen.SortAlgorithm;
import com.company.rdzen.SortArray;

public final class QuickSortSliding extends AbstractSortAlgorithm {

    public static final SortAlgorithm INSTANCE = new QuickSortSliding();


    public void sort(SortArray array) {
        sort(array, 0, array.length());
    }

    private static void sort(SortArray array, int start, int end) {
        if(start == end)
            return;

        array.setRange(start, end, SortArray.ElementState.INACTIVE);
        int partition = start;
        int pivot = end - 1;
        for (int i = start; i < end - 1; i++) {
            if(array.compare(i, pivot) < 0) {
                array.swap(i, partition);
                array.setElement(partition, SortArray.ElementState.INACTIVE);
                partition++;
            }
        }
        array.swap(pivot, partition);
        pivot = partition;
        array.setElement(pivot, SortArray.ElementState.DONE);
        array.setRange(pivot + 1, end, SortArray.ElementState.INACTIVE);

        sort(array, start, pivot);
        sort(array, pivot + 1, end);
    }

    private QuickSortSliding() {
        super("Szybkie sortowanie (przesuwane)");
    }
}
