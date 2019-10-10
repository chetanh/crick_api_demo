package com.android.demo.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class OldMatchResponseModel {

    private String creditsLeft;
    private String cache;
    @JsonProperty("data")
    private ArrayList<OldMatch> oldMatches;
    private Provider provider;
    private String cache2;
    private String v;
    private String ttl;

    public String getCreditsLeft() {
        return creditsLeft;
    }

    public void setCreditsLeft(String creditsLeft) {
        this.creditsLeft = creditsLeft;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public ArrayList<OldMatch> getOldMatches() {
        return oldMatches;
    }

    public void setOldMatches(ArrayList<OldMatch> oldMatches) {
        this.oldMatches = oldMatches;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public String getCache2() {
        return cache2;
    }

    public void setCache2(String cache2) {
        this.cache2 = cache2;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
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
        return "Class [creditsLeft = " + creditsLeft + ", cache = " + cache + ", oldMatches = " + oldMatches + ", provider = " + provider + ", cache2 = " + cache2 + ", v = " + v + ", ttl = " + ttl + "]";
    }
}