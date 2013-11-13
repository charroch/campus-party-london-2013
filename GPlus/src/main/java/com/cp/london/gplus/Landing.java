package com.cp.london.gplus;

import android.accounts.AccountManager;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.common.Scopes;

import java.io.IOException;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Landing extends FragmentActivity implements GetToken {

    public static final int CHOOSE_ACCOUNT_RESULT = 12;
    private static final int ENABLE_ACCOUNT_RESULT = 13;
    private String accountName;
    private String token;
    private Button login;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
        login = (Button) findViewById(R.id.login);
        logout = (Button) findViewById(R.id.logout);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.landing, menu);
        return true;
    }

    public void onLogout(View view) {
        if (!TextUtils.isEmpty(token)) {
            GoogleAuthUtil.invalidateToken(this, token);
            login.setEnabled(true);
            logout.setEnabled(false);
        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_CANCELED) {
            toast("If you cancel, you won't go very far!");
            return;
        }
        switch (requestCode) {
            case CHOOSE_ACCOUNT_RESULT:
                accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
                toast(accountName);
                getTokenFor(accountName);
                break;
            case ENABLE_ACCOUNT_RESULT:
                onTokenReceived(data.getStringExtra(AccountManager.KEY_AUTHTOKEN));
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onTokenReceived(String token) {
        if (TextUtils.isEmpty(token)) {
            toast("Something went wrong... token is null");
            return;
        }

        this.token = token;
        logout.setEnabled(true);
        login.setEnabled(false);
        getFeedWithToken(token);
    }

    private void getFeedWithToken(String token) {
        MyPlusActivitiesFragment frg = (MyPlusActivitiesFragment) getSupportFragmentManager().findFragmentById(R.id.plus_activity_fragment);
        if (frg != null) {
            frg.loadFeed(token);
        }
    }

    private void getTokenFor(String accountName) {
        new GetToken(accountName).execute();
    }

    private void toast(String what) {
        Toast.makeText(this.getApplicationContext(), what, Toast.LENGTH_LONG).show();
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

    @Override
    public String getToken() {
        return token;
    }

    @Override
    public boolean hasToken() {
        return !TextUtils.isEmpty(token);
    }

    /**
     * Get token asynchronously...
     */
    private class GetToken extends AsyncTask<Void, Void, String> {

        private static final String SCOPE = "oauth2:" + Scopes.PLUS_LOGIN + " https://www.googleapis.com/auth/userinfo.email";
        private final String accountName;

        private GetToken(String accountName) {
            this.accountName = accountName;
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                return GoogleAuthUtil.getToken(Landing.this, accountName, SCOPE);
            } catch (UserRecoverableAuthException userAuthEx) {
                startActivityForResult(userAuthEx.getIntent(), ENABLE_ACCOUNT_RESULT);
            } catch (IOException ioEx) {
            } catch (GoogleAuthException fatalAuthEx) {
            }
            return "";
        }

        @Override
        protected void onPostExecute(String token) {
            super.onPostExecute(token);
            onTokenReceived(token);
        }
    }
}
