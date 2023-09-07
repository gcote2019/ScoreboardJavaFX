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
    private ControleurEquipe[] controleurs = new ControleurEquipe[NOMS.length];
    private static final Random generateur = new Random();

    public final static String SCORE = "Score : ";
    private final static int TAILLE_POLICE_NOMS = 32;
    private final static int TAILLE_POLICE_SCORES = 24;

    private void ajouterEquipe(HBox parent, int pos) {
        Integer position = pos;
        VBox vBox = new VBox();
        Text titre = creerText(NOMS[position], TAILLE_POLICE_NOMS, pos);
        titre.setOnMouseClicked((mouseEvent) -> {
            controleurs[position].changerNom(mouseEvent.getClickCount() == 2 ? NOMS[position] : obtenirNom());
        });

        vBox.getChildren().add(titre);
        Text compte = creerText(SCORE + 0, TAILLE_POLICE_SCORES, pos);
        vBox.getChildren().add(compte);
        ControleurEquipe controleurEquipe = ControleurEquipe.creer(NOMS[pos], titre, compte, SCORE);
        controleurs[pos] = controleurEquipe;
        Button bouton = new Button("Compter un point");
        vBox.getChildren().add(bouton);
        bouton.setOnAction((event) -> {
            augmenterScore(position);
        });
        parent.getChildren().add(vBox);

    }

    private void augmenterScore(int pos) {
        controleurs[pos].ajouter();
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

    private Text creerText(String texte, int taillePolice, int pos) {
        Integer position = pos;
        Font font = Font.font(taillePolice);
        Text text = new Text(texte);
        text.setFont(font);
        return text;
    }
    private void changerLesCouleurs() {
        for (int i = 0; i < NOMS.length; i++) {
            controleurs[i].changerCouleurTitre(Color.BLACK);
        }

        boolean unique = true;
        int posValue = -1;
        for (int i = 0; i < NOMS.length; i++) {
            if (posValue == -1) { // jamais passé par ici
                posValue = i;
                unique = true;
            } else if (controleurs[i].getCompteur() > controleurs[posValue].getCompteur()) {
                posValue = i;
                unique = true;
            } else if (controleurs[i].getCompteur() == controleurs[posValue].getCompteur()) { // pas unique
                unique = false;
            }
        }

        if (unique) {
            controleurs[posValue].changerCouleurTitre(Color.GREEN);
        }
    }

    private static String obtenirNom() {
        return NOMS[generateur.nextInt(NOMS.length)];
    }

}