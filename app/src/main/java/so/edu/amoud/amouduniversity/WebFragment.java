package so.edu.amoud.amouduniversity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class WebFragment extends Fragment {

//    private Bundle webViewBundle;
//    private WebView webView;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_web, container, false);
//
//        WebView webView = (WebView) view.findViewById(R.id.wbWeb);
////        webView.loadUrl("http://www.amouduniversity.org/");
////
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//
//        if (webViewBundle == null ){
//            webView.loadUrl("http://www.amouduniversity.org/");
//        }else {
//            webView.restoreState(webViewBundle);
//        }
//
//
//        return view;
//    }
////    @Override
////    public void onPause() {
////        super.onPause();
////        webViewBundle = new Bundle();
////        webView.saveState(webViewBundle);
////    }

    Context context;
    private WebView myWebView;
    SwipeRefreshLayout mySwipeRefreshLayout;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        myWebView.saveState(outState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myWebView.restoreState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle saved)
    {
        View v = inflater.inflate(R.layout.fragment_web, group, false);
        final ProgressDialog pd = ProgressDialog.show(getActivity(),  "Amoud University Web", "Please Wait", true);
        pd.setCanceledOnTouchOutside(true);

        Bundle bundle =getArguments();
        if (bundle != null){
            String url = getArguments().getString("Url");
        }

        myWebView = (WebView) v.findViewById(R.id.wbWeb);
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                pd.dismiss();
            }
        });

        myWebView.getSettings().setBuiltInZoomControls(false);
        myWebView.getSettings().setSupportZoom(false);
        myWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setAllowFileAccess(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.canGoBack();
        myWebView.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);

        myWebView.loadUrl("http://www.amouduniversity.org/");
        myWebView.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK
                        && event.getAction() == MotionEvent.ACTION_UP
                        && myWebView.canGoBack()) {
                    myWebView.goBack();
                    if (null != mySwipeRefreshLayout) {
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                    return true;
                }
                return false;
            }
        });

        mySwipeRefreshLayout = (SwipeRefreshLayout)v.findViewById(R.id.swipeContainer);
        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                myWebView.reload();
            }
        });

        return v;
    }

}

