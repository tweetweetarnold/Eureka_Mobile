package arnold.eureka_mobile.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import arnold.eureka_mobile.Fragment.CanteenSelectorFragment;
import arnold.eureka_mobile.Fragment.FavouritesFragment;
import arnold.eureka_mobile.Fragment.OrderHistoryFragment;

public class HomepageTabsPagerAdapter extends FragmentPagerAdapter {
    public HomepageTabsPagerAdapter (FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int i){
        Fragment fragment = null;
            switch(i){
                case 0:
                    fragment = new OrderHistoryFragment();
                    break;
                case 1:
                    fragment = new CanteenSelectorFragment();
                    break;
                case 2:
                    fragment = new FavouritesFragment();
                    break;
            }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3; // to be adjusted according to the number of tabs
    }
}