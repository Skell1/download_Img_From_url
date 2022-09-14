import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = Jsoup.connect("https://lenta.ru");
            Document doc = connection.get();
            System.out.println(doc);
            Elements elements = doc.select("img");
            elements.forEach(element -> downloadImg(element.attr("abs:src")));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void downloadImg (String url) {
        try {
            InputStream inputStream = new URL(url).openStream();
            String path1 =url.substring(url.lastIndexOf('/')+1);
            String path = "data\\"+ path1;
            if (!new File(path).exists()) {
                Files.copy(inputStream, Path.of(path));
            }
            inputStream.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
