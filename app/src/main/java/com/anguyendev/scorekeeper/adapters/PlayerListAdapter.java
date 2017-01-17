package com.anguyendev.scorekeeper.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anguyendev.scorekeeper.ViewHolder;
import com.anguyendev.scorekeeper.R;
import com.anguyendev.scorekeeper.model.Player;

import java.util.ArrayList;

/**
 * Created by amanda on 7/21/16.
 */
public class PlayerListAdapter extends RecyclerView.Adapter<ViewHolder> implements ViewHolder.ICallbackListener{

    protected ArrayList<Player> mPlayerList = new ArrayList<>();

    private enum ScoreModifyType {
        INCREMENT,
        DECREMENT
    }

    public PlayerListAdapter(ArrayList<Player> playerList){
        mPlayerList = playerList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext().
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View playerView = inflater.inflate(R.layout.player_list_item, parent, false);
        ViewHolder viewholder = new ViewHolder(playerView);
        viewholder.setScoreChangeListener(this);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Player player = mPlayerList.get(position);
        holder.nameText.setText(player.getName());
        holder.scoreText.setText(String.valueOf(player.getScore()));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return mPlayerList.size();
    }


    public boolean requestAddPlayer(String playerName) {
        // Check for duplicate name
        boolean isDuplicate = false;
        for (Player p : mPlayerList) {
            if (playerName.equalsIgnoreCase(p.getName())) {
                isDuplicate = true;
                break;
            }
        }

        if(!isDuplicate) {
            mPlayerList.add(new Player(playerName));
            notifyItemInserted(mPlayerList.size() - 1);
            return true;
        }
        return false;
    }

    public ArrayList<Player> getPlayerList() {
        return mPlayerList;
    }

    @Override
    public void incrementScore(ViewHolder viewHolder, int position) {
        modifyScore(viewHolder, position, ScoreModifyType.INCREMENT);
    }

    @Override
    public void decrementScore(ViewHolder viewHolder, int position) {
        modifyScore(viewHolder, position, ScoreModifyType.DECREMENT);
    }

    private void modifyScore(ViewHolder viewHolder, int position, ScoreModifyType modifyType) {

        if (position < mPlayerList.size()) {

            Player player = mPlayerList.get(position);

            int newScore;
            switch (modifyType) {
                case INCREMENT:
                    newScore = player.getScore() + 1;
                    break;
                case DECREMENT:
                    newScore = player.getScore() - 1;
                    break;
                default:
                    newScore = player.getScore();
                    break;
            }

            mPlayerList.get(position).setScore(newScore);
            viewHolder.scoreText.setText(String.valueOf(newScore));
        }


    }
}
