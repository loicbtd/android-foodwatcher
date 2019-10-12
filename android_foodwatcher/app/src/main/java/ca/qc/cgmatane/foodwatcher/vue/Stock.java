package ca.qc.cgmatane.foodwatcher.vue;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurStock;
import ca.qc.cgmatane.foodwatcher.modele.ProduitAdapter;

public class Stock extends ActiviteMaitresse implements StockVue {
    private RecyclerView recyclerView;
    private ProduitAdapter adapter;
    private List<String> listeProduits;
    private Bitmap icon;
    private Button btn_view_stock_add_product;
    private ControleurStock stockController = new ControleurStock(this);
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.vue_stock);

        btn_view_stock_add_product = findViewById(R.id.btn_view_stock_add_product);
        btn_view_stock_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockController.actionNavigateToViewAddProduct();
            }
        });
        recyclerView = findViewById(R.id.my_recycler_view);
        listeProduits = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listeProduits.add("test");
        }
        adapter = new ProduitAdapter(listeProduits);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        stockController.onCreate(getApplicationContext());
//        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_add_home).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }

    public void ajouterProduitListe(){
        listeProduits.add("element ajouté");
        adapter.notifyItemInserted(listeProduits.size()-1);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            listeProduits.remove(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
                Paint p = new Paint();
                View itemView = viewHolder.itemView;
                //créé un drawable a partir de ic_delete_36 et conversion du drawable en bitmap
                Drawable d = getResources().getDrawable(R.drawable.ic_delete_36);
                icon = drawableToBitmap(d);
                if (dX>0){
                    p.setARGB(255,255,50,50);
                    //dessine le rectangle rouge
//                    c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(),dX + 18, (float) itemView.getBottom(),p);
                    c.drawBitmap(icon, (float) itemView.getLeft()+ dpFromPx(100), (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,p );
                }  else {
                    p.setARGB(255,255,50,50);
                    //dessine le rectangle rouge
//                    c.drawRect((float) itemView.getRight()+dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom(),p);
                    c.drawBitmap(icon, (float) itemView.getRight() - dpFromPx(100)-icon.getWidth(), (float) itemView.getTop() + ((float) itemView.getBottom() - (float) itemView.getTop() - icon.getHeight())/2,p );
                }

            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };
    public float dpFromPx( final float px) {
        return px / this.getResources().getDisplayMetrics().density;
    }
    public  Bitmap drawableToBitmap (Drawable drawable) {
        //besoin de cette fonction car BitmapFactory renvoyait null quand on essaye de creer une icone bitmap a partir d un drawable
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public void naviguerVueAjouterProduit(){
        startActivityForResult(new Intent(this, AjouterProduit.class), ControleurStock.ADD_PRODUCT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        stockController.onActivityResult(requestCode);
    }
}
