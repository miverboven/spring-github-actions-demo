package fact.it.springgithubactionsdemo.controller;

import fact.it.springgithubactionsdemo.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/output")
    public String output(HttpServletRequest request, Model model) {

        String givenName = request.getParameter("fname");
        String surname = request.getParameter("lname");

        Person person = new Person(givenName,surname);
        model.addAttribute("person",person);

        return "output";

    }

}
