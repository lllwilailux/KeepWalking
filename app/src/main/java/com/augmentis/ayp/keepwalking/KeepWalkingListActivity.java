package com.augmentis.ayp.keepwalking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

public class KeepWalkingListActivity extends FragmentActivity {

    private RecyclerView keepWalkingRecyclerView;

    private KeepWalkingAdapter adapter;

    public Button addBtn;

    protected static final String TAG = "KEEP_WALKING__LIST";

    private Integer[] kPos;

    private static final int REQUEST_UPDATE_KW = 191;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_keep_walking);

        addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KeepWalkingListActivity.this,KeepWalkingActivity.class);
                startActivity(intent);
            }
        });



        keepWalkingRecyclerView = (RecyclerView) findViewById(R.id.keep_walking_recycler_view);
        keepWalkingRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        updateUI();
    }



    /**
     * Update UI
     */
    private void updateUI(){
        KeepWalkingLab keepWalkingLab =  KeepWalkingLab.getInstance(this);
        List<KeepWalking> kpl = keepWalkingLab.getKeepWalking();

        Log.d(TAG,"create kpl : " + kpl);

        if (adapter == null) {
            adapter = new KeepWalkingAdapter(kpl,this);
            keepWalkingRecyclerView.setAdapter(adapter);
        } else {
            //_adapter.notifyDataSetChanged();
            if (kPos != null) {
                for(Integer pos : kPos) {
                    adapter.notifyItemChanged(pos);
                    Log.d(TAG, "notify change at " + pos);
                }
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Resume list");
        updateUI();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_UPDATE_KW) {
            if (resultCode == Activity.RESULT_OK) {
                kPos = (Integer[]) data.getExtras().get("position");
                Log.d(TAG, "Get crimePos = " + kPos );
            }
            Log.d(TAG, "Return from CrimeFragment");
        }
    }

    private class KeepWalkingHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView _titleTextView;
        public TextView  _dateTextView;

        KeepWalking _keep_walking;
        int _position;

        public KeepWalkingHolder(View itemView) {
            super(itemView);

            _titleTextView = (TextView) itemView.findViewById(R.id.list_item_title_text_view);
            _dateTextView = (TextView) itemView.findViewById(R.id.list_item_date_text_view);

            itemView.setOnClickListener(this);
        }

        public void bind(KeepWalking keep_walking, int position) {
            _keep_walking = keep_walking;
            _position = position;
            _titleTextView.setText(_keep_walking.getTitle());
            _dateTextView.setText(_keep_walking.getDate().toString());
        }

        @Override
        public void onClick(View view) {

        }
    }


    private class KeepWalkingAdapter extends RecyclerView.Adapter<KeepWalkingHolder> {

        private List<KeepWalking> keep_walking;
        private int _viewCreatingCount;
        private Context context;

        public KeepWalkingAdapter(List<KeepWalking> keepwalking, Context context) {
            this.context = context;
            this.keep_walking = keepwalking;
        }


        @Override
        public KeepWalkingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            _viewCreatingCount++;
            Log.d(TAG, "Create view holder for CrimeList: creating view time = "+ _viewCreatingCount);

            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View v = layoutInflater.inflate(R.layout.list_item_keep_walking, parent,false);

            return new KeepWalkingHolder(v);
        }

        @Override
        public void onBindViewHolder(KeepWalkingHolder holder, int position) {

            Log.d(TAG, "Bind view holder for CrimeList : position = " + position);

            KeepWalking crime = keep_walking.get(position);
            holder.bind(crime, position);
        }

        @Override
        public int getItemCount() {
            return keep_walking.size();
        }
    }


}
