package com.firebasetestapp.tmdbapitestapp.ui.bottomsheet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebasetestapp.tmdbapitestapp.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * <p>A fragment that shows a list of items as a modal bottom sheet.</p>
 * <p>You can show this modal bottom sheet from your activity like this:</p>
 * <pre>
 *     RecyclerListDialogFragment.newInstance(30).show(getSupportFragmentManager(), "dialog");
 * </pre>
 * <p>You activity (or fragment) needs to implement {@link RecyclerListDialogFragment.Listener}.</p>
 */
public class RecyclerListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    private static final String ARG_CURRENT_FRAGMENT = "current_fragment";

    private String[] dataSourceArray;
    private Listener mListener;
    private int currentFragmentId = 0;

    // TODO: Customize parameters
    public static RecyclerListDialogFragment newInstance(int currentFragmentId) {
        final RecyclerListDialogFragment fragment = new RecyclerListDialogFragment();
        final Bundle args = new Bundle();
        args.putInt(ARG_CURRENT_FRAGMENT, currentFragmentId);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (getArguments() != null)
            currentFragmentId = getArguments().getInt(ARG_CURRENT_FRAGMENT, 0);

        return inflater.inflate(R.layout.bottom_sheet_dialog_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        dataSourceArray  = getResources().getStringArray(R.array.recyclers);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.diff_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new RecyclerAdapter(dataSourceArray));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        } else {
            mListener = (Listener) context;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    public interface Listener {
        void onRecyclerClicked(int position);
    }


    private class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        private final int mItemCount;
        private final String[] dataSources;

        RecyclerAdapter(String[] dataSources) {
            mItemCount = dataSources.length;
            this.dataSources = dataSources;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.text.setText(dataSources[position]);
            if (position == currentFragmentId)
                holder.imageView.setImageResource(R.drawable.ic_bottom_sheet_selected);
        }

        @Override
        public int getItemCount() {
            return mItemCount;
        }

        private class ViewHolder extends RecyclerView.ViewHolder {

            final TextView text;
            final ImageView imageView;

            ViewHolder(LayoutInflater inflater, ViewGroup parent) {
                // TODO: Customize the item layout
                super(inflater.inflate(R.layout.bottom_sheet_dialog_item, parent, false));
                text = (TextView) itemView.findViewById(R.id.text);
                imageView = (ImageView) itemView.findViewById(R.id.bottom_sheet_image_item);
                text.setOnClickListener(v -> {
                    if (mListener != null) {
                        mListener.onRecyclerClicked(getAdapterPosition());
                        dismiss();
                    }
                });
            }
        }
    }

}
