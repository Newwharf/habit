package com.flowermake.habit.service;

import java.util.List;

import com.flowermake.habit.domain.BodyDataLog;

/**
 * @author lpp
 *
 */
public interface IBodyDataLogService {

	/**
	 * 添加一个身体数据日志
	 * 
	 * @param bodyDataLog
	 *            要添加的身体数据日志
	 * @return 如果添加成，则返回添加成功的id，否则返回-1
	 * @throws Exception
	 */
	public int addBodyDataLog(BodyDataLog bodyDataLog) throws Exception;

	/**
	 * 根据id删除一个身体数据日志
	 * 
	 * @param id
	 *            要删除的日志id
	 * @return 如果删除成功则返回被删除的日志di，否则返回-1
	 * @throws Exception
	 */
	public int deleteBodyDataLogById(long id) throws Exception;

	/**
	 * 修改一个身体数据日志
	 * 
	 * @param bodyDataLog
	 *            要修改的身体数据日志
	 * @return 如果修改成功则返回被删除的日志di，否则返回-1
	 * @throws Exception
	 */
	public int updateBodyDataLog(BodyDataLog bodyDataLog) throws Exception;

	/**
	 * 根据身体数据日志id查询一个身体数据
	 * 
	 * @param id
	 *            身体数据日志id
	 * @return 身体数据日志
	 * @throws Exception
	 */
	public BodyDataLog findBodyDataLogById(long id) throws Exception;

	/**
	 * 根据用户id返回身体数据集合
	 * 
	 * @param id
	 *            用户id
	 * @param m
	 *            从第几条开始读取
	 * @param n
	 *            一共读取多少条
	 * @return 身体数据集合
	 * @throws Exception
	 */
	public List<BodyDataLog> findBodyDataLogByUserId(long id, int m, int n) throws Exception;

	/**
	 * 返回某个用户下最新一条的不同身体指标的记录
	 * 
	 * @param uid
	 *            用户id
	 * @return 不同身体指标记录的集合
	 * @throws Exception
	 */
	public List<BodyDataLog> selectLatelyLog(long uid) throws Exception;

	/**
	 * 查询某个身体指标下的记录
	 * 
	 * @param index
	 *            身体指标标示
	 * @param uid
	 *            用户id
	 * @param m
	 *            分页起始量
	 * @param n
	 *            查询样本量
	 * @return
	 */
	List<BodyDataLog> selectByIndex(byte index, long uid, int m, int n);
}
