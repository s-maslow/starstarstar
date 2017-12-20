package sample;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Field extends Board {

    public Field(int n) {
        super(n);
    }

    public Field(int [][]m) {
        super(m);
    }

    public static void drawKnucle(int n, int x, int y, int size, Canvas canvas, GraphicsContext gc) {
        gc.strokeRoundRect(x + 5, y + 5, size - 10, size - 10, 0, 0);
        gc.fillText("" + n + "", x + (size / 2) - 5, y + (size / 2) + 5);
    }

    public void drawField(int pxlSize, Canvas canvas, int x, int y, GraphicsContext gc) {
        gc.strokeRoundRect(x, y, pxlSize, pxlSize, 0, 0);
        int size = pxlSize / this.dimension();
        int currX = x;
        int currY = y;
        for (int i = 0; i < this.dimension(); i++) {
            for (int j = 0; j < this.dimension(); j++) {
                if (this.board[i][j] != 0)
                    drawKnucle(this.board[i][j], currX, currY, size, canvas, gc);
                currX += size;
            }
            currX = x;
            currY += size;
        }

    }


}
