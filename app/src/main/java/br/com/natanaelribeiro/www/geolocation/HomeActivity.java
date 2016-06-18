package br.com.natanaelribeiro.www.geolocation;

import android.annotation.TargetApi;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private GoogleMap mMap;
    private Marker markerMyLocation;
    private LatLng[] posicoes = new LatLng[10];
    private List<LatLng> listClusters;
    private ClusterManager mClusterManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        listClusters = new ArrayList<>();

        posicoes[0] = new LatLng(-29.983067, -51.192679);
        posicoes[1] = new LatLng(-29.976004, -51.170492);
        posicoes[2] = new LatLng(-29.963215, -51.191134);
        posicoes[3] = new LatLng(-29.961245, -51.161308);
        posicoes[4] = new LatLng(-29.992753, -51.138726);
        posicoes[5] = new LatLng(-30.001301, -51.151644);
        posicoes[6] = new LatLng(-30.016612, -51.166106);
        posicoes[7] = new LatLng(-30.025678, -51.177865);
        posicoes[8] = new LatLng(-30.040205, -51.183959);
        posicoes[9] = new LatLng(-30.063090, -51.156959);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);

        //MarkerOptions mOpt = new MarkerOptions();
        //mOpt.position(sydney);
        //mOpt.title("Marker Sydney");
        //mOpt.snippet("Lugar dos bicho estranho");
        //mMap.addMarker(mOpt);
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
            mGoogleApiClient.connect();
        }

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateCamera(location);
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onConnected(Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);//request code
            return;
        }
        //################# Desenhos ###################//
        //PolylineOptions polOpt = new PolylineOptions();
        //polOpt.add(new LatLng(-30.032564, -51.227706));
        //polOpt.add(new LatLng(-30.035556, -51.227968));
        //polOpt.color(Color.BLUE);
        //polOpt.width(3);
        //mMap.addPolyline(polOpt);

        //CircleOptions circle = new CircleOptions();
        //circle.center(new LatLng(-29.974045, -51.194889));
        //circle.fillColor(Color.BLUE);
        //circle.strokeColor(Color.BLACK);
        //circle.radius(2000);
        //mMap.addCircle(circle);

        //for (LatLng posicao : posicoes){
        //    MarkerOptions mOpt = new MarkerOptions();
        //    mOpt.position(posicao);
        //    mMap.addMarker(mOpt);
        //}

        //################# Cluster ###################//
        //mClusterManager = new ClusterManager<MyItem>(this, mMap);
        //for (LatLng posicao : posicoes){
        //    MyItem offsetItem = new MyItem(posicao.latitude, posicao.longitude);
        //    mClusterManager.addItem(offsetItem);
        //}

        //mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener() {
        //    @Override
        //    public boolean onClusterItemClick(ClusterItem clusterItem) {
        //        listClusters.add(new LatLng(clusterItem.getPosition().latitude, clusterItem.getPosition().longitude));
        //        atualizaLinha();
        //        return false;
        //    }
        //});

        //mMap.setOnCameraChangeListener(mClusterManager);
        //mMap.setOnMarkerClickListener(mClusterManager);



        //LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);

        mClusterManager = new ClusterManager<MyItem>(HomeActivity.this, mMap);

        mClusterManager.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener() {
            @Override
            public boolean onClusterItemClick(ClusterItem clusterItem) {
                listClusters.add(new LatLng(clusterItem.getPosition().latitude, clusterItem.getPosition().longitude));
                atualizaLinha();
                return false;
            }
        });

        Call<Posicoes> call = ((CursoApplication) getApplication()).service.searchPositions();
        call.enqueue(new Callback<Posicoes>() {
            @Override
            public void onResponse(Call<Posicoes> call, Response<Posicoes> response) {

                for (Posicao posicao : response.body().posicoes) {
                    MyItem offsetItem = new MyItem(posicao.latitude, posicao.longitude);
                    mClusterManager.addItem(offsetItem);
                }

                mMap.setOnCameraChangeListener(mClusterManager);
                mMap.setOnMarkerClickListener(mClusterManager);
            }

            @Override
            public void onFailure(Call<Posicoes> call, Throwable t) {
                Log.e("CURSO", "Pepino: " + t.getLocalizedMessage());
            }
        });




        showMe();
    }

    public void atualizaLinha() {
        PolylineOptions polOpt = new PolylineOptions();
        for (LatLng ll : listClusters) {
            polOpt.add(ll);
        }
        polOpt.color(Color.BLUE);
        polOpt.width(3);
        mMap.addPolyline(polOpt);
    }

    public void showMe() { //fakegps

        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        updateCamera(mLastLocation);
    }

    public void updateCamera(Location loc) {
        LatLng eu = new LatLng(loc.getLatitude(), loc.getLongitude());
        if (markerMyLocation == null) {
            markerMyLocation = mMap.addMarker(new MarkerOptions().position(eu).title("Estou aqui"));
        } else {
            markerMyLocation.setPosition(eu);
        }
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(eu, 16));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showMe();
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
}
