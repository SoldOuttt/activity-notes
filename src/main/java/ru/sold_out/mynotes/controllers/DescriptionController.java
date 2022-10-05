package ru.sold_out.mynotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class DescriptionController { // rename and rewrite later
	@GetMapping()
	public String mainPage(Map<String, Object> model) {
		List<Integer> ss = new ArrayList<>();
		ss.add(1);
		ss.add(2);
		ss.add(3);
		ss.add(4);
		ss.add(5);
		ss.add(6);
		model.put("ss", ss);
		return "main/main_page";
	}
}
