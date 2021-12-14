package com.example.selfbillingqrapp;
//import static com.example.selfbillingqrapp.DisplayDetails.cdb;
import static com.example.selfbillingqrapp.HomeActivity2.cdb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.CircularIntArray;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;

public class display2 extends AppCompatActivity
{
    public static TextView grandTotal;
    public static int grandTotalPlus;
    public static ArrayList<ModelCartHandler> tempArrayList;
    public static int grandTotalplus;
    public static ArrayList<ModelCartHandler> temparraylist;
    private RecyclerView wishlistRV;
    private Button calc_total,check_out;
    private TextView show_total;
    // Arraylist for storing data
    //CartDB cdb;
    private ArrayList<ModelCartHandler> wishlistArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);

        Toast.makeText(getApplicationContext(), "Swipe to delete item", Toast.LENGTH_SHORT).show();

        wishlistArrayList = new ArrayList<>();
        //Toast.makeText(getApplicationContext(), LoginActivity.getuname(), Toast.LENGTH_LONG).show();
        Cursor res= cdb.getdata_byuname(LoginActivity.getuname());

        //    Toast.makeText(getApplicationContext(), "Entered here!", Toast.LENGTH_LONG).show();
        while(res.moveToNext()) {
            String name1 = res.getString(2);
            int price1 = res.getInt(4);
            int quant = res.getInt(3);

            wishlistArrayList.add(new ModelCartHandler(name1, 1, price1));
        }

        wishlistRV = findViewById(R.id.idRVCourse);
        calc_total = findViewById(R.id.total_button);
        show_total = findViewById(R.id.footer_text);
        check_out= findViewById(R.id.checkout_button);

        calc_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int cart_total_temp=0;
                for (int i = 0; i < wishlistArrayList.size(); i++) {
                    cart_total_temp += wishlistArrayList.get(i).getPrice() * wishlistArrayList.get(i).getQty();
                }
                show_total.setText("Total: ₹ " + cart_total_temp);
            }
        });

        check_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(display2.this);
                // Set the message show for the Alert time
                builder.setMessage("Confirm Checkout?");
                // Set Alert Title
                builder.setTitle("Hey "+LoginActivity.getuname());
                // Set Cancelable false
                // for when the user clicks on the outside
                // the Dialog Box then it will remain show
                builder.setCancelable(false);
                // Set the positive button with yes name
                // OnClickListener method is use of
                // DialogInterface interface.
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // When the user click yes button
                        // then enter intent
                        startActivity(new Intent(getApplicationContext(), CheckOut.class));
                    }
                });
                // Set the Negative button with No name
                // OnClickListener method is use
                // of DialogInterface interface.
                builder.setNegativeButton("No", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        // If user click no
                        // then dialog box is canceled.
                        dialog.cancel();
                    }
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });

        // we are initializing our adapter class and passing our arraylist to it.
        CartAdapter cartAdapter = new CartAdapter(this, wishlistArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        wishlistRV.setLayoutManager(linearLayoutManager);
        wishlistRV.setAdapter(cartAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView wishlistRV, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.
                ModelCartHandler deletedCourse = wishlistArrayList.get(viewHolder.getAdapterPosition());

                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                wishlistArrayList.remove(viewHolder.getAdapterPosition());

                // below line is to notify our item is removed from adapter.
                cartAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());

                Toast.makeText(getApplicationContext(),deletedCourse.get_name(),Toast.LENGTH_LONG).show();
                boolean temp=cdb.deletedata(deletedCourse.get_name());

                // below line is to display our snackbar with action.
                Snackbar.make(wishlistRV, deletedCourse.get_name(), Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // adding on click listener to our action of snack bar.
                        // below line is to add our item to array list with a position.
                        wishlistArrayList.add(position, deletedCourse);
                        // below line is to notify item is
                        // added to our adapter class.
                        cartAdapter.notifyItemInserted(position);
                    }
                }).show();
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(wishlistRV);

    }




    public ArrayList<ModelCartHandler> returnList()
    {
        return wishlistArrayList;
    }
}