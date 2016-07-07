package com.example.user.menutestapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    final static String FRAG_1 = "FRAGMENT_1";
    final static String FRAG_2 = "FRAGMENT_2";
    final static String FRAG_3 = "FRAGMENT_3";
    final static int BTN_ALL = 1;
    final static int BTN_ITALY = 2;
    final static int BTN_PANAZIYA = 3;

    FragmentMenuAll fragmentMenuAll;
    FragmentMenuItaly fragmentMenuItaly;
    FragmentMenuPan fragmentMenuPan;
    FragmentTransaction fragmentTrans;
    FragmentManager fragmentManager;

    Button btnAll;
    Button btnItaly;
    Button btnPanaziya;

    int pressedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentMenuAll = new FragmentMenuAll();
        fragmentMenuItaly = new FragmentMenuItaly();
        fragmentMenuPan = new FragmentMenuPan();

        btnAll = (Button) findViewById(R.id.btnAll);
        btnItaly = (Button) findViewById(R.id.btnItaly);
        btnPanaziya = (Button) findViewById(R.id.btnPan);

        btnAll.setOnClickListener(listenBtn);
        btnItaly.setOnClickListener(listenBtn);
        btnPanaziya.setOnClickListener(listenBtn);

        if(savedInstanceState != null) {
            pressedButton = savedInstanceState.getInt("state");
            switch (pressedButton) {
                case 1:
                    btnAll.callOnClick();
                    break;
                case 2:
                    btnItaly.callOnClick();
                    break;
                case 3:
                    btnPanaziya.callOnClick();
                    break;
                default:
                    break;
            }
        } else {
            btnAll.callOnClick();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("state", pressedButton);
        super.onSaveInstanceState(outState);
    }

    public void clickButtonAll() {

        FragmentMenuItaly frMenuItaly;
        FragmentMenuPan frMenuPan;

        frMenuItaly = (FragmentMenuItaly) fragmentManager.findFragmentByTag(FRAG_2);
        if (frMenuItaly != null) {
            fragmentTrans.remove(fragmentMenuItaly);
        }

        frMenuPan = (FragmentMenuPan) fragmentManager.findFragmentByTag(FRAG_3);
        if (frMenuPan != null) {
            fragmentTrans.remove(fragmentMenuPan);
        }

        fragmentTrans.replace(R.id.fragment_cont, fragmentMenuAll);

        fragmentTrans.addToBackStack(null);
        pressedButton = BTN_ALL;
        btnItaly.setTextColor(Color.parseColor("#767e7e"));
        btnPanaziya.setTextColor(Color.parseColor("#767e7e"));
        btnAll.setTextColor(Color.WHITE);
    }

    public void clickButtonItaly() {

        FragmentMenuAll frMenuAll;
        FragmentMenuPan frMenuPan;

        frMenuAll = (FragmentMenuAll) fragmentManager.findFragmentByTag(FRAG_1);
        if (frMenuAll != null) {
            fragmentTrans.remove(fragmentMenuAll);
        }

        frMenuPan = (FragmentMenuPan) fragmentManager.findFragmentByTag(FRAG_3);
        if (frMenuPan != null) {
            fragmentTrans.remove(fragmentMenuPan);
        }

        fragmentTrans.replace(R.id.fragment_cont, fragmentMenuItaly);

        fragmentTrans.addToBackStack(null);
        pressedButton = BTN_ITALY;
        btnAll.setTextColor(Color.parseColor("#767e7e"));
        btnPanaziya.setTextColor(Color.parseColor("#767e7e"));
        btnItaly.setTextColor(Color.WHITE);
    }

    public void clickButtonPanaziya() {

        FragmentMenuAll frMenuAll;
        FragmentMenuItaly frMenuItaly;

        frMenuAll = (FragmentMenuAll) fragmentManager.findFragmentByTag(FRAG_1);
        if (frMenuAll != null) {
            fragmentTrans.remove(fragmentMenuAll);
        }

        frMenuItaly = (FragmentMenuItaly) fragmentManager.findFragmentByTag(FRAG_2);
        if (frMenuItaly != null) {
            fragmentTrans.remove(fragmentMenuItaly);
        }

        fragmentTrans.replace(R.id.fragment_cont, fragmentMenuPan);

        fragmentTrans.addToBackStack(null);
        pressedButton = BTN_PANAZIYA;
        btnAll.setTextColor(Color.parseColor("#767e7e"));
        btnItaly.setTextColor(Color.parseColor("#767e7e"));
        btnPanaziya.setTextColor(Color.WHITE);
    }

    View.OnClickListener listenBtn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            fragmentManager = getFragmentManager();
            fragmentTrans = fragmentManager.beginTransaction();
            switch (v.getId()) {
                case R.id.btnAll:
                    clickButtonAll();
                    break;
                case R.id.btnItaly:
                    clickButtonItaly();
                    break;
                case R.id.btnPan:
                    clickButtonPanaziya();
                    break;
                default:
                    break;
            }
            fragmentTrans.addToBackStack(null);
            fragmentTrans.commit();
        }
    };
}
