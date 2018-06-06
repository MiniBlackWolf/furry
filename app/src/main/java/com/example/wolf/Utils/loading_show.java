package com.example.wolf.Utils;


import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class loading_show {
    private AppCompatActivity appCompatActivity;
    dialogloading dialogloading=new dialogloading();
    public loading_show(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }


    public void show(){
        FragmentTransaction fragmentTransaction = appCompatActivity.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        dialogloading.show(fragmentTransaction,"ss");
    }
    public void dimss(){
        dialogloading.dismiss();
    }
}
