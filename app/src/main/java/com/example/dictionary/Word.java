package com.example.dictionary;

public class Word {
    private String eng_word;
    private String myanmar_word;

    public String getEng_word() {
        return eng_word;
    }

    public void setEng_word(String eng_word) {
        this.eng_word = eng_word;
    }

    public String getMyanmar_word() {
        return myanmar_word;
    }

    public void setMyanmar_word(String myanmar_word) {
        this.myanmar_word = myanmar_word;
    }

    public Word(String eng_word, String myanmar_word) {
        this.eng_word = eng_word;
        this.myanmar_word = myanmar_word;

    }
    public Word() {

    }
}
