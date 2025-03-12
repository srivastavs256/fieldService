package com.mini.fieldServices;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FirebaseViewHolder extends RecyclerView.ViewHolder {
    public TextView name ,details,date;
    public FirebaseViewHolder(@NonNull View itemView) {
        super(itemView);
        name= itemView.findViewById(R.id.name);
        date=itemView.findViewById(R.id.date);
        details=itemView.findViewById(R.id.desc);

    }


}
