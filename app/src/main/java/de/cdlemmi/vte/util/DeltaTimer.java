package de.cdlemmi.vte.util;

public class DeltaTimer {

    private int frames;
    private long lastTime;

    private long lastDebugTime;
    private long debugInterval = 1000000000;

    public DeltaTimer() {
        lastTime = System.nanoTime();
    }

    public double getDeltaTime() {
        frames++;
        long currentTime = System.nanoTime();
        long interval = currentTime - lastTime;
        lastTime = currentTime;
        if(currentTime - debugInterval > lastDebugTime) {
            System.out.println("Average FPS: " + frames);
            frames = 0;
            lastDebugTime = currentTime - currentTime%debugInterval + debugInterval;
        }
        return (double)interval / 1000000000.0;
    }
}
