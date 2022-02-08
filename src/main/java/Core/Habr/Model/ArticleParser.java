package Core.Habr.Model;
import Core.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import  Core.Habr.ImgDownloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ArticleParser implements Parser<ArrayList<String>> {

    @Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();
        Elements elements = document.getElementsByAttributeValue("class", "tm-article-snippet");


        elements.forEach(element -> {

            Element headElement = element.getElementsByAttributeValue("class", "tm-article-snippet__title-link").first().child(0);
            Element textElement = element.getElementsByAttributeValue("class", "article-formatted-body article-formatted-body_version-2").first();

            if (textElement == null) {
                textElement = element.getElementsByAttributeValue("class", "article-formatted-body article-formatted-body_version-1").first();
            }

            Element imgElement = element.getElementsByAttributeValue("class", "tm-article-snippet__lead-image").first();
            if (imgElement != null) {
                String src = imgElement.attr("src");
                list.add(headElement.text() + "\n" + textElement.text() + "\n" + src + "\n\n");
            }
            else {
                list.add(headElement.text() + "\n" + textElement.text() + "\n" + "Картинка отсутствует!" + "\n\n");
            }

        });
        // получение списка названий статей
        return list;
    }
}
