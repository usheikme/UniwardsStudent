package xyz.uniwards.uniwards_student;

/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

        import android.graphics.drawable.Drawable;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import org.w3c.dom.Text;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private static final String TAG = "CustomAdapter";

    private String[] mDataSet;

    //TODO FUCK
    public static int count = 0;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        //TODO REMOVE
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;

        public String[] titles = new String[50];
        public String[] descs = new String[50];
        public Integer[] drawme = new Integer[50];

        public ViewHolder(View v) {
            super(v);

            //TODO TAKE OUT
            titles[0] = "Class detected!";
            titles[1] = "Coupon Redeemed!";
            titles[2] = "Reward Received!";

            descs[0] = "We've detected that you're in CSIT113, click this to confirm";
            descs[1] = "Thank you for redeeming your 15% off at SinX Digital Solutions";
            descs[2] = "250 points have added to your account for attending your last 3 classes in a row";

            drawme[0] = R.mipmap.classroom_detected;
            drawme[1] = R.mipmap.coupon_redeemed;
            drawme[2] = R.mipmap.reward_given;

            itemImage = (ImageView)itemView.findViewById(R.id.card_dashimage);
            itemTitle = (TextView)itemView.findViewById(R.id.card_dashtitle);
            itemDetail =
                    (TextView)itemView.findViewById(R.id.card_dashdesc);

            itemDetail.setText(descs[count]);
            itemImage.setImageResource(drawme[count]);
            itemTitle.setText(titles[count++]);

            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Element " + getAdapterPosition() + " clicked.");
                    Toast.makeText(v.getContext(), "Thank you, enjoy your points :)", Toast.LENGTH_LONG).show();
                }
            });
            textView = (TextView) v.findViewById(R.id.card_dashtitle);
        }

        public TextView getTextView() {
            return textView;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public CustomAdapter(String[] dataSet) {
        mDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_layout, viewGroup, false);

        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //TODO UNDO

        //viewHolder.getTextView().setText(mDataSet[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        //TODO PATCHES
        return mDataSet.length;
    }
}