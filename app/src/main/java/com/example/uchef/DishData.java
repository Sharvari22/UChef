package com.example.uchef;

public class DishData {

    private  String dishTitle;
    private  String dishDescription;
    private String dishPrice;
    private String dishImage;
    private String check1;
    private String check2;
    private  String check3;
    private String check4;
    private String check5;
    private String check6;
    private String spinner;
    private String key;

    public DishData() {

    }

    public DishData(String dishTitle, String dishDescription, String dishPrice, String dishImage) {
        this.dishTitle = dishTitle;
        this.dishDescription = dishDescription;
        this.dishPrice = dishPrice;
        this.dishImage = dishImage;
    }

    public String getDishTitle() {
        return dishTitle;
    }

    public String getDishDescription() {
        return dishDescription;
    }

    public String getDishPrice() {
        return dishPrice;
    }

    public String getDishImage() {
        return dishImage;
    }

    public String getCheck1() {
        return check1;
    }

    public void setCheck1(String Check1) {
        check1 = Check1;
    }

    public String getCheck2() {
        return check2;
    }

    public void setCheck2(String Check2) {
        check2 = Check2;
    }

    public String getCheck3() {
        return check3;
    }

    public void setCheck3(String Check3) {
        check3 = Check3;
    }

    public String getCheck4() {
        return check4;
    }

    public void setCheck4(String Check4) {
        check4 = Check4;
    }

    public String getCheck5() {
        return check5;
    }

    public void setCheck5(String Check5) {
        check5 = Check5;
    }

    public String getCheck6() {
        return check6;
    }

    public void setCheck6(String Check6) {
        check6 = Check6;
    }

    public String getSpinner() {
        return spinner;
    }

    public void setSpinner(String spinner) {
        this.spinner = spinner;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
