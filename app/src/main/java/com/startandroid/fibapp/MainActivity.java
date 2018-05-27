package com.startandroid.fibapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public final class MainActivity extends AppCompatActivity {
    private final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        final Button searchButton = findViewById(R.id.searching_button);
        final RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        final FibonacciAdapter fibonacciAdapter = new FibonacciAdapter();
        recyclerView.setAdapter(fibonacciAdapter);

        recyclerView.addOnScrollListener(new FirstVisiblePosition(linearLayoutManager) {
            @Override
            public void firstVisiblePositionChanged(int firstVisibleNumber) {
                editText.setText(Integer.toString(firstVisibleNumber));
            }
        });

        final PositionTester positionTester = new PositionTester();
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final int TOAST_RECOURSE = positionTester.getStringRecourseByEnteredString(editText.getText().toString());
                if (TOAST_RECOURSE == R.string.no_error) {
                    final int OFFSET = 0;
                    final int POSITION = Integer.parseInt(editText.getText().toString());
                    linearLayoutManager.scrollToPositionWithOffset(POSITION, OFFSET);
                } else {
                    ToastShowing.postToastMessage(MainActivity.this, TOAST_RECOURSE);
                }
            }
        });
    }
}


