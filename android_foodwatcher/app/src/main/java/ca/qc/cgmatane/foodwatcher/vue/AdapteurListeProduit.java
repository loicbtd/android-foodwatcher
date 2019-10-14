package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;

public class AdapteurListeProduit extends RecyclerView.Adapter<AdapteurListeProduit.ViewHolder> {
        List<ProduitStocke> listeProds;
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // for an        // Your holder should contain a member variabley view that will be set as you render a row
        public TextView nameTextView;
        public TextView nbRestants;
        public Button boutonMoins;
        public Button boutonPlus;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nom_produit);
            nbRestants = itemView.findViewById(R.id.nbrestants);
            boutonMoins = itemView.findViewById(R.id.bouton_moins);
            boutonPlus = itemView.findViewById(R.id.bouton_plus);
        }
    }
    @Override
    public AdapteurListeProduit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activite_stock_recycler_view_item_produit, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(AdapteurListeProduit.ViewHolder viewHolder, final int position) {
        // Get the data model based on position
        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        TextView textViewNbRestants = viewHolder.nbRestants;
        textView.setText(listeProds.get(position).getEtiquette());
        textViewNbRestants.setText(Double.toString(listeProds.get(position).getQuantite()));
        Button boutonMoins = viewHolder.boutonMoins;
        Button boutonPlus = viewHolder.boutonPlus;
        boutonMoins.setText("-");
        boutonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProduitStocke produit = listeProds.get(position);
                produit.setQuantite(produit.getQuantite()-1);
            }
        });
        boutonPlus.setText("+");
        boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProduitStocke produit = listeProds.get(position);
                produit.setQuantite(produit.getQuantite()+1);
            }
        });
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listeProds.size();
    }
    public AdapteurListeProduit(List<ProduitStocke> produits) {
        listeProds = produits;
    }
}