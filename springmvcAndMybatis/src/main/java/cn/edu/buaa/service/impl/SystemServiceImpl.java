package cn.edu.buaa.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.buaa.dao.ISysUserDao;
import cn.edu.buaa.entity.SysUser;
import cn.edu.buaa.service.ISysUserService;

@Service
public class SystemServiceImpl implements ISysUserService {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Resource
	private ISysUserDao dao;
	
	@Transactional
	@Override
	public void saveWithJDBC(String uName, int uAge) {
		final String sql = "insert into sys_user(uName, uAge) values (?, ?)";
//		jdbcTemplate.update(sql, new Object[]{uName, uAge});
		
		KeyHolder key = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement(sql);
				statement.setString(1, uName);
				statement.setInt(2, uAge);
				return statement;
			}
		}, key);
		
		// 输出主键
		System.out.println(key.getKey().intValue());
	}

	@Override
	public SysUser selectByIdWithJDBC(int uId) {
		String sql = "select * from sys_user where uId=?";
		SysUser user = new SysUser();
		jdbcTemplate.query(sql, new Object[]{uId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setuName(rs.getString("uName"));
				user.setuAge(rs.getInt("uAge"));
			}
		});
		user.setuId(uId);
		return user;
	}

	@Override
	public List<SysUser> selectAllWithJDBC() {
		String sql = "select * from sys_user";
		List<SysUser> userList = new ArrayList<>();
		jdbcTemplate.query(sql, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				SysUser user = new SysUser();
				user.setuName(rs.getString("uName"));
				user.setuAge(rs.getInt("uAge"));
				user.setuId(rs.getInt("uId"));
				userList.add(user);
			}
		});
		return userList;
	}
	
	@Transactional
	@Override
	public void deleteByIdWithJDBC(int uId) {
		String sql = "delete from sys_user where uId=?";
		jdbcTemplate.update(sql, uId);
	}

	
	@Transactional
	@Override
	public void saveWithMybatis(String uName, int uAge) {
		SysUser user = new SysUser();
		user.setuName(uName);
		user.setuAge(uAge);
		dao.save(user);
	}

	@Override
	public SysUser selectByIdWithMybatis(int uId) {
		return dao.selectById(uId);
	}

	@Override
	public List<SysUser> selectAllWithMybatis() {
		return dao.selectAll();
	}

	@Transactional
	@Override
	public void deleteByIdWithMybatis(int uId) {
		dao.deleteById(uId);
	}
	
}







