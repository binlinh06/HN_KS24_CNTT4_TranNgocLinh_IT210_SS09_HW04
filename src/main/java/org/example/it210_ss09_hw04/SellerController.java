package org.example.it210_ss09_hw04;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/register")
@SessionAttributes("sellerForm")
public class SellerController {

    // Khởi tạo object khi chưa có
    @ModelAttribute("sellerForm")
    public SellerForm initForm() {
        return new SellerForm();
    }
    // STEP 1
    @GetMapping("/step1")
    public String step1() {
        return "step1";
    }

    @PostMapping("/step1")
    public String processStep1(@ModelAttribute("sellerForm") SellerForm form) {
        return "redirect:/register/step2";
    }
    // STEP 2
    @GetMapping("/step2")
    public String step2() {
        return "step2";
    }

    @PostMapping("/step2")
    public String processStep2(@ModelAttribute("sellerForm") SellerForm form) {
        return "redirect:/register/step3";
    }
    // STEP 3 (Confirm)
    @GetMapping("/step3")
    public String step3(@ModelAttribute("sellerForm") SellerForm form, Model model) {
        model.addAttribute("data", form);
        return "step3";
    }
    // HOÀN TẤT
    @PostMapping("/finish")
    public String finish(@ModelAttribute("sellerForm") SellerForm form,
                         SessionStatus status) {

        //Giả lập lưu DB
        System.out.println("Lưu DB: " + form.getFullName());

        //QUAN TRỌNG: clear session tránh leak RAM
        status.setComplete();

        return "redirect:/success";
    }

    @GetMapping("/success")
    public String success() {
        return "success";
    }
}
