package de.cdlemmi.vte.input;

public record PlayerInputAction(boolean forward, boolean back, boolean left, boolean right, boolean up, boolean down, double turnX, double turnY) {
}
