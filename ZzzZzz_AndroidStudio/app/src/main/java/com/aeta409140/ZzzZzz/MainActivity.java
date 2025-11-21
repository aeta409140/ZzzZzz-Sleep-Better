package com.aeta409140.ZzzZzz;

import android.animation.*;
import android.app.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.format.DateFormat;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.activity.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.aeta409140.ZzzZzz.databinding.*;
import com.google.android.material.*;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.io.*;
import java.text.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.*;
import org.json.*;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.graphics.Insets;


public class MainActivity extends AppCompatActivity {
	
	private MainBinding binding;
	private String tdialogout = "";
	private String tdhour = "";
	private String tdminutes = "";
	private String sleppnw = "";
	private String final_sleep_now_value = "";
	private String wake_list = "";
	private String formatted = "";
	private String SelectedHours = "";
	private double SelectedMinutes = 0;
	private String SelectedMinute = "";
	private String ctksc = "";
	private String ctksw = "";
	
	private ArrayList<String> refreshed1 = new ArrayList<>();
	
	private TimePickerDialog tdialog;
	private TimePickerDialog.OnTimeSetListener tdialog_listener;
	private Calendar clndr = Calendar.getInstance();
	private Calendar wake_clndr = Calendar.getInstance();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
EdgeToEdge.enable(this);
		binding = MainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		setSupportActionBar(binding.Toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		binding.Toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _v) {
				onBackPressed();
			}
		});
		tdialog = new TimePickerDialog(this, tdialog_listener, Calendar.HOUR_OF_DAY, Calendar.MINUTE, false);
		
		binding.btnNow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				clndr = Calendar.getInstance();
				sleppnw = "";
				// Lets Calculate ~14 Minutes To An Human to sleep.
				clndr.add(Calendar.MINUTE, (int)(-14));
				// For 7 Times (7 Sleep Cycles) Calculat
				for(int _repeat15 = 0; _repeat15 < (int)(7); _repeat15++) {
					clndr.add(Calendar.MINUTE, (int)(90));
					final_sleep_now_value = new SimpleDateFormat("HH:mm").format(clndr.getTime());
					sleppnw = sleppnw.concat("\n".concat(final_sleep_now_value));
				}
				/*Now Showing The Result To User.
Showing 7 Sleep Cycles + What Time Takes To You Sleep

*/
				com.google.android.material.snackbar.Snackbar.make(binding.linear4, "Results Showed.", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("✓", new View.OnClickListener(){
					@Override
					public void onClick(View _view) {
						
					}
				}).show();
				binding.nowresult.setText(sleppnw);
			}
		});
		
		binding.button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				wake_clndr = Calendar.getInstance();
				ctksw = "";
				boolean isSystem24 = android.text.format.DateFormat.is24HourFormat(MainActivity.this);
				int Tformat = isSystem24 ? TimeFormat.CLOCK_24H : TimeFormat.CLOCK_12H;
				MaterialTimePicker ctk = new MaterialTimePicker.Builder()
				.setTimeFormat(Tformat)
				.setInputMode(MaterialTimePicker.INPUT_MODE_KEYBOARD)
				.setHour(12)
				.setMinute(00)
				.setTitleText("ZzzZzz Application timePicker")
				.build();
				ctk.addOnPositiveButtonClickListener(tok -> {
					if (isSystem24) {
						SelectedHours = "";
						SelectedMinute = "";
						wake_clndr.set(Calendar.MINUTE, ctk.getMinute());
						wake_clndr.set(Calendar.HOUR, ctk.getHour());
						wake_clndr.add(Calendar.MINUTE, (int)(-14));
						for(int _repeat26 = 0; _repeat26 < (int)(7); _repeat26++) {
							wake_clndr.add(Calendar.MINUTE, (int)(-90));
							ctksc = new SimpleDateFormat("HH:mm").format(wake_clndr.getTime());
							ctksw = ctksw.concat("\n".concat(ctksc));
						}
						binding.wakeresult.setText(ctksw);
						com.google.android.material.snackbar.Snackbar.make(binding.linear2, "Times Updated", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
								
							}
						}).show();
					} else {
						SelectedHours = "";
						SelectedMinute = "";
						wake_clndr.set(Calendar.MINUTE, ctk.getMinute());
						wake_clndr.set(Calendar.HOUR, ctk.getHour());
						wake_clndr.add(Calendar.MINUTE, (int)(-14));
						for(int _repeat43 = 0; _repeat43 < (int)(7); _repeat43++) {
							wake_clndr.add(Calendar.MINUTE, (int)(-90));
							ctksc = new SimpleDateFormat(String.format("hh:mm a", (ctk.getHour() == 0 || ctk.getHour() == 12) ? 12 : ctk.getHour() % 12, ctk.getMinute(), (ctk.getHour() < 12) ? "AM" : "PM")).format(wake_clndr.getTime());
							ctksw = ctksw.concat("\n".concat(ctksc));
						}
						binding.wakeresult.setText(ctksw);
						com.google.android.material.snackbar.Snackbar.make(binding.linear2, "Times Updated", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
							@Override
							public void onClick(View _view) {
								
							}
						}).show();
					}
				});
				ctk.addOnCancelListener(tcancel -> {
					com.google.android.material.snackbar.Snackbar.make(binding.linear2, "Canceled", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("Ok", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							
						}
					}).show();
				});
				ctk.addOnDismissListener(tnot -> {
					com.google.android.material.snackbar.Snackbar.make(binding.linear2, "Okay", com.google.android.material.snackbar.Snackbar.LENGTH_SHORT).setAction("✓", new View.OnClickListener(){
						@Override
						public void onClick(View _view) {
							
						}
					}).show();
				});
				ctk.show(getSupportFragmentManager(), "tg");
			}
		});
		
		tdialog_listener = new TimePickerDialog.OnTimeSetListener() {
			@Override
			public void onTimeSet(TimePicker _timePicker, int _hour, int _minute) {
				// شروع Add Source
				tdhour = String.valueOf((long)(_hour));
				tdminutes = String.valueOf((long)(_minute));
				
				wake_clndr = Calendar.getInstance();
				wake_clndr.set(Calendar.HOUR_OF_DAY, _hour); // حالا _hour 0-23 هست
				wake_clndr.set(Calendar.MINUTE, _minute);
				
				wake_list = "";
				for(int _repeat25 = 0; _repeat25 < 7; _repeat25++) {
					wake_clndr.add(Calendar.MINUTE, -90);
					formatted = new SimpleDateFormat("HH:mm").format(wake_clndr.getTime());
					wake_list = wake_list.concat("\n".concat(formatted));
				}
				
				binding.wakeresult.setText(wake_list);
				// پایان Add Source
			}
		};
	}
	
	private void initializeLogic() {
		// Block By Block Placed With Mistake, Errors , And More... 
		
		// By Shiba(aeta409140)
		// By You
		// By We All
		// Thanks For Your View
		ViewCompat.setOnApplyWindowInsetsListener(binding.AppBar, (v, insets) -> {
			Insets statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars());
			v.setPadding(0, statusBars.top, 0, 0);
			return insets;
		});
	}
	
}