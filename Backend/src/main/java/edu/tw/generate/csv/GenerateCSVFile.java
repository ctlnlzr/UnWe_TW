package edu.tw.generate.csv;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.opencsv.CSVWriter;
import edu.tw.contoller.EducationController;
import edu.tw.database.entity.Age;
import edu.tw.database.entity.Education;
import edu.tw.database.entity.Environment;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.database.repository.AgeRepository;
import edu.tw.database.repository.EducationRepository;
import edu.tw.database.repository.EnvironmentRepository;
import edu.tw.database.repository.TotalPerMonthRepository;

import javax.persistence.EntityManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GenerateCSVFile {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    public void allEducation() throws IOException {
        EducationRepository educationRepository=new EducationRepository(entityManager);
        List<Education> all= educationRepository.all();
        CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
        String line1[] = {"Luna","Judetul", "Fara studii", "Primar", "Gimnaziu", "Liceu", "Postliceala", "Profesionala", "Universitate"};
        writer.writeNext(line1);
        String data[] = new String[10];
        for (Education education : all) {
            data[0] = String.valueOf(education.getLuna());
            data[1] = education.getJudet();
            data[2] = String.valueOf(education.getFaraStudii());
            data[3] = String.valueOf(education.getPrimar());
            data[4] = String.valueOf(education.getGimnaziu());
            data[5] = String.valueOf(education.getLiceu());
            data[6] = String.valueOf(education.getPostliceala());
            data[7] = String.valueOf(education.getProfesionala());
            data[8] = String.valueOf(education.getUniversitate());
            writer.writeNext(data);
        }
        writer.flush();
    }
    public  void allAge() throws IOException {
        AgeRepository ageRepository=new AgeRepository(entityManager);
        List<Age> all= ageRepository.all();
        CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
        String[] line1 = {"Luna","Judetul", "Mai mic 25", "Intre 25 si 29", "Intre 30 si 39", "Intre 40 si 49", "Intre 50 si 55", "Peste 55"};
        writer.writeNext(line1);
        String[] data = new String[10];
        for (Age age : all) {
            data[0] = String.valueOf(age.getLuna());
            data[1] = age.getJudet();
            data[2] = String.valueOf(age.getMaiMic25());
            data[3] = String.valueOf(age.getIntre25si29());
            data[4] = String.valueOf(age.getIntre30si39());
            data[5] = String.valueOf(age.getIntre40si49());
            data[6] = String.valueOf(age.getIntre50si55());
            data[7] = String.valueOf(age.getPeste55());
            writer.writeNext(data);
        }
        writer.flush();
    }
    public void allEnvironment() throws IOException {
        EnvironmentRepository ageRepository=new EnvironmentRepository(entityManager);
        List<Environment> all= ageRepository.all();
        CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
        String[] line1 = {"Luna","Judetul", "Femei din mediul urban", "Femei din mediul rural", "Barbati din mediul Urban", "Intre 40 si 49", "Barbati din mediul rural"};
        writer.writeNext(line1);
        String[] data = new String[10];
        for (Environment environment : all) {
            data[0] = String.valueOf(environment.getLuna());
            data[1] = environment.getJudet();
            data[2] = String.valueOf(environment.getFemeiDinMediulUrban());
            data[3] = String.valueOf(environment.getFemeiDinMediulRural());
            data[4] = String.valueOf(environment.getBarbatiDinMediulUrban());
            data[5] = String.valueOf(environment.getBarbatiDinMediulRural());
            writer.writeNext(data);
        }
        writer.flush();
    }
    public void allTotalPerMonth() throws IOException {
        TotalPerMonthRepository totalPerMonthRepository=new TotalPerMonthRepository(entityManager);
        List<TotalPerMonth> all= totalPerMonthRepository.all();
        CSVWriter writer = new CSVWriter(new FileWriter("D://output.csv"));
        String[] line1 = {"Luna","Judetul", "Total", "Rata"};
        writer.writeNext(line1);
        String[] data = new String[10];
        for (TotalPerMonth totalPerMonth : all) {
            data[0] = String.valueOf(totalPerMonth.getLuna());
            data[1] = totalPerMonth.getJudet();
            data[2] = String.valueOf(totalPerMonth.getTotal());
            data[3] = String.valueOf(totalPerMonth.getRata());
            writer.writeNext(data);
        }
        writer.flush();
    }
}
