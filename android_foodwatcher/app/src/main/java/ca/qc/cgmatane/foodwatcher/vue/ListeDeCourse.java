package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.modele.ListeCourseAdapteur;

public class ListeDeCourse extends ActiviteMaitresse implements ListeDeCourseVue {
    private RecyclerView recyclerView;
    private ListeCourseAdapteur adapteur;
    private List<String> listeProduits;
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
    }
}
