package com.anguyendev.scorekeeper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.anguyendev.scorekeeper.R;
import com.anguyendev.scorekeeper.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amanda on 7/21/16.
 */
public class PlayerListViewAdapter extends BaseAdapter {

    private Context mContext;
    private List<Player> mPlayerList = new ArrayList<>();
    private static final int NUM_VIEW_TYPES = 1;

    public PlayerListViewAdapter(Context context, List<Player> players){
        mContext = context;
        mPlayerList.addAll(players);
    }

    @Override
    public int getCount() {
        return mPlayerList.size();
    }

    @Override
    public Object getItem(int i) {
        return mPlayerList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;

        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_view_item, null);

            holder = new Holder();
            holder.nameText = (TextView) view.findViewById(R.id.tv_name_value);
            holder.scoreText = (TextView) view.findViewById(R.id.tv_score_value);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }

        holder.nameText.setText(mPlayerList.get(i).getName());
        holder.scoreText.setText(String.valueOf(mPlayerList.get(i).getLastScore()));

        return view;
    }

    @Override
    public int getViewTypeCount() {
        return NUM_VIEW_TYPES;
    }

    @Override
    public boolean isEmpty() {
        return mPlayerList.isEmpty();
    }

    public class Holder {
        TextView nameText;
        TextView scoreText;
    }
}
