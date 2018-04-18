package xyz.uniwards.uniwards_student.MainScreens.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.uniwards.uniwards_student.R;


public class RewardFragment extends Fragment {

    public RewardFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageThree = inflater.inflate(R.layout.fragment_reward, container, false);



        return PageThree;
    }
}