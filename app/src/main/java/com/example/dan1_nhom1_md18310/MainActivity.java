package com.example.dan1_nhom1_md18310;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.dan1_nhom1_md18310.DAO.DoiMKDao;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ImageButton hang,donhang,thanhvien,doanhthu;

    QLThanhVienFragment thanhVienFragment;
    TKDoanhThuFragment DoanhThuFragment;
    TThanhVienFragment tThanhVienFragment;
    FragmentThongKe fragmentThongKe;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         hang=findViewById(R.id.img_hang);
         donhang=findViewById(R.id.img_donhang);
         thanhvien=findViewById(R.id.img_thanhvien);
         doanhthu=findViewById(R.id.img_doanhthu);
        hang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo Fragment mới
                Fragment fragment = new QLHangFragment();

                // Lấy ra FragmentManager từ Activity
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Thực hiện thay thế Fragment hiện tại bằng Fragment mới
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, fragment)  // R.id.FrameLayout là id của FrameLayout trong layout chứa Fragment
                        .addToBackStack(null)  // Cho phép người dùng quay trở lại Fragment trước đó bằng nút back
                        .commit();
            }
        });
        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo Fragment mới
                Fragment fragment = new QLHoaDonFragment();

                // Lấy ra FragmentManager từ Activity
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Thực hiện thay thế Fragment hiện tại bằng Fragment mới
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, fragment)  // R.id.FrameLayout là id của FrameLayout trong layout chứa Fragment
                        .addToBackStack(null)  // Cho phép người dùng quay trở lại Fragment trước đó bằng nút back
                        .commit();
            }
        });
        thanhvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo Fragment mới
                Fragment fragment = new QLThanhVienFragment();

                // Lấy ra FragmentManager từ Activity
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Thực hiện thay thế Fragment hiện tại bằng Fragment mới
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, fragment)  // R.id.FrameLayout là id của FrameLayout trong layout chứa Fragment
                        .addToBackStack(null)  // Cho phép người dùng quay trở lại Fragment trước đó bằng nút back
                        .commit();
            }
        });
        doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Khởi tạo Fragment mới
                Fragment fragment = new FragmentThongKe();

                // Lấy ra FragmentManager từ Activity
                FragmentManager fragmentManager = getSupportFragmentManager();

                // Thực hiện thay thế Fragment hiện tại bằng Fragment mới
                fragmentManager.beginTransaction()
                        .replace(R.id.FrameLayout, fragment)  // R.id.FrameLayout là id của FrameLayout trong layout chứa Fragment
                        .addToBackStack(null)  // Cho phép người dùng quay trở lại Fragment trước đó bằng nút back
                        .commit();
            }
        });
        Toolbar toolbar = findViewById(R.id.Toolbar);
        FrameLayout frameLayout = findViewById(R.id.FrameLayout);
        NavigationView nav = findViewById(R.id.Nav);
        drawerLayout = findViewById(R.id.DrawerLayout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.menu);


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                if (item.getItemId() == R.id.mnQLH){
                    fragment = new QLHangFragment();

                } else if (item.getItemId() == R.id.mnQLLH) {
                    fragment = new QLLHangFragment();

                } else if (item.getItemId() == R.id.mnHome) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);}

                else if (item.getItemId() == R.id.mnQLHD) {
                    fragment = new QLHoaDonFragment();

                } else if (item.getItemId() == R.id.mnQLTV) {
                    fragment = new QLThanhVienFragment();

                } else if (item.getItemId() == R.id.mnDoanhthu) {
                    fragment = new FragmentThongKe();
                    
                }else if (item.getItemId() == R.id.mnTaoTaiKhoan) {
                    fragment = new FragmentTaoTaikhoan();

                } else if (item.getItemId() == R.id.mnDMK) {
                    showDialogDoiMK();


                } else if (item.getItemId() == R.id.mnDX) {
                    Intent intent = new Intent(MainActivity.this, DangNhap.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    fragment = new QLHangFragment();
                }

                if (fragment != null) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction()
                            .replace(R.id.FrameLayout, fragment)
                            .commit();

                    drawerLayout.closeDrawer(GravityCompat.START);

                    toolbar.setTitle(item.getTitle());
                }
                return false;
            }
        });

        SharedPreferences preferences = getSharedPreferences("ThongTin",MODE_PRIVATE);
        String loaiTk= preferences.getString("taiKhoan","");
        if (!loaiTk.equals("admin")){
            ImageButton imageButton=findViewById(R.id.img_thanhvien);
            imageButton.setEnabled(false);
            imageButton.setOnClickListener(null);
            ImageButton imageButton1=findViewById(R.id.img_doanhthu);
            imageButton1.setEnabled(false);
            imageButton1.setOnClickListener(null);
            Menu menu = nav.getMenu();
            menu.findItem(R.id.mnQLTV).setVisible(false);
            menu.findItem(R.id.mnDoanhthu).setVisible(false);
            menu.findItem(R.id.mnTaoTaiKhoan).setVisible(false);
        }

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }

    //////////////////////////////////////////////////////////////////////////////////////////////
    private void showDialogDoiMK() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this).setNegativeButton("Cập nhật", null).setPositiveButton("Hủy", null);

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_doimatkhau, null);
        builder.setView(view);

        EditText edtPassOld = view.findViewById(R.id.edtPassOld);
        EditText edtPassNew= view.findViewById(R.id.edtPassNew);
        EditText edtReNewPass = view.findViewById(R.id.edtReNewPass);


        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passOld = edtPassOld.getText().toString();
                String passNew = edtPassNew.getText().toString();
                String renewpass = edtReNewPass.getText().toString();

                if(passOld.equals("") || passNew.equals("") || renewpass.equals("")){
                    Toast.makeText(MainActivity.this, "Không được bỏ trống thống tin", Toast.LENGTH_SHORT).show();
                }

                if (passNew.equals(renewpass)){
                    SharedPreferences sharedPreferences = getSharedPreferences("ThongTin", MODE_PRIVATE);
                    String maAD = sharedPreferences.getString("maAD", "");
                    DoiMKDao doiMKDao = new DoiMKDao(MainActivity.this);
                    if(doiMKDao.doiMatKhau(maAD, passOld, passNew) == 1){
                        Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this, DangNhap.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                    else if(doiMKDao.doiMatKhau(maAD, passOld, passNew) == 0){
                        Toast.makeText(MainActivity.this, "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(MainActivity.this, "Cập nhật mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                    Toast.makeText(MainActivity.this, "Xác nhân lại mật khẩu không chính xác", Toast.LENGTH_SHORT).show();

            }
        });
    }

    }
