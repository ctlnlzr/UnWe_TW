package api.Handlers;

import api.JsonModels.AgeJsonModels.AgeGetResponse;
import api.JsonModels.EducationJsonModels.EducationGetResponse;
import api.JsonModels.EnvironmentJsonModels.EnvironmentResponse;
import api.JsonModels.PageJsonModels.Criterion;
import api.JsonModels.PageJsonModels.CriterionResponse;
import api.JsonModels.gender.Gender;
import api.JsonModels.gender.GenderResponse;
import api.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import database.entity.Age;
import database.entity.Education;
import database.entity.Environment;
import database.entity.TotalPerMonth;
import services.AgeService;
import services.EducationService;
import services.EnvironmentService;
import services.TotalPerMonthService;


import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static utils.Utils.splitQuery;

public class PageHandler extends Handler{

    private final EnvironmentService environmentService = new EnvironmentService();
    private final EducationService educationService = new EducationService();
    private final AgeService ageService = new AgeService();
    private final TotalPerMonthService totalService = new TotalPerMonthService();

    public PageHandler(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    protected void execute(HttpExchange exchange) throws Exception {
        byte[] respText;
        if("GET".equals(exchange.getRequestMethod())){
            Map<String, List<String>> params = splitQuery(exchange.getRequestURI().getRawQuery());
            int noOfMonths;
            switch (params.get("monthID").get(0)){
                case "16": noOfMonths = 2;
                    break;
                case "17": noOfMonths=6;
                    break;
                case "18": noOfMonths=12; break;
                default: noOfMonths=0;
            }
            String criterion = "";
            for(Map.Entry<String,List<String>> entry : params.entrySet()){
            if(entry.getValue().contains(null)) { criterion = entry.getKey(); break;}}
            switch (criterion){
                case "gender":
                   ResponseEntity<GenderResponse> entity = doGetByGender(Integer.parseInt(params.get("monthID").get(0)), noOfMonths, params.get("county"));
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                   exchange.sendResponseHeaders(entity.getStatusCode(),0);
                   respText = super.writeResp(entity.getBody());
                   break;
                case "environment":
                    ResponseEntity<EnvironmentResponse> environmentEntity = doGetByEnvironment(Integer.parseInt(params.get("monthID").get(0)), noOfMonths, params.get("county"));
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(environmentEntity.getStatusCode(),0);
                    respText = super.writeResp(environmentEntity.getBody());
                    break;
                case "studies":
                    ResponseEntity<EducationGetResponse> educationEntity = doGetByEducation(Integer.parseInt(params.get("monthID").get(0)), noOfMonths, params.get("county"));
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(educationEntity.getStatusCode(),0);
                    respText = super.writeResp(educationEntity.getBody());
                    break;
                case "age":
                    ResponseEntity<AgeGetResponse> ageEntity = doGetByAge(Integer.parseInt(params.get("monthID").get(0)), noOfMonths, params.get("county"));
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(ageEntity.getStatusCode(),0);
                    respText = super.writeResp(ageEntity.getBody());
                    break;
                default:
                    ResponseEntity<CriterionResponse> responseEntity = doGetByCriterion(Integer.parseInt(params.get("monthID").get(0)), noOfMonths, criterion, params.get("county"));
                    exchange.getResponseHeaders().putAll(super.getHeaders());
                    exchange.sendResponseHeaders(responseEntity.getStatusCode(),0);
                    respText = super.writeResp(responseEntity.getBody());
               }
        } else {
            exchange.getResponseHeaders().putAll(super.getHeaders());
            exchange.sendResponseHeaders(400,0);
            respText = "Method not allowed".getBytes();
        }
        OutputStream os = exchange.getResponseBody();
        os.write(respText);
        os.close();

    }

    private ResponseEntity<CriterionResponse> doGetByCriterion(int month, int noOfMonths, String crit, List<String> counties) {
        CriterionResponse response = new CriterionResponse();
        response.setGroups(new ArrayList<>());
            if (month >= 16)
                for (int i = 1; i <= noOfMonths; i++) {
                    response.addGroups(getResponse(counties, crit, i));
                }
            else response.addGroups(getResponse(counties, crit, month));
        return new ResponseEntity<>(response, 200);
    }

    private List<Criterion> getResponse(List<String> counties, String crit, int month) {
     List<Criterion> response = new ArrayList<>();
    for (String county : counties) {
            switch (crit) {
                case "total":
                    List<TotalPerMonth> listTotal;
                    if(county.equals("entire")) listTotal = totalService.getByMonth(month);
                    else listTotal = totalService.getByMonthAndCounty(month, county);
                    for (TotalPerMonth total : listTotal) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(total.getLuna());
                        criterion.setCounty(total.getJudet());
                        criterion.setNumber(total.getTotal());
                        response.add(criterion);}
                    break;
                case "female":
                    List<Environment> list;
                    if(county.equals("entire")) list = environmentService.getByMonth(month);
                    else list = environmentService.getByMonthAndCounty(month, county);
                    for (Environment env : list) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(env.getLuna());
                        criterion.setCounty(env.getJudet());
                        criterion.setNumber(env.getFemeiDinMediulRural() + env.getFemeiDinMediulUrban());
                        response.add(criterion);}
                    break;
                case "male":
                    List<Environment> listMale;
                    if(county.equals("entire")) listMale = environmentService.getByMonth(month);
                    else listMale = environmentService.getByMonthAndCounty(month, county);
                    for (Environment env : listMale) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(env.getLuna());
                        criterion.setCounty(env.getJudet());
                        criterion.setNumber(env.getBarbatiDinMediulRural() + env.getBarbatiDinMediulUrban());
                        response.add(criterion);}
                    break;
                case "under25":
                    List<Age> listUnder;
                    if(county.equals("entire")) listUnder = ageService.getByMonth(month);
                    else listUnder = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listUnder) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getMaiMic25());
                        response.add(criterion);}
                    break;
                case "between25and29":
                    List<Age> listBtwn2;
                    if(county.equals("entire")) listBtwn2 = ageService.getByMonth(month);
                    else listBtwn2 = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listBtwn2) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getIntre25si29());
                        response.add(criterion);}
                    break;
                case "between30and39":
                    List<Age> listBtwn3;
                    if(county.equals("entire")) listBtwn3 = ageService.getByMonth(month);
                    else listBtwn3 = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listBtwn3) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getIntre30si39());
                        response.add(criterion);}
                    break;

                case "between40and49":
                    List<Age> listBtwn4;
                    if(county.equals("entire")) listBtwn4 = ageService.getByMonth(month);
                    else listBtwn4 = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listBtwn4) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getIntre40si49());
                        response.add(criterion);}
                    break;
                case "between50and55":
                    List<Age> listBtwn5;
                    if(county.equals("entire")) listBtwn5 = ageService.getByMonth(month);
                    else listBtwn5 = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listBtwn5) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getIntre50si55());
                        response.add(criterion);}
                    break;
                case "over50":
                    List<Age> listOver;
                    if(county.equals("entire")) listOver = ageService.getByMonth(month);
                    else listOver = ageService.getByMonthAndCounty(month, county);
                    for (Age age : listOver) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(age.getLuna());
                        criterion.setCounty(age.getJudet());
                        criterion.setNumber(age.getPeste55());
                        response.add(criterion);}
                    break;
                case "noStudies":
                    List<Education> listNoStud;
                    if(county.equals("entire")) listNoStud = educationService.getByMonth(month);
                    else  listNoStud = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listNoStud) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getFaraStudii());
                        response.add(criterion);}
                    break;
                case "primarySchool":
                    List<Education> listPrim;
                    if(county.equals("entire")) listPrim = educationService.getByMonth(month);
                    else listPrim = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listPrim) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getPrimar());
                        response.add(criterion);}
                    break;
                case "secondarySchool":
                    List<Education> listSecondary;
                    if(county.equals("entire")) listSecondary = educationService.getByMonth(month);
                    else listSecondary = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listSecondary) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getGimnaziu());
                        response.add(criterion);}
                    break;
                case "highschool":
                    List<Education> listHigh;
                    if(county.equals("entire")) listHigh = educationService.getByMonth(month);
                    else listHigh = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listHigh) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getLiceu());
                        response.add(criterion);}
                    break;
                case "postsecondarySchool":
                    List<Education> listPost;
                    if(county.equals("entire")) listPost = educationService.getByMonth(month);
                    else listPost = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listPost) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getPostliceala());
                        response.add(criterion);}
                    break;
                case "vocationalEducation":
                    List<Education> listVocational;
                    if(county.equals("entire")) listVocational = educationService.getByMonth(month);
                    else listVocational = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listVocational) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getProfesionala());
                        response.add(criterion);}
                    break;
                case "universityEducation":
                    List<Education> listUni;
                    if(county.equals("entire")) listUni = educationService.getByMonth(month);
                    else listUni = educationService.getByMonthAndCounty(month, county);
                    for (Education edu : listUni) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(edu.getLuna());
                        criterion.setCounty(edu.getJudet());
                        criterion.setNumber(edu.getUniversitate());
                        response.add(criterion);}
                    break;
                case "urban":
                    List<Environment> listUrban;
                    if(county.equals("entire")) listUrban = environmentService.getByMonth(month);
                    else listUrban = environmentService.getByMonthAndCounty(month, county);
                    for (Environment env : listUrban) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(env.getLuna());
                        criterion.setCounty(env.getJudet());
                        criterion.setNumber(env.getBarbatiDinMediulUrban() + env.getFemeiDinMediulUrban());
                        response.add(criterion);}
                    break;
                case "rural":
                    List<Environment> listRural;
                    if(county.equals("entire")) listRural = environmentService.getByMonth(month);
                    else listRural = environmentService.getByMonthAndCounty(month, county);
                    for (Environment env : listRural) {
                        Criterion criterion = new Criterion();
                        criterion.setMonth(env.getLuna());
                        criterion.setCounty(env.getJudet());
                        criterion.setNumber(env.getBarbatiDinMediulRural() + env.getFemeiDinMediulRural());
                        response.add(criterion);}
                    break;
                default:
                    Criterion criterion = new Criterion();
                    criterion.setMonth(0);
                    criterion.setCounty("Not found");
                    criterion.setNumber(0);
                    response.add(criterion);

            }
        }
        return response;
    }
    private List<Gender> fromEnvToGender (List<Environment> environments){
        List<Gender> genderList = new ArrayList<>();
        for (Environment environment :environments){
            Gender gender = new Gender();
            gender.setFemale(environment.getFemeiDinMediulRural() + environment.getFemeiDinMediulUrban());
            gender.setMale(environment.getBarbatiDinMediulRural()+environment.getBarbatiDinMediulUrban());
            gender.setCounty(environment.getJudet());
            gender.setMonth(environment.getLuna());
            genderList.add(gender);
        }
        return genderList;
    }
   private ResponseEntity<GenderResponse> doGetByGender(int month, int noOfMonths,  List<String> counties){
        GenderResponse response = new GenderResponse();
        response.setGroups(new ArrayList<>());
        if(month>=16){
            for(int i = 1; i <=noOfMonths; i++){
                for (String county: counties) {
                    response.addGroups(fromEnvToGender(environmentService.getByMonthAndCounty(i,county)));
                }
            }
        } else{
            for (String county: counties) {
                response.addGroups(fromEnvToGender(environmentService.getByMonthAndCounty(month,county)));
            }
        }
        return new ResponseEntity<>(response, 200 );
    }


    ResponseEntity<EnvironmentResponse> doGetByEnvironment(int month, int noOfMonths, List<String> counties){
        EnvironmentResponse response = new EnvironmentResponse();
        response.setGroups(new ArrayList<>());
        if(month>=16){
            for(int i = 1; i <=noOfMonths; i++){
                for (String county: counties) {
                    response.addGroups(environmentService.getByMonthAndCounty(i,county));
                }
            }
        } else{
            for (String county: counties) {
                response.addGroups(environmentService.getByMonthAndCounty(month,county));
            }
        }
        return new ResponseEntity<>(response, 200 );
    }
   private ResponseEntity<EducationGetResponse> doGetByEducation(int month, int noOfMonths, List<String> counties){
       EducationGetResponse response = new EducationGetResponse();
       response.setGroups(new ArrayList<>());
       if(month>=16){
           for(int i = 1; i <=noOfMonths; i++){
               for (String county: counties) {
                   response.addGroups(educationService.getByMonthAndCounty(i,county));
               }
           }
       } else{
           for (String county: counties) {
               response.addGroups(educationService.getByMonthAndCounty(month,county));
           }
       }
       return new ResponseEntity<>(response, 200 );
   }

   private ResponseEntity<AgeGetResponse> doGetByAge(int month,int noOfMonths, List<String> counties){
       AgeGetResponse response = new AgeGetResponse();
       response.setGroups(new ArrayList<>());
       if(month>=16){
           for(int i = 1; i <=noOfMonths; i++){
               for (String county: counties) {
                   response.addGroups(ageService.getByMonthAndCounty(i,county));
               }
           }
       } else{
           for (String county: counties) {
               response.addGroups(ageService.getByMonthAndCounty(month,county));
           }
       }
       return new ResponseEntity<>(response, 200 );
   }
}
