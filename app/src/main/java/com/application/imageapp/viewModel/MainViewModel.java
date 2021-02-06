package com.application.imageapp.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import com.application.imageapp.adapter.GetGalleryAdapter;
import com.application.imageapp.api.ImageResponse.ApiCallBack;
import com.application.imageapp.api.ImageResponse.ApiManager;
import com.application.imageapp.api.ImageResponse.ApiResponseListener;
import com.application.imageapp.api.ImageResponse.ImageResponse;
import com.application.imageapp.base.activity.ActivityViewModel;
import com.application.imageapp.utils.ApiConstant;
import com.application.imageapp.view.MainActivity;
import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainViewModel extends ActivityViewModel<MainActivity> {
    Context context;
    List<ImageResponse> venueList = new ArrayList<ImageResponse>();
    int pageNo;
    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager layoutManager;
    GetGalleryAdapter getGalleryAdapter;
    ProgressDialog progressDialog;

    public MainViewModel(final MainActivity activity) {
        super(activity);
        this.context = activity;

        layoutManager = new GridLayoutManager(context,2, RecyclerView.VERTICAL,false);
        activity.getBinding().rvImageList.setLayoutManager(layoutManager);
        loading=true;
        click();
        pageNo=1;
        getGallery(pageNo,"10");

    }
    private void click() {
        activity.getBinding().rvImageList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    visibleItemCount = layoutManager.getChildCount();
                    totalItemCount = layoutManager.getItemCount();
                    pastVisiblesItems = layoutManager.findFirstCompletelyVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            pageNo++;
                            getGallery(pageNo, "10");
//
                        }

                    }
                }
            }
        });

    }


    public void getGallery(int pageSize, String limit) {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        ApiManager apiManager = ApiManager.getInstance(context);
        ApiCallBack<List<ImageResponse>> call = new ApiCallBack<List<ImageResponse>>(new ApiResponseListener<List<ImageResponse>>() {
            @Override
            public void onApiSuccess(final List<ImageResponse> response, String apiName) {
                progressDialog.dismiss();
                if (response.size() > 0) {
                    activity.getBinding().rvImageList.setVisibility(View.VISIBLE);
                    if (pageNo > 1) {
                        if (response.size() > 0) {
                            progressDialog.dismiss();
                            venueList.removeAll(response);
                            venueList.addAll(response);
                            getGalleryAdapter.notifyDataSetChanged();
                        }
                    } else if (pageNo == 1) {
                        progressDialog.dismiss();
                        venueList = new ArrayList<>();
                        venueList = response;
                        setRecyclerData();
                    }
                    loading = true;
                } else {
                    loading = false;
                    if (!(pageNo > 1))
                        activity.getBinding().rvImageList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onApiError(String responses, String apiName) {
                progressDialog.dismiss();
            }

            @Override
            public void onApiFailure(String failureMessage, String apiName) {
                progressDialog.dismiss();
            }
        }, ApiConstant.GET_IMAGE, context);
        apiManager.imageLis(call,  String.valueOf(pageSize),limit);
    }

    private void setRecyclerData() {
         getGalleryAdapter = new GetGalleryAdapter(venueList, context);
        activity.getBinding().rvImageList.setAdapter(getGalleryAdapter);
    }

}