package com.anguyendev.scorekeeper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.anguyendev.scorekeeper.adapters.PlayerListAdapter;
import com.anguyendev.scorekeeper.model.Player;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddPlayerDialogFragment.IAddPlayerListener {

    private static final String PLAYER_LIST_TAG = "playerList";
    private static final String SCORE_ALL_DIALOG_TAG = "scoreAllDialog";
    private static final String ADD_PLAYER_DIALOG_TAG = "addPlayerDialog";
    private static final String EDIT_SCORE_DIALOG_TAG = "editScoreDialog";

    private RecyclerView mRecyclerView;
    private PlayerListAdapter mRecyclerAdapter;
    private AddPlayerDialogFragment mAddPlayerDialogFrag;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayoutManager = new LinearLayoutManager(this);

        // This means the activity is being restored
        ArrayList<Player> playerList = new ArrayList<>();
        if (savedInstanceState != null) {
            playerList = savedInstanceState.getParcelableArrayList(PLAYER_LIST_TAG);
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerAdapter = new PlayerListAdapter(playerList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAddPlayerDialogFrag = new AddPlayerDialogFragment();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (outState != null) {
            outState.putParcelableArrayList(PLAYER_LIST_TAG, mRecyclerAdapter.getPlayerList());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_add_player:
                if (mAddPlayerDialogFrag != null) {
                    mAddPlayerDialogFrag.show(getFragmentManager(), ADD_PLAYER_DIALOG_TAG);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void requestAddPlayer(String playerName) {
        if(!mRecyclerAdapter.requestAddPlayer(playerName)) {
            Toast.makeText(this, getString(R.string.duplicate_names_message), Toast.LENGTH_SHORT).show();
        }
    }
}
