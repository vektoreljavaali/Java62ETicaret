package com.vektorel.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.vektorel.utility.StaticValues.*;

import com.vektorel.dao.entity.Note;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NoteRepository extends SQLiteOpenHelper {

    private Context context;

    /**
     * creat table tblnote(
     * id varchar(100),
     * username varchar(200),
     * )
     */
    String CREATETABLE= "create table "+ TABLE_NAME_NOTE+"( "+
            TBL_ID+" vachar(100), "+
            TBL_USERNAME+ " varchar(200), "+
            TBL_PRIORITY+ " varchar(20), "+
            TBL_CONTENT+ " varchar(1000), "+
            TBL_PUBLISHAT+ " numeric, "+
            TBL_TITLE+ " varchar(100), "+
            TBL_STATUS+ " numeric )";

    public NoteRepository(Context context){
        super(context,TABLE_NAME_NOTE,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATETABLE);
    }

    /**
     * Eğer versionlar arasında uyuşmazlık olursa db yi günceller
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void save(Note note){
        String uuid = UUID.randomUUID().toString();
        String addSql= "insert into "+TABLE_NAME_NOTE+"( " +
                TBL_ID+", "+
                TBL_USERNAME+ ", "+
                TBL_PRIORITY+ ", "+
                TBL_CONTENT+ ", "+
                TBL_PUBLISHAT+ ", "+
                TBL_TITLE+ ", "+
                TBL_STATUS+
                ") values('" + uuid+
                "','" + note.getUsername()+
                "','" + note.getPriority()+
                "','" + note.getContent()+
                "',"  + note.getPublishAt()+
                ",'"  + note.getTitle()+
                "',"  + note.getStatus()+
                ")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(addSql);
    }

    private void delete(String id){
        String deleteSql= "delete tblnote where id="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(deleteSql);
    }

    public List<Note> findAll(){
        List<Note> result = new ArrayList<>();
        String[] columlist = {TBL_ID,TBL_USERNAME,TBL_TITLE,TBL_CONTENT,TBL_PUBLISHAT};
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cr = db.query(TABLE_NAME_NOTE,columlist,
                null,null,null,null,null);
        if(cr.getCount()>0){
            Note note;
            while (cr.moveToNext()){
                /**
                 * Burada Dikkat!!!!!
                 * columlist verdiniz, okurken bu liste sıralı ile okumalısınız
                 *
                 */
                note = new Note(
                     cr.getString(0),
                        cr.getString(1),
                        cr.getString(2),
                        cr.getString(3),
                        cr.getLong(4),
                        0,""
                );
                result.add(note);
            }
        }
        return result;
    }
}
