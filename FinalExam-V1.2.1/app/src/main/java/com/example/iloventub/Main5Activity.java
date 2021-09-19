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

public class Main5Activity extends AppCompatActivity {

    private Button IdxBtn;
    //宣告物件
    ListView Dep1, Dep2;

    SQLiteDatabase db;
    static final String DB_NAME="NTUB";
    static final String TB_NAME="Connect";
    static final String DB_PATH = "/data/data/com.example.iloventub/databases/";
    static final String[] FROM=new String[] {"department","phone","ext","location"};

    Cursor curA,curB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        Dep1=(ListView)findViewById(R.id.Dep1);
        Dep2=(ListView)findViewById(R.id.Dep2);
//
        IdxBtn = (Button)findViewById(R.id.IdxBtn);
        IdxBtn.setOnClickListener(btnIdxBtnListener);

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
//
        // 開啟或建立資料庫
        db = openOrCreateDatabase(DB_NAME,  Context.MODE_PRIVATE, null);

        curA = db.rawQuery("SELECT * FROM " + TB_NAME + " WHERE type = '行政' " , null);
        curB = db.rawQuery("SELECT * FROM " + TB_NAME + " WHERE type = '學術' " , null);
        if(curA != null && curA.getCount()>=0 ){
                SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
                        R.layout.item,
                        curA,
                        FROM,
                        new int[] {R.id.department,R.id.phone,R.id.ext, R.id.location},
                        0);
                Dep1.setAdapter(adapter);
            adapter.changeCursor(curA);
        }else{
            Toast.makeText(this, "DB NO DATA", Toast.LENGTH_SHORT).show();
        }

        if(curB != null && curB.getCount()>=0 ){
            SimpleCursorAdapter adapter= new SimpleCursorAdapter(this,
                    R.layout.item,
                    curB,
                    FROM,
                    new int[]{R.id.department,R.id.phone,R.id.ext, R.id.location},
                    0);
            Dep2.setAdapter(adapter);
            adapter.changeCursor(curB);
        }else{
            Toast.makeText(this, "DB NO DATA", Toast.LENGTH_SHORT).show();
        }
        // 取得電話號碼item位置
        Dep1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得 item的phone Text view
                TextView phone = ((TextView)view.findViewById(R.id.phone));
                // 打電話
                   call(phone);
            }
        });
        // 取得電話號碼item位置
        Dep2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 取得 item的phone Text view
                TextView phone = ((TextView)view.findViewById(R.id.phone));
                // 打電話
                call(phone);
            }
        });
    }

    // 打電話
    public void call(View v){
        String uri="tel:" + curA.getString(curA.getColumnIndex(FROM[1]));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(it);
    }
    // 返回首頁
    private Button.OnClickListener btnIdxBtnListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            //System.exit(0);
            finish();

        }
    };
}
