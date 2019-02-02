package thebetadeveloper.app.first_avenue_customer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fifth_avenue";
    public static final String TABLE_NAME = "staff_table";
    public static final String COL_1 = "username";
    public static final String COL_2 = "password";
    public static final String CREATE_TABLE_STAFF="create table " + TABLE_NAME +" (username TEXT,password TEXT)";
    public static final String FOOD_TABLE = "food";
    public static final String FOOD_ID = "food_id";
    public static final String FOOD_CAT = "category";
    public static final String FOOD_NAME = "name";
    public static final String FOOD_PRICE = "price";
    public static final String ORDER_TABLE= "orders";
    public static final String TABLE_ID="table_id";
    public static final String ORDER_ID="order_id";
    public static final String FOOD_ITEMS="food_items";
    public static final String ORDER_STATUS="status";
    public static final String FOOD_QUANTITY="quantity";
    public static final String FEEDBACK_TABLE="feedback";
    public static final String SERVICE="servicerating";
    public static final String FOOD_RAT="foodrating";
    public static final String HYGIENE="hygienerating";
    public static final String OFFER_TABLE="offer";
    public static final String MIN_VALUE="min_value";
    public static final String DISCOUNT="discount";
    public static final String CREATE_TABLE_FOOD_TABLE = "CREATE TABLE " + FOOD_TABLE + "(" + FOOD_ID + " TEXT," + FOOD_CAT + " TEXT," + FOOD_NAME + " TEXT," + FOOD_PRICE + " TEXT" + ")";
    public static final String CREATE_TABLE_ORDERS = "CREATE TABLE "+ ORDER_TABLE + "(" + ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "  +TABLE_ID + " INTEGER," +  FOOD_ITEMS + " TEXT," + FOOD_QUANTITY + " TEXT," + ORDER_STATUS + " INTEGER"+")";
    public static final String CREATE_TABLE_FEEDBACK ="CREATE TABLE "+ FEEDBACK_TABLE + "(" +SERVICE + " INTEGER," + FOOD_RAT + " INTEGER," + HYGIENE + " INTEGER" + ")";
    public static final String CREATE_TABLE_OFFERS ="CREATE TABLE " + OFFER_TABLE + "(" + MIN_VALUE + " INTEGER," + DISCOUNT + " INTEGER" + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        Log.v("qqqq","sae");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("qqqq","see");
        db.execSQL(CREATE_TABLE_STAFF);
        db.execSQL(CREATE_TABLE_FOOD_TABLE);
        db.execSQL(CREATE_TABLE_ORDERS);
        db.execSQL(CREATE_TABLE_FEEDBACK);
        db.execSQL(CREATE_TABLE_OFFERS);
        Log.v("qqqq","completed");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + ORDER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FEEDBACK_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + OFFER_TABLE);
        onCreate(db);
    }

    public boolean addStaff(String username,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,password);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean addFeedback(int service,int food,int hygiene) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(SERVICE,service);
        contentValues.put(FOOD_RAT,food);
        contentValues.put(HYGIENE,hygiene);
        long result = db.insert(FEEDBACK_TABLE,null ,contentValues);
        if(result == -1){
            {return false;}
        }

        else{
            return true;}
    }
    public boolean addOffer(int minvalue,int discount) {
        Log.v("qqqqq"," in function");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MIN_VALUE,minvalue);
        contentValues.put(DISCOUNT,discount);
        long result = db.insert(OFFER_TABLE,null ,contentValues);
        if(result == -1){
            {return false;}
        }

        else{
            return true;}
    }

    public boolean deleteOffer(int minvalue) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "min_value=?";
        String min=""+minvalue;
        String whereArgs[] = {min};
        long res=db.delete(OFFER_TABLE, whereClause, whereArgs);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getAllOfferData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+OFFER_TABLE,null);

        return res;
    }
    public Cursor getStaffData(String u,String p) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args={u,p};
        Cursor res = db.rawQuery("select * from "+TABLE_NAME+" where "+COL_1+" = ? and "+COL_2+" = ? ",args);
        return res;
    }
    public boolean insertFoodItem(String id, String cat, String name, String price) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_ID, id);
        contentValues.put(FOOD_CAT, cat);
        contentValues.put(FOOD_NAME, name);
        contentValues.put(FOOD_PRICE, price);
        long result1 = db.insert(FOOD_TABLE, null, contentValues);

        if (result1 == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean insertOrder(int table_no, String food_items,String quant) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ID, table_no);
        contentValues.put(FOOD_ITEMS, food_items);
        contentValues.put(FOOD_QUANTITY,quant);
        contentValues.put(ORDER_STATUS,0);
        Log.v("qqqq"," Im here at db");
        long result1 = db.insert(ORDER_TABLE, null, contentValues);
        Log.v("qqqqq","this "+result1);

        if (result1 == -1) {
            return false;

        } else {
            return true;
        }

    }

    public boolean deleteStaff(String user) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "username=?";
        String whereArgs[] = {user};
        long res=db.delete(TABLE_NAME, whereClause, whereArgs);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
    public boolean deletefood(String id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = "food_id=?";
        String whereArgs[] = {id};
        long res=db.delete(FOOD_TABLE, whereClause, whereArgs);
        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllStaffData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);

        return res;
    }
    public Cursor getAllFoodData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+FOOD_TABLE,null);
        return res;
    }
    public Cursor getAllOrderData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ORDER_TABLE,null);
        return res;
    }
    public  void updateOrderStatus(int id,int status)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(ORDER_STATUS,status);
        String where=ORDER_ID+"="+id;
        db.update(ORDER_TABLE,values,where,null);
    }
    public Cursor getOrder(int table_no)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ORDER_TABLE+" where table_id="+table_no,null);
        return res;
    }
    public Cursor foodPrice(String item)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String [] columns=new String[]{FOOD_PRICE};
        Cursor price=db.query(FOOD_TABLE,columns,FOOD_NAME+"=?",new String[]{item},null,null,null);
        return price;
    }
//    public boolean checkTableNumber(int table_no)
//    {
//        SQLiteDatabase db=this.getWritableDatabase();
//        Cursor res = db.rawQuery("select * from "+ORDER_TABLE+" where table_id="+table_no,null);
//        if(res.getCount()==0)
//            return false;
//        else
//            return true;
//    }

    public Cursor getAllFeedback() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+FEEDBACK_TABLE,null);
        return res;
    }




}