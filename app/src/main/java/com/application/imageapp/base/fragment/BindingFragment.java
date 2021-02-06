package com.application.imageapp.base.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


public abstract class BindingFragment<VM extends FragmentViewModel, B extends ViewDataBinding>
        extends Fragment {

    private B binding;
    private VM viewModel;
    private Bundle savedInstanceState;

    protected abstract VM onCreateViewModel(B binding);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, getLayoutResources(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
        try {
            inflater.inflate(getMenuLayoutResources(), menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_buddy:
                mFragmentNavigation.pushFragment(new BuddyFilterFragment());
                break;
            default:
                break;
        }
        return true;
    }*/


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        binding.setVariable(getVariable(), getUpdatedViewModel());
        binding.executePendingBindings();
        viewModel.onViewCreated();
    }

    public B getBinding() {
        return binding;
    }

    @SuppressWarnings("unchecked")
    private VM getUpdatedViewModel() {
        if (viewModel == null) viewModel = onCreateViewModel(binding);
        else viewModel.updateBinding(binding);
        return viewModel;
    }

    public VM getViewModel() {
        return viewModel;
    }

    public Bundle getSavedInstanceState() {
        return savedInstanceState;
    }

    public void resetViewModel() {
        viewModel = onCreateViewModel(binding);
        getBinding().setVariable(getVariable(), viewModel);
    }

    @Override
    public void onPause() {
        viewModel.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }

    @Override
    public void onDestroyView() {
        viewModel.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        viewModel.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (viewModel!= null){
            viewModel.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getVariable();

    /**
     * Override for set layout resource
     *
     * @return layout resource id
     */
    public abstract int getLayoutResources();

    /**
     * Override for set layout resource
     *
     * @return layout resource id
     */
    public int getMenuLayoutResources() {
        return 0;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (viewModel != null)
            viewModel.setUserVisibleHint(isVisibleToUser);
    }
}