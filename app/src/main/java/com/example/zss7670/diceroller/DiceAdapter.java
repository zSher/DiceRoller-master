package com.example.zss7670.diceroller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zss7670 on 9/4/15.
 * Adapter for Recycler view to show "dice throw cards"
 */
public class DiceAdapter extends RecyclerView.Adapter<DiceAdapter.DiceViewHolder> {
    private List<DiceThrow> mDiceThrows;

    public static class DiceViewHolder extends RecyclerView.ViewHolder {
        public TextView mTitleText;
        public TextView mSubTitleText;
        public TextView mDiceResultsText;

        public DiceViewHolder(View v) {
            super(v);
            mTitleText = (TextView) v.findViewById(R.id.titleText);
            mSubTitleText = (TextView) v.findViewById(R.id.subText);
            mDiceResultsText = (TextView) v.findViewById(R.id.diceResults);
        }
    }

    public DiceAdapter(List<DiceThrow> data){
        mDiceThrows = data;
    }

    @Override
    public DiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dice_card, parent, false);

        return new DiceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DiceViewHolder diceViewHolder, int i) {
        DiceThrow testData = mDiceThrows.get(i);
        diceViewHolder.mTitleText.setText("Total: " + testData.mTotal);
        diceViewHolder.mSubTitleText.setText(testData.mSubTitle);
        String resultText = "";
        for (Integer dieResult: testData.mDiceResults) {
            resultText += dieResult + " ";
        }
        diceViewHolder.mDiceResultsText.setText(resultText);
    }

    @Override
    public int getItemCount(){
        return mDiceThrows.size();
    }

    public void addThrow(DiceThrow diceThrow){
        mDiceThrows.add(0, diceThrow);
        notifyItemInserted(0);
    }
}
