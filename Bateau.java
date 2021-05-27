//V3_26/05/2021

import java.util.Scanner;

public class Bateau {
	
    public byte taille; //calculée automatiquement
    public byte debutH;
    public byte debutV;
    public byte finH;
    public byte finV;
    public byte fin;
    public byte numero;
    public byte estVertical; //1 pour vertical 2 pour horizontal
    Scanner sc = new Scanner(System.in);
    
    public Bateau (byte n) {
        this.numero = n;
        this.debutH = 0;
        this.debutV = 0;
        this.fin = 0;
    }
    
    public void placementJoueur (Joueur joueur) { // les infos seront directement rentrées par le joueur
        boolean orientation; 
        
            this.numero = i;
            this.calculTaille(i);
            do{
                do{
                    System.out.println("Veuillez saisir la ligne de la position la plus à droite et la plus en haut de votre bateau n° : "+i);
                    this.debutH = sc.nextByte();
                }while(debutH <= 1 || debutH >= 10);//doit être dans la grille
                
                do{
                    System.out.println("Veuillez saisir la colonne de la première la plus à droite et la plus en haut de votre bateau n° : "+i);
                    this.debutV = sc.nextByte();
                }while(debutV <= 1 || debutV >= 10);//doit être dans la grille
                
                do{
                    System.out.println("Veuillez saisir l'orientation de votre bateau : VERTICAL : 1 / HORIZONTAL : 2 ");
                    this.estVertical = sc.nextByte();
                }while(this.estVertical != 1 || this.estVertical != 2);
            }while(this.verifPlace3(joueur));
            
            this.placer(joueur);
    }
    
    public void placer (Joueur joueur) {
        if (this.estVertical() == true) {
            for (byte i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV+i][this.debutH] = this.numero;//ERREUR INCOMPATIBILITE BYTE INT 
            }
        } else {
            for (byte i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV][this.debutH+i] = this.numero;
            }
        }
    }
    
    public void placementOrdi (Joueur joueur) { // les infos seront générées aléatoirement
        boolean sens = false; // true : vers haut ou vers droite
        boolean estVertical = false; // true : est vertical
        this.calculTaille(this.numero);
        do{
            this.debutH = (byte)(joueur.bateaux.length*Math.random());
            this.debutV = (byte)(joueur.bateaux[0].length*Math.random());
            if (this.verifPlace1(joueur) == true) {
                byte a = (byte)(2*Math.random()); // donne un sens aléatoire
                if (a == 0) {
                    sens = true;
                }
                byte b = (byte)(2*Math.random()); // donne une orientation aléatoire
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
            byte var = this.finH;
            this.finH = this.debutH;
            this.debutH = var;
        }
        if (this.debutV > this.finV) {
            byte var = this.finV;
            this.finV = this.debutV;
            this.debutV = var;
        }
    }
    
    public byte calculTaille(byte numero){// calcul de la taille du bateau en fonction de son numéro
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
   
    
    public boolean sens(){
        if (this.debutH <= this.finH && this.debutV <= this.finV){
            return true; // true : vers bas ou vers droite
        }else{
            return false;
        }
    }
	public boolean verifPlace3(Joueur joueur){//méthode utilisée pour le placement joueur pas la même que verifPlace 3 avant car j'ai modifié
        byte i = 0;
        if(estVertical == 1){
            while(i < calculTaille(this.numero) && this.debutH - i < 0){//positionnement entre les bornes de la taille du bateau, vérif si bien dans le tableau de la grille
                if(joueur.bateaux[this.debutH-i][this.debutV] == 0){
                    return false;
                }
                i++;
            }
            return true;
        }else{
            while(i < calculTaille(this.numero) && this.debutH - i < 0){
                if(joueur.bateaux[this.debutH][this.debutV-i] == 0){
                    return false;
                }
                i++;
            }
            return true;
        }
    }
}
