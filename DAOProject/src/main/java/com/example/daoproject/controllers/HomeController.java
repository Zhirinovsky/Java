package com.example.daoproject.controllers;

import com.example.daoproject.dao.*;
import com.example.daoproject.model.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("")
public class HomeController {
    private BankDAO _bankDAO;
    private OrderDAO _orderDAO;
    private PersonDAO _personDAO;
    private ProductDAO _productDAO;
    private StorageDAO _storageDAO;

    @Autowired
    public HomeController(BankDAO bankDAO, OrderDAO orderDAO, PersonDAO personDAO, ProductDAO productDAO, StorageDAO storageDAO){
        _bankDAO = bankDAO;
        _orderDAO = orderDAO;
        _personDAO = personDAO;
        _productDAO = productDAO;
        _storageDAO = storageDAO;
    }

    @GetMapping()
    public String mainPage(Model model){
        model.addAttribute("banks", _bankDAO.readlist());
        model.addAttribute("orders", _orderDAO.readlist());
        model.addAttribute("peoples", _personDAO.readlist());
        model.addAttribute("products", _productDAO.readlist());
        model.addAttribute("storages", _storageDAO.readlist());
        return "main";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id, @RequestParam(value = "model") String modelName){
        model.addAttribute("model", modelName);
        switch (modelName) {
            case "bank":
                model.addAttribute("object", _bankDAO.read(id));
                break;
            case "order":
                model.addAttribute("object", _orderDAO.read(id));
                break;
            case "person":
                model.addAttribute("object", _personDAO.read(id));
                break;
            case "product":
                model.addAttribute("object", _productDAO.read(id));
                break;
            case "storage":
                model.addAttribute("object", _storageDAO.read(id));
                break;
        }
        return "edit";
    }

    @PostMapping("/{id}/editBank")
    public String update(@ModelAttribute BankModel model, @PathVariable("id") int id){
        _bankDAO.update(id, model);
        return "redirect:/";
    }

    @PostMapping("/{id}/editOrder")
    public String update(@ModelAttribute OrderModel model, @PathVariable("id") int id){
        _orderDAO.update(id, model);
        return "redirect:/";
    }

    @PostMapping("/{id}/editPerson")
    public String update(@ModelAttribute PersonModel model, @PathVariable("id") int id){
        _personDAO.update(id, model);
        return "redirect:/";
    }

    @PostMapping("/{id}/editProduct")
    public String update(@ModelAttribute ProductModel model, @PathVariable("id") int id){
        _productDAO.update(id, model);
        return "redirect:/";
    }

    @PostMapping("/{id}/editStorage")
    public String update(@ModelAttribute StorageModel model, @PathVariable("id") int id){
        _storageDAO.update(id, model);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newPerson(Model model, @RequestParam(value = "model") String modelName){
        model.addAttribute("model", modelName);
        switch (modelName) {
            case "bank":
                model.addAttribute("object", new BankModel());
                break;
            case "order":
                model.addAttribute("object", new OrderModel());
                break;
            case "person":
                model.addAttribute("object", new PersonModel());
                break;
            case "product":
                model.addAttribute("object", new ProductModel());
                break;
            case "storage":
                model.addAttribute("object", new StorageModel());
                break;
        }
        return "new";
    }

    @PostMapping("/newBank")
    public String create(@ModelAttribute BankModel model){
        _bankDAO.create(model);
        return "redirect:/";
    }

    @PostMapping("/newOrder")
    public String create(@ModelAttribute OrderModel model){
        _orderDAO.create(model);
        return "redirect:/";
    }

    @PostMapping("/newPerson")
    public String create(@ModelAttribute PersonModel model){
        _personDAO.create(model);
        return "redirect:/";
    }

    @PostMapping("/newProduct")
    public String create(@ModelAttribute ProductModel model){
        _productDAO.create(model);
        return "redirect:/";
    }

    @PostMapping("/newStorage")
    public String create(@ModelAttribute StorageModel model){
        _storageDAO.create(model);
        return "redirect:/";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id, @RequestParam(value = "model") String modelName){
        switch (modelName) {
            case "bank":
                _bankDAO.delete(id);
                break;
            case "order":
                _orderDAO.delete(id);
                break;
            case "person":
                _personDAO.delete(id);
                break;
            case "product":
                _productDAO.delete(id);
                break;
            case "storage":
                _storageDAO.delete(id);
                break;
        }
        return "redirect:/";
    }
}
