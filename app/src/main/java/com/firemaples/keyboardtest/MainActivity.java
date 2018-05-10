package com.firemaples.keyboardtest;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private InputMethodManager inputMethodManager;

    private View bt_attach;
    private EditText et_message;
    private View view_functionBlock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMethodManager = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));

        setViews();
    }

    private void setViews() {
        bt_attach = findViewById(R.id.bt_attach);
        et_message = findViewById(R.id.et_message);
        view_functionBlock = findViewById(R.id.view_functionBlock);

        bt_attach.setOnClickListener(onClickListener);
        et_message.setOnClickListener(onClickListener);

//        et_message.requestFocus();
//        inputMethodManager.showSoftInput(et_message, InputMethodManager.SHOW_FORCED);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if (id == R.id.bt_attach) {
                toggleFuncBlock();
            } else if (id == R.id.et_message) {
                showFuncBlock(false);
            }
        }
    };

    private void showFuncBlock(boolean show) {
        if (show) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING);
            closeKeyboard();
            view_functionBlock.setVisibility(View.VISIBLE);
        } else {
            view_functionBlock.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view_functionBlock.setVisibility(View.GONE);
                    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                }
            }, 100);
        }
    }

    private void toggleFuncBlock() {
        showFuncBlock(!(view_functionBlock.getVisibility() == View.VISIBLE));
    }

    private void closeKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(et_message.getWindowToken(), 0);
    }
}
