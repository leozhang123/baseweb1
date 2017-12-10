/**
 * 
 * Create on 2016年12月1日
 */
package org.zl.jpa;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.zl.dao.hibernate.model.ZUser;

/**
 * @author Leo
 * @version 0.0.1
 */
public interface UserRepository extends JpaRepository<ZUser, String> {

	@Query("select t from ZUser t JOIN t.wxUsers w where w.openid=:openid")
	ZUser findUserByWXOpenid(@Param("openid") String openid);
	
	@Query("select t from ZUser t where t.idcard=?1")
	ZUser findUserByIDcard(String idCard);
}
