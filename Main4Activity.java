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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {



    Veritabani2 myDb;
    EditText etAd, etSoyad, etTel,etId;
    Spinner spinner;
    Button btnDuzenle;

    List<String> list;

    Context context=this;
    ArrayAdapter<String> adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        init();
        myDb=new Veritabani2(this);


        etId=(EditText)findViewById(R.id.etId);
        etAd=(EditText) findViewById(R.id.etAd);
        etSoyad=(EditText) findViewById(R.id.etSoyad);
        etTel=(EditText) findViewById(R.id.etTel);
        spinner=(Spinner) findViewById(R.id.spinner);

        btnDuzenle=(Button) findViewById(R.id.btnDuzenle);

        updateData();

    }

    public void updateData()
    {
        btnDuzenle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate=myDb.updateData(etId.getText().toString(),etAd.getText().toString(),etSoyad.getText().toString(),etTel.getText().toString(),spinner.getSelectedItem().toString());
                        if(isUpdate==true)
                            Toast.makeText(Main4Activity.this,"Kisi güncellendi.",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main4Activity.this,"Kisi güncellenemedi.",Toast.LENGTH_LONG).show();

                        Intent listeekrani=new Intent(getApplicationContext(),Main2Activity.class);
                        startActivity(listeekrani);
                    }
                }
        );
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
