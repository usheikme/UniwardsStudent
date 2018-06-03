package xyz.uniwards.uniwards_student.MainScreens.Popups.DropEnrolment;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import xyz.uniwards.uniwards_student.APIHandling.ReqThreadEntity;
import xyz.uniwards.uniwards_student.DashCardHandle.DashCard;
import xyz.uniwards.uniwards_student.EnrolmentHandling.AsyncDeleteEnrolment;
import xyz.uniwards.uniwards_student.EnrolmentHandling.AsyncNewEnrolment;
import xyz.uniwards.uniwards_student.Globals;
import xyz.uniwards.uniwards_student.R;


public class DropAdaptor extends RecyclerView.Adapter<DropAdaptor.ViewHolder> {
    private static final String TAG = "CustomAdapter";
    private static Activity activity;
    private static List<DashCard> dashCards;


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


            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + dashCards.get(getAdapterPosition()).GetCardTitle() + " clicked.");
                    //new AsyncDeleteEnrolment(activity, dashCards.get(getAdapterPosition()).GetCardTitle()).execute();
                    ReqThreadEntity request = new ReqThreadEntity(activity, new AsyncDeleteEnrolment(activity, dashCards.get(getAdapterPosition()).GetCardTitle()));
                    Globals.getInstance().GetReqThread().AddRequest(request);
                }
            });
        }
    }

    public DropAdaptor(Activity activity, List<DashCard> dashCards) {
        this.activity = activity;
        this.dashCards = dashCards;
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
    //TODO Title is classname
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.itemDetail.setText(dashCards.get(position).GetCardDesc());
        viewHolder.itemImage.setImageResource(dashCards.get(position).GetCardImage());
        viewHolder.itemTitle.setText(dashCards.get(position).GetCardTitle());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //TODO PATCHES
        return dashCards.size();
    }
}