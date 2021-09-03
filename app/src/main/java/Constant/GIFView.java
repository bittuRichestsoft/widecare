package Constant;

import android.content.Context;
import android.graphics.Color;
import android.webkit.WebView;

import java.io.IOException;

/**
 * Created by indiaweb on 10/15/2016.
 */
public class GIFView extends WebView {

    public static final String HTML_FORMAT = "<html><body style=\"text-align: center;  vertical-align:                       right;background-color: transparent;\"><img src = \"%s\" /></body></html>";

    public GIFView(Context context, int fileUrl) throws IOException {
        super(context);


        final String html = String.format(HTML_FORMAT, fileUrl);

        setBackgroundColor(Color.TRANSPARENT);
        loadDataWithBaseURL("", html, "text/html", "UTF-8", "");

    }

}