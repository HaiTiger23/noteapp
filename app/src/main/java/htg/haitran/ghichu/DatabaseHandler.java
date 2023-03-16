package htg.haitran.ghichu;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "NoteDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Notes";

    private static final String KEY_ID = "id";
    private static final String KEY_TIEU_DE = "tieude";
    private static final String KEY_NOI_DUNG= "noidung";
    private static final String KEY_TIME_CREATE = "phone_number";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT, %s TEXT, %s DATETIME)", TABLE_NAME, KEY_ID, KEY_TIEU_DE, KEY_NOI_DUNG, KEY_TIME_CREATE);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }
    public void addStudent(Note note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TIEU_DE, note.getTieuDe());
        values.put(KEY_NOI_DUNG, note.getNoiDung());
        values.put(KEY_TIME_CREATE, note.getCreateTime());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
}
