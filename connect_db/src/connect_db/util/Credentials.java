package connect_db.util;

/**
 * @author Motor-APP
 */
public enum Credentials {

  USERNAME("root"), PASSWORD("1706"), HOST("localhost"), PORT("3306"), DATABASE("pruebaCRUD");

  private String value;

  public String getValue() {
    return value;
  }

  Credentials(String value) {
    this.value = value;
  }
}
