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
/*
 /*SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.googleMap);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {

                googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                    @Override
                    public void onMapClick(@NonNull LatLng latLng) {
                        MarkerOptions markerOptions=new MarkerOptions();
                        markerOptions.position(latLng);
                        markerOptions.title(latLng.latitude+" : "+ latLng.longitude);
                        googleMap.clear();
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                latLng,10
                        ));
                        googleMap.addMarker(markerOptions);
                    }
                });
            }
        });

  @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (REQUEST_CODE){
            case REQUEST_CODE:
                if (grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    getCureentLocation();
                }
                break;
        }
    }

          private void getCureentLocation(){
        if (ActivityCompat.checkSelfPermission(
                getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(),new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },REQUEST_CODE);
            return;
        }
        Task<Location> task= client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location!=null)
                {
                    currentLocation=location;
                    Toast.makeText(getContext(), (int) currentLocation.getLatitude(), Toast.LENGTH_SHORT).show();
                    SupportMapFragment mapFragment =
                            (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
                    mapFragment.getMapAsync(callback);
                }
            }
        });
    }

        */

