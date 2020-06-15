package com.company.wizualny;

import java.awt.*;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
final class BufferedCanvas extends Canvas{

    public Object synchronizer;
    private BufferedImage buffer;
    private Graphics bufGfx;
    public volatile int synchronize;

    public BufferedCanvas(int size) {
        this(size, size);
    }

    public BufferedCanvas(int width, int height) {
        if(width <= 0 || height <= 0)
            throw new IllegalArgumentException();
        this.setSize(width, height);
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        bufGfx = buffer.getGraphics();
    }

    public Graphics getBufferGraphics() {
        return bufGfx;
    }

    public void update(Graphics g) {
        paint(g);
    }

    public void paint(Graphics g) {
        synchronize = synchronize;
        g.drawImage(buffer, 0, 0, this);
    }

}
