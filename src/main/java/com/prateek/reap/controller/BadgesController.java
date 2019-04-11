package com.prateek.reap.controller;

import com.prateek.reap.service.BadgeService;
import com.prateek.reap.service.OrderService;
import com.prateek.reap.service.UserStarReceivedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.prateek.reap.util.CommonUtils.currentLoggedInUser;

@Controller
public class BadgesController {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserStarReceivedService userStarReceivedService;

    @Autowired
    private OrderService orderService;


    @RequestMapping("/badge/{id}")
    public String getPage(Model model, @PathVariable(value = "id") Integer id, Authentication authentication) {
        model.addAttribute("loggedUser", currentLoggedInUser(authentication));
        model.addAttribute("sharedRecognition", badgeService.findAllSharedRecognition(id));
        model.addAttribute("receivedRecognition", badgeService.findAllReceivedRecognition(id));
        model.addAttribute("allRecognition", badgeService.findAllRecognitionByUser(id));
        model.addAttribute("starReceived", userStarReceivedService.findByUserId(id));
        model.addAttribute("purchaseHistory",orderService.findByUserId(id));
        return "/badges";

    }


}










