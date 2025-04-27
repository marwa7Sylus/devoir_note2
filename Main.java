package Social_media;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("___ DÉMARRAGE DE LA PLATEFORME DE RÉSEAU SOCIAL ___\n");
        Plateforme plateforme = new Plateforme();
        Publication_texte pub1 = new Publication_texte(
                1,
                "Jean Martin",
                "J'ai passé une journée formidable au parc.",
                LocalDate.of(2023, 6, 15), "Ma journée");

        Publication_texte pub2 = new Publication_texte(
                2,
                "Marie Dupont",
                "Ce virus créé en laboratoire est une menace pour l'humanité!",
                LocalDate.of(2023, 7, 1),
                "Alerte sanitaire"
        );

        // Création de publications images
        Publication_Image pub3 = new Publication_Image(
                3,
                "Jean Martin",
                "Beau coucher de soleil",
                LocalDate.of(2023, 7, 5),
                "Photo prise à la plage hier soir"
        );

        Publication_Image pub4 = new Publication_Image(
                4,
                "Sophie Durant",
                "Image qui révèle la manipulation des médias",
                LocalDate.of(2023, 8, 10),
                "La preuve du complot mondial"
        );

        // Création de publications vidéos
        Publication_video pub5 = new Publication_video(
                5,
                "Marc Petit",
                "Mes vacances à la montagne",
                LocalDate.of(2023, 8, 15),
                "Séjour en Suisse",
                LocalDateTime.of(2023, 8, 15, 0, 5, 30) // 5min30s
        );

        Publication_video pub6 = new Publication_video(
                6,
                "Sophie Durant",
                "La vérité sur la puce 5G qui nous contrôle",
                LocalDate.of(2023, 9, 1),
                "Révélations choc",
                LocalDateTime.of(2023, 9, 1, 0, 10, 0) // 10min
        );

        // Ajout des publications à la plateforme
        plateforme.addPublication(pub1);
        plateforme.addPublication(pub2);
        plateforme.addPublication(pub3);
        plateforme.addPublication(pub4);
        plateforme.addPublication(pub5);
        plateforme.addPublication(pub6);


        System.out.println("\n");
        plateforme.afficherToutesLesPublications();

        System.out.println("\n");
        plateforme.afficherPublicationsDouteuses();

        System.out.println("\n");
        plateforme.afficherParAuteur("Jean Martin");

        System.out.println("\n");
        plateforme.afficherParAuteur("Sophie Durant");

        System.out.println("\n");
        plateforme.afficherParAuteur("Utilisateur Inexistant");
    }
}