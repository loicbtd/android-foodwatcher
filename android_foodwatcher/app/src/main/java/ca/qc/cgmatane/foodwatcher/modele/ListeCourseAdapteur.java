package ca.qc.cgmatane.foodwatcher.modele;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;

public class ListeCourseAdapteur extends RecyclerView.Adapter<ListeCourseAdapteur.ViewHolder>{
    List<Produit> listeProds;
    private int positionSelectionnee = 0;


    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTextView;
        public TextView quantiteTextView;
        public CardView elementListe;
        public ViewHolder(View itemView) {
            super(itemView);
            elementListe = itemView.findViewById(R.id.element_liste_vue_liste_course);
            nameTextView = (TextView) itemView.findViewById(R.id.nom_produit_liste_course);
            quantiteTextView = itemView.findViewById(R.id.quantite_liste_course);
        }
        public void bind(final Produit produit) {
            itemView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View view) {
                    produit.setSelectionne(!produit.estSelectionne());
                    if (produit.estSelectionne()){
                        elementListe.setCardBackgroundColor(Color.argb(255,150,150,150));
                    }else{
                        elementListe.setCardBackgroundColor(Color.argb(255,255,255,255));
                    }
                    return false;
                }
            });
        }
    }
    @Override
    public ListeCourseAdapteur.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activite_liste_course_recycler_view_item_produit, parent, false);
        // Return a new holder instance
        ListeCourseAdapteur.ViewHolder viewHolder = new ListeCourseAdapteur.ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ListeCourseAdapteur.ViewHolder viewHolder, int position) {
        TextView nom = viewHolder.nameTextView;
        TextView quantite = viewHolder.quantiteTextView;
        nom.setText(listeProds.get(position).getEtiquette());
        quantite.setText(Integer.toString(listeProds.get(position).getId_unite_quantite()));
        viewHolder.bind(listeProds.get(position));
    }



    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listeProds.size();
    }
    public ListeCourseAdapteur(List<Produit> produits) {
        listeProds = produits;
    }
    public void supprSelectionne(){
        if (listeProds.size()>0){
        for (int i = 0; i <listeProds.size() ; i++) {
            System.out.println(listeProds.get(i).getEtiquette() + listeProds.get(i).estSelectionne());
            if (listeProds.get(i).estSelectionne()) {
                listeProds.remove(i);
            }
        }

        }

    }
}
