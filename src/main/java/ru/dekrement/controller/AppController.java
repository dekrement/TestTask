package ru.dekrement.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ru.dekrement.model.User;
import ru.dekrement.service.UserService;
import org.slf4j.Logger;

import javax.validation.Valid;

/**
 * Created by web on 06.03.2017.
 */
@Controller
public class AppController {
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new UserFormValidator());
    }
    private final int MAX_RESULT = 10;
    Logger logger = LoggerFactory.getLogger(AppController.class);
    @Autowired(required = true)
    private UserService userService;

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable Integer id) {
        logger.info("id: " + id);
        userService.deleteUser(id);
        return "redirect:/";
    }

    @RequestMapping(path = "/edit/{id}", method = RequestMethod.GET)
    public String editUser(@PathVariable Integer id, Model model) {
        logger.info("id: " + id);
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }
    @RequestMapping(path = "/edit", method = RequestMethod.POST)
    public String editUser(User user) {
        userService.updateUser(user);
        return "redirect:/?edit=success";
    }

    @RequestMapping(path = "/add", method = RequestMethod.GET)
    public String createUser() {
        return "create";
    }

    @RequestMapping(path = "/add", method = RequestMethod.POST)
    public String createUser(@Valid User user, BindingResult result, Model model) {
        logger.info(result.toString());
        logger.info(user.toString());
        userService.addUser(user);
        if (result.hasErrors()) {
            logger.info(result.getAllErrors().toString());
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("user", user);
            return "edit";
        }
        return "redirect:/";
    }
    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String index(Model model, @RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "name", required = false) String name) {
        if (page == null)
            page = 0;
        logger.info("index() requested");
        logger.info("page #" + page);
        Integer offset = page * MAX_RESULT;
        logger.info(String.valueOf(offset));
        int pageCnt = (userService.count(name).intValue() - 1) / MAX_RESULT;
        logger.info(pageCnt + "");
        model.addAttribute("page", page);
        model.addAttribute("name", name);
        model.addAttribute("pageCnt", pageCnt);
        model.addAttribute("users", userService.listUsers(name, page*MAX_RESULT, MAX_RESULT));
        model.addAttribute("userCount", userService.count(name));
        logger.info("end index()");
        return "index";
    }
}
