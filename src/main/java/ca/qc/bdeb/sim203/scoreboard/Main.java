package ca.qc.bdeb.sim203.scoreboard;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Random;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static final String[] NOMS = {"Canadiens", "Lightnings", "Islanders", "Bruins", "Penguins"};
    private static final Random generateur = new Random();

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
        text.setOnMouseClicked((mouseEvent) -> {
            text.setText(obtenirNom());
        });
        return text;
    }
    private void augmenterScoreCanadiens() {
        scoreCanadiens++;
        titreScoreCanadiens.setText(SCORE + scoreCanadiens);
        changerLesCouleurs();
    }

    private void augmenterScoreLightnings() {
        scoreLightnings++;
        titreScoreLightnings.setText(SCORE + scoreLightnings);
        changerLesCouleurs();
    }

    private void changerLesCouleurs() {
        Color canadiens = Color.BLACK;
        Color lightnings = Color.BLACK;
        if (scoreCanadiens > scoreLightnings)
            canadiens = Color.GREEN;
        else if (scoreCanadiens < scoreLightnings)
            lightnings = Color.GREEN;
        nomCanadiens.setFill(canadiens);
        nomLightnings.setFill(lightnings);
    }

    private static String obtenirNom() {
        return NOMS[generateur.nextInt(NOMS.length)];
    }

}