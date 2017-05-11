package projects.hobby.urdufontcomparator.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import projects.hobby.urdufontcomparator.fragments.ContentFragment;
import projects.hobby.urdufontcomparator.models.UrduFont;

public class ContentAdapter extends FragmentPagerAdapter {

    private List<UrduFont> fonts;

    public ContentAdapter(FragmentManager fm, List<UrduFont> fonts) {
        super(fm);
        this.fonts = fonts;
    }

    @Override
    public Fragment getItem(int position) {
        final UrduFont font = fonts.get(position);
        return ContentFragment.newInstance(font);
    }

    @Override
    public int getCount() {
        return fonts.size();
    }
}
