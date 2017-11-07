package com.example.stgo.bildform.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.text.InputType;
import android.view.View;

import com.example.stgo.bildform.APIS.ConnectCallback;
import com.example.stgo.bildform.APIS.Utils;
import com.example.stgo.bildform.Interactor.FormFragmentInteractor;
import com.example.stgo.bildform.Model.Form;
import com.example.stgo.bildform.Model.Input;
import com.example.stgo.bildform.R;
import com.example.stgo.bildform.View.FormFragmentView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by shagos on 05-11-17.
 */

public class FormFragmentPresenter implements  Presenter<FormFragmentView>{
    private Context context;
    FormFragmentView formFragmentView;
    FormFragmentInteractor formFragmentInteractor;

    public FormFragmentPresenter(Context context) {
        this.context = context;
        formFragmentInteractor = new FormFragmentInteractor(this.context);
    }

    public boolean existFrom(){
        return formFragmentInteractor.existFrom();
    }

    public void getForms(){

        formFragmentInteractor.getForms(new ConnectCallback() {
            @Override
            public void onResponse(Object result) {
                formFragmentInteractor.saveFromJson((JSONObject) result);
                prepareFrom();
                formFragmentView.showMessage(context.getString(R.string.form_message_title),
                        context.getString(R.string.form_success));
            }

            @Override
            public void onError(Object error) {
                formFragmentView.showMessage(context.getString(R.string.form_message_title),
                        context.getString(R.string.form_error));
                formFragmentInteractor.deleteTokens();
                formFragmentView.goToFragment("Login");
            }
        });
    }

    public void prepareFrom(){
        JSONObject jForm = null;
        try {
            jForm = new JSONObject(formFragmentInteractor.getForm().getValue());
            JSONArray inputs = new JSONArray();
            String title = "";
            ArrayList<Input> arrayInput = new ArrayList<>();
            JSONArray formArray = jForm.getJSONObject("msg").getJSONArray("forms");
            for (int i = 0; i < formArray.length(); i++){
                JSONObject j = formArray.getJSONObject(i);
                title = j.getString("name");
                inputs = j.getJSONArray("inputs");
            }
            for (int i = 0; i < inputs.length(); i++){
                JSONObject j = inputs.getJSONObject(i);
                Input input = new Input();
                input.setName(j.getString("name"));
                input.setType(j.getString("type"));
                input.setRequired(j.getBoolean("required"));
                arrayInput.add(input);
            }
            formFragmentView.showFromTitle(title);
            formFragmentView.showForm(getViewByInput(arrayInput));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<View> getViewByInput(ArrayList<Input> inputs){
        ArrayList<View> views = new ArrayList<>();
        Utils utils = Utils.getInstance(context);
        for (Input i:inputs) {
            switch (i.getType()){
                case "text":
                    TextInputLayout textInput = new TextInputLayout(context);
                    TextInputEditText editText = new TextInputEditText(context);
                    editText.setHint(i.getName());
                    editText.setInputType(InputType.TYPE_TEXT_VARIATION_NORMAL);
                    editText.setTextColor(Color.BLACK);
                    editText.setTextSize(20);
                    textInput.addView(editText);
                    if (i.isRequired()){
                        textInput.setErrorEnabled(true);
                        textInput.setError(context.getString(R.string.form_required));
                    }
                    textInput.setHintAnimationEnabled(true);
                    textInput.setHintEnabled(true);
                    views.add(textInput);
                    break;
                case "email":
                    TextInputLayout emailInput = new TextInputLayout(context);
                    TextInputEditText editEmail = new TextInputEditText(context);
                    editEmail.setTextColor(Color.BLACK);
                    editEmail.setTextSize(20);
                    editEmail.setHint(i.getName());
                    editEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                    emailInput.addView(editEmail);
                    if (i.isRequired()){
                        emailInput.setErrorEnabled(true);
                        emailInput.setError(context.getString(R.string.form_required));
                    }
                    emailInput.setHintAnimationEnabled(true);
                    views.add(emailInput);
                    break;
            }

        }
        return views;
    }
    @Override
    public void attachView(FormFragmentView view) {
        if (view == null)
            throw new IllegalArgumentException("view is Null");
        formFragmentView = view;
    }

    @Override
    public void detachView() {
        formFragmentView = null;
    }

    @Override
    public void onResume() throws Exception {

    }
}
