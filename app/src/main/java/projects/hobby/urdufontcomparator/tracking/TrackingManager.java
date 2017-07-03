package projects.hobby.urdufontcomparator.tracking;


public interface TrackingManager {

    void appOpen();

    void openFontDetails(String fontName);

    void openFontRating(String fontName);

    void submitFontRating(String fontName, int rating);

    void pickFont(String fontName);

    void openLicenses();

    void openAboutDevs();

    void openCredits();

    void sendEmail();

    void sendTweet();

    void errorShown();

    void shareWithFriend();

}
