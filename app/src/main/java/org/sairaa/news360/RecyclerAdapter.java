package org.sairaa.news360;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {
    private Context ctx;
    ArrayList<News> newsListR;
    public RecyclerAdapter(ArrayList<News> newsList, Context newsFragment) {
        newsListR = newsList;
        ctx = newsFragment;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

        final News news = newsListR.get(position);
        holder.sectionT.setText(news.getSectionName()+"  >>");
        holder.headingT.setText(news.getWebTitle());
        holder.dateT.setText(convertStringToDate(news.getWebPublicationDate()));
        holder.sourceT.setText(news.getTagContributor());
        if(news.getFieldThumbnail() != null)
            holder.imageViewN.setImageBitmap(news.getFieldThumbnail());
        else
            holder.imageViewN.setBackgroundResource(R.drawable.ic_launcher_background);

        holder.imageViewN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ctx,NewsDetails.class);
                intent.putExtra("urlWeb",news.getWebUrl());
//                intent.putExtra("bmp",news.getFieldThumbnail());
                ctx.startActivity(intent);
            }
        });
    }

    private String convertStringToDate(String dateString) {
        return dateString.substring(0,10);
    }


    @Override
    public int getItemCount() {
        return newsListR.size();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageViewN;
        TextView sectionT, headingT,dateT,sourceT;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            sectionT = itemView.findViewById(R.id.section_name);
            imageViewN = itemView.findViewById(R.id.imageView);
            headingT = itemView.findViewById(R.id.heading);
            dateT = itemView.findViewById(R.id.date);
            sourceT = itemView.findViewById(R.id.source);
        }
    }
}
