package com.nyi.test.mediarouter;

import android.app.Presentation;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nyi.test.R;

public class SamplePresentation extends Presentation {

    private LinearLayout mLayout;
    private TextView mText;

    public SamplePresentation(Context outerContext, Display display) {
        super(outerContext, display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content view to the custom layout
        setContentView(R.layout.sample_presentation);

        // Get the Views
        mLayout = findViewById(R.id.display_layout);
        mText = findViewById(R.id.display_text);

        /*
         * Show the name of the display this presentation was embedded in.
         */
        TextView smallText = findViewById(R.id.display_smalltext);
        final String name = getDisplay().getName();
        smallText.setText(getResources().getString(R.string.display_name, name));
    }

    /**
     * Set the background color of the layout and display the color as a String.
     *
     * @param color The background color
     */
    public void setColor(int color) {
        mLayout.setBackgroundColor(color);

        // Display the color as a string on screen
        String s = getResources().getString(R.string.display_color, color);
        mText.setText(s);
    }

}
