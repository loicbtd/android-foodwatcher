package ca.qc.cgmatane.foodwatcher.donnees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;

public class ProduitAdapter extends
        RecyclerView.Adapter<ProduitAdapter.ViewHolder> {
        List<String> listeProds;
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button messageButton;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.nom_produit);
            messageButton = (Button) itemView.findViewById(R.id.bouton_test);
        }
    }
    @Override
    public ProduitAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_produit, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ProduitAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position


        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText("nom du produit");
        Button button = viewHolder.messageButton;
        button.setText("bouton");
        button.setEnabled(true);
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return 10;
    }
    public ProduitAdapter(List<String> produits) {
        listeProds = produits;
    }
}