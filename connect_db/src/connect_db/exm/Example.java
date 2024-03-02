package connect_db.exm;

/*
 * 
 */
public class Example {

  public static void main(String[] args) {

    System.out.println("El resultado es " + consultarPlanRecientePorAsoNumint(1L));
  }

  public static Integer consultarPlanRecientePorAsoNumint(Long numint) {
    
    try {

      return (Integer) numbers();

    } catch (RuntimeException e) {

      throw e;
    }
  }

  private static Object numbers() {
    return 999999;
  }
}
