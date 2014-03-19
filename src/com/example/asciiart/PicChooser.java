package com.example.asciiart;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/*
 * Picture Chooser allows user to choose a saved picture to load
 */

public class PicChooser extends ListActivity {

	//database and helper
	private ImageDataHelper picDataHelp;
	private SQLiteDatabase savedPictures;
	//cursor
	private Cursor picCursor;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load);

		//get database and helper
		picDataHelp=ImageDataHelper.getInstance(this);
		savedPictures=picDataHelp.getReadableDatabase();
		//query to retrieve cursor
		picCursor = savedPictures.query("pics", null, null, null, null, null, null);
		//define database table columns to display
		String[] columns = {ImageDataHelper.ID_COL, ImageDataHelper.CREATED_COL};
		//define views to map these to
		int[] views = {R.id.picID, R.id.picName};
		//set adapter to map data items to views in layout
		SimpleCursorAdapter picAdapter = new SimpleCursorAdapter(this, R.layout.pic_item, picCursor, columns, 
				views, SimpleCursorAdapter.FLAG_AUTO_REQUERY);
		//set adapter
		setListAdapter(picAdapter);
	}

	/*
	 * Method called from items in XML layout when a picture is chosen
	 */
	public void picChosen(View view){
		//get the ID text view from the view clicked
		TextView pickedView = (TextView)view.findViewById(R.id.picID);
		//get the ID text as database reference
		String chosenID = (String)pickedView.getText();

		//close
		picDataHelp.close();
		savedPictures.close();
		picCursor.close();

		//go back to called activity, pass back data from clicked view
		Intent backIntent = new Intent();
		backIntent.putExtra("pickedImg", chosenID);
		//data will be retrieved in onActivityResult
		setResult(RESULT_OK, backIntent);
		//done here
		finish();
	}

	/*
	 * Override
	 * - in case user hits back button
	 */
	@Override
	public void onDestroy() {
		picCursor.close();
		picDataHelp.close();
		savedPictures.close();
		super.onDestroy();
	}

}