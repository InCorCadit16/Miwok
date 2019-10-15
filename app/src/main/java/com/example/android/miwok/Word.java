package com.example.android.miwok;

/**
 * Created by InCorCadit16 on 01.11.2017.
 */

public class Word {
    private static final int NO_IMAGE = -1;
    private String miwokTranslation, defaultTranslation;
    private int resourceId = NO_IMAGE;
    private int mediaFileResourceId;

    public Word(String defaultTranslation, String miwokTranslation, int mediaFileResourceId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.mediaFileResourceId = mediaFileResourceId;
    }

    public Word(String defaultTranslation, String miwokTranslation, int resourceId, int mediaFileResourceId) {
        this.defaultTranslation = defaultTranslation;
        this.miwokTranslation = miwokTranslation;
        this.resourceId = resourceId;
        this.mediaFileResourceId = mediaFileResourceId;
    }


    public String getMiwokTranslation() {return this.miwokTranslation;}

    public String getDefaultTranslation() {return this.defaultTranslation;}

    public int getResourceId() {return this.resourceId;}

    public int getMediaFileResourceId() {return this.mediaFileResourceId;}

    public boolean hasResourceId() {return this.resourceId != NO_IMAGE;}


}
