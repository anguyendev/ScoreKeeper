package com.anguyendev.scorekeeper;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.AutoCompleteTextView;

/**
 * Created by amanda on 1/16/17.
 */

public class AddPlayerDialogFragment extends DialogFragment {

    private IAddPlayerListener mAddPlayerListener;

    public interface IAddPlayerListener {
        void requestAddPlayer(String playerName);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof IAddPlayerListener) {
            mAddPlayerListener = (IAddPlayerListener)context;
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstance) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setView(getActivity().getLayoutInflater().inflate(R.layout.dialog_add_player, null));
        builder.setTitle(getText(R.string.add_player_dialog_title));
        builder.setPositiveButton(getString(R.string.dialog_ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog dialog = (Dialog)dialogInterface;
                AutoCompleteTextView playerNameTextView =
                        (AutoCompleteTextView)dialog.findViewById(R.id.actv_player_name);
                if (mAddPlayerListener != null) {
                    mAddPlayerListener.requestAddPlayer(playerNameTextView.getText().toString());
                }
            }
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), null);
        return builder.create();
    }
}
