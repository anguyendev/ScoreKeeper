package com.anguyendev.scorekeeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anguyendev.scorekeeper.R;
import com.anguyendev.scorekeeper.model.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amanda on 7/21/16.
 */
public class PlayerRecyclerViewAdapter extends RecyclerView.Adapter<PlayerRecyclerViewAdapter.ViewHolder> {

    private Context mContext;
    private List<Player> mPlayerList = new ArrayList<>();

    public PlayerRecyclerViewAdapter(Context context, List<Player> players){
        mContext = context;
        mPlayerList = players;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View playerView = inflater.inflate(R.layout.list_view_item, null);
        return new ViewHolder(playerView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = mPlayerList.get(position);
        holder.nameText.setText(player.getName());
        holder.scoreText.setText(String.valueOf(player.getLastScore()));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameText;
        TextView scoreText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameText = (TextView) itemView.findViewById(R.id.tv_name_value);
            scoreText = (TextView) itemView.findViewById(R.id.tv_score_value);
        }
    }
}
