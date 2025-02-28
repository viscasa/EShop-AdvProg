package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
class CarController {
    @Autowired
    private CarService carServices;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car",car);
        return "CreateCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carServices.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String listCarPage(Model model) {
        List<Car> allCars = carServices.findAll();
        model.addAttribute("cars", allCars);
        return "CarList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carServices.findById(carId);
        model.addAttribute("car", car);
        return "EditCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carServices.update(car.getCarId(), car);

        return "redirect:listCar";
    }

    @PostMapping("/deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carServices.deleteCarById(carId);
        return "redirect:listCar";
    }
}
