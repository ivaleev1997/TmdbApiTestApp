package com.firebasetestapp.tmdbapitestapp.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebasetestapp.tmdbapitestapp.MainActivityViewModel;
import com.firebasetestapp.tmdbapitestapp.R;
import com.firebasetestapp.tmdbapitestapp.data.Status;
import com.firebasetestapp.tmdbapitestapp.ui.bottomsheet.RecyclerListDialogFragment;
import com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerFragment;
import com.firebasetestapp.tmdbapitestapp.ui.pagekeyed.PageKeyedFragment;
import com.firebasetestapp.tmdbapitestapp.ui.positional.PositionalPagedFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements RecyclerListDialogFragment.Listener {

    private String[] recyclers;

    @Inject
    AppViewModelFactory mAppViewModelFactory;

    private MainActivityViewModel mMainActivityViewModel;
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private int currentFragmentId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclers = getResources().getStringArray(R.array.recyclers);
        mMainActivityViewModel = ViewModelProviders.of(this, mAppViewModelFactory).get(MainActivityViewModel.class);
        mMainActivityViewModel.getStatusLiveData().observe(this, status -> {
            if (status == Status.SUCCESS) Toast.makeText(this, "Success loaded data", Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, "Error initial load data. Check network connection", Toast.LENGTH_SHORT).show();
        });
        BottomAppBar bottomAppBar = findViewById(R.id.bottomBar);
        setSupportActionBar(bottomAppBar);
        bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        FloatingActionButton fab = findViewById(R.id.fab);
        //setSupportActionBar(fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());

        startSelectedFragment(mCurrentFragment = MovieRecyclerFragment.newInstance(), MovieRecyclerFragment.class.getName());
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
            RecyclerListDialogFragment recyclerListDialogFragment = RecyclerListDialogFragment.newInstance(currentFragmentId);
            recyclerListDialogFragment.show(getSupportFragmentManager(), "recycler");
        }

        return super.onOptionsItemSelected(item);
    }

    private void startSelectedFragment(Fragment fragment, String tag) {
        if (mFragmentManager == null)
            mFragmentManager = getSupportFragmentManager();

        if (mFragmentManager.findFragmentByTag(tag) != null)
            fragment = mFragmentManager.findFragmentByTag(tag);
        mCurrentFragment = fragment;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    @Override
    public void onRecyclerClicked(int position) {
        //Toast.makeText(this, "position " + position, Toast.LENGTH_SHORT).show();
        switch (recyclers[position]) {
            case "Simple Recycler":
                if (!(mCurrentFragment instanceof MovieRecyclerFragment))
                    startSelectedFragment(mCurrentFragment = MovieRecyclerFragment.newInstance(), MovieRecyclerFragment.class.getName());
                break;
            case "Positional":
                if (!(mCurrentFragment instanceof PositionalPagedFragment))
                    startSelectedFragment(mCurrentFragment = PositionalPagedFragment.newInstance(), PositionalPagedFragment.class.getName());
                break;
            case "PageKeyed":
                if (!(mCurrentFragment instanceof PageKeyedFragment))
                    startSelectedFragment(mCurrentFragment = PageKeyedFragment.newInstance(), PageKeyedFragment.class.getName());
                break;
            default:
                break;
        }
    }

    public void fragmentOnResumed(String fragment) {
        switch (fragment) {
            case "com.firebasetestapp.tmdbapitestapp.ui.movierecycler.MovieRecyclerFragment":
                currentFragmentId = 0;
                break;
            case "com.firebasetestapp.tmdbapitestapp.ui.pagekeyed.PageKeyedFragment":
                currentFragmentId = 2;
                break;
            case "com.firebasetestapp.tmdbapitestapp.ui.positional.PositionalPagedFragment":
                currentFragmentId = 1;
                break;
        }
    }
}
