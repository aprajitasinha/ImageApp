package com.application.imageapp.base.fragment;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


public abstract class FragmentViewModel<F extends BindingFragment, B extends ViewDataBinding>
        extends BaseObservable {

    private F fragment;
    private B binding;
    private Activity activity;

    public FragmentViewModel(F fragment, B binding) {
        this.fragment = fragment;
        this.binding = binding;
        this.activity = this.fragment.getActivity();
        initialize(binding);
    }

    protected abstract void initialize(B binding);

    public F getFragment() {
        return fragment;
    }

    public Fragment getParentFragment() {
        return fragment.getParentFragment();
    }

    public B getBinding() {
        return binding;
    }

    public Activity getActivity() {
        return activity;
    }

    public void updateBinding(B binding) {
        this.binding = binding;
        initialize(binding);
    }

    /**
     * Fragment lifecycle
     */
    public void onViewCreated() {

    }

    public void onDestroy() {

    }

    public boolean setUserVisibleHint(boolean isVisibleToUser) {
        return  isVisibleToUser;
    }

    public void onPause() {

    }

    public void onResume() {

    }

    public void onDestroyView() {
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }

    public boolean onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        return false;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        return true;
    }



}