package com.pluartz.test.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.pluartz.test.R;
import com.pluartz.test.model.Pet;

public class PetAdapter extends FirestoreRecyclerAdapter<Pet, PetAdapter.ViewHolder> {
   /**
    * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
    * FirestoreRecyclerOptions} for configuration options.
    *
    * @param options
    */
   public PetAdapter(@NonNull FirestoreRecyclerOptions<Pet> options) {
      super(options);
   }

   @Override
   protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Pet Pet) {
      viewHolder.name.setText(Pet.getName());
      viewHolder.age.setText(Pet.getAge());
      viewHolder.color.setText(Pet.getColor());
   }

   @NonNull
   @Override
   public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pet_single, parent, false);
      return new ViewHolder(v);
   }

   public class ViewHolder extends RecyclerView.ViewHolder {
      TextView name, age, color;

      public ViewHolder(@NonNull View itemView) {
         super(itemView);

         name = itemView.findViewById(R.id.nombre);
         age = itemView.findViewById(R.id.edad);
         color = itemView.findViewById(R.id.color);
      }
   }
}
