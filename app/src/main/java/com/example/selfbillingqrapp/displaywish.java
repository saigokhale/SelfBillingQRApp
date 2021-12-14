package com.example.selfbillingqrapp;
import static com.example.selfbillingqrapp.HomeActivity2.cdb;
import static com.example.selfbillingqrapp.HomeActivity2.wdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularIntArray;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class displaywish extends AppCompatActivity
{
    private RecyclerView wishlistRV2;
    private ArrayList<ModelCartHandler> wishlistArrayList2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displaywish);

        //instructions for the user
        Toast.makeText(getApplicationContext(), "Swipe to delete item", Toast.LENGTH_SHORT).show();

        wishlistArrayList2 = new ArrayList<>();
        Cursor res= wdb.getdata_byuname(LoginActivity.getuname());

        //get data in wishlist by username
        while(res.moveToNext()) {
            String name1 = res.getString(2);
            int price1 = res.getInt(4);
            int quant = res.getInt(3);

            wishlistArrayList2.add(new ModelCartHandler(name1, 1, price1));
        }

        wishlistRV2 = findViewById(R.id.idRVCourse2);

        // we are initializing our adapter class and passing our arraylist to it.
        CartAdapter cartAdapter = new CartAdapter(this, wishlistArrayList2);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        wishlistRV2.setLayoutManager(linearLayoutManager);
        wishlistRV2.setAdapter(cartAdapter);


        //Swipe to delete data in wishlist DB implementation
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView wishlistRV2, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction. on below line we are getting the item at a particular position.
                ModelCartHandler deletedCourse = wishlistArrayList2.get(viewHolder.getAdapterPosition());

                // below line is to get the position of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped. below line is to remove item from our array list.
                wishlistArrayList2.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                cartAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Toast.makeText(getApplicationContext(),deletedCourse.get_name(),Toast.LENGTH_LONG).show();
                boolean temp=wdb.deletedata(deletedCourse.get_name());

                // below line is to display our snackbar with action.
                Snackbar.make(wishlistRV2, deletedCourse.get_name(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar line is to add our item to array list with a position.
                        wishlistArrayList2.add(position, deletedCourse);
                        // below line is to notify item is added to our adapter class.
                        cartAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
            // at last we are adding this to our recycler view.
        }).attachToRecyclerView(wishlistRV2);

    }

}