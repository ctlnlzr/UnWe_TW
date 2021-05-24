package edu.tw.services;

import com.google.gson.Gson;
import edu.tw.database.entity.Age;
import edu.tw.database.entity.Education;
import edu.tw.database.entity.Environment;
import edu.tw.database.entity.TotalPerMonth;
import edu.tw.generate.csv.GenerateCSVFile;
import edu.tw.generate.pdf.GeneratePDFFile;
import edu.tw.generate.svg.GenerateSvgFile;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PageService {
    EducationService educationService = new EducationService();
    EnvironmentService environmentService = new EnvironmentService();
    TotalPerMonthService totalPerMonthService = new TotalPerMonthService();
    AgeService ageService = new AgeService();

    //lunile sunt de la 1 la 15, pentru ultimele 2 luni->16, pt ultimele 6 luni->17,, ultimul an->18
    //pot exista mai multe judete si le despartim prin ","
    public Object criteriaService(int month, String table, String county) throws JSONException {
        JSONObject json = new JSONObject();
        String[] counties = county.split(",");
        if (month < 16) {
            switch (table) {
                case "gen":
                    List<Environment> environments = environmentService.getByMonthAndCounty(month, county);
                    if (counties.length < 2) {
                        String gen = new Gson().toJson(environments);
                        json.put("luna", month).put("judet", county);
                        String[] pack = gen.split(",");
                        String[] pack2 = pack[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "femeiDinMediulUrban");
                        jo.put("numar", pack2[1]);
                        pack2 = pack[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "femeiDinMediulRural");
                        jo2.put("numar", pack2[1]);
                        pack2 = pack[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "barbatiDinMediulUrban");
                        jo3.put("numar", pack2[1]);
                        pack2 = pack[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "barbatiDinMediulRural");
                        String[] pac = pack2[1].split("}");
                        jo4.put("numar", pac[0]);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2).put(jo3).put(jo4);
                        json.put("criterii", ja);
                        return json;
                    }
                    return environments;


                case "varsta":
                    if (counties.length < 2) {
                        List<Age> ages = ageService.getByMonthAndCounty(month, county);
                        String age = new Gson().toJson(ages);
                        json.put("luna", month).put("judet", county);
                        String[] pack = age.split(",");
                        String[] pack2 = pack[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "maiMic25");
                        jo.put("numar", pack2[1]);
                        pack2 = pack[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "intre25Si29");
                        jo2.put("numar", pack2[1]);
                        pack2 = pack[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "intre30Si39");
                        jo3.put("numar", pack2[1]);
                        pack2 = pack[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "intre40Si59");
                        jo4.put("numar", pack2[1]);
                        pack2 = pack[6].split(":");
                        JSONObject jo5 = new JSONObject();
                        jo5.put("nume", "intre50Si55");
                        jo5.put("numar", pack2[1]);
                        pack2 = pack[7].split(":");
                        JSONObject jo6 = new JSONObject();
                        jo6.put("nume", "peste55");
                        String[] pac = pack2[1].split("}");
                        jo6.put("numar", pac[0]);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2).put(jo3).put(jo4).put(jo5).put(jo6);
                        json.put("criterii", ja);
                        return json;
                    } else {
                        return countiesOneMonth(month, table, county);
                    }

                case "educatie":
                    List<Education> educations = educationService.getByMonthAndCounty(month, county);
                    if (counties.length < 2) {
                        String educatie = new Gson().toJson(educations);
                        json.put("luna", month).put("judet", county);
                        String[] pack = educatie.split(",");
                        String[] pack2 = pack[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "faraStudii");
                        jo.put("numar", pack2[1]);
                        pack2 = pack[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "invatamantPrimar");
                        jo2.put("numar", pack2[1]);
                        pack2 = pack[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "invatamantGimnazial");
                        jo3.put("numar", pack2[1]);
                        pack2 = pack[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "invatamantLiceal");
                        jo4.put("numar", pack2[1]);
                        pack2 = pack[6].split(":");
                        JSONObject jo5 = new JSONObject();
                        jo5.put("nume", "invatamantPostliceal");
                        jo5.put("numar", pack2[1]);
                        pack2 = pack[7].split(":");
                        JSONObject jo6 = new JSONObject();
                        jo6.put("nume", "invatamantProfesional");
                        jo6.put("numar", pack2[1]);
                        pack2 = pack[8].split(":");
                        JSONObject jo7 = new JSONObject();
                        jo7.put("nume", "invatamantUniversitar");
                        String[] pac = pack2[1].split("}");
                        jo7.put("numar", pac[0]);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2).put(jo3).put(jo4).put(jo5).put(jo6).put(jo7);
                        json.put("criterii", ja);
                        return json;
                    }
                    return json;

                case "total":
                    List<TotalPerMonth> totalPerMonths = totalPerMonthService.getByMonthAndCounty(month, county);
                    if (counties.length < 2) {
                        String total = new Gson().toJson(totalPerMonths);
                        json.put("luna", month).put("judet", county);
                        String[] pack = total.split(",");
                        String[] pack2 = pack[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "total");
                        jo.put("numar", pack2[1]);
                        pack2 = pack[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "rata");
                        String[] pac = pack2[1].split("}");
                        jo2.put("numar", pac[0]);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2);
                        json.put("criterii", ja);
                        return json;
                    }
                    return totalPerMonths;
                default:
                    return countiesOneMonth(month, table, county);
            }
        }
        if (month == 16) {
            switch (table) {
                case "gen":
                    List<Environment> environmentss = environmentService.filterByMonth(2, county);
                    if (counties.length < 2) {
                        List<Environment> environments = environmentService.getByMonthAndCounty(1, county);
                        List<Environment> environments2 = environmentService.getByMonthAndCounty(2, county);
                        String gen = new Gson().toJson(environments);
                        String gen2 = new Gson().toJson(environments2);
                        json.put("luna", month).put("judet", county);
                        String[] pack = gen.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = gen2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]);
                        jo.put("nume", "femeiDinMediulUrban");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]);
                        jo2.put("nume", "femeiDinMediulRural");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "barbatiDinMediulUrban");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        ja4.put(pac[0]).put(pac2[0]);
                        return getObject(json, jo, jo2, jo3, jo4, ja4);
                    }
                    return environmentss;
                case "varsta":
                    List<Age> agess = ageService.filterByMonth(2, county);
                    if (counties.length < 2) {
                        List<Age> ages = ageService.getByMonthAndCounty(1, county);
                        List<Age> ages2 = ageService.getByMonthAndCounty(2, county);
                        String age = new Gson().toJson(ages);
                        String age2 = new Gson().toJson(ages2);
                        json.put("luna", month).put("judet", county);
                        String[] pack = age.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = age2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]);
                        jo.put("nume", "maiMic25");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]);
                        jo2.put("nume", "intre25Si29");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]);
                        jo3.put("nume", "intre30Si39");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]);
                        jo4.put("nume", "intre40Si59");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        JSONObject jo5 = new JSONObject();
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]);
                        jo5.put("nume", "intre50Si55");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        JSONArray ja6 = new JSONArray();
                        ja6.put(pac[0]).put(pac2[0]);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, ja6);
                    }
                    return agess;

                case "educatie":
                    List<Education> educationss = educationService.filterByMonth(2, county);
                    if (counties.length < 2) {
                        List<Education> educations = educationService.getByMonthAndCounty(1, county);
                        List<Education> educations2 = educationService.getByMonthAndCounty(2, county);
                        String educatie = new Gson().toJson(educations);
                        String educatie2 = new Gson().toJson(educations2);
                        // System.out.println(educatie);
                        json.put("luna", month).put("judet", county);
                        String[] pack = educatie.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = educatie2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "faraStudii");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]);
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]);
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "invatamantPrimar");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "invatamantGimnazial");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]);
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "invatamantLiceal");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]);
                        JSONObject jo5 = new JSONObject();
                        jo5.put("nume", "invatamantPostliceal");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        JSONArray ja6 = new JSONArray();
                        ja6.put(pack2[1]).put(pack4[1]);
                        JSONObject jo6 = new JSONObject();
                        jo6.put("nume", "invatamantProfesional");
                        jo6.put("numar", ja6);
                        pack2 = pack[8].split(":");
                        pack4 = pack3[8].split(":");
                        JSONArray ja7 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        ja7.put(pac[0]).put(pac2[0]);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, jo6, ja7);
                    }
                    return educationss;

                case "total":
                    List<TotalPerMonth> totalPerMonthsh = totalPerMonthService.filterByMonth(2, county);
                    if (counties.length < 2) {
                        List<TotalPerMonth> totalPerMonths = totalPerMonthService.getByMonthAndCounty(1, county);
                        List<TotalPerMonth> totalPerMonths2 = totalPerMonthService.getByMonthAndCounty(2, county);
                        String total1 = new Gson().toJson(totalPerMonths);
                        String total2 = new Gson().toJson(totalPerMonths2);
                        System.out.println(total1);
                        json.put("judet", county);
                        String[] pack = total1.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = total2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "total");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]);
                        jo.put("numar", ja1);
                        System.out.println(pack3[3]);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        ja3.put(pac[0]).put(pac2[0]);
                        jo2.put("nume", "rata");
                        jo2.put("numar", ja3);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2);
                        json.put("criterii", ja);
                        return json;
                    }
                default:
                    return moreMonthsSeveralCounties(month, table, county);
                // return totalPerMonthsh;

            }
        }
        if (month == 17) {
            switch (table) {
                case "gen":
                    List<Environment> environmentss = environmentService.filterByMonth(6, county);
                    if (counties.length < 2) {
                        List<Environment> environments = environmentService.getByMonthAndCounty(1, county);
                        List<Environment> environments2 = environmentService.getByMonthAndCounty(2, county);
                        List<Environment> environments3 = environmentService.getByMonthAndCounty(3, county);
                        List<Environment> environments4 = environmentService.getByMonthAndCounty(4, county);
                        List<Environment> environments5 = environmentService.getByMonthAndCounty(5, county);
                        List<Environment> environments6 = environmentService.getByMonthAndCounty(6, county);
                        String gen = new Gson().toJson(environments);
                        String gen2 = new Gson().toJson(environments2);
                        String gen3 = new Gson().toJson(environments3);
                        String gen4 = new Gson().toJson(environments4);
                        String gen5 = new Gson().toJson(environments5);
                        String gen6 = new Gson().toJson(environments6);
                        json.put("luna", month).put("judet", county);
                        String[] pack = gen.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = gen2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = gen3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = gen4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = gen5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = gen6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo.put("nume", "femeiDinMediulUrban");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo2.put("nume", "femeiDinMediulRural");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "barbatiDinMediulUrban");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        ja4.put(pac[1]).put(pac2[1]).put(pac3[1]).put(pac4[1]).put(pac5[1]).put(pac6[1]);
                        return getObject(json, jo, jo2, jo3, jo4, ja4);
                    }
                    return environmentss;
                case "varsta":
                    List<Age> agess = ageService.filterByMonth(6, county);
                    if (counties.length < 2) {
                        List<Age> ages = ageService.getByMonthAndCounty(1, county);
                        List<Age> ages2 = ageService.getByMonthAndCounty(2, county);
                        List<Age> ages3 = ageService.getByMonthAndCounty(3, county);
                        List<Age> ages4 = ageService.getByMonthAndCounty(4, county);
                        List<Age> ages5 = ageService.getByMonthAndCounty(5, county);
                        List<Age> ages6 = ageService.getByMonthAndCounty(6, county);
                        String age = new Gson().toJson(ages);
                        String age2 = new Gson().toJson(ages2);
                        String age3 = new Gson().toJson(ages3);
                        String age4 = new Gson().toJson(ages4);
                        String age5 = new Gson().toJson(ages5);
                        String age6 = new Gson().toJson(ages6);
                        json.put("luna", month).put("judet", county);
                        String[] pack = age.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = age2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = age3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = age4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = age5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = age6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo.put("nume", "maiMic25");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo2.put("nume", "intre25Si29");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo3.put("nume", "intre30Si39");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo4.put("nume", "intre40Si59");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        pack6 = pack5[6].split(":");
                        pack8 = pack7[6].split(":");
                        pack10 = pack9[6].split(":");
                        pack12 = pack11[6].split(":");
                        JSONObject jo5 = new JSONObject();
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo5.put("nume", "intre50Si55");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        pack6 = pack5[7].split(":");
                        pack8 = pack7[7].split(":");
                        pack10 = pack9[7].split(":");
                        pack12 = pack11[7].split(":");
                        JSONArray ja6 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        ja6.put(pac[0]).put(pac2[0]).put(pac3[0]).put(pac4[0]).put(pac5[0]).put(pac6[0]);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, ja6);
                    }
                    return agess;

                case "educatie":
                    List<Education> educationss = educationService.filterByMonth(6, county);
                    if (counties.length < 2) {
                        List<Education> educations = educationService.getByMonthAndCounty(1, county);
                        List<Education> educations2 = educationService.getByMonthAndCounty(2, county);
                        List<Education> educations3 = educationService.getByMonthAndCounty(3, county);
                        List<Education> educations4 = educationService.getByMonthAndCounty(4, county);
                        List<Education> educations5 = educationService.getByMonthAndCounty(5, county);
                        List<Education> educations6 = educationService.getByMonthAndCounty(6, county);
                        String educatie = new Gson().toJson(educations);
                        String educatie2 = new Gson().toJson(educations2);
                        String educatie3 = new Gson().toJson(educations3);
                        String educatie4 = new Gson().toJson(educations4);
                        String educatie5 = new Gson().toJson(educations5);
                        String educatie6 = new Gson().toJson(educations6);
                        // System.out.println(educatie);
                        json.put("luna", month).put("judet", county);
                        String[] pack = educatie.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = educatie2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = educatie3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = educatie4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = educatie5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = educatie6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "faraStudii");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "invatamantPrimar");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "invatamantGimnazial");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "invatamantLiceal");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        pack6 = pack5[6].split(":");
                        pack8 = pack7[6].split(":");
                        pack10 = pack9[6].split(":");
                        pack12 = pack11[6].split(":");
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo5 = new JSONObject();
                        jo5.put("nume", "invatamantPostliceal");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        pack6 = pack5[7].split(":");
                        pack8 = pack7[7].split(":");
                        pack10 = pack9[7].split(":");
                        pack12 = pack11[7].split(":");
                        JSONArray ja6 = new JSONArray();
                        ja6.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        JSONObject jo6 = new JSONObject();
                        jo6.put("nume", "invatamantProfesional");
                        jo6.put("numar", ja6);
                        pack2 = pack[8].split(":");
                        pack4 = pack3[8].split(":");
                        pack6 = pack5[8].split(":");
                        pack8 = pack7[8].split(":");
                        pack10 = pack9[8].split(":");
                        pack12 = pack11[8].split(":");
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        JSONArray ja7 = new JSONArray();
                        ja7.put(pac[0]).put(pac2[0]).put(pac3[0]).put(pac4[0]).put(pac5[0]).put(pac6[0]);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, jo6, ja7);
                    }
                    return educationss;

                case "total":
                    List<TotalPerMonth> totalPerMonthss = totalPerMonthService.filterByMonth(6, county);
                    if (counties.length < 2) {
                        List<TotalPerMonth> totalPerMonths = totalPerMonthService.getByMonthAndCounty(1, county);
                        List<TotalPerMonth> totalPerMonths2 = totalPerMonthService.getByMonthAndCounty(2, county);
                        List<TotalPerMonth> totalPerMonths3 = totalPerMonthService.getByMonthAndCounty(3, county);
                        List<TotalPerMonth> totalPerMonths4 = totalPerMonthService.getByMonthAndCounty(4, county);
                        List<TotalPerMonth> totalPerMonths5 = totalPerMonthService.getByMonthAndCounty(5, county);
                        List<TotalPerMonth> totalPerMonths6 = totalPerMonthService.getByMonthAndCounty(6, county);
                        String total1 = new Gson().toJson(totalPerMonths);
                        String total2 = new Gson().toJson(totalPerMonths2);
                        String total3 = new Gson().toJson(totalPerMonths3);
                        String total4 = new Gson().toJson(totalPerMonths4);
                        String total5 = new Gson().toJson(totalPerMonths5);
                        String total6 = new Gson().toJson(totalPerMonths6);
                        json.put("judet", county);
                        String[] pack = total1.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = total2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = total3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = total4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = total5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = total6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "total");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]);
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        ja3.put(pac[0]).put(pac2[0]).put(pac3[0]).put(pac4[0]).put(pac5[0]).put(pac6[0]);
                        jo2.put("nume", "rata");
                        jo2.put("numar", ja3);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2);
                        json.put("criterii", ja);
                        return json;
                    }
                    return totalPerMonthss;
                default:
                   return moreMonthsSeveralCounties(month, table, county);
            }
        }
        if (month == 18) {
            switch (table) {
                case "gen":
                    List<Environment> environmentss = environmentService.filterByMonth(12, county);
                    if (counties.length < 2) {
                        List<Environment> environments = environmentService.getByMonthAndCounty(1, county);
                        List<Environment> environments2 = environmentService.getByMonthAndCounty(2, county);
                        List<Environment> environments3 = environmentService.getByMonthAndCounty(3, county);
                        List<Environment> environments4 = environmentService.getByMonthAndCounty(4, county);
                        List<Environment> environments5 = environmentService.getByMonthAndCounty(5, county);
                        List<Environment> environments6 = environmentService.getByMonthAndCounty(6, county);
                        List<Environment> environments7 = environmentService.getByMonthAndCounty(7, county);
                        List<Environment> environments8 = environmentService.getByMonthAndCounty(8, county);
                        List<Environment> environments9 = environmentService.getByMonthAndCounty(9, county);
                        List<Environment> environments10 = environmentService.getByMonthAndCounty(10, county);
                        List<Environment> environments11 = environmentService.getByMonthAndCounty(11, county);
                        List<Environment> environments12 = environmentService.getByMonthAndCounty(12, county);
                        String gen = new Gson().toJson(environments);
                        String gen2 = new Gson().toJson(environments2);
                        String gen3 = new Gson().toJson(environments3);
                        String gen4 = new Gson().toJson(environments4);
                        String gen5 = new Gson().toJson(environments5);
                        String gen6 = new Gson().toJson(environments6);
                        String gen7 = new Gson().toJson(environments7);
                        String gen8 = new Gson().toJson(environments8);
                        String gen9 = new Gson().toJson(environments9);
                        String gen10 = new Gson().toJson(environments10);
                        String gen11 = new Gson().toJson(environments11);
                        String gen12 = new Gson().toJson(environments12);
                        json.put("luna", month).put("judet", county);
                        String[] pack = gen.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = gen2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = gen3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = gen4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = gen5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = gen6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        String[] pack13 = gen7.split(",");
                        String[] pack14 = pack13[2].split(":");
                        String[] pack15 = gen8.split(",");
                        String[] pack16 = pack15[2].split(":");
                        String[] pack17 = gen9.split(",");
                        String[] pack18 = pack17[2].split(":");
                        String[] pack19 = gen10.split(",");
                        String[] pack20 = pack19[2].split(":");
                        String[] pack21 = gen11.split(",");
                        String[] pack22 = pack21[2].split(":");
                        String[] pack23 = gen12.split(",");
                        String[] pack24 = pack23[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo.put("nume", "femeiDinMediulUrban");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo2.put("nume", "femeiDinMediulRural");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        pack14 = pack13[3].split(":");
                        pack16 = pack15[3].split(":");
                        pack18 = pack17[3].split(":");
                        pack20 = pack19[3].split(":");
                        pack22 = pack21[3].split(":");
                        pack24 = pack23[3].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "barbatiDinMediulUrban");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        pack14 = pack13[3].split(":");
                        pack16 = pack15[3].split(":");
                        pack18 = pack17[3].split(":");
                        pack20 = pack19[3].split(":");
                        pack22 = pack21[3].split(":");
                        pack24 = pack23[3].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        last12Months(pack14, pack16, pack18, pack20, pack22, pack24, ja4, pac, pac2, pac3, pac4, pac5, pac6);
                        return getObject(json, jo, jo2, jo3, jo4, ja4);
                    }
                    return environmentss;
                case "varsta":
                    List<Age> agess = ageService.filterByMonth(12, county);
                    if (counties.length < 2) {
                        List<Age> ages = ageService.getByMonthAndCounty(1, county);
                        List<Age> ages2 = ageService.getByMonthAndCounty(2, county);
                        List<Age> ages3 = ageService.getByMonthAndCounty(3, county);
                        List<Age> ages4 = ageService.getByMonthAndCounty(4, county);
                        List<Age> ages5 = ageService.getByMonthAndCounty(5, county);
                        List<Age> ages6 = ageService.getByMonthAndCounty(6, county);
                        List<Age> ages7 = ageService.getByMonthAndCounty(7, county);
                        List<Age> ages8 = ageService.getByMonthAndCounty(8, county);
                        List<Age> ages9 = ageService.getByMonthAndCounty(9, county);
                        List<Age> ages10 = ageService.getByMonthAndCounty(10, county);
                        List<Age> ages11 = ageService.getByMonthAndCounty(11, county);
                        List<Age> ages12 = ageService.getByMonthAndCounty(12, county);
                        String age = new Gson().toJson(ages);
                        String age2 = new Gson().toJson(ages2);
                        String age3 = new Gson().toJson(ages3);
                        String age4 = new Gson().toJson(ages4);
                        String age5 = new Gson().toJson(ages5);
                        String age6 = new Gson().toJson(ages6);
                        String age7 = new Gson().toJson(ages7);
                        String age8 = new Gson().toJson(ages8);
                        String age9 = new Gson().toJson(ages9);
                        String age10 = new Gson().toJson(ages10);
                        String age11 = new Gson().toJson(ages11);
                        String age12 = new Gson().toJson(ages12);
                        json.put("luna", month).put("judet", county);
                        String[] pack = age.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = age2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = age3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = age4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = age5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = age6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        String[] pack13 = age7.split(",");
                        String[] pack14 = pack13[2].split(":");
                        String[] pack15 = age8.split(",");
                        String[] pack16 = pack15[2].split(":");
                        String[] pack17 = age9.split(",");
                        String[] pack18 = pack17[2].split(":");
                        String[] pack19 = age10.split(",");
                        String[] pack20 = pack19[2].split(":");
                        String[] pack21 = age11.split(",");
                        String[] pack22 = pack21[2].split(":");
                        String[] pack23 = age12.split(",");
                        String[] pack24 = pack23[2].split(":");
                        JSONObject jo = new JSONObject();
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo.put("nume", "maiMic25");
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        pack14 = pack13[3].split(":");
                        pack16 = pack15[3].split(":");
                        pack18 = pack17[3].split(":");
                        pack20 = pack19[3].split(":");
                        pack22 = pack21[3].split(":");
                        pack24 = pack23[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo2.put("nume", "intre25Si29");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        pack14 = pack13[4].split(":");
                        pack16 = pack15[4].split(":");
                        pack18 = pack17[4].split(":");
                        pack20 = pack19[4].split(":");
                        pack22 = pack21[4].split(":");
                        pack24 = pack23[4].split(":");
                        JSONObject jo3 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo3.put("nume", "intre30Si39");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        pack14 = pack13[5].split(":");
                        pack16 = pack15[5].split(":");
                        pack18 = pack17[5].split(":");
                        pack20 = pack19[5].split(":");
                        pack22 = pack21[5].split(":");
                        pack24 = pack23[5].split(":");
                        JSONObject jo4 = new JSONObject();
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo4.put("nume", "intre40Si59");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        pack6 = pack5[6].split(":");
                        pack8 = pack7[6].split(":");
                        pack10 = pack9[6].split(":");
                        pack12 = pack11[6].split(":");
                        pack14 = pack13[6].split(":");
                        pack16 = pack15[6].split(":");
                        pack18 = pack17[6].split(":");
                        pack20 = pack19[6].split(":");
                        pack22 = pack21[6].split(":");
                        pack24 = pack23[6].split(":");
                        JSONObject jo5 = new JSONObject();
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo5.put("nume", "intre50Si55");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        pack6 = pack5[7].split(":");
                        pack8 = pack7[7].split(":");
                        pack10 = pack9[7].split(":");
                        pack12 = pack11[7].split(":");
                        pack14 = pack13[7].split(":");
                        pack16 = pack15[7].split(":");
                        pack18 = pack17[7].split(":");
                        pack20 = pack19[7].split(":");
                        pack22 = pack21[7].split(":");
                        pack24 = pack23[7].split(":");
                        JSONArray ja6 = new JSONArray();

                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        last12Months(pack14, pack16, pack18, pack20, pack22, pack24, ja6, pac, pac2, pac3, pac4, pac5, pac6);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, ja6);
                    }
                    return agess;

                case "educatie":
                    List<Education> educationss = educationService.filterByMonth(12, county);
                    if (counties.length < 2) {
                        List<Education> educations = educationService.getByMonthAndCounty(1, county);
                        List<Education> educations2 = educationService.getByMonthAndCounty(2, county);
                        List<Education> educations3 = educationService.getByMonthAndCounty(3, county);
                        List<Education> educations4 = educationService.getByMonthAndCounty(4, county);
                        List<Education> educations5 = educationService.getByMonthAndCounty(5, county);
                        List<Education> educations6 = educationService.getByMonthAndCounty(6, county);
                        List<Education> educations7 = educationService.getByMonthAndCounty(7, county);
                        List<Education> educations8 = educationService.getByMonthAndCounty(8, county);
                        List<Education> educations9 = educationService.getByMonthAndCounty(9, county);
                        List<Education> educations10 = educationService.getByMonthAndCounty(10, county);
                        List<Education> educations11 = educationService.getByMonthAndCounty(11, county);
                        List<Education> educations12 = educationService.getByMonthAndCounty(12, county);
                        String educatie = new Gson().toJson(educations);
                        String educatie2 = new Gson().toJson(educations2);
                        String educatie3 = new Gson().toJson(educations3);
                        String educatie4 = new Gson().toJson(educations4);
                        String educatie5 = new Gson().toJson(educations5);
                        String educatie6 = new Gson().toJson(educations6);
                        String educatie7 = new Gson().toJson(educations7);
                        String educatie8 = new Gson().toJson(educations8);
                        String educatie9 = new Gson().toJson(educations9);
                        String educatie10 = new Gson().toJson(educations10);
                        String educatie11 = new Gson().toJson(educations11);
                        String educatie12 = new Gson().toJson(educations12);
                        json.put("luna", month).put("judet", county);
                        String[] pack = educatie.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = educatie2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = educatie3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = educatie4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = educatie5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = educatie6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        String[] pack13 = educatie7.split(",");
                        String[] pack14 = pack13[2].split(":");
                        String[] pack15 = educatie8.split(",");
                        String[] pack16 = pack15[2].split(":");
                        String[] pack17 = educatie9.split(",");
                        String[] pack18 = pack17[2].split(":");
                        String[] pack19 = educatie10.split(",");
                        String[] pack20 = pack19[2].split(":");
                        String[] pack21 = educatie11.split(",");
                        String[] pack22 = pack21[2].split(":");
                        String[] pack23 = educatie12.split(",");
                        String[] pack24 = pack23[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "faraStudii");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo.put("numar", ja1);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        pack14 = pack13[3].split(":");
                        pack16 = pack15[3].split(":");
                        pack18 = pack17[3].split(":");
                        pack20 = pack19[3].split(":");
                        pack22 = pack21[3].split(":");
                        pack24 = pack23[3].split(":");
                        JSONArray ja2 = new JSONArray();
                        ja2.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo2 = new JSONObject();
                        jo2.put("nume", "invatamantPrimar");
                        jo2.put("numar", ja2);
                        pack2 = pack[4].split(":");
                        pack4 = pack3[4].split(":");
                        pack6 = pack5[4].split(":");
                        pack8 = pack7[4].split(":");
                        pack10 = pack9[4].split(":");
                        pack12 = pack11[4].split(":");
                        pack14 = pack13[4].split(":");
                        pack16 = pack15[4].split(":");
                        pack18 = pack17[4].split(":");
                        pack20 = pack19[4].split(":");
                        pack22 = pack21[4].split(":");
                        pack24 = pack23[4].split(":");
                        JSONArray ja3 = new JSONArray();
                        ja3.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo3 = new JSONObject();
                        jo3.put("nume", "invatamantGimnazial");
                        jo3.put("numar", ja3);
                        pack2 = pack[5].split(":");
                        pack4 = pack3[5].split(":");
                        pack6 = pack5[5].split(":");
                        pack8 = pack7[5].split(":");
                        pack10 = pack9[5].split(":");
                        pack12 = pack11[5].split(":");
                        pack14 = pack13[5].split(":");
                        pack16 = pack15[5].split(":");
                        pack18 = pack17[5].split(":");
                        pack20 = pack19[5].split(":");
                        pack22 = pack21[5].split(":");
                        pack24 = pack23[5].split(":");
                        JSONArray ja4 = new JSONArray();
                        ja4.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo4 = new JSONObject();
                        jo4.put("nume", "invatamantLiceal");
                        jo4.put("numar", ja4);
                        pack2 = pack[6].split(":");
                        pack4 = pack3[6].split(":");
                        pack6 = pack5[6].split(":");
                        pack8 = pack7[6].split(":");
                        pack10 = pack9[6].split(":");
                        pack12 = pack11[6].split(":");
                        pack14 = pack13[6].split(":");
                        pack16 = pack15[6].split(":");
                        pack18 = pack17[6].split(":");
                        pack20 = pack19[6].split(":");
                        pack22 = pack21[6].split(":");
                        pack24 = pack23[6].split(":");
                        JSONArray ja5 = new JSONArray();
                        ja5.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo5 = new JSONObject();
                        jo5.put("nume", "invatamantPostliceal");
                        jo5.put("numar", ja5);
                        pack2 = pack[7].split(":");
                        pack4 = pack3[7].split(":");
                        pack6 = pack5[7].split(":");
                        pack8 = pack7[7].split(":");
                        pack10 = pack9[7].split(":");
                        pack12 = pack11[7].split(":");
                        pack14 = pack13[7].split(":");
                        pack16 = pack15[7].split(":");
                        pack18 = pack17[7].split(":");
                        pack20 = pack19[7].split(":");
                        pack22 = pack21[7].split(":");
                        pack24 = pack23[7].split(":");
                        JSONArray ja6 = new JSONArray();
                        ja6.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        JSONObject jo6 = new JSONObject();
                        jo6.put("nume", "invatamantProfesional");
                        jo6.put("numar", ja6);
                        pack2 = pack[8].split(":");
                        pack4 = pack3[8].split(":");
                        pack6 = pack5[8].split(":");
                        pack8 = pack7[8].split(":");
                        pack10 = pack9[8].split(":");
                        pack12 = pack11[8].split(":");
                        pack14 = pack13[8].split(":");
                        pack16 = pack15[8].split(":");
                        pack18 = pack17[8].split(":");
                        pack20 = pack19[8].split(":");
                        pack22 = pack21[8].split(":");
                        pack24 = pack23[8].split(":");
                        JSONArray ja7 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        last12Months(pack14, pack16, pack18, pack20, pack22, pack24, ja7, pac, pac2, pac3, pac4, pac5, pac6);
                        return getObject(json, jo, jo2, jo3, jo4, jo5, jo6, ja7);
                    }
                    return educationss;

                case "total":
                    List<TotalPerMonth> totalPerMonthss = totalPerMonthService.filterByMonth(12, county);
                    if (counties.length < 2) {
                        List<TotalPerMonth> totalPerMonths = totalPerMonthService.getByMonthAndCounty(1, county);
                        List<TotalPerMonth> totalPerMonths2 = totalPerMonthService.getByMonthAndCounty(2, county);
                        List<TotalPerMonth> totalPerMonths3 = totalPerMonthService.getByMonthAndCounty(3, county);
                        List<TotalPerMonth> totalPerMonths4 = totalPerMonthService.getByMonthAndCounty(4, county);
                        List<TotalPerMonth> totalPerMonths5 = totalPerMonthService.getByMonthAndCounty(5, county);
                        List<TotalPerMonth> totalPerMonths6 = totalPerMonthService.getByMonthAndCounty(6, county);
                        List<TotalPerMonth> totalPerMonths7 = totalPerMonthService.getByMonthAndCounty(7, county);
                        List<TotalPerMonth> totalPerMonths8 = totalPerMonthService.getByMonthAndCounty(8, county);
                        List<TotalPerMonth> totalPerMonths9 = totalPerMonthService.getByMonthAndCounty(9, county);
                        List<TotalPerMonth> totalPerMonths10 = totalPerMonthService.getByMonthAndCounty(10, county);
                        List<TotalPerMonth> totalPerMonths11 = totalPerMonthService.getByMonthAndCounty(11, county);
                        List<TotalPerMonth> totalPerMonths12 = totalPerMonthService.getByMonthAndCounty(12, county);
                        String total1 = new Gson().toJson(totalPerMonths);
                        String total2 = new Gson().toJson(totalPerMonths2);
                        String total3 = new Gson().toJson(totalPerMonths3);
                        String total4 = new Gson().toJson(totalPerMonths4);
                        String total5 = new Gson().toJson(totalPerMonths5);
                        String total6 = new Gson().toJson(totalPerMonths6);
                        String total7 = new Gson().toJson(totalPerMonths7);
                        String total8 = new Gson().toJson(totalPerMonths8);
                        String total9 = new Gson().toJson(totalPerMonths9);
                        String total10 = new Gson().toJson(totalPerMonths10);
                        String total11 = new Gson().toJson(totalPerMonths11);
                        String total12 = new Gson().toJson(totalPerMonths12);
                        json.put("judet", county);
                        String[] pack = total1.split(",");
                        String[] pack2 = pack[2].split(":");
                        String[] pack3 = total2.split(",");
                        String[] pack4 = pack3[2].split(":");
                        String[] pack5 = total3.split(",");
                        String[] pack6 = pack5[2].split(":");
                        String[] pack7 = total4.split(",");
                        String[] pack8 = pack7[2].split(":");
                        String[] pack9 = total5.split(",");
                        String[] pack10 = pack9[2].split(":");
                        String[] pack11 = total6.split(",");
                        String[] pack12 = pack11[2].split(":");
                        String[] pack13 = total7.split(",");
                        String[] pack14 = pack13[2].split(":");
                        String[] pack15 = total8.split(",");
                        String[] pack16 = pack15[2].split(":");
                        String[] pack17 = total9.split(",");
                        String[] pack18 = pack17[2].split(":");
                        String[] pack19 = total10.split(",");
                        String[] pack20 = pack19[2].split(":");
                        String[] pack21 = total11.split(",");
                        String[] pack22 = pack21[2].split(":");
                        String[] pack23 = total12.split(",");
                        String[] pack24 = pack23[2].split(":");
                        JSONObject jo = new JSONObject();
                        jo.put("nume", "total");
                        JSONArray ja1 = new JSONArray();
                        ja1.put(pack2[1]).put(pack4[1]).put(pack6[1]).put(pack8[1]).put(pack10[1]).put(pack12[1]).put(pack14[1]).put(pack16[1]).put(pack18[1]).put(pack20[1]).put(pack22[1]).put(pack24[1]);
                        jo.put("numar", ja1);
                        System.out.println(pack3[3]);
                        pack2 = pack[3].split(":");
                        pack4 = pack3[3].split(":");
                        pack6 = pack5[3].split(":");
                        pack8 = pack7[3].split(":");
                        pack10 = pack9[3].split(":");
                        pack12 = pack11[3].split(":");
                        pack14 = pack13[3].split(":");
                        pack16 = pack15[3].split(":");
                        pack18 = pack17[3].split(":");
                        pack20 = pack19[3].split(":");
                        pack22 = pack21[3].split(":");
                        pack24 = pack23[3].split(":");
                        JSONObject jo2 = new JSONObject();
                        JSONArray ja3 = new JSONArray();
                        String[] pac = pack2[1].split("}");
                        String[] pac2 = pack4[1].split("}");
                        String[] pac3 = pack6[1].split("}");
                        String[] pac4 = pack8[1].split("}");
                        String[] pac5 = pack10[1].split("}");
                        String[] pac6 = pack12[1].split("}");
                        last12Months(pack14, pack16, pack18, pack20, pack22, pack24, ja3, pac, pac2, pac3, pac4, pac5, pac6);
                        jo2.put("nume", "rata");
                        jo2.put("numar", ja3);
                        JSONArray ja = new JSONArray();
                        ja.put(jo).put(jo2);
                        json.put("criterii", ja);
                        return json;
                    }
                default: return moreMonthsSeveralCounties(month, table, county);
            }
        }
        return new ArrayList<>();
    }

    private Object severalCounties(int month, String criterion, String county) throws JSONException {
        String[] counties = county.split(",");
        if (criterion.equals("maiMic25")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[2].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("intre25Si29")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[3].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("intre30Si39")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[4].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("intre40Si49")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[5].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("intre50Si55")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[6].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("peste55")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Age> ages = ageService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(ages);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[7].split(":");
                JSONObject jo = new JSONObject();
                String[] pac = pack2[1].split("}");
                jo.put("nume", counties[i]).put("numar", pac[0]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("totalSomeri")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<TotalPerMonth> total = totalPerMonthService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(total);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[2].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                return ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("rata")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<TotalPerMonth> total = totalPerMonthService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(total);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[3].split(":");
                JSONObject jo = new JSONObject();
                String[] pac = pack2[1].split("}");
                jo.put("nume", counties[i]).put("numar", pac[0]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("faraStudii")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[2].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        }
        if (criterion.equals("invatamantPrimar")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[3].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("invatamantGimnazial")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[4].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("invatamantLiceal")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[5].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("invatamantPostliceal")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[6].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("invatamantProfesional")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[7].split(":");
                JSONObject jo = new JSONObject();
                System.out.println("DF");
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("invatamantUniversitar")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Education> educations = educationService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(educations);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[8].split(":");
                JSONObject jo = new JSONObject();
                String[] pac = pack2[1].split("}");
                jo.put("nume", counties[i]).put("numar", pac[0]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("femeiDinMediulUrban")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Environment> environments = environmentService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(environments);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[2].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("femeiDinMediulRural")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Environment> environments = environmentService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(environments);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[3].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("barbatiDinMediulUrban")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Environment> environments = environmentService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(environments);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[4].split(":");
                JSONObject jo = new JSONObject();
                jo.put("nume", counties[i]).put("numar", pack2[1]);
                ja.put(jo);
            }
            return ja;
        } else if (criterion.equals("barbatiDinMediulRural")) {
            JSONArray ja = new JSONArray();
            for (int i = 0; i < counties.length; i++) {
                List<Environment> environments = environmentService.getByMonthAndCounty(month, counties[i]);
                String ages1 = new Gson().toJson(environments);
                String[] pack = ages1.split(",");
                String[] pack2 = pack[5].split(":");
                JSONObject jo = new JSONObject();
                String[] pac = pack2[1].split("}");
                jo.put("nume", counties[i]).put("numar", pac[0]);
                ja.put(jo);
            }
            return ja;
        }
        return null;
    }

    private Object moreMonthsSeveralCounties(int month, String criterion, String county) throws JSONException {
        JSONObject json = new JSONObject();
        if (month == 16) {
            JSONObject json1 = new JSONObject();
            JSONObject json2 = new JSONObject();
            json1.put("luna", "1");
            json1.put("judete", severalCounties(1, criterion, county));
            json2.put("luna", 2);
            json2.put("judete", severalCounties(2, criterion, county));
            return json.put("1", json1).put("2", json2);
        }
        if(month==17){
            JSONObject json1 = new JSONObject();
            JSONObject json2 = new JSONObject();
            JSONObject json3 = new JSONObject();
            JSONObject json4 = new JSONObject();
            JSONObject json5 = new JSONObject();
            JSONObject json6 = new JSONObject();
            json1.put("luna", "1");
            json1.put("judete", severalCounties(1, criterion, county));
            json2.put("luna", 2);
            json2.put("judete", severalCounties(2, criterion, county));
            json3.put("luna", "3");
            json3.put("judete", severalCounties(3, criterion, county));
            json4.put("luna", "4");
            json4.put("judete", severalCounties(4, criterion, county));
            json5.put("luna", "5");
            json5.put("judete", severalCounties(5, criterion, county));
            json6.put("luna", "6");
            json6.put("judete", severalCounties(6, criterion, county));
            return json.put("1", json1).put("2", json2).put("3", json3).put("4", json4).put("5", json5).put("6", json6);
        }
        if(month==18){
            JSONObject json1 = new JSONObject();
            JSONObject json2 = new JSONObject();
            JSONObject json3 = new JSONObject();
            JSONObject json4 = new JSONObject();
            JSONObject json5 = new JSONObject();
            JSONObject json6 = new JSONObject();
            JSONObject json7 = new JSONObject();
            JSONObject json8 = new JSONObject();
            JSONObject json9 = new JSONObject();
            JSONObject json10 = new JSONObject();
            JSONObject json11 = new JSONObject();
            JSONObject json12= new JSONObject();
            json1.put("luna", "1");
            json1.put("judete", severalCounties(1, criterion, county));
            json2.put("luna", 2);
            json2.put("judete", severalCounties(2, criterion, county));
            json3.put("luna", "3");
            json3.put("judete", severalCounties(3, criterion, county));
            json4.put("luna", "4");
            json4.put("judete", severalCounties(4, criterion, county));
            json5.put("luna", "5");
            json5.put("judete", severalCounties(5, criterion, county));
            json6.put("luna", "6");
            json6.put("judete", severalCounties(6, criterion, county));
            json7.put("luna", "7");
            json7.put("judete", severalCounties(7, criterion, county));
            json8.put("luna", "8");
            json8.put("judete", severalCounties(8, criterion, county));
            json9.put("luna", "9");
            json9.put("judete", severalCounties(9, criterion, county));
            json10.put("luna", "10");
            json10.put("judete", severalCounties(10, criterion, county));
            json11.put("luna", "11");
            json11.put("judete", severalCounties(11, criterion, county));
            json12.put("luna", "12");
            json12.put("judete", severalCounties(12, criterion, county));
            return json.put("1", json1).put("2", json2).put("3", json3).put("4", json4).put("5",json5).put("6", json6).put("7", json7).put("8", json8).put("9", json9).put("10", json10).put("11",json11).put("12", json12);
        }
        return null;
    }

    private Object countiesOneMonth(int month, String criterion, String county) throws JSONException {
        JSONObject json = new JSONObject();
        json.put("luna", month);
        json.put("judete", severalCounties(month, criterion, county));
        return json;
    }

    private void last12Months(String[] pack14, String[] pack16, String[] pack18, String[] pack20, String[] pack22, String[] pack24, JSONArray ja6, String[] pac, String[] pac2, String[] pac3, String[] pac4, String[] pac5, String[] pac6) {
        String[] pac7 = pack14[1].split("}");
        String[] pac8 = pack16[1].split("}");
        String[] pac9 = pack18[1].split("}");
        String[] pac10 = pack20[1].split("}");
        String[] pac11 = pack22[1].split("}");
        String[] pac12 = pack24[1].split("}");
        ja6.put(pac[0]).put(pac2[0]).put(pac3[0]).put(pac4[0]).put(pac5[0]).put(pac6[0]).put(pac7[0]).put(pac8[0]).put(pac9[0]).put(pac10[0]).put(pac11[0]).put(pac12[0]);
    }

    private Object getObject(JSONObject json, JSONObject jo, JSONObject jo2, JSONObject jo3, JSONObject jo4, JSONObject jo5, JSONArray ja6) throws JSONException {
        JSONObject jo6 = new JSONObject();
        jo6.put("nume", "peste55");
        jo6.put("numar", ja6);
        JSONArray ja = new JSONArray();
        ja.put(jo).put(jo2).put(jo3).put(jo4).put(jo5).put(jo6);
        json.put("criterii", ja);
        return json;
    }

    private Object getObject(JSONObject json, JSONObject jo, JSONObject jo2, JSONObject jo3, JSONObject jo4, JSONObject jo5, JSONObject jo6, JSONArray ja7) throws JSONException {
        JSONObject jo7 = new JSONObject();
        jo7.put("nume", "invatamantUniversitar");
        jo7.put("numar", ja7);
        JSONArray ja = new JSONArray();
        ja.put(jo).put(jo2).put(jo3).put(jo4).put(jo5).put(jo6).put(jo7);
        json.put("criterii", ja);
        return json;
    }

    private Object getObject(JSONObject json, JSONObject jo, JSONObject jo2, JSONObject jo3, JSONObject jo4, JSONArray ja4) throws JSONException {
        jo4.put("nume", "barbatiDinMediulRural");
        jo4.put("numar", ja4);
        JSONArray ja = new JSONArray();
        ja.put(jo).put(jo2).put(jo3).put(jo4);
        json.put("criterii", ja);
        return json;
    }


    public boolean downloadCsv(int criteria1, String criteria2, String criteria3, String criteria4, String criteria5) {
        GenerateCSVFile csv = new GenerateCSVFile();
        return false;
    }

    public boolean downloadPdf(int criteria1, String criteria2, String criteria3, String criteria4, String criteria5) {
        GeneratePDFFile pdf = new GeneratePDFFile();
        return false;

    }

    public boolean downloadSvg(int criteria1, String criteria2, String criteria3, String criteria4, String criteria5) {
        GenerateSvgFile svg = new GenerateSvgFile();
        return false;
    }


}
