package Maryan1200861.Hamza1201619;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "database3";
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
                    COLUMN_USER_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUMN_USER_HASHED_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_USER_FIRSTNAME + " TEXT, " +
                    COLUMN_USER_LASTNAME + " TEXT, " +
                    COLUMN_USER_PHONE + " TEXT UNIQUE NOT NULL, " +
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

    public void registerNewUser(User user) {

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_USER_FIRSTNAME, user.getFirstname());
        contentValues.put(COLUMN_USER_LASTNAME, user.getLastname());
        contentValues.put(COLUMN_USER_EMAIL, user.getEmail());
        contentValues.put(COLUMN_USER_HASHED_PASSWORD, user.getHashedPassword());
        contentValues.put(COLUMN_USER_PHONE, user.getPhone());
        contentValues.put(COLUMN_USER_GENDER, user.getGender());

        sqLiteDatabase.insert(TABLE_USERS, null, contentValues);
    }

    public void insert(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_PIZZA_NAME, name);
        db.insert(DatabaseHelper.TABLE_PIZZAS, null, contentValue);
    }

    // method to get all pizzas from the database, for debugging purposes
    public ArrayList<Pizza> getAllPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PIZZAS;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            Pizza pizza = new Pizza(id, name);
            pizzas.add(pizza);
        }
        cursor.close();
        db.close();

        return pizzas;
    }

    public boolean isPizzaInDatabase(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PIZZAS + " WHERE " + COLUMN_PIZZA_NAME + " = ?", new String[]{name});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    public ArrayList<User> getAllUsers() {

        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            String email = cursor.getString(0);
            String hashed_password = cursor.getString(1);
            String firstname = cursor.getString(2);
            String lastname = cursor.getString(3);
            String phone = cursor.getString(4);
            String gender = cursor.getString(5);
            User user = new User(firstname, lastname, email, gender, phone, hashed_password);

            users.add(user);
        }
        cursor.close();
        db.close();
        return users;
    }
}
