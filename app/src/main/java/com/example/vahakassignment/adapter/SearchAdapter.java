package com.example.vahakassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.vahakassignment.ItemClickListener;
import com.example.vahakassignment.R;
import com.example.vahakassignment.models.Page;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    ArrayList<Page> data;
    Context context;
    ItemClickListener itemClickListener;
  public   SearchAdapter(Context context ,  ArrayList<Page> data, ItemClickListener itemClickListener)
    {
         this.context = context;
         this.data = data;
         this.itemClickListener = itemClickListener;
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new SearchAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inflater_adapter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {

      if(data!=null && data.size()>0) {
          if (data.get(position).getTitle() != null && !data.get(position).getTitle().equals(""))
              holder.tvHead.setText(data.get(position).getTitle());
          if(data.get(position).getTerms()!=null) {
              if (data.get(position).getTerms().getDescription() != null && data.get(position).getTerms().getDescription().size() > 0)
                  holder.tvDesc.setText(data.get(position).getTerms().getDescription().get(0));
          }

          if(data.get(position).getThumbnail()!=null) {

              if (data.get(position).getThumbnail().getSource() != null && !data.get(position).getThumbnail().getSource().equals("")) {
                  Glide.with(context).load(data.get(position).getThumbnail().getSource())
                          .placeholder(ContextCompat.getDrawable(context, R.color.black)).centerCrop()
                          .into(holder.ivImage);
              }
          }

          holder.rlMain.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {

                  itemClickListener.onClickItem(position);
              }
          });

      }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvHead, tvDesc;
        ImageView ivImage;
        RelativeLayout rlMain;
        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.iv_image);
            tvDesc = itemView.findViewById(R.id.tv_description);
            tvHead = itemView.findViewById(R.id.tv_title);
            rlMain = itemView.findViewById(R.id.rl_main);
        }
    }
}
