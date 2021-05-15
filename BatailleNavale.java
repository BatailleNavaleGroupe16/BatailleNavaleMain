//Version 15/05/21
import java.util.Scanner;

public class BatailleNavale {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        
        //Initialisation : choix du mode jeu : JVJ, JVO
        
        byte typeJeu = 0;
        byte typeMax = 3;
        
        do{
            System.out.println("Recherche des bateaux de l'ordinateur : 1 - Joueur contre Joueur : 2 - Joueur contre Ordinateur : 3");
            System.out.print("Votre choix : ");
            typeJeu = sc.nextByte();
        }while ((typeJeu <=0) || (typeJeu > typeMax));
        
        
        //Création des joueurs
        
        Joueur j1;
        Joueur j2;
        
        Joueur[] tabJ = new Joueur[2];
        
        /*
        si 1 => j1(2) j2(3)
        si 2 => j1(1) j2(1)
        si 3 => j1(1) j2(4)
        */
        
        if (typeJeu == 1){
            j1 = new Joueur((byte)2);
            j2 = new Joueur((byte)3);
        }else if(typeJeu == 2){
            j1 = new Joueur((byte)1);
            j2 = new Joueur((byte)1);
        }else{
            j1 = new Joueur((byte)1);
            j2 = new Joueur((byte)4);
        }
        
        tabJ[0] = j1;
        tabJ[1] = j2;
        
        
        
        
        /*boucle du jeu : Affichage des coups joués, nb bateaux restants à adversaire.
        choix case à attaquer : check si déjà attaquée ou pas
        */
        
        byte actuel = 1; // pour commencer par joueur 1 dans boucle
        byte autre = (byte)((actuel + 1)%2);
        boolean enCours = true;
        
        while (enCours){
            
            actuel = (byte)((actuel + 1)%2);
            autre = (byte)((actuel + 1)%2);
            
            System.out.println("\n\n\n\n\n"+"Joueur " + (actuel+1));
            
            //Affichage bateau
            tabJ[actuel].afficheCoups(tabJ[autre].nbBateaux);
            
            //Affichage nb bateaux restants
            tabJ[actuel].Attaque(tabJ[autre]);
            
            
            System.out.println("Tour joué");
            
            enCours = tabJ[autre].fini();
            
            
        }
        
        System.out.println("Le joueur " + (actuel+1) + " a gagné cette partie");
        
        
        
        
    }
    
    
    
}







//Version 8/05/21
/*import java.util.Scanner;

public class BatailleNavale {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        
        //Initialisation : choix du mode jeu : JVJ, JVO ; placement bateaux
        
        byte typeJeu = 0;
        byte typeMax = 2;
        
        do{
            System.out.println("Joueur contre Ordinateur : 1 - Joueur contre Joueur : 2");
            System.out.print("Votre choix : ");
            typeJeu = sc.nextByte();
        }while ((typeJeu <=0) || (typeJeu > typeMax));
        
        
        //Création des joueurs
        
        Joueur[] tabJ = new Joueur[2];
        Joueur j1 = new Joueur();
        tabJ[0] = j1;
        
        Joueur j2;
        if (typeJeu == 1){
            j2 = new Joueur(false);
        }else{ //remplacer par le type de jeu jvj si introduction jeu ordi
            j2 = new Joueur();
        }
        
        tabJ[1] = j2;
        
        
        
        /*boucle du jeu : Affichage des coups joués, nb bateaux restants à adversaire.
        choix case à attaquer : check si déjà attaquée ou pas
        */
        
 /*       byte actuel = 1; // pour commencer par joueur 1 dans boucle
        
        if (typeJeu == 1){
            actuel = 0; //comme ça pas besoin de changer de joueur1 dans la suite
        }
        
        byte autre = (byte)((actuel + 1)%2);
        boolean enCours = true;
        
        while (enCours){
            
            if (typeJeu != 1){
                actuel = (byte)((actuel + 1)%2);
                autre = (byte)((actuel + 1)%2);
                System.out.print( actuel + autre);
            }
            
            System.out.println("\n\n\n\n\n"+"Joueur " + (actuel+1));
            
            //Affichage bateau
            tabJ[actuel].afficheCoups();
            
            //Affichage nb bateaux restants
            System.out.println("\n" + "Il reste " + tabJ[autre].nbBateaux + " bateaux à trouver");
            tabJ[actuel].Attaque(tabJ[autre]);
            
            enCours = tabJ[autre].fini();
            
            
        }
        
        System.out.println("Le joueur " + (actuel+1) + " a gagné cette partie");
        
        
        
        
    }
    
    
    
}*/






//Version avant 8/05/21
/*import java.util.Scanner;

public class BatailleNavale {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Combien de joueurs ?");
        int a = sc.nextInt();*/
        
        
        //Initialisation : choix du mode jeu : JVJ, JVO ; placement bateaux
        
/*        byte typeJeu = 0;
        byte typeMax = 2;
        do{
            System.out.println("Joueur contre Joueur : 1 - Joueur contre Ordinateur : 2");
            System.out.print("Votre choix : ");
            typeJeu = sc.nextByte();
        }while ((typeJeu <=0) || (typeJeu > typeMax));
        
        //Création des joueurs
        
        Joueur[] tabJ = new Joueur[typeJeu];
        Joueur j1 = new Joueur();
        tabJ[0] = j1;
        //creation bateaux
        
        if (typeJeu==1){
            Joueur j2 = new Joueur();
            tabJ[1] = j2;
            //creation bateaux
        }
        
        
        byte a;// pour faire pausse dans le while dessous
        
        /*boucle du jeu : Affichage des coups joués, nb bateaux restants à adversaire.
        choix case à attaquer : check si déjà attaquée ou pas
        */
        
  /*      byte ligne;
        byte colonne;
        
        while (true){
            //Affichage bateau
            tabJ[0].afficheGrille(j1.coupsPrecedents);
            //Affichage nb bateaux restants
            
            a = sc.nextByte();
            
            do{
                System.out.print("Choisissez la ligne à attaquer ");
                ligne = sc.nextByte();
            }while( (ligne<1) || (ligne>tabJ[0].grille.length) );
            ligne-=1;
            
            do{
                System.out.print("Choisissez la colonne à attaquer ");
                colonne = sc.nextByte();
            }while( (ligne<1) || (ligne>tabJ[0].grille[0].length) );
            colonne -=1;
            
            
            /*Choix case :  - si déjà attaquée, redemander case
                            - sinon modif case puis dire si touché ou pas
            Modif tableau coup joués
            Modif nombre bateau si nécessaire
            
            
            
            */
        }
        
        
        
        
        
    }
    
    
    /**
     * Méthode pour affichage de la grille
     * Prend la grille en paramètre
     * Ne retourne rien
     */
    /*public static void AffichageGrille(char[][] coups){
        System.out.print("     ");
        for(byte i = 0; i < coups[0].length ; i+=1){
            System.out.print(i+1 + "  ");
        }
        System.out.println("\n");
        for(byte i = 0; i < coups.length ; i+=1){
            if (i+1<10){
                System.out.print(" ");
            }
            System.out.print(i+1 + "   ");
            
            for(byte j = 0; j < coups[i].length ; j+=1){
                System.out.print(coups[i][j] + "  ");
            }
            System.out.println();
        }
    }*/
    
    
    
    
    
    
    
    
}
