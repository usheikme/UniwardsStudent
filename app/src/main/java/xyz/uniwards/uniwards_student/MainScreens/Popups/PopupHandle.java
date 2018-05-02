package xyz.uniwards.uniwards_student.MainScreens.Popups;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Layout;
import android.view.View;
import android.widget.Button;

/**
 * Created by Umayr on 5/2/2018.
 */

public class PopupHandle {
    public static void ShowPopup(Activity activity, final Dialog dialog, Integer layout, final Integer exitButton) {
        dialog.setContentView(layout);
        final Button button_efinish = dialog.findViewById(exitButton);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button_efinish.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
    }
}
