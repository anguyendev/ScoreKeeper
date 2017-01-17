package com.anguyendev.scorekeeper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by amanda on 1/16/17.
 */

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    public TextView nameText;
    public TextView scoreText;
    private ICallbackListener callbackListener;

    public interface ICallbackListener {
        void incrementScore(ViewHolder viewHolder, int position);
        void decrementScore(ViewHolder viewHolder, int position);
    }

    public ViewHolder(View itemView) {
        super(itemView);
        nameText = (TextView) itemView.findViewById(R.id.tv_name_value);
        scoreText = (TextView) itemView.findViewById(R.id.tv_score_value);
        itemView.findViewById(R.id.iv_increment_icon).setOnClickListener(this);
        itemView.findViewById(R.id.iv_decrement_icon).setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void setScoreChangeListener(ICallbackListener callbackListener) {
        this.callbackListener = callbackListener;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_decrement_icon:
                if (callbackListener != null) {
                    callbackListener.decrementScore(ViewHolder.this, getAdapterPosition());
                }
                break;
            case R.id.iv_increment_icon:
                if (callbackListener != null) {
                    callbackListener.incrementScore(ViewHolder.this, getAdapterPosition());
                }
                break;
            default:
                break;
        }
    }
}
