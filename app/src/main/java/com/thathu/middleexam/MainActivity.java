package com.thathu.middleexam;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {
    private TextView tvHoTen, tvNgaySinh, tvSdt, tvEmail;
    private Spinner spQuequan;
    private Button btnSubmit;
    private ImageView imageView;
    private String[] arrayQuequan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ANh xa
        tvHoTen = (TextView) findViewById(R.id.editHoTen);
        tvNgaySinh = (TextView) findViewById(R.id.editNgaySinh);
        tvSdt = (TextView) findViewById(R.id.editSdt);
        tvEmail = (TextView) findViewById(R.id.editEmail);
        imageView = (ImageView) findViewById(R.id.imgView);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        spQuequan = (Spinner) findViewById(R.id.spQUequan);

        arrayQuequan = new String[]{
                "Hà Nội",
                "Hải Phòng",
                "Nam Định"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_item,
                arrayQuequan
        );
        spQuequan.setAdapter(adapter);

        // Set click
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = tvHoTen.getText().toString();
                String ngaysinh = tvNgaySinh.getText().toString();
                String sdt = tvSdt.getText().toString();
                String email = tvEmail.getText().toString();


                if (isEmpty(hoten)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập họ tên", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isEmpty(ngaysinh)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập ngày sinh", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmpty(sdt)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập SDT", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isEmpty(email)) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                String que = spQuequan.getSelectedItem().toString();
                String bm = getByteStr(imageView);

                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                Bundle bundle = new Bundle();
                bundle.putString("hoten", hoten);
                bundle.putString("ngaysinh", ngaysinh);
                bundle.putString("sdt", sdt);
                bundle.putString("email", email);
                bundle.putString("quequan", que);
                bundle.putString("bitmap", bm);

                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
    }

    private boolean isEmpty(String x) {

        if (x.isEmpty())
            return true;
        return false;
    }

    private String getByteStr(ImageView img){
        BitmapDrawable bm = (BitmapDrawable) img.getDrawable();
        Bitmap bitmap = bm.getBitmap();
        ByteArrayOutputStream out =new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        byte[] bytes = out.toByteArray();
        String str = Base64.encodeToString(bytes, Base64.DEFAULT);
        return str;
    }




}

