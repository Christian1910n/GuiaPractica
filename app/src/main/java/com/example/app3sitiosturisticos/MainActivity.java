package com.example.app3sitiosturisticos;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.example.app3sitiosturisticos.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Bitmap bitmap;
    ActivityResultLauncher<Intent> activitResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnGuardar.setOnClickListener(v -> {
            String nombre=binding.txtNombre.getText().toString();
            String ubicacion=binding.txtUbicacion.getText().toString();
            String informacion=binding.txtInformacion.getText().toString();
            float valoracion= binding.rtbValoracion.getRating();
            abrirActivityDetalle(nombre,ubicacion,informacion,valoracion);
        });

        activitylauncher();
        binding.imgSitio.setOnClickListener(v -> {
            abrircamara();
        });
    }

    private void abrircamara(){
        Intent camaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //startActivity(camaraIntent);
        //startActivityForResult(camaraIntent, 1000);
        activitResultLauncher.launch(camaraIntent);
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK && requestCode== 1000){
            if(data!=null){
                bitmap = data.getExtras().getParcelable("data");
                binding.imgSitio.setImageBitmap(bitmap);

            }
        }

    }*/

    public void activitylauncher(){
        activitResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode()==RESULT_OK){
                    if(result.getData() != null){
                        bitmap=result.getData().getExtras().getParcelable("data");
                        binding.imgSitio.setImageBitmap(bitmap);
                    }
                }
            }
        });
    }

    private void abrirActivityDetalle(String nom, String ubi, String inf, float val){
        Intent intent = new Intent(this,ActivityDetalle.class);

        Sitioturistico sitio = new Sitioturistico(nom,ubi, inf,val);
        intent.putExtra(ActivityDetalle.SITIO_TURISTICO_KEY, sitio);
        intent.putExtra(ActivityDetalle.BITMAP_KEY, bitmap);

        startActivity(intent);
    }
}