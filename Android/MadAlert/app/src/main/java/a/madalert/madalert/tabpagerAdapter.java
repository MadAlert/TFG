package a.madalert.madalert;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by gmolindi on 21/03/2018.
 */

public class TabpagerAdapter extends FragmentStatePagerAdapter {

    private String[] tabArray = new String[]{"Alertas", "Mapa", "Distritos"};
    private Integer tabNumber = tabArray.length;

    public TabpagerAdapter(FragmentManager fm) {
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
                AlertasFragmento aler = new AlertasFragmento();
                return aler;
            case 1:
                MapaFragmento map = new MapaFragmento();
                return map;
            case 2:
                DistritosFragmento dist = new DistritosFragmento();
                return dist;
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabNumber;
    }

}
