package com.example.formula1.fragmentsMenu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.formula1.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    WebView page;
    public Home() {
        // Required empty public constructor
    }


    public static Home newInstance( ) {
        Home fragment = new Home();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vista = inflater.inflate(R.layout.fragment_home, container, false);
        page = vista.findViewById(R.id.pageF1);
        page.getSettings().setJavaScriptEnabled(true);
        page.getSettings().setDomStorageEnabled(true);
        page.getSettings().setAllowFileAccess(true);
        page.loadUrl("https://www.formula1.com/");
        page.setWebViewClient(new WebViewClient(){
            //con esto controlo con javaScript la página esto me ayuda para eliminar la cabecera que por defecto que tiene la página.
            //además quito el espacio que tiene la cabecera es decir el margin que contiene la página, por que el desarrollador lo puso.
            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                view.loadUrl("javascript:(function() { " +
                        "var body = document.querySelector('body');" +
                        "var navbar = document.getElementsByClassName('site-nav')[0]; " +
                        "body.style.marginTop = '0';" +
                        "navbar.style.display = 'none'; " +
                        "})()");
            }
            //aquí contemplo si el usuario pulsa por encima de una noticia de formula 1 este no cargue un navegador o intente instanciar en el navegador.
            //entonces aquí vuelvo a cargar la página
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        return vista;
    }
}