package dto;

import com.google.gson.annotations.SerializedName;

public class TranslatedText {
    @SerializedName("translatedText")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
