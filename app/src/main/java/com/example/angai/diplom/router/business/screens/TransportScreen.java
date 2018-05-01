package com.example.angai.diplom.router.business.screens;

import android.support.v4.app.Fragment;

import com.example.angai.diplom.R;
import com.example.angai.diplom.router.business.AppScreen;
import com.example.angai.diplom.transport.presentation.view.TransportFragment_;

public class TransportScreen extends AppScreen {

    @Override
    public int getId() {
        return R.id.navigation_transport;
    }

    @Override
    public Fragment getFragment() {
        return new TransportFragment_();
    }

    @Override
    public String toString() {
        return "TransportScreen";
    }
}
