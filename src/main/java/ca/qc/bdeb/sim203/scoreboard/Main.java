package ca.qc.bdeb.sim203.scoreboard;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    private final static String SCORE = "Score : ";
    private final static int TAILLE_POLICE_NOMS = 32;
    private final static int TAILLE_POLICE_SCORES = 24;
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
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        vBox.setAlignment(Pos.CENTER_RIGHT);

        nomCanadiens = creerText("Canadiens", TAILLE_POLICE_NOMS);
        vBox.getChildren().add(nomCanadiens);
        titreScoreCanadiens = creerText(SCORE + 0, TAILLE_POLICE_SCORES);
        vBox.getChildren().add(titreScoreCanadiens);
        boutonCanadiens = new Button("Compter un point");
        vBox.getChildren().add(boutonCanadiens);
        boutonCanadiens.setOnAction((event) -> {
            augmenterScoreCanadiens();
        });

        root.getChildren().add(new Separator(Orientation.VERTICAL));
        vBox = new VBox();
        root.getChildren().add(vBox);
        vBox.setAlignment(Pos.CENTER_LEFT);
        nomLightnings = creerText("Lightnings", TAILLE_POLICE_NOMS);
        vBox.getChildren().add(nomLightnings);
        titreScoreLightnings = creerText(SCORE + 0, TAILLE_POLICE_SCORES);
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

    private Text creerText(String texte, int taillePolice) {
        Font font = Font.font(taillePolice);
        Text text = new Text(texte);
        text.setFont(font);
        return text;
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