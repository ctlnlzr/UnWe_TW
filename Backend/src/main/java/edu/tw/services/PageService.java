package edu.tw.services;

import edu.tw.database.entity.Age;
import edu.tw.database.entity.Education;
import edu.tw.database.entity.Environment;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.database.entitymanager.EntityManagerProvider;
import edu.tw.generate.csv.GenerateCSVFile;
import edu.tw.generate.pdf.GeneratePDFFile;
import edu.tw.generate.svg.GenerateSvgFile;

import javax.persistence.EntityManager;
import java.util.List;

public class PageService {
    EntityManager entityManager = EntityManagerProvider.getEntityManagerFactory().createEntityManager();
    EducationService educationService=new EducationService();
  EnvironmentService environmentService=new EnvironmentService();
    TotalPerMonthService totalPerMonthService=new TotalPerMonthService();
    AgeService ageService=new AgeService();
//lunile sunt de la 1 la 15, pentru ultimele 2 luni->16, pt ultimele 6 luni->17,, ultimul an->18
    public boolean criteriaService(String criteria1, String table, String county, String criteria4, String criteria5) {
       if(!check(criteria1, table, county, criteria4, criteria5)){
           return false;
       }
       int month=Integer.parseInt(criteria1);

       if(criteria4==null && criteria5==null && month<16){
           if(table.equals("gen")){
               List<Environment> environments= environmentService.getByMonthAndCounty(month,county);
           }
           else if(table.equals("varsta")){
               List<Age> ages=ageService.getByMonthAndCounty(month, county);

           }
           else if(table.equals("educatie")){
               List<Education> educations=educationService.getByMonthAndCounty(month, county);

           }else {
               List<TotalPerMonth> totalPerMonths=totalPerMonthService.getByMonthAndCounty(month, county);

           }
       }
        if(criteria4==null && criteria5==null && month==16){
            if(table.equals("gen")){
                List<Environment> environments= environmentService.filterByMonth(2, county);
            }
            else if(table.equals("varsta")){
                List<Age> ages=ageService.filterByMonth(2, county);

            }
            else if(table.equals("educatie")){
                List<Education> educations=educationService.filterByMonth(2, county);

            }else {
                List<TotalPerMonth> totalPerMonths=totalPerMonthService.filterByMonth(2, county);

            }
        }
        if(criteria4==null && criteria5==null && month==17){
            if(table.equals("gen")){
                List<Environment> environments= environmentService.filterByMonth(6, county);
            }
            else if(table.equals("varsta")){
                List<Age> ages=ageService.filterByMonth(6, county);

            }
            else if(table.equals("educatie")){
                List<Education> educations=educationService.filterByMonth(6, county);

            }else {
                List<TotalPerMonth> totalPerMonths=totalPerMonthService.filterByMonth(6, county);

            }
        }
        if(criteria4==null && criteria5==null && month==18){
            if(table.equals("gen")){
                List<Environment> environments= environmentService.filterByMonth(12, county);
            }
            else if(table.equals("varsta")){
                List<Age> ages=ageService.filterByMonth(12, county);

            }
            else if(table.equals("educatie")){
                List<Education> educations=educationService.filterByMonth(12, county);

            }else {
                List<TotalPerMonth> totalPerMonths=totalPerMonthService.filterByMonth(12, county);

            }
        }

       return true;
    }

    public boolean downloadCsv(String criteria1, String criteria2, String criteria3, String criteria4, String criteria5) {
        GenerateCSVFile csv = new GenerateCSVFile();
        if(check(criteria1, criteria2, criteria3, criteria4, criteria5)){
            return true;
        }
        return false;
    }

    public boolean downloadPdf(String criteria1, String criteria2, String criteria3, String criteria4, String criteria5) {
        GeneratePDFFile pdf = new GeneratePDFFile();
        if(check(criteria1, criteria2, criteria3, criteria4, criteria5)){
            return true;
        }
        return false;

    }
    public boolean downloadSvg(String criteria1, String criteria2, String criteria3, String criteria4, String criteria5){
        GenerateSvgFile svg=new GenerateSvgFile();
        if(check(criteria1, criteria2, criteria3, criteria4, criteria5)){
            return true;
        }
        return false;
    }
    public boolean check(String criteria1, String criteria2, String criteria3, String criteria4, String criteria5){
        int number = 0;
        if (criteria1 == null) {
            number++;
        }
        if (criteria2 == null) {
            number++;
        }
        if (criteria3 == null) {
            number++;
        }
        if (criteria4 == null) {
            number++;
        }
        if (criteria5 == null) {
            number++;
        }
        if (number > 2) {
            return false;
        }

        return true;
    }
}
