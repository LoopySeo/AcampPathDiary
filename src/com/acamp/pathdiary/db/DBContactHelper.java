package com.acamp.pathdiary.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.acamp.pathdiary.model.Contact;

public class DBContactHelper extends SQLiteOpenHelper {
	  
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_SEQ = "seq";
    private static final String KEY_LAT = "lat";
    private static final String KEY_LNG = "lng";
    private static final String KEY_SI  = "si";
    private static final String KEY_GU  = "gu";
    private static final String KEY_DONG = "dong";
    private static final String KEY_DETAIL_ADDR = "detail_addr";
    private static final String KEY_FILE_NAME	= "file_name"; 
    private static final String KEY_REG_DT		= "reg_dt";

    public DBContactHelper(Context context) {
              super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
              String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                                   + KEY_SEQ + " INTEGER PRIMARY KEY," 
                                   + KEY_LAT + " REAL NOT NULL,"
                                   + KEY_LNG + " REAL NOT NULL,"
                                   + KEY_SI + " TEXT NOT NULL,"
                                   + KEY_GU + " TEXT NOT NULL,"
                                   + KEY_DONG + " TEXT NOT NULL,"
                                   + KEY_DETAIL_ADDR + " TEXT,"
                                   + KEY_FILE_NAME + " TEXT,"
                                   + KEY_REG_DT + " DATETIME NOT NULL"
                                   + ")";
              db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
              // Drop older table if existed
              db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
              // Create tables again
              onCreate(db);
    }

    /**
     * CRUD 함수 
     */

    // 새로운 Contact 함수 추가 
    public void addContact(Contact contact) {
              SQLiteDatabase db = this.getWritableDatabase();

              ContentValues values = new ContentValues();
              values.put(KEY_LAT, contact.getLat()); 
              values.put(KEY_LNG, contact.getLng());
              values.put(KEY_SI, contact.getSi());
              values.put(KEY_GU, contact.getGu());
              values.put(KEY_DONG, contact.getDong());
              values.put(KEY_DETAIL_ADDR, contact.getDetail_addr());
              values.put(KEY_FILE_NAME, contact.getFile_name());
              values.put(KEY_REG_DT, contact.getReg_dt());
              
              // Inserting Row
              db.insert(TABLE_CONTACTS, null, values);
              db.close(); // Closing database connection
    }

    // 모든 Contact 정보 가져오기 
    public List<Contact> getAllContacts() {
              List<Contact> contactList = new ArrayList<Contact>();
              // Select All Query
              String columns = String.format(" %s, %s, %s, %s, %s, %s, %s, %s, %s ", 
            		  KEY_SEQ,
            		  KEY_LAT,
            		  KEY_LNG,
            		  KEY_SI,
            		  KEY_GU,
            		  KEY_DONG,
            		  KEY_DETAIL_ADDR,
            		  KEY_FILE_NAME,
            		  KEY_REG_DT); 
              String format = "SELECT %s FROM %s"; 
              String selectQuery = String.format(format, columns,TABLE_CONTACTS);
              
              SQLiteDatabase db = this.getWritableDatabase();
              Cursor cursor = db.rawQuery(selectQuery, null);

              // looping through all rows and adding to list
              if (cursor.moveToFirst()) {
                         do {
                                   Contact contact = new Contact();
                                   contact.setSeq(Integer.parseInt(cursor.getString(0)));
                                   contact.setLat(Double.parseDouble(cursor.getString(1)));
                                   contact.setLng(Double.parseDouble(cursor.getString(2)));
                                   contact.setSi(cursor.getString(3));
                                   contact.setGu(cursor.getString(4));
                                   contact.setDong(cursor.getString(5));
                                   contact.setDetail_addr(cursor.getString(6));
                                   contact.setFile_name(cursor.getString(7));
                                   contact.setReg_dt(cursor.getString(8));
                                   contactList.add(contact);
                         } while (cursor.moveToNext());
              }

              // return contact list
              return contactList;
    }
    
 // 모든 Contact 정보 가져오기 
    public List<Contact> getAllContacts( String condition) {
              List<Contact> contactList = new ArrayList<Contact>();
              // Select All Query
              
              String columns = String.format(" %s, %s, %s, %s, %s, %s, %s, %s, %s ", 
            		  KEY_SEQ,
            		  KEY_LAT,
            		  KEY_LNG,
            		  KEY_SI,
            		  KEY_GU,
            		  KEY_DONG,
            		  KEY_DETAIL_ADDR,
            		  KEY_FILE_NAME,
            		  KEY_REG_DT); 
              String format = "SELECT %s FROM %s WHERE %s"; 
              String selectQuery = String.format(format, columns,TABLE_CONTACTS, condition);

              SQLiteDatabase db = this.getWritableDatabase();
              Cursor cursor = db.rawQuery(selectQuery, null);

              // looping through all rows and adding to list
              if (cursor.moveToFirst()) {
                         do {
                                   Contact contact = new Contact();
                                   contact.setSeq(Integer.parseInt(cursor.getString(0)));
                                   contact.setLat(Double.parseDouble(cursor.getString(1)));
                                   contact.setLng(Double.parseDouble(cursor.getString(2)));
                                   contact.setSi(cursor.getString(3));
                                   contact.setGu(cursor.getString(4));
                                   contact.setDong(cursor.getString(5));
                                   contact.setDetail_addr(cursor.getString(6));
                                   contact.setFile_name(cursor.getString(7));
                                   contact.setReg_dt(cursor.getString(8));
                                   contactList.add(contact);
                         } while (cursor.moveToNext());
              }

              // return contact list
              return contactList;
    }
    /*
    //Contact 정보 업데이트 
    public int updateContact(Contact contact) {
              SQLiteDatabase db = this.getWritableDatabase();

              ContentValues values = new ContentValues();
              values.put(KEY_NAME, contact.getName());
              values.put(KEY_PH_NO, contact.getPhoneNumber());

              // updating row
              return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                                   new String[] { String.valueOf(contact.getID()) });
    }*/

    // Contact 정보 삭제하기 
    public void deleteContact(Contact contact) {
              SQLiteDatabase db = this.getWritableDatabase();
              db.delete(TABLE_CONTACTS, KEY_SEQ + " = ?",
                                   new String[] { String.valueOf(contact.getSeq()) });
              db.close();
    }

    // Contact 정보 숫자 
    public int getContactsCount() {
              String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
              SQLiteDatabase db = this.getReadableDatabase();
              Cursor cursor = db.rawQuery(countQuery, null);
              cursor.close();

              // return count
              return cursor.getCount();
    }

}
