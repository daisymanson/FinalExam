package com.example.iloventub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main7Activity extends AppCompatActivity {
    private Button IdxBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        IdxBtn = (Button)findViewById(R.id.IdxBtn);
        IdxBtn.setOnClickListener(btnIdxBtnListener);
    }
    // 返回首頁
    private Button.OnClickListener btnIdxBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();

        }
    };
}
