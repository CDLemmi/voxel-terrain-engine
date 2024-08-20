package de.cdlemmi.vte.util;

public class DeltaTimer {

    private int frames;
    private long lastTime;

    private long lastDebugTime;
    private long debugInterval = 1000000000;

    DebugPrinting[] debugPrintings;

    public DeltaTimer() {
        this(new DebugPrinting[0]);
    }

    public DeltaTimer(DebugPrinting... debugPrintings) {
        this.debugPrintings = debugPrintings;
        lastTime = System.nanoTime();
    }

    private void debugPrint() {
        System.out.println("----------Debug Information:");
        System.out.println("Average FPS: " + frames);
        for(DebugPrinting debugPrinting : debugPrintings) {
            debugPrinting.printDebug();
        }
    }

    public double getDeltaTime() {
        frames++;
        long currentTime = System.nanoTime();
        long interval = currentTime - lastTime;
        lastTime = currentTime;
        if(currentTime - debugInterval > lastDebugTime) {
            debugPrint();
            frames = 0;
            lastDebugTime = currentTime - currentTime%debugInterval + debugInterval;
        }
        return (double)interval / 1000000000.0;
    }
}
