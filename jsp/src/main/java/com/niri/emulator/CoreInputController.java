package com.niri.emulator;

import com.niri.emulator.data.core.CoreDTO;
import com.niri.emulator.data.coreinput.CoreInput;
import com.niri.emulator.data.coreinput.CoreInputDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CoreInputController {

    private CrudService<CoreInputDTO> cICrudService;
    private CrudService<CoreDTO> cCrudService;

    @Autowired
    public void setCoreCrudService(CrudService<CoreInputDTO> cICrudService) {
        this.cICrudService = cICrudService;
    }

    @Autowired
    public void setcCrudService(CrudService<CoreDTO> cCrudService) {
        this.cCrudService = cCrudService;
    }

    @RequestMapping(value = "/{id}/keys/add", method= RequestMethod.POST)
    public ModelAndView keys(@PathVariable long id,
                             @ModelAttribute("key") CoreInputDTO cI) {
        ModelAndView model = new ModelAndView("keys");
        if(cI != null) {
            CoreDTO core = cCrudService.findById(id);
            cI.setCore(core);
            cICrudService.create(cI);
            model.addObject("warning", "Core Registration Success");
        }
        else{
            model.addObject("danger","Something Going Bad" );
        }
        return new ModelAndView("redirect:/" + id +"/keys");
    }

}
