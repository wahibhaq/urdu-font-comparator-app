package com.androidistan.urdufontcomparator.tracking;


public interface TrackingManager {

    void appOpen();

    void openFontDetails(String fontName);

    void openFontRating(String fontName);

    void submitFontRating(String fontName, int rating);

    void pickFont(String fontName);

    void openLicenses();

    void openAboutUs();

    void openCredits();

    void sendEmail();

    void sendTweet();

    void errorShown();

    void shareAppWithFriend();

}
