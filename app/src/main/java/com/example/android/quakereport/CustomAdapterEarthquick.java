package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.graphics.drawable.GradientDrawable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CustomAdapterEarthquick extends ArrayAdapter<Earthquake> {
    private static final String LOCATION_SEPARATOR = " of ";


    public CustomAdapterEarthquick(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Earthquake earthquake = (Earthquake) getItem(position);

        View listItemView = convertView;
        if(listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_earthquake, parent, false);
        }

        Date dateObject = new Date(earthquake.getDate());

        String originalLocation = earthquake.getPlace();
        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }


        TextView magnitude = (TextView)listItemView.findViewById(R.id.magnitudeID);
        magnitude.setText(String.valueOf(earthquake.getMagnitude()));

        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);




        TextView distance = (TextView)listItemView.findViewById(R.id.distanceID);
        distance.setText(locationOffset);

        TextView location = (TextView)listItemView.findViewById(R.id.locationID);
        location.setText(primaryLocation);

        TextView date = (TextView)listItemView.findViewById(R.id.dateID);
        String formattedDate = formatDate(dateObject);
        date.setText(formattedDate);

        TextView time = (TextView)listItemView.findViewById(R.id.timeID);
        String formattedTime =formatTime(dateObject);
        time.setText(formattedTime);

        return listItemView;
    }

    public String formatDate(Date objectDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("DD.MMM.yyyy");
        return dateFormat.format(objectDate);
    }

    public String formatTime(Date objectDate){
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm a");
        return dateFormat.format(objectDate);
    }

    public int getMagnitudeColor(double magnitude){
        int magnitudeColor;
        int magnitudeFloor =(int) Math.floor(magnitude);

        switch (magnitudeFloor){
            case 0: case 1: magnitudeColor=R.color.magnitude1; break;
            case 2:magnitudeColor=R.color.magnitude2;break;
            case 3:magnitudeColor=R.color.magnitude3;break;
            case 4:magnitudeColor=R.color.magnitude4;break;
            case 5:magnitudeColor=R.color.magnitude5;break;
            case 6:magnitudeColor=R.color.magnitude6;break;
            case 7:magnitudeColor=R.color.magnitude7;break;
            case 8:magnitudeColor=R.color.magnitude8;break;
            case 9:magnitudeColor=R.color.magnitude9;break;
            case 10:magnitudeColor=R.color.magnitude10plus;break;
            default:magnitudeColor=R.color.magnitude10plus;break;
        }

        return magnitudeColor;
    }
}
