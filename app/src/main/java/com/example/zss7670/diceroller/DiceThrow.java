package com.example.zss7670.diceroller;

/**
 * Created by zss7670 on 9/4/2015.
 * Object for Dice throw information
 */
public class DiceThrow {
    protected Integer mTotal = 0;
    protected String mSubTitle;
    protected Integer[] mDiceResults;

    public DiceThrow(String subTitle, Integer[] diceResults) {
        mSubTitle = subTitle;
        mDiceResults = diceResults;
        for (Integer i : diceResults) {
            mTotal += i;
        }
    }

}
