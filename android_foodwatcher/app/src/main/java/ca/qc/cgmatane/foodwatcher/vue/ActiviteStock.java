package ca.qc.cgmatane.foodwatcher.vue;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;
import ca.qc.cgmatane.foodwatcher.controleur.ControleurActiviteStock;
import ca.qc.cgmatane.foodwatcher.donnees.ProduitStockeDAO;
import ca.qc.cgmatane.foodwatcher.modele.ProduitStocke;

public class ActiviteStock extends ConteneurPrincipal implements ActiviteStockVue {

    private static final String CHANNEL_ID = "myChannel";
    public static final String TITRE_NOTIFICATION = "TITRE";

    private RecyclerView recyclerView;
    private AdapteurListeProduit adapter;
    private Bitmap icon;
    private Button btn_view_stock_add_product;
    protected List<ProduitStocke> listeProduits;
    protected int idStock;
    protected ProduitStockeDAO accesseurProduitStocke;
    private ControleurActiviteStock stockController = new ControleurActiviteStock(this);
    public static final String MESSAGE_NOTIFICATION = "Appuyez pour accéder à votre liste de courses";
    //TODO: create and add controller as attribute

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_stock);
        Bundle parametres = this.getIntent().getExtras();
//        idStock = (int) parametres.get("idStock");
//        Toast.makeText(this, "id maison "+idStock, Toast.LENGTH_SHORT).show();

        accesseurProduitStocke = ProduitStockeDAO.getInstance();
        btn_view_stock_add_product = findViewById(R.id.btn_view_stock_add_product);
        btn_view_stock_add_product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stockController.actionNaviguerVueAjouterProduit();
            }
        });
        recyclerView = findViewById(R.id.my_recycler_view);
        stockController.onCreate(getApplicationContext());
        adapter = new AdapteurListeProduit(listeProduits);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
//        navigationView.getMenu().findItem(R.id.activity_master_drawer_action_add_home).setChecked(true); //TODO: improve check verification system
        // TODO: call the controller onCreate method
    }


    public void setListeProduits(List<ProduitStocke> listeProduit){
        this.listeProduits = listeProduit;
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            accesseurProduitStocke.supprimerProduitDuStock(listeProduits.get(viewHolder.getAdapterPosition()));
            listeProduits.remove(viewHolder.getAdapterPosition());

            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            if(listeProduits.size() < 4){
                declencherNotification();
            }


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
        Intent intent = new Intent(this, ActiviteAjouterProduit.class);
        startActivityForResult(intent, ControleurActiviteStock.ADD_PRODUCT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        stockController.onActivityResult(requestCode);
    }

    public void declencherNotification(){

        createNotificationChannel();

        Intent intent = new Intent(this, ActiviteListeCourse.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "myChannel")
                .setSmallIcon(R.drawable.ic_caddie)
                .setContentTitle(TITRE_NOTIFICATION)
                .setContentText(MESSAGE_NOTIFICATION)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(15, builder.build());
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
