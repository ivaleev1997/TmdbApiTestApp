package com.firebasetestapp.tmdbapitestapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.ui.bottomsheet.RecyclerListDialogFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements RecyclerListDialogFragment.Listener {

    private final String[] recyclers = getResources().getStringArray(R.array.recyclers);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomAppBar bottomAppBar = findViewById(R.id.bottomBar);
        setSupportActionBar(bottomAppBar);
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        FloatingActionButton fab = findViewById(R.id.fab);
        //setSupportActionBar(fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            RecyclerListDialogFragment recyclerListDialogFragment = RecyclerListDialogFragment.newInstance(1);
            recyclerListDialogFragment.show(getSupportFragmentManager(), "recycler");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRecyclerClicked(int position) {
        //Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
        switch (recyclers[position]) {
            case "Positional":
                break;
            case "PageKeyed":
                break;
            case "ItemKeyed":
                break;
            default:
                break;
        }
    }
}
