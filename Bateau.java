/*
 * Bateau.java
 * 
 * Copyright 2021 rapha <rapha@LAPTOP-04CPE065>
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */

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

