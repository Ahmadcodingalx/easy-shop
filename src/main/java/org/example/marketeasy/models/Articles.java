package org.example.marketeasy.models;

public class Articles {

    public String nomDuProduit;
    public String prix;
    public String date;
    public String seuilDAlerte;

    public String getNomDuProduit() {
        return nomDuProduit;
    }

    public void setNomDuProduit(String nomDuProduit) {
        this.nomDuProduit = nomDuProduit;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSeuilDAlerte() {
        return seuilDAlerte;
    }

    public void setSeuilDAlerte(String seuilDAlerte) {
        this.seuilDAlerte = seuilDAlerte;
    }
}
