package ca.qc.cgmatane.foodwatcher.vue;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import ca.qc.cgmatane.foodwatcher.R;

public class ListeDeCourse extends ActiviteMaitresse implements ListeDeCourseVue {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_liste_de_course);
    }
}
