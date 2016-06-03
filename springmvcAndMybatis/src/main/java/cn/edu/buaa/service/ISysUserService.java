package cn.edu.buaa.service;

import java.util.List;

import cn.edu.buaa.entity.SysUser;

public interface ISysUserService {
	
	public void saveWithJDBC(String uName, int uAge);
	public SysUser selectByIdWithJDBC(int uId);
	public List<SysUser> selectAllWithJDBC();
	public void deleteByIdWithJDBC(int uId);
	
	
	public void saveWithMybatis(String uName, int uAge);
	public SysUser selectByIdWithMybatis(int uId);
	public List<SysUser> selectAllWithMybatis();
	public void deleteByIdWithMybatis(int uId);
	
}
