package com.example.lightory.mynote;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Created by Lightory on 2016/8/5.
 */
public class newActivity extends AppCompatActivity {

    EditText ed_title,ed_content;
    private NoteDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0xFFFFFF);
        setSupportActionBar(toolbar);


        dbHelper = new NoteDatabaseHelper(this, "NoteData.db", null, 2);
        ed_title = (EditText)findViewById(R.id.ed_title);
        ed_content = (EditText)findViewById(R.id.ed_content);

    }

    @TargetApi(24)
    @Override
    public void finish() {
        if(ed_title.getText().toString().isEmpty()&&ed_content.getText().toString().isEmpty()){

        }
        else {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("TITLE", ed_title.getText().toString());
            values.put("CONTENT", ed_content.getText().toString());

            int y, m, d, h, mi, s;
            Calendar cal = Calendar.getInstance();
            y = cal.get(Calendar.YEAR);
            m = cal.get(Calendar.MONTH);
            d = cal.get(Calendar.DATE);
            h = cal.get(Calendar.HOUR_OF_DAY);
            mi = cal.get(Calendar.MINUTE);
            s = cal.get(Calendar.SECOND);
            String str = "" + y + "年" + m + "月" + d + "日" + "   " + h + "时" + mi + "分" + s + "秒";
            values.put("DATE", str);
            db.insert("Notes", null, values);
            values.clear();
            Toast.makeText(this, "保存成功", Toast.LENGTH_LONG).show();
        }
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        super.finish();
    }



}
