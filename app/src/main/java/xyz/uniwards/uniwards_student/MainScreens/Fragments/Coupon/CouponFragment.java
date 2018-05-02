package xyz.uniwards.uniwards_student.MainScreens.Fragments.Coupon;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.uniwards.uniwards_student.R;

public class CouponFragment extends Fragment {

    public CouponFragment(){};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View PageTwo = inflater.inflate(R.layout.fragment_coupon, container, false);



        return PageTwo;
    }
}