package Gateways;

import java.util.List;


/** This is an entity for a Conference, and it will export a timetable in HTML format of the events that the user is
 * attending. Information includes; the date, time, duration, speaker, event name and like status.
 * @author group 0400
 */

public class ExportHTML {
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

    /**
     * Encapsulates the passed content with the passed tag name.
     * @param tagName the tag name of an HTML element.
     * @param content the content of the tag.
     * @return a String representation of the tag with the content.
     */
    private String encapsulateTag(String tagName, String content) {
        if (tagName.equals("html")) return head + content + footer;
        return "<" + tagName + ">" + content + "</" + tagName + ">";
    }

    /**
     * Creates the table structure of the passed columns as headers and rows as the body.
     * @param columns list of columns for the table. Also represents the number of cells per row.
     * @param rows list of rows for the table.
     * @return a String representation of a table.
     */
    private String createTableBody(List<String> columns, List<List<String>> rows) {
        String headerContent = "";
        for (String column : columns) {
            headerContent += encapsulateTag("th", column);
        }
        headerContent = encapsulateTag("thead", encapsulateTag("tr", headerContent));

        String tableBodyContent = "";
        String tableBodyContentFinal = "";
        for (List<String> row : rows) {
            String tableRow = "";
            for (String cell : row) {
                tableRow += encapsulateTag("td", cell);
            }
            tableBodyContent += encapsulateTag("tr", tableRow);
        }
        tableBodyContentFinal += encapsulateTag("tbody", tableBodyContent);
        return encapsulateTag("html", (headerContent + tableBodyContentFinal));
    }

    /**
     * Creates an html file with the passed filename that represents the users events as a HTML Table element
     * which can be opened using any browser
     * @param fileName the name of the html file to be exported / generated
     * @param columns the columns / headers of the table to generate
     * @param rows the list of list of columns for each event
     * @return true iff the file was exported successfully, otherwise false.
     */
    public boolean exportHTML(String fileName, List<String> columns, List<List<String>> rows) {
        try {
            ReadAndWrite writeHTMl = new ReadAndWrite();
            String content = createTableBody(columns, rows);
            writeHTMl.exportAsHTML(fileName, content);
        } catch (Exception e) {
            System.out.println("There was some error: " + e.toString());
            return false;
        }
        return true;
    }
}