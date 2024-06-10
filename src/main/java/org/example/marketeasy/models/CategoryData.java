package org.example.marketeasy.models;

public class CategoryData {

    private String catName;
    private String catDate;
    private String catTotProd;
    private String catRedProd;

    public CategoryData (String catName, String catDate, String catTotProd, String catRedProd) {

        this.catName = catName;
        this.catDate = catDate;
        this.catTotProd = catTotProd;
        this.catRedProd = catRedProd;

    }

    public String getCatName() {
        return catName;
    }

    public String getCatDate() {
        return catDate;
    }

    public String getCatTotProd() {
        return catTotProd;
    }

    public String getCatRedProd() {
        return catRedProd;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public void setCatDate(String catDate) {
        this.catDate = catDate;
    }

    public void setCatTotProd(String catTotProd) {
        this.catTotProd = catTotProd;
    }

    public void setCatRedProd(String catRedProd) {
        this.catRedProd = catRedProd;
    }
}
