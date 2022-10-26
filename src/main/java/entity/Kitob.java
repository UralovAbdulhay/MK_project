package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;

@AllArgsConstructor
@NoArgsConstructor
public class Kitob {

    @Setter
    @Getter
    private String bookName;

    private ArrayList<Bob> chapters = new ArrayList<Bob>();


    public Kitob(String bookName) {
        this.bookName = bookName;
    }

    public ArrayList<Bob> getChapters() {
        return chapters;
    }

    public void addChapter(Bob bob) {
        this.chapters.add(bob);
    }

    @Override
    public String toString() {
        return String.format("<HTML>\n" +
                "<TITLE>%s</TITLE>\n" +
                "<BODY>\n" +
                "<P>\n" +
                StringService.toString(chapters.toArray()) +
                "</P>\n" +
                "</BODY>\n" +
                "</HTML>", bookName
        );
    }
}

