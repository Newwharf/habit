package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.Plan;

/**
 * @author lpp
 *
 */
public interface IPlanService {

	/**根据用户id搜索数据库中的计划
	 * @param uid 用户id
	 * @param m 从第几条开始读取
	 * @param n 一共读取多少条
	 * @return 计划集合
	 * @throws Exception
	 */
	public List<Plan> findPlansByUserId(long uid,int m,int n)throws Exception;
	
	/**根据训练ID返回训练
	 * @param id 要查询的训练ID
	 * @return 训练对象
	 * @throws Exception
	 */
	public Plan findPlanById(long id)throws Exception;
	
	/**根据训练ID删除训练
	 * @param id 要删除的训练ID
	 * @return 若删除成功，返回被删除的训练，否则返回-1
	 * @throws Exception
	 */
	public int deletePlanById(long id)throws Exception;
	
	/**修改一个训练
	 * @param plan 要修改的训练
	 * @return 若修改成功则返回被修改的训练ID，否则返回-1
	 * @throws Exception
	 */
	public int updatePlan(Plan plan)throws Exception;
	
	/**
	 * 添加一个训练
	 * @param plan 要添加的训练
	 * @return 
	 * @throws Exception
	 */
	public int addPlan(Plan plan)throws Exception;

}
