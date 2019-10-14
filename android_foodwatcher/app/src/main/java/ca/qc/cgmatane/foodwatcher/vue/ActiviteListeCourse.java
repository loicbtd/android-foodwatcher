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
import ca.qc.cgmatane.foodwatcher.modele.Produit;

public class ActiviteListeCourse extends ConteneurPrincipal implements ActiviteListeCourseVue {
    private RecyclerView recyclerView;
    private AdapteurListeCourse adapteur;
    private List<Produit> listeProduits;
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
                adapteur.supprSelectionne();
                adapteur.notifyDataSetChanged();
            }
        });
    }
    public void setListeProduits(List<Produit> listeProduits){
        this.listeProduits = listeProduits;
    }
}
