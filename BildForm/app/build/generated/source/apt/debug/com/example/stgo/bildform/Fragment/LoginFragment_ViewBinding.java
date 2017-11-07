// Generated code from Butter Knife. Do not modify!
package com.example.stgo.bildform.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.stgo.bildform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginFragment_ViewBinding implements Unbinder {
  private LoginFragment target;

  @UiThread
  public LoginFragment_ViewBinding(LoginFragment target, View source) {
    this.target = target;

    target.userName = Utils.findRequiredViewAsType(source, R.id.loginUserName, "field 'userName'", EditText.class);
    target.userPassword = Utils.findRequiredViewAsType(source, R.id.loginUserPassword, "field 'userPassword'", EditText.class);
    target.loginButton = Utils.findRequiredViewAsType(source, R.id.loginButton, "field 'loginButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LoginFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.userName = null;
    target.userPassword = null;
    target.loginButton = null;
  }
}
