// ------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//
// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// ------------------------------------------------------------------------------

package com.microsoft.recognizers.text.datetime.resources;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class EnglishTimeZone {

    public static final String DirectUtcRegex = "\\b(utc|gmt)(\\s*[+\\-\\u00B1]?\\s*[\\d]{1,2}h?(\\s*:\\s*[\\d]{1,2})?)?\\b";

    public static final List<String> AbbreviationsList = Arrays.asList("ABST", "ACDT", "ACST", "ACT", "ADT", "AEDT", "AEST", "AET", "AFT", "AKDT", "AKST", "AMST", "AMT", "AOE", "AoE", "ARBST", "ARST", "ART", "AST", "AWDT", "AWST", "AZOST", "AZOT", "AZST", "AZT", "BIT", "BST", "BTT", "CADT", "CAST", "CBST", "CBT", "CCST", "CDT", "CDTM", "CEST", "CET", "COT", "CST", "CSTM", "CT", "CVT", "EAT", "ECT", "EDT", "EDTM", "EEST", "EET", "EGST", "ESAST", "ESAT", "EST", "ESTM", "ET", "FJST", "FJT", "GET", "GMT", "GNDT", "GNST", "GST", "GTBST", "HADT", "HAST", "HDT", "HKT", "HST", "IRDT", "IRKT", "IRST", "ISDT", "ISST", "IST", "JDT", "JST", "KRAT", "KST", "LINT", "MAGST", "MAGT", "MAT", "MDT", "MDTM", "MEST", "MOST", "MSK", "MSK+1", "MSK+2", "MSK+3", "MSK+4", "MSK+5", "MSK+6", "MSK+7", "MSK+8", "MSK+9", "MSK-1", "MST", "MSTM", "MUT", "MVST", "MYST", "NCAST", "NDT", "NMDT", "NMST", "NPT", "NST", "NZDT", "NZST", "NZT", "PDST", "PDT", "PDTM", "PETT", "PKT", "PSAST", "PSAT", "PST", "PSTM", "PT", "PYST", "PYT", "RST", "SAEST", "SAPST", "SAST", "SAWST", "SBT", "SGT", "SLT", "SMST", "SNST", "SST", "TADT", "TAST", "THA", "TIST", "TOST", "TOT", "TRT", "TST", "ULAT", "UTC", "VET", "VLAT", "WAST", "WAT", "WEST", "WET", "WPST", "YAKT", "YEKT");

    public static final List<String> FullNameList = Arrays.asList("Acre Time", "Afghanistan Standard Time", "Alaskan Standard Time", "Anywhere on Earth", "Arab Standard Time", "Arabian Standard Time", "Arabic Standard Time", "Argentina Standard Time", "Atlantic Standard Time", "AUS Central Standard Time", "Australian Central Time", "AUS Eastern Standard Time", "Australian Eastern Time", "Australian Eastern Standard Time", "Australian Central Daylight Time", "Australian Eastern Daylight Time", "Azerbaijan Standard Time", "Azores Standard Time", "Bahia Standard Time", "Bangladesh Standard Time", "Belarus Standard Time", "Canada Central Standard Time", "Cape Verde Standard Time", "Caucasus Standard Time", "Cen. Australia Standard Time", "Central America Standard Time", "Central Asia Standard Time", "Central Brazilian Standard Time", "Central Daylight Time", "Europe Central Time", "European Central Time", "Central Europe Standard Time", "Central Europe Std Time", "Central European Std Time", "Central European Standard Time", "Central Pacific Standard Time", "Central Standard Time", "Central Standard Time (Mexico)", "China Standard Time", "Dateline Standard Time", "E. Africa Standard Time", "E. Australia Standard Time", "E. Europe Standard Time", "E. South America Standard Time", "Eastern Time", "Eastern Daylight Time", "Eastern Standard Time", "Eastern Standard Time (Mexico)", "Egypt Standard Time", "Ekaterinburg Standard Time", "Fiji Standard Time", "FLE Standard Time", "Georgian Standard Time", "GMT Standard Time", "Greenland Standard Time", "Greenwich Standard Time", "GTB Standard Time", "Hawaiian Standard Time", "India Standard Time", "Iran Standard Time", "Israel Standard Time", "Jordan Standard Time", "Kaliningrad Standard Time", "Kamchatka Standard Time", "Korea Standard Time", "Libya Standard Time", "Line Islands Standard Time", "Magadan Standard Time", "Mauritius Standard Time", "Mid-Atlantic Standard Time", "Middle East Standard Time", "Montevideo Standard Time", "Morocco Standard Time", "Mountain Standard Time", "Mountain Standard Time (Mexico)", "Myanmar Standard Time", "N. Central Asia Standard Time", "Namibia Standard Time", "Nepal Standard Time", "New Zealand Standard Time", "Newfoundland Standard Time", "North Asia East Standard Time", "North Asia Standard Time", "North Korea Standard Time", "Pacific SA Standard Time", "Pacific Standard Time", "Pacific Daylight Time", "Pacific Time", "Pacific Standard Time", "Pacific Standard Time (Mexico)", "Pakistan Standard Time", "Paraguay Standard Time", "Romance Standard Time", "Russia Time Zone 1", "Russia Time Zone 2", "Russia Time Zone 3", "Russia Time Zone 4", "Russia Time Zone 5", "Russia Time Zone 6", "Russia Time Zone 7", "Russia Time Zone 8", "Russia Time Zone 9", "Russia Time Zone 10", "Russia Time Zone 11", "Russian Standard Time", "SA Eastern Standard Time", "SA Pacific Standard Time", "SA Western Standard Time", "Samoa Standard Time", "SE Asia Standard Time", "Singapore Standard Time", "Singapore Time", "South Africa Standard Time", "Sri Lanka Standard Time", "Syria Standard Time", "Taipei Standard Time", "Tasmania Standard Time", "Tokyo Standard Time", "Tonga Standard Time", "Turkey Standard Time", "Ulaanbaatar Standard Time", "US Eastern Standard Time", "US Mountain Standard Time", "Mountain", "Venezuela Standard Time", "Vladivostok Standard Time", "W. Australia Standard Time", "W. Central Africa Standard Time", "W. Europe Standard Time", "West Asia Standard Time", "West Pacific Standard Time", "Yakutsk Standard Time", "Pacific Daylight Saving Time", "Austrialian Western Daylight Time", "Austrialian West Daylight Time", "Australian Western Daylight Time", "Australian West Daylight Time", "Colombia Time", "Hong Kong Time", "Central Europe Time", "Central European Time", "Central Europe Summer Time", "Central European Summer Time", "Central Europe Standard Time", "Central European Standard Time", "Central Europe Std Time", "Central European Std Time", "West Coast Time", "West Coast", "Central Time", "Central", "Pacific", "Eastern");

    public static final String BaseTimeZoneSuffixRegex = "((\\s+|-)(friendly|compatible))?(\\s+|-)time(zone)?";

    public static final String LocationTimeSuffixRegex = "({BaseTimeZoneSuffixRegex})\\b"
            .replace("{BaseTimeZoneSuffixRegex}", BaseTimeZoneSuffixRegex);

    public static final String TimeZoneEndRegex = "({BaseTimeZoneSuffixRegex})$"
            .replace("{BaseTimeZoneSuffixRegex}", BaseTimeZoneSuffixRegex);

    public static final List<String> AmbiguousTimezoneList = Arrays.asList("bit", "get", "art", "cast", "eat", "lint", "mat", "most", "west", "vet", "wet", "cot", "pt", "et", "eastern", "pacific", "central", "mountain", "west coast");

    public static final ImmutableMap<String, Integer> AbbrToMinMapping = ImmutableMap.<String, Integer>builder()
        .put("abst", 180)
        .put("acdt", 630)
        .put("acst", 570)
        .put("act", -10000)
        .put("adt", -10000)
        .put("aedt", 660)
        .put("aest", 600)
        .put("aet", 600)
        .put("aft", 270)
        .put("akdt", -480)
        .put("akst", -540)
        .put("amst", -10000)
        .put("amt", -10000)
        .put("aoe", -720)
        .put("arbst", 180)
        .put("arst", 180)
        .put("art", -180)
        .put("ast", -10000)
        .put("awdt", 540)
        .put("awst", 480)
        .put("azost", 0)
        .put("azot", -60)
        .put("azst", 300)
        .put("azt", 240)
        .put("bit", -720)
        .put("bst", -10000)
        .put("btt", 360)
        .put("cadt", -360)
        .put("cast", 480)
        .put("cbst", -240)
        .put("cbt", -240)
        .put("ccst", -360)
        .put("cdt", -10000)
        .put("cdtm", -360)
        .put("cest", 120)
        .put("cet", 60)
        .put("cot", -300)
        .put("cst", -10000)
        .put("cstm", -360)
        .put("ct", -360)
        .put("cvt", -60)
        .put("eat", 180)
        .put("ect", -10000)
        .put("edt", -240)
        .put("edtm", -300)
        .put("eest", 180)
        .put("eet", 120)
        .put("egst", 0)
        .put("esast", -180)
        .put("esat", -180)
        .put("est", -300)
        .put("estm", -300)
        .put("et", -240)
        .put("fjst", 780)
        .put("fjt", 720)
        .put("get", 240)
        .put("gmt", 0)
        .put("gndt", -180)
        .put("gnst", -180)
        .put("gst", -10000)
        .put("gtbst", 120)
        .put("hadt", -540)
        .put("hast", -600)
        .put("hdt", -540)
        .put("hkt", 480)
        .put("hst", -600)
        .put("irdt", 270)
        .put("irkt", 480)
        .put("irst", 210)
        .put("isdt", 120)
        .put("isst", 120)
        .put("ist", -10000)
        .put("jdt", 120)
        .put("jst", 540)
        .put("krat", 420)
        .put("kst", -10000)
        .put("lint", 840)
        .put("magst", 720)
        .put("magt", 660)
        .put("mat", -120)
        .put("mdt", -360)
        .put("mdtm", -420)
        .put("mest", 120)
        .put("most", 0)
        .put("msk+1", 240)
        .put("msk+2", 300)
        .put("msk+3", 360)
        .put("msk+4", 420)
        .put("msk+5", 480)
        .put("msk+6", 540)
        .put("msk+7", 600)
        .put("msk+8", 660)
        .put("msk+9", 720)
        .put("msk-1", 120)
        .put("msk", 180)
        .put("mst", -420)
        .put("mstm", -420)
        .put("mut", 240)
        .put("mvst", -180)
        .put("myst", 390)
        .put("ncast", 420)
        .put("ndt", -150)
        .put("nmdt", 60)
        .put("nmst", 60)
        .put("npt", 345)
        .put("nst", -210)
        .put("nzdt", 780)
        .put("nzst", 720)
        .put("nzt", 720)
        .put("pdst", -420)
        .put("pdt", -420)
        .put("pdtm", -480)
        .put("pett", 720)
        .put("pkt", 300)
        .put("psast", -240)
        .put("psat", -240)
        .put("pst", -480)
        .put("pstm", -480)
        .put("pt", -420)
        .put("pyst", -10000)
        .put("pyt", -10000)
        .put("rst", 60)
        .put("saest", -180)
        .put("sapst", -300)
        .put("sast", 120)
        .put("sawst", -240)
        .put("sbt", 660)
        .put("sgt", 480)
        .put("slt", 330)
        .put("smst", 780)
        .put("snst", 480)
        .put("sst", -10000)
        .put("tadt", 600)
        .put("tast", 600)
        .put("tha", 420)
        .put("tist", 480)
        .put("tost", 840)
        .put("tot", 780)
        .put("trt", 180)
        .put("tst", 540)
        .put("ulat", 480)
        .put("utc", 0)
        .put("vet", -240)
        .put("vlat", 600)
        .put("wast", 120)
        .put("wat", -10000)
        .put("west", 60)
        .put("wet", 0)
        .put("wpst", 600)
        .put("yakt", 540)
        .put("yekt", 300)
        .build();

    public static final ImmutableMap<String, Integer> FullToMinMapping = ImmutableMap.<String, Integer>builder()
        .put("beijing", 480)
        .put("shanghai", 480)
        .put("shenzhen", 480)
        .put("suzhou", 480)
        .put("tianjian", 480)
        .put("chengdu", 480)
        .put("guangzhou", 480)
        .put("wuxi", 480)
        .put("xiamen", 480)
        .put("chongqing", 480)
        .put("shenyang", 480)
        .put("china", 480)
        .put("redmond", -480)
        .put("seattle", -480)
        .put("bellevue", -480)
        .put("pacific daylight", -420)
        .put("pacific", -480)
        .put("afghanistan standard", 270)
        .put("alaskan standard", -540)
        .put("anywhere on earth", -720)
        .put("arab standard", 180)
        .put("arabian standard", 180)
        .put("arabic standard", 180)
        .put("argentina standard", -180)
        .put("atlantic standard", -240)
        .put("aus central standard", 570)
        .put("aus eastern standard", 600)
        .put("australian eastern", 600)
        .put("australian eastern standard", 600)
        .put("australian central daylight", 630)
        .put("australian eastern daylight", 660)
        .put("azerbaijan standard", 240)
        .put("azores standard", -60)
        .put("bahia standard", -180)
        .put("bangladesh standard", 360)
        .put("belarus standard", 180)
        .put("canada central standard", -360)
        .put("cape verde standard", -60)
        .put("caucasus standard", 240)
        .put("cen. australia standard", 570)
        .put("central australia standard", 570)
        .put("central america standard", -360)
        .put("central asia standard", 360)
        .put("central brazilian standard", -240)
        .put("central daylight", -10000)
        .put("central europe", 60)
        .put("central european", 60)
        .put("central europe std", 60)
        .put("central european std", 60)
        .put("central europe standard", 60)
        .put("central european standard", 60)
        .put("central europe summer", 120)
        .put("central european summer", 120)
        .put("central pacific standard", 660)
        .put("central standard time (mexico)", -360)
        .put("central standard", -360)
        .put("china standard", 480)
        .put("dateline standard", -720)
        .put("e. africa standard", 180)
        .put("e. australia standard", 600)
        .put("e. europe standard", 120)
        .put("e. south america standard", -180)
        .put("europe central", 60)
        .put("european central", 60)
        .put("central", -300)
        .put("eastern", -240)
        .put("eastern daylight", -10000)
        .put("eastern standard time (mexico)", -300)
        .put("eastern standard", -300)
        .put("egypt standard", 120)
        .put("ekaterinburg standard", 300)
        .put("fiji standard", 720)
        .put("fle standard", 120)
        .put("georgian standard", 240)
        .put("gmt standard", 0)
        .put("greenland standard", -180)
        .put("greenwich standard", 0)
        .put("gtb standard", 120)
        .put("hawaiian standard", -600)
        .put("india standard", 330)
        .put("iran standard", 210)
        .put("israel standard", 120)
        .put("jordan standard", 120)
        .put("kaliningrad standard", 120)
        .put("kamchatka standard", 720)
        .put("korea standard", 540)
        .put("libya standard", 120)
        .put("line islands standard", 840)
        .put("magadan standard", 660)
        .put("mauritius standard", 240)
        .put("mid-atlantic standard", -120)
        .put("middle east standard", 120)
        .put("montevideo standard", -180)
        .put("morocco standard", 0)
        .put("mountain", -360)
        .put("mountain standard", -420)
        .put("mountain standard time (mexico)", -420)
        .put("myanmar standard", 390)
        .put("n. central asia standard", 420)
        .put("namibia standard", 60)
        .put("nepal standard", 345)
        .put("new zealand standard", 720)
        .put("newfoundland standard", -210)
        .put("north asia east standard", 480)
        .put("north asia standard", 420)
        .put("north korea standard", 510)
        .put("west coast", -420)
        .put("pacific sa standard", -240)
        .put("pacific standard", -480)
        .put("pacific standard time (mexico)", -480)
        .put("pakistan standard", 300)
        .put("paraguay standard", -240)
        .put("romance standard", 60)
        .put("russia time zone 1", 120)
        .put("russia time zone 2", 180)
        .put("russia time zone 3", 240)
        .put("russia time zone 4", 300)
        .put("russia time zone 5", 360)
        .put("russia time zone 6", 420)
        .put("russia time zone 7", 480)
        .put("russia time zone 8", 540)
        .put("russia time zone 9", 600)
        .put("russia time zone 10", 660)
        .put("russia time zone 11", 720)
        .put("russian standard", 180)
        .put("sa eastern standard", -180)
        .put("sa pacific standard", -300)
        .put("sa western standard", -240)
        .put("samoa standard", -660)
        .put("se asia standard", 420)
        .put("singapore standard", 480)
        .put("singapore", 480)
        .put("south africa standard", 120)
        .put("sri lanka standard", 330)
        .put("syria standard", 120)
        .put("taipei standard", 480)
        .put("tasmania standard", 600)
        .put("tokyo standard", 540)
        .put("tonga standard", 780)
        .put("turkey standard", 180)
        .put("ulaanbaatar standard", 480)
        .put("us eastern standard", -300)
        .put("us mountain standard", -420)
        .put("venezuela standard", -240)
        .put("vladivostok standard", 600)
        .put("w. australia standard", 480)
        .put("w. central africa standard", 60)
        .put("w. europe standard", 0)
        .put("western european", 0)
        .put("west europe standard", 0)
        .put("west europe std", 0)
        .put("western europe standard", 0)
        .put("western europe summer", 60)
        .put("w. europe summer", 60)
        .put("western european summer", 60)
        .put("west europe summer", 60)
        .put("west asia standard", 300)
        .put("west pacific standard", 600)
        .put("yakutsk standard", 540)
        .put("pacific daylight saving", -420)
        .put("australian western daylight", 540)
        .put("australian west daylight", 540)
        .put("austrialian western daylight", 540)
        .put("austrialian west daylight", 540)
        .put("colombia", -300)
        .put("hong kong", 480)
        .put("madrid", 60)
        .put("bilbao", 60)
        .put("seville", 60)
        .put("valencia", 60)
        .put("malaga", 60)
        .put("las Palmas", 60)
        .put("zaragoza", 60)
        .put("alicante", 60)
        .put("alche", 60)
        .put("oviedo", 60)
        .put("gijón", 60)
        .put("avilés", 60)
        .build();

    public static final List<String> MajorLocations = Arrays.asList("Dominican Republic", "Dominica", "Guinea Bissau", "Guinea-Bissau", "Guinea", "Equatorial Guinea", "Papua New Guinea", "New York City", "New York", "York", "Mexico City", "New Mexico", "Mexico", "Aberdeen", "Adelaide", "Anaheim", "Atlanta", "Auckland", "Austin", "Bangkok", "Baltimore", "Baton Rouge", "Beijing", "Belfast", "Birmingham", "Bolton", "Boston", "Bournemouth", "Bradford", "Brisbane", "Bristol", "Calgary", "Canberra", "Cardiff", "Charlotte", "Chicago", "Christchurch", "Colchester", "Colorado Springs", "Coventry", "Dallas", "Denver", "Derby", "Detroit", "Dubai", "Dublin", "Dudley", "Dunedin", "Edinburgh", "Edmonton", "El Paso", "Glasgow", "Gold Coast", "Hamilton", "Hialeah", "Houston", "Ipswich", "Jacksonville", "Jersey City", "Kansas City", "Kingston-upon-Hull", "Leeds", "Leicester", "Lexington", "Lincoln", "Liverpool", "London", "Long Beach", "Los Angeles", "Louisville", "Lubbock", "Luton", "Madison", "Manchester", "Mansfield", "Melbourne", "Memphis", "Mesa", "Miami", "Middlesbrough", "Milan", "Milton Keynes", "Minneapolis", "Montréal", "Montreal", "Nashville", "New Orleans", "Newark", "Newcastle-upon-Tyne", "Newcastle", "Northampton", "Norwich", "Nottingham", "Oklahoma City", "Oldham", "Omaha", "Orlando", "Ottawa", "Perth", "Peterborough", "Philadelphia", "Phoenix", "Plymouth", "Portland", "Portsmouth", "Preston", "Québec City", "Quebec City", "Québec", "Quebec", "Raleigh", "Reading", "Redmond", "Richmond", "Rome", "San Antonio", "San Diego", "San Francisco", "San José", "Santa Ana", "Seattle", "Sheffield", "Southampton", "Southend-on-Sea", "Spokane", "St Louis", "St Paul", "St Petersburg", "St. Louis", "St. Paul", "St. Petersburg", "Stockton-on-Tees", "Stockton", "Stoke-on-Trent", "Sunderland", "Swansea", "Swindon", "Sydney", "Tampa", "Tauranga", "Telford", "Toronto", "Vancouver", "Virginia Beach", "Walsall", "Warrington", "Washington", "Wellington", "Wolverhampton", "Abilene", "Akron", "Albuquerque", "Alexandria", "Allentown", "Amarillo", "Anchorage", "Ann Arbor", "Antioch", "Arlington", "Arvada", "Athens", "Augusta", "Aurora", "Bakersfield", "Beaumont", "Bellevue", "Berkeley", "Billings", "Boise", "Boulder", "Bridgeport", "Broken Arrow", "Brownsville", "Buffalo", "Burbank", "Cambridge", "Cape Coral", "Carlsbad", "Carrollton", "Cary", "Cedar Rapids", "Centennial", "Chandler", "Charleston", "Chattanooga", "Chengdu", "Chesapeake", "Chongqing", "Chula Vista", "Cincinnati", "Clarksville", "Clearwater", "Cleveland", "Clovis", "College Station", "Columbia", "Columbus", "Concord", "Coral Springs", "Corona", "Costa Mesa", "Daly City", "Davenport", "Dayton", "Denton", "Des Moines", "Downey", "Durham", "Edison", "El Cajon", "El Monte", "Elgin", "Elizabeth", "Elk Grove", "Erie", "Escondido", "Eugene", "Evansville", "Everett", "Fairfield", "Fargo", "Farmington Hills", "Fayetteville", "Fontana", "Fort Collins", "Fort Lauderdale", "Fort Wayne", "Fort Worth", "Fremont", "Fresno", "Frisco", "Fullerton", "Gainesville", "Garden Grove", "Garland", "Gilbert", "Glendale", "Grand Prairie", "Grand Rapids", "Green Bay", "Greensboro", "Gresham", "Guangzhou", "Hampton", "Hartford", "Hayward", "Henderson", "High Point", "Hollywood", "Honolulu", "Huntington Beach", "Huntsville", "Independence", "Indianapolis", "Inglewood", "Irvine", "Irving", "Jackson", "Joliet", "Kent", "Killeen", "Knoxville", "Lafayette", "Lakeland", "Lakewood", "Lancaster", "Lansing", "Laredo", "Las Cruces", "Las Vegas", "Lewisville", "Little Rock", "Lowell", "Macon", "McAllen", "McKinney", "Mesquite", "Miami Gardens", "Midland", "Milwaukee", "Miramar", "Mobile", "Modesto", "Montgomery", "Moreno Valley", "Murfreesboro", "Murrieta", "Naperville", "New Haven", "Newport News", "Norfolk", "Norman", "North Charleston", "North Las Vegas", "Norwalk", "Oakland", "Oceanside", "Odessa", "Olathe", "Ontario", "Orange", "Overland Park", "Oxnard", "Palm Bay", "Palmdale", "Pasadena", "Paterson", "Pearland", "Pembroke Pines", "Peoria", "Pittsburgh", "Plano", "Pomona", "Pompano Beach", "Providence", "Provo", "Pueblo", "Rancho Cucamonga", "Reno", "Rialto", "Richardson", "Riverside", "Rochester", "Rockford", "Roseville", "Round Rock", "Sacramento", "Saint Paul", "Salem", "Salinas", "Salt Lake City", "San Bernardino", "San Jose", "San Mateo", "Sandy Springs", "Santa Clara", "Santa Clarita", "Santa Maria", "Santa Rosa", "Savannah", "Scottsdale", "Shanghai", "Shenyang", "Shenzhen", "Shreveport", "Simi Valley", "Sioux Falls", "South Bend", "Springfield", "Stamford", "Sterling Heights", "Sunnyvale", "Surprise", "Suzhou", "Syracuse", "Tacoma", "Tallahassee", "Temecula", "Tempe", "Thornton", "Thousand Oaks", "Tianjing", "Toledo", "Topeka", "Torrance", "Tucson", "Tulsa", "Tyler", "Vallejo", "Ventura", "Victorville", "Visalia", "Waco", "Warren", "Waterbury", "West Covina", "West Jordan", "West Palm Beach", "West Valley City", "Westminster", "Wichita", "Wichita Falls", "Wilmington", "Winston-Salem", "Worcester", "Wuxi", "Xiamen", "Yonkers", "Bentonville", "Afghanistan", "AK", "AL", "Alabama", "Åland", "Åland Islands", "Alaska", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "AR", "Argentina", "Arizona", "Arkansas", "Armenia", "Aruba", "Australia", "Austria", "AZ", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bonaire", "Bosnia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "CA", "Cabo Verde", "California", "Cambodia", "Cameroon", "Canada", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "CO", "Cocos Islands", "Colombia", "Colorado", "Comoros", "Congo", "Congo (DRC)", "Connecticut", "Cook Islands", "Costa Rica", "Côte d’Ivoire", "Croatia", "CT", "Cuba", "Curaçao", "Cyprus", "Czechia", "DE", "Delaware", "Denmark", "Djibouti", "Ecuador", "Egypt", "El Salvador", "Eritrea", "Estonia", "eSwatini", "Ethiopia", "Falkland Islands", "Falklands", "Faroe Islands", "Fiji", "Finland", "FL", "Florida", "France", "French Guiana", "French Polynesia", "French Southern Territories", "FYROM", "GA", "Gabon", "Gambia", "Georgia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guyana", "Haiti", "Hawaii", "Herzegovina", "HI", "Honduras", "Hong Kong", "Hungary", "IA", "Iceland", "ID", "Idaho", "IL", "Illinois", "IN", "India", "Indiana", "Indonesia", "Iowa", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Ivory Coast", "Jamaica", "Jan Mayen", "Japan", "Jersey", "Jordan", "Kansas", "Kazakhstan", "Keeling Islands", "Kentucky", "Kenya", "Kiribati", "Korea", "Kosovo", "KS", "Kuwait", "KY", "Kyrgyzstan", "LA", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Louisiana", "Luxembourg", "MA", "Macao", "Macedonia", "Madagascar", "Maine", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Maryland", "Massachusetts", "Mauritania", "Mauritius", "Mayotte", "MD", "ME", "MI", "Michigan", "Micronesia", "Minnesota", "Mississippi", "Missouri", "MN", "MO", "Moldova", "Monaco", "Mongolia", "Montana", "Montenegro", "Montserrat", "Morocco", "Mozambique", "MS", "MT", "Myanmar", "Namibia", "Nauru", "NC", "ND", "NE", "Nebraska", "Nepal", "Netherlands", "Nevada", "New Caledonia", "New Hampshire", "New Jersey", "New Zealand", "NH", "Nicaragua", "Niger", "Nigeria", "Niue", "NJ", "NM", "Norfolk Island", "North Carolina", "North Dakota", "North Korea", "Northern Mariana Islands", "Norway", "NV", "NY", "OH", "Ohio", "OK", "Oklahoma", "Oman", "OR", "Oregon", "PA", "Pakistan", "Palau", "Palestinian Authority", "Panama", "Paraguay", "Pennsylvania", "Peru", "Philippines", "Pitcairn Islands", "Poland", "Portugal", "Puerto Rico", "Qatar", "Réunion", "Rhode Island", "RI", "Romania", "Russia", "Rwanda", "Saba", "Saint Barthélemy", "Saint Kitts and Nevis", "Saint Lucia", "Saint Martin", "Saint Pierre and Miquelon", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "São Tomé and Príncipe", "Saudi Arabia", "SC", "SD", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Sint Eustatius", "Sint Maarten", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Carolina", "South Dakota", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Tennessee", "Texas", "Thailand", "Timor-Leste", "TN", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "TX", "U.S. Outlying Islands", "US Outlying Islands", "U.S. Virgin Islands", "US Virgin Islands", "Uganda", "UK", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "US", "USA", "UT", "Utah", "Uzbekistan", "VA", "Vanuatu", "Vatican City", "Venezuela", "Vermont", "Vietnam", "Virginia", "VT", "WA", "Wallis and Futuna", "West Virginia", "WI", "Wisconsin", "WV", "WY", "Wyoming", "Yemen", "Zambia", "Zimbabwe", "Paris", "Tokyo", "Shanghai", "Sao Paulo", "Rio de Janeiro", "Rio", "Brasília", "Brasilia", "Recife", "Milan", "Mumbai", "Moscow", "Frankfurt", "Munich", "Berlim", "Madrid", "Lisbon", "Warsaw", "Johannesburg", "Seoul", "Istanbul", "Kuala Kumpur", "Jakarta", "Amsterdam", "Brussels", "Valencia", "Seville", "Bilbao", "Malaga", "Las Palmas", "Zaragoza", "Alicante", "Elche", "Oviedo", "Gijón", "Avilés", "West Coast", "Central", "Pacific", "Eastern", "Mountain");
}