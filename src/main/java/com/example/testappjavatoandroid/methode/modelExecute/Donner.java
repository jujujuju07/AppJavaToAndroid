
package com.example.testappjavatoandroid.methode.modelExecute;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Donner {

    @SerializedName("execute")
    private String mExecute;

    public String getExecute() {
        return mExecute;
    }

    public void setExecute(String execute) {
        mExecute = execute;
    }

}
