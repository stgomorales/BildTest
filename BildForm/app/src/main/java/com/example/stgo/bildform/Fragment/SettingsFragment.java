package com.example.stgo.bildform.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.stgo.bildform.APIS.Utils;
import com.example.stgo.bildform.Activity.MainActivity;
import com.example.stgo.bildform.Presenter.SettingFragmentPresenter;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.DefaultView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment implements DefaultView {

    View view;
    @BindView(R.id.settingIp) EditText settingIp;
    @BindView(R.id.settingPort) EditText settingPort;
    @BindView(R.id.buttonSetting) Button settingButton;

    private SettingFragmentPresenter settingFragmentPresenter;

    public static SettingsFragment newInstance(){
        SettingsFragment fragment = new SettingsFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        ButterKnife.bind(this, view);
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingFragmentPresenter.saveSettingData(settingIp.getText().toString(), settingPort.getText().toString());
            }
        });
        Utils utils = Utils.getInstance(getContext());
        settingIp.setFilters(utils.getIpFilter());
        settingFragmentPresenter = new SettingFragmentPresenter(this.getContext());
        settingFragmentPresenter.attachView(this);
        return view;
    }

    private void showAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setPositiveButton(getContext().getString(R.string.ok_button), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog =  builder.create();
        alertDialog.show();
    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMessage(String title, String message) {
        showAlertDialog(title, message);
    }

    @Override
    public void goToFragment(String fragmentName) {
        ((MainActivity)getActivity()).setFragmentByName(fragmentName);
    }
}
