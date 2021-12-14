package com.example.selfbillingqrapp;

import static com.budiyev.android.codescanner.CodeScanner.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.google.zxing.Result;

import org.w3c.dom.Text;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import java.util.concurrent.ExecutionException;
import java.util.regex.Pattern;


public class QrActivity<permissions> extends AppCompatActivity
{
    TextView txt;
    CodeScanner codeScanner;
    CodeScannerView codeScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        setPermission();
        txt = (TextView) findViewById(R.id.textView);
        codeScannerView = (CodeScannerView) findViewById(R.id.scannerView);
        codeScanner = new CodeScanner(this, codeScannerView);

        //Part where Library uses it's algorithm to decode the Qr
        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {                  //does background operation on current page and updates on main
                    @Override
                    public void run() {
                        //SUPER IMPORTANT PART OF THE CODE WHICH WILL TAKE US TO THE NEXT ACTIVITY

                        txt.setText(result.getText());          //result stores the value of scanned code
                        String code=txt.getText().toString();   //code is the scanned value of the QR Code, hashcode for database in this case

                        //perform intent from here, activity to add item into cart or not

                        Intent intent_display = new Intent(getApplicationContext(), DisplayDetails.class);

                        //send database code along with the intent
                        intent_display.putExtra("code", code);
                        startActivity(intent_display);
                    }
                });
            }
        });

    }

    //on resuming activity use camera resource again
    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
        requestCamera();
    }

    //if activity is paused, exit camera resource
    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    //preview of camera starts after permission granted
    private void requestCamera() {
        codeScanner.startPreview();
    }

    //accesses permission information
    private void setPermission()
    {
        int permission= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest();
        }
    }

    //Making a Camera Request to User
    private void makeRequest() {
        Pattern ptr = Pattern.compile(" ");
        ActivityCompat.requestPermissions(this, ptr.split(Manifest.permission.CAMERA.toString()), 103);

    }

    //If request code of camera isn't granted
    public void OnRequestPermissionsResultCallback(int requestCode,String[] permissions, int[] grantResults)
    {
        if(requestCode==103)
        {
            if(grantResults.length==0||grantResults[0]!=PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "You need to enable Camera permissions",Toast.LENGTH_SHORT).show();
            }
            else
            {
                //successful
            }
        }
    }
}