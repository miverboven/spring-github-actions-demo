package fact.it.springgithubactionsdemo.controller;

import fact.it.springgithubactionsdemo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(Model model) {

        Person john = new Person("John","Doe");
        model.addAttribute("person",john);

        return "index";
    }

}
