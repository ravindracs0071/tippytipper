package net.mandaria.tippytipperlibrary.activities;

import net.mandaria.tippytipperlibrary.R;
import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.flurry.android.FlurryAgent;

public class Settings extends PreferenceActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);
	}
	
	@Override
	public void onStart()
    {
       super.onStart();
       boolean enableErrorLogging = Settings.getEnableErrorLogging(getBaseContext());
       String API = getString(R.string.flurrykey);
       if(!API.equals("") && enableErrorLogging == true)
       {
    	   FlurryAgent.setContinueSessionMillis(30000);
    	   FlurryAgent.onStartSession(this, API);
       }
    }
    
    @Override
	public void onStop()
    {
       super.onStop();
       FlurryAgent.onEndSession(this);
    }
	
	public static int getDefaultTipPercentage(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt("default_tip_percentage", 15);
	}
	
	public static int getTipPercentagePresetOne(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt("tip_percentage_one", 10);
	}
	
	public static int getTipPercentagePresetTwo(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt("tip_percentage_two", 15);
	}
	
	public static int getTipPercentagePresetThree(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt("tip_percentage_three", 20);
	}
	
	public static int getDefaultNumberOfPeopleToSplitBill(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getInt("default_number_of_people", 2);
	}
	
	public static boolean getEnableErrorLogging(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("enable_error_logging", true);
	}
	
	public static boolean getEnableExcludeTaxRate(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("enable_exclude_tax_rate", false);
	}
	
	public static boolean isSetToRoundByTip(Context context)
	{
		String RoundType = PreferenceManager.getDefaultSharedPreferences(context).getString("round_type", "round_total");
		if(RoundType.equals("round_tip"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static float getExcludeTaxRate(Context context)
	{
		return PreferenceManager.getDefaultSharedPreferences(context).getFloat("exclude_tax", 0);
	}
}
