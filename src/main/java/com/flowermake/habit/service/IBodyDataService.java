package com.flowermake.habit.service;

import com.flowermake.habit.domain.BodyData;

/**
 * @author lpp
 *
 */
public interface IBodyDataService {

	/**根据用户id查询该用户的身体数据
	 * @param userId 用户id
	 * @return 身体数据对象
	 * @throws Exception
	 */
	public BodyData findBodyDataByUserId(long userId)throws Exception;
	
	/**添加一个身体数据
	 * @param bodyData 要添加的身体数据
	 * @return 添加成功则返回添加成功的ID，否则返回-1
	 * @throws Exception
	 */
	public int addBodyData(BodyData bodyData)throws Exception;
	
	/**修改一个身体数据
	 * @param bodyData 要修改的身体数据
	 * @return 如果修改成功则返回id，否则返回-1
	 * @throws Exception
	 */
	public int updateBodyData(BodyData bodyData)throws Exception;
	
	/**根据用户id查询一个身体数据
	 * @param iUserid
	 * @return
	 * @throws Exception
	 */
	public BodyData selectByUserId(Long iUserid)throws Exception;
	
}
