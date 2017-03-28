package projects.hobby.urdufontcomparator.models;

import android.support.annotation.StringRes;
import java.util.LinkedList;
import projects.hobby.urdufontcomparator.R;

/**
 * Enum to hold details of all the urdu fonts offered in the application
 */
public enum UrduFonts {
    //TODO later add download link, source, license, provider info etc

    NASTALEEQ_MEHER (R.string.label_urdu_font_meher_nastaleeq, "Meher Nastaleeq",
            R.string.font_nastaleeq_meher, "Center for Speech and Language Technologies, ITU (CSaLT)",
            "http://csalt.itu.edu.pk/urdufont/", "http://csalt.itu.edu.pk/urdufont/", "110 KB"),
    JAMEEL_NOORI_NASTALEEQ (R.string.label_urdu_font_jameel_noori_nastaleeq,
            "Jameel Noori Nastaleeq",
            R.string.font_jameel_noori_nastaleeq, "Jameel Nastaliq Team",
            "", "http://www.urdujahan.com/urdu-fonts/Jameel%20Noori%20Nastaleeq.zip", "10.8 MB"),
    JAMEEL_NOORI_NASTALEEQ_KASHEEDA (R.string.label_urdu_font_jameel_noori_nastaleeq_kasheeda,
            "Jameel Noori Nastaleeq Kasheeda",
            R.string.font_jameel_noori_nastaleeq_kasheeda, "Jameel Nastaliq Team", "Not Available",
            "http://www.urdujahan.com/urdu-fonts/Jameel%20Noori%20Nastaleeq.zip", "10.1 MB"),
    PAKISTAN_NASTALEEQ (R.string.label_urdu_font_pakistan_nastaleeq, "Center of Excellence for Urdu Informatics",
            R.string.font_pakistan_nastaleeq, "Not Available", "Not Available",
            "http://www.urdujahan.com/urdu-fonts/Pak%20Nastaleeq%20(Beta%20Release).ttf", "170 KB"),
    FAJER_NOORI_NASTALEEQ (R.string.label_urdu_font_fajer_noori_nastaleeq, "Fajer Noori Nastaleeq",
            R.string.font_fajer_noori_nastaleeq, "M. G. Abbas Malik", "Not Available",
            "http://www.urdujahan.com/urdu-fonts/Fajer%20Noori%20Nastalique%2015-12-2006.ttf", "261 KB"),
    URDU_NASKH_ASIATYPE (R.string.label_urdu_font_urdu_naskh_asiatype, "Urdu Naskh Asiatype",
            R.string.font_urdu_naskh_asiatype, "Asiasoft", "Not Available",
            "http://www.urdujahan.com/urdu-fonts/asunaskh.ttf", "93 KB"),
    NOTO_NASTALIQ_URDU_REGULAR (R.string.label_urdu_font_noto_nastaliq_urdu, "Noto Nastaliq Urdu",
            R.string.font_noto_nastaliq_urdu, "Google", "https://www.google.com/get/noto/#nastaliq-aran",
            "https://noto-website-2.storage.googleapis.com/pkgs/NotoNastaliqUrdu-unhinted.zip", "497 KB"),
    NOTO_NASKH_ARABIC (R.string.label_urdu_font_noto_naskh_arabic, "Noto Nashk Arabic",
            R.string.font_noto_naskh_arabic, "Google", "https://www.google.com/get/noto/#naskh-arab",
            "https://noto-website-2.storage.googleapis.com/pkgs/NotoNaskhArabic-unhinted.zip", "146 KB"),
    NAFEES_NASTALEEQ (R.string.label_urdu_font_nafees_nastaleeq, "Nafees Nastaleeq",
            R.string.font_nafees_nastaleeq, "Center for Language Engineering (CLE)",
            "http://www.cle.org.pk/software/localization/Fonts/nafeesNastaleeq.html",
            "http://www.cle.org.pk/software/localization/Fonts/nafeesNastaleeq.html", "397 KB"),
    NAFEES_RIQA (R.string.label_urdu_font_nafees_riqa, "Nafees Riqa",
            R.string.font_nafees_riqa, "Center for Language Engineering (CLE)",
            "http://www.cle.org.pk/software/localization/Fonts/nafeesRiqa.html",
            "http://www.cle.org.pk/software/localization/Fonts/nafeesRiqa.html", "322 KB");

    @StringRes
    public int fontLabel;

    public final String serializedName;

    @StringRes
    public int fontFileName;

    public final String provider;

    public final String website;

    public final String downloadLink;

    public final String fileSize;


    UrduFonts(@StringRes int fontLabel, String serializedName, @StringRes int fontFileName,
            String provider,  String website, String downloadLink, String fileSize) {
        this.fontLabel = fontLabel;
        this.serializedName = serializedName;
        this.fontFileName = fontFileName;
        this.provider = provider;
        this.website = website;
        this.downloadLink = downloadLink;
        this.fileSize = fileSize;
    }

    public static UrduFonts from(String value) {
        for (UrduFonts font : UrduFonts.values()) {
            if (font.serializedName.equals(value)) {
                return font;
            }
        }
        throw new IllegalArgumentException("No UrduFonts enum constant for " + value);
    }

    public static String[] getFontNames() {
        LinkedList<String> list = new LinkedList<>();
        for (UrduFonts font : UrduFonts.values()) {
            list.add(font.serializedName);
        }
        return list.toArray(new String[list.size()]);
    }

    public static UrduFonts getDefaultFont() {
        return NASTALEEQ_MEHER;
    }
}
