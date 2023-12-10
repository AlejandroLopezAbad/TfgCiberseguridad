package com.alex.tfgciberseguridad.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@Controller
class AngularController {
    @GetMapping(value = ["/{path:[^.]*}"])
    fun redirect(): String {
        return "forward:/"
    }
}