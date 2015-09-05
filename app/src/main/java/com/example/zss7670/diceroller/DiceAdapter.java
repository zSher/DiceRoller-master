package com.example.zss7670.diceroller;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Zeta on 9/4/15.
 */
public class DiceAdapter extends RecyclerView.Adapter<DiceAdapter.DiceViewHolder> {
    private List<DiceThrow> mDiceThrows;
    private Context mContext;

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

    public DiceAdapter(List<DiceThrow> data, Context context){
        mDiceThrows = data;
        mContext = context;
    }

    @Override
    public DiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.dice_card, parent, false);

        DiceViewHolder vh = new DiceViewHolder(v);
        return vh;
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

        setAnimation(diceViewHolder.itemView, i);
    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position == 0)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
        }
    }

    @Override
    public int getItemCount(){
        return mDiceThrows.size();
    }

    public void addThrow(DiceThrow diceThrow){
        mDiceThrows.add(0, diceThrow);
        notifyDataSetChanged();
    }
}
