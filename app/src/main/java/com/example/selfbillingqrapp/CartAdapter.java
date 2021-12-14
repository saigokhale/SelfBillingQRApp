package com.example.selfbillingqrapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder>{
    private Context context;
    private ArrayList<ModelCartHandler> ModelArrayList;
    //AdapterView.OnItemClickListener mListener;
    // Constructor
    public CartAdapter(Context context, ArrayList<ModelCartHandler> courseModelArrayList) {
        this.context = context;
        this.ModelArrayList = courseModelArrayList;
    }


//    public interface onItemClickListener
//    {
//        void onIncClick(int position);
//        void onDecClick(int position);
//    }
//
//    public void setOnItemClickListener(AdapterView.OnItemClickListener listener) {
//        mListener = listener;
//    }

    @NonNull
    @Override
    public CartAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_cart, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        ModelCartHandler model = ModelArrayList.get(position);
        holder.NameTV.setText(model.get_name());
        holder.MfgTV.setText(model.getMfg());
        holder.priceTV.setText(String.valueOf(model.getPrice()));

    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return ModelArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {

        private TextView priceTV, NameTV,MfgTV;
        private ImageButton up,down;

        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            priceTV = itemView.findViewById(R.id.card_text3);
            NameTV = itemView.findViewById(R.id.card_text);
            MfgTV = itemView.findViewById(R.id.card_text2);
            up = itemView.findViewById(R.id.increase_btn);
            down = itemView.findViewById(R.id.decrease_btn);

//            up.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                        if (listener != null) {
//                            int position = getAdapterPosition();
//                            if (position != RecyclerView.NO_POSITION) {
//                                listener.onDeleteClick(position);
//                            }
//                        }
//                    }
//
//                }
//            });
        }
    }
}
