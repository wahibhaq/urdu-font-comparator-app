package projects.hobby.urdufontcomparator.models;

import android.support.annotation.StringRes;

import java.util.LinkedList;

import projects.hobby.urdufontcomparator.R;

/**
 * Enum to hold details of all the urdu fonts offered in the application
 */
@Deprecated
//For now data fetched from https://quarkbackend.com/getfile/wahib-tech/urdufontslist 
public enum UrduFontsSource {

    NASTALEEQ_MEHER (R.string.label_urdu_font_meher_nastaleeq, "Meher Nastaleeq",
            R.string.font_nastaleeq_meher, "Center for Speech and Language Technologies, ITU (CSaLT)",
            R.string.csalt_itu_url, "110 KB"),
    JAMEEL_NOORI_NASTALEEQ (R.string.label_urdu_font_jameel_noori_nastaleeq,
            "Jameel Noori Nastaleeq",
            R.string.font_jameel_noori_nastaleeq, "Jameel Nastaliq Team",
            R.string.urdujahan_url, "10.8 MB"),
    JAMEEL_NOORI_NASTALEEQ_KASHEEDA (R.string.label_urdu_font_jameel_noori_nastaleeq_kasheeda,
            "Jameel Noori Nastaleeq Kasheeda",
            R.string.font_jameel_noori_nastaleeq_kasheeda, "Jameel Nastaliq Team",
            R.string.urdujahan_url, "10.1 MB"),
    PAKISTAN_NASTALEEQ (R.string.label_urdu_font_pakistan_nastaleeq, "Pakistan Nastaleeq",
            R.string.font_pakistan_nastaleeq, "Mohsin Shafeeque Hijazee",
            R.string.urdujahan_url, "170 KB"),
    FAJER_NOORI_NASTALEEQ (R.string.label_urdu_font_fajer_noori_nastaleeq, "Fajer Noori Nastaleeq",
            R.string.font_fajer_noori_nastaleeq, "M. G. Abbas Malik", R.string.urdujahan_url
            , "261 KB"),
    URDU_NASKH_ASIATYPE (R.string.label_urdu_font_urdu_naskh_asiatype, "Urdu Naskh Asiatype",
            R.string.font_urdu_naskh_asiatype, "Asiasoft", R.string.urdujahan_url, "93 KB"),
    NOTO_NASTALIQ_URDU_REGULAR (R.string.label_urdu_font_noto_nastaliq_urdu, "Noto Nastaliq Urdu",
            R.string.font_noto_nastaliq_urdu, "Google", R.string.google_noto_urdu_url, "497 KB"),
    NOTO_NASKH_ARABIC (R.string.label_urdu_font_noto_naskh_arabic, "Noto Nashk Arabic",
            R.string.font_noto_naskh_arabic, "Google", R.string.google_noto_arabic_url, "146 KB"),
    NAFEES_NASTALEEQ (R.string.label_urdu_font_nafees_nastaleeq, "Nafees Nastaleeq",
            R.string.font_nafees_nastaleeq, "Center for Language Engineering (CLE)",
            R.string.cle_nafees_nastaleeq_url, "397 KB"),
    NAFEES_RIQA (R.string.label_urdu_font_nafees_riqa, "Nafees Riqa",
            R.string.font_nafees_riqa, "Center for Language Engineering (CLE)",
            R.string.cle_nafees_riqa_url, "322 KB");

    @StringRes
    public int fontLabel;

    public final String serializedName;

    @StringRes
    public int fontFileName;

    public final String provider;

    @StringRes
    public int website;

    public final String fileSize;


    UrduFontsSource(@StringRes int fontLabel, String serializedName, @StringRes int fontFileName,
            String provider, @StringRes int website, String fileSize) {
        this.fontLabel = fontLabel;
        this.serializedName = serializedName;
        this.fontFileName = fontFileName;
        this.provider = provider;
        this.website = website;
        this.fileSize = fileSize;
    }

    public static UrduFontsSource from(String value) {
        for (UrduFontsSource font : UrduFontsSource.values()) {
            if (font.serializedName.equals(value)) {
                return font;
            }
        }
        //throw new IllegalArgumentException("No UrduFontsSource enum constant for " + value);
        return null;
    }

    public static String[] getFontNames() {
        LinkedList<String> list = new LinkedList<>();
        for (UrduFontsSource font : UrduFontsSource.values()) {
            list.add(font.serializedName);
        }
        return list.toArray(new String[list.size()]);
    }

    public static UrduFontsSource getDefaultFont() {
        return NASTALEEQ_MEHER;
    }
}
