package com.example.lightory.mynote;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private NoteDatabaseHelper dbHelper;
    ListAdapter<BaseItem> list;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(0xFFFFFF);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, newActivity.class);
                startActivity(intent);
                finish();
               /* Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });






        /*BaseItem item1, item2, item3;
        item1 =new  BaseItem("Title One","Content One","Date One");
        item2 =new  BaseItem("Two","Content","Date");
        item3 =new  BaseItem("Main","Left","Right");
        list.add(item1);
        list.add(item2);
        list.add(item3);*/
        /*boolean flag = cursor.moveToFirst();
        String title = cursor.getString(cursor.getColumnIndex("TITLE"));
        String content = cursor.getString(cursor.getColumnIndex("CONTENT"));
        String date = cursor.getString(cursor.getColumnIndex("DATE"));
        BaseItem item = new BaseItem(title,content,date);
        list.add(item);*/

        addValuesToAdapter();
        lv = (ListView) findViewById(R.id.list_view);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                MainActivity.this.list.startActivity(view, i);
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(final AdapterView<?> adapterView, View view, int i, long l) {
                list.position = i;
                AlertDialog.Builder  dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("删除");
                dialog.setCancelable(true);
                dialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        list.delView(list.position);
                        MainActivity.this.addValuesToAdapter();
                    }
                });
                dialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();

                return true;
            }






        });

    }



    public void addValuesToAdapter(){
        lv = (ListView)findViewById(R.id.list_view);
        list = new ListAdapter<BaseItem>(this, R.layout.list_layout) {
            @Override
            public void initView(View view, int position) {
                title = (TextView) view.findViewById(R.id.list_item_title);
                content = (TextView) view.findViewById(R.id.list_item_content);
                date = (TextView) view.findViewById(R.id.list_item_date);
                title.setText(getItem(position).getTitle().toString());
                content.setText(getItem(position).getContent().toString());
                date.setText(getItem(position).getDate().toString());
            }

            @Override
            public void startActivity(View view, int position) {
                Intent intent = new Intent(MainActivity.this, contentActivity.class);
                intent.putExtra("title", getItem(position).getTitle().toString());
                intent.putExtra("content", getItem(position).getContent().toString());
                MainActivity.this.finish();
                MainActivity.this.startActivity(intent);
            }

            @Override
            public void delView( int position) {
                //Toast.makeText(MainActivity.this,""+position,Toast.LENGTH_LONG).show();
                NoteDatabaseHelper dbHelper = new NoteDatabaseHelper(MainActivity.this, "NoteData.db", null, 2);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                String str = getItem(position).getTitle().toString();
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                db.delete("Notes", "TITLE = ?", new String[]{str});
            }
        };
        dbHelper = new NoteDatabaseHelper(this, "NoteData.db", null, 2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query("Notes", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.
                        getColumnIndex("TITLE"));
                String content = cursor.getString(cursor.
                        getColumnIndex("CONTENT"));
                String date = cursor.getString(cursor.
                        getColumnIndex("DATE"));
                BaseItem item = new BaseItem(title, content, date);
                list.add(item);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        lv.setAdapter(list);
    }
}
