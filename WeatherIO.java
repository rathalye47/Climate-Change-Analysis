package climatechange;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class WeatherIO implements IWeatherIO {
	private ArrayList<ITemperature> allData;
	
	public WeatherIO()
	{
		allData = new ArrayList<ITemperature>();
	}

	// 
	// Reads all the data from the weather data file.
	//
	@Override
	public ArrayList<ITemperature> readDataFromFile(String fileName) throws FileNotFoundException {
		File inputFile = new File(fileName); // Creates a new file based on the fileName.
		Scanner scan = new Scanner(inputFile); // Instantiates a Scanner to read the data.
		
		while (scan.hasNextLine())
		{
			String line = scan.nextLine(); // Reads each line from the weather data file.
			
			if (line.startsWith("Temperature"))
			{
				continue; // Skips the first line in the weather data file, which is the subject header.
			}
			
			String[] dataValues = line.split(","); // Splits the line by the comma.
			
			double temperature = Double.parseDouble(dataValues[0].trim()); // Parses the temperatures into double values.
			int year = Integer.parseInt(dataValues[1].trim()); // Parses the years into integer values.
			String month = dataValues[2].trim(); // Parses the months into String values.
			String countryName = dataValues[3].trim(); // Parses the names of the countries into String values.
			String countryCode = dataValues[4].trim(); // Parses the 3-Letter Codes of the countries into String values.
			ITemperature data = new Temperature(temperature, year, month, countryName, countryCode);
			allData.add(data);			
		}
		
		scan.close(); // Closes the Scanner.
		
		return allData;
	}

	//
	// Writes the subject header before dumping data returned from each ClimateAnalyzer method.
	//
	@Override
	public void writeSubjectHeaderInFile(String filename, String subject) throws IOException {
		File inputFile = new File(filename); // Creates a file based on the filename.
		FileWriter fWriter = new FileWriter(inputFile, true); // Creates a FileWriter.
		PrintWriter pWriter = new PrintWriter(fWriter); // Creates a PrintWriter.
		pWriter.write(subject); // Writes the subject header. 
		pWriter.write("");
		pWriter.close(); // Closes the PrintWriter.		
	}

	//
	// Writes the temperature information for each ClimateAnalyzer task to a file.
	//
	@Override
	public void writeDataToFile(String filename, String topic, ArrayList<ITemperature> theWeatherList) throws IOException {
		//File inputFile = new File(filename); // Creates a file based on the filename.
		FileWriter fWriter = new FileWriter(filename, true); // Creates a FileWriter.
		PrintWriter pWriter = new PrintWriter(fWriter); // Creates a PrintWriter.
		pWriter.write("\n"); // Appends a new line to the file.
		pWriter.write(topic); // Appends the topic to the file.
		pWriter.write("\n"); // Appends a new line to the file.
		
		for (ITemperature temp : theWeatherList)
		{
			pWriter.write(Math.round(temp.getTemperature(false) * 100.0) / 100.0 + "(C) " + Math.round(temp.getTemperature(true) * 100.0) / 100.0 + "(F)"); // Appends the temperature values to the file with the correct formatting.
			pWriter.write(", "); // Appends a comma to the file.
			pWriter.write(String.valueOf(temp.getYear())); // Appends the year to the file.
			pWriter.write(", "); // Appends a comma to the file.
			pWriter.write(temp.getMonth()); // Appends the month to the file.
			pWriter.write(", "); // Appends a comma to the file.
			pWriter.write(temp.getCountry()); // Appends the name of the country to the file.
			pWriter.write(", "); // Appends a comma to the file.
			pWriter.write(temp.getCountry3LetterCode()); // Appends the 3-Letter Code of the country to the file.
			pWriter.write("\n"); // Appends a new line to the file.
		}
		
		pWriter.write("");
		pWriter.close(); // Closes the PrintWriter.
	}
	
	//
	// Tests to see if readDataFromFile() is reading the weather data properly.
	//
	public static void main(String[] args) throws FileNotFoundException
	{		
		WeatherIO wIO = new WeatherIO();
		System.out.println(wIO.readDataFromFile("data/world_temp_2000-2016.csv"));
	}
}
