package com.example.zss7670.diceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DiceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Spinner mNumberSpinner;
    private Spinner mSideSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dice);

        mNumberSpinner = (Spinner) findViewById(R.id.dieNumberSpinner);
        ArrayAdapter<CharSequence> numberAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.die_number_array,
                        android.R.layout.simple_spinner_dropdown_item);
        numberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mNumberSpinner.setAdapter(numberAdapter);

        mSideSpinner = (Spinner) findViewById(R.id.dieSideSpinner);
        ArrayAdapter<CharSequence> sideAdapter =
                ArrayAdapter.createFromResource(this,
                        R.array.die_sides_array,
                        android.R.layout.simple_spinner_dropdown_item);
        sideAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSideSpinner.setAdapter(sideAdapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<DiceThrow> dice = new ArrayList<>();

        mAdapter = new DiceAdapter(dice, getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);


        Button rollBtn = (Button) findViewById(R.id.rollBtn);
        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer numberOfDie = Integer.parseInt((String)mNumberSpinner.getSelectedItem());
                Integer numberOfSides = Integer.parseInt((String) mSideSpinner.getSelectedItem());
                Integer[] dice = new Integer[numberOfDie];
                Random rand = new Random();
                for (int i = 0; i < dice.length; i++){
                    dice[i] = rand.nextInt(numberOfSides) + 1;
                }

                String subTitleText = "You threw: " + numberOfDie + "d" + numberOfSides;

                DiceThrow diceThrow = new DiceThrow(subTitleText, dice);
                mAdapter.addThrow(diceThrow);
                mRecyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dice, menu);
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
