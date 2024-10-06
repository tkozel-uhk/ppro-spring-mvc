/*
 * Polozka.java
 *
 * Created on 7. březen 2007, 14:12
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package cz.uhk.ppro.mvc.shop.domain;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

/**
 * Polozka katalogu zbozi
 * @author kozelto1, krizpa1
 */
public class Polozka  {
    private int id;
    @NotBlank(message="Název položky nesmí být prázdný")
    private String nazev;
    private double cena;
    @Range(min=0,max=100,message="DPH musí být v rozsahu 0-100%")
    private double dph;
    
    /** Creates a new instance of Polozka */
    public Polozka() {
    }

    public Polozka(int id, String nazev, double cena, double dph) {
        this.id = id;
        this.cena = cena;
        this.dph = dph;
        this.nazev = nazev;
    }
    
    public double getCenaSDph() {
        return cena*(100+dph)/100;
    }
    
    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getDph() {
        return dph;
    }

    public void setDph(double dph) {
        this.dph = dph;
    }
    
    public int getId() {
        return id;
    }

	/**
	 * @param id id, který má být nastaven
	 */
	public void setId(int id) {
		this.id = id;
	}
}
