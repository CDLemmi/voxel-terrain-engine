package de.cdlemmi.vte.util;

public class DeltaTimer {

    private int frames;
    private long lastTime;

    private long lastDebugTime;
    private long debugIntervalNS;
    private double debugIntervalS;

    DebugPrinting[] debugPrintings;

    public DeltaTimer(double interval) {
        this(interval, new DebugPrinting[0]);
    }

    public DeltaTimer(double interval, DebugPrinting... debugPrintings) {
        debugIntervalS = interval;
        debugIntervalNS = (long)(interval * 1000000000.0);
        this.debugPrintings = debugPrintings;
        lastTime = System.nanoTime();
    }

    private void debugPrint() {
        System.out.println("----------Debug Information (System Time: " + System.currentTimeMillis() + "):");
        int fps = (int)(frames/debugIntervalS);
        System.out.println("Average FPS: " + fps);
        for(DebugPrinting debugPrinting : debugPrintings) {
            debugPrinting.printDebug();
        }
    }

    public double getDeltaTime() {
        frames++;
        long currentTime = System.nanoTime();
        long interval = currentTime - lastTime;
        lastTime = currentTime;
        if(currentTime - debugIntervalNS > lastDebugTime) {
            //debugPrint();
            frames = 0;
            lastDebugTime = currentTime - currentTime%debugIntervalNS;
        }
        return (double)interval / 1000000000.0;
    }
}
