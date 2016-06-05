package cn.edu.buaa.test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import cn.edu.buaa.map.InterfaceJikeUserMap;
import cn.edu.buaa.pojo.Author;
import cn.edu.buaa.pojo.JikeUser;
import cn.edu.buaa.pojo.Visit;

public class TestMybatis {

	public static void test1() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();
			JikeUser user = session.selectOne("findById", 1);

			System.out.println("查询结果：" + user.getUserName() + " " + user.getPassword());

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testInsert() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			JikeUser user = new JikeUser();
			user.setUserName("honghong");
			user.setPassword("9878");

			session.insert("insertUser", user);

			session.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testUpdate() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			JikeUser user = new JikeUser();
			user.setId(3);
			user.setUserName("lvlv");
			user.setPassword("6666");

			session.update("updateUser", user);
			session.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testDelete() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			InterfaceJikeUserMap iMap = session.getMapper(InterfaceJikeUserMap.class);
			iMap.deleteUser(3);

			session.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testLoginSelect() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			Map<String, String> map = new HashMap<>();
			map.put("userName", "xiaoming");
			map.put("password", "123");

			JikeUser user = session.selectOne("loginSelect", map);
			if (user != null) {
				System.out.println("登录成功！");
			}

			session.commit();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testResultMap() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			List<JikeUser> users = session.selectList("selectUsers");
			for (JikeUser user : users) {
				System.out.println(user);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testInsertAuthor() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession(false);

			JikeUser one = new JikeUser();
			one.setUserName("author001");
			one.setPassword("123456");
			session.insert("insertUser", one);
			System.out.println("新加入的user id = " + one.getId());

			Author at = new Author();
			at.setJikeUser(one);
			at.setRealName("yisi");
			at.setIDCard("4211231");
			session.insert("insertAuthor", at);
			session.commit();// 事务提交

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testSelectAuthorJoin() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			List<Author> ap = session.selectList("selectAuthorJoin"); // 联合查询
			for (Author temp : ap) {
				System.out
						.println("作者真实姓名 = " + temp.getRealName() + "   对应的用户名 = " + temp.getJikeUser().getUserName());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testSelectAuthor() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			List<Author> ap = session.selectList("selectAuthor"); // 子查询
			for (Author temp : ap) {
				System.out
						.println("作者真实姓名 = " + temp.getRealName() + "   对应的用户名 = " + temp.getJikeUser().getUserName());
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void testCollection() {
		String src = "MyBatisConfig.xml";
		Reader reader = null;
		SqlSession session = null;
		try {
			reader = Resources.getResourceAsReader(src);
			SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
			session = sqlMapper.openSession();

			List<JikeUser> ap = session.selectList("selectVisit"); // 子查询
			for (JikeUser temp : ap) {
				System.out.println("用户名=" + temp.getUserName());
				for (Visit oneVisit : temp.getVisitList()) {
					System.out.println("登录时间=" + oneVisit.getVisitDate() + "登录IP=" + oneVisit.getVisitIP());
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}

			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {

		// test1();

		// testInsert();

		// testUpdate();

		// testDelete();

		// testLoginSelect();

		// testResultMap();

		// testInsertAuthor();

		// testSelectAuthorJoin();

		// testSelectAuthor();

		testCollection();

	}

}
