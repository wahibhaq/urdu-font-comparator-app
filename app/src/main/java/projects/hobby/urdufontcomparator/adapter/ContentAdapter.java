package projects.hobby.urdufontcomparator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import projects.hobby.urdufontcomparator.fragments.ContentFragment;

/**
 * Created by Zeeshan on 4/12/17.
 */

public class ContentAdapter extends FragmentPagerAdapter {

    private List<String> fonts;

    public ContentAdapter(FragmentManager fm, List<String> fonts) {
        super(fm);
        this.fonts = fonts;
    }

    @Override
    public Fragment getItem(int position) {
        String font = fonts.get(position);
        return ContentFragment.newInstance(font);
    }

    @Override
    public int getCount() {
        return fonts.size();
    }
}
