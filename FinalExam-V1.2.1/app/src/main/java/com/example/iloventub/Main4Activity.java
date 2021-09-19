package com.example.iloventub;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main4Activity extends AppCompatActivity {

    private Button IdxBtn;

    ListView Traff;
    SQLiteDatabase db;
    static final String DB_NAME="NTUB";
    static final String TB_NAME="Traffic";
    static final String DB_PATH = "/data/data/com.example.iloventub/databases/";
    static final String[] FROM=new String[] {"way","details"};

    Cursor curA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Traff=(ListView)findViewById(R.id.Traff);

        IdxBtn = (Button)findViewById(R.id.IdxBtn);
        IdxBtn.setOnClickListener(btnIdxBtnListener);

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
                    R.layout.item4, curA,
                    FROM,
                    new int[] {R.id.way,R.id.details},
                    0);
            Traff.setAdapter(adapter);
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
