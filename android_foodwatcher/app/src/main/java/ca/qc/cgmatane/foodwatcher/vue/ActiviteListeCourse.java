package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteListeDeCourse;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurConteneurPrincipal;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.Produit;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;

public class ActiviteListeCourse extends ConteneurPrincipal implements ActiviteListeCourseVue {
    private RecyclerView recyclerView;
    private AdapteurListeCourse adapteur;
    private List<ProduitStocke> listeProduits;
    private Button boutonListeCourseActionSupprimer;
    private ControleurActiviteListeDeCourse controleur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_liste_course);
        controleur = new ControleurActiviteListeDeCourse(this);
        recyclerView = findViewById(R.id.recycler_view_liste_de_courses);
        controleur.onCreate(getApplicationContext());
        adapteur = new AdapteurListeCourse(listeProduits);
        recyclerView.setAdapter(adapteur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        boutonListeCourseActionSupprimer = findViewById(R.id.btn_vue_liste_course_action_supprimer);
        boutonListeCourseActionSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                controleur.actionSupprimerSelection();
            }
        });
    }
    @Override
    public void supprimerSelection(){
        if (listeProduits.size()>0){
            for (int i = listeProduits.size()-1; i >=0 ; i--) {
                System.out.println(listeProduits.get(i).getEtiquette() + listeProduits.get(i).isSelectionne());
                if (listeProduits.get(i).isSelectionne()) {
                    ProduitStockeDAO.getInstance().supprimerProduitDuStock(listeProduits.get(i));
                    listeProduits.remove(i);
                    adapteur.notifyDataSetChanged();
                }
            }
        }
    }
    public void setListeProduits(List<ProduitStocke> listeProduits){
        this.listeProduits = listeProduits;
    }
}
