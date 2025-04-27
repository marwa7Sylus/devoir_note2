package Social_media;

import java.time.LocalDate;

public class Publication_Image extends Publication {
    public String description;

    public Publication_Image(int id1, String author, String con, LocalDate da, String des) {
        super(id1, author, con, da);
        this.description = des;
    }

    @Override
    public boolean is_potentially_Fake(int id) {
        String[] keywords = {"complot", "virus créé", "puce 5G", "fake", "manipulation", "mensonge"};
        String texte = (description + " " + content).toLowerCase();

        for (String key : keywords) {
            if (texte.contains(key.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
    public void addpublication(int id, String auteur, String content, LocalDate date_publication,String description ) {
        super.addpublication(id, auteur, content, date_publication);
        this.description = description;
    }
    @Override
    public void display_info() {
        super.display_info();
        System.out.println("Description: " + description);
    }
}