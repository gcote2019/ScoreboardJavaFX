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

public class MainMulti extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static final String[] NOMS = {"Canadiens", "Lightnings", "Islanders", "Bruins", "Penguins"};
    private static final Random generateur = new Random();

    private final static String SCORE = "Score : ";
    private final static int TAILLE_POLICE_NOMS = 32;
    private final static int TAILLE_POLICE_SCORES = 24;
    private int[] scores = new int[NOMS.length];
    private Text[] noms = new Text[NOMS.length];
    private Text[] titres = new Text[NOMS.length];
    private Button[] boutons = new Button[NOMS.length];

    private void ajouterEquipe(HBox parent, int pos) {
        Integer position = pos;
        VBox vBox = new VBox();
        noms[pos] = creerText(NOMS[pos], TAILLE_POLICE_NOMS);
        vBox.getChildren().add(noms[pos]);
        titres[pos] = creerText(SCORE + 0, TAILLE_POLICE_SCORES);
        vBox.getChildren().add(titres[pos]);
        boutons[pos] = new Button("Compter un point");
        vBox.getChildren().add(boutons[pos]);
        boutons[pos].setOnAction((event) -> {
            augmenterScore(position);
        });
        parent.getChildren().add(vBox);

    }

    private void augmenterScore(int pos) {
        scores[pos]++;
        titres[pos].setText(SCORE + scores[pos]);
        changerLesCouleurs();
    }

    @Override
    public void start(Stage stage) {
        HBox root = new HBox();
        Scene scene = new Scene(root, 900, 150);
        VBox vBox = new VBox();
        root.getChildren().add(vBox);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        vBox.setAlignment(Pos.CENTER_RIGHT);

        for (int i = 0; i < NOMS.length; i++) {
            ajouterEquipe(root, i);
            if (i < NOMS.length - 1) {
                root.getChildren().add(new Separator(Orientation.VERTICAL));
            }

        }
        stage.setTitle("Scoreboard");
        stage.setScene(scene);
        stage.show();

    }

    private Text creerText(String texte, int taillePolice) {
        Font font = Font.font(taillePolice);
        Text text = new Text(texte);
        text.setFont(font);
        text.setOnMouseClicked((mouseEvent) -> {
            if (mouseEvent.getClickCount() == 2) {
                text.setText(texte);
            } else {
                text.setText(obtenirNom());
            }
        });
        return text;
    }
    private void changerLesCouleurs() {
        for (int i = 0; i < NOMS.length; i++) {
            noms[i].setFill(Color.BLACK);
        }

        boolean unique = true;
        int posValue = -1;
        for (int i = 0; i < NOMS.length; i++) {
            if (posValue == -1) { // jamais passé par ici
                posValue = i;
                unique = true;
            } else if (scores[i] > scores[posValue]) {
                posValue = i;
                unique = true;
            } else if (scores[i] == scores[posValue]) { // pas unique
                unique = false;
            }
        }

        if (unique) {
            noms[posValue].setFill(Color.GREEN);
        }
    }

    private static String obtenirNom() {
        return NOMS[generateur.nextInt(NOMS.length)];
    }

}