package cz.czechitas.java2webapps.lekce10.controller;

import cz.czechitas.java2webapps.lekce10.service.SkolaService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SkolaController {

    private final SkolaService skolaService;

    public SkolaController(SkolaService skolaService) {
        this.skolaService = skolaService;
    }

    @GetMapping("/")
    public ModelAndView index(@PageableDefault(sort = {"nazev"}) Pageable pageable) {
        return new ModelAndView("index")
                .addObject("listTrid", skolaService.listTrid());
    }

    @GetMapping(path = "/", params = "id")
    public ModelAndView detailTrida(@PageableDefault(sort = {"prijmeni, jmeno"}) short id) {
        ModelAndView result = new ModelAndView("detailTrida");
        result.addObject("trida", skolaService.findTridaById(id));
        result.addObject("listStudentu", skolaService.listStudentuTridy(id));
        return result;
    }

    @GetMapping(path = "/student", params = "id")
    public ModelAndView detailStudent(@PageableDefault(sort = {"prijmeni, jmeno"}) Integer id) {
        ModelAndView result = new ModelAndView("detailStudent");
        result.addObject("student", skolaService.findById(id));
        result.addObject("listRodicu", skolaService.listRodicuStudenta(id));
        return result;
    }
}

