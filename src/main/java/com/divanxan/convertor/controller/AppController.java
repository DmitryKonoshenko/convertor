package com.divanxan.convertor.controller;

import com.divanxan.convertor.model.ConverterData;
import com.divanxan.convertor.model.ResponseModel;
import com.divanxan.convertor.rest.ConverterServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {
    @Autowired
    ConverterServiceImpl converterService;

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("title", "Convertor");
        return "index";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.POST)
    public String doConvert(@ModelAttribute ConverterData cModel, Model model) {
        model.addAttribute("converterModel", cModel);
        model.addAttribute("title", "Karten&Konten KLT");
        ResponseModel responseModel = converterService.getConvertedValue(cModel.getCurrency(), cModel.getResultCurrency(),
                cModel.getMoney());
        cModel.setResult(responseModel.getMoney());
        cModel.setCurrency(responseModel.getCurrency());
        cModel.setResultCurrency(responseModel.getResultCurrency());
        return "convert";
    }

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    public String doConvert(Model model) {
        ConverterData cModel = converterService.getConverterModel();
        model.addAttribute("converterModel", cModel);
        model.addAttribute("title", "Karten&Konten");
        return "convert";
    }
}
