package htg.haitran.ghichu;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNoteActivity extends AppCompatActivity {
    private SQLiteDatabase db;
    EditText edtTieuDe, edtNoiDung;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        AnhXa();
        initData();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });
    }
    private void AnhXa(){
        edtTieuDe = findViewById(R.id.edtTieuDe);
        edtNoiDung = findViewById(R.id.edtNoiDung);
        btnSave = findViewById(R.id.btn_save);
    }
    private void initData() {
            db = openOrCreateDatabase("note.db", MODE_PRIVATE, null);
    }
    private void  insertData() {
        String tieuDe = edtTieuDe.getText().toString();
        String  noiDung = edtNoiDung.getText().toString();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        String CreateTime = dateFormat.format(calendar.getTime());
        if (tieuDe.length() == 0 || noiDung.length() == 0) {
            make_toast("Bạn cần nhập đầy đủ thông tin");
        }else {
            String sql = "INSERT INTO Notes(tieude,noidung,createtime) VALUES ('"+tieuDe+"','"+noiDung+"','"+CreateTime+"')";
            db.execSQL(sql);
            Intent intent = new Intent(AddNoteActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void   make_toast(String text) {
        Toast.makeText(this, text,Toast.LENGTH_SHORT).show();
    }


}