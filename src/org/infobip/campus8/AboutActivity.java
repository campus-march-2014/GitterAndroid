package org.infobip.campus8;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;

public class AboutActivity extends Activity {
	
@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.about);
}
	
@Override
public boolean onCreateOptionsMenu(Menu menu) {
menu.add(0, 1, 0, "Settings");
menu.add(0, 2, 0, "About");
 return true;
}

@Override
public boolean onOptionsItemSelected(MenuItem item) {

 switch (item.getItemId()) {
 case 1: {
  Intent intentSubsOne = new Intent(AboutActivity.this,PostRegistrationChannelSubscription.class);
  startActivity(intentSubsOne);
  break;
 	}
 case 2: {
	   Intent intentSubs = new Intent(AboutActivity.this,AboutActivity.class);
	   startActivity(intentSubs);
	   break;
	  	}
 }
 return true;
}
}
