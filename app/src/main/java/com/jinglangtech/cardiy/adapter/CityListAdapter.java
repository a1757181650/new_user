package com.jinglangtech.cardiy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jinglangtech.cardiy.R;
import com.jinglangtech.cardiy.entity.CityResult;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;


public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.QueryHolder> {

    private Context mContext;
    private List<CityResult> cityResultList;

    public CityListAdapter(Context ctx, List<CityResult> CityResults) {
        this.mContext = ctx;
        this.cityResultList = CityResults;
    }

    @Override
    public QueryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_city, parent, false);
        QueryHolder queryHolder = new QueryHolder(itemView);
        return queryHolder;
    }

    @Override
    public void onBindViewHolder(QueryHolder holder, final int position) {
        CityResult cityResult = cityResultList.get(position);
        holder.tvCityName.setText(cityResult.getShortName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityResultList != null ? cityResultList.size() : 0;
    }

    public static class QueryHolder extends RecyclerView.ViewHolder {

        private TextView tvCityName;
        private LinearLayout linearLayout;
        public QueryHolder(View itemView) {
            super(itemView);
            tvCityName = itemView.findViewById(R.id.tv_city_title);
            linearLayout = itemView.findViewById(R.id.ll_city_item);
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