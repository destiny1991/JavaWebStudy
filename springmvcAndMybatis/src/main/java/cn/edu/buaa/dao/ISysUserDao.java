package cn.edu.buaa.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.edu.buaa.entity.SysUser;

@Repository
public interface ISysUserDao {
	
	public void save(SysUser user);
	public SysUser selectById(int id);
	public void deleteById(int id);
	public List<SysUser> selectAll();
	
}
