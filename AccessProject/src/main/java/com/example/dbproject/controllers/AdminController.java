package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Collections;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class AdminController {
    private final orderRepository OrderRepository;
    private final staffRepository StaffRepository;
    private final productRepository ProductRepository;
    private final storageRepository StorageRepository;
    private final supplierRepository SupplierRepository;
    private final eventRepository EventRepository;
    private final categoryRepository CategoryRepository;
    private final buyerRepository BuyerRepository;
    private final discountCardRepository DiscountCardRepository;
    private final userRepository UserRepository;

    @Autowired
    public AdminController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, userRepository userRepository){
        this.OrderRepository = OrderRepository;
        this.ProductRepository = ProductRepository;
        this.StorageRepository = StorageRepository;
        this.StaffRepository = StaffRepository;
        this.SupplierRepository = supplierRepository;
        this.EventRepository = eventRepository;
        this.CategoryRepository = categoryRepository;
        this.BuyerRepository = buyerRepository;
        this.DiscountCardRepository = discountCardRepository;
        this.UserRepository = userRepository;
    }

    @PostMapping("/editStaff")
    public String update(@ModelAttribute Staff model){
        StaffRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editBuyer")
    public String update(@ModelAttribute Buyer model){
        BuyerRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editCard")
    public String update(@ModelAttribute Discount_Card model){
        DiscountCardRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newStaff")
    public String create(@ModelAttribute Staff model){
        StaffRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newBuyer")
    public String create(@ModelAttribute Buyer model){
        BuyerRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/createCard")
    public String create(@RequestParam(value = "email") String email){
        Discount_Card card = new Discount_Card();
        Buyer buyer = BuyerRepository.findByEmail(email).get(0);
        card.setId(buyer.getId());
        card.setNumber(10000 + buyer.getId());
        card.setDiscount(0);
        card.setOwner(buyer);
        DiscountCardRepository.save(card);
        buyer.setDiscountCard(card);
        BuyerRepository.save(buyer);
        return "redirect:/";
    }

    @PostMapping("delete")
    public String delete(@RequestParam(value = "id") int id, @RequestParam(value = "model") String modelName){
        switch (modelName) {
            case "order":
                OrderRepository.deleteById(id);
                break;
            case "staff":
                StaffRepository.deleteById(id);
                break;
            case "product":
                ProductRepository.deleteById(id);
                break;
            case "storage":
                StorageRepository.deleteById(id);
                break;
            case "supplier":
                SupplierRepository.deleteById(id);
                break;
            case "event":
                EventRepository.deleteById(id);
                break;
            case "category":
                CategoryRepository.deleteById(id);
                break;
            case "buyer":
                BuyerRepository.deleteById(id);
                break;
            case "card":
                BuyerRepository.findById(id).get().setDiscountCard(null);
                DiscountCardRepository.deleteById(id);
                break;
        }
        return "redirect:/";
    }
}
