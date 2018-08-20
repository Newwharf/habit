package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.PlanAction;

/**
 * @author lpp
 *
 */
public interface IPlanActionService {

	/**
	 * 添加一个训练动作关联
	 * 
	 * @param planAction
	 *            要添加的训练动作关联
	 * @return 如果添加成功则返回添加的训练动作的ID，否则返回-1
	 * @throws Exception
	 */
	public int addPlanAction(PlanAction planAction) throws Exception;

	/**
	 * 根据训练id，查询该训练下所有关联的训练动作
	 * 
	 * @param id
	 *            训练id
	 * @param m
	 *            从第几条开始读取
	 * @param n
	 *            一共读取多少条
	 * @return 关联的动作集合
	 * @throws Exception
	 */
	public List<PlanAction> findPlanActionByPlanId(long planId, int m, int n) throws Exception;

	/**
	 * 修改一个训练动作关联
	 * 
	 * @param planAction
	 *            要修改的训练动作关联
	 * @return 如果修改成功，则返回被修改的训练动作的id，否则返回-1
	 * @throws Exception
	 */
	public int updatePlanAction(PlanAction planAction) throws Exception;

	/**
	 * 根据di查询一个训练动作关联
	 * 
	 * @param id
	 *            要查询的id
	 * @return 查询出来的训练动作关联的对象
	 * @throws Exception
	 */
	public PlanAction findPlanActionById(long id) throws Exception;

	/**
	 * 根据动作id查询该与该动作关联的所有未删除的训练计划
	 * 
	 * @param actionId
	 *            动作id
	 * @param m
	 *            分页开始的条数
	 * @param n
	 *            每页取多少条
	 * @return 动作集合
	 * @throws Exception
	 */
	public List<PlanAction> findPlanActionByActionid(long actionId, int m, int n) throws Exception;

	/**
	 * 根据训练id删除关联的动作中间表，逻辑删除
	 * 
	 * @param planid
	 *            训练id
	 * @return
	 * @throws Exception
	 */
	public int deleteByPlanId(long planid) throws Exception;
}
