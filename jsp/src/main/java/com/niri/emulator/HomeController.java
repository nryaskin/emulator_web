package com.niri.emulator;

import com.niri.emulator.data.core.CoreCrudService;
import com.niri.emulator.data.core.CoreDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class HomeController {

    private CoreCrudService coreCrudService;

    @Autowired
    public void setCoreCrudService(CoreCrudService coreCrudService) {
        this.coreCrudService = coreCrudService;
    }

    @Value("${home.message}")
    private String message;

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        return "/index";
    }

    @RequestMapping(value = "", method=RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView("index");
        model.addObject("list", coreCrudService.findAll());
        return model;
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public ModelAndView deleteUsers(@PathVariable long id) {
        coreCrudService.delete(id);
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value="/add", method = RequestMethod.POST)
    public ModelAndView userRegister(@ModelAttribute("cr")CoreDTO core){
        ModelAndView model = new ModelAndView("index");
        if(core != null){
            coreCrudService.create(core);
            model.addObject("warning", "Core Registration Success");
        }
        else{
            model.addObject("danger","Something Going Bad" );
        }
        return new ModelAndView("redirect:/index");
    }

    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable("id") long id) {
        ModelAndView model = new ModelAndView("edit");
        CoreDTO core = coreCrudService.findById(id);
        model.addObject("cr", core);
        return model;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("id") long id,
                               @RequestParam("name") String name,
                               @RequestParam("path") String path) {
        CoreDTO core = new CoreDTO();
        core.setId(id);
        core.setCoreName(name);
        core.setCorePath(path);
        coreCrudService.update(core);
        return new ModelAndView("redirect:/index");
    }


}
