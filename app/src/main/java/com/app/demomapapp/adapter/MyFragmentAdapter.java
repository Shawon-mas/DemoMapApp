package com.app.demomapapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.app.demomapapp.homefffragment.AddFriendFragment;
import com.app.demomapapp.homefffragment.ListFriendFragment;
import com.app.demomapapp.homefffragment.MapFragment;

public class MyFragmentAdapter extends FragmentStateAdapter {
    public MyFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position==0)
        {
            return new AddFriendFragment();
        }else if (position==1){
            return new ListFriendFragment();
        }else if (position==2){
            return new MapFragment();
        }
        return new AddFriendFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}


