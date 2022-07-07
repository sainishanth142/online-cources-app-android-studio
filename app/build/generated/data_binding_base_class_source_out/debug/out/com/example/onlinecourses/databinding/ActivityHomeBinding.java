// Generated by view binder compiler. Do not edit!
package com.example.onlinecourses.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import androidx.viewpager.widget.ViewPager;
import com.example.onlinecourses.R;
import com.google.android.material.navigation.NavigationView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityHomeBinding implements ViewBinding {
  @NonNull
  private final DrawerLayout rootView;

  @NonNull
  public final DrawerLayout dra;

  @NonNull
  public final LinearLayout linearLayout;

  @NonNull
  public final ImageButton menubt;

  @NonNull
  public final NavigationView navd;

  @NonNull
  public final RecyclerView recyclerview;

  @NonNull
  public final TextView usname;

  @NonNull
  public final ViewPager viewpager;

  private ActivityHomeBinding(@NonNull DrawerLayout rootView, @NonNull DrawerLayout dra,
      @NonNull LinearLayout linearLayout, @NonNull ImageButton menubt, @NonNull NavigationView navd,
      @NonNull RecyclerView recyclerview, @NonNull TextView usname, @NonNull ViewPager viewpager) {
    this.rootView = rootView;
    this.dra = dra;
    this.linearLayout = linearLayout;
    this.menubt = menubt;
    this.navd = navd;
    this.recyclerview = recyclerview;
    this.usname = usname;
    this.viewpager = viewpager;
  }

  @Override
  @NonNull
  public DrawerLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHomeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_home, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHomeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      DrawerLayout dra = (DrawerLayout) rootView;

      id = R.id.linearLayout;
      LinearLayout linearLayout = ViewBindings.findChildViewById(rootView, id);
      if (linearLayout == null) {
        break missingId;
      }

      id = R.id.menubt;
      ImageButton menubt = ViewBindings.findChildViewById(rootView, id);
      if (menubt == null) {
        break missingId;
      }

      id = R.id.navd;
      NavigationView navd = ViewBindings.findChildViewById(rootView, id);
      if (navd == null) {
        break missingId;
      }

      id = R.id.recyclerview;
      RecyclerView recyclerview = ViewBindings.findChildViewById(rootView, id);
      if (recyclerview == null) {
        break missingId;
      }

      id = R.id.usname;
      TextView usname = ViewBindings.findChildViewById(rootView, id);
      if (usname == null) {
        break missingId;
      }

      id = R.id.viewpager;
      ViewPager viewpager = ViewBindings.findChildViewById(rootView, id);
      if (viewpager == null) {
        break missingId;
      }

      return new ActivityHomeBinding((DrawerLayout) rootView, dra, linearLayout, menubt, navd,
          recyclerview, usname, viewpager);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}