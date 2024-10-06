package cz.uhk.ppro.mvc.shop.web;

import cz.uhk.ppro.mvc.shop.domain.Katalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Kontroler spravy katalogu (priklad, jak rozlisit akce pomoci parametru v URL)
 * @author kozelto1, krizpa1
 *
 */
@Controller
@RequestMapping("/sprava")
public class SpravaKataloguController {
	Katalog katalog = null;

	/**
	 * Zobrazeni tabulky polozek s tlacitk pro editaci/mazani
	 */
	@RequestMapping(params="!akce")
	public ModelAndView show() {
		return new ModelAndView("katalogSprava","katalog",katalog);
	}

	/**
	 * Smazani polozky
	 */
	@RequestMapping(params="akce=remove")
	public String remove(@RequestParam("polId") int id) {
		katalog.odstran(id);
		return "redirect:/sprava";
	}
	
	public Katalog getKatalog() {
		return katalog;
	}

	@Autowired
	public void setKatalog(Katalog katalog) {
		this.katalog = katalog;
	}
}
