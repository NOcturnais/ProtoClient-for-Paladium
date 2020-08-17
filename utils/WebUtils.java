package fr.noctu.haxx.proto.utils;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;

public class WebUtils {
    public static String getPageContent(URL urlS) throws IOException {
        URL url = urlS;
        Scanner sc = new Scanner(url.openStream());
        StringBuffer sb = new StringBuffer();

        while(sc.hasNext()) {
            sb.append(sc.next());
        }
        String result = sb.toString();
        result = result.replaceAll("<[^>]*>", "");
        return result;
    }
}
