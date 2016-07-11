package com.example.user.menutestapp;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

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

    ArrayList<Integer> buttonPressedBackStack;
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
        buttonPressedBackStack = new ArrayList<Integer>();

        buttonPressedBackStack.add(BTN_ALL);

        if(savedInstanceState != null) {
            buttonPressedBackStack = savedInstanceState.getIntegerArrayList("backStack");
            int index = buttonPressedBackStack.size() - 1;
            switch (buttonPressedBackStack.get(index)) {
                case BTN_ALL:
                    btnAll.callOnClick();
                    break;
                case BTN_ITALY:
                    btnItaly.callOnClick();
                    break;
                case BTN_PANAZIYA:
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
        outState.putIntegerArrayList("backStack", buttonPressedBackStack);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        buttonPressedBackStack.remove(buttonPressedBackStack.size() - 1);
        int index = buttonPressedBackStack.size() - 1;
        if(index >= 0) {
            switch (buttonPressedBackStack.get(index)) {
                case BTN_ALL:
                    btnAll.callOnClick();
                break;
                case BTN_ITALY:
                    btnItaly.callOnClick();
                    break;
                case BTN_PANAZIYA:
                    btnPanaziya.callOnClick();
                    break;
                default:
                    break;
            }
        } else {
            this.finish();
        }
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

        if(buttonPressedBackStack.lastIndexOf(BTN_ALL) != (buttonPressedBackStack.size() - 1)) {
            buttonPressedBackStack.add(BTN_ALL);
        }

        pressedButton = BTN_ALL;
        btnItaly.setTextColor(getResources().getColor(R.color.colorTextButton));
        btnPanaziya.setTextColor(getResources().getColor(R.color.colorTextButton));
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

        if(buttonPressedBackStack.lastIndexOf(BTN_ITALY) != (buttonPressedBackStack.size() - 1)) {
            buttonPressedBackStack.add(BTN_ITALY);
        }

        pressedButton = BTN_ITALY;
        btnAll.setTextColor(getResources().getColor(R.color.colorTextButton));
        btnPanaziya.setTextColor(getResources().getColor(R.color.colorTextButton));
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

        if(buttonPressedBackStack.lastIndexOf(BTN_PANAZIYA) != (buttonPressedBackStack.size() - 1)) {
            buttonPressedBackStack.add(BTN_PANAZIYA);
        }

        pressedButton = BTN_PANAZIYA;
        btnAll.setTextColor(getResources().getColor(R.color.colorTextButton));
        btnItaly.setTextColor(getResources().getColor(R.color.colorTextButton));
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
            fragmentTrans.commit();
        }
    };
}
