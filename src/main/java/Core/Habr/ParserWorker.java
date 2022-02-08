package Core.Habr;

import Core.Parser;
import Core.ParserSettings;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWorker<T> {
    Parser<T> parser;
    ParserSettings parserSettings;
    HtmlLoader loader;
    Parser<T> imgParser;
    boolean isActive;
    public ArrayList<String> list;
    public  ParserWorker(Parser<T> parser, Parser<T> imgParser) {
        this.parser = parser;
        this.imgParser = imgParser;
    }

    public void setImgParser(Parser<T> imgParser) {
        this.imgParser = imgParser;
    }
    public Parser<T> getImgParser() {
        return imgParser;
    }
    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }
    public Parser<T> getParser() {
        return parser;
    }
    public void setParserSettings(ParserSettings parserSettings) {
        this.parserSettings = parserSettings;
        loader = new HtmlLoader(parserSettings);
    }
    public ParserSettings getParserSettings() {
        return parserSettings;
    }

    public void Start() throws IOException {
        isActive = true;
        Worker();
    }
    public void Abort() {
        isActive = false;
    }
    public interface OnNewDataHandler<T> {
        void OnNewData(Object sender, T e);
    }
    public interface OnCompleted {
        void OnCompleted(Object sender);
    }
    ArrayList<OnNewDataHandler> onNewDataList = new ArrayList<>();
    ArrayList<OnCompleted> onCompletedList = new ArrayList<>();
    private void Worker() throws IOException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPageId(i);
            ImgDownloader downloader = new ImgDownloader(parserSettings.savePath);
            T result = parser.Parse(document);

            //list.add(parser.Parse(document));
            downloader.download((ArrayList<String>) imgParser.Parse(document));

            onNewDataList.get(0).OnNewData(this, result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

    public ArrayList<String> getData(ArrayList<String> result) {
        return result;
    }
}

