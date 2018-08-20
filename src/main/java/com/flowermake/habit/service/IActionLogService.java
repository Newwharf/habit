package com.flowermake.habit.service;

import java.util.List;


import com.flowermake.habit.domain.ActionLog;

/**
 * @author lpp
 *
 */
public interface IActionLogService {

	/**
	 * 添加一个动作日志
	 * 
	 * @param actionLog
	 *            要添加的训练动作日志
	 * @return 如果添加成功，则返回添加的日志ID，否则返回0
	 * @throws Exception
	 */
	public int addActionLog(ActionLog actionLog) throws Exception;

	/**
	 * 根据id删除一个动作日志
	 * 
	 * @param id
	 *            动作id
	 * @return 如果删除成功则返回被删除的动作id，否则返回-1
	 * @throws Exception
	 */
	public int deleteActionById(long id) throws Exception;

	/**
	 * 根据训练日志id查询该训练日志下所有的动作日志
	 * 
	 * @param pl_id
	 *            训练日志id
	 * @return 动作日志集合
	 * @throws Exception
	 */
	public List<ActionLog> findByPLId(long pl_id) throws Exception;

	/**
	 * 根据actionlogid取出同一个plan下面这个actionlog前后指定条数的记录
	 * 
	 * @param pid
	 *            planid
	 * @param aid
	 *            actionid
	 * @param alid
	 *            actionlogid
	 * @param before
	 *            指定要取多少条记录
	 * @param after
	 *            指定要取后面多条记录
	 * @return actionlog集合
	 * @throws Exception
	 */
	public List<ActionLog> findByPId(long pid, long aid, long alid, int before, int after) throws Exception;

	/**
	 * 取得某个plan里面某个action下的最后n个actionLog
	 * 
	 * @param pid
	 *            plan id
	 * @param aid
	 *            action id
	 * @param n
	 *            要取多少条
	 * @return 返回符合条件的actionLog集合，按时间远到近排升序
	 */
	public List<ActionLog> findLast(long pid, long aid, int n);

	/**
	 * 查询每个该用户的每个动作下最近的记录
	 * 
	 * @param uid
	 *            用户id
	 * @return 动作记录集合
	 */
	public List<ActionLog> selectLatelyLog(long uid);

	/**
	 * 根据动作id查询该动作下的日志记录
	 * 
	 * @param aid
	 *            动作id
	 * @param m
	 *            分页开始量
	 * @param n
	 *            数据总量
	 * @return
	 */
	public List<ActionLog> selectByAid(long aid, int m, int n);

	/**
	 * 修改一个actiolog
	 * 
	 * @param actionLog
	 *            要修改的actionlog
	 * @return
	 */
	public int updateActionLog(ActionLog actionLog);

	/**
	 * 根据actionlogid查询一个actionlog
	 * 
	 * @param alid
	 *            actionlog的id
	 * @return 查询出来的acitonlog
	 */
	public ActionLog selectByAlid(long alid);

	/**
	 * 查询某个训练中，最后一个训练日志下面的所有动作日志
	 * 
	 * @param pid
	 *            训练id
	 * @return 动作日志集合
	 */
	public List<ActionLog> selectByLastPlanLog(long pid);
}
