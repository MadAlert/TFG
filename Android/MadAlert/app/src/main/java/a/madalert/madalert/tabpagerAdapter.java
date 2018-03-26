package a.madalert.madalert;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by gmolindi on 21/03/2018.
 */

public class tabpagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabArray = new String[]{"Alertas", "Mapa", "Distritos"};
    private Integer tabNumber = tabArray.length;

    public tabpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabArray[position];
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                alertas aler = new alertas();
                return aler;
            case 1:
                mapa map = new mapa();
                return map;
            case 2:
                distritos dist = new distritos();
                return dist;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabNumber;
    }

}
