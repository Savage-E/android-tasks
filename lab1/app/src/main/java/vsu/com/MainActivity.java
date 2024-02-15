package vsu.com;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public final class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        Rectangle rectangle = new Rectangle("Orange", 3.0, 5.0);
        Circle circle = new Circle("White", 2.0);
        Triangle triangle = new Triangle("Yellow", 4.0, 4.0, 6.0);
        List<Shape> shapes = Arrays.asList(circle, rectangle, triangle);
        double totalAreas = Utils.calculateSumArea(shapes);
        View totalAreaTextView = this.findViewById(R.id.total_area_textView);
        TextView resultTextView = (TextView) totalAreaTextView;
        resultTextView.setText("Total area of figures: " + totalAreas);
    }
}

