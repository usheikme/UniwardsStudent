package xyz.uniwards.uniwards_student.MainScreens.Fragments.Coupon;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.MainScreens.Popups.Passcode.StudentCode;
import xyz.uniwards.uniwards_student.R;


public class CouponAdaptor extends RecyclerView.Adapter<CouponAdaptor.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private static List<DashCard> dashCards;
    private static Activity activity;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //TODO REMOVE
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public ViewHolder(View v) {
            super(v);

            itemImage = (ImageView)itemView.findViewById(R.id.card_dashimage);
            itemTitle = (TextView)itemView.findViewById(R.id.card_dashtitle);
            itemDetail = (TextView)itemView.findViewById(R.id.card_dashdesc);

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent popup_studentcode = new Intent(v.getContext(), StudentCode.class);
                    popup_studentcode.putExtra("couponName", itemTitle.getText());
                    activity.startActivity(popup_studentcode);
                }
            });
        }
    }

    public CouponAdaptor(List<DashCard> dashCards, Activity activity) {
        this.dashCards = dashCards;
        this.activity = activity;
    }

    // Create new views (invoked by the layout manager)
    //TODO UNRIG

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = null;
        try {
            v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.cardview_layout, viewGroup, false);
        } catch (Exception e){ }


        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.itemDetail.setText(dashCards.get(position).GetCardDesc());
        viewHolder.itemImage.setImageResource(dashCards.get(position).GetCardImage());
        viewHolder.itemTitle.setText(dashCards.get(position).GetCardTitle());
        //viewHolder.getTextView().setText(mDataSet[position]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //TODO PATCHES
        return dashCards.size();
    }
}