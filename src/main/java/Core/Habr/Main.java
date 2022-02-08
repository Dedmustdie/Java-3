package Core.Habr;
import Core.Habr.Model.ArticleParser;
import Core.Habr.Model.ImgParser;

import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        /*ParserWorker<ArrayList<String>>  parser = new ParserWorker<>(new ArticleParser(), new ImgParser());
        // задайте значения переменным start и end
        parser.setParserSettings(new HabrSettings(1, 2));
        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewData());
        parser.Start();
        Thread.sleep(10000);
        parser.Abort();*/

        GUI gui = new GUI();


    }
}

