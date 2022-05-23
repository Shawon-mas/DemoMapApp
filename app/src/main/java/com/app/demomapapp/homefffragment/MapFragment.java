package com.app.demomapapp.homefffragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.app.demomapapp.R;
import com.app.demomapapp.databinding.FragmentMapBinding;
import com.app.demomapapp.model.MyDatabase;
import com.app.demomapapp.model.MyFriendLocation;
import com.app.demomapapp.model.User;
import com.app.demomapapp.model.UserDao;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.maps.android.clustering.ClusterManager;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


public class MapFragment extends Fragment  {
      private FragmentMapBinding binding;
      boolean isPermissionGranted;
      private FusedLocationProviderClient client;
      SupportMapFragment supportMapFragment;
      private ClusterManager<MyFriendLocation> friendLocationClusterManager;
      private MyFriendLocation myFriendLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding= FragmentMapBinding.inflate(inflater, container, false);
        supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.fragment);
        client=new FusedLocationProviderClient(getActivity());
        checkPermission();

      /*  binding.bootm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrenLoc();
            }
        });*/

        return binding.getRoot();
    }







    private boolean isGpsenable() {
        LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean providerEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (providerEnable) {
            return true;
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                    .setTitle("GPS Permission")
                    .setMessage("GPS is required for this app to work.Please enable GPS")
                    .setPositiveButton("Yes", ((dialog, i) -> {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, 9001);
                    }))
                    .setCancelable(false)
                    .show();
        }
        return false;
    }
    private void checkPermission() {
        Dexter.withContext(getActivity()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                Toast.makeText(getActivity(), "Permission Granted", Toast.LENGTH_SHORT).show();
                    getCurrenLoc();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                Intent intent=new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri=Uri.fromParts("package",getActivity().getPackageName(),"");
                intent.setData(uri);
                startActivity(intent);
            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    @SuppressLint("MissingPermission")
    private void getCurrenLoc() {

        Task<Location> task=client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
            supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull GoogleMap googleMap) {
                    Double latitude=location.getLatitude();
                    Double longitude=location.getLongitude();
                    LatLng latLng=new LatLng(latitude,longitude);
                    MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Current Location");
                    googleMap.addMarker(markerOptions);
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,12));

                    friendLocationClusterManager=new ClusterManager<MyFriendLocation>(getActivity(),googleMap);
                    googleMap.setOnCameraIdleListener(friendLocationClusterManager);
                    googleMap.setOnMarkerClickListener(friendLocationClusterManager);
                    addItems();
                }
            });
            }
        });


    }

    private void addItems() {
        MyDatabase myDatabase= Room.databaseBuilder(getContext(),MyDatabase.class,"friend_list").allowMainThreadQueries().build();
        UserDao userDao=myDatabase.userDao();
        List<User> users=userDao.getallusers();
        for (User user:users)
        {
            Double lat=Double.valueOf(user.getUserLatitude());
            Double lon=Double.valueOf(user.getUserLongitude());
            myFriendLocation=new MyFriendLocation(lat,lon,user.getUserName(),user.getUserAddress());
            friendLocationClusterManager.addItems(Collections.singleton(myFriendLocation));
        }
    }


}