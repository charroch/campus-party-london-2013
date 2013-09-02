package com.cp.london.gplus;

import android.accounts.AccountManager;
import android.annotation.TargetApi;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.common.AccountPicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Landing extends FragmentActivity {

    private static final int CHOOSE_ACCOUNT_RESULT = 12;
    private String accountName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    public void onLogin(View view) {
        startActivityForResult(

                AccountPicker.newChooseAccountIntent(null, null,
                        new String[]{"com.google"},
                        false, null, null, null, null),

                CHOOSE_ACCOUNT_RESULT
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_ACCOUNT_RESULT:
                accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                toast(accountName);
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void toast(String what) {
        Toast.makeText(this.getApplicationContext(), what, Toast.LENGTH_LONG);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ListFragment getListFragment() {
        return (ListFragment) getSupportFragmentManager().findFragmentById(R.id.plus_activity_fragment);
    }
}
