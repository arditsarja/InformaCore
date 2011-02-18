package org.witness.sscphase1;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CameraObscuraMainMenu extends Activity implements OnClickListener {
	    
		final static String LOGTAG = "CAMERA OBSCRUA";
			
		final static int CAMERA_RESULT = 0;
		final static int GALLERY_RESULT = 1;
		final static int IMAGE_EDITOR = 2;
					
		public final static int PREFERENCES_MENU_ITEM = 0;
		public final static int PANIC_MENU_ITEM = 1;

		Button choosePictureButton, takePictureButton;
		
		File tmpImageFile;
		
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.mainmenu);
	        
	    	choosePictureButton = (Button) this.findViewById(R.id.ChoosePictureButton);
	    	choosePictureButton.setOnClickListener(this);
	    	
	    	takePictureButton = (Button) this.findViewById(R.id.TakePictureButton);
	    	takePictureButton.setOnClickListener(this);
	    }

		public void onClick(View v) {
			if (v == choosePictureButton) {
				
				// Only from the Memory Card.
				// INTERNAL_CONTENT_URI from the normal memory space
				Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivityForResult(i, GALLERY_RESULT);
				
			} else if (v == takePictureButton) {
				
				// This should be obscured/hidden/encrypted etc.
		    	tmpImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myfavoritepicture.jpg");

				// Add in choice to capture video
		    	
				Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, Uri.fromFile(tmpImageFile));
				startActivityForResult(i, CAMERA_RESULT);

				takePictureButton.setVisibility(View.VISIBLE);
				choosePictureButton.setVisibility(View.VISIBLE);
			}
		}

		protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
			super.onActivityResult(requestCode, resultCode, intent);

			if (resultCode == RESULT_OK) {
				if (requestCode == GALLERY_RESULT || requestCode == CAMERA_RESULT) {
					
					Uri imageFileUri;
					if (requestCode == CAMERA_RESULT) {
						imageFileUri = Uri.fromFile(tmpImageFile);
					} else { //if (requestCode == GALLERY_RESULT) {
						imageFileUri = intent.getData();
					}
					Log.v(LOGTAG,imageFileUri.toString());					
					
					Intent passingIntent = new Intent(this,ImageEditor.class);
					passingIntent.setData(imageFileUri);
					startActivityForResult(passingIntent, IMAGE_EDITOR);					
				}
			}
		}	

	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	    	MenuItem panicMenuItem = menu.add(Menu.NONE, PANIC_MENU_ITEM, Menu.NONE, "Panic");
	        MenuItem preferencesMenuItem = menu.add(Menu.NONE, PREFERENCES_MENU_ITEM, Menu.NONE, "Preferences");
	        return true;
	    }
	    
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
	        	case PREFERENCES_MENU_ITEM:
	        	 	// Load Preferences Activity
	        		Intent intent = new Intent(this, PreferencesActivity.class);
	        		startActivity(intent);	
	        		return true;
	        	case PANIC_MENU_ITEM:
	        		// Look up preferences and do what is required
	    		default:
	    			return false;
	    	}
	    }
}