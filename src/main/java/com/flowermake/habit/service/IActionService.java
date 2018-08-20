package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.Action;
import com.flowermake.habit.domain.Action_PlanDetailsJSPTemp;

public interface IActionService {

	/**
	 * 添加一个动作，添加动作之前会调用valiDataAction进行验证
	 * 
	 * @param action
	 *            要添加的action
	 * @return 添加成功返回success，添加失败返回失败原因
	 * @throws Exception
	 */
	public String addAction(Action action) throws Exception;

	/**
	 * 验证一个需要新增的action的内容是否合法
	 * 
	 * @param 要验证的action
	 * @return 成功返回success，失败返回失败原因
	 * @throws Exception
	 */
	public String valiDataAction(Action action) throws Exception;

	/**
	 * 根据用户id查询未删除的动作
	 * 
	 * @param uid
	 *            用户id
	 * @param m
	 *            分页开始的条数
	 * @param n
	 *            每页取多少条
	 * @return 动作集合
	 * @throws Exception
	 */
	public List<Action> findActionByUserId(long uid, int m, int n) throws Exception;

	/**
	 * 根据动作id删除一个动作
	 * 
	 * @param id
	 *            要删除的动作id
	 * @return 1成功，0失败
	 * @throws Exception
	 */
	public int deleteActionById(long id) throws Exception;

	/**
	 * 根据动作id查询一个动作
	 * 
	 * @param id
	 *            动作id
	 * @return 查询出来的动作
	 * @throws Exception
	 */
	public Action findActionById(long id) throws Exception;

	/**
	 * 修改一个动作，需要设置其所有字段
	 * 
	 * @param action
	 *            要修改的action
	 * @return 0失败，1成功
	 * @throws Exception
	 */
	public int updateAction(Action action) throws Exception;

	/**
	 * 查询某个用户的某个训练下动作
	 * 
	 * @param pid
	 *            训练id
	 * @param uid
	 *            用户id
	 * @return 动作集合
	 * @throws Exception
	 */
	List<Action_PlanDetailsJSPTemp> selectByPlan(long pid, long uid) throws Exception;
}
