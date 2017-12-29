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
		//����һ������
		Criteria c = example.createCriteria();
		c.andEnameLike("%"+ename+"%");
		//��ѯ�ܼ�¼��
		int total=(int)em.countByExample(example);
		PageTools ps=new PageTools(page, total, rows);
		//ͨ��RowBounds�Զ�����ÿҳ������
		RowBounds rb = new RowBounds(ps.getStartIndex()-1,rows);
		List<Emp> empList = em.selectByExampleWithRowbounds(example, rb);
		ps.setRows(empList);
		return ps;
	}
	 
	/**
	 * ���Ա��
	 */
	public void saveEmp(Emp emp){	
		em.insert(emp);
	}
	
	
	/**
	 * ɾ��Ա��
	 */
	public void deleteEmp(Integer id){
		em.deleteByPrimaryKey(id);	
	}
	
	/**
	 * �޸�Ա��
	 */
	public void updateEmp(Emp emp){
		em.updateByPrimaryKey(emp);
	}
	
	public PageTools queryEmpByDeptid(Integer deptid, Integer page, Integer rows){
		EmpExample example = new EmpExample();
		//����һ������
		Criteria c = example.createCriteria();
		//����ж���������Ժ����and...
		
		c.andDeptidEqualTo(deptid);
		//��ѯ�ܼ�¼��
		int total=(int)em.countByExample(example);
		
		PageTools ps=new PageTools(page, total, rows);
		//ͨ��RowBounds�Զ�����ÿҳ������
		RowBounds rb = new RowBounds(ps.getStartIndex()-1,rows);
		List<Emp> empList = em.selectByExampleWithRowbounds(example, rb);
		ps.setRows(empList);
		return ps;
	}
}
