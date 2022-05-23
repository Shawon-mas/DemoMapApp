package com.app.demomapapp.homefffragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.demomapapp.R;
import com.app.demomapapp.adapter.FriendAdaptar;
import com.app.demomapapp.databinding.FragmentAddFriendBinding;
import com.app.demomapapp.databinding.FragmentListFriendBinding;
import com.app.demomapapp.model.MyDatabase;
import com.app.demomapapp.model.User;
import com.app.demomapapp.model.UserDao;

import java.util.ArrayList;
import java.util.List;

public class ListFriendFragment extends Fragment {
    private FragmentListFriendBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentListFriendBinding.inflate(inflater, container, false);
        fetchFriendList();
        return binding.getRoot();
    }

    private void fetchFriendList() {
        MyDatabase myDatabase= Room.databaseBuilder(getContext(),MyDatabase.class,"friend_list").allowMainThreadQueries().build();
        UserDao userDao=myDatabase.userDao();

        binding.friendListRecycler.setHasFixedSize(true);
        binding.friendListRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        List<User> users=userDao.getallusers();

        FriendAdaptar adaptar=new FriendAdaptar(getContext(),users);
        binding.friendListRecycler.setAdapter(adaptar);
        adaptar.notifyDataSetChanged();


    }
}