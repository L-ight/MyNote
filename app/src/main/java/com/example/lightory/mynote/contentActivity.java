package com.example.lightory.mynote;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;


import java.util.Calendar;

/**
 * Created by Lightory on 2016/8/5.
 */
public class contentActivity extends AppCompatActivity {

    Intent intent;
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

        intent = getIntent();
        ed_title.setText(intent.getStringExtra("title"));
        ed_content.setText(intent.getStringExtra("content"));
    }










    @TargetApi(24)
    @Override
    public void finish() {
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
            db.update("Notes",values,"TITLE=?",new String[]{intent.getStringExtra("title")});
            //db.insert("Notes", null, values);
            values.clear();
            Toast.makeText(this,"保存成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            super.finish();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.content_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.content_save) {
            Toast.makeText(this,"按钮",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
