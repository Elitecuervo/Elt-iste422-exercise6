package oata;

// Import log4j classes.
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.logging.log4j.LogManager;

public class HelloWorld {
  private static final Logger LOGGER = LogManager.getLogger(HelloWorld.class);

  public static void main(String[] args) {
    String w = null;
    try {
      w = causeError2();
    }
    catch (Exception e) {
        LOGGER.error("There was a problem!", e);
    }
    try {
      causeError2();
    }
    catch (Exception e) {
       LOGGER.error("There was a problem!\nPlease fix the code.", e);
    }
    LOGGER.info(w);
  }

  public static String readFile(InputStream fs) throws IOException {
    BufferedReader br = null;
    StringBuilder sb = new StringBuilder();

    String line;
    try {

      br = new BufferedReader(new InputStreamReader(fs));
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }

    } catch (IOException e) {
      LOGGER.error("An IO exception has occured.", e);
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException e) {
          LOGGER.error("An IO exception has occured.", e);
        }
      }
    }

    return sb.toString();
  }
  public static String causeError2() throws IOException {
    StringBuilder sb = new StringBuilder();
    try (FileInputStream fs = (FileInputStream) HelloWorld.class.getClassLoader().getResourceAsStream("myfile")){
      sb.append(readFile(fs));
    }
    catch (IOException e) {
      LOGGER.error("I caught a problem", e);
    }
    return sb.toString();
  }
}
