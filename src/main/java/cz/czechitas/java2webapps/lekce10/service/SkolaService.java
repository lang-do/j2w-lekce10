package cz.czechitas.java2webapps.lekce10.service;

import cz.czechitas.java2webapps.lekce10.entity.Rodic;
import cz.czechitas.java2webapps.lekce10.entity.Student;
import cz.czechitas.java2webapps.lekce10.entity.Trida;
import cz.czechitas.java2webapps.lekce10.repository.RodicRepository;
import cz.czechitas.java2webapps.lekce10.repository.StudentRepository;
import cz.czechitas.java2webapps.lekce10.repository.TridaRepository;
import cz.czechitas.java2webapps.lekce10.repository.UcitelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkolaService {
    private final TridaRepository tridaRepository;
    private final StudentRepository studentRepository;
    private final UcitelRepository ucitelRepository;
    private final RodicRepository rodicRepository;

    @Autowired
    public SkolaService(TridaRepository tridaRepository, StudentRepository studentRepository, UcitelRepository ucitelRepository, RodicRepository rodicRepository) {
        this.tridaRepository = tridaRepository;
        this.studentRepository = studentRepository;
        this.ucitelRepository = ucitelRepository;
        this.rodicRepository = rodicRepository;
    }

    public Page<Trida> listTrid() {
        return tridaRepository.findAll(Pageable.unpaged());
    }

    public Trida findTridaById(short id) {
        return tridaRepository.findById(id).get();
    }

    public Page<Rodic> listRodicuStudenta(Integer id) {
        return rodicRepository.findByDetiIdOrderByJmenoAscPrijmeniAsc(id,Pageable.unpaged());
    }

    public Page<Student> listStudentuTridy(short id) {
        return studentRepository.findStudentByTridaIdOrderByPrijmeni(id, Pageable.unpaged());
    }

    public Student findById(Integer id) {
        return studentRepository.findById(id).get();
    }

}