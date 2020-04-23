package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Drawing {
    public static void draw(boolean[][] currentTimeStep, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        double squareSize = 10;
        int currentRow = 0;
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (boolean[] singleRaw : currentTimeStep) {
            int currentColumn = 0;
            for (boolean singleCell : singleRaw) {
                if (singleCell) {
                    double x = squareSize * currentColumn + 0.2;
                    double y = squareSize * currentRow + 0.2;
                    gc.fillRect(x, y, squareSize, squareSize);
                }
                currentColumn++;
            }
            currentRow++;
        }
    }
}
