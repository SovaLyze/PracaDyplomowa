package com.company.wizualny;

import com.company.rdzen.AbstractSortArray;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Objects;

public class VisualSortArray extends AbstractSortArray {

    private int[] state;

    private final int scale;
    public final BufferedCanvas canvas;
    private final Graphics graphics;

    private volatile int comparisonCount;
    private volatile int swapCount;

    private final double targetFrameRate = 60;
    private final double stepsPerFrame;
    private double remainingStepsAllowed;
    private long nextRepaintTime;
    private final boolean drawIncrementally;

    public VisualSortArray(int size, int scale, double speed) {
        super(size);
        if(scale <= 0 || speed <= 0 || Double.isInfinite(speed) || Double.isNaN(speed))
            throw new IllegalArgumentException();
        shuffle();
        state = new int[size];

        comparisonCount = 0;
        swapCount = 0;
        stepsPerFrame = speed / targetFrameRate;
        remainingStepsAllowed = 0;
        nextRepaintTime = System.nanoTime();
        drawIncrementally = stepsPerFrame < size;

        this.scale = scale;
        canvas = new BufferedCanvas(size * scale);
        graphics = canvas.getBufferGraphics();
        redraw(0, values.length);
    }

    public int compare(int i, int j) {
        if (Thread.interrupted())
            throw new StopException();

        setElement(i, ElementState.COMPARING);
        setElement(j, ElementState.COMPARING);
        beforeStep();
        comparisonCount++;

        setElement(i, ElementState.ACTIVE);
        setElement(j, ElementState.ACTIVE);

        return super.compare(i, j);
    }

    public void swap(int i, int j) {
        if (Thread.interrupted())
            throw new StopException();
        super.swap(i, j);
        if (state != null) {  // If outside the constructor
            beforeStep();
            swapCount++;
            setElement(i, ElementState.ACTIVE);
            setElement(j, ElementState.ACTIVE);
        }
    }

    public void setElement(int index, ElementState state) {
        Objects.requireNonNull(state);
        this.state[index] = state.ordinal();
        if (drawIncrementally)
            redraw(index, index + 1);
    }


    public void setRange(int start, int end, ElementState state) {
        Objects.requireNonNull(state);
        Arrays.fill(this.state, start, end, state.ordinal());
        if (drawIncrementally)
            redraw(start, end);
    }


    public int getComparisonCount() {
        return comparisonCount;
    }

    public int getSwapCount() {
        return swapCount;
    }

    public void assertSorted() {
        for (int i = 1; i < values.length; i++) {
            if (values[i - 1] > values[i])
                throw new AssertionError();
        }
        redraw(0, values.length);
        canvas.repaint();
    }

    private void redraw(int start, int end) {
        graphics.setColor(BACKGROUND_COLOR);
        graphics.fillRect(0, start * scale, values.length * scale, (end - start) * scale);
        for (int i = start; i < end; i++) {
            graphics.setColor(STATE_COLORS[state[i]]);
            if (scale == 1)
                graphics.drawLine(0, i, values[i], i);
            else  // scale > 1
                graphics.fillRect(0, i * scale, (values[i] + 1) * scale, scale);
        }
    }

    private void beforeStep() {
        boolean first = true;
        while (remainingStepsAllowed < 1) {
            long currentTime;
            while (true) {
                currentTime = System.nanoTime();
                if (currentTime >= nextRepaintTime)
                    break;
                long delay = nextRepaintTime - currentTime;
                try {
                    Thread.sleep(delay / 1000000, (int)(delay % 1000000));
                } catch (InterruptedException e) {
                    throw new StopException();
                }
            }
            if (first) {
                if (!drawIncrementally)
                    redraw(0, values.length);
                canvas.synchronizer = canvas.synchronizer;
                canvas.repaint();
                first = false;
            }
            nextRepaintTime += Math.round(1e9 / targetFrameRate);
            if (nextRepaintTime <= currentTime)
                nextRepaintTime = currentTime + Math.round(1e9 / targetFrameRate);
            remainingStepsAllowed += stepsPerFrame;
        }
        remainingStepsAllowed--;
    }

    private static Color[] STATE_COLORS = {
            new Color(0x294099),  // Active: Blue
            new Color(0x959EBF),  // Inactive: Gray
            new Color(0xD4BA0D),  // Comparing: Yellow
            new Color(0x25963D),  // Done: Green
    };

    private static Color BACKGROUND_COLOR = new Color(0xFFFFFF);
}
