package tlu.cse.ht63.bottomtabpager.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tlu.cse.ht63.bottomtabpager.fragmentMenu.HomeFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.ManageBookFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.SavedBookFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.UserFragment;

public class menuAdapter extends FragmentStatePagerAdapter {

    public menuAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new SavedBookFragment();
            case 2:
                return new ManageBookFragment();
            case 3:
                return new UserFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

}