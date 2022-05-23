package com.app.demomapapp.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.demomapapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MyInfoAdapter implements GoogleMap.InfoWindowAdapter {
   Context context;

    public MyInfoAdapter(Context context) {
        this.context = context;
    }

    @Nullable
    @Override
    public View getInfoContents(Marker marker) {
        View view= LayoutInflater.from(context).inflate(R.layout.map_window,null);
        TextView name=view.findViewById(R.id.name);
        TextView address=view.findViewById(R.id.address);
        name.setText(marker.getTitle());
        address.setText(marker.getSnippet());
        return view;
    }

    @Nullable
    @Override
    public View getInfoWindow(@NonNull Marker marker) {
        return null;
    }
}
