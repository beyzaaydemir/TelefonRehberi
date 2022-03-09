package com.example.beyza.telefonrehberi;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private ListView VeriListele;
    Veritabani2 db;
    //int idBul = 0;
    String idBul,etAd, etSoyad,etTel,etCinsiyet;
    ArrayAdapter<String> adapter;
    Context context=this;
    List<String> list;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        VeriListele = (ListView) findViewById(R.id.VeriListele);
        Listele();
        ListViewItem();

        FloatingActionButton floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                Main2Activity.this.startActivity(intent);
                Main2Activity.this.finish();
            }
        });

    }
    public void Listele()
    {
        Veritabani2 vt=new Veritabani2(Main2Activity.this);
        List<String> list=vt.VeriListele();
        ArrayAdapter<String> adapter=new ArrayAdapter<>(Main2Activity.this,android.R.layout.simple_list_item_1,android.R.id.text1,list);
        VeriListele.setAdapter(adapter);
    }

    public void ListViewItem()
    {
        VeriListele.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //bilgileriCek(VeriListele.getItemAtPosition(i).toString()); //tıklanan veriyi alıp bilgileri çek'e gönderdik

                // Tıklanan verimizi alıyoruz
                String item = VeriListele.getItemAtPosition(i).toString();
                // - Göre bölüyoruz
                String[] itemBol = item.split(" - ");
                // id'mizi alıyoruz
                int idBul = Integer.parseInt(String.valueOf(itemBol[0].toString()).trim());
                etAd=String.valueOf(itemBol[1].toString()).trim();
                etSoyad=String.valueOf(itemBol[2].toString()).trim();
                //etTel=String.valueOf(itemBol[3].toString());
                //etCinsiyet=String.valueOf(itemBol[4].toString());

                // Diğer verilerimizi set ediyoruz.
                // etAd.setText(itemBol[1].toString());
                //etSoyad.setText(itemBol[2].toString());
                //etTel.setText(itemBol[3].toString());
                //etCinsiyet.setText(itemBol[4].toString());
                bilgileriCek(idBul);

            }
        });
    }

    public void bilgileriCek(int kisiId)
    {
        try
        {
            ArrayAdapter<String> arrayAdapter2=new ArrayAdapter<String>(Main2Activity.this, android.R.layout.select_dialog_singlechoice);


            Veritabani2 vt=new Veritabani2(Main2Activity.this);
            // String kisiAdi=vt.VeriGetir(kisiAd);
            //adapter.add(kisiAdi + " \n " + null + " \n " + null + " \n " + null);
//
            String[] sutunlar={"id", "ad", "soyad", "tel", "cinsiyet"};
            db = new Veritabani2(Main2Activity.this);
            SQLiteDatabase sdb=vt.getReadableDatabase();

            Cursor okunanlar=sdb.rawQuery(" select * from kisiler where id like '%"+kisiId+"%' ",null);
            //ad a göre arama yapıldı. 3. parametre where
            //4.parametre where yerine koyacağımız parametre

            if(okunanlar!=null)
            {
                if (okunanlar.moveToFirst()) {
                    do {
                        String ad=okunanlar.getString(okunanlar.getColumnIndex("ad"));
                        String soyad=okunanlar.getString(okunanlar.getColumnIndex("soyad"));
                        String tel=okunanlar.getString(okunanlar.getColumnIndex("tel"));
                        String cinsiyet=okunanlar.getString(okunanlar.getColumnIndex("cinsiyet"));

                        arrayAdapter2.add("\n" + ad + " \n " + soyad + " \n " + tel + " \n " + cinsiyet);
                    } while (okunanlar.moveToNext());
                }
                okunanlar.close();
            }
            cekilenleriGoster(arrayAdapter2);
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(),"Bilgiler getirilirken hata oluştu",Toast.LENGTH_SHORT).show();
        }
    }

    private void cekilenleriGoster(final ArrayAdapter arrayAdapter2)
    {
        AlertDialog.Builder builderSingle=new AlertDialog.Builder(Main2Activity.this);
        builderSingle.setIcon(R.drawable.ic_launcher_background);
        builderSingle.setTitle("Getirilen Kişi");

        builderSingle.setNegativeButton("Çıkış", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String strName=(String)arrayAdapter2.getItem(i);
                AlertDialog.Builder builderInner=new AlertDialog.Builder(Main2Activity.this);
                builderInner.setCancelable(false);
                builderInner.setMessage(strName);

                builderInner.setTitle("Kayıtlı Kişi");
                builderInner.setPositiveButton("DÜZENLE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent duzenlemeekrani=new Intent(getApplicationContext(),Main4Activity.class);
                        startActivity(duzenlemeekrani);
                    }
                });


                builderInner.setNegativeButton("SİL", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface,int i) {


                    }
                });
                builderInner.show();
            }
        });
        builderSingle.show();
    }
}
