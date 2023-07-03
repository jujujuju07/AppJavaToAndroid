
package com.example.testappjavatoandroid.model.button;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@SuppressWarnings("unused")
public class ListButton {

    @SerializedName("Button_Button")
    private List<List<ButtonList>> mButtonButton;

    public List<List<ButtonList>> getButtonButton() {
        return mButtonButton;
    }

    public void setButtonButton(List<List<ButtonList>> buttonButton) {
        mButtonButton = buttonButton;
    }

}
