package cn.et.food.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.EmpMapper;
import cn.et.food.entity.Emp;
import cn.et.food.entity.EmpExample;
import cn.et.food.entity.EmpExample.Criteria;
import cn.et.food.service.EmpService;
import cn.et.food.utils.PageTools;

@Service
public class EmpServiceImpl  implements EmpService{
	
	
	@Autowired
	EmpMapper em;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.FoodService#queryStudent(java.lang.String)
	 */
	public PageTools queryEmp(String ename,Integer page,Integer rows){
		if(ename==null){
			ename="";
		}
		EmpExample example = new EmpExample();
		//构造一个条件
		Criteria c = example.createCriteria();
		c.andEnameLike("%"+ename+"%");
		//查询总记录数
		int total=(int)em.countByExample(example);
		PageTools ps=new PageTools(page, total, rows);
		//通过RowBounds自动生成每页的数据
		RowBounds rb = new RowBounds(ps.getStartIndex()-1,rows);
		List<Emp> empList = em.selectByExampleWithRowbounds(example, rb);
		ps.setRows(empList);
		return ps;
	}
	 
	/**
	 * 添加员工
	 */
	public void saveEmp(Emp emp){	
		em.insert(emp);
	}
	
	
	/**
	 * 删除员工
	 */
	public void deleteEmp(Integer id){
		em.deleteByPrimaryKey(id);	
	}
	
	/**
	 * 修改员工
	 */
	public void updateEmp(Emp emp){
		em.updateByPrimaryKey(emp);
	}
	
	public PageTools queryEmpByDeptid(Integer deptid, Integer page, Integer rows){
		EmpExample example = new EmpExample();
		//构造一个条件
		Criteria c = example.createCriteria();
		//如果有多个条件可以后面跟and...
		
		c.andDeptidEqualTo(deptid);
		//查询总记录数
		int total=(int)em.countByExample(example);
		
		PageTools ps=new PageTools(page, total, rows);
		//通过RowBounds自动生成每页的数据
		RowBounds rb = new RowBounds(ps.getStartIndex()-1,rows);
		List<Emp> empList = em.selectByExampleWithRowbounds(example, rb);
		ps.setRows(empList);
		return ps;
	}
}
