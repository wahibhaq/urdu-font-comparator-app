package projects.hobby.urdufontcomparator.models;

import android.support.annotation.StringRes;
import java.util.LinkedList;
import projects.hobby.urdufontcomparator.R;

/**
 * Enum to hold details of all the urdu fonts offered in the application
 */
public enum UrduFonts {
    //TODO later add download link, source etc

    NASTALEEQ_MEHER (R.string.label_urdu_font_meher_nastaleeq, "Meher Nastaleeq",
            R.string.font_nastaleeq_meher),
    JAMEEL_NOORI_NASTALEEQ (R.string.label_urdu_font_jameel_noori_nastaleeq,
            "Jameel Noori Nastaleeq",
            R.string.font_jameel_noori_nastaleeq),
    JAMEEL_NOORI_NASTALEEQ_KASHEEDA (R.string.label_urdu_font_jameel_noori_nastaleeq_kasheeda,
            "Jameel Noori Nastaleeq Kasheeda",
            R.string.font_jameel_noori_nastaleeq_kasheeda),
    PAKISTAN_NASTALEEQ (R.string.label_urdu_font_pakistan_nastaleeq, "Pakistan Nastaleeq",
            R.string.font_pakistan_nastaleeq),
    FAJER_NOORI_NASTALEEQ (R.string.label_urdu_font_fajer_noori_nastaleeq, "Fajer Noori Nastaleeq",
            R.string.font_fajer_noori_nastaleeq),
    URDU_NASKH_ASIATYPE (R.string.label_urdu_font_urdu_naskh_asiatype, "Urdu Naskh Asiatype",
            R.string.font_urdu_naskh_asiatype);

    @StringRes
    public int fontLabel;

    public final String serializedName;

    @StringRes
    public int fontFileName;

    UrduFonts(@StringRes int fontLabel, String serializedName, @StringRes int fontFileName) {
        this.fontLabel = fontLabel;
        this.serializedName = serializedName;
        this.fontFileName = fontFileName;
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
}
