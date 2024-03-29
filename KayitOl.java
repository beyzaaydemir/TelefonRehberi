package com.example.beyza.telefonrehberi;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class KayitOl extends AppCompatActivity {


    EditText kayit_ad, kayit_email, kayit_sifre;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayit_ol);

        //EditText ler tanımlanır
        kayit_ad = (EditText) findViewById(R.id.kayit_ad);
        kayit_email = (EditText) findViewById(R.id.kayit_email);
        kayit_sifre = (EditText) findViewById(R.id.kayit_sifre);
    }


    public void KayitIslem(View v) {
        switch (v.getId())
        {
            case R.id.kayit_tamamla:
                //EditTextlerdeki girdiler String olarak tutuluyor.
                String kullaniciadi = kayit_ad.getText().toString();
                String sifresi = kayit_sifre.getText().toString();
                String emaili = kayit_email.getText().toString();

                try {
                    if (kullaniciadi.isEmpty()||sifresi.isEmpty()||emaili.isEmpty())
                        Toast.makeText(getApplicationContext(), "Alanlar boş geçilemez!", Toast.LENGTH_SHORT).show();
                    else
                    {
                        //Bilgiler türünde bir k1 nesnesi,Bilgiler class nın parametrelerine göre değerleri alıyor.
                        Bilgiler k1 = new Bilgiler(kullaniciadi, sifresi, emaili);
                        //Bir veritabanı bağlantısı açılıyor.
                        Veritabani db = new Veritabani(getApplicationContext());
                        //Veritabanında bulunan KayıtEkle metodunun dönüş tipi long'tur.
                        //Burada da long türünden bir değişkene KayıtEkle methodunun döndürdüğü değeri veriyoruz.
                        //Böylece kayıt başarılı mı değil mi bunu anlıyoruz.
                        long id = db.KayıtEkle(k1);
                        //Bu değer -1 dönmemeli.Eğer -1 dönüyorsa kayıt başarılı değil ve bir sorun vardır diyoruz.
                        if (id == -1)
                        {
                            Toast.makeText(KayitOl.this, " Kayıt işlemi hatalı!", Toast.LENGTH_SHORT).show();
                        }
                        //Başarılı olması durumunda buradan o satırın id değeri dönecektir.
                        else
                            Toast.makeText(getApplicationContext(), "Kayıt işlemi başarılı.", Toast.LENGTH_SHORT).show();
                            Intent intentgeri=new Intent(getApplicationContext(),LoginEkrani.class);
                            startActivity(intentgeri);
                    }

                } catch (Exception e) {

                    Toast.makeText(KayitOl.this, "Bilinmeyen Hata!\n" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.vazgec:
                //Vazgeç butonuna basıldığında yazılar temizlenecek ve Ana ekrana geri dönüş olacaktır.
                kayit_ad.getText().clear();
                kayit_sifre.getText().clear();
                kayit_email.getText().clear();

                Intent intentgeri=new Intent(getApplicationContext(),LoginEkrani.class);
                startActivity(intentgeri);

                break;
        }
    }
}
