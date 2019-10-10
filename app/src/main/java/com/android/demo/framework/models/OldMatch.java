package com.android.demo.framework.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.jetbrains.annotations.NotNull;

public class OldMatch {

    @JsonProperty("unique_id")
    private String uniqueId;
    private String description;
    private String title;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NotNull
    @Override
    public String toString() {
        return "Class [uniqueId = " + uniqueId + ", description = " + description + ", title = " + title + "]";
    }

}