package com.practicekotlin.razorpayintegration;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements PaymentResultListener {

    private Button btn;
    private TextView txtvw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        txtvw = findViewById(R.id.textView);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment(){
        Activity activity = this;
        Random random = new Random();
        int randomUniqueId = random.nextInt(1000);
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_Ws3e7fJWQehNge");
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Random tech pvt ltd");
            options.put("description", "Subscription charges");
//            options.put("send_sms_hash",true);
//            options.put("allow_rotation", true);
            options.put("transaction id", randomUniqueId);
            options.put("theme.color","#3399cc");
//            You can omit the image option to fetch the image from dashboard
//            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", 100);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "email@gmail.com");
            preFill.put("contact","8980898089");
            options.put("prefill", preFill);
            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"msg:"+s,Toast.LENGTH_SHORT).show();
        txtvw.setText(s);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"msg:"+s,Toast.LENGTH_SHORT).show();
        txtvw.setText(s);
    }
}
