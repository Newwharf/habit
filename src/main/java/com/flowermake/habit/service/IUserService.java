package com.flowermake.habit.service;

import com.flowermake.habit.domain.User;

/**
 * @author lpp
 *
 */
public interface IUserService {

	/**
	 * 注册一个用户
	 * 
	 * @param user
	 *            要注册的用户对象
	 * @return 如果注册成功，则返回该用户id，否则返回-1
	 * @throws Exception
	 */
	public int addUser(User user) throws Exception;

	// /**根据用户微信id查询用户
	// * @param wid 用户微信id
	// * @return 查询出来的用户对象
	// * @throws Exception
	// */
	// public User findUserByWid(String wid) throws Exception;

	/**
	 * 根据用户id查询用户
	 * 
	 * @param id
	 *            用户id
	 * @return 查询出来的用户对象
	 * @throws Exception
	 */
	public User findUserById(long id) throws Exception;

	/**
	 * 修改一个用户
	 * 
	 * @param user
	 *            要修改的用户
	 * @return 如果修改成功，返回修改的用户id，否则返回-1
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception;

	/**
	 * 根据设备唯一标示获取一个用户
	 * 
	 * @param did
	 *            设备唯一标示
	 * @return 用户
	 * @throws Exception
	 */
	public User selectByDid(String did) throws Exception;

	/**
	 * 根据设备唯一标示注册一个新用户，注册的新用户将默认设置其个人信息及身体信息
	 * 
	 * @param deviceid
	 *            设备唯一标示
	 * @return 0失败，1成功
	 * @throws Exception
	 */
	public User register(String deviceid) throws Exception;

}
