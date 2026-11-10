package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }



    @Override
    @Transactional(readOnly = true)
    public User getUserByCarModelAndSeries(String model, int series) {
        String hql = "SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series";
        return sessionFactory.getCurrentSession()
                .createQuery(hql, User.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

}
