package cz.uhk.ppro.mvc.shop.web;

import cz.uhk.ppro.mvc.shop.domain.Katalog;
import cz.uhk.ppro.mvc.shop.domain.Polozka;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.DecimalFormat;

/**
 * Kontroler formulare pro zadavani, resp.editaci polozek katalogu
 * @author kozelto1, krizpa1
 *
 */
@Controller
@RequestMapping("/polozka")
public class PolozkaFormController {
	private Katalog katalog = null;

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		//Nastaveni cutom editoru polozek formulare
		CustomNumberEditor ed = new CustomNumberEditor(
				Double.class,
				new DecimalFormat("#,##0.00"),
				true
		); 
		binder.registerCustomEditor(Double.class,"cena",ed);
		binder.registerCustomEditor(Double.class,"dph",ed);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	protected String onSubmit(@ModelAttribute("polozka") @Valid Polozka polozka, BindingResult result) {
		//Provede se po odeslani formu
		if (result.hasErrors()) {
			return "polozkaForm";
		}
		if (polozka.getId() != 0) {
			katalog.odstran(polozka.getId());
			polozka.setId(0);
		}
		katalog.pridej(polozka);
		return "redirect:/sprava";
	}
	
	@RequestMapping(method=RequestMethod.GET)
	protected String form(@RequestParam(value="polId",required=false) Integer id, Model m) {
		//Priprava dat pro form, pokud je zadano id polozky, pak bude predvyplnena
		if (id!=null) {
			m.addAttribute("polozka", katalog.getById(id));
		} else {
			// pokud vytvarime novou polozku, tak do JSP predame novou instanci
			m.addAttribute("polozka", new Polozka());
		}
		return "polozkaForm";
	}

	public Katalog getKatalog() {
		return katalog;
	}

	@Autowired
	public void setKatalog(Katalog katalog) {
		this.katalog = katalog;
	}

}
