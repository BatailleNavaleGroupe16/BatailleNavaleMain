//V2_16/05/2021 Antonin

import java.util.Scanner;

public class Bateau {
	
	public int taille; //calculée automatiquement
    public int debutH;
    public int debutV;
    public int finH;
    public int finV;
    public int fin;
    public int numero;
    /*public boolean estVertical;
    public boolean versLeHaut;
    public boolean versLaDroite;
    public boolean zoneLegale;
    public boolean tailleOk;
    public boolean placeLibre;
    public boolean estPlace;*/
    Scanner sc = new Scanner(System.in);
    
    public Bateau (int n) {
        this.numero = n;
        this.debutH = 0;
        this.debutV = 0;
        this.fin = 0;
        /*this.estVertical = false;
        this.zoneLegale = false;
        this.tailleOk = false;
        this.placeLibre = false;
        this.estPlace = false;*/
    }
    
    public void placement (Joueur joueur /*, int ligne, int colonne, int z, boolean orientation*/) { // les infos seront directement rentrées par le joueur
        
        
        
        boolean orientation; 
        
        for(int i = 1 ; i<5 ; i++){
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
                    this.debutH = sc.nextInt();
                }while(finH < 1 || finH > 10);//doit être dans la grille
                
                do{
                    System.out.println("Veuillez saisir la colonne de la dernière position votre bateau n° : "+i);
                    this.debutV = sc.nextInt();
                }while(finV < 1 || finV > 10);//doit être dans la grille
            }while(this.verifTaille() == false || this.verifPlace2(joueur) == false);//condition de suffisament de place avec cette orientation et aussi de position du bateau par rapport à sa taille
            
            this.placer(joueur);//opération finale de placement 
            
            
            /*this.ordre();
            this.verifZone(joueur);
            this.verifTaille();
            if (this.tailleOk && this.zoneLegale == true) {
                this.verifPlace(joueur);
                if (this.placeLibre == true) {
                    this.placer(joueur);
                }
            }*/
            
        }
        
    }
	public void placementOrdi (Joueur joueur) { // les infos seront générées aléatoirement
        boolean sens = false; // true : vers haut ou vers droite
        boolean estVertical = false; // true : est vertical
        for(byte i = 1 ; i<=5 ; i++){
            this.numero = i;
            this.calculTaille(i);
            do{
                this.debutH = (int)(joueur.bateaux.length*Math.random());
                this.debutV = (int)(joueur.bateaux[0].length*Math.random());
                if (this.verifPlace1(joueur) == true) {
                    int a = (int)(2*Math.random()); // donne 0 ou 1 aléatoirement
                    if (a == 0) {
                        sens = true;
                    }
                    int b = (int)(2*Math.random()); // donne 0 ou 1 aléatoirement
                    if (b == 0) {
                        estVertical = true;
                    }
                    if (estVertical == false) {
                        this.finH = this.debutH;
                        if (sens == true) {
                            this.finV = this.debutV + taille - 1;
                        } else {
                            this.finV = this.debutV - taille + 1;
                        }
                    } else {
                        this.finV = this.debutV;
                        if (sens == true) {
                            this.finH = this.debutH - taille + 1;
                        } else {
                            this.finH = this.debutH + taille - 1;
                        }
                    }
                }
            }while(this.verifPlace2(joueur) == false);
            this.placer(joueur);
        }
    
    
    public void placer (Joueur joueur) {
        if (this.estVertical() == false) {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV][this.debutH+i] = this.numero;//ERREUR INCOMPATIBILITE BYTE INT 
            }
        } else {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV+i][this.debutH] = this.numero;
            }
        }
        //this.estPlace = true;
    }
    
    public void verifZone (Joueur joueur) {
        boolean verification = true;
        if (this.debutV < 0) {
            verification = false;
        }
        if (this.debutV >= joueur.bateaux.length) {
            verification = false;
        }
        if (this.debutH < 0) {
            verification = false;
        }
        if (this.debutH >= joueur.bateaux[0].length) {
            verification = false;
        }
        if (this.fin < 0) {
            verification = false;
        }
        if (this.estVertical() == true) {
            if (this.fin >= joueur.bateaux.length) {
                verification = false;
            } 
        } else {
            if (this.fin >= joueur.bateaux[0].length) {
                verification = false;
            }
        }
        //this.zoneLegale = verification;
    }
    
    public boolean verifTaille () {
        boolean tailleOk = false;
        if (this.estVertical() == true) {
            if (Math.abs(this.fin - this.debutV + 1) == this.calculTaille(this.numero)) {//faut comparer la valeur absolue
                tailleOk = true;
            }else{
                tailleOk = false;
            }
        } else {
            if (Math.abs(this.fin - this.debutH + 1) == this.calculTaille(this.numero)) {
                tailleOk = true;
            }else{
                tailleOk = false;
            }
        }
        
        return tailleOk;
    }
    
    public boolean verifPlace1 (Joueur joueur) {
        
        boolean verification = true;
        
        for (int i = 0; i < this.taille; i++) {
            if (verification == true && joueur.bateaux[this.debutV][this.debutH+i] == 0){//vérifie si il a les antécédents qui sont vides et que la case est elle même vide
                verification = true;
            }else{
                verification = false;
            }
        }
        if(verification == true){
            return verification;
        }
        verification = true;
        for (int i = 0; i < this.taille; i++) {
            if (verification == true && joueur.bateaux[this.debutV][this.debutH-i] == 0){
                verification = true;
            }else{
                verification = false;
            }
        }
        if(verification == true){
            return verification;
        }
        
        verification = true;
        for (int i = 0; i < this.taille; i++) {
            if (verification == true &&joueur.bateaux[this.debutV+i][this.debutH] == 0){
                verification = true;
            }else{
                verification = false;
            }
        }
        if(verification == true){
            return verification;
        }
        verification = true;
        for (int i = 0; i < this.taille; i++) {
            if (verification == true && joueur.bateaux[this.debutV-i][this.debutH] == 0){
                verification = true;
            }else{
                verification = false;
            }
        }
        if(verification == true){
            return verification;
        }
        
        return false; //si on arrive à la fin de la méthode c'est qu'aucun des côtés ne marche
    }
    
    public void ordre () {
        if (this.estVertical() == false) {
            if (this.debutH > this.fin) {
                int var = this.fin;
                this.fin = this.debutH;
                this.debutH = var;
            }
        } else {
            if (this.debutV > this.fin) {
                int var = this.fin;
                this.fin = this.debutV;
                this.debutV = var;
            }
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
    }
    
    public boolean estVertical(){
        if (this.debutV == this.finV){
            return true;
        }else{
            return false;
        }
    }
    
    public boolean verifPlace2 (Joueur joueur) {
        boolean verification = true;
        if (this.estVertical() == false) {//première disjonction de cas
            
            if(this.sens() == true){//deuxième disfonction de cas vers la droite
                for (int i = 0; i < this.taille; i++) {
                    if (verification == true && joueur.bateaux[this.debutV][this.debutH+i] == 0) {//vérification de si les antécédents sont bons et le suivant aussi
                        verification = true;
                    }else{
                        verification = false;
                    }
                }
                if (verification == true){
                    return verification;
                }
            }else{//vers la gauche
                for (int i = 0; i < this.taille; i++) {
                    if (verification == true && joueur.bateaux[this.debutV][this.debutH-i] == 0) {
                        verification = true;
                    }else{
                        verification = false;
                    }
                }
                if (verification == true){
                    return verification;
                }
            }
            
        } else {// si le bateau est vertical
             if(this.sens() == true){//deuxième disfonction de cas vers le haut
                for (int i = 0; i < this.taille; i++) {
                    if (verification == true && joueur.bateaux[this.debutV+i][this.debutH] == 0) {
                        verification = true;
                    }else{
                        verification = false;
                    }
                }
                if (verification == true){
                    return verification;
                }
            }else{//vers le bas
                for (int i = 0; i < this.taille; i++) {
                    if (verification == true && joueur.bateaux[this.debutV-i][this.debutH] == 0) {
                        verification = true;
                    }else{
                        verification = false;
                    }
                }
                if (verification == true){
                    return verification;
                }
            }
        }
        return false; // si tous les tests précendents ont échoué alors c'est forcèment qu'il n'y a pas la place
    }
    
    public boolean sens(){
        if (this.debutH <= this.finH && this.debutV <= this.finV){
            return true; // true : vers haut ou vers droite
        }else{
            return false;
        }
    }
}


//V1_07/05/2021
public class Bateau {
	
	public int taille;
    public int debutH;
    public int debutV;
    public int fin;
    public int numero;
    public boolean estVertical;
    public boolean zoneLegale;
    public boolean tailleOk;
    public boolean placeLibre;
    public boolean estPlace;
    
    public Bateau (int n, int t) {
        this.taille = t;
        this.numero = n;
        this.debutH = 0;
        this.debutV = 0;
        this.fin = 0;
        this.estVertical = false;
        this.zoneLegale = false;
        this.tailleOk = false;
        this.placeLibre = false;
        this.estPlace = false;
    }
    
    public void placement (Joueur joueur, int ligne, int collone, int z, boolean orientation) {
        this.debutH = ligne;
        this.debutV = collone;
        this.fin = z;
        this.estVertical = orientation;
        this.ordre();
        this.verifZone(joueur);
        this.verifTaille();
        if (this.tailleOk && this.zoneLegale == true) {
            this.verifPlace(joueur);
            if (this.placeLibre == true) {
                this.placer(joueur);
            }
        }
    }
    
    public void placer (Joueur joueur) {
        if (estVertical == false) {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV][this.debutH+i] = this.numero;
            }
        } else {
            for (int i = 0; i < this.taille; i++) {
                joueur.bateaux[this.debutV+i][this.debutH] = this.numero;
            }
        }
        this.estPlace = true;
    }
    
    public void verifZone (Joueur joueur) {
        boolean verification = true;
        if (this.debutV < 0) {
            verification = false;
        }
        if (this.debutV >= joueur.bateaux.length) {
            verification = false;
        }
        if (this.debutH < 0) {
            verification = false;
        }
        if (this.debutH >= joueur.bateaux[0].length) {
            verification = false;
        }
        if (this.fin < 0) {
            verification = false;
        }
        if (this.estVertical == true) {
            if (this.fin >= joueur.bateaux.length) {
                verification = false;
            } 
        } else {
            if (this.fin >= joueur.bateaux[0].length) {
                verification = false;
            }
        }
        this.zoneLegale = verification;
    }
    
    public void verifTaille () {
        if (this.estVertical == true) {
            if (this.fin - this.debutV + 1 == this.taille) {
                this.tailleOk = true;
            }
        } else {
            if (this.fin - this.debutH + 1 == this.taille) {
                this.tailleOk = true;
            }
        }
    }
    
    public void verifPlace (Joueur joueur) {
        boolean verification = true;
        if (estVertical == false) {
            for (int i = 0; i < this.taille; i++) {
                if (joueur.bateaux[this.debutV][this.debutH+i] != 0) {
                    verification = false;
                }
            }
        } else {
            for (int i = 0; i < this.taille; i++) {
                if (joueur.bateaux[this.debutV+i][this.debutH] != 0) {
                    verification = false;
                }
            }
        }
        this.placeLibre = verification;
    }
    
    public void ordre () {
        if (this.estVertical == false) {
            if (this.debutH > this.fin) {
                int var = this.fin;
                this.fin = this.debutH;
                this.debutH = var;
            }
        } else {
            if (this.debutV > this.fin) {
                int var = this.fin;
                this.fin = this.debutV;
                this.debutV = var;
            }
        }
    }
    
    
}

