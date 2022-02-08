package Core.Habr.Model;
import Core.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class HabrParser implements Parser<ArrayList<String>> {

    @Override
    public ArrayList<String> Parse(Document document) {

        ArrayList<String> list = new ArrayList<String>();

        Elements elements = document.getElementsByAttributeValue("class", "tm-article-snippet__title-link");
        elements.forEach(element -> {
            Element spanElement = element.child(0);
            list.add(spanElement.text());
        });
        return list;
    }


}
