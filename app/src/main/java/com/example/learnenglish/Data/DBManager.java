package com.example.learnenglish.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.learnenglish.model.Vocabulary;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    //dung truong co dinh trong database static final => de de su dung lai
    private static final String DATABASE_NAME = "vocabulary_manager";
    private static final String TABLE_NAME = "vocabulary";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String DEFINE = "define";
    private static final String DESCRIPTION = "description";
    private static final String EXAMPLE = "example";
    private static final String SYNONYMOUS = "synonymous";
    private static int VERSION = 1;
    private Context context;


    private String SQLQuery = "create table " + TABLE_NAME+" ("+
            ID+" integer primary key, "+
            NAME+" TEXT, "+
            DEFINE+" TEXT, "+
            DESCRIPTION+" TEXT, "+
            EXAMPLE+" TEXT, "+
            SYNONYMOUS+" TEXT)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
//nó đc gọi khi SAVE => gọi phương thức thêm vào dababase.Nếu database chưa đc khởi tạo.Thì nó sẽ vào trong hàm này và khỏi tạo database
        db.execSQL(SQLQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //    Add new vocabulary
    public void addVocabulary(Vocabulary vocabulary){
//        getWritableDatabase tra ve line database => co the doc va chinh sua
        SQLiteDatabase db = this.getWritableDatabase();
//        không thể thêm trực tiếp mà phải thông qua phương thức save and see editor để boot dữ liệu đó vào contentvalue rồi lưu về
        ContentValues values = new ContentValues();
        values.put(NAME,vocabulary.getmName());
        values.put(DEFINE,vocabulary.getmDefine());
        values.put(DESCRIPTION,vocabulary.getmDescription());
        values.put(EXAMPLE,vocabulary.getmExample());
        values.put(SYNONYMOUS,vocabulary.getmSyn());
        db.insert(TABLE_NAME,null, values);
        db.close();

    }
//List vocabulary
    public List<Vocabulary> getAllVocabulary(){
        List<Vocabulary> listvocabulary = new ArrayList<>();
        String selectQuery = "Select * from "+ TABLE_NAME;
//Khoi tao doi duong sqlitedatabase
        SQLiteDatabase db = this.getWritableDatabase();
//select het vocabulary ma khong can dieu kien
        Cursor cursor = db.rawQuery(selectQuery, null);
        if(cursor.moveToFirst()){
            do{
                Vocabulary vocabulary = new Vocabulary();
                vocabulary.setmID(cursor.getInt(0));
                vocabulary.setmName(cursor.getString(1));
                vocabulary.setmDefine(cursor.getString(2));
                vocabulary.setmDescription(cursor.getString(3));
                vocabulary.setmExample(cursor.getString(4));
                vocabulary.setmSyn(cursor.getString(5));
                listvocabulary.add(vocabulary);
            }while(cursor.moveToNext());
        }
        db.close();
return listvocabulary;
    }
//    int update => tra ve so luong table update
    public int updateVocabulary(Vocabulary vocabulary){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME,vocabulary.getmName());
        values.put(DEFINE,vocabulary.getmDefine());
        values.put(DESCRIPTION,vocabulary.getmDescription());
        values.put(EXAMPLE,vocabulary.getmExample());
        values.put(SYNONYMOUS,vocabulary.getmSyn());
//    ?    tranh nhung cau lenh sql gian tiep tu ben ngoai
       return db.update(TABLE_NAME, values, ID + "=?", new String[] {String.valueOf(vocabulary.getmID())});

    }
//    delete vocabulary theo ID
    public int deleteVocabulary(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID+ "=?", new String[] {String.valueOf(id)});

    }

}

