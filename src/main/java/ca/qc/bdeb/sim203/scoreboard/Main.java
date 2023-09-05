package ca.qc.bdeb.sim203.scoreboard;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final static String SCORE = "Score : ";
    private int scoreCanadiens = 0;
    private int scoreLightnings = 0;
    private Text nomCanadiens;
    private Text nomLightnings;
    private Text titreScoreCanadiens;
    private Text titreScoreLightnings;
    private Button boutonCanadiens;
    private Button boutonLightnings;
    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 400, 150);
        VBox vBox = new VBox();
        root.getChildren().add(vBox);

        nomCanadiens = new Text("Canadiens");
        vBox.getChildren().add(nomCanadiens);
        titreScoreCanadiens = new Text(SCORE + 0);
        vBox.getChildren().add(titreScoreCanadiens);
        boutonCanadiens = new Button("Compter un point");
        vBox.getChildren().add(boutonCanadiens);
        boutonCanadiens.setOnAction((event) -> {
            augmenterScoreCanadiens();
        });

        vBox = new VBox();
        root.getChildren().add(vBox);
        nomLightnings = new Text("Lightnings");
        vBox.getChildren().add(nomLightnings);
        titreScoreLightnings = new Text(SCORE + 0);
        vBox.getChildren().add(titreScoreLightnings);
        boutonLightnings = new Button("Compter un point");
        vBox.getChildren().add(boutonLightnings);
        boutonLightnings.setOnAction((event) -> {
            augmenterScoreLightnings();
        });
        stage.setTitle("Scoreboard");
        stage.setScene(scene);
        stage.show();

    }

    private void augmenterScoreCanadiens() {
        scoreCanadiens++;
        titreScoreCanadiens.setText(SCORE + scoreCanadiens);

    }

    private void augmenterScoreLightnings() {
        scoreLightnings++;
        titreScoreLightnings.setText(SCORE + scoreLightnings);
    }

}