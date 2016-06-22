package org.muyutu.dao;

import javax.transaction.Transactional;

import org.muyutu.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * A DAO for the entity User is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 * 
 * @author netgloo
 */
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);
  
  /**
   * Return the user having the passed name or null if no user is found.
   * @param name the name of the user
   * @return
   */
  public User findByName(String name);
  
  @Query("select u from User u join Group g on u.groupId = g.id where g.name= ?1 ")
  public Page<User> getUserByGroupName(String groupname, Pageable pageable);

} // class UserDao
