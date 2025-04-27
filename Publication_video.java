package Social_media;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Publication_video extends Publication {
    public String title;
    public LocalDateTime duree;

    public Publication_video(int id2, String author2, String con2, LocalDate d, String title2, LocalDateTime duree2) {
        super(id2, author2, con2, d);
        this.title = title2;
        this.duree = duree2;
    }

    @Override
    public boolean is_potentially_Fake(int id) {
        String[] keywords = {"complot", "virus créé", "puce 5G", "fake", "manipulation", "mensonge"};
        String texte = (title + " " + content).toLowerCase();

        for (String key : keywords) {
            if (texte.contains(key.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public void addpublication(int id, String auteur, String content, LocalDate date_publication,String title, LocalDateTime duree2) {
        super.addpublication(id, auteur, content, date_publication);
        this.title = title;
        this.duree = duree2;
    }
    @Override
    public void display_info() {
        super.display_info();
        System.out.println("Titre: " + title);
        System.out.println("Durée: " + duree);
    }
}