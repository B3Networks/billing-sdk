package sdk;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan(basePackages = {"sdk"})
public class Main implements CommandLineRunner{
  
  @Autowired
  private ApplicationContext applicationContext;
  
  private static final int COMMAND_ID_INDEX = 0;
  public static void main(String []args) {
    
      try {
        SpringApplication app = new SpringApplication(Main.class);
        app.setWebEnvironment(false);
        app.run(args).close(); // close after running
    } catch (Throwable throwable) {
        
    }
    System.exit(0);
  }  

  @Override
  public void run(String... args) throws Exception {
      
      try {
          if (args.length == 0) {
            throw new IllegalArgumentException("The job name is missing. The job name is the first argument you pass into the Main class.");
          }
          String commandId = args[COMMAND_ID_INDEX];
          List<String> argumentList = new ArrayList<>(Arrays.asList(args));
          argumentList.remove(COMMAND_ID_INDEX);
          Command command = applicationContext.getBean(commandId, Command.class);

          command.execute(argumentList);
          
      } catch (NoSuchBeanDefinitionException ex) {
         System.out.println( ex) ;
      } catch (Throwable ex) {
          
      }
      
  }
}

