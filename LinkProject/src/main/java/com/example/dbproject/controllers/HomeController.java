package com.example.dbproject.controllers;

import com.example.dbproject.model.*;
import com.example.dbproject.repos.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
public class HomeController {
    private final orderRepository OrderRepository;
    private final staffRepository StaffRepository;
    private final productRepository ProductRepository;
    private final storageRepository StorageRepository;
    private final supplierRepository SupplierRepository;
    private final eventRepository EventRepository;
    private final categoryRepository CategoryRepository;
    private final buyerRepository BuyerRepository;
    private final discountCardRepository DiscountCardRepository;

    public String search;
    public String modelName;
    public String column;

    @Autowired
    public HomeController(orderRepository OrderRepository, productRepository ProductRepository, storageRepository StorageRepository, staffRepository StaffRepository, supplierRepository supplierRepository, eventRepository eventRepository, categoryRepository categoryRepository, buyerRepository buyerRepository, discountCardRepository discountCardRepository){
        this.OrderRepository = OrderRepository;
        this.ProductRepository = ProductRepository;
        this.StorageRepository = StorageRepository;
        this.StaffRepository = StaffRepository;
        this.SupplierRepository = supplierRepository;
        this.EventRepository = eventRepository;
        this.CategoryRepository = categoryRepository;
        this.BuyerRepository = buyerRepository;
        this.DiscountCardRepository = discountCardRepository;
    }

    @GetMapping()
    public String mainPage(Model model){
        //Это поиск
        if (search != null && modelName != null && column != null) {
            switch (modelName) {
                case "product":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("products", ProductRepository.findByNumber(search));
                            break;
                        case "Наименование":
                            model.addAttribute("products", ProductRepository.findByName(search));
                            break;
                        case "Количество":
                            model.addAttribute("products", ProductRepository.findByAmount(Integer.parseInt(search)));
                            break;
                        case "Цена":
                            model.addAttribute("products", ProductRepository.findByPrice(Double.parseDouble(search)));
                            break;
                        case "Хранилище":
                            model.addAttribute("products", ProductRepository.findByStorageAddress(search));
                            break;
                        case "Поставщик":
                            model.addAttribute("products", ProductRepository.findBySupplierName(search));
                            break;
                        case "Акция":
                            model.addAttribute("products", ProductRepository.findByEventName(search));
                            break;
                    }
                    break;
                case "storage":
                    switch (column) {
                        case "Адрес":
                            model.addAttribute("storages", StorageRepository.findByAddress(search));
                            break;
                        case "Вместительность":
                            model.addAttribute("storages", StorageRepository.findByCapacity(Integer.parseInt(search)));
                            break;
                    }
                    break;
                case "supplier":
                    switch (column) {
                        case "Название":
                            model.addAttribute("suppliers", SupplierRepository.findByName(search));
                            break;
                    }
                    break;
                case "event":
                    switch (column) {
                        case "Название":
                            model.addAttribute("events", EventRepository.findByName(search));
                            break;
                    }
                    break;
                case "staff":
                    switch (column) {
                        case "Фамилия":
                            model.addAttribute("staffs", StaffRepository.findByLastName(search));
                            break;
                        case "Имя":
                            model.addAttribute("staffs", StaffRepository.findByName(search));
                            break;
                        case "Отчество":
                            model.addAttribute("staffs", StaffRepository.findByMiddleName(search));
                            break;
                        case "Пол":
                            model.addAttribute("staffs", StaffRepository.findByGender(search));
                            break;
                        case "Почта":
                            model.addAttribute("staffs", StaffRepository.findByEmail(search));
                            break;
                        case "Пароль":
                            model.addAttribute("staffs", StaffRepository.findByPassword(search));
                            break;
                    }
                    break;
                case "order":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("orders", OrderRepository.findByNumber(Integer.parseInt(search)));
                            break;
                        case "Дата составления":
                            model.addAttribute("orders", OrderRepository.findByDate(Date.valueOf(search)));
                            break;
                    }
                    break;
                case "category":
                    switch (column) {
                        case "Название":
                            model.addAttribute("categories", CategoryRepository.findByName(search));
                            break;
                    }
                    break;
                case "buyer":
                    switch (column) {
                        case "Почта":
                            model.addAttribute("buyers", BuyerRepository.findByEmail(search));
                            break;
                        case "Телефон":
                            model.addAttribute("buyers", BuyerRepository.findByPhone(search));
                            break;
                        case "Пароль":
                            model.addAttribute("buyers", BuyerRepository.findByPassword(search));
                            break;
                        case "Номер скидочной карты":
                            model.addAttribute("buyers", BuyerRepository.findByDiscountCardNumber(Integer.parseInt(search)));
                            break;
                        case "Размер скидки":
                            model.addAttribute("buyers", BuyerRepository.findByDiscountCardDiscount(Integer.parseInt(search)));
                            break;
                    }
                    break;
                case "card":
                    switch (column) {
                        case "Номер":
                            model.addAttribute("cards", DiscountCardRepository.findByNumber(Integer.parseInt(search)));
                            break;
                        case "Скидка":
                            model.addAttribute("cards", DiscountCardRepository.findByDiscount(Integer.parseInt(search)));
                            break;
                        case "Владелец":
                            model.addAttribute("cards", DiscountCardRepository.findByOwnerEmail(search));
                            break;
                    }
                    break;
            }
            search = null;
            modelName = null;
            column = null;
        }
        else {
            model.addAttribute("orders", OrderRepository.findAll());
            model.addAttribute("staffs", StaffRepository.findAll());
            model.addAttribute("products", ProductRepository.findAll());
            model.addAttribute("storages", StorageRepository.findAll());
            model.addAttribute("suppliers", SupplierRepository.findAll());
            model.addAttribute("events", EventRepository.findAll());
            model.addAttribute("categories", CategoryRepository.findAll());
            model.addAttribute("buyers", BuyerRepository.findAll());
            model.addAttribute("cards", DiscountCardRepository.findAll());
        }
        return "main";
    }

    @PostMapping("/search")
    public String search(@RequestParam(value = "model") String model, @RequestParam(value = "column") String column, @RequestParam(value = "search") String search) {
        modelName = model;
        this.search = search;
        this.column = column;
        return "redirect:/";
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

    @PostMapping("/editStaff")
    public String update(@ModelAttribute Staff model){
        StaffRepository.save(model);
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

    @PostMapping("/newStaff")
    public String create(@ModelAttribute Staff model){
        StaffRepository.save(model);
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
