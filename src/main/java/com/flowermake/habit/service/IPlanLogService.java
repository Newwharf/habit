package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.PlanLog;

/**
 * @author lpp
 *
 */
public interface IPlanLogService {

	/**
	 * 根据训练id，查询该训练下最近一次训练日志
	 * 
	 * @param planId
	 *            训练ID
	 * @return 训练日志
	 * @throws Exception
	 */
	public PlanLog findLastPlanLogByPlanId(long planId) throws Exception;

	/**
	 * 添加一个训练计划日志
	 * 
	 * @param planLog
	 *            要添加的训练计划日志
	 * @return 如果添加成功，则返回添加的训练计划日志id，否则返回-1
	 * @throws Exception
	 */
	public int addPlanLog(PlanLog planLog) throws Exception;

	/**
	 * 根据训练日志id删除训练日志
	 * 
	 * @param id
	 *            训练日志id
	 * @return 如果删除成功，则返回训练日志id，否则返回-1
	 * @throws Exception
	 */
	public int deletePlanLog(long id) throws Exception;

	/**
	 * 根据训练id查询该训练下所有的训练日志
	 * 
	 * @param planId
	 *            训练id
	 * @param m
	 *            从第几条开始读取
	 * @param n
	 *            一共读取多少条
	 * @return 训练日志集合
	 * @throws Exception
	 */
	public List<PlanLog> findPlanLogByPlanId(long planId, int m, int n) throws Exception;

	/**
	 * 根据训练日志ID查询一个训练日志
	 * 
	 * @param plid
	 *            训练日志id
	 * @return 训练日志
	 * @throws Exception
	 */
	public PlanLog findPlanLogById(long plid) throws Exception;

	/**
	 * 修改一个训练日志
	 * 
	 * @param planLog
	 * @return
	 * @throws Exception
	 */
	public int updatePlanLog(PlanLog planLog) throws Exception;

}
