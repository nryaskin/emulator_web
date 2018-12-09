package com.niri.emulator.data.controller;

import com.niri.emulator.data.dto.CoreDTO;
import com.niri.emulator.data.dto.CoreInputDTO;
import com.niri.emulator.data.util.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("core/input")
public class CoreInputController {

    private CrudService<CoreInputDTO> coreInputCrudService;
    private CrudService<CoreDTO> coreCrudService;

    @Autowired
    public void setCoreInputCrudService(CrudService<CoreInputDTO> coreInputCrudService) {
        this.coreInputCrudService = coreInputCrudService;
    }

    @Autowired
    public void setCoreCrudService(CrudService<CoreDTO> coreCrudService) {
        this.coreCrudService = coreCrudService;
    }

//
//    @RequestMapping(value = "/{id}/keys/add", method= RequestMethod.POST)
//    public ModelAndView addKeys(@PathVariable long id,
//                                @ModelAttribute("key") CoreInputDTO cI) {
//        ModelAndView model = new ModelAndView("keys");
//        if(cI != null) {
//            CoreDTO core = cCrudService.findById(id);
//            cI.setCore(core);
//            cICrudService.create(cI);
//            model.addObject("warning", "Core Registration Success");
//        }
//        else{
//            model.addObject("danger","Something Going Bad" );
//        }
//        return new ModelAndView("redirect:/" + id +"/keys");
//    }

}
