package com.pluartz.test;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateActivity extends AppCompatActivity {

   Button btn_agg;
   EditText name, age, color;
   private FirebaseFirestore mFirestore;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_create);
      this.setTitle("Crear mascota");
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      mFirestore = FirebaseFirestore.getInstance();

      name = findViewById(R.id.nombre);
      age = findViewById(R.id.edad);
      color = findViewById(R.id.color);
      btn_agg = findViewById(R.id.btn_agregar);

      btn_agg.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            String nombreMascota = name.getText().toString().trim();
            String edadMascota = age.getText().toString().trim();
            String colorMascota = color.getText().toString().trim();

            if (nombreMascota.isEmpty() && edadMascota.isEmpty() && colorMascota.isEmpty()){
               Toast.makeText(getApplicationContext(), "Ingresar datos", Toast.LENGTH_SHORT).show();
            }else{
               postmascota(nombreMascota, edadMascota, colorMascota);
            }
         }
      });
   }

   private void postmascota(String nombreMascota, String edadMascota, String colorMascota) {
      Map<String, Object> map = new HashMap<>();
      map.put("name", nombreMascota);
      map.put("age", edadMascota);
      map.put("color", colorMascota);
      mFirestore.collection("mascota").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
         @Override
         public void onSuccess(DocumentReference documentReference) {
            Toast.makeText(getApplicationContext(), "Mascota ingresada correctamente", Toast.LENGTH_SHORT).show();
            finish();
         }
      }).addOnFailureListener(new OnFailureListener() {
         @Override
         public void onFailure(@NonNull Exception e) {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
         }
      });
   }

   @Override
   public boolean onSupportNavigateUp() {
      onBackPressed();
      return false;
   }
}