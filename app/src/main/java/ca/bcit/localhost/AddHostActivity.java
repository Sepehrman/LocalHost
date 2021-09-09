package ca.bcit.localhost;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

public class AddHostActivity extends AppCompatActivity {


    private EditText inputName, inputAddress, inputPhone, autocompleteInput;
    private final int AUTOCOMPLETE_REQUEST_CODE = 100;
    ImageView googleImage;
    PlacesClient placesClient;
    DatabaseReference hostsDB;
    private Button addHostBtn;
    private Host host;
    private double lattitude, longitude;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_host);
        getSupportActionBar().hide();

        hostsDB = FirebaseDatabase.getInstance().getReference().child("Hosts");

        addHostBtn = findViewById(R.id.addHost);
        autocompleteInput = findViewById(R.id.automateAddress);
        inputName = findViewById(R.id.hostName);
        inputAddress = findViewById(R.id.inputAddress);
        inputPhone = findViewById(R.id.hostPhone);


        String[] hostTypesArray = new String[] {
                HostType.CAFE.toString(), HostType.RESTAURANT.toString()
        };
        Spinner s = (Spinner) findViewById(R.id.hostTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, hostTypesArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        Places.initialize(getApplicationContext(), Keys.GOOGLE_API);
        placesClient = Places.createClient(this);

        addHostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    host = new Host(inputAddress.getText().toString(), inputName.getText().toString(), inputPhone.getText().toString(), HostType.RESTAURANT, lattitude, longitude);
                    String selection = s.getSelectedItem().toString();


                    hostsDB.child(selection).setValue(host.getName());

//                    Task setTaskHost = hostsDB.child(currentHost).child(HostType.RESTAURANT.toString()).setValue(host);
////                    setTaskHost.addOnSuccessListener(o -> {
//                        Toast.makeText(AddHostActivity.this,"Item and pricing added.",Toast.LENGTH_LONG).show();
//                    });


            }
        });

        autocompleteInput.setFocusable(false);
        autocompleteInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Place.Field> fieldList = Arrays.asList(Place.Field.NAME,
                        Place.Field.LAT_LNG, Place.Field.ADDRESS, Place.Field.PHONE_NUMBER, Place.Field.OPENING_HOURS);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.OVERLAY,
                        fieldList)
                        // Set filtering for countries US & Canada
                        .setCountries(Arrays.asList("CA", "US"))
                        .build(AddHostActivity.this);

                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AUTOCOMPLETE_REQUEST_CODE && resultCode == RESULT_OK) {
            Place place = Autocomplete.getPlaceFromIntent(data);
            inputName.setText(place.getName());
            inputAddress.setText(place.getAddress());
            inputPhone.setText(place.getPhoneNumber());

            lattitude = place.getLatLng().latitude;
            longitude = place.getLatLng().longitude;



        } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
            Status status = Autocomplete.getStatusFromIntent(data);
            Toast.makeText(getApplicationContext(), status.getStatusMessage(), Toast.LENGTH_LONG).show();
        }
    }



}