package com.example.iloventub;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main2Activity extends AppCompatActivity {

    private Button IdxBtn;
    ListView Intro;
    SQLiteDatabase db;
    static final String DB_NAME="NTUB";
    static final String TB_NAME="Introduce";
    static final String DB_PATH = "/data/data/com.example.iloventub/databases/";
    static final String[] FROM=new String[] {"title","content"};

    Cursor curA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intro=(ListView)findViewById(R.id.Intro);

        IdxBtn = (Button)findViewById(R.id.IdxBtn);
        IdxBtn.setOnClickListener(btnIdxBtnListener);
        //finish();

        //資料庫開啟
        try{
            InputStream is = getBaseContext().getAssets().open(DB_NAME);
            OutputStream os = new FileOutputStream(DB_PATH + DB_NAME);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer))>0){
                os.write(buffer, 0,length);
            }
            os.flush();
            os.close();
            is.close();
        }catch (Exception e){
            e.printStackTrace();
        }

        // 開啟或建立資料庫
        db = openOrCreateDatabase(DB_NAME,  Context.MODE_PRIVATE, null);

        curA = db.rawQuery("SELECT * FROM " + TB_NAME , null);
        if(curA != null && curA.getCount()>=0 ){
            SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
                    R.layout.item3, curA,
                    FROM,
                    new int[] {R.id.title,R.id.content},
                    0);
            Intro.setAdapter(adapter);
            adapter.changeCursor(curA);
        }else{
            Toast.makeText(this, "DB NO DATA", Toast.LENGTH_SHORT).show();
        }

    }

    private Button.OnClickListener btnIdxBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {

            //System.exit(0);
            finish();

        }
    };
}
