package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setOnCloseRequest((e) -> Controller.closeProgram());
        primaryStage.setTitle("Modelowanie wieloskalowe lab2 - Gra w zycie");
        primaryStage.setHeight(400);
        primaryStage.setWidth(700);
        primaryStage.setMinHeight(300);
        primaryStage.setMinWidth(700);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
