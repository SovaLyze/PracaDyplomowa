package com.company.algorytmy;

import com.company.rdzen.AbstractSortAlgorithm;
import com.company.rdzen.SortAlgorithm;
import com.company.rdzen.SortArray;

public final class QuickSortDoubleEnded extends AbstractSortAlgorithm {


    public static final SortAlgorithm INSTANCE = new QuickSortDoubleEnded();

    @Override
    public void sort(SortArray array) {
        sort(array, 0, array.length());
    }

    private static void sort(SortArray array, int start, int end) {
        if(start == end)
            return;

        array.setRange(start, end, SortArray.ElementState.ACTIVE);
        int left = start;
        int right = end - 1;
        int pivot = left;

        while (left != right) {
            while (array.compare(right, pivot) >= 0 && left != right) {
                array.setElement(right, SortArray.ElementState.INACTIVE);
                right--;
            }
            if (left != right) {
                array.swap(left, right);
                pivot = right;
                array.setElement(left, SortArray.ElementState.INACTIVE);
                left++;
            }
            while (array.compare(left, pivot) <= 0 && left != pivot) {
                array.setElement(left, SortArray.ElementState.INACTIVE);
                left++;
            }
            if(left != right) {
                array.swap(right, left);
                pivot = left;
                array.setElement(right, SortArray.ElementState.INACTIVE);
                right--;
            }
        }

        array.setElement(pivot, SortArray.ElementState.DONE);
        sort(array, start, pivot);
        sort(array, pivot + 1, end);
    }


    protected QuickSortDoubleEnded() {
        super("Szybkie sortowanie (dwustronne)");
    }

}
