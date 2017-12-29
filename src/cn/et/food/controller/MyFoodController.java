package cn.et.food.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.et.food.entity.Food;
import cn.et.food.entity.Result;
import cn.et.food.service.MyFoodService;
import cn.et.food.utils.PageTools;

@Controller
public class MyFoodController {
	@Autowired
	MyFoodService service;
	@ResponseBody
	@RequestMapping("/queryFood")
	public PageTools queryFoodList(String foodname,Integer page,Integer rows){
		return service.queryFoodList(foodname, page, rows);
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFood/{foodid}",method=RequestMethod.DELETE)
	public Result deleteFood(@PathVariable Integer foodid,Integer page,Integer rows){
		Result r = new Result();
		r.setCode(1);
		try {
			service.deleteFood(foodid);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/updateFood/{foodid}",method=RequestMethod.PUT)
	public Result updateFood(@PathVariable Integer foodid, Food food,Integer page,Integer rows){
		food.setFoodid(foodid);
		Result r = new Result();
		r.setCode(1);
		try {
			service.updateFood(food);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
	
	@ResponseBody
	@RequestMapping(value="/saveFood",method=RequestMethod.POST)
	public Result saveFood(Food food,MultipartFile image){
		Result r = new Result();
		r.setCode(1);
		try {
			String fileName = image.getOriginalFilename();
			File destFile = new File("E:\\myImage\\"+fileName);
			image.transferTo(destFile);
			service.saveFood(food);
		} catch (Exception e) {
			r.setCode(0);
			r.setMessage(e);
		}
		return r;
	}
}
