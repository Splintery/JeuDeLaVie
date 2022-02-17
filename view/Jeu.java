package view;
import model.Regles; 

public class Jeu {
public static int compteur=0;

public Jeu(){
    Grille test = new Grille(null);

    while(compteur>=10 && compteur<999){ // pour l'instant en attendant d'avoir un vrai bouton play pause
        System.out.println("tour "+compteur); // ca va dire tour 10 mais osef c juste pour le début en attendant le vrai bouton
        //Regles.testRegle();
        compteur++;
    }
}
public static void main(String[] args){
    Jeu test = new Jeu();
    if(compteur==4){
        System.out.println("Yes");
    }
}
}
