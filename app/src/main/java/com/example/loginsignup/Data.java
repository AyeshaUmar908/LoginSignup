package com.example.loginsignup;

public class Data {

    String title;
    String description;
    String budget;
    String category;
    String locality;
    String id;
    String date;
    String workType;
    String userName;

    public Data(){

    }
    public Data(String title, String description, String budget, String category, String locality, String userName){

        this.title = title;
        this.description = description;
        this.budget = budget;
        this.category = category;
        this.locality = locality;
        this.id = id;
        this.userName =userName;
        this.date = date;
        this.workType = workType;

    }


    public String getUserName(){return  userName;}
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getWorkType(){
        return  workType;
    }
    public void setWorkType(String workType){
        this.workType = workType;
    }
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
