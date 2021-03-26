package com.azikar24.articlya.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.azikar24.articlya.Activities.ArticleDetails;
import com.azikar24.articlya.Models.Article;
import com.azikar24.articlya.R;

import java.util.ArrayList;

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.ViewHolder> {
    private ArrayList<Article> arraylist;
    private Context context;

    public ArticleListAdapter(Context context, ArrayList<Article> arraylist) {
        this.arraylist = arraylist;
        this.context = context;
    }

    @NonNull
    @Override
    public ArticleListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        /**
         * Set a click listener for the view
         */
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClick(holder.getAdapterPosition());
            }
        });
        return holder;
    }

    /**
     * On a card click send the article data to the ArticleDetails Activity
     * @param position : item position in the arraylist
     */
    private void buttonClick(int position){
        Article itemData = arraylist.get(position);
        Intent intent = new Intent(context, ArticleDetails.class);
        intent.putExtra("article", itemData);
        context.startActivity(intent);
    }

    /**
     * Load the data on the view
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull final ArticleListAdapter.ViewHolder holder, final int position) {
        Article itemData = arraylist.get(position);
        holder.row_title.setText(itemData.getTitle());
        holder.row_by.setText(itemData.getBy());
        holder.published_date.setText(itemData.getDate());
        holder.category.setText(itemData.getCategory());
    }

    /**
     * Get how many items in the arraylist
     * @return int : arraylist size
     */
    @Override
    public int getItemCount() {
        return arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView row_title, row_by, published_date, category;
        private CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            row_title = itemView.findViewById(R.id.row_title);
            row_by = itemView.findViewById(R.id.row_by);
            published_date = itemView.findViewById(R.id.published_date);
            category = itemView.findViewById(R.id.category);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
