package Maryan1200861.Hamza1201619;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "db0";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_PIZZAS = "Pizza";
    public static final String TABLE_USERS = "User";
    public static final String TABLE_ORDERS = "_Order";
    public static final String TABLE_ORDER_DETAILS = "OrderDetails";
    public static final String TABLE_FAVORITES = "Favorite";
    public static final String TABLE_SPECIAL_OFFERS = "SpecialOffer";
    public static final String TABLE_ADMINS = "Admin";
    public static final String TABLE_SPECIAL_OFFER_DETAILS = "SpecialOfferDetails";
    public static final String TABLE_PIZZA_DETAILS = "PizzaDetails";


    // Common column names
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_PIZZA_ID = "pizza_id";
    public static final String COLUMN_OFFER_ID = "offer_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_ORDER_DATE = "order_date";
    public static final String COLUMN_FAVORITE_ID = "favorite_id";
    public static final String COLUMN_OFFER_DETAILS_ID = "offer_details_id";
    public static final String COLUMN_ORDER_DETAILS_ID = "order_details_id";

    public static final String COLUMN_ADMIN_ID = "admin_id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_PROFILE_PICTURE = "profile_picture";

    // User specific column names
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_HASHED_PASSWORD = "hashed_password";
    public static final String COLUMN_USER_FIRSTNAME = "firstname";
    public static final String COLUMN_USER_LASTNAME = "lastname";
    public static final String COLUMN_USER_PHONE = "phone";
    public static final String COLUMN_USER_GENDER = "gender";
    private static final String COLUMN_ORDER_ID = "order_id";
    private static final String COLUMN_PIZZA_NAME = "pizza_name";


    // SQL Create statements
    private static final String TABLE_CREATE_PIZZAS =
            "CREATE TABLE " + TABLE_PIZZAS + " (" +
                    COLUMN_PIZZA_NAME + " TEXT PRIMARY KEY NOT NULL" + ");";


    private static final String TABLE_CREATE_PIZZA_DETAILS =
            "CREATE TABLE " + TABLE_PIZZA_DETAILS + " (" +
                COLUMN_PIZZA_NAME + " TEXT NOT NULL," +
                COLUMN_DESCRIPTION + " TEXT," +
                "category TEXT NOT NULL," +
                COLUMN_PRICE + " REAL NOT NULL," +
                COLUMN_SIZE + " TEXT NOT NULL" + ");";


    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUMN_USER_HASHED_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_USER_FIRSTNAME + " TEXT, " +
                    COLUMN_USER_LASTNAME + " TEXT, " +
                    COLUMN_USER_PHONE + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_PROFILE_PICTURE + " BLOB, " +
                    COLUMN_USER_GENDER + " TEXT);";

    private static final String TABLE_CREATE_ORDERS =
            "CREATE TABLE " + TABLE_ORDERS + " (" +
                    COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EMAIL + " TEXT NOT NULL," +
                    COLUMN_ORDER_DATE + " TEXT NOT NULL," +
                    COLUMN_TOTAL_PRICE + " REAL NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + ")" +
                    ");";

    private static final String TABLE_CREATE_ORDER_DETAILS =
            "CREATE TABLE " + TABLE_ORDER_DETAILS + " (" +
                    COLUMN_ORDER_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_ORDER_ID + " INTEGER NOT NULL," +
                    COLUMN_PIZZA_NAME + " TEXT NOT NULL," +
                    COLUMN_QUANTITY + " INTEGER NOT NULL," +
                    COLUMN_SIZE + " TEXT NOT NULL," +
                    COLUMN_PRICE + " REAL NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_ORDER_ID + ") REFERENCES " + TABLE_ORDERS + "(" + COLUMN_ORDER_ID + ")," +
                    "FOREIGN KEY (" + COLUMN_PIZZA_NAME + ") REFERENCES " + TABLE_PIZZAS + "(" + COLUMN_PIZZA_NAME + ")" +
                    ");";

    private static final String TABLE_CREATE_FAVORITES =
            "CREATE TABLE " + TABLE_FAVORITES + " (" +
                    COLUMN_FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EMAIL + " TEXT NOT NULL," +
                    COLUMN_PIZZA_NAME + " TEXT NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + ")," +
                    "FOREIGN KEY (" + COLUMN_PIZZA_NAME + ") REFERENCES " + TABLE_PIZZAS + "(" + COLUMN_PIZZA_NAME + ")" +
                    ");";

    private static final String TABLE_CREATE_SPECIAL_OFFERS =
            "CREATE TABLE " + TABLE_SPECIAL_OFFERS + " (" +
                    COLUMN_OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PIZZA_NAME + " TEXT NOT NULL," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_START_DATE + " TEXT NOT NULL," +
                    COLUMN_END_DATE + " TEXT NOT NULL," +
                    COLUMN_PRICE + " REAL NOT NULL" +
                    ");";

    private static final String TABLE_CREATE_ADMINS =
            "CREATE TABLE " + TABLE_ADMINS + " (" +
                    COLUMN_ADMIN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EMAIL + " TEXT UNIQUE NOT NULL," +
                    COLUMN_PASSWORD + " TEXT NOT NULL," +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL," +
                    COLUMN_LAST_NAME + " TEXT NOT NULL," +
                    COLUMN_PHONE_NUMBER + " TEXT NOT NULL," +
                    COLUMN_PROFILE_PICTURE + " BLOB" +
                    ");";

    private static final String TABLE_CREATE_SPECIAL_OFFER_DETAILS =
            "CREATE TABLE " + TABLE_SPECIAL_OFFER_DETAILS + " (" +
                    COLUMN_OFFER_DETAILS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_OFFER_ID + " INTEGER NOT NULL," +
                    COLUMN_PIZZA_ID + " INTEGER NOT NULL," +
                    COLUMN_SIZE + " TEXT NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_OFFER_ID + ") REFERENCES " + TABLE_SPECIAL_OFFERS + "(" + COLUMN_OFFER_ID + ")," +
                    "FOREIGN KEY (" + COLUMN_PIZZA_ID + ") REFERENCES " + TABLE_PIZZAS + "(" + COLUMN_PIZZA_ID + ")" +
                    ");";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PIZZAS);
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_ORDERS);
        db.execSQL(TABLE_CREATE_ORDER_DETAILS);
        db.execSQL(TABLE_CREATE_FAVORITES);
        db.execSQL(TABLE_CREATE_SPECIAL_OFFERS);
        db.execSQL(TABLE_CREATE_ADMINS);
        db.execSQL(TABLE_CREATE_SPECIAL_OFFER_DETAILS);
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

    public void insertPizza(String name) {
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

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            Pizza pizza = new Pizza(name);
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

    public User getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + " = ?", new String[]{email});
        User user = null;
        if (cursor.moveToFirst()) {
            String hashed_password = cursor.getString(1);
            String firstname = cursor.getString(2);
            String lastname = cursor.getString(3);
            String phone = cursor.getString(4);

            user = new User(firstname, lastname, email, null, phone, hashed_password);
        }

        cursor.close();
        db.close();
        return user;
    }

    public void addFavoritePizza(int userId, int pizzaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USER_ID, userId);
        contentValues.put(COLUMN_PIZZA_ID, pizzaId);
        db.insert(TABLE_FAVORITES, null, contentValues);
        db.close();
    }

    public void removeFavoritePizza(int userId, int pizzaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = COLUMN_USER_ID + " = ? AND " + COLUMN_PIZZA_ID + " = ?";
        String[] whereArgs = { String.valueOf(userId), String.valueOf(pizzaId) };
        db.delete(TABLE_FAVORITES, whereClause, whereArgs);
        db.close();
    }

    public ArrayList<Pizza> getFavoritePizzas(String userEmail) {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PIZZAS +
                " INNER JOIN " + TABLE_FAVORITES +
                " ON " + TABLE_PIZZAS + "." + COLUMN_PIZZA_NAME + " = " + TABLE_FAVORITES + "." + COLUMN_PIZZA_NAME +
                " WHERE " + TABLE_FAVORITES + "." + COLUMN_EMAIL + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{userEmail});

        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            Pizza pizza = new Pizza(name);
            pizzas.add(pizza);
        }
        cursor.close();
        db.close();

        return pizzas;
    }



}
