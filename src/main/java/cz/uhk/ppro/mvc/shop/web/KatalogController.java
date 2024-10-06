package cz.uhk.ppro.mvc.shop.web;

import cz.uhk.ppro.mvc.shop.domain.Katalog;
import cz.uhk.ppro.mvc.shop.domain.Kosik;
import cz.uhk.ppro.mvc.shop.domain.PolozkaKosiku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * Kontroler hlavni stranky katalogu
 * @author kozelto1, krizpa1
 *
 */
@Controller
public class KatalogController extends KosikInSessionController {
	private Katalog katalog = null;
	/**
	 * @return katalog
	 */
	public Katalog getKatalog() {
		return katalog;
	}
	/**
	 * @param katalog katalog, který má být nastaven (injektujeme pomoci anotaci)
	 */
	@Autowired
	public void setKatalog(Katalog katalog) {
		this.katalog = katalog;
	}
	
	/**
	 * Vlastni akce namapovana na danou URL, naplni Model pro JSP a urci logicke jmeno view
	 */
	@RequestMapping("/katalog")
	public ModelAndView zobrazit(@ModelAttribute("kosik") Kosik k) {
		ModelAndView model = new ModelAndView("katalog");
		model.addObject("polozky", katalog.getPolozky());	
		Map<Integer,PolozkaKosiku> mk = new HashMap<>();
		if (k!=null) {		
			for (PolozkaKosiku p : k.getPolozky()) {
				mk.put(p.getPolozka().getId(), p);
			}
		}		
		model.addObject("mapKosik", mk);

		return model;
	}

}
