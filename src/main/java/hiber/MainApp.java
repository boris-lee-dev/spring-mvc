package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

       User user1 = new User("Scott", "Greenwood", "scott@mail.com");
       Car car1 = new Car("Audi", 6);
       user1.setCar(car1);


       User user2 = new User("John", "Doe", "john@mail.com");
       Car car2 = new Car("BMW", 5);
       user2.setCar(car2);


       User user3 = new User("Jane", "Smith", "jane@mail.com");
       Car car3 = new Car("Audi", 6);
       user3.setCar(car3);


       User user4 = new User("Alex", "Brown", "alex@mail.com");
       Car car4 = new Car("Mercedes", 3);
       user4.setCar(car4);


       User user5 = new User("Jake", "Lennox", "jake@mail.com");
       Car car5 = new Car("Rolls-Royce", 2);
       user5.setCar(car5);

       userService.add(user1);
       userService.add(user2);
       userService.add(user3);
       userService.add(user4);
       userService.add(user5);


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      context.close();
   }
}
