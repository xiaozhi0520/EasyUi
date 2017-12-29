package cn.et.food.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.et.food.dao.DeptMapper;
import cn.et.food.entity.Dept;
import cn.et.food.entity.DeptExample;
import cn.et.food.entity.TreeNode;
import cn.et.food.service.DeptService;
@Service
public class DeptServiceImpl implements DeptService {
	@Autowired
	DeptMapper dm;
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.StudentService#queryStudent(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see cn.et.food.service.impl.DeptService#queryTreeNode(java.lang.Integer)
	 */
	public List<TreeNode> queryTreeNode(Integer pid){
		//创建查询条件
		DeptExample de = new DeptExample();
		de.createCriteria().andPidEqualTo(pid);
		//查询数据
		List<Dept> dept = dm.selectByExample(de);
		List<TreeNode> deptList = new ArrayList();
		//将数据转成TreeNode并存入list
		for(Dept d:dept){
			//创建TreeNode
			TreeNode tn = new TreeNode();
			tn.setId(d.getId());
			tn.setText(d.getDname());
			
			//判断在当前节点下是否存在子节点
			if(queryTreeNode(d.getId()).size()==0){
				//当前节点下没有子节点，将状态改为open
				tn.setState("open");
			}
			deptList.add(tn);
		}
		
		return deptList;
	}
}
