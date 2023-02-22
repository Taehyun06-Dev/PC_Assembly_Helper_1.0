package ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SceneLauncher extends Application {

    double x, y = 0;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/ui$main.fxml"));
        //stage.getIcons().add(new Image(getClass().getResourceAsStream("/coronainfo/resources/info_64px.png")));
        stage.setTitle("T-Helper 1.0");
        stage.initStyle(StageStyle.DECORATED);
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });
        stage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
        stage.setScene(new Scene(root));
        stage.show();
    }

    //Main
    public static void main(String[] args) {
        launch(args);
    }
}

