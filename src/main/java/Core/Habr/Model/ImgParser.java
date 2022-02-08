package Core.Habr.Model;

import Core.Habr.ImgDownloader;
import Core.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class ImgParser implements Parser<ArrayList<String>> {
    @Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();
        Elements elements = document.getElementsByAttributeValue("class", "tm-article-snippet");


        elements.forEach(element -> {
            Element imgElement = element.getElementsByAttributeValue("class", "tm-article-snippet__lead-image").first();
            if (imgElement != null) {
                String src = imgElement.attr("src");
                list.add(src);
            }
        });
        // получение списка названий статей
        return list;
    }
}
