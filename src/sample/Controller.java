package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import java.util.Vector;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class Controller {

    private Field currentField;
 //   private Solver solver;
 //   private Vector<Board> solution;
 //   private int currentStatement;

    @FXML public Canvas mainCan;
    @FXML public TextField dimension;
    @FXML public TextArea matrix;

    public void doIT() {
        GraphicsContext gc = mainCan.getGraphicsContext2D();
        gc.clearRect(0, 0, mainCan.getWidth(), mainCan.getHeight());
        String temp = dimension.getText();
        if (temp.length() != 0) {
            int n = Integer.parseInt(temp);
            if (2 <= n && n <= 10) {
                currentField = new Field(n);
                int pxlSize = 300;
                currentField.drawField(pxlSize, mainCan, ((int) mainCan.getWidth() - pxlSize) / 2, ((int) mainCan.getHeight() - pxlSize) / 2, gc);
            }
        }
 //       solver = new Solver(currentField);
//        solution = new Vector<>(0);
//        for(Board board : solver.solution()){
//            solution.add(board);
//        }
//        currentStatement = 0;
    }

    public void makeFieldFromMatrix() {
        GraphicsContext gc = mainCan.getGraphicsContext2D();
        gc.clearRect(0, 0, mainCan.getWidth(), mainCan.getHeight());
        String temp = matrix.getText();
        if (temp.length() != 0) {
            String strArr[] = temp.split("\\p{P}?[ \\t\\n\\r]+");
            int size = (int) Math.sqrt(strArr.length);
            int matrix[][] = new int[size][size];
            int k = 0;
            for(int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    matrix[i][j] = Integer.parseInt(strArr[k]);
                    k++;
                }
            }
            currentField = new Field(matrix);
            int pxlSize = 300;
            currentField.drawField(pxlSize, mainCan, ((int) mainCan.getWidth() - pxlSize) / 2, ((int) mainCan.getHeight() - pxlSize) / 2, gc);
        }
    }

//    public void nextNode() {
//        currentStatement += 1;
//        if (currentStatement < solution.size()) {
//            GraphicsContext gc = mainCanvas.getGraphicsContext2D();
//            gc.clearRect(0, 0, mainCanvas.getWidth(), mainCanvas.getHeight());
//            currentField.board = solution.get(currentStatement).board;
//            int pxlSize = 300;
//            currentField.drawField(pxlSize, mainCanvas, ((int) mainCanvas.getWidth() - pxlSize) / 2, ((int) mainCanvas.getHeight() - pxlSize) / 2, gc);
//        }
//    }
//
//    public void prevNode() {
//
//
//    }
}