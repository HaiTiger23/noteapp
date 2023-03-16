package htg.haitran.ghichu;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Lấy đối tượng SQLiteDatabase cho phép đọc dữ liệu từ database
    SQLiteDatabase db;
Button btnAdd;
ListView lv;
ArrayList<Note> note = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        initData();
        GetData();

       NoteAdapter adapter = new NoteAdapter(MainActivity.this, R.layout.item_note, note);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
        lv.setAdapter(adapter);
    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            int id = note.get(i).getId();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    });
    lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int vitri, long l) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("Cảnh báo !");
            alertDialog.setIcon(R.mipmap.ic_launcher);
            alertDialog.setMessage("Bạn có muốn xóa ghi chú này không ?");

            alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    int id = note.get(vitri).getId();
                    String sql = "DELETE FROM Notes WHERE id ="+id;
                    db.execSQL(sql);
                    note.clear();
                    GetData();
                    adapter.notifyDataSetChanged();
                }
            });
            alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alertDialog.show();
            return false;
        }
    });

    }
    private void AnhXa() {
        btnAdd = findViewById(R.id.btn_add);
        lv = findViewById(R.id.listview);
    }
    private void initData() {
        db = openOrCreateDatabase("note.db", MODE_PRIVATE, null);
        String sql ="CREATE TABLE IF NOT EXISTS Notes(" +
                "id integer primary key autoincrement," +
                "tieude text," +
                "noidung text," +
                "createtime DATETIME)";
        db.execSQL(sql);
    }
    private void GetData() {
        Cursor cursor = db.rawQuery("SELECT * FROM Notes", null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Lấy giá trị từ các cột tương ứng trong hàng
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    String tieude = cursor.getString(cursor.getColumnIndex("tieude"));
                    String noidung = cursor.getString(cursor.getColumnIndex("noidung"));
                    String createTime = cursor.getString(cursor.getColumnIndex("createtime"));
                    note.add(new Note(id, tieude, noidung, createTime));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
    public void   make_toast(String text) {
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
    }


}