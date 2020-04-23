package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;

public class Animation extends AnimationTimer {
    private Canvas canvas;
    private boolean[][] array;
    private boolean ifPBCs;
    private boolean ifInfinity;

    private long lastUpdate = 0;
    private int index = 0;

    @Override
    public void handle(long now) {
        if (now - lastUpdate >= 300_000_000) {
            Drawing.draw(array, canvas);
            array = Calculations.calculateNextTimeStep(array, ifPBCs);
            lastUpdate = now;
            if(!this.ifInfinity){
                index++;
                if (index > 10) this.stop();
            }
        }
    }

    public Animation(Canvas canvas, boolean[][] array, boolean ifPBCs, boolean ifInfinity) {
        this.canvas = canvas;
        this.array = array;
        this.ifPBCs = ifPBCs;
        this.ifInfinity = ifInfinity;
    }
}
