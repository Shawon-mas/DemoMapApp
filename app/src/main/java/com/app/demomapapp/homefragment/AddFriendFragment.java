package com.app.demomapapp.homefragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.app.demomapapp.databinding.FragmentAddFriendBinding;
import com.app.demomapapp.model.MyDatabase;
import com.app.demomapapp.model.User;
import com.app.demomapapp.model.UserDao;

public class AddFriendFragment extends Fragment {
private FragmentAddFriendBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentAddFriendBinding.inflate(inflater, container, false);
        button();
       return binding.getRoot();

    }

    private void button() {

        binding.addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkInputValidation();

                /*MyDatabase myDatabase= Room.databaseBuilder(getContext(),MyDatabase.class,"friend_list").allowMainThreadQueries().build();
                UserDao userDao=myDatabase.userDao();
                Boolean check=userDao.is_exist(Integer.parseInt(binding.editTextNumber.getText().toString()));
                if (check==false)
                {
                    userDao.insertrecord(new User
                            (Integer.parseInt(binding.editTextNumber.getText().toString()),binding.editTextName.getText().toString(),
                                    binding.editTextEmail.getText().toString(),binding.editTextAddress.getText().toString(),
                                    binding.latitude.getText().toString(),binding.longitude.getText().toString()));
                    binding.editTextName.setText("");
                    binding.editTextEmail.setText("");
                    binding.editTextAddress.setText("");
                    binding.editTextNumber.setText("");
                    binding.latitude.setText("");
                    binding.longitude.setText("");
                    Toast.makeText(getContext(), "Friend Added ", Toast.LENGTH_SHORT).show();
                    binding.status.setText("Friend Added Your List");

                }else {
                    binding.editTextName.setText("");
                    binding.editTextEmail.setText("");
                    binding.editTextAddress.setText("");
                    binding.editTextNumber.setText("");
                    binding.latitude.setText("");
                    binding.longitude.setText("");
                    binding.status.setText("Friend Already Exists");

                }*/
            }
        });
    }

    private void checkInputValidation()
    {
        if (binding.editTextName.getText().toString().isEmpty())
        {
            binding.editTextName.setError("Enter Your Name");
            binding.editTextName.requestFocus();
            return;
        }if (binding.editTextEmail.getText().toString().isEmpty())
    {
        binding.editTextEmail.setError("Enter your email");
        binding.editTextEmail.requestFocus();
        return;
    }if (binding.editTextAddress.getText().toString().isEmpty())
    {
        binding.editTextAddress.setError("Enter your address");
        binding.editTextAddress.requestFocus();
        return;
    }if (binding.latitude.getText().toString().isEmpty())
    {
        binding.latitude.setError("Enter your location latitude");
        binding.latitude.requestFocus();
        return;
    }if (binding.longitude.getText().toString().isEmpty())
    {
        binding.longitude.setError("Enter your location longitude");
        binding.longitude.requestFocus();
        return;
    }else {
            submitInfo();
    }

    }

    private void submitInfo() {
        MyDatabase myDatabase= Room.databaseBuilder(getContext(),MyDatabase.class,"friend_list").allowMainThreadQueries().build();
        UserDao userDao=myDatabase.userDao();
        Boolean check=userDao.is_exist(Integer.parseInt(binding.editTextNumber.getText().toString()));
        if (check==false)
        {
            userDao.insertrecord(new User
                    (Integer.parseInt(binding.editTextNumber.getText().toString()),binding.editTextName.getText().toString(),
                            binding.editTextEmail.getText().toString(),binding.editTextAddress.getText().toString(),
                            binding.latitude.getText().toString(),binding.longitude.getText().toString()));
            binding.editTextName.setText("");
            binding.editTextEmail.setText("");
            binding.editTextAddress.setText("");
            binding.editTextNumber.setText("");
            binding.latitude.setText("");
            binding.longitude.setText("");
            Toast.makeText(getContext(), "Friend Added ", Toast.LENGTH_SHORT).show();
            binding.status.setText("Friend Added Your List");

        }else {
            binding.editTextName.setText("");
            binding.editTextEmail.setText("");
            binding.editTextAddress.setText("");
            binding.editTextNumber.setText("");
            binding.latitude.setText("");
            binding.longitude.setText("");
            binding.status.setText("Friend Already Exists");

        }
    }


}
