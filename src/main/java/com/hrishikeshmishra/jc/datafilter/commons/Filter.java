package com.hrishikeshmishra.jc.datafilter.commons;

import java.util.List;

/**
 * Created by hrishikesh.mishra on 14/10/16.
 */
public class Filter {

    public static boolean filter(CensusData data, List<FilterData> filters){
        boolean result = true;
        for (FilterData filter: filters){
            result = result && evaluate(data, filter);
            if (!result)
                return false;
        }
        return result;
    }

    private static boolean evaluate(CensusData data, FilterData filter) {
        switch (filter.getAttributeId()) {
            case 0 :
                return data.getAge() == Integer.valueOf(filter.getAttributeValue());
            case 1:
                return data.getClassOfWorker().equals(filter.getAttributeValue());
            case 2:
                return  data.getDetailedIndustryRecode() == Integer.valueOf(filter.getAttributeValue());
            case 3:
                return data.getDetailedOccupationRecode() == Integer.valueOf(filter.getAttributeValue());
            case 4:
                return data.getEducation().equals(filter.getAttributeValue());
            case 5:
                return data.getWagePerHour() == Integer.valueOf(filter.getAttributeValue());
            case 6:
                return data.getEnroll().equals(filter.getAttributeValue());
            case 7:
                return data.getMaritalStatus().equals(filter.getAttributeValue()) ;
            case 8:
                return data.getMajorIndustryCode().equals(filter.getAttributeValue());
            case 9:
                return data.getMajorOccupationCode().equals(filter.getAttributeValue());
            case 10:
                return data.getRace().equals(filter.getAttributeValue());
            case 11:
                return data.getHispanicOrigin().equals(filter.getAttributeValue());
            case 12:
                return data.getSex().equals(filter.getAttributeValue());
            case 13:
                return data.getMemberOfALaborUnion().equals(filter.getAttributeValue());
            case 14:
                return data.getReasonForUnemployment().equals(filter.getAttributeValue());
            case 15:
                return data.getFullOrPartTimeEmploymentStatus().equals(filter.getAttributeValue());
            case 16:
                return data.getCapitalGains() == Integer.valueOf(filter.getAttributeValue());
            case 17:
                return data.getCapitallosses() == Integer.valueOf(filter.getAttributeValue());
            case 18:
                return data.getDividendsFromStocks() == Integer.valueOf(filter.getAttributeValue());
            case 19:
                return data.getTaxFilerStatus().equals(filter.getAttributeValue());
            case 20:
                return data.getRegionOfPreviousResidence().equals(filter.getAttributeValue());
            case 21:
                return data.getStateOfPreviousResidence().equals(filter.getAttributeValue());
            case 22:
                return data.getDetailedHouseholdAndFamilyStatus().equals(filter.getAttributeValue());
            case 23:
                return data.getDetailedHouseholdSummaryInHousehold().equals(filter.getAttributeValue());
            case 24:
                return data.getMigrationCodeChangeInMsa().equals(filter.getAttributeValue());
            case 25:
                return data.getMigrationCodeChangeInReg().equals(filter.getAttributeValue());
            case 26:
                return data.getMigrationCodeMoveWithinReg().equals(filter.getAttributeValue());
            case 27:
                return data.getLiveInThisHouse1YearAgo().equals(filter.getAttributeValue());
            case 28:
                return data.getMigrationPrevResInSunbelt().equals(filter.getAttributeValue());
            case 29:
                return data.getNumPersonsWorkedForEmployer().equals(filter.getAttributeValue());
            case 30:
                return data.getFamilyMembersUnder18().equals(filter.getAttributeValue());
            case 31:
                return data.getCountryOfBirthFather().equals(filter.getAttributeValue());
            case 32:
                return data.getCountryOfBirthMother().equals(filter.getAttributeValue());
            case 33:
                return data.getCountryOfBirthSelf().equals(filter.getAttributeValue());
            case 34:
                return data.getCitizenship().equals(filter.getAttributeValue());
            case 35:
                return data.getOwnBusinessOrSelfEmployed().equals(filter.getAttributeValue());
            case 36:
                return data.getFillIncQuestionnaire().equals(filter.getAttributeValue());
            case 37:
                return data.getVeterans().equals(filter.getAttributeValue());
            case 38:
                return data.getWeeksWorkedInYear() == Integer.valueOf(filter.getAttributeValue());
            case 39:
                return data.getYear() == Integer.valueOf(filter.getAttributeValue());
        }
        return false;
    }


}
