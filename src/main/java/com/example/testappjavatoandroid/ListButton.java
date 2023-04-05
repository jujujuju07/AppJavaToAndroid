
package com.example.testappjavatoandroid;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ListButton {

    @SerializedName("buttonDonner")
    private String mButtonDonner;
    @SerializedName("buttonType")
    private String mButtonType;
    @SerializedName("listButton")
    private List<ListButton> mListButton;

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

    public List<ListButton> getListButton() {
        return mListButton;
    }

    public void setListButton(List<ListButton> listButton) {
        mListButton = listButton;
    }

}
