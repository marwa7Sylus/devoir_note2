package Social_media;

import java.time.LocalDate;

public class Publication_texte extends Publication {
    public String title;

    public Publication_texte(int id3, String author3, String con3, LocalDate da3, String title3) {
        super(id3, author3, con3, da3);
        this.title = title3;
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
    public void addpublication(int id, String auteur, String content, LocalDate date_publication,String title) {
        super.addpublication(id, auteur, content, date_publication);
        this.title = title;
    }
    @Override
    public void display_info() {
        super.display_info();
        System.out.println("Titre: " + title);
    }
}