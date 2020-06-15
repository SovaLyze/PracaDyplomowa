package com.company.wizualny;

import com.company.algorytmy.BubbleSort;
import com.company.algorytmy.QuickSortDoubleEnded;
import com.company.algorytmy.QuickSortSliding;
import com.company.algorytmy.RotationMergeSort;
import com.company.rdzen.SortAlgorithm;

import java.util.Arrays;

public final class SortMain {
    public static void main(String[] args) {
        SortAlgorithm[] algos = {
                QuickSortDoubleEnded.INSTANCE,
                QuickSortSliding.INSTANCE,
                BubbleSort.INSTANCE,
                RotationMergeSort.INSTANCE
        };
        new LaunchFrame(Arrays.asList(algos));
    }
}
