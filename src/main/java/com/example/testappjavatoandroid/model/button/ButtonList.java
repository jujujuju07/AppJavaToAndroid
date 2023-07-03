
package com.example.testappjavatoandroid.model.button;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ButtonList {

    @SerializedName("buttonDonner")
    private String mButtonDonner;
    @SerializedName("buttonType")
    private String mButtonType;

    public String getButtonDonner() {
        return mButtonDonner;
    }

    public void setButtonDonner(String buttonDonner) {
        mButtonDonner = buttonDonner;
    }

    public String getButtonType() {
        return mButtonType;
    }

    public void setButtonType(String buttonType) {
        mButtonType = buttonType;
    }

}
