package com.example.asus.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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

public class Main3Activity extends AppCompatActivity {

    Veritabani myDb;
    EditText etAd, etSoyad, etTel,etId;
    Spinner spinner;
    Button btnKaydet;
    Button btnListele;
    Button btnSil;
    Button btnDuzenle;


    List<String> list;

    Context context=this;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        init();
        myDb=new Veritabani(this);


        etId=(EditText)findViewById(R.id.etId);
        etAd=(EditText) findViewById(R.id.etAd);
        etSoyad=(EditText) findViewById(R.id.etSoyad);
        etTel=(EditText) findViewById(R.id.etTel);
        spinner=(Spinner) findViewById(R.id.spinner);
        btnKaydet=(Button) findViewById(R.id.btnKaydet);
        btnListele=(Button) findViewById(R.id.btnListele);
        btnSil=(Button) findViewById(R.id.btnSil);
        btnDuzenle=(Button) findViewById(R.id.btnDuzenle);
        AddData();
        viewAll();
       // updateData();
        deleteData();
    }

    public void AddData()
    {
        btnKaydet.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted=myDb.insertData(etAd.getText().toString(),etSoyad.getText().toString(),etTel.getText().toString(),spinner.getSelectedItem().toString());
                        if(isInserted==true)
                            Toast.makeText(Main3Activity.this,"Kayit Basarili",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Kayıt Basarisiz",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void viewAll()
    {
        btnListele.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res=myDb.getAllData();
                        if(res.getCount()==0) {
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer=new StringBuffer();
                        while (res.moveToNext())
                        {
                            buffer.append("ID:" +res.getInt(0) + "\n");
                            buffer.append("AD:" +res.getString(1)+ "\n");
                            buffer.append("SOYAD:" +res.getString(2)+ "\n");
                            buffer.append("CINSIYET:" +res.getString(3)+ "\n");
                            buffer.append("TELEFON:" +res.getString(4)+ "\n\n\n");
                        }
                        showMessage("KAYITLI KISILER",buffer.toString());
                    }
                }
        );
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
/*
    public void updateData()
    {
        btnDuzenle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate=myDb.updateData(etId.getText().toString(),etAd.getText().toString(),etSoyad.getText().toString(),etTel.getText().toString(),spinner.getSelectedItem().toString());
                        if(isUpdate==true)
                            Toast.makeText(Main3Activity.this,"Kisiler guncellendi.",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Kisiler guncellenemedi.",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }*/

    public void deleteData()
    {
        btnSil.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows=myDb.deleteData(etId.getText().toString());
                        if(deletedRows>0)
                            Toast.makeText(Main3Activity.this,"Kisi silindi.",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Main3Activity.this,"Kisi silinemedi",Toast.LENGTH_LONG).show();
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
