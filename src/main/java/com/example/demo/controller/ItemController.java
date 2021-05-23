package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Item;
import com.example.demo.form.ItemForm;
import com.example.demo.repository.ItemRepository;

@Controller
public class ItemController {

	@Autowired
	ItemRepository repository;
	
	@GetMapping("/items/findAll")
	public String showItemList(Model model) {
		model.addAttribute("items", repository.findAll());
		return "/items/item_list";
	}
	
	@GetMapping("/items/findAllByOrderByPriceDesc")
	public String shoItemListByOrderByPriceDesc(Model model) {
		model.addAttribute("items", repository.findAllByOrderByPriceDesc());
		return "/items/item_list";
	}
	
	@GetMapping("/items/getOne/{id}")
	public String showItem(@PathVariable int id, Model model) {
		model.addAttribute("item", repository.getOne(id));
		return "/items/item";
	}

	@GetMapping("/items/findByPrice/{price}")
	public String findByPrice(@PathVariable int price, Model model) {
		model.addAttribute("items", repository.findByPrice(price));
		return "/items/item_list";
	}
	
	@GetMapping("/items/findByNameLike/{name}")
	public String findByNameLike(@PathVariable String name, Model model) {
		model.addAttribute("items", repository.findByNameLike("%" + name + "%"));
		String path = "/items/item_list";
		return path;
	}
	
	@GetMapping("/items/hello")
	public String hello(String yeah) throws UnsupportedEncodingException {
		System.out.println(yeah);
		return "redirect:/items/findByNameLike/"+ URLEncoder.encode(yeah, "UTF-8");
	}
	
	@GetMapping("/items/findAllAndSetSelectButton")
	public String ItemListSetSelectButton(Model model) {
		model.addAttribute("items", repository.findAll());
		return "/items/item_list_dropdown";
	}
	
	@GetMapping("/items/create/input")
	public String ItemCreateInput() {
		return "/items/create_input";
	}
	
	@PostMapping("/items/create/complete")
	public String ItemCreateComplete(ItemForm form) {
		Item item = new Item();
		item.setName(form.getName());
		item.setPrice(form.getPrice());
		repository.save(item);
		
		return "redirect:/items/getOne/" + item.getId();
	}
	
	@GetMapping("/items/update/input/{id}")
	public String ItemUpdateInput(@PathVariable int id, Model model) {
		model.addAttribute("item", repository.getOne(id));
		return "/items/update_input";
	}
	
	@PostMapping("/items/update/complete")
	public String ItemUpdateCompetele(ItemForm form) {
		Item item = repository.getOne(form.getId());

		item.setName(form.getName());
		item.setPrice(form.getPrice());	
		
		repository.save(item);
		
		return "redirect:/items/getOne/" + item.getId();
		
	}
	
}
