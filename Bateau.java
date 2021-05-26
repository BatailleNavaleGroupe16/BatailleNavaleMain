//V3_26/05/2021

import java.util.Scanner;

public class Bateau {
	
	public int taille; //calculée automatiquement
    public int debutH;
    public int debutV;
    public int finH;
    public int finV;
    public int fin;
    public byte numero;
    Scanner sc = new Scanner(System.in);
    
    public Bateau (byte n) {
        this.numero = n;
        this.debutH = 0;
        this.debutV = 0;
        this.fin = 0;
    }
    
    public void placementJoueur (Joueur joueur) { // les infos seront directement rentrées par le joueur
        boolean orientation; 
        for(byte i = 1 ; i<5 ; i++){
            this.numero = i;
            this.calculTaille(i);
            do{
                do{
                    System.out.println("Veuillez saisir la ligne de la première position votre bateau n° : "+i);
                    this.debutH = sc.nextInt();
                }while(debutH < 1 || debutH > 10);//doit être dans la grille
                do{
                    System.out.println("Veuillez saisir la colonne de la première position votre bateau n° : "+i);
                    this.debutV = sc.nextInt();
                }while(debutV < 1 || debutV > 10);//doit être dans la grille
            }while(this.verifPlace1(joueur) == false);
            do{
                do{
                    System.out.println("Veuillez saisir la ligne de la dernière position votre bateau n° : "+i);
                    this.finH = sc.nextInt();
                }while(finH < 1 || finH > 10);//doit être dans la grille
                do{
                    System.out.println("Veuillez saisir la colonne de la dernière position votre bateau n° : "+i);
                    this.finV = sc.nextInt();
                }while(finV < 1 || finV > 10);//doit être dans la grille
            }while(this.verifTaille() == false || this.verifPlace2(joueur) == false);//condition de suffisament de place avec cette orientation et aussi de position du bateau par rapport à sa taille
            this.placer(joueur);//opération finale de placement 
        }
    }
    
    public void placer (Joueur joueur) {
        if (this.estVertical() == true) {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV+i][this.debutH] = this.numero;//ERREUR INCOMPATIBILITE BYTE INT 
            }
        } else {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV][this.debutH+i] = this.numero;
            }
        }
    }
    
    public void placementOrdi (Joueur joueur) { // les infos seront générées aléatoirement
        boolean sens = false; // true : vers haut ou vers droite
        boolean estVertical = false; // true : est vertical
        this.calculTaille(this.numero);
        do{
            this.debutH = (int)(joueur.bateaux.length*Math.random());
            this.debutV = (int)(joueur.bateaux[0].length*Math.random());
            if (this.verifPlace1(joueur) == true) {
                int a = (int)(2*Math.random()); // donne un sens aléatoire
                if (a == 0) {
                    sens = true;
                }
                int b = (int)(2*Math.random()); // donne une orientation aléatoire
                if (b == 0) {
                    estVertical = true;
                }
                if (estVertical == true) {
                    this.finH = this.debutH;
                    if (sens == false) {
                        this.finV = this.debutV - this.taille + 1;
                    } else {
                        this.finV = this.debutV + this.taille - 1;
                    }
                } else {
                    this.finV = this.debutV;
                    if (sens == false) {
                        this.finH = this.debutH + this.taille - 1;
                    } else {
                        this.finH = this.debutH - this.taille + 1;
                    }
                }
            }
        }while(this.verifPlace2(joueur) == false);
        this.placer(joueur);
    }
    
    public boolean verifTaille () {
        boolean tailleOk = false;
        this.ordre();
        if (this.estVertical() == true) {
            if (Math.abs(this.finV - this.debutV + 1) == this.taille) {//faut comparer la valeur absolue
                tailleOk = true;
            }else{
                tailleOk = false;
            }
        } else {
            if (Math.abs(this.finH - this.debutH + 1) == this.taille) {
                tailleOk = true;
            }else{
                tailleOk = false;
            }
        }
        return tailleOk;
    }
    
    public boolean verifPlace1 (Joueur joueur) {
        boolean verification = true;
        if (this.debutH < 0) {
            verification = false;
        }
        if (this.debutV < 0) {
            verification = false;
        }
        if (this.debutV >= joueur.bateaux[0].length) {
            verification = false;
        }
        if (this.debutH >= joueur.bateaux.length) {
            verification = false;
        }
        if (verification == true) {
            if (joueur.bateaux[this.debutV][this.debutH] > 0) {
                verification = false;
            }
        }
        return verification;
    }
    
    public void ordre () {
        if (this.debutH > this.finH) {
            int var = this.finH;
            this.finH = this.debutH;
            this.debutH = var;
        }
        if (this.debutV > this.finV) {
            int var = this.finV;
            this.finV = this.debutV;
            this.debutV = var;
        }
    }
    
    public int calculTaille(int numero){// calcul de la taille du bateau en fonction de son numéro
        if(numero == 1){
            this.taille = 2;
        }else if(numero == 2 || numero == 3){
            this.taille = 3;
        }else if(numero == 4){
            this.taille = 4;
        }else if(numero == 5){
            this.taille = 5;
        }else{
            this.taille = 0;
        }
        return this.taille;
    }
    
    public boolean estVertical(){
        if (this.debutH == this.finH){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verifPlace2 (Joueur joueur) {
        boolean verification = true;
        if (this.finH < 0) {
            verification = false;
        }
        if (this.finV < 0) {
            verification = false;
        }
        if (this.finH >= joueur.bateaux.length) {
            verification = false;
        }
        if (this.finV >= joueur.bateaux[0].length) {
            verification = false;
        }
        if (this.debutH == finH && debutV == finV) {
            verification = false;
        }
        if (this.debutH != finH && debutV != finV) {
            verification = false;
        }
        if (verification == true) {
            verification = this.verifTaille();
        }
        if (verification == true) {
            verification = this.verifPlace3(joueur);
        }
        return verification;
    }
    
    public boolean verifPlace3 (Joueur joueur) {
        boolean verification = true;
        this.ordre();
        if (this.debutH == this.finH) {
            for (int i = 0; i < this.taille; i++) {
                if (joueur.bateaux[this.debutV+i][this.debutH] != 0) {
                    verification = false;
                }
            }
        } else {
            for (int i = 0; i < this.taille; i++) {
                if (joueur.bateaux[this.debutV][this.debutH+i] != 0) {
                    verification = false;
                }
            }
        }
        return verification;
    }
    
    public boolean sens(){
        if (this.debutH <= this.finH && this.debutV <= this.finV){
            return true; // true : vers bas ou vers droite
        }else{
            return false;
        }
    }
}
