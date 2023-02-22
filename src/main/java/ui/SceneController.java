package ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import utils.ThreadUtil;
import web.WebSearcher;


import java.net.URL;
import java.util.ResourceBundle;

public class SceneController implements Initializable {

    @FXML
    private Label lbl_fan_height, lbl_case_height, lbl_stat;

    @FXML
    private Button btt_fan, btt_case;

    @FXML
    private TextField txtf_fan, txtf_case;

    @FXML
    private ProgressBar prog_main;

    @FXML
    private Pane pane_go, pane_rgb, pane_rgb_clone;


    private ThreadUtil threadUtil;

    public void setFanHeight(Integer h){
        Platform.runLater(() -> lbl_fan_height.setText("Height: "+h+"mm"));
    }

    public void setCaseHeight(Integer h){
        Platform.runLater(() ->lbl_case_height.setText("Height: "+h+"mm"));
    }

    public void setStat(String s){
        Platform.runLater(() ->lbl_stat.setText("진행: "+s));
    }

    public void setProg(double ds){
        Platform.runLater(() ->prog_main.setProgress(ds));
    }

    public void setRGB(int ind){
        if(ind == 10){
            Platform.runLater(() ->{
                pane_rgb.setBackground(new Background(new BackgroundFill(Color.web("#ffff"), CornerRadii.EMPTY, Insets.EMPTY)));
                pane_rgb_clone.setBackground(new Background(new BackgroundFill(Color.web("#ffff"), CornerRadii.EMPTY, Insets.EMPTY)));
            });
            return;
        }
        if(ind > 6){
            Platform.runLater(() ->{
                pane_rgb.setBackground(new Background(new BackgroundFill(Color.web("#14d952"), CornerRadii.EMPTY, Insets.EMPTY)));
                pane_rgb_clone.setBackground(new Background(new BackgroundFill(Color.web("#14d952"), CornerRadii.EMPTY, Insets.EMPTY)));
            });
        }else if(ind > 3){
            pane_rgb.setBackground(new Background(new BackgroundFill(Color.web("#ebde52"), CornerRadii.EMPTY, Insets.EMPTY)));
            pane_rgb_clone.setBackground(new Background(new BackgroundFill(Color.web("#ebde52"), CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            pane_rgb.setBackground(new Background(new BackgroundFill(Color.web("#eb3434"), CornerRadii.EMPTY, Insets.EMPTY)));
            pane_rgb_clone.setBackground(new Background(new BackgroundFill(Color.web("#eb3434"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        threadUtil = new ThreadUtil();
        threadUtil.init();
        String[] list = new String[0];
        new WebSearcher(list).registerController(this);
        btt_fan.setOnMouseClicked(e -> txtf_fan.setText(""));
        btt_case.setOnMouseClicked(e -> txtf_case.setText(""));
        pane_go.setOnMouseClicked(e -> {
            String[] argList = new String[6];
            argList[0] = txtf_fan.getText();
            argList[1] = "높이";
            argList[2] = "span";
            argList[3] = txtf_case.getText();
            argList[4] = "CPU쿨러";
            argList[5] = "a";
            threadUtil.execute(argList);
        });
    }
}
