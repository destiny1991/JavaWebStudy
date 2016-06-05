package cn.edu.buaa.map;

import org.apache.ibatis.annotations.Delete;

// 接口的方式尽量不要使用，对复杂的操作有一定的局限性
public interface InterfaceJikeUserMap {
	
	@Delete("delete from jikeUser where id=#{id}")
	public void deleteUser(Integer id); 
	
}
