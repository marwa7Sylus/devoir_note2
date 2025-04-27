package Social_media;

import java.util.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Plateforme {
    public ArrayList<Publication> publications = new ArrayList<>();
    public Map<String, ArrayList<Publication>> pub_par_auteur;

    public Plateforme() {
        pub_par_auteur = new HashMap<>();
    }

    public void addPublication(Publication publication) {
        publications.add(publication);

        // Regroupement par auteur
        String auteur = publication.getAuteur();
        if (!pub_par_auteur.containsKey(auteur)) {
            pub_par_auteur.put(auteur, new ArrayList<>());
        }
        pub_par_auteur.get(auteur).add(publication);
    }

    public void afficherToutesLesPublications() {
        System.out.println("=== TOUTES LES PUBLICATIONS ===");
        if (publications.isEmpty()) {
            System.out.println("Aucune publication disponible.");
        } else {
            for (Publication pub : publications) {
                pub.display_info();
                System.out.println("-----------------------------");
            }
        }
    }

    public void afficherPublicationsDouteuses() {
        System.out.println("=== PUBLICATIONS POTENTIELLEMENT FAKE ===");
        boolean aucuneFake = true;

        for (Publication pub : publications) {
            if (pub.is_potentially_Fake(pub.getId())) {
                pub.display_info();
                System.out.println("-----------------------------");
                aucuneFake = false;
            }
        }

        if (aucuneFake) {
            System.out.println("Aucune publication douteuse détectée.");
        }
    }

    public void afficherParAuteur(String auteur) {
        System.out.println("=== PUBLICATIONS DE " + auteur.toUpperCase() + " ===");

        if (pub_par_auteur.containsKey(auteur)) {
            for (Publication pub : pub_par_auteur.get(auteur)) {
                pub.display_info();
                System.out.println("-----------------------------");
            }
        } else {
            System.out.println("Aucune publication trouvée pour cet auteur.");
        }
    }

    // Méthodes déplacées depuis EXEMPLE.java
    public String getFormattedAllPublications() {
        StringBuilder output = new StringBuilder();
        output.append("____ TOUTES LES PUBLICATIONS ___\n\n");

        if (publications.isEmpty()) {
            output.append("Aucune publication disponible.\n");
        } else {
            for (Publication pub : publications) {
                formatPublicationInfo(output, pub);
            }
        }

        return output.toString();
    }

    public String getFormattedFakePublications() {
        StringBuilder output = new StringBuilder();
        output.append("=== PUBLICATIONS POTENTIELLEMENT FAKE ===\n\n");
        boolean aucuneFake = true;

        for (Publication pub : publications) {
            if (pub.is_potentially_Fake(pub.getId())) {
                formatPublicationInfo(output, pub);
                aucuneFake = false;
            }
        }

        if (aucuneFake) {
            output.append("Aucune publication douteuse détectée.\n");
        }

        return output.toString();
    }

    public String getFormattedPublicationsByAuthor(String author) {
        StringBuilder output = new StringBuilder();
        output.append("___ PUBLICATIONS DE ").append(author.toUpperCase()).append(" ___\n\n");

        if (pub_par_auteur.containsKey(author)) {
            for (Publication pub : pub_par_auteur.get(author)) {
                formatPublicationInfo(output, pub);
            }
        } else {
            output.append("Aucune publication trouvée pour cet auteur.\n");
        }

        return output.toString();
    }

    public String getFormattedPublicationsByType(String type) {
        StringBuilder output = new StringBuilder();
        output.append("___ PUBLICATIONS DE TYPE ").append(type.toUpperCase()).append(" ___\n\n");

        boolean found = false;
        for (Publication pub : publications) {
            boolean isMatchType = false;

            if (type.equalsIgnoreCase("texte") && pub instanceof Publication_texte) {
                isMatchType = true;
            } else if (type.equalsIgnoreCase("image") && pub instanceof Publication_Image) {
                isMatchType = true;
            } else if (type.equalsIgnoreCase("video") && pub instanceof Publication_video) {
                isMatchType = true;
            }

            if (isMatchType) {
                formatPublicationInfo(output, pub);
                found = true;
            }
        }

        if (!found) {
            output.append("Aucune publication de type ").append(type).append(" trouvée.\n");
        }

        return output.toString();
    }

    public String[] getAuthorsList() {
        ArrayList<String> authors = new ArrayList<>();
        authors.add("");
        if (pub_par_auteur != null) {
            for (String author : pub_par_auteur.keySet()) {
                authors.add(author);
            }
        }

        return authors.toArray(new String[0]);
    }

    private void formatPublicationInfo(StringBuilder output, Publication pub) {
        output.append("ID: ").append(pub.getId()).append("\n");
        output.append("Auteur: ").append(pub.getAuteur()).append("\n");
        output.append("Contenu: ").append(pub.getContent()).append("\n");
        output.append("Date: ").append(pub.getDate_publication().format(DateTimeFormatter.ISO_LOCAL_DATE)).append("\n");

        if (pub instanceof Publication_texte) {
            output.append("Type: Texte\n");
            output.append("Titre: ").append(((Publication_texte) pub).title).append("\n");
        } else if (pub instanceof Publication_Image) {
            output.append("Type: Image\n");
            output.append("Description: ").append(((Publication_Image) pub).description).append("\n");
        } else if (pub instanceof Publication_video) {
            output.append("Type: Vidéo\n");
            output.append("Titre: ").append(((Publication_video) pub).title).append("\n");
            output.append("Durée: ").append(((Publication_video) pub).duree.toLocalTime()).append("\n");
        }

        if (pub.is_potentially_Fake(pub.getId())) {
            output.append("⚠️ POTENTIELLEMENT FAKE ⚠️\n");
        }

        output.append("-----------------------------\n\n");
    }

    // Méthode pour générer un nouvel ID
    public int getNewId() {
        int maxId = 0;
        for (Publication pub : publications) {
            if (pub.getId() > maxId) {
                maxId = pub.getId();
            }
        }
        return maxId + 1;
    }
}