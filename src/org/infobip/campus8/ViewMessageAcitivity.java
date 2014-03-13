package org.infobip.campus8;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewMessageAcitivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_message);
		Intent viewMessage = getIntent();
		TextView textViewTitle = (TextView) findViewById(R.id.textViewTitle);
		textViewTitle.setText(viewMessage.getStringExtra("title"));
		
		TextView textViewMessage = (TextView) findViewById(R.id.textViewMessage);
		textViewMessage.setText(viewMessage.getStringExtra("message"));
		textViewMessage.setMovementMethod(new ScrollingMovementMethod());
		
		final Button backToMain = (Button) findViewById(R.id.buttonToMain);
		backToMain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent main = new Intent(ViewMessageAcitivity.this, MainActivity.class);
				startActivity(main);
			}
		});
	}
}
