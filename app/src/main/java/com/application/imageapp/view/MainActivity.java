package com.application.imageapp.view;

import com.application.imageapp.BR;
import com.application.imageapp.R;
import com.application.imageapp.base.activity.BindingActivity;
import com.application.imageapp.databinding.ActivityMainBinding;
import com.application.imageapp.viewModel.MainViewModel;

public class MainActivity extends BindingActivity<ActivityMainBinding, MainViewModel> {

        @Override
        public MainViewModel onCreate() {
            return new MainViewModel(this);
        }

        @Override
        public int getVariable() {
            return BR.viewModel;
        }

        @Override
        public int getLayoutId() {
            return R.layout.activity_main;
        }
    }

