package com.example.zss7670.diceroller;

/**
 * Created by FractalVagus on 9/4/2015.
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

    public DiceThrow(String subTitle, Integer diceResult){
        mSubTitle = subTitle;
        mDiceResults = new Integer[]{diceResult};
        mTotal = diceResult;
    }
}
