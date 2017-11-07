package com.example.stgo.bildform.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.BuildConfig;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.stgo.bildform.Fragment.FormFragment;
import com.example.stgo.bildform.Fragment.LoginFragment;
import com.example.stgo.bildform.Fragment.SettingsFragment;
import com.example.stgo.bildform.Presenter.MainActivityPresenter;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.MainActivityView;

import java.util.ArrayList;

import cl.transtel.permisionutil.PermissionCallback;
import cl.transtel.permisionutil.PermissionUtil;

import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity implements MainActivityView ,
        ActivityCompat.OnRequestPermissionsResultCallback, PermissionCallback {


    private ArrayList<String> permissions;
    private PermissionUtil permissionUtil;
    private boolean permFlag = false;
    MainActivityPresenter mainActivityPresenter;
    private static int REQUEST_CODE = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mainActivityPresenter = new MainActivityPresenter(this.getApplicationContext());
        mainActivityPresenter.attachView(this);
        checkPermission();
        setFragmentByName(mainActivityPresenter.getInitFragment());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            SettingsFragment settingsFragment = SettingsFragment.newInstance();
            setFragment(settingsFragment);
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragmentByName(String fragmentName){
        switch (fragmentName){
            case "Login":
                LoginFragment loginFragment = LoginFragment.newInstance();
                setFragment(loginFragment);
                break;
            case "Setting":
                SettingsFragment settingsFragment = SettingsFragment.newInstance();
                setFragment(settingsFragment);
                break;
            case "Form":
                FormFragment formFragment = FormFragment.newInstance();
                setFragment(formFragment);
                break;
        }
    }
    @Override
    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainFrameLayout, fragment)
                .addToBackStack(null).commit();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults){
        permissionUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void PermissionGranted(int i) {
        Log.d("PERMISSION:", "Permission Granted");
        mainActivityPresenter.makeDefaultParameter();
    }

    @Override
    public void PermissionPartial(int i, ArrayList<String> arrayList) {
        Log.d("PERMISSION:", "Permission Partial");
        permissionMessage();
    }

    @Override
    public void PermissionDenied(int i) {
        Log.d("PERMISSION:", "Permission Denied");
        permissionMessage();
    }

    @Override
    public void NeverAsk(int i) {
        Log.d("PERMISSION:", "Never Ask Again");
    }

    private void checkPermission(){
        permissions = new ArrayList<>();
        permissions.add(INTERNET);
        permissions.add(WRITE_EXTERNAL_STORAGE);
        permissions.add(READ_EXTERNAL_STORAGE);

        Activity act = (Activity) MainActivity.this;
        permissionUtil = new PermissionUtil(act, MainActivity.this, this);
        permissionUtil.checkPermission(permissions,getString(R.string.permission_msg), REQUEST_CODE);
    }

    public void permissionMessage(){

        new AlertDialog.Builder(this)
                .setMessage(getString(R.string.permission_msg))
                .setPositiveButton(getString(R.string.permission_config), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                                Uri.parse("package:" + getPackageName()));
                        startActivity(intent);
                        dialog.dismiss();
                        permFlag = true;
                    }
                })
                .setNegativeButton(getString(R.string.permission_cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        return;
                    }
                })
                .setCancelable(false)
                .create()
                .show();
    }
}
