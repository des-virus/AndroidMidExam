package com.thathu.middleexam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private TextView tvHoTen, tvNgaySinh, tvSdt, tvEmail;
    private TextView spQuequan;
    private Button btnSubmit;
    private ImageView imageView;
    private String[] arrayQuequan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Anh xa
        tvHoTen = (TextView) findViewById(R.id.editHoTen1);
        tvNgaySinh = (TextView) findViewById(R.id.editNgaySinh1);
        tvSdt = (TextView) findViewById(R.id.editSdt1);
        tvEmail = (TextView) findViewById(R.id.editEmail1);
        btnSubmit = (Button) findViewById(R.id.btnBack);
        spQuequan = (TextView) findViewById(R.id.spQUequan1);
        imageView = (ImageView) findViewById(R.id.imgView2);

        // Lay bundle

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String hoten = bundle.get("hoten").toString();
            String ngaysinh =  bundle.get("ngaysinh").toString();
            String sdt = bundle.get("sdt").toString();
            String email = bundle.get("email").toString();
            String que =  bundle.get("quequan").toString();
            String bm = bundle.get("bitmap").toString();

            tvHoTen.setText(hoten);
            tvNgaySinh.setText(ngaysinh);
            tvSdt.setText(sdt);
            tvEmail.setText(email);
            spQuequan.setText(que);
            imageView.setImageBitmap(getBitmap(bm));
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private Bitmap getBitmap(String str){
        byte[] bytes = Base64.decode(str, Base64.DEFAULT);
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return bm;
    }
}
