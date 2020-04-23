package sample;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class Controller implements Initializable {
    @FXML
    Button pauseResumeButton;
    @FXML
    Button startButton;
    @FXML
    Canvas canvas;
    @FXML
    CheckBox checkBoxPeriodicBoundaryConditions;
    @FXML
    ComboBox<String> comboBoxNumberOfIterations;
    @FXML
    ComboBox<String> comboBoxInitialState;
    @FXML
    TextField textFieldX;
    @FXML
    TextField textFieldY;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxInitialState.setItems(FXCollections.observableArrayList("Beehive", "Glider", "Oscillator", "Random"));
        comboBoxNumberOfIterations.setItems(FXCollections.observableArrayList("Infinity", "10"));
        comboBoxInitialState.setValue("Random");
        comboBoxNumberOfIterations.setValue("10");
        textFieldX.setText("20");
        textFieldY.setText("20");
    }

    Animation animation;

    public void clickStartButton() {
        try {
            animation.stop();
        } catch (NullPointerException nullException) {
            System.out.println("First usage");
        }
        boolean ifPeriodicBoundaryConditions = checkBoxPeriodicBoundaryConditions.isSelected();
        String numberOfIterations = comboBoxNumberOfIterations.getValue();
        String initialState = comboBoxInitialState.getValue();
        int xSize = Integer.parseInt(textFieldX.getText());
        int ySize = Integer.parseInt(textFieldY.getText());
        boolean[][] currentGeneration = initializeArray(initialState, xSize, ySize);

        boolean ifInfinity;
        if (numberOfIterations.equals("Infinity")) {
            ifInfinity = true;
        } else {
            ifInfinity = false;
        }
        animation = new Animation(canvas, currentGeneration,
                ifPeriodicBoundaryConditions, ifInfinity);
        animation.start();
    }

    private int pauseResumeCounter = 0;

    public void clickPauseResumeButton() {
        if (pauseResumeCounter % 2 == 0) {
            try {
                animation.stop();
            } catch (NullPointerException nullException) {
                System.out.println("There's nothing to stop/resume");
            }
        } else {
            try {
                animation.start();
            } catch (NullPointerException nullException) {
                System.out.println("There's nothing to stop/resume");
            }
        }
        pauseResumeCounter++;
    }

    private boolean[][] initializeArray(String initialState, int xSize, int ySize) {
        boolean[][] initializedArray = new boolean[xSize][ySize];

        int xCenter, yCenter;
        if (xSize % 2 == 0) {
            xCenter = xSize / 2;
        } else {
            xCenter = (xSize - 1) / 2;
        }
        if (ySize % 2 == 0) {
            yCenter = ySize / 2;
        } else {
            yCenter = (ySize - 1) / 2;
        }
        switch (initialState) {
            case "Beehive": {
                initializedArray[xCenter][yCenter] = true;
                initializedArray[xCenter - 1][yCenter - 1] = true;
                initializedArray[xCenter - 2][yCenter - 1] = true;
                initializedArray[xCenter - 3][yCenter] = true;
                initializedArray[xCenter - 2][yCenter + 1] = true;
                initializedArray[xCenter - 1][yCenter + 1] = true;
                break;
            }
            case "Glider": {
                initializedArray[xCenter][yCenter] = true;
                initializedArray[xCenter - 1][yCenter] = true;
                initializedArray[xCenter][yCenter - 1] = true;
                initializedArray[xCenter + 1][yCenter - 1] = true;
                initializedArray[xCenter + 1][yCenter + 1] = true;
                break;
            }
            case "Oscillator": {
                initializedArray[xCenter][yCenter] = true;
                initializedArray[xCenter][yCenter + 1] = true;
                initializedArray[xCenter][yCenter - 1] = true;
                break;
            }
            case "Random": {
                Random generator = new Random();
                for (int i = 0; i < xSize; i++) {
                    for (int j = 0; j < ySize; j++) {
                        if (generator.nextInt() % 4 == 0) {
                            initializedArray[i][j] = true;
                        }
                    }
                }
                break;
            }
            default: {
                System.out.println("ERROR");
            }
        }
        return initializedArray;
    }

    public static void closeProgram() {
        Platform.exit();
        System.exit(0);
    }
}
