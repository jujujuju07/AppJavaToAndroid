
package com.example.testappjavatoandroid.methode.modelExecute;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ResponseExecute {

    @SerializedName("Donner")
    private List<Donner> mDonner;

    public List<Donner> getDonner() {
        return mDonner;
    }

    public void setDonner(List<Donner> donner) {
        mDonner = donner;
    }

}
