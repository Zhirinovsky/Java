package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import static java.util.Map.entry;

@Controller("")
public class HomeController {

    @GetMapping("/main")
    String main() {
        return "main";
    }

    @GetMapping("/calc")
    String calc(){
        return "calc";
    }

    @PostMapping("/calc")
    String calc(Model model, @RequestParam(value = "first")float first, @RequestParam(value = "second")float second, @RequestParam(value = "operation")String operation){
        String result;
        switch (operation) {
            case "+":
                result = "Результат: " + (first + second);
                break;
            case "-":
                result = "Результат: " + (first - second);
                break;
            case "*":
                result = "Результат: " + (first * second);
                break;
            case "/":
                if (second == 0)  result = "На ноль не дели брат";
                else result = "Результат: " + (first / second);
                break;
            case "%":
                if (second == 0) result = "На ноль не дели брат";
                else result = "Результат: " + (first % second);
                break;
            default:
                result = "Некорректный результат";
                break;
        }
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/convert")
    String convert(){
        return "convert";
    }

    @PostMapping("/convert")
    String convert(Model model, @RequestParam(value = "value", required = false)String value, @RequestParam(value = "oldCurrency", required = false)String oldCurrency, @RequestParam(value = "newCurrency", required = false)String newCurrency){
        String result;
        if (oldCurrency == null || newCurrency == null) result = "Не выбран тип валюты";
        else {
            try {
                Map<String,String> currency = Map.ofEntries(entry("€", "102.55"), entry("£", "118.85"), entry("₽", "1"), entry("¥", "13.3"), entry("$", "97.31"));
                result = "Результат: " + (Float.parseFloat(value) * Float.parseFloat(currency.get(oldCurrency)) / Float.parseFloat(currency.get(newCurrency))) + newCurrency;
            }
            catch (NumberFormatException e) {
                result = "не введено число";
            }
        }
        model.addAttribute("result", result);
        return "result";
    }
}
