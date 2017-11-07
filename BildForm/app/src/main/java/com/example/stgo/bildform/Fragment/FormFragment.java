package com.example.stgo.bildform.Fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.stgo.bildform.Activity.MainActivity;
import com.example.stgo.bildform.Database.FormResponseDbAdapter;
import com.example.stgo.bildform.Model.FormResponse;
import com.example.stgo.bildform.Presenter.FormFragmentPresenter;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.FormFragmentView;
import com.google.gson.stream.JsonWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FormFragment extends Fragment implements FormFragmentView {
    View view;
    @BindView(R.id.formTitle) TextView formTitle;
    @BindView(R.id.formSaveButton) Button formSaveButton;
    @BindView(R.id.formButton) Button fromButton;
    @BindView(R.id.fromLayout) LinearLayout fromLayout;
    private FormFragmentPresenter formFragmentPresenter;
    private ArrayList<View> views;


    public static FormFragment newInstance(){
        FormFragment fragment = new FormFragment();
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
        formFragmentPresenter.detachView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_form, container, false);
        ButterKnife.bind(this, view);
        fromButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!formFragmentPresenter.existFrom()){
                    formFragmentPresenter.getForms();
                }
                else{
                    formFragmentPresenter.prepareFrom();
                }
            }
        });
        formSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFormResponse();
            }
        });
        formFragmentPresenter = new FormFragmentPresenter(this.getContext());
        formFragmentPresenter.attachView(this);
        return view;
    }

    private void getFormResponse(){
        JSONObject responses = new JSONObject();
        JSONArray jArray = new JSONArray();
        try {
            for (View v: views){
                JSONObject j = new JSONObject();
                TextInputLayout t = (TextInputLayout) v;
                    j.put(t.getHint().toString(), t.getEditText().getText().toString());
                    jArray.put(j);

            }
            responses.put("responses", jArray);
            FormResponse formResponse = new FormResponse(responses.toString());
            FormResponseDbAdapter frdb = FormResponseDbAdapter.getInstance(getContext());
            long d = frdb.addFormResponse(formResponse);
            Log.d("getFormResponse", "getFormResponse: " + d);
        } catch (JSONException e) {
            e.printStackTrace();
        }
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

    @Override
    public void showForm(ArrayList<View> views) {
        this.views = views;
        ScrollView contentView = new ScrollView(this.getContext());
        contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        LinearLayout screen = new LinearLayout(this.getContext());
        screen.setOrientation(LinearLayout.VERTICAL);
        screen.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        screen.setPadding(20,0,20,0);
        for (View v:views) {
            screen.addView(v);
        }
        contentView.addView(screen);
        fromLayout.addView(contentView);
        formSaveButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showFromTitle(String title) {
        formTitle.setText(title);
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
}
