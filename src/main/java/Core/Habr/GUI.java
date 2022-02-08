package Core.Habr;

import Core.Habr.Model.ArticleParser;
import Core.Habr.Model.ImgParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

public class GUI extends JFrame {
    JTextArea textArea = new JTextArea();

    public GUI() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        setTitle("Habr");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel rightPanel = new JPanel();
        JPanel Panel = new JPanel();
        Panel.setPreferredSize(new Dimension(500, 500));
        rightPanel.setPreferredSize(new Dimension(500, 500));
        GridLayout layout = new GridLayout(0, 1, 5, 12);
        rightPanel.setLayout(layout);
        rightPanel.add(new JLabel("Первая страница"));
        JTextField textStart = new JTextField(10);
        JTextField textEnd = new JTextField(10);
        JButton startButton = new JButton("Start");
        JButton abortButton = new JButton("Abort");
        add(rightPanel, BorderLayout.EAST);


        rightPanel.add(startButton);
        rightPanel.add(abortButton);
        Panel.add(textArea);
        add(Panel);



        ParserWorker<ArrayList<String>>  parser = new ParserWorker<>(new ArticleParser(), new ImgParser());


        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                parser.setParserSettings(new HabrSettings(1, 2));
                parser.onCompletedList.add(new Completed());
                parser.onNewDataList.add(new NewDataa());

                try {
                    parser.Start();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        abortButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                parser.Abort();
                //textArea.setToolTipText("Парсинг приостановлен!");
            }
        });

        pack();
        setVisible(true);
    }


    class Completed implements ParserWorker.OnCompleted {

        @Override
        public void OnCompleted(Object sender) {
            //System.out.println("Загрузка закончена");
            textArea.append("\nЗагрузка закончена");
        }
    }
    class NewDataa implements ParserWorker.OnNewDataHandler<ArrayList<String>> {

        @Override
        public void OnNewData(Object sender, ArrayList<String> args) {
            for (String s : args) {
                textArea.append("\n"+s);
            }
        }
    }
}
