package com.android.demo.framework.models;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FeaturedMatchResponseModel {

    private String creditsLeft;
    private Provider provider;
    private String v;
    private ArrayList<Match> matches;
    private String ttl;

    public String getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(String creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public ArrayList<Match> getMatches() {
        return matches;
    }

    public void setMatches(ArrayList<Match> matches) {
        this.matches = matches;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    @NotNull
    @Override
    public String toString() {
        return "Class [creditsLeft = " + creditsLeft + ", provider = " + provider + ", v = " + v + ", matches = " + matches + ", ttl = " + ttl + "]";
    }
}