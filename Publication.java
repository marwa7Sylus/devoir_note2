package Social_media;

import java.time.LocalDate;

public abstract class Publication {
    private int  id;
    public  String auteur;
    public  String content;
    public LocalDate date_publication;
    public Publication(int i, String a, String c, LocalDate dat) {
        this.id = i;
        this.auteur = a;
        this.content = c;
        this.date_publication =dat ;
    }

    public int getId() {return id;}
    public String getAuteur() {return auteur;}
    public String getContent() {return content;}
    public LocalDate getDate_publication() {return date_publication;}
    public abstract boolean is_potentially_Fake(int id);
    public void addpublication(int id, String auteur, String content, LocalDate date_publication) {
        this.id = id;
        this.auteur = auteur;
        this.content = content;
        this.date_publication = date_publication;
        // Note: this method doesn't update the description field
    }
    public void display_info(){
        System.out.println("Post's ID: " + id);
        System.out.println("Post's author: " + auteur);
        System.out.println("Post's content: " + content);
        System.out.println("Post's date: " + date_publication);

    }
}
