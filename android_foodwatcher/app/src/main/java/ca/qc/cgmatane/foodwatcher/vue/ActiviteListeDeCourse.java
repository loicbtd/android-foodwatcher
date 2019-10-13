package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.modele.ListeCourseAdapteur;

public class ActiviteListeDeCourse extends ActiviteMaitresse implements VueListeDeCourse {
    private RecyclerView recyclerView;
    private ListeCourseAdapteur adapteur;
    private List<String> listeProduits;
    private Button boutonListeCourseActionSupprimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_liste_de_course);
        recyclerView = findViewById(R.id.recycler_view_liste_de_courses);
        listeProduits = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listeProduits.add("test");
        }
        adapteur = new ListeCourseAdapteur(listeProduits);
        recyclerView.setAdapter(adapteur);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration itemDecor = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecor);
        boutonListeCourseActionSupprimer = findViewById(R.id.btn_vue_liste_course_action_supprimer);
        boutonListeCourseActionSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapteur.supprSelectionne();
            }
        });
    }
}
