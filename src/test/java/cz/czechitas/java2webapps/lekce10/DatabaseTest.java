package cz.czechitas.java2webapps.lekce10;

import cz.czechitas.java2webapps.lekce10.entity.Rodic;
import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.entity.Ucitel;
import cz.czechitas.java2webapps.lekce10.repository.RodicRepository;
import cz.czechitas.java2webapps.lekce10.repository.StudentRepository;
import cz.czechitas.java2webapps.lekce10.repository.TridaRepository;
import cz.czechitas.java2webapps.lekce10.repository.UcitelRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Třída pro testování přístupu do databáze.
 */
@SpringBootTest
public class DatabaseTest {
  private final Logger logger = LoggerFactory.getLogger(DatabaseTest.class);

  private final RodicRepository rodicRepository;
  private final StudentRepository studentRepository;
  private final TridaRepository tridaRepository;
  private final UcitelRepository ucitelRepository;

  @Autowired
  public DatabaseTest(RodicRepository rodicRepository, StudentRepository studentRepository, TridaRepository tridaRepository, UcitelRepository ucitelRepository) {
    this.rodicRepository = rodicRepository;
    this.studentRepository = studentRepository;
    this.tridaRepository = tridaRepository;
    this.ucitelRepository = ucitelRepository;
  }

  @Test
  @Transactional
  void mujTest() {
    Student student = studentRepository.getOne(1);
    logger.debug("Našel jsem studenta: {}", student);
    logger.debug("Chodí do třídy: {}", student.getTrida());
    for (Rodic rodic: student.getRodice()) {
      logger.debug("Jeho rodič: {}", rodic);
    }
  }

  @Test
  @Transactional
  void testStudent() {
    Student student = studentRepository.getOne(1);
    logger.debug("Student s ID=1: {}", student);
    assertAll(
            () -> assertEquals(1, student.getId()),
            () -> assertEquals("Michal", student.getJmeno()),
            () -> assertEquals("Kubát", student.getPrijmeni())
    );

    assertEquals(2, student.getRodice().size());
    for (Rodic rodic : student.getRodice()) {
      logger.debug("Rodič: {}", rodic);
    }

    Trida trida = student.getTrida();
    logger.debug("Třída: {}", trida);
    assertEquals("1.A", trida.getNazev());
  }

  @Test
  @Transactional
  void testTrida() {
    Trida trida = tridaRepository.getOne((short) 1);
    logger.debug("Třída s ID=1: {}", trida);

    Ucitel ucitel = trida.getTridniUcitel();
    logger.debug("Třídní učitel: {}", ucitel);

    List<Student> studenti = trida.getStudenti();
    for (Student student : studenti) {
      logger.debug("Student ve třídě: {}", student);
    }
  }

  @Test
  @Transactional
  void testUcitel() {
    Ucitel ucitel = ucitelRepository.getOne(1);
    logger.debug("Učitel s ID=1: {}", ucitel);

    Trida trida = ucitel.getTrida();
    logger.debug("Je třídní ve třídě: {}", trida);
  }

  @Test
  @Transactional
  void testUcitelNeniTridni() {
    Ucitel ucitel = ucitelRepository.getOne(20);
    logger.debug("Učitel s ID=20: {}", ucitel);

    Trida trida = ucitel.getTrida();
    assertNull(trida);
  }

  @Test
  @Transactional
  void testRodic() {
    Rodic rodic = rodicRepository.getOne(1);
    logger.debug("Rodič s ID=1: {}", rodic);

    List<Student> deti = rodic.getDeti();
    for (Student dite : deti) {
      logger.debug("Dítě: {}", dite);
    }
    assertEquals(1, deti.size());
  }

  @Test
  @Transactional
  void testRodicDveDeti() {
    Rodic rodic = rodicRepository.getOne(35);
    logger.debug("Rodič s ID=1: {}", rodic);

    List<Student> deti = rodic.getDeti();
    for (Student dite : deti) {
      logger.debug("Dítě: {}", dite);
    }
    assertEquals(2, deti.size());
  }
}
