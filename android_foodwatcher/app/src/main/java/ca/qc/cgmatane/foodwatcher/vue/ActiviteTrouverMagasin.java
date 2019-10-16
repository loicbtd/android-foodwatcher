package ca.qc.cgmatane.foodwatcher.vue;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.foodwatcher.R;

public class ActiviteTrouverMagasin extends ConteneurPrincipal implements OnMapReadyCallback {

    public Location currentLocation;
    public FusedLocationProviderClient fusedLocationProviderClient;

    private static final int REQUEST_CODE = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.configureActivityContent(R.layout.activite_carte_magasin);

        navigationView.getMenu().findItem(R.id.conteneur_principal_drawer_action_naviguer_carte_magasin).setChecked(true);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        fetchLocation();

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        LatLng latLng = new LatLng(currentLocation.getLatitude(),currentLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title("Vous")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_position));

        googleMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15.0f));
        googleMap.addMarker(markerOptions);

        mockDataMarker(googleMap);

    }

    private void mockDataMarker(GoogleMap googleMap) {

        List<String> listeNomsLieux = new ArrayList<>();
        List<LatLng> listeCoordonnees = new ArrayList<>();

        listeCoordonnees.add(new LatLng(48.840892, -67.519760));
        listeNomsLieux.add("Boni-Soir - Mini marché du Lac inc.");

        listeCoordonnees.add(new LatLng(48.851537, -67.511659));
        listeNomsLieux.add("IGA Marché DesRosiers inc.");

        listeCoordonnees.add(new LatLng(48.851712, -67.508867));
        listeNomsLieux.add("Maxi");

        listeCoordonnees.add(new LatLng(48.845465, -67.529805));
        listeNomsLieux.add("shoppers drug mart");

        listeCoordonnees.add(new LatLng(48.846317, -67.532576));
        listeNomsLieux.add("Dépanneur Labonté");

        listeCoordonnees.add(new LatLng(48.840892, -67.519760));
        listeNomsLieux.add("Boni-Soir - Mini marché du Lac inc.");

        listeCoordonnees.add(new LatLng(48.847176, -67.529925));
        listeNomsLieux.add("Boulangerie Toujours Dimanche Inc");

        listeCoordonnees.add(new LatLng(48.852327, -67.537126));
        listeNomsLieux.add("Metro Plus Matane");

        listeCoordonnees.add(new LatLng(48.852249, -67.537416));
        listeNomsLieux.add("Super C");

        listeCoordonnees.add(new LatLng(48.850117, -67.547238));
        listeNomsLieux.add("Poissonnerie du Phare Ouest");

        listeCoordonnees.add(new LatLng(48.843929, -67.556156));
        listeNomsLieux.add("Walmart Matane Store");

        listeCoordonnees.add(new LatLng(48.839684, -67.540591));
        listeNomsLieux.add("ÉPICERIE-DÉPANNEUR R. ST-GELAIS INC.");

        MarkerOptions markerOptions;
        for (int iterateur = 0; iterateur < listeCoordonnees.size(); iterateur++){

            markerOptions = new MarkerOptions()
                    .position(listeCoordonnees.get(iterateur))
                    .title(listeNomsLieux.get(iterateur));
            googleMap.addMarker(markerOptions);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE:

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    fetchLocation();

                }

                break;
        }
    }

    private void fetchLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION
            },REQUEST_CODE);

            return;

        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){

                    currentLocation = location;
//                    Toast.makeText(getApplicationContext(),currentLocation.getLatitude()+", "+currentLocation.getLongitude(),Toast.LENGTH_SHORT).show();
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_map);

                    supportMapFragment.getMapAsync(ActiviteTrouverMagasin.this);

                }
            }
        });

    }


}
