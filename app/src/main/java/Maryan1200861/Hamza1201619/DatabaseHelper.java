package Maryan1200861.Hamza1201619;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database1";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PIZZAS = "pizzas";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PIZZA_NAME = "name";

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_HASHED_PASSWORD = "hashed_password";

    public static final String COLUMN_USER_FIRSTNAME = "firstname";

    public static final String COLUMN_USER_LASTNAME = "lastname";

    public static final String COLUMN_USER_PHONE = "phone";

    public static final String COLUMN_USER_GENDER = "gender";


    private static final String TABLE_CREATE_PIZZAS =
            "CREATE TABLE " + TABLE_PIZZAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_PIZZA_NAME + " TEXT NOT NULL);";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_USER_EMAIL + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_USER_HASHED_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_USER_FIRSTNAME + " TEXT, " +
                    COLUMN_USER_LASTNAME + " TEXT, " +
                    COLUMN_USER_PHONE + " TEXT NOT NULL, " +
                    COLUMN_USER_GENDER + ");";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PIZZAS);
        db.execSQL(TABLE_CREATE_USERS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }

    public void registerNewUser(String firstname, String lastname, String email, String phone,
                                String gender, String hashedPassword) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_FIRSTNAME, firstname);
        contentValues.put(COLUMN_USER_LASTNAME, lastname);
        contentValues.put(COLUMN_USER_EMAIL, email);
        contentValues.put(COLUMN_USER_HASHED_PASSWORD, hashedPassword);
        contentValues.put(COLUMN_USER_PHONE, phone);
        contentValues.put(COLUMN_USER_GENDER, gender);

        sqLiteDatabase.insert(TABLE_USERS, null, contentValues);
    }

    public void insert(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_PIZZA_NAME, name);
        db.insert(DatabaseHelper.TABLE_PIZZAS, null, contentValue);
    }


}
