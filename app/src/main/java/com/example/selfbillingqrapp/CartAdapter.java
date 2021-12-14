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
    private ArrayList<ModelCartHandler> ModelArrayList; //ArrayList for storing card data

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
        // to set data to textview of each card layout
        int position_2= holder.getAdapterPosition();        //tells us the position of card in recyclerview
        ModelCartHandler model = ModelArrayList.get(position_2);

        holder.NameTV.setText(model.get_name());
        holder.QtyTV.setText(String.valueOf(model.getQty()));
        holder.priceTV.setText(String.valueOf(model.getPrice()));

        //Button to increase cart quantity
        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = ModelArrayList.get(position_2).getQty();
                Log.d("counterthegun", String.valueOf(ModelArrayList.get(position_2).getQty()));

                if (count == 1) {
                    holder.down.setEnabled(true);

                }
                count += 1;
                ModelArrayList.get(position_2).setQty((count));
                holder.QtyTV.setText(String.valueOf(ModelArrayList.get(position_2).getQty()));

                //updates total
                int cash = (ModelArrayList.get(position_2).getQty() * (ModelArrayList.get(position_2).getPrice()));
                holder.priceTV.setText(String.valueOf(cash));

            }
        });

        //button to decrease cart quantity
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
                    count -= 1;
                    ModelArrayList.get(position_2).setQty((count));
                    holder.QtyTV.setText(String.valueOf(ModelArrayList.get(position_2).getQty()));

                    //updates total
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
    // your views such as TextView
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
        }
    }
}
