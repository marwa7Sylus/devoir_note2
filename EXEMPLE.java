package Social_media;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class EXEMPLE extends JFrame implements ActionListener {
    private JPanel searchPanel, contentPanel, buttonPanel, createPanel;
    private JLabel typeLabel;
    private JComboBox<String> typeComboBox;
    private JButton searchTypeButton, showAllButton, showFakeButton, createButton;
    private JTextArea displayArea;
    private JScrollPane scrollPane;
    private JTextField titreField, auteurField, contenuField;
    private JLabel titreLabel, auteurLabel, contenuLabel, typeCreateLabel;
    private JComboBox<String> typeCreateComboBox;
    private JButton submitButton;
    private Plateforme plateforme;

    public EXEMPLE() {
        this.setTitle("Plateforme Social Media");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(900, 700);

        initializeData();

        searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        typeLabel = new JLabel("Type de publication:");
        String[] types = {"", "Texte", "Image", "Video"};
        typeComboBox = new JComboBox<>(types);
        searchTypeButton = new JButton("CHERCHER TYPE");

        showAllButton = new JButton("AFFICHER TOUT");
        showFakeButton = new JButton("PUBLICATIONS DOUTEUSES");
        searchPanel.add(typeLabel);
        searchPanel.add(typeComboBox);
        searchPanel.add(searchTypeButton);
        searchPanel.add(showAllButton);
        searchPanel.add(showFakeButton);
        createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(5, 2, 5, 5));
        createPanel.setBorder(BorderFactory.createTitledBorder("Créer une nouvelle publication"));

        auteurLabel = new JLabel("Auteur:");
        auteurField = new JTextField(20);
        contenuLabel = new JLabel("Contenu:");
        contenuField = new JTextField(20);
        titreLabel = new JLabel("Titre/Description:");
        titreField = new JTextField(20);
        typeCreateLabel = new JLabel("Type:");
        typeCreateComboBox = new JComboBox<>(new String[]{"Texte", "Image", "Video"});
        submitButton = new JButton("PUBLIER");

        createPanel.add(auteurLabel);
        createPanel.add(auteurField);
        createPanel.add(contenuLabel);
        createPanel.add(contenuField);
        createPanel.add(titreLabel);
        createPanel.add(titreField);
        createPanel.add(typeCreateLabel);
        createPanel.add(typeCreateComboBox);
        createPanel.add(new JLabel()); // Espace vide
        createPanel.add(submitButton);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        displayArea = new JTextArea(30, 60);
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        scrollPane = new JScrollPane(displayArea);

        contentPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(searchPanel, BorderLayout.NORTH);
        northPanel.add(createPanel, BorderLayout.CENTER);

        this.add(northPanel, BorderLayout.NORTH);
        this.add(contentPanel, BorderLayout.CENTER);
        searchTypeButton.addActionListener(this);
        showAllButton.addActionListener(this);
        showFakeButton.addActionListener(this);
        submitButton.addActionListener(this);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initializeData() {
        plateforme = new Plateforme();
        Publication_texte pub1 = new Publication_texte(1, "Jean Martin", "J'ai passé une journée formidable au parc.", LocalDate.of(2023, 6, 15), "Ma journée");
        Publication_Image pub2 = new Publication_Image(2, "Jean Martin", "Beau coucher de soleil", LocalDate.of(2023, 7, 5), "Photo prise à la plage hier soir");
        Publication_Image pub3 = new Publication_Image(3, "Sophie Durant", "Image qui révèle la manipulation des médias", LocalDate.of(2023, 8, 10), "La preuve du complot mondial");
        Publication_video pub4 = new Publication_video(4,"Sophie Durant", "La vérité sur la puce 5G qui nous contrôle", LocalDate.of(2023, 9, 1), "Révélations choc", LocalDateTime.of(2023, 9, 1, 0, 10, 0));
        plateforme.addPublication(pub1);
        plateforme.addPublication(pub2);
        plateforme.addPublication(pub3);
        plateforme.addPublication(pub4);
    }

    private void displayAllPublications() {
        displayArea.setText(plateforme.getFormattedAllPublications());
        displayArea.setCaretPosition(0);
    }

    private void displayFakePublications() {
        displayArea.setText(plateforme.getFormattedFakePublications());
        displayArea.setCaretPosition(0);
    }

    private void displayPublicationsByType(String type) {
        displayArea.setText(plateforme.getFormattedPublicationsByType(type));
        displayArea.setCaretPosition(0);
    }

    private void createNewPublication() {
        String auteur = auteurField.getText().trim();
        String contenu = contenuField.getText().trim();
        String titreOuDescription = titreField.getText().trim();
        String type = (String) typeCreateComboBox.getSelectedItem();

        if (auteur.isEmpty() || contenu.isEmpty() || titreOuDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs obligatoires", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newId = plateforme.getNewId();
        LocalDate datePublication = LocalDate.now();

        switch (type) {
            case "Texte":
                Publication_texte pubTexte = new Publication_texte(newId, auteur, contenu, datePublication, titreOuDescription);
                plateforme.addPublication(pubTexte);
                break;
            case "Image":
                Publication_Image pubImage = new Publication_Image(newId, auteur, contenu, datePublication, titreOuDescription);
                plateforme.addPublication(pubImage);
                break;
            case "Video":
                // Utilisation d'une durée par défaut de 10 minutes
                LocalDateTime duree = LocalDateTime.of(datePublication.getYear(),
                        datePublication.getMonthValue(),
                        datePublication.getDayOfMonth(),
                        0, 10, 0);
                Publication_video pubVideo = new Publication_video(newId, auteur, contenu, datePublication, titreOuDescription, duree);
                plateforme.addPublication(pubVideo);
                break;
        }

        auteurField.setText("");
        contenuField.setText("");
        titreField.setText("");

        JOptionPane.showMessageDialog(this, "Publication créée avec succès", "Information", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchTypeButton) {
            String selectedType = (String) typeComboBox.getSelectedItem();
            if (selectedType != null && !selectedType.isEmpty()) {
                displayPublicationsByType(selectedType);
            }
        } else if (e.getSource() == showAllButton) {
            displayAllPublications();
        } else if (e.getSource() == showFakeButton) {
            displayFakePublications();
        } else if (e.getSource() == submitButton) {
            createNewPublication();
        }
    }
}