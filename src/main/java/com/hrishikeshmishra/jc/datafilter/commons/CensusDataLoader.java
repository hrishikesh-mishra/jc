package com.hrishikeshmishra.jc.datafilter.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hrishikesh.mishra on 13/10/16.
 */
public class CensusDataLoader {

    public static CensusData [] load(Path file) throws IOException {
        List<CensusData> list = new ArrayList<>();
        try(BufferedReader reader = Files.newBufferedReader(file)){
            String line;
            while ((line=reader.readLine()) != null){
                CensusData data = parseData(line);
                list.add(data);
            }
        }
        return list.toArray(new CensusData[list.size()]);
    }

    private static CensusData parseData(String line) {
        CensusData data = new CensusData();
        String [] tokens = line.split(",");

        data.setAge(Integer.valueOf(tokens[0].trim())) ;
        data.setClassOfWorker(tokens[1].trim());
        data.setDetailedIndustryRecode(Integer.valueOf(tokens[2].trim()));
        data.setDetailedOccupationRecode(Integer.valueOf(tokens[3].trim()));
        data.setEducation(tokens[4].trim());
        data.setWagePerHour(Integer.valueOf(tokens[5].trim()));
        data.setEnroll(tokens[6].trim());
        data.setMaritalStatus(tokens[7].trim()) ;
        data.setMajorIndustryCode(tokens[8].trim());
        data.setMajorOccupationCode(tokens[9].trim());
        data.setRace(tokens[10].trim());
        data.setHispanicOrigin(tokens[11].trim());
        data.setSex(tokens[12].trim());
        data.setMemberOfALaborUnion(tokens[13].trim());
        data.setReasonForUnemployment(tokens[14].trim());
        data.setFullOrPartTimeEmploymentStatus(tokens[15].trim());
        data.setCapitalGains(Integer.valueOf(tokens[16].trim()));
        data.setCapitallosses(Integer.valueOf(tokens[17].trim()));
        data.setDividendsFromStocks(Integer.valueOf(tokens[18].trim()));
        data.setTaxFilerStatus(tokens[19].trim());
        data.setRegionOfPreviousResidence(tokens[20].trim());
        data.setStateOfPreviousResidence(tokens[21].trim());
        data.setDetailedHouseholdAndFamilyStatus(tokens[22].trim());
        data.setDetailedHouseholdSummaryInHousehold(tokens[23].trim());
        data.setMigrationCodeChangeInMsa(tokens[24].trim());
        data.setMigrationCodeChangeInReg(tokens[25].trim());
        data.setMigrationCodeMoveWithinReg(tokens[26].trim());
        data.setLiveInThisHouse1YearAgo(tokens[27].trim());
        data.setMigrationPrevResInSunbelt(tokens[28].trim());
        data.setNumPersonsWorkedForEmployer(tokens[29].trim());
        data.setFamilyMembersUnder18(tokens[30].trim());
        data.setCountryOfBirthFather(tokens[31].trim());
        data.setCountryOfBirthMother(tokens[32].trim());
        data.setCountryOfBirthSelf(tokens[33].trim());
        data.setCitizenship(tokens[34].trim());
        data.setOwnBusinessOrSelfEmployed(tokens[35].trim());
        data.setFillIncQuestionnaire(tokens[36].trim());
        data.setVeterans(tokens[37].trim());
        data.setWeeksWorkedInYear(Integer.valueOf(tokens[38].trim()));
        data.setYear(Integer.valueOf(tokens[39].trim()));

        return data;
    }
}
