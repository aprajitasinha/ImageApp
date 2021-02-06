package com.application.imageapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.application.imageapp.R;
import com.application.imageapp.api.ImageResponse.ImageResponse;
import com.application.imageapp.databinding.GallerydadaBinding;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class GetGalleryAdapter extends RecyclerView.Adapter<GetGalleryAdapter.ViewHolder> {
    private List<ImageResponse> list;
    private Context context;

    public GetGalleryAdapter(List<ImageResponse> list, Context context) {
        this.list = list;
        this.context = context;
    }
    public void updateSheet(List<ImageResponse> mlist){
        this.list=mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerydada, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
      if (list!=null){
          final ImageResponse subCategoryDatum= list.get(position);
          Log.e("TAG", "onBindViewHolder: "+list.get(position).getDownloadUrl() );
          Glide.with(context).load(list.get(position).getDownloadUrl()).into(holder.binding.thumbnail);
      }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       GallerydadaBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding= DataBindingUtil.bind(itemView);
        }
    }
}
