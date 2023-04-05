
package com.example.testappjavatoandroid.button;

import java.util.List;
import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class ListButton {

    @SerializedName("Button_List")
    private List<ButtonList> mButtonList;

    public List<ButtonList> getButtonList() {
        return mButtonList;
    }

    public void setButtonList(List<ButtonList> buttonList) {
        mButtonList = buttonList;
    }

}
