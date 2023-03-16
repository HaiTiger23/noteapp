package htg.haitran.ghichu;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoteDetailActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    int id;
    TextView edtTieuDe,edtNoiDung;
    Button btnUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            id = bundle.getInt("id");
        }
        AnhXa();
        initData();
        getData();
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateData();
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sql = "DELETE FROM Notes WHERE id ="+id;
                db.execSQL(sql);
                Intent intent = new Intent(NoteDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void AnhXa(){
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);

    }
    private void initData() {
        db = openOrCreateDatabase("note.db", MODE_PRIVATE, null);

    }
    private void getData() {
        Cursor cursor = db.rawQuery("SELECT * FROM Notes WHERE id = "+id, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    // Lấy giá trị từ các cột tương ứng trong hàng
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                    edtTieuDe.setText(cursor.getString(cursor.getColumnIndex("tieude")));
                    edtNoiDung.setText(cursor.getString(cursor.getColumnIndex("noidung")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }
    private void  updateData() {
        String tieuDe = edtTieuDe.getText().toString();
        String  noiDung = edtNoiDung.getText().toString();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String CreateTime = dateFormat.format(calendar.getTime());
        if (tieuDe.length() == 0 || noiDung.length() == 0) {
            make_toast("Bạn cần nhập đầy đủ thông tin");
        }else {
            String sql = "UPDATE Notes SET tieude ='"+tieuDe+"',noidung = '"+noiDung+"',createtime ='"+CreateTime+"' WHERE id ="+id;
            db.execSQL(sql);
            Intent intent = new Intent(NoteDetailActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void   make_toast(String text) {
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
    }

}