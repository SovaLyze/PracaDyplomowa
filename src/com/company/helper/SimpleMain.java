package com.company.helper;

import com.company.algorytmy.BubbleSort;
import com.company.algorytmy.QuickSortDoubleEnded;
import com.company.algorytmy.QuickSortSliding;
import com.company.algorytmy.RotationMergeSort;
import com.company.rdzen.SortAlgorithm;

public final class SimpleMain {
    private static final SortAlgorithm[] ALGORITHMS = {
            QuickSortDoubleEnded.INSTANCE,
            QuickSortSliding.INSTANCE,
            BubbleSort.INSTANCE,
            RotationMergeSort.INSTANCE
    };

    private static final int DEFAULT_SIZE = 300;

    private static void main(String[] args) {

        int size;
        if(args.length == 0)
            size = DEFAULT_SIZE;
        else if (args.length == 1)
            size = Integer.parseInt(args[0]);
        else {
            System.err.println("Stosowanie: java com.company.algorytmy.helper.SimpleMain [ArraySize]");
            System.exit(1);
            return;
        }
        if (size <= 0)
            throw new IllegalArgumentException("Rozmiar tablicy musi być dodatni");

        System.err.println("Nazwa algorytmu\tLiczba porównań\tLiczba zamian");
        SimpleSortArray referenceArray = new SimpleSortArray(size);
        for (SortAlgorithm algo : ALGORITHMS) {
            SimpleSortArray array = referenceArray.clone();
            algo.sort(array);
            System.err.printf("%s\t%d\t%d%n", algo.getName(), array.comparisonCount, array.swapCount);
        }
    }
}
