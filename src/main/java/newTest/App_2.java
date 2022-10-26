package newTest;

import entity.Bob;
import entity.Kitob;
import entity.Oyat;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App_2 {
    public static void main(String[] args) throws Exception {

        List<Kitob> kitobs = new LinkedList<>();
        boolean b = false;


        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse("input/response_latin.xml");

        NodeList osis = doc.getElementsByTagName("div");

        System.out.println(osis.getLength());
        for (int i = 0; i < osis.getLength(); i++) {
            Node eskiAhd = osis.item(i);
            if (isElementAndExistValue(eskiAhd, "type", "bookGroup")) {
                // Eski va Yangi Ahd ga bo'lingan qisim
                NodeList oldBooks = getNodeList(eskiAhd, "div");

                // ahd kitoblari bo'yicha aylanish
                for (int j = 0; j < oldBooks.getLength(); j++) {
                    Node book = oldBooks.item(j);
                    if (isElementAndExistValue(book, "type", "book")) {
                        // kitoblarga bo'lingan qisim

                        String bookName = "";
                        int chaptersCount = 0;

                        // Kitobning nomini olish uchun barcha
                        NodeList titles = getNodeList(book, "title");
                        for (int t = 0; t < titles.getLength(); t++) {
                            Node title = titles.item(t);
                            if (isElementAndExistValue(title, "type", "main")
                                    && isElementAndExistValue(title, "level", "1")) {
                                bookName = title.getTextContent();
                            }
                        }

                        NodeList chapters = getNodeList(book, "chapter");
                        chaptersCount = chapters.getLength();

                        System.out.println(j + " - " + bookName + " - " + chaptersCount);

                        Kitob kitob = new Kitob(bookName);


                        // chapter lar bo'yicha aylanish
                        for (int c = 0; c < chapters.getLength(); c++) {
                            Node chapter = chapters.item(c);
                            NodeList verses = getNodeList(chapter, "verse");

                            Bob bob = new Bob(c + 1);
                            String bobName = getNode(chapter, "title").getTextContent();
                            System.out.println(bobName);

                            System.out.println(verses.getLength());


                            for (int v = 0; v < verses.getLength(); v++) {
                                Node verseXml = verses.item(v);

                                deleteNode(verseXml);
//                                System.out.println(b);
                                String id = getAttribute(verseXml, "osisID");
                                String oyatXml = getTextContent(verseXml);

                                b = id.equals("Ps.3.1");

//                                System.out.println("**************************");
//                                System.out.println("oyat = " + oyatXml);
                                System.out.println("id = " + id);
                                parseId(id, oyatXml, bob);
//                                System.out.println("###############################");


//                                System.out.println(String.format("\t%s - %s", id, oyat));

                            }

//                            if (b) System.out.println("kitobga bob qo'shish boshlandi");
                            kitob.addChapter(bob);
//                            if (b) System.out.println("kitobga bob qo'shish tugadi");
                        }


//                        break;
//                        if (b) System.out.println("kitobsga kitob qo'shish boshlandi");
                        kitobs.add(kitob);
//                        if (b) System.out.println("kitobsga kitob qo'shish tugadi");
                    }
                }


                System.out.println("*************************");
            }
        }

        System.out.println(kitobs.size());

        for (int i = 0; i < kitobs.size(); i++) {
            String htmlOutPath = "output";
            BufferedWriter htmlWriter = new BufferedWriter(new FileWriter(htmlOutPath + "/" + "uz" + (i + 1) + ".htm"));

            htmlWriter.write(kitobs.get(i).toString());
            htmlWriter.flush();

        }

//        appRun(kitobs);

    }


    /*
     * <reference type="x-glossary"></reference>
     *
     *
     *
     * //
     * delete
     * title canonical="false"
     * <milestone>
     *<list></list>
     * */

    static NodeList getNodeList(Node node, String tagName) {
        return ((Element) node).getElementsByTagName(tagName);
    }

    static boolean isElementAndExistValue(Node node, String attributeName, String attributeValue) {
        return node.getNodeType() == Node.ELEMENT_NODE && ((Element) node).getAttribute(attributeName).equals(attributeValue);
    }

    static String getAttribute(Node node, String attribute) {
        return ((Element) node).getAttribute(attribute);
    }

    static String getTextContent(Node node) {
        String s = node.getTextContent()/*.replace("—", "-")*/;
//        s = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

//        return s;

        while (

                false &&
                (
                s.contains("  ")
                        || s.contains(" .")
                        || s.contains("ʻ")
                ||s.contains("—")
                || s.contains("–")
                || s.contains("“")
        )
        ) {

            s = s.replace("  ", " ");
            s = s.replace(" .", ".");
            s = s.replace("—", "-");
            s = s.replace("–", "-");
            s = s.replace("ʻ", "'");
            s = s.replace("“", "\"");
        }


        return s;

//        return node.getTextContent();
    }


    static Node getNode(Node node, String nodeName) {
        return ((Element) node).getElementsByTagName(nodeName).item(0);
    }

    static void deleteNode(Node node) {
//        System.out.println("start of delete");

        NodeList note = ((Element) node).getElementsByTagName("note");
//        System.out.println("end of get element note ");

        NodeList milestone = ((Element) node).getElementsByTagName("milestone");
//        System.out.println("end of get element milestone ");

        NodeList title = ((Element) node).getElementsByTagName("title");
//        System.out.println("end of get element title ");

        NodeList list = ((Element) node).getElementsByTagName("list");
//        System.out.println("end of get element list ");


        while (0 < note.getLength()) {
            Node node1 = note.item(0);
            node1.getParentNode().removeChild(node1);
        }
//        System.out.println("end of get element note while ");


        while (0 < milestone.getLength()) {
            Node node1 = milestone.item(0);
            node1.getParentNode().removeChild(node1);
        }
//        System.out.println("end of get element milestone while ");


        while (0 < list.getLength()) {
            Node node1 = list.item(0);
            node1.getParentNode().removeChild(node1);

        }
//        System.out.println("end of get element list while ");

        ArrayList<Node> titleList = new ArrayList<>();
        for (int i = 0; i < title.getLength(); i++) {
            titleList.add(title.item(i));

        }

        titleList.stream()
                .filter(e -> "false".equals(((Element) e).getAttribute("canonical")) && !((Element) e).hasAttribute("type"))
                .forEach(e -> e.getParentNode().removeChild(e));


//        while (0 < title.getLength()) {
//            System.out.println("in of title while ");
//            Element node1 = (Element) title.item(0);
//            if (node1.getAttribute("canonical").equals("false")
//                    && !node1.hasAttribute("type")) {
//                ((Node) node1).getParentNode().removeChild(node1);
//            }
//
//        }
//        System.out.println("end of get element title while ");

//        System.out.println("end of delete");


    }


    static void parseId(String id, String oyatXml, Bob bobModel) {
        boolean b = id.equals("Ps.3.1");
        if (b) System.out.println("parseId boshlandi");
        if (!id.contains(" ")) {
            if (b) System.out.println(" if (!id.contains()) ni ichiga kirdi");
            setValues(id, oyatXml, bobModel);
            if (b) System.out.println(" setValues tugadi");
        } else {
            // 2 ta oyat 1 ta text bilan kelgan vaziyat uchun
            String[] oyats = id.split(" ");
            for (String oyat : oyats) {
                setValues(oyat, oyatXml, bobModel);
            }
        }
    }


    static void setValues(String id, String oyatXml, Bob bobModel) {
        String[] s = id.split("\\.");
        String kitob = s[0];
        int bob = Integer.parseInt(s[1]);
        int oyatNum = Integer.parseInt(s[2]);

        if (bobModel.getChapterNum() == bob) {
            bobModel.addOyat(new Oyat(oyatNum, oyatXml));
        }
    }


    private static void appRun(List<Kitob> kitobs) {


        Scanner k = new Scanner(System.in);

        while (true) {
            System.out.println("Kitobning raqamini kiriting:");
            int bookNum = k.nextInt();
            System.out.println("bobning raqamini kiriting:");
            int bobNum = k.nextInt();
            System.out.println("oyatning raqamini kiriting:");
            int oyatNum = k.nextInt();

            String verse = kitobs.get(bookNum - 1).getChapters().get(bobNum - 1).getVerses().get(oyatNum - 1).getVerseValue();

            System.out.println(verse);
            System.out.println("##################");
            System.out.println();

        }


    }

    static String trimDoubleSpace(String s) {

        while (
                s.contains("  ")
//                        || s.contains("\n")
                        || s.contains(" .")
                        || s.contains("ʻ")
//                ||s.contains("–")
        ) {
            s = s.replace("  ", " ");
//            s = s.replace("\n", " ");
            s = s.replace(" .", ".");
//            s = s.replace("–", "-");
            s = s.replace("ʻ", "'");
        }
//        s = "*" + s;
        return s;
    }


}
