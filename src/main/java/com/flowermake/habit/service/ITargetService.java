package com.flowermake.habit.service;

import java.util.Date;
import java.util.List;


import com.flowermake.habit.domain.Target;

/**
 * 集成
 * 
 * @author lpp
 *
 */
public interface ITargetService {

	/**
	 * 根据用户id查询该用户下所有的目标
	 * 
	 * @param uid
	 *            用户id
	 * @param m
	 *            从第几条开始读取
	 * @param n
	 *            一共读取多少条
	 * @return 目标集合
	 * @throws Exception
	 */
	public List<Target> findTargetsByUserId(long uid, int m, int n) throws Exception;

	/**
	 * 根据用户id查询统计目标总数
	 * 
	 * @param uid
	 *            用户id
	 * @return 目标总数
	 * @throws Exception
	 */
	public int countTargetByParams(long uid) throws Exception;

	/**
	 * 添加一个目标
	 * 
	 * @param target
	 *            要添加的目标对象
	 * @return 如果添加成功，则返回新增目标id，否则返回-1
	 * @throws Exception
	 */
	public int addTarget(Target target) throws Exception;

	/**
	 * 修改一个目标
	 * 
	 * @param target
	 *            要修改的目标
	 * @return 如果修改成功则修改目标的目标id，否则返回-1
	 * @throws Exception
	 */
	public int updateTarget(Target target) throws Exception;

	/**
	 * 删除一个目标（逻辑删除）
	 * 
	 * @param id
	 *            要删除的目标id
	 * @return 删除成功则返回目标id，否则返回-1
	 * @throws Exception
	 */
	public int deleteTargetById(long id) throws Exception;

	/**
	 * 根据目标创建时间范围查询符合条件的目标，返回符合条件的目标集合，并先按照目标状态从未完成到已完成排序，再按照创建时间从近到远排序
	 * 
	 * @param uid
	 *            用户id，指定查询哪个用户的目标
	 * @param startCdt
	 *            时间范围条件中的开始时间，关系是大于等于
	 * @param endCdt
	 *            时间范围条件中的结束时间，关系是小于等于
	 * @param start
	 *            limit参数中的第一个参数
	 * @param end
	 *            limit参数中的第二个参数
	 * @return 符合条件的目标集合
	 * @throws Exception
	 */
	public List<Target> findTargetByDt(long uid, Date startCdt, Date endCdt, int start, int end) throws Exception;

	/**
	 * 根据目标类型以及完成情况查询某个用户的目标
	 * 
	 * @param uid
	 *            用户id
	 * @param index
	 *            目标类型
	 * @param flag
	 *            完成情况
	 * @param start
	 *            limit参数中的第一个参数
	 * @param end
	 *            limit参数中的第二个参数
	 * @return 符合条件的目标集合
	 * @throws Exception
	 */
	public List<Target> findeTargetByIndex(long uid, byte index, byte flag, int start, int end) throws Exception;

	/**
	 * 验证一个需要新增的目标
	 * @param target 新增的目标
	 * @return 验证成功返回“success”，否则返回错误原因
	 * @throws Exception
	 */
	public String validateTargt(Target target) throws Exception;
}
