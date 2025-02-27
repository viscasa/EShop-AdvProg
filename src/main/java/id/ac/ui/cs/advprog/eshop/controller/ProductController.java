package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product",product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String id, Model model) {
        Product product = service.findById(id); // You'll need `findById` in the service layer
        model.addAttribute("product", product);
        return "EditProduct"; // The name of the Thymeleaf template
    }

    @PostMapping("/edit/{id}")
    public String editProductPost(@PathVariable("id") String id, @ModelAttribute Product product) {
        product.setProductId(id); // Ensure ID is preserved
        service.update(product);  // This requires adding an `update` method in Service and Repository
        return "redirect:/product/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String id) {
        service.deleteById(id);
        return "redirect:/product/list";
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carservices;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car",car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carservices.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listCarPage(Model model) {
        List<Car> allCars = carservices.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carservices.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carservices.update(car.getCarId(), car);

        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carservices.deleteCarById(carId);
        return "redirect:listCar";
    }
}
