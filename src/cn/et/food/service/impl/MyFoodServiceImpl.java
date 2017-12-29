package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.et.food.dao.FoodMapper;
import cn.et.food.entity.Food;
import cn.et.food.entity.FoodExample;
import cn.et.food.service.MyFoodService;
import cn.et.food.utils.PageTools;

@Repository
public class MyFoodServiceImpl implements MyFoodService{

	@Autowired
	FoodMapper fm;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.MyFoodService#queryFoodList(java.lang.String)
	 */
	public PageTools queryFoodList(String foodname ,Integer page ,Integer rows){
		if (foodname==null) {
			foodname="";
		}
		FoodExample fe = new FoodExample();
		fe.createCriteria().andFoodnameLike("%"+foodname+"%");
		//发起sql语句查询总记录数
		int total = queryFoodCount(fe);
		//limit 开始位置,查询条数
		PageTools pts = new PageTools(page, total, rows);
		RowBounds rb = new RowBounds(pts.getStartIndex()-1, rows);
		List<Food> foodList = fm.selectByExampleWithRowbounds(fe, rb);
		pts.setRows(foodList);
		return pts;
		
	}
	
	public void deleteFood(Integer foodid){
		fm.deleteByPrimaryKey(foodid);
	}
	
	public void updateFood(Food foodid){
		fm.updateByPrimaryKey(foodid);
	}
	
	public void saveFood(Food food){
		fm.insert(food);
	}
	
	public int queryFoodCount(FoodExample fe){
		int total = (int)fm.countByExample(fe);
		return total;
	}
}
