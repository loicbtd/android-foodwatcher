package ca.qc.cgmatane.foodwatcher.modele;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;

public class ListeCourseAdapteur extends RecyclerView.Adapter<ListeCourseAdapteur.ViewHolder>{
    List<String> listeProds;
    private int checkedPosition = 0;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public CardView elementListe;
        public ViewHolder(View itemView) {
            super(itemView);
            elementListe = itemView.findViewById(R.id.element_liste_vue_liste_course);
            nameTextView = (TextView) itemView.findViewById(R.id.nom_produit_liste_course);
        }
        public void bind(String produit) {
            if (checkedPosition == -1) {
                nameTextView.setText("non selectionné");
                elementListe.setCardBackgroundColor(Color.argb(255,255,255,255));
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    nameTextView.setText("selectionné");
                } else {
                    nameTextView.setText("non selectionné");
                    elementListe.setCardBackgroundColor(Color.argb(255,255,255,255));
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nameTextView.setText("selectionné");
                    elementListe.setCardBackgroundColor(Color.argb(255,150,150,150));
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                }
            });
        }
    }
    @Override
    public ListeCourseAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.vue_liste_course_recycler_view_item_produit, parent, false);
        // Return a new holder instance
        ListeCourseAdapteur.ViewHolder viewHolder = new ListeCourseAdapteur.ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListeCourseAdapteur.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        // Set item views based on your views and data model
//        TextView textView = viewHolder.nameTextView;
//        TextView textViewNbRestants = viewHolder.nbRestants;
//        textView.setText(listeProds.get(position));
//        textViewNbRestants.setText("3");
//        Button button = viewHolder.messageButton;
//        Button bouton2 = viewHolder.boutonPlus;
//        button.setText("-");
//        bouton2.setText("+");
        viewHolder.bind(listeProds.get(position));
    }



    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listeProds.size();
    }
    public ListeCourseAdapteur(List<String> produits) {
        listeProds = produits;
    }
    public void supprSelectionne(){
        if (checkedPosition != -1) {
             listeProds.remove(checkedPosition);
             notifyItemRemoved(checkedPosition);
        }
    }
}
