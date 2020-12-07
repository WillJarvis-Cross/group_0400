package Gateways;

import java.util.ArrayList;

public class ExportHTML {
    private String pathPrefix = "";
    private ArrayList Events = null;

    private static final String head = "<!DOCTYPE html>" +
            "<html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width," +
            "initial-scale=1.0\"><title>Document</title></head><body><section><table border=\"1\">";

    private static final String footer = "</table>" +
            "  </section>" +
            "  <style>" +
            "    section {" +
            "      padding: 0 5%;" +
            "    }" +
            "    table {" +
            "      width: 100%;" +
            "    }" +
            "    table, table * {" +
            "      border: none;" +
            "      border-collapse: collapse;" +
            "    }" +
            "    th {" +
            "      border-top: 1px solid lightgray;" +
            "      background-color: lightgray;" +
            "    }" +
            "    th, td {" +
            "      text-align: center;" +
            "      padding: 4px 8px;" +
            "      border-bottom: 1px solid lightgray;" +
            "    }" +
            "  </style>" +
            "</body>" +
            "</html>";

    public void setEvents(ArrayList events) {
        Events = events;
        System.out.println(this.Events);
    }

    private String encapsulateTag(String tagName, String content) {
        if (tagName.equals("html")) return head + content + footer;
        return "<" + tagName + ">" + content + "</" + tagName + ">";
    }

    private String createTableBody(String[] columns, String[][] rows) {
        String headerContent = "";
        for (String column : columns) {
            headerContent += encapsulateTag("th", column);
        }
        headerContent = encapsulateTag("thead", encapsulateTag("tr", headerContent));

        String tableBodyContent = "";
        for (String[] row : rows) {
            String tableRow = "";
            for (String cell : row) {
                tableRow += encapsulateTag("td", cell);
            }
            tableBodyContent += encapsulateTag("tr", tableRow);
        }
        tableBodyContent += encapsulateTag("tbody", tableBodyContent);
        return encapsulateTag("html", (headerContent + tableBodyContent));
    }

    public boolean exportHTML(String fileName, String[] columns, String[][] rows) {
        try {
            ReadAndWrite writeHTMl = new ReadAndWrite();
            String content = createTableBody(columns, rows);
            System.out.println(content);
            writeHTMl.exportAsHTML("index", content);
        } catch (Exception e) {
            System.out.println("There was some error: " + e.toString());
            return false;
        }
        return true;
    }
}
