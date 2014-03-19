package com.example.asciiart;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

/*
 * ColorChooser allows user to pick colors for text and background
 */

public class ColorChooser extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_choice);
	}

	/*
	 * setColors called from layout XML for each color choice button
	 */
	public void setColors(View view){
		//get color details from XML tag
		String tagInfo = (String)view.getTag();
		//split: "#ffffff #000000"
		String[] tagColors = tagInfo.split(" ");
		//go back to previous activity, pass back this info
		Intent backIntent = new Intent();
		backIntent.putExtra("textColor", tagColors[0]);
		backIntent.putExtra("backColor", tagColors[1]);
		//data will be retrieved in onActivityResult
		setResult(RESULT_OK, backIntent);
		//done here
		finish();
	}
}