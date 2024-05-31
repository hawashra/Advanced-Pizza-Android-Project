package Maryan1200861.Hamza1201619;

import android.content.ContentValues;
import android.content.Context;
import android.database.AbstractCursor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.Date;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static int currentPizzaId = 0; // used to assign unique IDs to pizzas


    private static final String DATABASE_NAME = "db4";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_PIZZAS = "Pizza";
    public static final String TABLE_USERS = "User";
    public static final String TABLE_ORDERS = "orders";
    public static final String TABLE_FAVORITES = "Favorite";
    public static final String TABLE_SPECIAL_OFFERS = "SpecialOffer";
    public static final String TABLE_ADMINS = "Admin";

    public static final String TABLE_SPECIAL_OFFER_PIZZAS = "SpecialOfferPizza";


    // Common column names
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_PRICE = "price";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_PIZZA_ID = "pizza_id";
    public static final String COLUMN_OFFER_ID = "offer_id";
    public static final String COLUMN_QUANTITY = "quantity";
    public static final String COLUMN_TOTAL_PRICE = "total_price";
    public static final String COLUMN_ORDER_DATE = "order_date";
    public static final String COLUMN_FAVORITE_ID = "favorite_id";

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
    private static final String COLUMN_PIZZA_CATEGORY = "category";


    // SQL Create statements
    private static final String TABLE_CREATE_PIZZAS =
            "CREATE TABLE " + TABLE_PIZZAS + " (" +
                    COLUMN_PIZZA_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_PIZZA_NAME + " TEXT NOT NULL UNIQUE," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_PIZZA_CATEGORY + " TEXT NOT NULL," +
                    COLUMN_PRICE + " REAL NOT NULL," +
                    COLUMN_SIZE + " TEXT NOT NULL" + ");";


    private static final String TABLE_CREATE_ORDER =
            "CREATE TABLE orders (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_EMAIL + " TEXT NOT NULL, " +
            "order_date DATETIME, " +
            "total_price REAL, " +
            "FOREIGN KEY (" + COLUMN_EMAIL + ") REFERENCES User(" + COLUMN_EMAIL + ")\n" +
            ");";

    private static final String TABLE_CREATE_ORDER_ITEM =
            "CREATE TABLE order_item (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "order_id INTEGER NOT NULL, " +
            "pizza_id INTEGER NOT NULL, " +
            "quantity INTEGER NOT NULL, " +
            "FOREIGN KEY (order_id) REFERENCES orders(id), " +
            "FOREIGN KEY (pizza_id) REFERENCES Pizza(id)\n" +
            ");";

    private static final String TABLE_CREATE_USERS =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_USER_EMAIL + " TEXT PRIMARY KEY NOT NULL, " +
                    COLUMN_USER_HASHED_PASSWORD + " TEXT NOT NULL, " +
                    COLUMN_USER_FIRSTNAME + " TEXT, " +
                    COLUMN_USER_LASTNAME + " TEXT, " +
                    COLUMN_USER_PHONE + " TEXT UNIQUE NOT NULL, " +
                    COLUMN_PROFILE_PICTURE + " BLOB, " +
                    COLUMN_USER_GENDER + " TEXT);";



    private static final String TABLE_CREATE_FAVORITES =
            "CREATE TABLE " + TABLE_FAVORITES + " (" +
                    COLUMN_FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_EMAIL + " TEXT NOT NULL," +
                    COLUMN_PIZZA_ID + " INTEGER NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_EMAIL + ") REFERENCES " + TABLE_USERS + "(" + COLUMN_EMAIL + ")," +
                    "FOREIGN KEY (" + COLUMN_PIZZA_ID + ") REFERENCES " + TABLE_PIZZAS + "(" + COLUMN_PIZZA_ID + ")" +
                    ");";

    private static final String TABLE_CREATE_SPECIAL_OFFERS =
            "CREATE TABLE " + TABLE_SPECIAL_OFFERS + " (" +
                    COLUMN_OFFER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    COLUMN_PIZZA_ID + " INTEGER NOT NULL," +
                    COLUMN_DESCRIPTION + " TEXT," +
                    COLUMN_START_DATE + " TEXT NOT NULL," +
                    COLUMN_END_DATE + " TEXT NOT NULL," +
                    COLUMN_PRICE + " REAL NOT NULL," +
                    "FOREIGN KEY (" + COLUMN_OFFER_ID + ") REFERENCES " + TABLE_SPECIAL_OFFERS + "(" + COLUMN_OFFER_ID + ")," +
                    "FOREIGN KEY (" + COLUMN_PIZZA_ID + ") REFERENCES " + TABLE_PIZZAS + "(" + COLUMN_PIZZA_ID + ")" +

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



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_PIZZAS);
        db.execSQL(TABLE_CREATE_USERS);
        db.execSQL(TABLE_CREATE_FAVORITES);
        db.execSQL(TABLE_CREATE_SPECIAL_OFFERS);
        db.execSQL(TABLE_CREATE_ADMINS);
        db.execSQL(TABLE_CREATE_ORDER);
        db.execSQL(TABLE_CREATE_ORDER_ITEM);
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

    public void insertPizza(Pizza pizza) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.COLUMN_PIZZA_NAME, pizza.getName());
        contentValue.put(DatabaseHelper.COLUMN_DESCRIPTION, pizza.getDescription());
        contentValue.put(DatabaseHelper.COLUMN_PIZZA_CATEGORY, pizza.getCategory());
        contentValue.put(DatabaseHelper.COLUMN_PRICE, pizza.getPrice());
        contentValue.put(DatabaseHelper.COLUMN_SIZE, pizza.getSize());
        currentPizzaId++;
        db.insert(DatabaseHelper.TABLE_PIZZAS, null, contentValue);
    }

    // method to get all pizzas from the database, for debugging purposes
    public ArrayList<Pizza> getAllPizzas() {
        ArrayList<Pizza> pizzas = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PIZZAS;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            String category = cursor.getString(3);
            int price = cursor.getInt(4);
            String size = cursor.getString(5);
            Pizza pizza = new Pizza(name,size, price, description, category);
            pizza.setId(id);

            // add the pizza to the list if it's not already there (name is unique)
            // does not matter which size is added, as long as the name is the same
            // the user can choose when ordering the pizza
            if (!pizzas.contains(pizza)) {
                pizzas.add(pizza);
            }

        }
        cursor.close();
        db.close();

        return pizzas;
    }

    public boolean isPizzaInDatabase(String pizzaName) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_PIZZAS + " WHERE " + COLUMN_PIZZA_NAME + " = ?", new String[]{pizzaName});
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

    public ArrayList<String> getFavoritePizzas(String userEmail) {
        ArrayList<String> pizzasNames = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PIZZAS +
                " INNER JOIN " + TABLE_FAVORITES +
                " ON " + TABLE_PIZZAS + "." + COLUMN_PIZZA_ID + " = " + TABLE_FAVORITES + "." + COLUMN_PIZZA_ID +
                " WHERE " + TABLE_FAVORITES + "." + COLUMN_EMAIL + " = ? AND " + TABLE_PIZZAS + "." + COLUMN_PIZZA_NAME;

        Cursor cursor = db.rawQuery(query, new String[]{userEmail});

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            pizzasNames.add(name);
        }
        cursor.close();
        db.close();

        return pizzasNames;
    }


    public ArrayList<Pizza> getPizzasWithFilter(String filterType, String query) {
        ArrayList<Pizza> pizzas = new ArrayList<>();

        // Open the database
        SQLiteDatabase db = this.getWritableDatabase();

        // Define the query
        String selectQuery;

        if (filterType.equals("price")) {
            // Convert the query to a number
            double priceQuery = Double.parseDouble(query);

            selectQuery = "SELECT * FROM " + TABLE_PIZZAS + " WHERE " + COLUMN_PRICE + " <= " + priceQuery;
        } else {
            selectQuery = "SELECT * FROM " + TABLE_PIZZAS + " WHERE " + filterType.toLowerCase() + " LIKE '%" + query + "%'";
        }

        // Execute the query
        Cursor cursor = db.rawQuery(selectQuery, null);

        // Loop through the results and create Pizza objects
        if (cursor.moveToFirst()) {
            do {
                Pizza pizza = new Pizza();
                pizza.setId(cursor.getInt(0));
                pizza.setName(cursor.getString(1));
                pizza.setPrice(cursor.getInt(2));
                // ... set the other properties ...

                pizzas.add(pizza);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return pizzas;
    }
    public boolean userExists(String email, String phone) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USER_EMAIL + " = ? OR " + COLUMN_USER_PHONE + " = ?", new String[]{email, phone});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }


    public void orderPizza(String email, int pizzaId, int quantity, double totalPrice) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        // add to the orders table
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_ORDER_DATE, new Date(System.currentTimeMillis()).toString());
        contentValues.put(COLUMN_TOTAL_PRICE, totalPrice);
        db.insert(TABLE_ORDERS, null, contentValues);

        // get the order id
        Cursor cursor = db.rawQuery("SELECT MAX(id) FROM orders", null);
        cursor.moveToFirst();
        int orderId = cursor.getInt(0);
        cursor.close();

        // add to the order_item table
        contentValues.clear();
        contentValues.put("order_id", orderId);
        contentValues.put("pizza_id", pizzaId);
        contentValues.put("quantity", quantity);
        db.insert("order_item", null, contentValues);

        db.close();
    }

    public ArrayList<Order> getOrdersForUser(String userEmail) {
        ArrayList<Order> orders = new ArrayList<>();

        // Open the database
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the query
        String selectQuery = "SELECT * FROM " + TABLE_ORDERS +
                " INNER JOIN " + "order_item" +
                " ON " + TABLE_ORDERS + ".id = " + "order_item.order_id" +
                " WHERE " + TABLE_ORDERS + "." + COLUMN_EMAIL + " = ?";

        // Execute the query
        Cursor cursor = db.rawQuery(selectQuery, new String[]{userEmail});

        // Loop through the results and create Order objects
        if (cursor.moveToFirst()) {
            do {
                int orderId = cursor.getInt(0);
                String orderDate = cursor.getString(3);
                double totalPrice = cursor.getDouble(4);

                Order order = new Order(orderId, userEmail, orderDate, totalPrice);

                // Get the pizzas for this order
                Cursor pizzaCursor = db.rawQuery("SELECT * FROM " + "order_item" +
                        " WHERE order_id = ?", new String[]{String.valueOf(orderId)});

                while (pizzaCursor.moveToNext()) {
                    int pizzaId = pizzaCursor.getInt(2);
                    int quantity = pizzaCursor.getInt(3);

                    // Get the pizza details
                    Cursor pizzaDetailsCursor = db.rawQuery("SELECT * FROM " + TABLE_PIZZAS +
                            " WHERE " + COLUMN_PIZZA_ID + " = ?", new String[]{String.valueOf(pizzaId)});

                    if (pizzaDetailsCursor.moveToFirst()) {
                        String pizzaName = pizzaDetailsCursor.getString(1);
                        String pizzaDescription = pizzaDetailsCursor.getString(2);
                        String pizzaCategory = pizzaDetailsCursor.getString(3);
                        int pizzaPrice = pizzaDetailsCursor.getInt(4);
                        String pizzaSize = pizzaDetailsCursor.getString(5);

                        Pizza pizza = new Pizza(pizzaName, pizzaSize, pizzaPrice, pizzaDescription, pizzaCategory);
                        pizza.setId(pizzaId);

                        // Add the pizza to the order
                        order.addPizza(pizza, quantity);
                    }

                    pizzaDetailsCursor.close();
                }

                pizzaCursor.close();

                // Add the order to the list
                orders.add(order);
            } while (cursor.moveToNext());
        }

        // Close the cursor and the database
        cursor.close();
        db.close();

        return orders;
    }

}
