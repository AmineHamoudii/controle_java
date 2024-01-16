import java.util.Arrays;

abstract class Employe implements Comparable<Employe> {
    private String nom;
    private String matricule;
    private int age;

    public Employe(String nom, String matricule, int age) {
        this.nom = nom;
        this.matricule = matricule;
        this.age = age;
    }

    public abstract double calculerSalaire();

    public abstract void afficherType();

    @Override
    public int compareTo(Employe autreEmploye) {
        return Double.compare(this.calculerSalaire(), autreEmploye.calculerSalaire());
    }

    public String toString() {
        return "Nom: " + nom + ", Matricule: " + matricule + ", Age: " + age + ", Salaire: " + calculerSalaire();
    }
}

class Manager extends Employe {
    private String departement;
    private static final double SALAIRE_DE_BASE = 50000; // Valeur fictive
    private static final double BONUS = 1.2; // Valeur fictive

    public Manager(String nom, String matricule, int age, String departement) {
        super(nom, matricule, age);
        this.departement = departement;
    }

    @Override
    public double calculerSalaire() {
        return SALAIRE_DE_BASE * BONUS;
    }

    public void infoSup() {
        System.out.println("Département du Manager: " + departement);
    }

    @Override
    public void afficherType() {
        System.out.println("Type: Manager");
    }
}

class TravailleurHoraire extends Employe {
    private double tauxHoraire;
    private double heuresTravaillees;

    public TravailleurHoraire(String nom, String matricule, int age, double tauxHoraire, double heuresTravaillees) {
        super(nom, matricule, age);
        this.tauxHoraire = tauxHoraire;
        this.heuresTravaillees = heuresTravaillees;
    }

    @Override
    public double calculerSalaire() {
        return tauxHoraire * heuresTravaillees;
    }

    @Override
    public void afficherType() {
        System.out.println("Type: Travailleur Horaire");
    }
}

public class Main {
    public static void main(String[] args) {
        Employe[] employes = {
                new Manager("John Doe", "M001", 35, "IT"),
                new Manager("Jane Smith", "M002", 40, "HR"),
                new TravailleurHoraire("Alice Johnson", "TH001", 25, 20.0, 40),
                new TravailleurHoraire("Bob Anderson", "TH002", 30, 18.0, 35)
        };

        // Afficher le type et les informations de chaque employé
        for (Employe employe : employes) {
            employe.afficherType();
            System.out.println(employe.toString());
        }

        // Pour les Managers, exécuter la méthode infoSup
        for (Employe employe : employes) {
            if (employe instanceof Manager) {
                ((Manager) employe).infoSup();
            }
        }

        // Trier le tableau des employés par salaire
        Arrays.sort(employes);

        // Afficher le tableau après le tri
        System.out.println("Tableau trié par salaire:");
        for (Employe employe : employes) {
            employe.afficherType();
            System.out.println(employe.toString());
        }

        // Ajouter un nouvel employé
        Employe nouvelEmploye = new TravailleurHoraire("Charlie Brown", "TH003", 22, 15.0, 30);
        employes = Arrays.copyOf(employes, employes.length + 1);
        employes[employes.length - 1] = nouvelEmploye;

        // Supprimer le premier élément dans le tableau
        if (employes.length > 0) {
            employes = Arrays.copyOfRange(employes, 1, employes.length);
        }

        // Copier le tableau dans un nouveau tableau
        Employe[] nouveauTableau = Arrays.copyOf(employes, employes.length);

        // Inverser les éléments du tableau
        for (int i = 0; i < employes.length / 2; i++) {
            Employe temp = employes[i];
            employes[i] = employes[employes.length - 1 - i];
            employes[employes.length - 1 - i] = temp;
        }

        // Afficher le tableau inversé
        System.out.println("Tableau inversé:");
        for (Employe employe : employes) {
            employe.afficherType();
            System.out.println(employe.toString());
        }
    }
}
