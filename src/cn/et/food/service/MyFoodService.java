package cn.et.food.service;

import cn.et.food.entity.Food;
import cn.et.food.utils.PageTools;

public interface MyFoodService {

	public abstract PageTools queryFoodList(String foodname,Integer page , Integer rows);
	public void deleteFood(Integer foodid);
	public void updateFood(Food foodid);
	public void saveFood(Food food);
}