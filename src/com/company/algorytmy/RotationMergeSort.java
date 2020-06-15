package com.company.algorytmy;

import com.company.rdzen.AbstractSortAlgorithm;
import com.company.rdzen.SortAlgorithm;
import com.company.rdzen.SortArray;

public final class RotationMergeSort extends AbstractSortAlgorithm {

    public static final SortAlgorithm INSTANCE = new RotationMergeSort();

    @Override
    public void sort(SortArray array) {
        sort(array, 0, array.length());
        array.setRange(0, array.length(), SortArray.ElementState.DONE);
    }

    private static void sort(SortArray array, int start, int end) {
        if(!(0 <= start && start <= end && end <= array.length()))
            throw new IllegalArgumentException();
        if(end - start <= 1)
            return;

        array.setRange(start, end, SortArray.ElementState.ACTIVE);
        int mid = (start + end) / 2;
        array.setRange(mid, end, SortArray.ElementState.INACTIVE);
        sort(array, start, mid);
        array.setRange(start, mid, SortArray.ElementState.INACTIVE);
        sort(array, mid, end);
        merge(array, start, mid, end);
    }

    private static void merge(SortArray array, int start, int mid, int end) {
        if(!(0 <= start && start <= mid && mid <= end && end <= array.length()))
            throw new IllegalArgumentException();
        if(start == mid || mid == end)
            return;
        array.setRange(start, end, SortArray.ElementState.ACTIVE);

        int left = mid - 1;
        int right = mid;
        while (start <= left && right < end && array.compare(left, right) > 0) {
            left--;
            right++;
        }

        int n = right - mid;
        for (int i = 0; i < n; i++)
            array.swap(mid - n + i, mid + i);

        array.setRange(mid, end, SortArray.ElementState.INACTIVE);
        merge(array, start, left + 1, mid);
        array.setRange(start, mid, SortArray.ElementState.INACTIVE);
        merge(array, mid, right, end);
    }

    private RotationMergeSort() {
        super("Sortowanie ze scalaniem rotacyjnym");
    }
}
