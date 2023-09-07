package ca.qc.bdeb.sim203.scoreboard;

public class Equipe {
    private String nom;
    private int compteur;

    public Equipe(String nom) {
        this.nom = nom;
        compteur = 0;
    }

    public void ajouter() {
        compteur++;
    }

    public void changerNom(String nouveauNom) {
        this.nom = nouveauNom;
    }

    public int getCompteur() {
        return compteur;
    }
}
