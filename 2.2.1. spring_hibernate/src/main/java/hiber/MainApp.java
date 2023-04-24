package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import jakarta.persistence.NoResultException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      User Uri = new User("Юрий", "Иванов", "uriivanov@gmail.com");
      Uri.setCarOfUser(new Car("kia", 8762));
      User Polina = new User("Полина", "Феоктистова", "pollyFFF@gmail.com");
      Polina.setCarOfUser(new Car("lada kalina", 3280));
      User Shasha = new User("Саша", "Сабитовкин", "fl0werd@gmail.com");
      Shasha.setCarOfUser(new Car("kia", 9999));

      userService.add(Uri);
      userService.add(Polina);
      userService.add(Shasha);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         if(user.getCarOfUser() != null) {
            System.out.println("modelOfCar = " + user.getCarOfUser().getModel());
            System.out.println("seriesOfCar = " + user.getCarOfUser().getSeries());
            System.out.println();
         }
      }
      try {System.out.println(userService.getUserByCarCharacter("kia",9999).toString());
      } catch (NoResultException e) {
         System.out.println("Пользователя с такой машиной не существует");
      }

      context.close();
   }
}
