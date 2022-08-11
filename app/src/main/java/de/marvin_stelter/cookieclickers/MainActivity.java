package de.marvin_stelter.cookieclickers;


import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class MainActivity extends AppCompatActivity {


    /*
        This code is basically like a piece of shit, probably shit is better I guess
     */
    TextView tv_geld;
    ArrayList<ShopItem>
    ImageView iv_cookie;
    long geld = 1;
    long gpk = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        iniViews();
        load();
        save();

        if(geld <= 0){
            geld = 1;
            gpk = 1;
            save();
        }

        iv_cookie.setOnClickListener(view -> {
            Animation clicker_animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
            iv_cookie.startAnimation(clicker_animation);
            toasti();
            geld = geld + gpk;
            tv_geld.setText(geld + " Cookies");
            save();
        });

    }
    private void iniViews(){
        tv_geld = findViewById(R.id.tv_geld);
        iv_cookie = findViewById(R.id.iv_cookie);
    }
    private void load(){
        SharedPreferences sp_app_prefs = getSharedPreferences(getPackageName(), 0);
        geld = sp_app_prefs.getLong("geld", 0);
        gpk = sp_app_prefs.getLong("gpk", 0);

        tv_geld.setText(geld + " Cookies");
    }
    private void save(){
        SharedPreferences sp_app_prefs = getSharedPreferences(getPackageName(), 0);
        SharedPreferences.Editor editor = sp_app_prefs.edit();
        editor.putLong("geld",geld);
        editor.putLong("gpk", gpk);
        editor.apply();
    }
    @SuppressLint("SetTextI18n")
    private void toasti(){
        Random random = new Random();
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER | Gravity.LEFT, random.nextInt(600) + 100, random.nextInt(600) - 300);
        toast.setDuration(Toast.LENGTH_SHORT);
        TextView tv_toast = new TextView(this);
        tv_toast.setTextSize(30f);
        tv_toast.setText("+" + gpk);
        tv_toast.setTextColor(Color.WHITE);
        toast.setView(tv_toast);
        toast.show();

    }

    private void shop(){

    }
  
}
