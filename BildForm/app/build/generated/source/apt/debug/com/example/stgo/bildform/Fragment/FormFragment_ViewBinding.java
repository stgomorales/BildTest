// Generated code from Butter Knife. Do not modify!
package com.example.stgo.bildform.Fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.stgo.bildform.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FormFragment_ViewBinding implements Unbinder {
  private FormFragment target;

  @UiThread
  public FormFragment_ViewBinding(FormFragment target, View source) {
    this.target = target;

    target.formTitle = Utils.findRequiredViewAsType(source, R.id.formTitle, "field 'formTitle'", TextView.class);
    target.formSaveButton = Utils.findRequiredViewAsType(source, R.id.formSaveButton, "field 'formSaveButton'", Button.class);
    target.fromButton = Utils.findRequiredViewAsType(source, R.id.formButton, "field 'fromButton'", Button.class);
    target.fromLayout = Utils.findRequiredViewAsType(source, R.id.fromLayout, "field 'fromLayout'", LinearLayout.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FormFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.formTitle = null;
    target.formSaveButton = null;
    target.fromButton = null;
    target.fromLayout = null;
  }
}
