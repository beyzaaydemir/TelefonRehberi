package com.example.beyza.telefonrehberi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    private EditText etAd,etSoyad,etTel;
    private Button btnKaydet;
    private Spinner spinner;

    List<String> list;
    Context context=this;
    ArrayAdapter<String> adapter;
    Main2Activity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();

        etAd=(EditText)findViewById(R.id.etAd);
        etSoyad=(EditText)findViewById(R.id.etSoyad);
        etTel=(EditText)findViewById(R.id.etTel);
        spinner=(Spinner)findViewById(R.id.spinner);
        btnKaydet=(Button)findViewById(R.id.btnKaydet);




        btnKaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String gelenAd=etAd.getText().toString();
                String gelenSoyad=etSoyad.getText().toString();
                String gelenTel=etTel.getText().toString();
                String gelenCinsiyet=spinner.getSelectedItem().toString();

                Veritabani2 vt=new Veritabani2(Main3Activity.this);
                vt.VeriEkle(gelenAd, gelenSoyad, gelenTel, gelenCinsiyet);


                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                Main3Activity.this.startActivity(intent);
                Main3Activity.this.finish();


            }
        });

    }
    public void init()
    {
        list=new ArrayList<>();

        list.add("Kadın");
        list.add("Erkek");

        spinner=(Spinner) findViewById(R.id.spinner);
        adapter=new ArrayAdapter<>(context,android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(adapter);

    }
}
