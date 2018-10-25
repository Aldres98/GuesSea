package aldres.example.com.guessea

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.Feature
import com.google.maps.android.data.geojson.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, IAnswerPicked {

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.overlay))


        var layer =  GeoJsonLayer(mMap, R.raw.seas, applicationContext)
        var style : GeoJsonPolygonStyle = layer.defaultPolygonStyle
        style.fillColor = 0xbf9999ab.toInt()
        layer.addLayerToMap()

        layer.setOnFeatureClickListener { feature: Feature? ->
            println(feature?.getProperty("name"))
            var polyStyle = GeoJsonPolygonStyle()
            var lineStyle = GeoJsonLineStringStyle()
            var polyFeature = feature as GeoJsonFeature
            polyStyle.fillColor = 0xbf333335.toInt()
            lineStyle.color = 0x000000000
            polyFeature.polygonStyle = polyStyle
            polyFeature.lineStringStyle = lineStyle
            Toast.makeText(this, feature.getProperty("name"), Toast.LENGTH_LONG).show()
        }
    }

    override fun onAnswerPicked(color: Int) {
    }


}
