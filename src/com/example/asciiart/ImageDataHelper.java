package com.example.asciiart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/*
 * Database Helper defines ASCII picture database
 */

public class ImageDataHelper  extends SQLiteOpenHelper {

	/*
	 * Database stores an ID, name and content for each ASCII image
	 * Content is text characters
	 * Name is reference to date and time created or modified
	 */
	//db version
	private static final int DATABASE_VERSION = 1;
	//database name
	private static final String DATABASE_NAME = "asciipics.db";
	//ID column
	public static final String ID_COL = BaseColumns._ID;
	//table name
	public static final String TABLE_NAME = "pics";
	//ascii text
	public static final String ASCII_COL = "ascii_text";
	//creation time
	public static final String CREATED_COL = "pic_creation";
	//database creation string
	private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + ID_COL + " INTEGER " +
			"PRIMARY KEY AUTOINCREMENT, " + ASCII_COL + " TEXT, " + CREATED_COL + " TEXT);";

	//database instance
	private static ImageDataHelper dbInstance;
	//application context
	private Context dbContext;

	/*
	 * Private constructor for use in this class only
	 */
	private ImageDataHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.dbContext=context;
	}

	/*
	 * Factory method to return instance of the class
	 */
	public static ImageDataHelper getInstance(Context context) {
		//check if instance already exists
		if (dbInstance == null) 
			dbInstance = new ImageDataHelper(context.getApplicationContext());
		//return
		return dbInstance;
	}

	/*
	 * Create the database
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	/*
	 * Upgrade - this must be provided
	 */
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS pics");
		db.execSQL("VACUUM");
		onCreate(db);
	}

}
