package xyz.uniwards.uniwards_student.MainScreens.Fragments.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xyz.uniwards.uniwards_student.MainScreens.Popups.Enrol.PopupEnrolActivity;
import xyz.uniwards.uniwards_student.R;

import android.widget.Button;

public class ProfileFragment extends Fragment {

    public ProfileFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View Page = inflater.inflate(R.layout.fragment_profile, container, false);


        InitEnrolButtonAsClickable(Page);

        return Page;
    }

    private void InitEnrolButtonAsClickable(View page) {
        Button button_penrol = page.findViewById(R.id.button_penrol);
        //final Integer button_efinish = R.id.button_efinish;
        //final Dialog dialog = new Dialog(page.getContext());
        final Intent popup_enrol = new Intent(page.getContext(), PopupEnrolActivity.class);

         button_penrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(popup_enrol);
            }
        });
    }

}