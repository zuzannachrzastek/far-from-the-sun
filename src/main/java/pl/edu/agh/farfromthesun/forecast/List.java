package pl.edu.agh.farfromthesun.forecast;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class List extends JPanel {
//   private JList list;
//
//    private final JTextPane text = new JTextPane();
//    private JScrollPane pane1;
//    private JScrollPane pane2;
//    private JLabel label1;
//    private JLabel label2;
//    private Icon icon;
//    private JLabel labelicon;
//    private StyleContext context;
//    private StyledDocument document;
//    private Style labelStyle;

//    public List(){
//        list = new JList(listOfCities.toArray());
//
//        label1 = new JLabel("List of Places");
//        label2 = new JLabel("Weather Forecast");
//
//        pane1 = new JScrollPane(list);
//        pane2 = new JScrollPane(text);
//
//        pane1.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
//        pane1.setPreferredSize(new Dimension(200,200));
//        pane1.setMinimumSize(new Dimension(200,200));
//
//        pane2.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
//        pane2.setPreferredSize(new Dimension(100,250));
//        pane2.setMinimumSize(new Dimension(100,250));
//
//        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
//        text.setEditable(false);
//
//        list.addMouseListener(new MouseAdapter() {
//            public void mouseClicked(MouseEvent e) {
//                text.setText("");
//                printWeather(text);
//            }
//        });
//
//        add(label1);
//        add(pane1);
//        add(Box.createRigidArea(new Dimension(0,5)));
//        add(label2);
//        add(pane2);
//    }

    public void printWeather(JTextPane pane, WeatherLocation wl){
        if(wl.getIconUrl() == null){
            System.out.println("Forecast has not been downloaded - date out of range or bad coordinates");
            return;
        }
        //appendIcon(icon);
        appendText("Date: ", pane);
        appendText(wl.getDate().toString() + "\n", pane);
        appendText("Latitude: ", pane);
        appendText(String.valueOf(wl.getLat()) + "\n",pane);
        appendText("Logitude: ", pane);
        appendText(String.valueOf(wl.getLon()) + "\n", pane);
        appendText("High Temperature: ", pane);
        appendText(String.valueOf(wl.getHighTemp()) + "\n", pane);
        appendText("Low Temperature: ", pane);
        appendText(String.valueOf(wl.getLowTemp()) + "\n", pane);
        appendText("Precipitation Type: ", pane);
        appendText(wl.getPrecipitationType().toString() + "\n", pane);
        appendText("Precipitation Level: ", pane);
        appendText(String.valueOf(wl.getPrecipitationLevel()) + "\n", pane);
        appendText("Average Wind: ", pane);
        appendText(String.valueOf(wl.getAveWind()) + "\n", pane);
        appendText("Average Humidity: ", pane);
        appendText(String.valueOf(wl.getAveHumidity()) + "\n", pane);

    }

    public void appendText(String appended, JTextPane pane){
        Document doc = pane.getDocument();
        try{
            doc.insertString(doc.getLength(), appended, null);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}
