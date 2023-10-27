package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
public class ManagerController {
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
    public ManagerController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository, userRepository userRepository){
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

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id") int id, @RequestParam(value = "model") String modelName){
        model.addAttribute("model", modelName);
        switch (modelName) {
            case "order":
                model.addAttribute("object", OrderRepository.findById(id));
                break;
            case "staff":
                model.addAttribute("object", StaffRepository.findById(id));
                break;
            case "product":
                model.addAttribute("object", ProductRepository.findById(id));
                model.addAttribute("storages", StorageRepository.findAll());
                model.addAttribute("suppliers", SupplierRepository.findAll());
                model.addAttribute("events", EventRepository.findAll());
                break;
            case "storage":
                model.addAttribute("object", StorageRepository.findById(id));
                break;
            case "supplier":
                model.addAttribute("object", SupplierRepository.findById(id));
                break;
            case "event":
                model.addAttribute("object", EventRepository.findById(id));
                break;
            case "category":
                model.addAttribute("object", CategoryRepository.findById(id));
                break;
            case "buyer":
                model.addAttribute("object", BuyerRepository.findById(id));
                break;
            case "card":
                model.addAttribute("object", DiscountCardRepository.findById(id));
                break;
            case "categories":
                model.addAttribute("object", ProductRepository.findById(id));
                model.addAttribute("categories", CategoryRepository.findAll());
                break;
        }
        return "edit";
    }

    @PostMapping("/editOrder")
    public String update(@ModelAttribute Orderr model){
        OrderRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editProduct")
    public String update(@ModelAttribute Product model, @RequestParam int storageId, @RequestParam int supplierId, @RequestParam int eventId){
        model.setStorage(StorageRepository.findById(storageId).get());
        model.setSupplier(SupplierRepository.findById(supplierId).get());
        model.setEvent(EventRepository.findById(eventId).get());
        ProductRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editStorage")
    public String update(@ModelAttribute Storage model){
        StorageRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editSupplier")
    public String update(@ModelAttribute Supplier model){
        SupplierRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editEvent")
    public String update(@ModelAttribute Event model){
        EventRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/editCategory")
    public String update(@ModelAttribute Category model){
        CategoryRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/addCategory")
    public String add(@ModelAttribute Product product, @RequestParam(value = "categoryName") String categoryName){
        Product product1 = ProductRepository.findByName(product.getName()).get(0);
        Category category = CategoryRepository.findByName(categoryName).get(0);
        if (!product1.getCategories().contains(category))
        {
            product1.getCategories().add(category);
            category.getProducts().add(product1);
            CategoryRepository.save(category);
            ProductRepository.save(product1);
        }
        return "redirect:/";
    }

    @PostMapping("/removeCategory")
    public String remove(Model model, @ModelAttribute Product product, @RequestParam(value = "categoryName") String categoryName){
        Product product1 = ProductRepository.findByName(product.getName()).get(0);
        Category category = CategoryRepository.findByName(categoryName).get(0);
        product1.getCategories().remove(category);
        category.getProducts().remove(product1);
        CategoryRepository.save(category);
        ProductRepository.save(product1);
        return "redirect:/";
    }

    @GetMapping("/new")
    public String newPerson(Model model, @RequestParam(value = "model") String modelName){
        model.addAttribute("model", modelName);
        switch (modelName) {
            case "order":
                model.addAttribute("object", new Orderr());
                break;
            case "staff":
                model.addAttribute("object", new Staff());
                break;
            case "product":
                model.addAttribute("object", new Product());
                model.addAttribute("storages", StorageRepository.findAll());
                model.addAttribute("suppliers", SupplierRepository.findAll());
                model.addAttribute("events", EventRepository.findAll());
                break;
            case "storage":
                model.addAttribute("object", new Storage());
                break;
            case "supplier":
                model.addAttribute("object", new Supplier());
                break;
            case "event":
                model.addAttribute("object", new Event());
                break;
            case "category":
                model.addAttribute("object", new Category());
                break;
            case "buyer":
                model.addAttribute("object", new Buyer());
                break;
        }
        return "new";
    }

    @PostMapping("/newOrder")
    public String create(@ModelAttribute Orderr model){
        OrderRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newProduct")
    public String create(@ModelAttribute Product model, @RequestParam int storageId, @RequestParam int supplierId, @RequestParam int eventId){
        model.setStorage(StorageRepository.findById(storageId).get());
        model.setSupplier(SupplierRepository.findById(supplierId).get());
        model.setEvent(EventRepository.findById(eventId).get());
        ProductRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newStorage")
    public String create(@ModelAttribute Storage model){
        StorageRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newSupplier")
    public String create(@ModelAttribute Supplier model){
        SupplierRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newEvent")
    public String create(@ModelAttribute Event model){
        EventRepository.save(model);
        return "redirect:/";
    }

    @PostMapping("/newCategory")
    public String create(@ModelAttribute Category model){
        CategoryRepository.save(model);
        return "redirect:/";
    }
}
