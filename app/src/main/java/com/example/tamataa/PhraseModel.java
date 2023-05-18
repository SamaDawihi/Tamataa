package com.example.tamataa;

public class PhraseModel {
    private int phraseId;
    private int brandId;
    private String phrase;

    public PhraseModel(int phraseId, int brandId, String phrase) {
        this.phraseId = phraseId;
        this.brandId = brandId;
        this.phrase = phrase;
    }

    public int getPhraseId() {
        return phraseId;
    }

    public void setPhraseId(int phraseId) {
        this.phraseId = phraseId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getPhrase() {
        return phrase;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}

