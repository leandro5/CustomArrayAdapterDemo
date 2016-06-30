package com.leandroasouza.customarrayadapterdemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Property> rentalProperties = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create property elements
        rentalProperties.add(
                new Property(10, "Smith Street", "Sydney", "NSW", "A large 3 bedroom apartment right in the heart of Sydney! A rare find, with 3 bedrooms and a secured car park.", "property_image_1", 450.00, 3, 1, 1));

        rentalProperties.add(
                new Property(66, "King Street", "Sydney", "NSW", "A fully furnished studio apartment overlooking the harbour. Minutes from the CBD and next to transport, this is a perfect set-up for city living.", "property_image_2", 320.00, 1, 1, 1));

        rentalProperties.add(
                new Property(1, "Liverpool Road", "Liverpool", "NSW", "A standard 3 bedroom house in the suburbs. With room for several cars and right next to shops this is perfect for new families.", "property_image_3", 360.00, 3, 2, 2));

        rentalProperties.add(
                new Property(567, "Sunny Street", "Gold Coast", "QLD", "Come and see this amazing studio appartment in the heart of the gold coast, featuring stunning waterfront views.", "property_image_4" , 360.00, 1, 1, 1));

        ArrayAdapter<Property> adapter = new propertyArrayAdapter(this, 0, rentalProperties);

        ListView listView = (ListView) findViewById(R.id.customListView);
        listView.setAdapter(adapter);
    }

    class propertyArrayAdapter extends ArrayAdapter<Property> {

        private Context context;
        private List<Property> rentalProperties;

        public propertyArrayAdapter(Context context, int resource, ArrayList<Property> objects) {
            super(context, resource, objects);

            this.context = context;
            this.rentalProperties = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Property property = rentalProperties.get(position);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.property_layout, null);

            TextView description = (TextView) view.findViewById(R.id.description);
            TextView address = (TextView) view.findViewById(R.id.address);
            TextView bedroom = (TextView) view.findViewById(R.id.bedroom);
            TextView bathroom = (TextView) view.findViewById(R.id.bathroom);
            TextView carspot = (TextView) view.findViewById(R.id.carspot);
            TextView price = (TextView) view.findViewById(R.id.price);
            ImageView image = (ImageView) view.findViewById(R.id.image);

            String completeAddress = property.getStreetNumber() + " " + property.getStreetName() + ", " + property.getSuburb() + " " + property.getState();
            address.setText(completeAddress);

            int descriptionLength = property.getDescription().length();

            if (descriptionLength >= 100){
                String descriptionTrim = property.getDescription().substring(0, 100) + "...";
                description.setText(descriptionTrim);
            }else{
                description.setText(property.getDescription());
            }

            price.setText("$" + String.valueOf(property.getPrice()) + " ");
            bedroom.setText("Bed: " + String.valueOf(property.getBedrooms()) + " ");
            bathroom.setText("Bath: " + String.valueOf(property.getBathrooms()) + " ");
            carspot.setText("Car: " + String.valueOf(property.getCarspots()) + " ");

            int imageID = context.getResources().getIdentifier(property.getImage(), "drawable", context.getPackageName());
            image.setImageResource(imageID);

            return view;
        }
    }
}
