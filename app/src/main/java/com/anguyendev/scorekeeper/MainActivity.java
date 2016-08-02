package com.anguyendev.scorekeeper;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.anguyendev.scorekeeper.adapters.PlayerRecyclerViewAdapter;
import com.anguyendev.scorekeeper.model.Player;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String STATE_PLAYER_LIST = "playerList";
    private RecyclerView mRecyclerView;
    private PlayerRecyclerViewAdapter mRecyclerAdapter;
    private ArrayList<Player> mPlayerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: Is this actually where I should be restoring this?
        if (savedInstanceState != null) {
            mPlayerList = (ArrayList) savedInstanceState.getSerializable(STATE_PLAYER_LIST);
        }
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerAdapter = new PlayerRecyclerViewAdapter(getApplicationContext(), mPlayerList);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO remove stupid hardcode
                mPlayerList.add(new Player("Player 1", 99));
                mRecyclerAdapter.notifyItemInserted(mPlayerList.size()-1);
            }
        });
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPlayerList = (ArrayList) savedInstanceState.getSerializable(STATE_PLAYER_LIST);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putSerializable(STATE_PLAYER_LIST, mPlayerList);
        super.onSaveInstanceState(outState);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
