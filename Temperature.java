package climatechange;

import java.util.HashMap;

public class Temperature implements ITemperature, Comparable<Temperature>{
	private double temperature;
	private int year;
	private String month;
	private String countryName;
	private String countryCode;
	
	public Temperature(double theTemperature, int theYear, String theMonth, String theCountryName, String theCountryCode)
	{
		temperature = theTemperature;
		year = theYear;
		month = theMonth;
		countryName = theCountryName;
		countryCode = theCountryCode;
	}
	
	//
	// Gets the name of the country.
	//
	@Override
	public String getCountry() {
		return countryName;
	}

	//
	// Gets the 3-Letter Code of the country.
	//
	@Override
	public String getCountry3LetterCode() {
		return countryCode;
	}

	//
	// Gets the month.
	//
	@Override
	public String getMonth() {
		return month;
	}

	//
	// Gets the year.
	//
	@Override
	public int getYear() {
		return year;
	}

	//
	// Gets the temperature. If getFahrenheit is false, the Celsius value is returned. Otherwise, the Fahrenheit value is returned.
	//
	@Override
	public double getTemperature(boolean getFahrenheit) {
		if (getFahrenheit == false)
		{
			return temperature;
		}
		
		return (1.8 * temperature) + 32;
	}
	
	//
	// Overrides the toString() method from the Object superclass. This makes it easier to read Temperature objects as opposed to reading
	// object references. This helps me test methods in ClimateAnalyzer, so I can clearly read the output.
	//
	public String toString()
	{
		String allTemps = "Temperature: " + temperature + " / ";
		String allYears = "Year: " + year + " / ";
		String allMonths = "Month: " + month + " / ";
		String allCountries = "Country Name: " + countryName + " / ";
		String allCodes = "Country Code: " + countryCode;
		return allTemps + allYears + allMonths + allCountries + allCodes;
	}
	
	//
	// Maps String values of months to integer values. This will be used in the compareTo() method, so we can compare the earliest months 
	// based on their integer values as opposed to their String values.
	//
	public int mapMonthsToIntegers(String monthString)
	{
		HashMap<String, Integer> stringMonthToIntegerMonth = new HashMap<String, Integer>();
		// Each String key is unique and corresponds to a unique integer value.
		stringMonthToIntegerMonth.put("Jan", 1);
		stringMonthToIntegerMonth.put("Feb", 2);
		stringMonthToIntegerMonth.put("Mar", 3);
		stringMonthToIntegerMonth.put("Apr", 4);
		stringMonthToIntegerMonth.put("May", 5);
		stringMonthToIntegerMonth.put("Jun", 6);
		stringMonthToIntegerMonth.put("Jul", 7);
		stringMonthToIntegerMonth.put("Aug", 8);
		stringMonthToIntegerMonth.put("Sep", 9);
		stringMonthToIntegerMonth.put("Oct", 10);
		stringMonthToIntegerMonth.put("Nov", 11);
		stringMonthToIntegerMonth.put("Dec", 12);
				
		int monthInteger = stringMonthToIntegerMonth.get(monthString);
		return monthInteger;
	}

	//
	// Compares two Temperature objects by their temperatures. If the temperatures are the same, then we compare by the names of the
	// countries. If the names of the countries are the same, then we compare by the years. If the years are the same, then we compare
	// by the months.
	//
	public int compareTo(Temperature other) {
		if (Double.compare(this.temperature, other.temperature) != 0)
		{
			return Double.compare(this.temperature, other.temperature);
		}
		
		if (this.countryName.compareTo(other.countryName) != 0)
		{
			return this.countryName.compareTo(other.countryName);
		}
		
		if (Integer.compare(this.year, other.year) != 0)
		{
			return Integer.compare(this.year, other.year);
		}
		
		return Integer.compare(mapMonthsToIntegers(this.month), mapMonthsToIntegers(other.month));
	}
	
	//
	// Tests if 2 Temperature objects are equal to each other.
	//
	public boolean equals(Object other)
	{
		Temperature that = (Temperature) other;
		return this.compareTo(that) == 0;
	}
	
	//
	// Gets the sum of all the hashCodes.
	//
	public int hashCode()
	{
		int temperatureInteger = (int) temperature;
		return temperatureInteger + year + month.hashCode() + countryName.hashCode() + countryCode.hashCode();
	}
	
}
