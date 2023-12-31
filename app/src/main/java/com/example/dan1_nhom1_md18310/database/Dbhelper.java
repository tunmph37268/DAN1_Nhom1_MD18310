package com.example.dan1_nhom1_md18310.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Dbhelper extends SQLiteOpenHelper {

    public Dbhelper(Context context){
        super(context,"DAN131",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Admin = "CREATE TABLE ADMIN(maAD TEXT PRIMARY KEY,hoTen TEXT ,matKhau TEXT,taiKhoan TEXT)";
        db.execSQL(Admin);
        String Nhanvien= "CREATE TABLE NHANVIEN(maTV INTEGER PRIMARY KEY AUTOINCREMENT,hoTen TEXT,soDT TEXT,luong DOUBLE)";
        db.execSQL(Nhanvien);
        String LoaiHang= "CREATE TABLE LOAIHANG(maLoai INTEGER PRIMARY KEY AUTOINCREMENT,tenLoai TEXT)";
        db.execSQL(LoaiHang);
        String Hang= "CREATE TABLE HANG(maHang INTEGER PRIMARY KEY AUTOINCREMENT,tenHang TEXT,giaHang INTEGER,maLoai INTEGER REFERENCES LOAIHANG(maLoai),RAM TEXT,ROM TEXT,Mau TEXT,soLuong INTEGER)";
        db.execSQL(Hang);
        String HoaDon="CREATE TABLE HOADON(maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT,maHang INTEGER REFERENCES LOAIHANG(maHang), soLuongMua INTEGER ,giaHoaDon INTEGER,Ngay TEXT, traHang INTEGER,RAM TEXT,ROM TEXT,Mau TEXT )";
        db.execSQL(HoaDon);
//        String HoaDonCT="CREATE TABLE HOADONCT(maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT,maHang INTEGER REFERENCES LOAIHANG(maHang),maLoai INTEGER REFERENCES LOAIHANG(maLoai),giaHoaDon INTEGER,RAM TEXT,ROM TEXT,Mau TEXT,soLuongMua INTEGER)";
//        db.execSQL(HoaDonCT);
        String eAdmin="INSERT INTO ADMIN VALUES('tunm','Minh Tu','123','admin'),('toan','Quoc Toan','123','nhanvien')";
        db.execSQL(eAdmin);
        String eNhanvien="INSERT INTO NHANVIEN VALUES(1,'Minh Tu','0976614573',100000),(2,'QuocToan','0972970239',10000990)";
        db.execSQL(eNhanvien);
        String eLoaiHang="INSERT INTO LOAIHANG VALUES(1,'Iphone'),(2,'SamSung'),(3,'Oppo')";
        db.execSQL(eLoaiHang);
        String eHang="INSERT INTO HANG VALUES(1,'Iphone 15',35000000,1,'8gb','128','Xanh',100),(2,'SamSung JS',12000000,2,'8gb','128','Cam',34)";
        db.execSQL(eHang);
        String eHoaDon="INSERT INTO HOADON VALUES(1,1,2,70000000,'2023-11-22',1,'8gb','128','Xanh'),(2,2,3,36000000,'2023-11-23',0,'8gb','128','Cam')";
        db.execSQL(eHoaDon);
//        String eHoaDonCT="INSERT INTO HOADONCT VALUES(1,1,1,70000000,'8gb','128','Xanh',2),(2,2,2,36000000,'8gb','128','Cam',3)";
//        db.execSQL(eHoaDonCT);

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS ADMIN");
            db.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            db.execSQL("DROP TABLE IF EXISTS LOAIHANG");
            db.execSQL("DROP TABLE IF EXISTS HANG");
            db.execSQL("DROP TABLE IF EXISTS HOADON");
            db.execSQL("DROP TABLE IF EXISTS HOADONCT");
            onCreate(db);
        }
    }
}
