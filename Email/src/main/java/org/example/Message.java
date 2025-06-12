package org.example;
import java.time.LocalDate;

public class Message {
    private int id;
    private String contenu;
    private LocalDate localDate;

    public Message(int id, String contenu, LocalDate date) {
        this.id = id;
        this.contenu = contenu;
        this.localDate = date;
    }

    public String getContenu() { return contenu; }
}
