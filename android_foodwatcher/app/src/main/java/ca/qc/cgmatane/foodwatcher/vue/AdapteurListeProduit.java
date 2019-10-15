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
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;

public class AdapteurListeProduit extends RecyclerView.Adapter<AdapteurListeProduit.ViewHolder> {
    protected List<ProduitStocke> listeProds;
    protected ProduitStockeDAO accesseurProduitStocke;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nameTextView;
        public TextView nbRestants;
        public TextView uniteQuantiteTextView;
        public Button boutonMoins;
        public Button boutonPlus;


        public ViewHolder(View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nom_produit);
            nbRestants = itemView.findViewById(R.id.nbrestants);
            boutonMoins = itemView.findViewById(R.id.bouton_moins);
            boutonPlus = itemView.findViewById(R.id.bouton_plus);
            uniteQuantiteTextView = itemView.findViewById(R.id.textView_unite_quantite_vue_stock);

        }
    }
    @Override
    public AdapteurListeProduit.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        accesseurProduitStocke = ProduitStockeDAO.getInstance();

        View contactView = inflater.inflate(R.layout.activite_stock_recycler_view_item_produit, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AdapteurListeProduit.ViewHolder viewHolder, final int position) {

        TextView textView = viewHolder.nameTextView;
        TextView textViewNbRestants = viewHolder.nbRestants;
        TextView uniteQuantite = viewHolder.uniteQuantiteTextView;
        textView.setText(listeProds.get(position).getEtiquette());
        textViewNbRestants.setText(Double.toString(listeProds.get(position).getQuantite()));
        uniteQuantite.setText(listeProds.get(position).getUniteQuantite().getEtiquette());
        Button boutonMoins = viewHolder.boutonMoins;
        Button boutonPlus = viewHolder.boutonPlus;
        boutonMoins.setText("-");
        boutonMoins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProduitStocke produit = listeProds.get(position);
                if (produit.getQuantite()>0){
                    produit.setQuantite(produit.getQuantite()-1);
                    accesseurProduitStocke.modifierProduitStocke(produit);
                    notifyItemChanged(position);
                }
            }
        });
        boutonPlus.setText("+");
        boutonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProduitStocke produit = listeProds.get(position);
                produit.setQuantite(produit.getQuantite()+1);
                accesseurProduitStocke.modifierProduitStocke(produit);
                notifyItemChanged(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return listeProds.size();
    }
    public AdapteurListeProduit(List<ProduitStocke> produits) {
        listeProds = produits;
    }
}