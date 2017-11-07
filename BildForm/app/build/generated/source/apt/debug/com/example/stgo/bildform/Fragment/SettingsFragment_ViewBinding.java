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

public class SettingsFragment_ViewBinding implements Unbinder {
  private SettingsFragment target;

  @UiThread
  public SettingsFragment_ViewBinding(SettingsFragment target, View source) {
    this.target = target;

    target.settingIp = Utils.findRequiredViewAsType(source, R.id.settingIp, "field 'settingIp'", EditText.class);
    target.settingPort = Utils.findRequiredViewAsType(source, R.id.settingPort, "field 'settingPort'", EditText.class);
    target.settingButton = Utils.findRequiredViewAsType(source, R.id.buttonSetting, "field 'settingButton'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.settingIp = null;
    target.settingPort = null;
    target.settingButton = null;
  }
}
