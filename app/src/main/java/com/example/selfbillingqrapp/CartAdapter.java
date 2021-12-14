package com.example.selfbillingqrapp;


import static com.example.selfbillingqrapp.display2.grandTotalPlus;
import static com.example.selfbillingqrapp.display2.grandTotalplus;
import static com.example.selfbillingqrapp.display2.tempArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.Viewholder>{
    private Context context;
    private ArrayList<ModelCartHandler> ModelArrayList;
    // Constructor
    public CartAdapter(Context context, ArrayList<ModelCartHandler> courseModelArrayList) {
        this.context = context;
        this.ModelArrayList = courseModelArrayList;
    }

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
        int position_2= holder.getAdapterPosition();
        // to set data to textview and imageview of each card layout\
        ModelCartHandler model = ModelArrayList.get(position_2);
        holder.NameTV.setText(model.get_name());
        holder.QtyTV.setText(String.valueOf(model.getQty()));
        holder.priceTV.setText(String.valueOf(model.getPrice()));
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ModelArrayList.get(position_2).getQty();
                Log.d("counterthegun", String.valueOf(ModelArrayList.get(position_2).getQty()));

                if (count == 1) {
                    holder.down.setEnabled(true);

                }
                //holder.down_btn.setEnabled(true);
                count += 1;
                ModelArrayList.get(position_2).setQty((count));
                holder.QtyTV.setText(String.valueOf(ModelArrayList.get(position_2).getQty()));
                int cash = (ModelArrayList.get(position_2).getQty() * (ModelArrayList.get(position_2).getPrice()));

                holder.priceTV.setText(String.valueOf(cash));
                //cartModelArrayList.get(position).setTotalCash(cash);
                //holder.productCartPrice.setText(String.valueOf(cash));
                //for (int i = 0; i < temparraylist.size(); i++) {
                //   grandTotalplus = grandTotalplus + temparraylist.get(i).getTotalCash();
                //}

//                    Log.d("totalcashthegun", String.valueOf(grandTotalplus));
//                    grandTotal.setText(String.valueOf(grandTotalplus));
            }
        });

        holder.down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //holder.down_btn.setEnabled(true);
                int count = ModelArrayList.get(position_2).getQty();
                Log.d("counterthegun", String.valueOf(ModelArrayList.get(position_2).getQty()));

                if (count == 1) {
                    holder.down.setEnabled(false);
                    Toast.makeText(context, "quantity can't be zero", Toast.LENGTH_SHORT).show();
                }else {
                    //holder.down_btn.setEnabled(true);
                    count -= 1;
                    ModelArrayList.get(position_2).setQty((count));
                    holder.QtyTV.setText(String.valueOf(ModelArrayList.get(position_2).getQty()));
                    int cash = (ModelArrayList.get(position_2).getQty() * (ModelArrayList.get(position_2).getPrice()));
                    holder.priceTV.setText(String.valueOf(cash));

                }
            }
        });


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

        private TextView priceTV, NameTV,QtyTV;
        private Button up,down;
        private TextView grandTot;

        public Viewholder(@NonNull View itemView)
        {
            super(itemView);
            priceTV = itemView.findViewById(R.id.card_text3);
            NameTV = itemView.findViewById(R.id.card_text);
            QtyTV = itemView.findViewById(R.id.card_text2);
            up = itemView.findViewById(R.id.buttonup);
            down = itemView.findViewById(R.id.buttondown);
            grandTot=itemView.findViewById(R.id.footer_text);
            display2.grandTotal=itemView.findViewById(R.id.footer_text);
        }
    }
}
