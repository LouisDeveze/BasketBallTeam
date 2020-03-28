package fr.android.basketballteam.mapping;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.List;
import java.util.Locale;

public class AsyncLocationFinder extends AsyncTask<Double[], Integer, String> {

    private TextView text;
    private Context context;

    private double latitude;
    private double longitude;


    public AsyncLocationFinder(TextView text, Context context){
        this.text = text;
        this.context = context;
    }

    @Override
    protected String doInBackground(Double[]... doubles) {
        String ret = "";
        this.latitude = doubles[0][0];
        this.longitude = doubles[0][1];

        try{
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(context, Locale.getDefault());
            addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5

            String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
            String city = addresses.get(0).getLocality();
            String state = addresses.get(0).getAdminArea();
            String country = addresses.get(0).getCountryName();
            ret = address +", " + city + ", " + country;
        }catch (Exception e){
            e.printStackTrace();
            ret = "Unknown Address";
        }

        return ret;
    }

    @Override
    protected void onPostExecute(String str){
        text.setText(str);
    }
}
