package ca.qc.bdeb.sim203.scoreboard;

import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ControleurEquipe {
    private Equipe equipe;
    private Text titre;

    private Text compte;
    private String prefixe;

    private ControleurEquipe(String nom, Text titre, Text compte, String prefixe) {
        this.equipe = new Equipe(nom);
        this.titre = titre;
        this.compte = compte;
        this.prefixe = prefixe;
    }

    public static ControleurEquipe creer(String nom, Text titre, Text compte, String prefixe) {
        return new ControleurEquipe(nom, titre, compte, prefixe);
    }

    public void ajouter() {
        equipe.ajouter();
        compte.setText(prefixe + equipe.getCompteur());
    }

    public void changerCouleurTitre(Color couleur) {
        titre.setFill(couleur);
    }

    public int getCompteur() {
        return equipe.getCompteur();
    }

    public void changerNom(String nouveauNom) {
        equipe.changerNom(nouveauNom);
        titre.setText(nouveauNom);
    }
}
