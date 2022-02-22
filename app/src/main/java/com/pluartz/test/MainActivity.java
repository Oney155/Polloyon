package com.pluartz.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.pluartz.test.adapter.PetAdapter;
import com.pluartz.test.model.Pet;

public class MainActivity extends AppCompatActivity {

   Button btn_add, btn_add_fragment;
   RecyclerView mRecycler;
   PetAdapter mAdapter;
   FirebaseFirestore mFirestore;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      mFirestore = FirebaseFirestore.getInstance();
      mRecycler = findViewById(R.id.recyclerViewSingle);
      mRecycler.setLayoutManager(new LinearLayoutManager(this));
      Query query = mFirestore.collection("pet");

      FirestoreRecyclerOptions<Pet> firestoreRecyclerOptions =
              new FirestoreRecyclerOptions.Builder<Pet>().setQuery(query, Pet.class).build();

      mAdapter = new PetAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
      mAdapter.notifyDataSetChanged();
      mRecycler.setAdapter(mAdapter);

      btn_add = findViewById(R.id.btn_add);
      btn_add_fragment = findViewById(R.id.btn_add_fragment);
      btn_add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            startActivity(new Intent(MainActivity.this, CreatePetActivity.class));
         }
      });
      btn_add_fragment.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
            CreatePetFragment fm = new CreatePetFragment();
            fm.show(getSupportFragmentManager(), "Navegar a fragment");
         }
      });
   }

   @Override
   protected void onStart() {
      super.onStart();
      mAdapter.startListening();
   }

   @Override
   protected void onStop() {
      super.onStop();
      mAdapter.stopListening();
   }
}