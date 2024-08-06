package org.example.marketeasy;

public class ProductData {

    private String prodName;
    private String prodDate;
    private String prodSize;

    private String prodFreq;
    private String prodSeuil;
    private String prodPrice;
    private int prodSomme;

    public Integer tmpId;

    public Integer getTmpId() {
        return tmpId;
    }

    public void setTmpId(Integer tmpId) {
        this.tmpId = tmpId;
    }

    public ProductData (String prodName, String prodPrice,
                        String prodFreq, String prodSize) {

        this.prodName = prodName;
        this.prodSize = prodSize;
        this.prodFreq = prodFreq;
        this.prodPrice = prodPrice;

    }

    public int getProdSomme() {
        return prodSomme;
    }

    public void setProdSomme(int prodSomme) {
        this.prodSomme = prodSomme;
    }

//    public ProductData (String prodName, String prodDate, String prodSize,
//                        String prodFreq, String prodSeuil, String prodPrice) {
//
//        this.prodName = prodName;
//        this.prodDate = prodDate;
//        this.prodSize = prodSize;
//        this.prodFreq = prodFreq;
//        this.prodSeuil = prodSeuil;
//        this.prodPrice = prodPrice;
//
//    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDate() {
        return prodDate;
    }

    public void setProdDate(String prodDate) {
        this.prodDate = prodDate;
    }

    public String getProdSize() {
        return prodSize;
    }

    public void setProdSize(String prodSize) {
        this.prodSize = prodSize;
    }

    public String getProdFreq() {
        return prodFreq;
    }

    public void setProdFreq(String prodFreq) {
        this.prodFreq = prodFreq;
    }

    public String getProdSeuil() {
        return prodSeuil;
    }

    public void setProdSeuil(String prodSeuil) {
        this.prodSeuil = prodSeuil;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }
}
