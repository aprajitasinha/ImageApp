package com.application.imageapp.base.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BaseObservable;


public abstract class ActivityViewModel<A extends AppCompatActivity>
        extends BaseObservable {


    public static boolean isVisible = false;

    protected A activity;

    public ActivityViewModel(A activity) {
        this.activity = activity;
    }


    public A getActivity() {
        return activity;
    }

    public  void finish() {
        activity.finish();
    }

    /**
     * Activity lifecycle
     */
    public boolean onBackKeyPress() {
        return false;
    }

    public void onStart() {
        isVisible = true;
    }

    public void onStop() {
        isVisible = false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
    public void startActivity(Intent intent){

    }

    public void onDestroy() {
        //realm.close();
    }

    public void onPause() {

    }

    public void onResume() {
        isVisible = true;

    }
    public void onBackPressed() {

    }



    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

    }

    public void onPostCreate(Bundle savedInstanceState) {

    }

    public void onOptionsItemSelected(MenuItem item) {

    }

    public void onConfigurationChanged(Configuration newConfig) {

    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    public void onSaveInstanceState(Bundle outState){

    }

    public void onCreateOptionsMenu(Menu menu) {

    }

    public void onPrepareOptionsMenu(Menu menu){

    }

    public void onWindowFocusChanged(boolean hasFocus){

    }


}