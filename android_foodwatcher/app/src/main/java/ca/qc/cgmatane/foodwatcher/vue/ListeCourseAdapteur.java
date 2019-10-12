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

class ListeCourseAdapteur extends RecyclerView.Adapter<ListeCourseAdapteur.ViewHolder>{
    List<String> listeProds;
    public class ViewHolder extends RecyclerView.ViewHolder {
        // for an        // Your holder should contain a member variabley view that will be set as you render a row
//        public TextView nameTextView;
//        public TextView nbRestants;
//        public Button messageButton;
//        public Button boutonPlus;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            /*nameTextView = (TextView) itemView.findViewById(R.id.nom_produit);
            nbRestants = itemView.findViewById(R.id.nbrestants);
            messageButton = (Button) itemView.findViewById(R.id.bouton_test);
            boutonPlus = itemView.findViewById(R.id.bouton_test2);*/
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
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return listeProds.size();
    }
    public ListeCourseAdapteur(List<String> produits) {
        listeProds = produits;
    }
}