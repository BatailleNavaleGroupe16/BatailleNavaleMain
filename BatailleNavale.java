import java.util.Scanner;

public class BatailleNavale {
    
    public static void main(String[] args){
        
        Scanner sc = new Scanner(System.in);
        
        /*Scanner sc = new Scanner(System.in);
        System.out.println("Combien de joueurs ?");
        int a = sc.nextInt();*/
        
        
        //Initialisation : choix du mode jeu : JVJ, JVO ; placement bateaux
        
        byte typeJeu = 0;
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
        
        byte ligne;
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
