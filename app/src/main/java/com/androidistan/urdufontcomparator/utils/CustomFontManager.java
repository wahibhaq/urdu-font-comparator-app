package com.androidistan.urdufontcomparator.utils;

import android.content.res.AssetManager;
import android.graphics.Typeface;

import java.util.HashMap;
import java.util.Map;

import timber.log.Timber;

/**
 * Manager responsible to handle validation of font assets and efficient access of Fonts.
 * <p>
 * It is a bit improved version of FontManager
 * found here http://stackoverflow.com/a/29134056/1016544
 */
public class CustomFontManager {

    private AssetManager assetManager;

    private Map<String, Typeface> fonts;

    public CustomFontManager(AssetManager _mgr) {
        assetManager = _mgr;
        fonts = new HashMap<>();
    }

    public Typeface getFont(String asset) {
        Typeface font = null;

        // if font filename is emptry null? Better just return null to make problem more explicit.
        if (asset == null || asset.isEmpty()) {
            return font;
        }

        if (fonts.containsKey(asset)) {
            return fonts.get(asset);
        }

        try {
            font = Typeface.createFromAsset(assetManager, asset);
            fonts.put(asset, font);
            return font;
        } catch (Exception e) {
            Timber.e(e, e.getMessage());
        }

        if (font == null) {
            try {
                String fixedAsset = fixAssetFilename(asset);
                font = Typeface.createFromAsset(assetManager, fixedAsset);
                fonts.put(asset, font);
                fonts.put(fixedAsset, font);
                return font;
            } catch (Exception e) {
                Timber.e(e, e.getMessage());
            }
        }

        return font;
    }

    private String fixAssetFilename(String asset) {
        // Make sure that the font ends in '.ttf' or '.ttc'
        if ((!asset.endsWith(".ttf")) && (!asset.endsWith(".ttc"))) {
            asset = String.format("%s.ttf", asset);
        }
        return asset;
    }
}
