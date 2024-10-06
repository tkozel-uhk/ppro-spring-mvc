package cz.uhk.ppro.mvc.shop.domain;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementace kosiku 
 * @author kozelto1
 *
 */
public class SimpleKosik implements Kosik {
	private List<PolozkaKosiku> polozky = new ArrayList<>();
	
	public List<PolozkaKosiku> getPolozky() {
		return polozky;
	}

	public void odstran(Polozka polozka) {
        polozky.removeIf(p -> p.getPolozka().getId() == polozka.getId());
	}

	public void update(Polozka polozka, int cnt) {
		for (PolozkaKosiku p : polozky) {
			if (p.getPolozka().getId()==polozka.getId()) {
				if (cnt == 0) {
					polozky.remove(p);
				} else {
					p.setPocet(cnt);
				}
				return;
			}
		}
		polozky.add(new PolozkaKosiku(polozka,cnt));
		polozky.sort((p1,p2) -> Collator
				.getInstance()
				.compare(p1.getPolozka().getNazev(), p2.getPolozka().getNazev()));
	}

	public void vysyp() {
		polozky.clear();
	}

	public double getCenaCelkem() {
		double cena = 0;
		for (PolozkaKosiku p : polozky) {
			cena += p.getCena();
		}
		return cena;
	}

	public double getCenaSDphCelkem() {
		double cena = 0;
		for (PolozkaKosiku p : polozky) {
			cena += p.getCenaSDph();
		}
		return cena;
	}

}
