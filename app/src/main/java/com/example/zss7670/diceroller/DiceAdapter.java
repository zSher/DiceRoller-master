package com.example.zss7670.diceroller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Zeta on 9/4/15.
 */
public class DiceAdapter extends RecyclerView.Adapter {
    private String[] mDataset;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView) v.findViewById(R.id.testCardText);
        }
    }

    public DiceAdapter(String[] data){
        mDataset = data;
    }

    @Override
    public DiceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dice_card, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        //Get element from data set
        //set name
        viewHolder.mTextView.setText("Hi");
    }

    @Override
    public int getItemCount(){
        return
    }
}
