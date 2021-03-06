// Generated by view binder compiler. Do not edit!
package com.example.onlinecourses.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.onlinecourses.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivitySigninBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText collegename;

  @NonNull
  public final EditText email;

  @NonNull
  public final EditText firstname;

  @NonNull
  public final EditText lastname;

  @NonNull
  public final EditText mobilenumber;

  @NonNull
  public final EditText password;

  @NonNull
  public final EditText registernumber;

  private ActivitySigninBinding(@NonNull ConstraintLayout rootView, @NonNull EditText collegename,
      @NonNull EditText email, @NonNull EditText firstname, @NonNull EditText lastname,
      @NonNull EditText mobilenumber, @NonNull EditText password,
      @NonNull EditText registernumber) {
    this.rootView = rootView;
    this.collegename = collegename;
    this.email = email;
    this.firstname = firstname;
    this.lastname = lastname;
    this.mobilenumber = mobilenumber;
    this.password = password;
    this.registernumber = registernumber;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivitySigninBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_signin, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivitySigninBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.collegename;
      EditText collegename = ViewBindings.findChildViewById(rootView, id);
      if (collegename == null) {
        break missingId;
      }

      id = R.id.email;
      EditText email = ViewBindings.findChildViewById(rootView, id);
      if (email == null) {
        break missingId;
      }

      id = R.id.firstname;
      EditText firstname = ViewBindings.findChildViewById(rootView, id);
      if (firstname == null) {
        break missingId;
      }

      id = R.id.lastname;
      EditText lastname = ViewBindings.findChildViewById(rootView, id);
      if (lastname == null) {
        break missingId;
      }

      id = R.id.mobilenumber;
      EditText mobilenumber = ViewBindings.findChildViewById(rootView, id);
      if (mobilenumber == null) {
        break missingId;
      }

      id = R.id.password;
      EditText password = ViewBindings.findChildViewById(rootView, id);
      if (password == null) {
        break missingId;
      }

      id = R.id.registernumber;
      EditText registernumber = ViewBindings.findChildViewById(rootView, id);
      if (registernumber == null) {
        break missingId;
      }

      return new ActivitySigninBinding((ConstraintLayout) rootView, collegename, email, firstname,
          lastname, mobilenumber, password, registernumber);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
