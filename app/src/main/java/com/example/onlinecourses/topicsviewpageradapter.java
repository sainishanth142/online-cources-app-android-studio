package com.example.onlinecourses;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class topicsviewpageradapter extends PagerAdapter {
    Context context;
    ArrayList<topicsviewpagermodel> list;
    LayoutInflater lf;

    public topicsviewpageradapter(Context context, ArrayList<topicsviewpagermodel> list) {
        this.context = context;
        this.list = list;
        lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==((ConstraintLayout) object);
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        // inflating the item.xml
        View itemView = lf.inflate(R.layout.viewtopicitem, container, false);

        // referencing the image view from the item.xml file
        ImageView imageView = (ImageView) itemView.findViewById(R.id.timage);
        TextView textView=itemView.findViewById(R.id.ttext);
        WebView webView=itemView.findViewById(R.id.tweb);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress)
            {
                progressDialog.setProgress(progress * 100);

                if(progress == 100)
                    progressDialog.dismiss();
            }
        });
        // setting the image in the imageView
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.setText(Html.fromHtml(list.get(position).getText(), Html.FROM_HTML_MODE_COMPACT));
        } else {
            textView.setText(Html.fromHtml(list.get(position).getText()));
        }
        webView.loadUrl(list.get(position).getWeblink());
        Picasso.get().load(list.get(position).getImagelink()).into(imageView);
        // Adding the View
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ConstraintLayout) object);
    }
}
