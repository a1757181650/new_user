package com.jinglangtech.cardiy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.HeadNewsResult;
import com.jinglangtech.cardiy.entity.news.NewsList;
import com.squareup.picasso.Picasso;

import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class ItemNewsAdapter extends RecyclerView.Adapter<ItemNewsAdapter.QueryHolder> {

    private Context mContext;
    private List<NewsList> mHeadLists;

    public ItemNewsAdapter(Context ctx, List<NewsList> headLists) {
        this.mContext = ctx;
        this.mHeadLists = headLists;
    }

    public ItemNewsAdapter(Context ctx) {
        this.mContext = ctx;
    }

    @Override
    public QueryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        QueryHolder queryHolder = new QueryHolder(itemView);
        return queryHolder;
    }

    @Override
    public void onBindViewHolder(QueryHolder holder, final int position) {
        NewsList headNewsResult = mHeadLists.get(position);
        holder.itemTvTitle.setText(headNewsResult.getTitle());
        holder.itemTvDate.setText(headNewsResult.getCreatetime());
        holder.itemTvViewNum.setText(String.format("%d", headNewsResult.getViewNum()));
        holder.llNewsContent.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position);
            }
        });
        Picasso.get().load(headNewsResult.getLogo()).placeholder(R.mipmap.atavar).into(holder.itemHeadNewsImg);
    }

    @Override
    public int getItemCount() {
        return mHeadLists != null ? mHeadLists.size() : 0;
    }

    public static class QueryHolder extends RecyclerView.ViewHolder {

        private ImageView itemHeadNewsImg;
        private TextView itemTvTitle;
        private TextView itemTvDate;
        private TextView itemTvViewNum;
        private LinearLayout llNewsContent;

        public QueryHolder(View itemView) {
            super(itemView);
            itemHeadNewsImg = itemView.findViewById(R.id.item_img_icon);
            itemTvTitle = itemView.findViewById(R.id.item_tv_title);
            itemTvDate = itemView.findViewById(R.id.item_tv_date);
            itemTvViewNum = itemView.findViewById(R.id.item_tv_view_num);
            llNewsContent = itemView.findViewById(R.id.ll_news_content);
        }
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
}