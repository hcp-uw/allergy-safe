package com.allergysafe;

import java.util.ArrayList;
import java.util.Date;

public class Allergy {
    public static ArrayList<Allergy> allergyArrayList = new ArrayList<>();
    public static String ALLERGY_EDIT_EXTRA = "allergyEdit";

    private int id;
    private String allergy;
    private String description;
    private Date deleted;

    public Allergy(int id, String allergy, String description, Date deleted) {
        this.id = id;
        this.allergy = allergy;
        this.description = description;
        this.deleted = deleted;
    }

    public Allergy(int id, String allergy, String description) {
        this.id = id;
        this.allergy = allergy;
        this.description = description;
        deleted = null;
    }

    public static Allergy getAllergyForID(int passedAllergyID) {
        for (Allergy allergy : allergyArrayList){
            if (allergy.getId() == passedAllergyID)
                return allergy;
        }
        return null;
    }

    public static ArrayList<Allergy> nonDeletedAllergies () {
        ArrayList<Allergy> nonDeleted = new ArrayList<>();
        for (Allergy allergy : allergyArrayList) {
            if (allergy.getDeleted() == null)
                nonDeleted.add(allergy);
        }
        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
