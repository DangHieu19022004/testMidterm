package tlu.cse.ht63.bottomtabpager.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import tlu.cse.ht63.bottomtabpager.fragmentMenu.HomeFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.ManageBookFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.SavedBookFragment;
import tlu.cse.ht63.bottomtabpager.fragmentMenu.UserFragment;
import tlu.cse.ht63.bottomtabpager.fragmentOption.tab1Fragment;
import tlu.cse.ht63.bottomtabpager.fragmentOption.tab2Fragment;

public class optionadapter extends FragmentStatePagerAdapter {

    public optionadapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new tab1Fragment();
            case 1:
                return new tab2Fragment();
            default:
                return new tab1Fragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "tab1";
            case 1:
                return "tab2";
            default:
                return "tab1";
        }
    }
}