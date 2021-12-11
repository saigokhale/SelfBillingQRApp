package com.example.selfbillingqrapp;

import static com.budiyev.android.codescanner.CodeScanner.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
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

//import me.dm7.barcodescanner.zbar.ZBarScannerView;

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


        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(this, result.toString(),Toast.LENGTH_SHORT);
                        //SUPER IMPORTANT PART OF TH CODE WHICH WILL TAKE US TO THE NEXT ACTIVITY
                        txt.setText(result.getText());
                        String temp=txt.getText().toString();
                        Toast.makeText(QrActivity.this, temp,Toast.LENGTH_SHORT).show();
                        //perform intent from here, activity to add item into cart or not
                    }
                });
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        codeScanner.startPreview();
        requestCamera();
    }

    @Override
    protected void onPause() {
        codeScanner.releaseResources();
        super.onPause();
    }

    private void requestCamera() {
        codeScanner.startPreview();
    }

    private void setPermission()
    {
        int permission= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        if(permission != PackageManager.PERMISSION_GRANTED)
        {
            makeRequest();
        }
    }

    private void makeRequest() {
        Pattern ptr = Pattern.compile(" ");
        ActivityCompat.requestPermissions(this, ptr.split(Manifest.permission.CAMERA.toString()), 103);

    }


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